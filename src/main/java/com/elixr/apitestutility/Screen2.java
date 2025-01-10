/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elixr.apitestutility;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Screen2 class represents a secondary frame in the application, providing
 * functionality for displaying and managing JSON request bodies, HTTP headers,
 * and other details. It allows users to customize request data, view headers,
 * and execute tests.
 */
public class Screen2 extends javax.swing.JFrame {

    private String name;
    private Screen1 previousFrame;

    /**
     * Default constructor for Screen2. Initializes the components of the frame.
     */
    public Screen2() {
        initComponents();
    }

    /**
     * Constructor for Screen2 with parameters to set up the frame.
     *
     * @param previousFrame The parent Screen1 instance for navigation purposes.
     * @param jsonRequestBodyTableData A 2D array of data representing JSON
     * request bodies.
     * @param baseUrl The base URL of the request.
     * @param method The HTTP method (e.g., GET, POST).
     * @param path The request path.
     * @param name The name or identifier for the current screen.
     * @param headersTableModel The table model containing HTTP headers.
     * @param previousState The previous frame state (e.g., maximized).
     */
    public Screen2(Screen1 previousFrame, Object[][] jsonRequestBodyTableData, String baseUrl, String method, String path, String name, DefaultTableModel headersTableModel, int previousState) {
        this.previousFrame = previousFrame;
        this.name = name;
        initComponents();
        setupFrame(previousState);
        if (jsonRequestBodyTableData != null) {
            for (Object[] row : jsonRequestBodyTableData) {
                if (row[1] == "String") {
                    row[1] = "String";
                }
            }
            jsonTable.setModel(new DefaultTableModel(jsonRequestBodyTableData, new String[]{
                "Field", "Data Type", "Positive Data", "Negative Data", "Error Message"
            }) {
                Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column == 2 || column == 3 || column == 4; // Allow edits for relevant columns
                }
            });
            customizeTable();
        } else {
            jsonTable.setEnabled(false);
        }
        urlValueLabel.setText(baseUrl);
        methodValueLabel.setText(method.toUpperCase());
        //  to display the Headers in Screen 2.
        if (headersTableModel.getRowCount() != 0) {
            StringBuilder headersText = new StringBuilder();
            for (int i = 0; i < headersTableModel.getRowCount(); i++) {
                String headerName = (String) headersTableModel.getValueAt(i, 0);
                String headerValue = (String) headersTableModel.getValueAt(i, 1);
                headersText.append(headerName).append(": ").append(headerValue);
                if (i < headersTableModel.getRowCount() - 1) {
                    headersText.append(" , ").append("\n");
                }
            }
            headersValueLabel.setText(headersText.toString());
        } else {
            headersValueLabel.setText("No headers available");
        }
    }

    /**
     * Constructor for Screen2 with only a reference to the previous frame.
     *
     * @param previousFrame The parent Screen1 instance for navigation.
     */
    public Screen2(Screen1 previousFrame) {
    }

    /**
     * Configures the frame's initial setup.
     *
     * @param state The state of the frame (e.g., maximized, minimized).
     */
    private void setupFrame(int state) {
        setExtendedState(state);
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
        executeTestbtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        otherComponentsPanel = new javax.swing.JPanel();
        urlLabel = new javax.swing.JLabel();
        methodLabel = new javax.swing.JLabel();
        headersLabel = new javax.swing.JLabel();
        urlValueLabel = new javax.swing.JLabel();
        methodValueLabel = new javax.swing.JLabel();
        headersValueLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Request Field Data and Validations");

        jsonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Field", "Datatype", "Positive Data", "Negative Data", "Error Message"
            }
        ));
        jsonTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jsonTablePropertyChange(evt);
            }
        });
        requestBodyScrollPane.setViewportView(jsonTable);

        executeTestbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        executeTestbtn.setText("Execute Test");
        executeTestbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeTestbtnActionPerformed(evt);
            }
        });

        exitBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        otherComponentsPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        otherComponentsPanel.setForeground(new java.awt.Color(255, 255, 255));

        urlLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        urlLabel.setText("URL :");

        methodLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        methodLabel.setText(" Method :");

        headersLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        headersLabel.setText("Headers :");

        javax.swing.GroupLayout otherComponentsPanelLayout = new javax.swing.GroupLayout(otherComponentsPanel);
        otherComponentsPanel.setLayout(otherComponentsPanelLayout);
        otherComponentsPanelLayout.setHorizontalGroup(
            otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                        .addComponent(headersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(headersValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                        .addComponent(urlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(urlValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                        .addComponent(methodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(methodValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        otherComponentsPanelLayout.setVerticalGroup(
            otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherComponentsPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlLabel)
                    .addComponent(urlValueLabel))
                .addGap(18, 18, 18)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(methodLabel)
                    .addComponent(methodValueLabel))
                .addGap(18, 18, 18)
                .addGroup(otherComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(headersValueLabel))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(requestBodyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(otherComponentsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(executeTestbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(otherComponentsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(requestBodyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(executeTestbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
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
        // TODO add your handling code here:
        Object[][] screen3TableData = null;
        if (jsonTable.getRowCount() == 0) {
            screen3TableData = null;
        } else {
            try {
                screen3TableData = generateRequestBodies(name, jsonTable);
                System.out.println("Screen 3 row count = " + screen3TableData.length);
            } catch (Exception ex) {
                Logger.getLogger(Screen2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Screen3Frame screen3 = new Screen3Frame(this, screen3TableData, getExtendedState());
        screen3.setVisible(true);
        setVisible(false);
        this.dispose();
    }//GEN-LAST:event_executeTestbtnActionPerformed

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
    public static Object[][] generateRequestBodies(String name, JTable table) {
        List<Object[]> requestBodies = new ArrayList<>();

        // Step 1: Extract fields and their values from the JTable
        JSONObject defaultRequestBody = new JSONObject();
        List<String> fieldsWithMultipleValues = new ArrayList<>();
        List<List<Object>> valueCombinations = new ArrayList<>();

        int rowCount = table.getRowCount();

        for (int row = 0; row < rowCount; row++) {
            String fieldName = table.getValueAt(row, 0).toString(); // Field name
            String dataType = table.getValueAt(row, 1).toString(); // Data type (String, Integer, Boolean)
            String positiveValuesStr = table.getValueAt(row, 2).toString(); // Positive values (comma-separated)
            String negativevalueStr = table.getValueAt(row, 3).toString();
            String[] values = positiveValuesStr.split(",");
            String[] negativeValues = negativevalueStr.split(",");

            List<Object> parsedValues = new ArrayList<>();

            for (String value : values) {
                parsedValues.add(parseValue(dataType, value.trim()));
            }

            for (String value : negativeValues) {
                if (!value.equals("")) {
                    parsedValues.add(parseValue(dataType, value.trim()));
                }
            }

            // Add default value (first value in the list) to the request body
            defaultRequestBody.put(fieldName, parsedValues.get(0));

            // Track fields with multiple values for generating combinations
            if (parsedValues.size() > 1) {
                fieldsWithMultipleValues.add(fieldName);
                valueCombinations.add(parsedValues);
            }
        }

        // Step 2: Generate all combinations of values for fields with multiple values
        if (fieldsWithMultipleValues.isEmpty()) {
            // If no field has multiple values, generate a single request body
            String testName = "Verify " + name + " with all default values";
            requestBodies.add(new Object[]{testName, defaultRequestBody.toString(4)});

        } else {
            generateCombinations(fieldsWithMultipleValues, valueCombinations, defaultRequestBody, name, requestBodies);
        }

        // Convert the list to an array and return
        return requestBodies.toArray(new Object[0][]);
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
     * Generates all possible combinations of values for fields with multiple
     * values and creates corresponding JSON request bodies. Each combination is
     * added as a test case.
     *
     * @param fields a list of field names with multiple values.
     * @param valueCombinations a list of value combinations for each field.
     * @param baseRequestBody the default request body to build upon.
     * @param name the base name for test case descriptions.
     * @param requestBodies the list to store generated test cases (test name
     * and JSON body).
     */
    private static void generateCombinations(List<String> fields, List<List<Object>> valueCombinations,
            JSONObject baseRequestBody, String name, List<Object[]> requestBodies) {
        int[] indices = new int[fields.size()];
        int totalCombinations = 1;

        for (List<Object> values : valueCombinations) {
            totalCombinations *= values.size();
        }

        for (int i = 0; i < totalCombinations; i++) {
            JSONObject requestBody = new JSONObject(baseRequestBody.toString());

            for (int fieldIndex = 0; fieldIndex < fields.size(); fieldIndex++) {
                String fieldName = fields.get(fieldIndex);
                Object value = valueCombinations.get(fieldIndex).get(indices[fieldIndex]);
                requestBody.put(fieldName, value);
            }

            String testName = "Verify " + name;
            for (int fieldIndex = 0; fieldIndex < fields.size(); fieldIndex++) {
                String fieldName = fields.get(fieldIndex);
                Object value = valueCombinations.get(fieldIndex).get(indices[fieldIndex]);
                testName += String.format(" with %s value %s", fieldName, value);
            }

            requestBodies.add(new Object[]{testName, formatJsonObject(requestBody)});

            // Update indices for the next combination
            for (int j = fields.size() - 1; j >= 0; j--) {
                if (indices[j] < valueCombinations.get(j).size() - 1) {
                    indices[j]++;
                    break;
                } else {
                    indices[j] = 0;
                }
            }
        }
    }

    /**
     * Converts a flat JSON object with dot-separated keys into a nested JSON
     * structure. Supports handling nested objects and arrays.
     *
     * @param input the flat JSON object to format.
     * @return a nested JSON object based on the dot-separated keys.
     * @throws JSONException if any JSON operation fails.
     */
    public static JSONObject formatJsonObject(JSONObject input) throws JSONException {
        JSONObject result = new JSONObject();
        for (String key : input.keySet()) {
            String[] parts = key.split("\\.");
            addNestedKeys(result, parts, input.get(key));
        }
        return result;
    }

    /**
     * Adds nested keys and values to a JSON object. Handles dot-separated keys
     * and array indices to create nested objects and arrays dynamically.
     *
     * @param current the current JSON object to which keys will be added.
     * @param keys an array of key segments (split by dots) representing the
     * nested structure.
     * @param value the value to assign to the final key in the structure.
     * @throws JSONException if any JSON operation fails.
     */
    private static void addNestedKeys(JSONObject current, String[] keys, Object value) throws JSONException {
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];

            // Check if this is the last key in the array
            if (i == keys.length - 1) {
                if (key.matches(".*\\[\\d+\\]$")) { // Handle array indices
                    String baseKey = key.substring(0, key.indexOf("["));
                    int index = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("]")));

                    if (!current.has(baseKey)) {
                        current.put(baseKey, new JSONArray());
                    }

                    JSONArray array = current.getJSONArray(baseKey);
                    ensureCapacity(array, index);
                    array.put(index, value);
                } else {
                    current.put(key, value);
                }
            } else {
                if (key.matches(".*\\[\\d+\\]$")) { // Handle nested array keys
                    String baseKey = key.substring(0, key.indexOf("["));
                    int index = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("]")));

                    if (!current.has(baseKey)) {
                        current.put(baseKey, new JSONArray());
                    }

                    JSONArray array = current.getJSONArray(baseKey);
                    ensureCapacity(array, index);

                    if (array.isNull(index)) {
                        array.put(index, new JSONObject());
                    }

                    current = array.getJSONObject(index);
                } else {
                    if (!current.has(key)) {
                        current.put(key, new JSONObject());
                    }
                    current = current.getJSONObject(key);
                }
            }
        }
    }

    /**
     * Ensures that a JSONArray has sufficient capacity to accommodate the
     * specified index. If the array is smaller than the required size, null
     * placeholders are added.
     *
     * @param array the JSONArray to resize.
     * @param index the index to ensure capacity for.
     */
    private static void ensureCapacity(JSONArray array, int index) {
        while (array.length() <= index) {
            array.put(JSONObject.NULL);
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
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?");
        if (confirm == 0) {
            exit(0);
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
        if (previousFrame != null) {
            previousFrame.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Screen2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screen2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screen2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screen2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Screen2().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
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
    private javax.swing.JLabel urlLabel;
    private javax.swing.JLabel urlValueLabel;
    // End of variables declaration//GEN-END:variables

}
