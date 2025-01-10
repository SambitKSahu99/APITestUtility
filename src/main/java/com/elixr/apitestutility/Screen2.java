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
 *
 * @author chandrakanth.shaji
 */
public class Screen2 extends javax.swing.JFrame {

    private String name;
    private Screen1 previousFrame;

    /**
     * Creates new form Screen2
     */
    public Screen2() {
        initComponents();
//        setupFrame();
    }

    public Screen2(Screen1 previousFrame, Object[][] jsonRequestBodyTableData, String baseUrl, String method, String path, String name, DefaultTableModel headersTableModel, int previousState) {
    public Screen2(JSONObject jsonRequestBodyObject,Object[][] jsonRequestBodyTableData, String baseUrl, String method, String path, String name, DefaultTableModel headersTableModel, int previousState) {
//        setExtendedState(previousState);
        this.previousFrame = previousFrame;
        this.name = name;
        initComponents();
        setupFrame(previousState);
        setUpComponents(jsonRequestBodyTableData,baseUrl,method,headersTableModel);
    }

    private void setUpComponents(Object[][] jsonRequestBodyTableData, String baseUrl, String method, DefaultTableModel headersTableModel){
        if (jsonRequestBodyTableData != null) {
            for (Object[] row : jsonRequestBodyTableData) {
                if (row[1] == "String") {
                    row[1] = "String";
                }
            }
            jsonTable.setModel(new DefaultTableModel(jsonRequestBodyTableData, new String[]{
                "Field", "Data Type", "Default Data","Positive Data", "Negative Data", "Error Message"
            }) {
                Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int row, int column) {

                    return  column == 1 || column == 5; // Allow edits for relevant columns
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

    public Screen2(Screen1 previousFrame) {

    }

    // to ensure the frame opens maximized, Allow resizing, and set a default close operation
    private void setupFrame(int state) {
        jsonTable.getTableHeader().setReorderingAllowed(false);
        setExtendedState(state);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(requestBodyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(otherComponentsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(otherComponentsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(requestBodyScrollPane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deleteValueBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addValueBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(executeTestbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(requestBodyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(addValueBtn)
                        .addGap(27, 27, 27)
                        .addComponent(deleteValueBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(executeTestbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    // Helper method to parse values based on data type
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

    // Helper method to generate combinations
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

    public static JSONObject formatJsonObject(JSONObject input) throws JSONException {
        JSONObject result = new JSONObject();
        for (String key : input.keySet()) {
            String[] parts = key.split("\\.");
            addNestedKeys(result, parts, input.get(key));
        }
        return result;
    }

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

    private static void ensureCapacity(JSONArray array, int index) {
        while (array.length() <= index) {
            array.put(JSONObject.NULL);
        }
    }

    private void jsonTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jsonTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jsonTablePropertyChange

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?");
        if (confirm == 0) {
            exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        if (previousFrame != null) {
            previousFrame.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void addValueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addValueBtnActionPerformed
        // TODO add your handling code here:
        if(jsonTable.getSelectedRow()!=-1){
            AddValuePopUp popUp = new AddValuePopUp(this, true);
            popUp.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Please Selected a field", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addValueBtnActionPerformed

    public void addFieldValues(String type,String value){
        int selectedRow = jsonTable.getSelectedRow();
        int selectedColumn = 0;
        if(type.equalsIgnoreCase("positive data")){
            selectedColumn = 3;
        }else if(type.equalsIgnoreCase("negative data")){
            selectedColumn = 4;
        }
        String existedValue = (String) jsonTable.getValueAt(selectedRow, selectedColumn);
        if(!existedValue.equals("")){
            String newValue = existedValue+","+value;
            jsonTable.setValueAt(newValue, selectedRow, selectedColumn);
        }else{
            jsonTable.setValueAt(value, selectedRow, selectedColumn);
        }
    }

    private void deleteValueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteValueBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jsonTable.getSelectedRow();
        if(selectedRow==-1){
            String positiveValue = (String) jsonTable.getValueAt(selectedRow, 3);
            String negativeValue = (String) jsonTable.getValueAt(selectedRow, 4);
            String[] postiveValueAr = positiveValue.split(",");
            String[] negativeValueAr = negativeValue.split(",");
            DeleteValuePopUp popUp = new DeleteValuePopUp(this,true,postiveValueAr, negativeValueAr);
            popUp.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Please Selected a field", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteValueBtnActionPerformed

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
    private javax.swing.JButton deleteValueBtn;
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
