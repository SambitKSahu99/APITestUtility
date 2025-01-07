/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elixr.apitestutility;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * @author chandrakanth.shaji
 */
public class Screen3 extends javax.swing.JFrame {

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable resultTable;

    /**
     * Creates new form Screen3
     */
    public Screen3() {
        initComponents();
    }

    public Screen3(Object[][] tableData, int state) {
        initComponents();
        setupFrame(state);
        populateResultTable(tableData);
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
            java.util.logging.Logger.getLogger(Screen3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screen3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screen3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screen3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Screen3().setVisible(true);
            }
        });
    }

    private void setupFrame(int state) {
        setExtendedState(state);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void populateResultTable(Object[][] tableData) {
        DefaultTableModel model = new DefaultTableModel(
                tableData,
                new String[]{"Sl", "Test Name", "Request Body", "Response Code", "Response Body", "Test Result"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Make the table cells non-editable.
            }
        };

        resultTable.setModel(model);
        resultTable.getColumnModel().getColumn(2).setCellRenderer(new TextAreaRenderer());
//        resultTable.getColumnModel().getColumn(2).setCellEditor(new TextAreaEditor());
        for (int row = 0; row < resultTable.getRowCount(); row++) {
            adjustRowHeight(resultTable, row, 2); // Adjust for column 1
        }
        resultTable.revalidate();
        resultTable.repaint();
//      resultTable.getColumnModel().getColumn(2).setCellRenderer(new BeautifiedJsonCellRenderer());
    }

    private static void adjustRowHeight(JTable table, int row, int column) {
        TableCellRenderer renderer = table.getCellRenderer(row, column);
        Component comp = table.prepareRenderer(renderer, row, column);
        int preferredHeight = comp.getPreferredSize().height;
        table.setRowHeight(row, Math.max(table.getRowHeight(row), preferredHeight));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Results");

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(resultTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                                        .addComponent(jLabel1))
                                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    class TextAreaRenderer extends DefaultTableCellRenderer {
        private final JTextArea textArea = new JTextArea();

        public TextAreaRenderer() {
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            textArea.setText(value != null ? value.toString() : "");
            textArea.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            textArea.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            return textArea;
        }
    }

    // Custom Editor for JTextArea
    class TextAreaEditor extends AbstractCellEditor implements TableCellEditor {
        private final JTextArea textArea = new JTextArea();
        private final JScrollPane scrollPane = new JScrollPane(textArea);

        public TextAreaEditor() {
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
        }

        @Override
        public Object getCellEditorValue() {
            return textArea.getText();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            textArea.setText(value != null ? value.toString() : "");
            return scrollPane;
        }
    }
    // End of variables declaration                   
}
