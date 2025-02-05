/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elixr.apitestutility;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 * The Screen2 class represents a secondary frame in the application, providing
 * functionality for displaying and managing JSON request bodies, HTTP headers,
 * and other details. It allows users to customize request data, view headers,
 * and execute tests.
 */
public class Screen2 extends javax.swing.JFrame {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Screen2.class);
    private String name;
    private Screen1 previousFrame;
    private int deleteValueSelectedRow;
    private JSONObject jsonRequestBodyObject;
    private String url;
    private String methodType;
    private Map<String, String> headers = new HashMap<>();

    /**
     * Default constructor for Screen2. Initializes the components of the frame.
     */
    public Screen2() {
        logger.info("Screen2 initialized with default constructor");
        this.deleteValueSelectedRow = -1;
        initComponents();
    }

    /**
     * Constructor for Screen2 with parameters to set up the frame.
     *
     * @param previousFrame The parent Screen1 instance for navigation purposes.
     * @param jsonRequestBodyObject
     * @param jsonRequestBodyTableData A 2D array of data representing JSON
     * request bodies.
     * @param baseUrl The base URL of the request.
     * @param method The HTTP method (e.g., GET, POST).
     * @param name The name or identifier for the current screen.
     * @param headersTableModel The table model containing HTTP headers.
     * @param previousState The previous frame state (e.g., maximized).
     */
    public Screen2(Screen1 previousFrame, JSONObject jsonRequestBodyObject, Object[][] jsonRequestBodyTableData, String baseUrl, String method, String name, DefaultTableModel headersTableModel, int previousState) {
        this.deleteValueSelectedRow = -1;
        this.jsonRequestBodyObject = jsonRequestBodyObject;
        this.previousFrame = previousFrame;
        this.name = name;
        logger.info("Screen2 initialized with parameters: url={}, method={}, name={}", baseUrl, method, name);
        initComponents();
        setupFrame(previousState);
        setUpComponents(jsonRequestBodyTableData, baseUrl, method, headersTableModel);
    }

    /**
     * Configures and sets up the components of the screen using the provided
     * data. This includes populating the JSON request body table, displaying
     * the base URL and method, and formatting headers for display.
     *
     * @param jsonRequestBodyTableData An array of objects representing the rows
     * of the JSON request body table. Each row contains information about a
     * field and its properties.
     * @param baseUrl The base URL to be displayed on the screen.
     * @param method The HTTP method (e.g., GET, POST) to be displayed.
     * @param headersTableModel A table model containing headers to be displayed
     * in a formatted way.
     */
    private void setUpComponents(Object[][] jsonRequestBodyTableData, String baseUrl, String method, DefaultTableModel headersTableModel) {
        logger.debug("Setting up components for Screen2 with URL: {} and Method: {}", baseUrl, method);
        topComponentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Vertical scrollbar only when needed
        topComponentsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        if (jsonRequestBodyTableData != null) {
            for (Object[] row : jsonRequestBodyTableData) {
                if (row[1] == "String") {
                    row[1] = "String";
                }
            }
            logger.info("Populating JSON table with {} rows", jsonRequestBodyTableData.length);
            jsonTable.setModel(new DefaultTableModel(jsonRequestBodyTableData, new String[]{
                "Field", "Data Type", "Default Data", "Positive Data", "Negative Data", "Error Message"
            }) {
                Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column == 5; // Allow edits for relevant columns
                }
            });
            customizeTable();
        } else {
            jsonTable.setEnabled(false);
        }
        urlValueLabel.setText(baseUrl);
        url = baseUrl;
        methodType = method.toUpperCase();
        methodValueLabel.setText(methodType);
        //  to display the Headers in Screen 2.
        if (headersTableModel.getRowCount() != 0) {
            StringBuilder headersText = new StringBuilder("<html>"); // Start HTML formatting
            int rowCount = headersTableModel.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                String headerName = (String) headersTableModel.getValueAt(i, 0);
                String headerValue = (String) headersTableModel.getValueAt(i, 1);
                this.headers.put(headerName, headerValue);

                headersText.append(headerName).append(": ").append(headerValue);
                // Only add "<br>" if it's NOT the last item
                if (i < rowCount - 1) {
                    headersText.append(",<br>");
                }
            }
            headersText.append("</html>"); // End HTML formatting
            logger.info("Headers set up: {}", headers);
            headersValueLabel.setText(headersText.toString());
        } else {
            logger.warn("No headers available to set up");
            headersValueLabel.setText("<html>No headers available</html>");
        }
    }

    /**
     * Constructor for Screen2 with only a reference to the previous frame.
     *
     * @param previousFrame The parent Screen1 instance for navigation.
     */
    public Screen2(Screen1 previousFrame) {
        this.deleteValueSelectedRow = -1;
    }

    /**
     * Configures the frame's initial setup.
     *
     * @param previousState The previousState of the frame (e.g., maximized,
     * minimized).
     */
    private void setupFrame(int previousState) {
        logger.debug("Setting up the frame with state: {}", previousState);
        this.setTitle("APITestUtility");
        jsonTable.getTableHeader().setReorderingAllowed(false);
        setExtendedState(previousState);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Customizes the JSON table to use a combo box for selecting data types in
     * the "Data Type" column.
     */
    private void customizeTable() {
        // Get the "Data Type" column (index 1)
        TableColumn dataTypeColumn = jsonTable.getColumnModel().getColumn(1);
        // Create a combo box with options
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Integer", "Boolean", "String"});
        // Set the combo box as the editor for the column
        dataTypeColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        requestBodyScrollPane = new javax.swing.JScrollPane();
        jsonTable = new javax.swing.JTable();
        addValueBtn = new javax.swing.JButton();
        deleteValueBtn = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        executeTestbtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        topComponentsScrollPane = new javax.swing.JScrollPane();
        otherComponentsPanel = new javax.swing.JPanel();
        urlLabel = new javax.swing.JLabel();
        methodLabel = new javax.swing.JLabel();
        headersLabel = new javax.swing.JLabel();
        urlValueLabel = new javax.swing.JLabel();
        methodValueLabel = new javax.swing.JLabel();
        headersValueLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Request Field Data and Validations");

        jsonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Field", "Datatype","Default Value", "Positive Data", "Negative Data", "Error Message"
            }
        ));
        jsonTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jsonTablePropertyChange(evt);
            }
        });
        requestBodyScrollPane.setViewportView(jsonTable);

        addValueBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addValueBtn.setText("Add Value");
        addValueBtn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addValueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addValueBtnActionPerformed(evt);
            }
        });

        deleteValueBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteValueBtn.setText("Delete Value");
        deleteValueBtn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        deleteValueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteValueBtnActionPerformed(evt);
            }
        });

        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(backBtn);

        executeTestbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        executeTestbtn.setText("Execute Test");
        executeTestbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeTestbtnActionPerformed(evt);
            }
        });
        bottomPanel.add(executeTestbtn);

        exitBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(exitBtn);

        otherComponentsPanel.setBackground(new java.awt.Color(255, 255, 255));
        otherComponentsPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        otherComponentsPanel.setForeground(new java.awt.Color(255, 255, 255));

        urlLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        urlLabel.setText("URL :");
        urlLabel.setMaximumSize(new java.awt.Dimension(34, 20));
        urlLabel.setMinimumSize(new java.awt.Dimension(34, 20));
        urlLabel.setPreferredSize(new java.awt.Dimension(34, 20));

        methodLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        methodLabel.setText("Method :");

        headersLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        headersLabel.setText("Headers :");

        urlValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        methodValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        headersValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout otherComponentsPanelLayout = new javax.swing.GroupLayout(otherComponentsPanel);
        otherComponentsPanel.setLayout(otherComponentsPanelLayout);
        otherComponentsPanelLayout.setHorizontalGroup(
            otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                        .addComponent(urlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(urlValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
                    .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                        .addComponent(methodLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(methodValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(headersValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(headersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
        );
        otherComponentsPanelLayout.setVerticalGroup(
            otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(urlValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(methodValueLabel)
                    .addComponent(methodLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headersLabel)
                    .addComponent(headersValueLabel))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        topComponentsScrollPane.setViewportView(otherComponentsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(topComponentsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(requestBodyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deleteValueBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addValueBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(topComponentsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(requestBodyScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(addValueBtn)
                        .addGap(53, 53, 53)
                        .addComponent(deleteValueBtn)
                        .addGap(0, 97, Short.MAX_VALUE)))
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the action of the "Execute Test" button.
     *
     * - Generates request bodies based on table data. - Navigates to
     * Screen3Frame with the generated data.
     *
     * @param evt The ActionEvent triggered by the button click.
     */
    private void executeTestbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeTestbtnActionPerformed
        logger.info("Executing test...");

        // Create and show the loading dialog
        JDialog loadingDialog = createLoadingDialog();
        SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));

        // Run the tests in a background thread
        new SwingWorker<Object[][], Void>() {
            @Override
            protected Object[][] doInBackground() throws Exception {
                Object[][] screen3TableData = null;
                Object[][] jsonTableBody = null;

                if (jsonTable.getRowCount() == 0) {
                    logger.warn("JSON Table is empty. Proceeding with default request body.");
                    jsonTableBody = new Object[1][2];
                    String testName = "Verify " + name;
                    jsonTableBody[0][1] = "";
                    logger.info("Generated test name for empty JSON Table: " + testName);
                    screen3TableData = new Object[1][3];
                    screen3TableData[0] = executeTest(testName, null);
                } else {
                    try {
                        logger.info("Generating request bodies for JSON Table with " + jsonTable.getRowCount() + " rows.");
                        jsonTableBody = generateRequestBodies(name, jsonTable);
                        int jsonBodyLength = jsonTableBody.length;
                        screen3TableData = new Object[jsonBodyLength][3];
                        for (int i = 0; i < jsonBodyLength; i++) {
                            logger.info("Executing test: " + jsonTableBody[i][0]);
                            screen3TableData[i] = executeTest((String) jsonTableBody[i][0], (JSONObject) jsonTableBody[i][1]);
                        }
                    } catch (Exception ex) {
                        showErrorDialog(ex);
                        logger.error("Error during test execution. JSON Table rows: {}", jsonTable.getRowCount(), ex);
                    }
                }
                return screen3TableData;
            }

            @Override
            protected void done() {
                try {
                    Object[][] screen3TableData = get();
                    logger.info("Transitioning to Screen3Frame with table data. Rows: " + screen3TableData.length);

                    // Close the loading dialog on the Event Dispatch Thread
                    SwingUtilities.invokeLater(() -> {
                        loadingDialog.dispose();

                        // Open the next screen
                        Screen3Frame screen3 = new Screen3Frame(Screen2.this, screen3TableData, getExtendedState());
                        screen3.setVisible(true);
                        setVisible(false);
                        dispose();
                    });
                } catch (Exception ex) {
                    logger.error("Error retrieving results from background task", ex);
                    showErrorDialog(ex);
                    SwingUtilities.invokeLater(loadingDialog::dispose);
                }
            }
        }.execute();
    }

    /**
     * Creates and returns a non-modal loading dialog with a progress bar.
     */
    private JDialog createLoadingDialog() {
        JDialog dialog = new JDialog(this, "Processing", false); // Set modal to false
        dialog.setSize(250, 100);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JLabel loadingLabel = new JLabel("Executing tests,please wait", SwingConstants.CENTER);
        Timer timer = new Timer(500, new ActionListener() {
            private int dots = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                dots = (dots + 1) % 4;
                loadingLabel.setText("Executing tests,please wait" + ".".repeat(dots));
            }
        });
        timer.start();// Show infinite loading animation

        dialog.add(loadingLabel, BorderLayout.CENTER);

        return dialog;
    }
//GEN-LAST:event_executeTestbtnActionPerformed

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
        Object[] resultData = new Object[4]; // To store test details
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(this.url))
                    .method(methodType, (requestBody != null)
                            ? HttpRequest.BodyPublishers.ofString(requestBody.toString())
                            : HttpRequest.BodyPublishers.noBody());
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    requestBuilder.header(entry.getKey(), entry.getValue());
                }
            }
            HttpRequest request = requestBuilder.build();
            logger.info("Executing test: name={}, url={}, method={}", name, url, methodType);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            resultData[0] = name;
            resultData[1] = (requestBody != null) ? requestBody.toString() : "Empty Request Body"; // Request Body
            resultData[2] = response.statusCode(); // Response Code
            resultData[3] = response.body(); // Response Body

            logger.info("Test executed: name={}, responseCode={}, responseBody={}",
                    name, response.statusCode(), response.body());

        } catch (IOException | InterruptedException ex) {
            showErrorDialog(ex);
            logger.error("Exception during test execution", ex);
        } catch (Exception ex) {
            showErrorDialog(ex);
            logger.error("Exception during test execution", ex);
        }
        return resultData;
    }

    /**
     * Generates combinations of request bodies for testing based on the input
     * JTable's data. The method extracts field values, handles fields with
     * multiple values, and creates combinations of request bodies for
     * comprehensive testing.
     *
     * @param name the base name for test case descriptions.
     * @param table the JTable containing field names, data types, and values.
     * @return a 2D Object array where each row contains a test case name and
     * the corresponding JSON request body.
     */
    public Object[][] generateRequestBodies(String name, JTable table) {
        logger.info("Generating request bodies for: {}", name);
        int rowCount = table.getRowCount();
        int colCount = 2; // Test Case Name and Request Body

        JSONObject baseJsonBody = this.jsonRequestBodyObject;

        // Calculate the total number of test cases
        int totalTestCases = 0;
        for (int i = 0; i < rowCount; i++) {
            String positiveData = (String) table.getValueAt(i, 3);
            String negativeData = (String) table.getValueAt(i, 4);
            if (positiveData != null && !positiveData.isEmpty()) {
                totalTestCases += positiveData.split(",").length;
            }
            if (negativeData != null && !negativeData.isEmpty()) {
                totalTestCases += negativeData.split(",").length;
            }
        }

        // Initialize the result array
        logger.debug("Total test cases calculated: {}", totalTestCases);
        Object[][] result = new Object[totalTestCases + 1][colCount];
        int resultIndex = 0;
        result[resultIndex][0] = "Verify " + name + " with default values";
        result[resultIndex][1] = new JSONObject(baseJsonBody.toString());
        resultIndex++;

        // Generate JSON bodies for positive and negative data
        for (int i = 0; i < rowCount; i++) {
            String fieldName = (String) table.getValueAt(i, 0);
            String dataType = (String) table.getValueAt(i, 1);
            String positiveData = (String) table.getValueAt(i, 3);
            String negativeData = (String) table.getValueAt(i, 4);

            // Generate JSONs for positive data
            if (positiveData != null && !positiveData.isEmpty()) {
                String[] positiveValues = positiveData.split(",");
                for (String value : positiveValues) {
                    JSONObject newJson = new JSONObject(baseJsonBody.toString());
                    replaceValue(newJson, fieldName, parseValue(dataType, value.trim()));
                    result[resultIndex][0] = "Verify " + name + " with " + fieldName + " value as " + value.trim();
                    result[resultIndex][1] = newJson;
                    logger.debug("Generated positive test case: {}", result[resultIndex][0]);
                    resultIndex++;
                }
            }

            // Generate JSONs for negative data
            if (negativeData != null && !negativeData.isEmpty()) {
                String[] negativeValues = negativeData.split(",");
                for (String value : negativeValues) {
                    JSONObject newJson = new JSONObject(baseJsonBody.toString());
                    replaceValue(newJson, fieldName, parseValue(dataType, value.trim()));
                    result[resultIndex][0] = "Verify " + name + " with " + fieldName + " value as " + value.trim();
                    result[resultIndex][1] = newJson;
                    logger.debug("Generated negative test case: {}", result[resultIndex][0]);
                    resultIndex++;
                }
            }
        }
        logger.info("Request bodies generated successfully.");
        return result;
    }

    /**
     * Parses a string value into the specified data type.
     *
     * @param dataType the target data type ("String", "Integer", or "Boolean").
     * @param value the string representation of the value to parse.
     * @return the parsed value as an Object, matching the specified data type.
     * Defaults to returning the input as a String if the type is unrecognized.
     */
    private static Object parseValue(String dataType, String value) {
        return switch (dataType.toLowerCase()) {
            case "integer" ->
                Integer.parseInt(value);
            case "boolean" ->
                Boolean.parseBoolean(value);
            default ->
                value;
        }; // Default is String
    }

    /**
     * Replaces the value of a specified field in a JSON object. Supports nested
     * keys and array notation for targeting specific elements.
     *
     * @param json The JSONObject in which the value needs to be replaced.
     * @param fieldName The field name whose value should be replaced. Supports
     * dot notation for nested fields (e.g., "parent.child") and array notation
     * (e.g., "arrayField[0]").
     * @param value The new value to set for the specified field.
     * @throws IllegalArgumentException If the fieldName is invalid or does not
     * exist in the JSONObject.
     */
    private void replaceValue(JSONObject json, String fieldName, Object value) {
        logger.info("Replacing value for field: {} with value: {}", fieldName, value);
        if (fieldName.contains(".")) {
            // Handle nested keys
            String[] keys = fieldName.split("\\.");
            JSONObject current = json;
            for (int i = 0; i < keys.length - 1; i++) {
                String key = keys[i];
                // Handle array notation (e.g., "customErrors[0]")
                if (key.contains("[")) {
                    int arrayIndex = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("]")));
                    key = key.substring(0, key.indexOf("["));
                    JSONArray array = current.optJSONArray(key);
                    if (array == null) {
                        throw new IllegalArgumentException("Invalid key: " + fieldName);
                    }
                    current = array.getJSONObject(arrayIndex);
                } else {
                    current = current.optJSONObject(key);
                    if (current == null) {
                        throw new IllegalArgumentException("Invalid key: " + fieldName);
                    }
                }
            }
            // Replace the value for the final key
            String finalKey = keys[keys.length - 1];
            current.put(finalKey, value);
            logger.info("Successfully replaced value for nested field: {}", fieldName);
        } else {
            // Simple key, replace directly
            json.put(fieldName, value);
            logger.info("Successfully replaced value for field: {}", fieldName);
        }
    }

    private void jsonTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jsonTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jsonTablePropertyChange

    /**
     * Handles the action of the "Exit" button.
     *
     * - Displays a confirmation dialog. - Exits the application if confirmed.
     *
     * @param evt The ActionEvent triggered by the button click.
     */
    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        logger.debug("Exit button clicked");
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?");
        if (confirm == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    /**
     * Handles the action of the "Back" button.
     *
     * - Returns to the previous frame if it exists. - Disposes the current
     * frame.
     *
     * @param evt The ActionEvent triggered by the button click.
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        logger.debug("Back button clicked");
        if (previousFrame != null) {
            previousFrame.setExtendedState(this.getExtendedState());
            previousFrame.setVisible(true);
        }
        this.dispose();
        logger.info("Returning to the previous frame");
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * Action handler for the "Add Value" button. Opens a popup dialog to add
     * positive or negative values to the selected field in the JSON table.
     * Displays an error message if no field is selected.
     *
     * @param evt The ActionEvent triggered by clicking the "Add Value" button.
     */
    private void addValueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addValueBtnActionPerformed
        logger.info("Add Value button clicked.");
        if (jsonTable.getSelectedRow() != -1) {
            logger.debug("Row selected: {}", jsonTable.getSelectedRow());
            AddValuePopUp popUp = new AddValuePopUp(this, true);
            popUp.setVisible(true);
            logger.info("AddValuePopUp displayed successfully.");
        } else {
            logger.warn("No row selected for adding a value.");
            JOptionPane.showMessageDialog(this, "Please Selected a field", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addValueBtnActionPerformed

    /**
     * Adds a positive or negative value to the specified column of the selected
     * row in the JSON table. If values already exist, the new value is appended
     * to the existing values, separated by a comma.
     *
     * @param type The type of value to add ("positive data" or "negative
     * data").
     * @param value The value to be added to the JSON table.
     */
    public void addFieldValues(String type, String value) {
        logger.info("Adding field value. Type: {}, Value: {}", type, value);
        int selectedRow = jsonTable.getSelectedRow();
        int selectedColumn = type.equalsIgnoreCase("positive data") ? 3 : 4; // Positive data -> column 3, Negative data -> column 4

        String existedValue = (String) jsonTable.getValueAt(selectedRow, selectedColumn);
        if (!existedValue.equals("")) {
            String newValue = existedValue + "," + value; // Append new value
            jsonTable.setValueAt(newValue, selectedRow, selectedColumn);
            logger.debug("Appended value: {} to column: {}", newValue, selectedColumn);
        } else {
            jsonTable.setValueAt(value, selectedRow, selectedColumn);
            logger.debug("Added new value: {} to column: {}", value, selectedColumn);
        }
        logger.info("Field value added successfully.");
    }

    /**
     * Action handler for the "Delete Value" button. Opens a popup dialog to
     * delete selected positive or negative values from the JSON table. Displays
     * an error message if no field is selected or if there are no values to
     * delete.
     *
     * @param evt The ActionEvent triggered by clicking the "Delete Value"
     * button.
     */
    private void deleteValueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteValueBtnActionPerformed
        logger.info("Delete value button clicked.");
        deleteValueSelectedRow = jsonTable.getSelectedRow();

        if (deleteValueSelectedRow != -1) {
            logger.debug("Selected row for deletion: {}", deleteValueSelectedRow);
            String positiveValue = (String) jsonTable.getValueAt(deleteValueSelectedRow, 3);
            String negativeValue = (String) jsonTable.getValueAt(deleteValueSelectedRow, 4);
            logger.debug("Positive values: {}, Negative values: {}", positiveValue, negativeValue);

            String[] postiveValueAr = positiveValue.split(",");
            String[] negativeValueAr = negativeValue.split(",");
            if (positiveValue.equals("") && negativeValue.equals("")) {
                logger.warn("No positive or negative value present for selected field.");
                JOptionPane.showMessageDialog(this, "No positive or negative value is present for selected field", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            logger.info("Launching DeleteValuePopUp...");
            DeleteValuePopUp popUp = new DeleteValuePopUp(this, true, postiveValueAr, negativeValueAr);
            popUp.setVisible(true);
        } else {
            logger.warn("No row selected for deletion.");
            JOptionPane.showMessageDialog(this, "Please Selected a field", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteValueBtnActionPerformed

    /**
     * Updates the JSON table with the new positive and negative values for the
     * selected row. Converts the values in the DefaultListModel into a
     * comma-separated string before updating the table.
     *
     * @param positiveValue The DefaultListModel containing the updated positive
     * values.
     * @param negativeValue The DefaultListModel containing the updated negative
     * values.
     */
    public void updaTableValues(DefaultListModel<String> positiveValue, DefaultListModel<String> negativeValue) {
        logger.info("Updating table values for selected row...");
        deleteValueSelectedRow = jsonTable.getSelectedRow();
        String positiveValues = "";
        if (!positiveValue.isEmpty()) {
            positiveValues = IntStream.range(0, positiveValue.getSize())
                    .mapToObj(positiveValue::getElementAt)
                    .collect(Collectors.joining(", "));
            logger.debug("Collected positive values: {}", positiveValues);
        }
        String negativeValues = "";
        if (!negativeValue.isEmpty()) {
            negativeValues = IntStream.range(0, negativeValue.getSize())
                    .mapToObj(negativeValue::getElementAt)
                    .collect(Collectors.joining(", "));
            logger.debug("Collected negative values: {}", negativeValues);
        }
        jsonTable.setValueAt(positiveValues, deleteValueSelectedRow, 3);
        jsonTable.setValueAt(negativeValues, deleteValueSelectedRow, 4);
        logger.info("Table values updated successfully for row: {}", deleteValueSelectedRow);
    }

    public static void showErrorDialog(Exception exception) {
        String message = "An error occured: " + exception.toString();
        JOptionPane.showMessageDialog(
                null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            showErrorDialog(ex);
            java.util.logging.Logger.getLogger(Screen2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Screen2().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addValueBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton deleteValueBtn;
    private javax.swing.JButton executeTestbtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel headersLabel;
    private javax.swing.JLabel headersValueLabel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTable jsonTable;
    private javax.swing.JLabel methodLabel;
    private javax.swing.JLabel methodValueLabel;
    private javax.swing.JPanel otherComponentsPanel;
    private javax.swing.JScrollPane requestBodyScrollPane;
    private javax.swing.JScrollPane topComponentsScrollPane;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JLabel urlValueLabel;
    // End of variables declaration//GEN-END:variables

}
