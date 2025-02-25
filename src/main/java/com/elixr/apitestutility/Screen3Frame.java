/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elixr.apitestutility;

import static com.elixr.apitestutility.Screen2.showErrorDialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Screen3Frame class represents a JFrame that displays test results in a table
 * format. It includes functionality to format and display JSON data in table
 * cells, navigate back to the previous screen, and manage the frame state.
 */
public class Screen3Frame extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(Screen3Frame.class);
    private JFrame previousFrame;
    private String url;
    private String methodType;
    private Map<String, String> headers = new HashMap<>();
    private Object[][] jsonTableBody;
    private String nameFromScreen1;

    /**
     * Constructs a new Screen3Frame with the given data and previousState.
     *
     * @param previousFrame the previous screen's frame, used for navigation.
     * @param testScenarios the data to populate the result table.
     * @param previousState the previousState of the frame (e.g., maximized,
     * normal).
     * @param url The base URL of the request.
     * @param methodType The HTTP method (e.g., GET, POST).
     * @param headers The Map<String,String> containing HTTP headers.
     */
    public Screen3Frame(JFrame previousFrame, int previousState, Object[][] testScenarios, String url, String methodType, Map<String, String> headers, String nameFromScreen1) {
        logger.info("Initializing Screen3Frame with data and previous state.");
        this.previousFrame = previousFrame;
        initComponents();
        setupFrame(previousState);
        this.url = url;
        this.methodType = methodType;
        this.headers = headers;
        this.jsonTableBody = testScenarios;  // Assign jsonTableBody
        this.nameFromScreen1 = nameFromScreen1;
        setUpUrlMethodAndHeaders();
        populateResultTable(testScenarios);
    }

    /**
     * Constructs the Screen3Frame.
     *
     * Initializes the UI components. Extracts and processes the provided URL,
     * method type, headers, and test cases. Parses headers from a
     * comma-separated string into a key-value map. Extracts the name from the
     * filename. Calls methods to set up the URL, method type, headers, and
     * populate the result table.
     *
     * @param previosuFrame The previous JFrame instance (Screen 2).
     * @param url The API endpoint to be tested.
     * @param method The HTTP method (GET, POST, etc.).
     * @param headers The request headers in a comma-separated string format.
     * @param testCases The test cases represented as a 2D Object array.
     * @param fileName The name of the test file, which contains the test name.
     */
    public Screen3Frame(JFrame previosuFrame, String url, String method, String headers, Object[][] testCases, String fileName) {
        initComponents();
        setupFrame(MAXIMIZED_BOTH);
        this.previousFrame = previosuFrame;
        this.url = url;
        this.methodType = method;
        if (!headers.equals("")) {
            String[] headerPairs = headers.split(",");
            for (String header : headerPairs) {
                // Split each header pair by colon to separate key and value
                String[] keyValue = header.split(":", 2);  // Limit to 2 parts in case the value contains ":"
                if (keyValue.length == 2) {
                    this.headers.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        }
        this.jsonTableBody = testCases;
        String[] name = fileName.split("_");
        this.nameFromScreen1 = name[0];
        setUpUrlMethodAndHeaders();
        populateResultTable(testCases);
    }

    /**
     * Extracts and sets up the URL, method type, and headers in the UI.
     */
    private void setUpUrlMethodAndHeaders() {
        logger.debug("Setting up URL, Method, and Headers for Screen 3.");
        try {
            URL fullUrlObj = new URL(url);
            String protocol = fullUrlObj.getProtocol();
            String host = fullUrlObj.getHost();
            String fullPath = fullUrlObj.getPath();
            protocolValueLabel.setText(protocol);
            baseUrlTextField.setText(host);
            pathValueLabel.setText(fullPath);
            methodValueLabel.setText(methodType.toUpperCase());
            StringBuilder headersText = new StringBuilder();
            if (!headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    headersText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
            } else {
                headersText.append("No headers available");
            }
            headersTextArea.setText(headersText.toString());
            headersTextArea.setEditable(true);
            addBaseUrlChangeListener();
            addHeadersChangeListener();

        } catch (MalformedURLException e) {
            logger.error("Invalid URL format: {}", url, e);
        }
    }

    /**
     * Adds a listener to track changes in the headers text area.
     *
     * This listener updates the `updatedHeaders` map whenever the user modifies
     * the headers.
     */
    private void addHeadersChangeListener() {
        headersTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateHeadersFromTextArea();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateHeadersFromTextArea();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateHeadersFromTextArea();
            }
        });
    }

    /**
     * Extracts headers from the text area and updates the `updatedHeaders` map.
     *
     * Clears existing headers before adding new ones. Splits each line by ":"
     * to separate header keys and values. Ignores invalid header formats.
     */
    private void updateHeadersFromTextArea() {
        headers.clear();
        String[] lines = headersTextArea.getText().split("\n");
        for (String line : lines) {
            if (line.contains(":")) {
                String[] parts = line.split(":", 2);
                headers.put(parts[0].trim(), parts[1].trim());
            }
        }
    }

    /**
     * Adds a listener to track changes in the base URL text field. Updates the
     * 'URL' whenever the user_modifies the URL.
     */
    private void addBaseUrlChangeListener() {
        baseUrlTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                url = updateUrl(protocolValueLabel.getText(), pathValueLabel.getText(), baseUrlTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                url = updateUrl(protocolValueLabel.getText(), pathValueLabel.getText(), baseUrlTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                url = updateUrl(protocolValueLabel.getText(), pathValueLabel.getText(), baseUrlTextField.getText());
            }
        });
    }

    /**
     * Constructs a complete URL by combining the protocol, updated base URL,
     * and path. Ensures the path starts with a forward slash ("/") before
     * combining the components.
     *
     * @param protocol The protocol to use in the URL (e.g., "http" or "https").
     * @param path The path to append to the URL. If it doesn't start with "/",
     * one is added.
     * @param updatedUrl The base URL to which the path will be appended.
     * @return A complete URL in the format: `protocol://updatedUrl/path.
     */
    private String updateUrl(String protocol, String path, String updatedUrl) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return protocol + "://" + updatedUrl + path;
    }

    /**
     * Default constructor for Screen3Frame.
     */
    public Screen3Frame() {
        logger.info("Default constructor for Screen3Frame called.");
    }

    /**
     * Configures the frame's previousState and default close operation.
     *
     * @param previousState the previousState to set the frame to (e.g.,
     * maximized, normal).
     */
    private void setupFrame(int previousState) {
        logger.debug("Setting up frame with previous state: {}", previousState);
        this.setTitle("APITestUtility");
        setExtendedState(previousState);
        setLocationRelativeTo(null);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultTable.getTableHeader().setReorderingAllowed(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Populates the result table with the provided data and customizes its
     * appearance. Adds a serial number (SL) column and sets custom cell
     * renderers for JSON data.
     *
     * @param tableData the data to populate the table, with each row
     * representing a test case.
     */
    private void populateResultTable(Object[][] tableData) {
        logger.info("Populating result table with data.");
        Object[][] updatedTableData = new Object[tableData.length][7]; // 8 columns now

        for (int i = 0; i < tableData.length; i++) {
            updatedTableData[i][0] = true; // SL column with serial numbers starting from 1
            updatedTableData[i][1] = tableData[i][0]; // Test Name
            updatedTableData[i][2] = tableData[i][1]; // Request Body
        }

        DefaultTableModel model = new DefaultTableModel(
                updatedTableData,
                new String[]{"Select", "Test Name", "Request Body", "Response Code", "Response Body", "Response Time", "Test Result"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Only allow editing the checkbox coloum.
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Set the checkbox column to Boolean type
                }
                return super.getColumnClass(columnIndex);
            }
        };
        resultTable.setModel(model);
        resultTable.getColumnModel().getColumn(0).setMaxWidth(50); // Small width for SL
        resultTable.setRowHeight(125);
        resultTable.getColumnModel().getColumn(2).setCellRenderer(new JsonCellRenderer());
        resultTable.getColumnModel().getColumn(4).setCellRenderer(new JsonCellRenderer());
        DefaultTableCellRenderer borderedCellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    ((JLabel) c).setVerticalAlignment(SwingConstants.TOP); // Set vertical alignment to top
                }
                // Adds a black border around the cell.
                ((JLabel) c).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                return c;
            }
        };
        // Apply the bordered renderer to all columns except "Request Body"
        for (int col = 0; col < resultTable.getColumnCount(); col++) {
            if (col != 2 && col != 4) { // Skip JSON column to preserve its special rendering
                resultTable.getColumnModel().getColumn(col).setCellRenderer(borderedCellRenderer);
            }
        }
        resultTableScrollPane.revalidate();
        resultTableScrollPane.repaint();
        logger.info("Result table populated successfully.");
    }

    /**
     * Executes an HTTP request based on the provided request body, headers, and
     * method type.
     *
     * @param requestBody A JSON object representing the request body to be sent
     * with the HTTP request.
     * @return An Object array containing: - [0]: The request body as a string.
     * - [1]: The HTTP response code as an integer. - [2]: The HTTP response
     * body as a string.
     *
     * This method performs the following: 1. Configures an HTTP connection
     * using the provided URL and method type. 2. Adds custom headers to the
     * request if specified. 3. Sends the provided JSON request body if
     * applicable. 4. Captures the HTTP response code and response body. 5.
     * Returns the results as an Object array for further processing.
     *
     */
    private Object[] executeTest(String name, JSONObject requestBody) {
        Object[] resultData = new Object[3]; // To store test details

        try {
            // Debugging: Check headers before building the request
            if (headers == null || headers.isEmpty()) {
                System.out.println("No custom headers set. Only default headers will be used!");
            } else {
                System.out.println("Headers before request: " + headers);
            }
            // Build HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .method(methodType, (requestBody != null)
                            ? HttpRequest.BodyPublishers.ofString(requestBody.toString())
                            : HttpRequest.BodyPublishers.noBody());
            // Add only custom headers
            if (!headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    requestBuilder.header(entry.getKey(), entry.getValue());
                }
            }
            HttpRequest request = requestBuilder.build();
            logger.info("Executing test: name={}, url={}, method={}, headers={}", name, url, methodType, headers);
            // System.out.println("Executing test with URL: " + url);

            // capture the start time
            long startTime = System.currentTimeMillis();
            logger.info("Start time: {} ms", startTime);

            // Execute request
            logger.info("Sending API Request...");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // capture the end time.
            long endTime = System.currentTimeMillis();
            logger.info("End Time: {} ms", endTime);

            //calculate the response time
            long responceTime = endTime - startTime;
            logger.info("Total Response Time: {} ms", responceTime);

            resultData[0] = response.statusCode(); // Response Code
            resultData[1] = response.body(); // Response Body
            resultData[2] = responceTime + "ms"; //Response Time in milliseconds

            logger.info("Test executed: name={}, responseCode={}, responseBody={}, responceTime={} ms",
                    name, response.statusCode(), responceTime, response.body());

            // Log response headers
            System.out.println("Response Headers: " + response.headers().map());
            System.out.println("Request Headers: " + request.headers().toString());

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid URL format: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.error("Invalid URL: {}", e.getMessage(), e);
            return new Object[]{"Error", "Invalid URL format", "-"};

        } catch (Exception ex) {
            showErrorDialog(ex);
            logger.error("Exception during test execution", ex);
            return new Object[]{"Error", ex.getMessage(), "-"};
        }
        return resultData;
    }

    /**
     * Renders JSON data in a JTable cell with proper formatting and line
     * wrapping.
     */
    class JsonCellRenderer extends DefaultTableCellRenderer {

        private final JTextArea textArea = new JTextArea();

        public JsonCellRenderer() {
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Add border to JSON cells
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null) {
                try {
                    // Format the JSON value
                    String formattedJson = new JSONObject(value.toString()).toString(4); // Indent with 4 spaces
                    textArea.setText(formattedJson);
                } catch (Exception e) {
                    // If value is not valid JSON, display as is
                    textArea.setText(value.toString());
                }
            } else {
                textArea.setText("");
            }
            textArea.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            textArea.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            return textArea;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        resultTableScrollPane = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        executeTestBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        otherComponentsPanel = new javax.swing.JPanel();
        urlLabel = new javax.swing.JLabel();
        protocolValueLabel = new javax.swing.JLabel();
        baseUrlTextField = new javax.swing.JTextField();
        pathValueLabel = new javax.swing.JLabel();
        methodLabel = new javax.swing.JLabel();
        methodValueLabel = new javax.swing.JLabel();
        headersLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        headersTextArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        exportMenubar = new javax.swing.JMenu();
        cUrlConversionBtn = new javax.swing.JMenuItem();
        allTesttoExcel = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Result");

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        resultTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                resultTableMouseMoved(evt);
            }
        });
        resultTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                resultTableFocusLost(evt);
            }
        });
        resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultTableMouseClicked(evt);
            }
        });
        resultTableScrollPane.setViewportView(resultTable);

        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel1.add(backBtn);

        executeTestBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        executeTestBtn.setText("ExecuteTest");
        executeTestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeTestBtnActionPerformed(evt);
            }
        });
        jPanel1.add(executeTestBtn);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        otherComponentsPanel.setBackground(new java.awt.Color(255, 255, 255));
        otherComponentsPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        otherComponentsPanel.setForeground(new java.awt.Color(255, 255, 255));

        urlLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        urlLabel.setText("URL : ");

        protocolValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        protocolValueLabel.setText("https");
        protocolValueLabel.setToolTipText("Non Editable");
        protocolValueLabel.setAlignmentX(1.5F);
        protocolValueLabel.setAlignmentY(1.5F);
        protocolValueLabel.setPreferredSize(new java.awt.Dimension(50, 25));

        baseUrlTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        baseUrlTextField.setText("baseUrlTextField");
        baseUrlTextField.setToolTipText("Click To Edit");
        baseUrlTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        baseUrlTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseUrlTextFieldActionPerformed(evt);
            }
        });

        pathValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pathValueLabel.setText("pathValueLabel");
        pathValueLabel.setToolTipText("Non Editable");
        pathValueLabel.setPreferredSize(new java.awt.Dimension(150, 25));

        methodLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        methodLabel.setText("Method : ");

        methodValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        methodValueLabel.setText("methodValueLabel");
        methodValueLabel.setToolTipText("Non Editable");

        headersLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        headersLabel.setText("Headers : ");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        headersTextArea.setColumns(20);
        headersTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        headersTextArea.setRows(5);
        headersTextArea.setToolTipText("Editable Line Separated");
        headersTextArea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(headersTextArea);

        javax.swing.GroupLayout otherComponentsPanelLayout = new javax.swing.GroupLayout(otherComponentsPanel);
        otherComponentsPanel.setLayout(otherComponentsPanelLayout);
        otherComponentsPanelLayout.setHorizontalGroup(
            otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                        .addComponent(urlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(protocolValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(baseUrlTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pathValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                        .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(methodLabel)
                            .addComponent(headersLabel))
                        .addGap(18, 18, 18)
                        .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(methodValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1))
                        .addGap(27, 27, 27))))
        );
        otherComponentsPanelLayout.setVerticalGroup(
            otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baseUrlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(urlLabel)
                        .addComponent(protocolValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pathValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(methodLabel)
                    .addComponent(methodValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headersLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        exportMenubar.setText("Export");
        exportMenubar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportMenubar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportMenubarActionPerformed(evt);
            }
        });

        cUrlConversionBtn.setText("Selected Test To cURL");
        cUrlConversionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cUrlConversionBtnActionPerformed(evt);
            }
        });
        exportMenubar.add(cUrlConversionBtn);

        allTesttoExcel.setText("All Test To Excel");
        allTesttoExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allTesttoExcelActionPerformed(evt);
            }
        });
        exportMenubar.add(allTesttoExcel);

        jMenuBar1.add(exportMenubar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(resultTableScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addComponent(otherComponentsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(otherComponentsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the action performed when the back button is clicked. Navigates
     * to the previous screen if available; otherwise, displays an error
     * message.
     *
     * @param evt the action event triggered by the back button click.
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        logger.info("Back button clicked.");
        System.out.println(this.previousFrame);
        if (previousFrame != null) {
            logger.debug("Navigating back to previous frame.");
            previousFrame.setVisible(true);
            this.dispose();
        } else {
            logger.warn("No previous frame available to navigate back to.");
            JOptionPane.showMessageDialog(this, "No previous screen to navigate to!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * Handles the action when the Exit button is clicked.
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        logger.info("Exit button clicked.");
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?");
        if (confirm == 0) {
            logger.info("Application exit confirmed by the user.");
            System.exit(0);
        } else {
            logger.info("Application exit canceled by the user.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Handles mouse click events on the result table. If the user clicks on
     * columns 2 (Request Body) or 4 (Response Body), a popup dialog is shown
     * displaying the full JSON content in a formatted view.
     *
     * @param evt
     */
    private void resultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTableMouseClicked
        // TODO add your handling code here:
        int row = resultTable.rowAtPoint(evt.getPoint());
        int col = resultTable.columnAtPoint(evt.getPoint());
        if (col == 2 || col == 4) { // Open popup only for JSON columns
            Object value = resultTable.getValueAt(row, col);
            if (value != null) {
                if (value.toString().equalsIgnoreCase("Empty Request Body")
                        || value.toString().equalsIgnoreCase("")) {
                    return;
                }
                String formattedJson = new JSONObject(value.toString()).toString(4);
                JTextArea textArea = new JTextArea(formattedJson);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(600, 400)); // Adjust popup size
                resultTable.clearSelection();
                JOptionPane.showMessageDialog(
                        null, scrollPane, "Full JSON View", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_resultTableMouseClicked

    /**
     * Handles mouse movement over the result table.
     *
     * @param evt
     */
    private void resultTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTableMouseMoved
        // TODO add your handling code here:
        int col = resultTable.columnAtPoint(evt.getPoint());
        if (col == 2 || col == 4) { // "Request Body" & "Response Body" columns
            resultTable.setToolTipText("Click to open full content");
        } else {
            resultTable.setToolTipText(null);
        }
    }//GEN-LAST:event_resultTableMouseMoved

    private void resultTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_resultTableFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_resultTableFocusLost

    /**
     * Handles the action when the "Execute Test" button is clicked. Retrieves
     * the selected row from the result table. Extracts the request body JSON
     * from the selected row. Shows a loading dialog while executing the test in
     * a background thread. Displays an error message if no test is selected.
     *
     * @param evt
     */
    private void executeTestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeTestBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = resultTable.getSelectedRow();
        JSONObject jsonBody;
        if (selectedRow != -1) {
            logger.debug("Test selected: {}", resultTable.getSelectedRow());
            Object requestBody = resultTable.getValueAt(selectedRow, 2);
            if (requestBody == null || requestBody.toString().equalsIgnoreCase("Empty Request Body") || requestBody.toString().equalsIgnoreCase("")) {
                jsonBody = null;
            } else {
                jsonBody = (JSONObject) requestBody;
            }
            String testName = (String) resultTable.getValueAt(selectedRow, 1);
            JDialog loadingDialog = createLoadingDialog("Executing Test,Please Wait");
            SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));
            new SwingWorker<Object[], Void>() {
                @Override
                protected Object[] doInBackground() throws Exception {
                    Object[] responseResult = null;
                    try {
                        responseResult = executeTest(testName, jsonBody);
                    } catch (Exception ex) {
                        Screen2.showErrorDialog(ex);
                        logger.error("Error during test execution.");
                    }
                    return responseResult;
                }

                @Override
                protected void done() {
                    try {
                        Object[] responseData = get();
                        SwingUtilities.invokeLater(() -> {
                            loadingDialog.dispose();
                            resultTable.setValueAt(responseData[0], selectedRow, 3);
                            resultTable.setValueAt(responseData[1], selectedRow, 4);
                            resultTable.setValueAt(responseData[2], selectedRow, 5);
                            resultTable.clearSelection();
                        });
                    } catch (Exception ex) {
                        logger.error("Error retrieving results from background task", ex);
                        showErrorDialog(ex);
                        SwingUtilities.invokeLater(loadingDialog::dispose);
                    }
                }
            }.execute();
            logger.info("Executing Test");
        } else {
            logger.warn("No test selected for executing");
            JOptionPane.showMessageDialog(this, "Please Select a Test To Execute", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_executeTestBtnActionPerformed

    private void baseUrlTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseUrlTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_baseUrlTextFieldActionPerformed

    private void exportMenubarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportMenubarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_exportMenubarActionPerformed

    private void cUrlConversionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cUrlConversionBtnActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int selectedRow = resultTable.getSelectedRow();
        String jsonBody;
        if (selectedRow != -1) {
            logger.debug("Test selected: {}", resultTable.getSelectedRow());
            Object requestBody = resultTable.getValueAt(selectedRow, 2);
            if (requestBody == null || requestBody.toString().equalsIgnoreCase("Empty Request Body")) {
                jsonBody = "";
            } else {
                jsonBody = requestBody.toString();
            }
        } else {
            logger.warn("No test selected for converting to cURL");
            JOptionPane.showMessageDialog(this, "Please Select a Test", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String cUrlCommand = "";
        try {
            cUrlCommand = generateCurlCommand(jsonBody);
        } catch (Exception e) {
            showErrorDialog(e);
            logger.error("Some error occured", e);
        }
        if (!cUrlCommand.equals("")) {
            JFrame displayCurl = new JFrame("Generated cURL Command");
            displayCurl.setSize(600, 400);
            JTextArea textArea = new JTextArea(cUrlCommand);
            textArea.setEditable(false);  // Make text area non-editable
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));  // Use monospaced font for better readability
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 250));
            displayCurl.getContentPane().add(scrollPane, BorderLayout.CENTER);
            displayCurl.setLocationRelativeTo(this);
            displayCurl.setVisible(true);
        }
    }//GEN-LAST:event_cUrlConversionBtnActionPerformed

    private void allTesttoExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allTesttoExcelActionPerformed
        // TODO add your handling code here:
        try {
            // Generate timestamp for the filename
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = nameFromScreen1 + "_" + timestamp + ".xlsx";
            // Call the export function
            ExcelExporter.exportDataToExcel(url, methodType, headers, jsonTableBody, fileName);
            JOptionPane.showMessageDialog(this, "Data exported successfully to " + fileName);
        } catch (HeadlessException e) {
            showErrorDialog(e);
            logger.error("Error exporting data", e);
        } catch (Exception e) {
            showErrorDialog(e);
            logger.error("Error exporting data", e);
        }
    }//GEN-LAST:event_allTesttoExcelActionPerformed

    private String generateCurlCommand(String jsonBody) {
        StringBuilder curlCommand = new StringBuilder("curl -X " + methodType.toUpperCase());
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            curlCommand.append(" -H \"").append(entry.getKey()).append(": ").append(entry.getValue()).append("\"");
        }
        // Add Request Body (only for methods like POST, PUT, PATCH)
        if (jsonBody != null && (methodType.equalsIgnoreCase("POST") || methodType.equalsIgnoreCase("PUT") || methodType.equalsIgnoreCase("PATCH"))) {
            curlCommand.append(" -d '").append(jsonBody).append("'");
        }
        curlCommand.append(" \"").append(url).append("\"");
        return curlCommand.toString();
    }

    /**
     * Creates and returns a non-modal loading dialog with a progress bar.
     *
     * @param name to show on the loading dialog
     * @return Returns a loading dialog
     */
    public JDialog createLoadingDialog(String name) {
        JDialog dialog = new JDialog(this, "Processing", false); // Set modal to false
        dialog.setSize(250, 100);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        JLabel loadingLabel = new JLabel(name, SwingConstants.CENTER);
        Timer timer = new Timer(500, new ActionListener() {
            private int dots = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                dots = (dots + 1) % 4;
                loadingLabel.setText(name + ".".repeat(dots));
            }
        });
        timer.start();// Show infinite loading animation
        dialog.add(loadingLabel, BorderLayout.CENTER);
        return dialog;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Screen3Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screen3Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screen3Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screen3Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Screen3Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem allTesttoExcel;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField baseUrlTextField;
    private javax.swing.JMenuItem cUrlConversionBtn;
    private javax.swing.JButton executeTestBtn;
    private javax.swing.JMenu exportMenubar;
    private javax.swing.JLabel headersLabel;
    private javax.swing.JTextArea headersTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel methodLabel;
    private javax.swing.JLabel methodValueLabel;
    private javax.swing.JPanel otherComponentsPanel;
    private javax.swing.JLabel pathValueLabel;
    private javax.swing.JLabel protocolValueLabel;
    private javax.swing.JTable resultTable;
    private javax.swing.JScrollPane resultTableScrollPane;
    private javax.swing.JLabel urlLabel;
    // End of variables declaration//GEN-END:variables
}
