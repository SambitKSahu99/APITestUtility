/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elixr.apitestutility;

import java.awt.BorderLayout;
import java.awt.Dimension;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author sambit.sahu
 */
public class Screen1 extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private JTable headersTable;

    /**
     * Creates new form Screen1
     */
    public Screen1() {
        initComponents();
        setUpHeaderComponents();
        jsonScrollPane.setEnabled(false);
        jsonRequestBody.setEnabled(false);
        jsonrequestBody1.setEnabled(false);
        jsonTextLabel.setEnabled(false);
    }

    private void setUpHeaderComponents() {
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
    }

    private void deleteSelectedRow() {
        int selectedRow = headersTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SubmitBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SubmitBtn.setText("Submit");
        SubmitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(SubmitBtn);

        resetBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        resetBtn.setText("Reset");
        resetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(resetBtn);

        exitBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exitBtn.setText("Exit");
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        methodDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Get", "Post", "Put", "Delete","Patch" }));
        methodDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                methodDropDownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(methodLabel)
                        .addGap(12, 12, 12)
                        .addComponent(methodDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(headersJScrollPane)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addHeaderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteHeaderBtn)))
                    .addComponent(jsonTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsonScrollPane)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addGap(24, 24, 24)
                                .addComponent(nameField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(baseUrlLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(baseUrl))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pathLabel)
                                .addGap(33, 33, 33)
                                .addComponent(path)))
                        .addGap(107, 107, 107)))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baseUrlLabel)
                    .addComponent(baseUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(headersJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(addHeaderBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteHeaderBtn)))
                .addGap(6, 6, 6)
                .addComponent(jsonTextLabel)
                .addGap(6, 6, 6)
                .addComponent(jsonScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        if (baseUrl.getText().isBlank() && methodDropDown.getSelectedItem() == null && path.getText().isBlank() && nameField.getText().isBlank() && tableModel.getRowCount() == 0 && jsonrequestBody1.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "There is no value present");
            return;
        }
        int resetConfirm = JOptionPane.showConfirmDialog(null, "Are you sure want to reset ?", "Rest", JOptionPane.YES_NO_OPTION);
        if (resetConfirm == 0) {
            baseUrl.setText("");
            methodDropDown.setSelectedIndex(0);
            nameField.setText("");
            path.setText("");
            jsonrequestBody1.setText("");
            DefaultTableModel model = tableModel;
            model.setRowCount(0);
        }
    }//GEN-LAST:event_resetBtnActionPerformed

    private static void extractFieldsAndValues(Object requestObject, String path) {
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
    }

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        String baseUrlInput = baseUrl.getText();
        String methodInput = (String) methodDropDown.getSelectedItem();
        String nameInput = nameField.getText();
        String pathInput = path.getText();
        Object[][] jsonRequestBodyTableData;
        if (baseUrlInput.isBlank()) {
            JOptionPane.showMessageDialog(this, "Base URL is mandatory", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (methodInput.isBlank()) {
            JOptionPane.showMessageDialog(this, "Method is mandatory", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nameInput.isBlank()) {
            JOptionPane.showMessageDialog(this, "Name is mandatory", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (pathInput.isBlank()) {
            JOptionPane.showMessageDialog(this, "Path is mandatory", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String jsonInput = jsonrequestBody1.getText();
        try {
            if (methodInput.equalsIgnoreCase("post") || methodInput.equalsIgnoreCase("put") || methodInput.equalsIgnoreCase("patch")) {
                JSONObject jsonObject = new JSONObject(jsonInput);
                jsonFieldsAndValues.clear(); // Clear previous data
                extractFieldsAndValues(jsonObject, "");
                jsonRequestBodyTableData = prepareTableData(jsonFieldsAndValues);
            } else {
                jsonRequestBodyTableData = null;
            }
            Screen2 screen2 = new Screen2(this, jsonRequestBodyTableData, baseUrlInput, methodInput, pathInput, nameInput, tableModel, getExtendedState());
            screen2.setVisible(true);
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void addHeaderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHeaderBtnActionPerformed
        // TODO add your handling code here:
        OpenHeaderDialog dialog = new OpenHeaderDialog(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_addHeaderBtnActionPerformed

    private void deleteHeaderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteHeaderBtnActionPerformed
        // TODO add your handling code here:
        deleteSelectedRow();
    }//GEN-LAST:event_deleteHeaderBtnActionPerformed

    private void methodDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_methodDropDownActionPerformed
        // TODO add your handling code here:
        String method = (String) methodDropDown.getSelectedItem();
        if (method.equalsIgnoreCase("post") || method.equalsIgnoreCase("put") || method.equalsIgnoreCase("patch")) {
            jsonTextLabel.setEnabled(true);
            jsonScrollPane.setEnabled(true);
            jsonrequestBody1.setEnabled(true);
        } else {
            jsonTextLabel.setEnabled(false);
            jsonScrollPane.setEnabled(false);
            jsonrequestBody1.setEnabled(false);
        }
    }//GEN-LAST:event_methodDropDownActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?");
        if (confirm == 0) {
            exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    public void addHeader(String name, String value) {
        if (tableModel != null) {
            tableModel.addRow(new Object[]{name, value});
        }
    }

    private static Object[][] prepareTableData(Map<String, Object> fieldKeyAndValueMap) {
        List<Object[]> tableDataList = new ArrayList<>();

        for (Map.Entry<String, Object> entry : fieldKeyAndValueMap.entrySet()) {
            String field = entry.getKey();  // Field name
            Object value = entry.getValue(); // Field value
            String dataType = "String"; // Default data type

            // Prepare table row with default values
            tableDataList.add(new Object[]{field, dataType, value, "", ""});
        }

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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
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
    private javax.swing.JButton resetBtn;
    // End of variables declaration//GEN-END:variables

}
