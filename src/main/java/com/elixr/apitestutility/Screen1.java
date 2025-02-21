/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elixr.apitestutility;

import static com.elixr.apitestutility.Screen2.showErrorDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Screen1 class represents the first screen in the application's workflow.
 * This JFrame handles the input of request details, including headers, method,
 * base URL, path, and request body. It allows the user to configure these
 * details and navigate to the next screen.
 */
public class Screen1 extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(Screen1.class);
    private DefaultTableModel tableModel;
    private JTable headersTable;
    private JFrame previousFrame;

    /**
     * Default constructor for Screen1. Initializes the UI components and sets
     * up the headers table. Disables JSON-related fields until a method that
     * supports a request body is selected.
     */
    public Screen1(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setExtendedState(MAXIMIZED_BOTH);
        logger.info("Initializing Screen 1.");
        this.setTitle("APITestUtility");
        initComponents();
        setUpHeaderComponents();
        jsonScrollPane.setEnabled(false);
        jsonRequestBody.setEnabled(false);
        jsonrequestBody1.setEnabled(false);
        jsonTextLabel.setEnabled(false);
        setLocationRelativeTo(null);
        logger.info("Screen1 Initialized Successfully.");
    }

    public Screen1(JFrame previousFrame, String url, String method, Map<String, String> headers, String requestBody) {
        this(previousFrame);
        setUpCurlElements(url, method, headers, requestBody);
    }

    public Screen1() {
    }

    /**
     * Sets up the table for HTTP headers, including the column names ("Header
     * Name" and "Header Value"). Embeds the table inside a JScrollPane and
     * integrates it into the headers panel.
     */
    private void setUpHeaderComponents() {
        logger.debug("Setting up header components...");
        // Initialize table model and table
        String[] columnNames = {"Header Name", "Header Value"};
        tableModel = new DefaultTableModel(columnNames, 0);
        headersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(headersTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        if (headersPanel != null) {
            headersPanel.removeAll(); // Clear any existing content
            headersPanel.setLayout(new BorderLayout());
            headersPanel.add(scrollPane, BorderLayout.CENTER);
            // Revalidate and repaint headersPanel
            headersPanel.revalidate();
            headersPanel.repaint();
        }
        logger.debug("Header components set up successfully.");
    }

    private void setUpCurlElements(String curlUrl, String method, Map<String, String> curlHeaders, String requestBody) {
        URL fullUrl;
        try {
            fullUrl = new URL(curlUrl);
            protocolComboBox.setSelectedItem(fullUrl.getProtocol());
            baseUrl.setText(fullUrl.getHost());
            path.setText(fullUrl.getPath());
        } catch (MalformedURLException ex) {
            showErrorDialog(ex);
            logger.error("Error Occured while extracting url", ex);
        }
        if (method.equalsIgnoreCase("POST") && requestBody.isEmpty()) {
            method = "GET";
        }
        methodDropDown.setSelectedItem(method);
        for (Map.Entry<String, String> headers : curlHeaders.entrySet()) {
            addHeader(headers.getKey(), headers.getValue());
        }
        if (!method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE")) {
            jsonrequestBody1.setText(requestBody);
        }
    }

    /**
     * Deletes the selected row from the headers table. Displays a warning if no
     * row is selected.
     */
    private void deleteSelectedRow() {
        logger.debug("Deleting selected row...");
        int selectedRow = headersTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            logger.info("Row deleted at index {}", selectedRow);
        } else {
            logger.warn("No row selected for deletion.");
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static final Map<String, Object> jsonFieldsAndValues = new LinkedHashMap<>();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bottomPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        SubmitBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jsonScrollPane = new javax.swing.JScrollPane();
        jsonRequestBody = new javax.swing.JTextArea();
        jsonrequestBody1 = new javax.swing.JTextArea();
        jsonTextLabel = new javax.swing.JLabel();
        deleteHeaderBtn = new javax.swing.JButton();
        addHeaderBtn = new javax.swing.JButton();
        headersJScrollPane = new javax.swing.JScrollPane();
        headersPanel = new javax.swing.JPanel();
        baseUrlLabel = new javax.swing.JLabel();
        baseUrl = new javax.swing.JTextField();
        pathLabel = new javax.swing.JLabel();
        path = new javax.swing.JTextField();
        methodLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        methodDropDown = new javax.swing.JComboBox<>();
        protocolComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);

        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(backBtn);

        SubmitBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SubmitBtn.setText("Submit");
        SubmitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SubmitBtn.setMaximumSize(new java.awt.Dimension(81, 23));
        SubmitBtn.setMinimumSize(new java.awt.Dimension(81, 23));
        SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(SubmitBtn);

        resetBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        resetBtn.setText("Reset");
        resetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetBtn.setMaximumSize(new java.awt.Dimension(81, 23));
        resetBtn.setMinimumSize(new java.awt.Dimension(81, 23));
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(resetBtn);

        exitBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exitBtn.setText("Exit");
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.setMaximumSize(new java.awt.Dimension(81, 23));
        exitBtn.setMinimumSize(new java.awt.Dimension(81, 23));
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(exitBtn);

        jsonRequestBody.setColumns(20);
        jsonRequestBody.setRows(5);
        jsonScrollPane.setViewportView(jsonRequestBody);

        jsonrequestBody1.setColumns(20);
        jsonrequestBody1.setRows(5);
        jsonScrollPane.setViewportView(jsonrequestBody1);

        jsonTextLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jsonTextLabel.setText("Enter Json Request Body");

        deleteHeaderBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteHeaderBtn.setText("Delete Row");
        deleteHeaderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHeaderBtnActionPerformed(evt);
            }
        });

        addHeaderBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addHeaderBtn.setText("Add Row");
        addHeaderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHeaderBtnActionPerformed(evt);
            }
        });

        headersPanel.setLayout(new javax.swing.BoxLayout(headersPanel, javax.swing.BoxLayout.Y_AXIS));
        headersJScrollPane.setViewportView(headersPanel);

        baseUrlLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        baseUrlLabel.setText("Base URL");

        pathLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pathLabel.setText("Path");

        methodLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        methodLabel.setText("Method");

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameLabel.setText("Name");

        methodDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GET", "POST", "PUT", "DELETE","PATCH" }));
        methodDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                methodDropDownActionPerformed(evt);
            }
        });

        protocolComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "http", "https"}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(headersJScrollPane)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addHeaderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteHeaderBtn)))
                    .addComponent(jsonTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsonScrollPane)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(methodLabel)
                        .addGap(12, 12, 12)
                        .addComponent(methodDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addGap(24, 24, 24)
                                .addComponent(nameField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(baseUrlLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(protocolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(baseUrl, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pathLabel)
                                .addGap(33, 33, 33)
                                .addComponent(path)))
                        .addGap(107, 107, 107)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baseUrlLabel)
                    .addComponent(baseUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(protocolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(pathLabel))
                    .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(methodLabel))
                    .addComponent(methodDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(nameLabel))
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headersJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(addHeaderBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteHeaderBtn)))
                .addGap(6, 6, 6)
                .addComponent(jsonTextLabel)
                .addGap(6, 6, 6)
                .addComponent(jsonScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(0, 0, 703, 484);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Resets all input fields, including the base URL, method dropdown, path,
     * name, headers, and JSON request body. Prompts the user for confirmation
     * before clearing the fields.
     *
     * @param evt Action event triggered by the reset button.
     */
    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        logger.debug("Reset button clicked.");
        if (baseUrl.getText().isBlank() && methodDropDown.getSelectedItem() == null && path.getText().isBlank() && nameField.getText().isBlank() && tableModel.getRowCount() == 0 && jsonrequestBody1.getText().isBlank()) {
            logger.warn("Nothing to reset, all fields are already empty.");
            JOptionPane.showMessageDialog(null, "There is no value present");
            return;
        }
        int resetConfirm = JOptionPane.showConfirmDialog(null, "Are you sure want to reset ?", "Rest", JOptionPane.YES_NO_OPTION);
        if (resetConfirm == 0) {
            logger.info("Resetting all input fields and table data...");
            baseUrl.setText("");
            methodDropDown.setSelectedIndex(0);
            nameField.setText("");
            path.setText("");
            jsonrequestBody1.setText("");
            DefaultTableModel model = tableModel;
            model.setRowCount(0);
            logger.info("All fields reset successfully.");
        }
    }//GEN-LAST:event_resetBtnActionPerformed

    /**
     * Recursively extracts fields and their values from a JSON object or array.
     * Populates the `jsonFieldsAndValues` map with field paths as keys and
     * their values.
     *
     * @param requestObject The JSON object or array to process.
     * @param path The current JSON path (used to maintain field hierarchy).
     */
    private static void extractFieldsAndValues(Object requestObject, String path) {
        logger.debug("Extracting fields and values from JSON object...");
        if (requestObject instanceof JSONObject jsonObject) {
            for (Object key : jsonObject.keySet()) {
                String keyField = key.toString();
                extractFieldsAndValues(jsonObject.get(keyField), path.isEmpty() ? keyField : path + "." + keyField);
            }
        } else if (requestObject instanceof JSONArray jsonArray) {
            for (int i = 0; i < jsonArray.length(); i++) {
                extractFieldsAndValues(jsonArray.get(i), path + "[" + i + "]");
            }
        } else {
            jsonFieldsAndValues.put(path, requestObject); // Store field and value
        }
        logger.debug("Fields and values extracted successfully.");
    }

    /**
     * Handles the submission of the request details. Validates the user inputs
     * and parses the JSON request body if applicable. Navigates to the next
     * screen (Screen2) with the captured data.
     *
     * @param evt Action event triggered by the submit button.
     */
    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        logger.debug("Submit button clicked.");
        String protocol = protocolComboBox.getSelectedItem().toString().trim();
        String baseUrlInput = baseUrl.getText();
        String methodInput = (String) methodDropDown.getSelectedItem();
        String nameInput = nameField.getText();
        String pathInput = path.getText();
        JSONObject jsonRequestBodyObject = null;
        Object[][] jsonRequestBodyTableData = null;
        if (baseUrlInput.isBlank()) {
            logger.warn("Base url is mandatory");
            JOptionPane.showMessageDialog(this, "Base URL is mandatory", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (methodInput.isBlank()) {
            logger.warn("Method is mandatory");
            JOptionPane.showMessageDialog(this, "Method is mandatory", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nameInput.isBlank()) {
            logger.warn("Name is mandatory");
            JOptionPane.showMessageDialog(this, "Name is mandatory", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (pathInput.isBlank()) {
            logger.warn("Path is mandatory");
            JOptionPane.showMessageDialog(this, "Path is mandatory", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String jsonInput = jsonrequestBody1.getText();
        try {
            if (methodInput.equalsIgnoreCase("post") || methodInput.equalsIgnoreCase("put") || methodInput.equalsIgnoreCase("patch")) {
                jsonRequestBodyObject = new JSONObject(jsonInput);
                jsonFieldsAndValues.clear(); // Clear previous data
                extractFieldsAndValues(jsonRequestBodyObject, "");
                jsonRequestBodyTableData = prepareTableData(jsonFieldsAndValues);
            } else {
                jsonRequestBodyTableData = null;
            }
            if (!pathInput.startsWith("/")) {
                pathInput = "/" + pathInput;
            }
            String formattedBaseUrl = protocol + "://" + baseUrlInput + pathInput;
            Screen2 screen2 = new Screen2(this, jsonRequestBodyObject, jsonRequestBodyTableData, formattedBaseUrl, methodInput, nameInput, tableModel, getExtendedState());
            logger.info("Navigating to Screen2 with Base URL: {}", formattedBaseUrl);
            screen2.setVisible(true);
            setVisible(false);
        } catch (Exception exception) {
            logger.error("Error while processing Submit action: {}", exception.getMessage(), exception);
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SubmitBtnActionPerformed

    /**
     * Opens a dialog for adding a new HTTP header.
     *
     * @param evt Action event triggered by the "Add Header" button.
     */
    private void addHeaderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHeaderBtnActionPerformed
        logger.info("Add Header button clicked. Opening Add Header dialog.");
        OpenHeaderDialog dialog = new OpenHeaderDialog(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_addHeaderBtnActionPerformed

    /**
     * Deletes the selected HTTP header from the headers table.
     *
     * @param evt Action event triggered by the "Delete Header" button.
     */
    private void deleteHeaderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteHeaderBtnActionPerformed
        logger.info("Delete Header button clicked.");
        deleteSelectedRow();
        logger.info("Header deletion attempted.");
    }//GEN-LAST:event_deleteHeaderBtnActionPerformed

    /**
     * Toggles the visibility and enablement of JSON-related fields based on the
     * selected HTTP method. Enables JSON fields for methods that support a
     * request body (e.g., POST, PUT, PATCH).
     *
     * @param evt Action event triggered by the method dropdown selection.
     */
    private void methodDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_methodDropDownActionPerformed
        // TODO add your handling code here:
        String method = (String) methodDropDown.getSelectedItem();
        logger.info("Method selected: " + method);
        if (method.equalsIgnoreCase("post") || method.equalsIgnoreCase("put") || method.equalsIgnoreCase("patch")) {
            jsonTextLabel.setEnabled(true);
            jsonScrollPane.setEnabled(true);
            jsonrequestBody1.setEnabled(true);
            logger.info("JSON input components enabled for method: " + method);
        } else {
            jsonTextLabel.setEnabled(false);
            jsonScrollPane.setEnabled(false);
            jsonrequestBody1.setEnabled(false);
            jsonrequestBody1.setText("");
            logger.info("JSON input components disabled for method: " + method);
        }
    }//GEN-LAST:event_methodDropDownActionPerformed

    /**
     * Confirms with the user before exiting the application. If confirmed, the
     * application is terminated.
     *
     * @param evt Action event triggered by the "Exit" button.
     */
    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        // TODO add your handling code here:
        logger.info("Exit button clicked.");
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?");
        if (confirm == 0) {
            logger.info("User confirmed exit. Exiting application.");
            System.exit(0);
        } else {
            logger.info("User canceled exit.");
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    /**
     * Handles the action when the "Back" button is clicked.
     *
     * @param evt
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        logger.debug("Back button clicked");
        if (previousFrame != null) {
            previousFrame.setVisible(true);
        }
        this.dispose();
        logger.info("Returning to the previous frame");
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * Adds a new header to the headers table.
     *
     * @param name The name of the header.
     * @param value The value of the header.
     */
    public void addHeader(String name, String value) {
        if (tableModel != null) {
            tableModel.addRow(new Object[]{name, value});
            logger.info("Header added: Name = " + name + ", Value = " + value);
        } else {
            logger.warn("Table model is null. Unable to add header.");
        }
    }

    /**
     * Prepares table data for the JSON request body by converting the
     * field-value map into a 2D array. Each row contains the field name, its
     * data type, value, and placeholders for additional fields.
     *
     * @param fieldKeyAndValueMap The map of JSON fields and their values.
     * @return A 2D array representing the table data.
     */
    private static Object[][] prepareTableData(Map<String, Object> fieldKeyAndValueMap) {
        logger.info("Preparing table data for JSON fields and values...");
        List<Object[]> tableDataList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : fieldKeyAndValueMap.entrySet()) {
            String field = entry.getKey();  // Field name
            Object value = entry.getValue(); // Field value
            String dataType = "String"; // Default data type

            // Prepare table row with default values
            tableDataList.add(new Object[]{field, dataType, value, "", ""});
        }
        logger.info("Table data prepared with " + tableDataList.size() + " entries.");
        return tableDataList.toArray(new Object[0][]);
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
        logger.info("Starting Screen1 application...");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Screen2.showErrorDialog(ex);
            java.util.logging.Logger.getLogger(Screen1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Screen1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SubmitBtn;
    private javax.swing.JButton addHeaderBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField baseUrl;
    private javax.swing.JLabel baseUrlLabel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton deleteHeaderBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JScrollPane headersJScrollPane;
    private javax.swing.JPanel headersPanel;
    private javax.swing.JTextArea jsonRequestBody;
    private javax.swing.JScrollPane jsonScrollPane;
    private javax.swing.JLabel jsonTextLabel;
    private javax.swing.JTextArea jsonrequestBody1;
    private javax.swing.JComboBox<String> methodDropDown;
    private javax.swing.JLabel methodLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField path;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JComboBox<String> protocolComboBox;
    private javax.swing.JButton resetBtn;
    // End of variables declaration//GEN-END:variables
}
