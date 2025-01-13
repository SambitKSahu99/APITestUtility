/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elixr.apitestutility;

import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.json.JSONArray;
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
    private int deleteValueSelectedRow;
    private JSONObject jsonRequestBodyObject;

    /**
     * Default constructor for Screen2. Initializes the components of the frame.
     */
    public Screen2() {
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
     * @param path The request path.
     * @param name The name or identifier for the current screen.
     * @param headersTableModel The table model containing HTTP headers.
     * @param previousState The previous frame state (e.g., maximized).
     */
    public Screen2(Screen1 previousFrame, JSONObject jsonRequestBodyObject, Object[][] jsonRequestBodyTableData, String baseUrl, String method, String path, String name, DefaultTableModel headersTableModel, int previousState) {
        this.deleteValueSelectedRow = -1;
        this.jsonRequestBodyObject = jsonRequestBodyObject;
        this.previousFrame = previousFrame;
        this.name = name;
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
        if (jsonRequestBodyTableData != null) {
            for (Object[] row : jsonRequestBodyTableData) {
                if (row[1] == "String") {
                    row[1] = "String";
                }
            }
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
        this.deleteValueSelectedRow = -1;
    }

    /**
     * Configures the frame's initial setup.
     *
     * @param state The state of the frame (e.g., maximized, minimized).
     */
    private void setupFrame(int state) {
        jsonTable.getTableHeader().setReorderingAllowed(false);
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
        addValueBtn = new javax.swing.JButton();
        deleteValueBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(689, 476));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(26, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(otherComponentsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(requestBodyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deleteValueBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addValueBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(executeTestbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(otherComponentsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(requestBodyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(addValueBtn)
                        .addGap(33, 33, 33)
                        .addComponent(deleteValueBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(executeTestbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    public Object[][] generateRequestBodies(String name, JTable table) {
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
                    result[resultIndex][0] = "Verify " + name + " with " + fieldName + " value " + value.trim();
                    result[resultIndex][1] = newJson;
                    resultIndex++;
                }
            }

            // Generate JSONs for negative data
            if (negativeData != null && !negativeData.isEmpty()) {
                String[] negativeValues = negativeData.split(",");
                for (String value : negativeValues) {
                    JSONObject newJson = new JSONObject(baseJsonBody.toString());
                    replaceValue(newJson, fieldName, parseValue(dataType, value.trim()));
                    result[resultIndex][0] = "Verify " + name + " with " + fieldName + " value " + value.trim();
                    result[resultIndex][1] = newJson;
                    resultIndex++;
                }
            }
        }
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
        } else {
            // Simple key, replace directly
            json.put(fieldName, value);
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
     * Action handler for the "Add Value" button. Opens a popup dialog to add
     * positive or negative values to the selected field in the JSON table.
     * Displays an error message if no field is selected.
     *
     * @param evt The ActionEvent triggered by clicking the "Add Value" button.
     */
    private void addValueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addValueBtnActionPerformed
        // TODO add your handling code here:
        if (jsonTable.getSelectedRow() != -1) {
            AddValuePopUp popUp = new AddValuePopUp(this, true);
            popUp.setVisible(true);
        } else {
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
        int selectedRow = jsonTable.getSelectedRow();
        int selectedColumn = 0;
        if (type.equalsIgnoreCase("positive data")) {
            selectedColumn = 3;
        } else if (type.equalsIgnoreCase("negative data")) {
            selectedColumn = 4;
        }
        String existedValue = (String) jsonTable.getValueAt(selectedRow, selectedColumn);
        if (!existedValue.equals("")) {
            String newValue = existedValue + "," + value;
            jsonTable.setValueAt(newValue, selectedRow, selectedColumn);
        } else {
            jsonTable.setValueAt(value, selectedRow, selectedColumn);
        }
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
        // TODO add your handling code here:
        deleteValueSelectedRow = jsonTable.getSelectedRow();
        if (deleteValueSelectedRow != -1) {
            String positiveValue = (String) jsonTable.getValueAt(deleteValueSelectedRow, 3);
            String negativeValue = (String) jsonTable.getValueAt(deleteValueSelectedRow, 4);
            String[] postiveValueAr = positiveValue.split(",");
            String[] negativeValueAr = negativeValue.split(",");
            if (positiveValue.equals("") && negativeValue.equals("")) {
                JOptionPane.showMessageDialog(this, "No positive or negative value is present for selected field", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DeleteValuePopUp popUp = new DeleteValuePopUp(this, true, postiveValueAr, negativeValueAr);
            popUp.setVisible(true);
        } else {
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
        deleteValueSelectedRow = jsonTable.getSelectedRow();
        String positiveValues = "";
        if (!positiveValue.isEmpty()) {
            positiveValues = IntStream.range(0, positiveValue.getSize())
                    .mapToObj(positiveValue::getElementAt)
                    .collect(Collectors.joining(", "));
        }
        String negativeValues = "";
        if (!negativeValue.isEmpty()) {
            negativeValues = IntStream.range(0, negativeValue.getSize())
                    .mapToObj(negativeValue::getElementAt)
                    .collect(Collectors.joining(", "));
        }
        jsonTable.setValueAt(positiveValues, deleteValueSelectedRow, 3);
        jsonTable.setValueAt(negativeValues, deleteValueSelectedRow, 4);
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
    private javax.swing.JButton addValueBtn;
    private javax.swing.JButton backBtn;
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
    private javax.swing.JLabel urlLabel;
    private javax.swing.JLabel urlValueLabel;
    // End of variables declaration//GEN-END:variables

}
