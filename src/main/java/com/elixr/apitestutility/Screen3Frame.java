/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elixr.apitestutility;

import java.awt.*;

import static java.lang.System.exit;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.json.JSONObject;

/**
 * Screen3Frame class represents a JFrame that displays test results in a table
 * format. It includes functionality to format and display JSON data in table
 * cells, navigate back to the previous screen, and manage the frame state.
 */
public class Screen3Frame extends javax.swing.JFrame {

    private Screen2 previousFrame;

    /**
     * Constructs a new Screen3Frame with the given data and previousState.
     *
     * @param previousFrame the previous screen's frame, used for navigation.
     * @param tableData the data to populate the result table.
     * @param previousState the previousState of the frame (e.g., maximized,
     * normal).
     */
    public Screen3Frame(Screen2 previousFrame, Object[][] tableData, int previousState) {
        this.previousFrame = previousFrame;
        initComponents();
        setupFrame(previousState);
        populateResultTable(tableData);
    }

    /**
     * Default constructor for Screen3Frame.
     */
    public Screen3Frame() {
    }

    /**
     * Configures the frame's previousState and default close operation.
     *
     * @param previousState the previousState to set the frame to (e.g.,
     * maximized, normal).
     */
    private void setupFrame(int previousState) {
        this.setTitle("APITestUtility");
        setExtendedState(previousState);
        setLocationRelativeTo(null);
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
        // Add an "SL" column to the table data
        Object[][] updatedTableData = new Object[tableData.length][6]; // 6 columns now
        for (int i = 0; i < tableData.length; i++) {
            updatedTableData[i][0] = i + 1; // SL column with serial numbers starting from 1
            updatedTableData[i][1] = tableData[i][0]; // Test Name
            updatedTableData[i][2] = tableData[i][1]; // Request Body
            updatedTableData[i][3] = tableData[i][2]; // Response Code
            updatedTableData[i][4] = tableData[i][3]; // Response Body
            updatedTableData[i][5] = ""; // Test Result (empty for now)
        }

        DefaultTableModel model = new DefaultTableModel(
                updatedTableData,
                new String[]{"SL", "Test Name", "Request Body", "Response Code", "Response Body", "Test Result"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Make the table cells non-editable.
            }
        };

        resultTable.setModel(model);

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
        for (int row = 0; row < resultTable.getRowCount(); row++) {
            adjustRowHeight(resultTable, row, 2); // Adjust for column 2 ("Request Body")
            adjustRowHeight(resultTable, row, 4); //Adjust for column 4 ("Response Body")
        }
        resultTableScrollPane.revalidate();
        resultTableScrollPane.repaint();
    }

    /**
     * Adjusts the row height of a table to fit the content in the specified
     * column.
     *
     * @param table the JTable whose row height needs adjustment.
     * @param row the row index to adjust.
     * @param column the column index to consider for height adjustment.
     */
    private static void adjustRowHeight(JTable table, int row, int column) {
        TableCellRenderer renderer = table.getCellRenderer(row, column);
        Component comp = table.prepareRenderer(renderer, row, column);
        int preferredHeight = comp.getPreferredSize().height;
        table.setRowHeight(row, Math.max(table.getRowHeight(row), preferredHeight));
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
        resultTableScrollPane.setViewportView(resultTable);

        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel1.add(backBtn);

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
        if (previousFrame != null) {
            previousFrame.setExtendedState(this.getExtendedState());
            previousFrame.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No previous screen to navigate to!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_backBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?");
        if (confirm == 0) {
            exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable resultTable;
    private javax.swing.JScrollPane resultTableScrollPane;
    // End of variables declaration//GEN-END:variables
}
