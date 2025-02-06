/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elixr.apitestutility;

import static com.elixr.apitestutility.Screen2.showErrorDialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
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
    private Screen2 previousFrame;
    private String url;
    private String methodType;
    private Map<String, String> headers = new HashMap<>();

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
    public Screen3Frame(Screen2 previousFrame,int previousState,Object[][] testScenarios,String url,String methodType,Map<String,String> headers) {
        logger.info("Initializing Screen3Frame with data and previous state.");
        this.previousFrame = previousFrame;
        initComponents();
        setupFrame(previousState);
        this.url = url;
        this.methodType = methodType;
        this.headers = headers;
        populateResultTable(testScenarios);
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
        Object[][] updatedTableData = new Object[tableData.length][6]; // 6 columns now
        for (int i = 0; i < tableData.length; i++) {
            updatedTableData[i][0] = i + 1; // SL column with serial numbers starting from 1
            updatedTableData[i][1] = tableData[i][0]; // Test Name
            updatedTableData[i][2] = tableData[i][1]; // Request Body
        }

        DefaultTableModel model = new DefaultTableModel(
                updatedTableData,
                new String[]{"SL", "Test Name", "Request Body","Response Code", "Response Body", "Test Result"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Make the table cells non-editable.
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
                // Apply border
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
        Object[] resultData = new Object[2]; // To store test details
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
            resultData[0] = response.statusCode(); // Response Code
            resultData[1] = response.body(); // Response Body
            logger.info("Test executed: name={}, responseCode={}, responseBody={}",
                    name, response.statusCode(), response.body());
        } catch (Exception ex) {
            showErrorDialog(ex);
            logger.error("Exception during test execution", ex);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(688, 476));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(resultTableScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
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
        if (previousFrame != null) {
            logger.debug("Navigating back to previous frame.");
            previousFrame.setExtendedState(this.getExtendedState());
            previousFrame.setVisible(true);
            this.dispose();
        } else {
            logger.warn("No previous frame available to navigate back to.");
            JOptionPane.showMessageDialog(this, "No previous screen to navigate to!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_backBtnActionPerformed

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

    private void resultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTableMouseClicked
        // TODO add your handling code here:
        int row = resultTable.rowAtPoint(evt.getPoint());
        int col = resultTable.columnAtPoint(evt.getPoint());
        if (col == 2 || col == 4) { // Open popup only for JSON columns
            Object value = resultTable.getValueAt(row, col);
            if (value != null) {
               if (value.toString().equalsIgnoreCase("Empty Request Body") || 
                       value.toString().equalsIgnoreCase("")){
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

    private void executeTestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeTestBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = resultTable.getSelectedRow();
        JSONObject jsonBody;
        if ( selectedRow != -1) {
            logger.debug("Test selected: {}", resultTable.getSelectedRow());
            Object requestBody = resultTable.getValueAt(selectedRow, 2);
            if(requestBody == null || requestBody.toString().equalsIgnoreCase("Empty Request Body")){
                jsonBody = null;
            }else{
                jsonBody = (JSONObject) requestBody;
            }
            String testName = (String) resultTable.getValueAt(selectedRow, 1);
            JDialog loadingDialog = createLoadingDialog("Executing Test,Please Wait");
            SwingUtilities.invokeLater(()->loadingDialog.setVisible(true));
            new SwingWorker<Object[],Void>(){

                @Override
                protected Object[] doInBackground() throws Exception {
                    Object[] responseResult = null;
                    try{
                       responseResult = executeTest(testName,jsonBody);
                    } catch (Exception ex){
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

    /**
     * Creates and returns a non-modal loading dialog with a progress bar.
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
    private javax.swing.JButton backBtn;
    private javax.swing.JButton executeTestBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable resultTable;
    private javax.swing.JScrollPane resultTableScrollPane;
    // End of variables declaration//GEN-END:variables
}
