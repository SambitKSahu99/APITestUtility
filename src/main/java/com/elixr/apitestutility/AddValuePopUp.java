/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.elixr.apitestutility;

import javax.swing.*;
import java.util.Arrays;

/**
 *
 * @author chandrakanth.shaji
 */
public class AddValuePopUp extends javax.swing.JDialog {

    /**
     * Creates new form AdddValuePopUp
     */
    public AddValuePopUp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // Positive TextArea key listener
        positiveTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    javax.swing.SwingUtilities.invokeLater(() -> removeExtraNewLines(positiveTextArea1));
                }
            }
        });

        // Negative TextArea key listener
        negativeTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    javax.swing.SwingUtilities.invokeLater(() -> removeExtraNewLines(negativeTextArea1));
                }
            }
        });

        setUpFrame();
    }

    /**
     * Ensures there are no unnecessary blank lines in the JTextArea.
     */
    private void removeExtraNewLines(JTextArea textArea) {
        String text = textArea.getText();
        String[] lines = text.split("\\n");
        StringBuilder sanitizedText = new StringBuilder();

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                sanitizedText.append(line.trim()).append("\n");
            }
        }

        textArea.setText(sanitizedText.toString());
    }

    /**
     * Converts the text area content into a single comma-separated string.
     */
    private String getCommaSeparatedValues(JTextArea textArea) {
        String text = textArea.getText();
        String[] lines = text.split("\\n");
        StringBuilder commaSeparated = new StringBuilder();

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                if (commaSeparated.length() > 0) {
                    commaSeparated.append(",");
                }
                commaSeparated.append(line.trim());
            }
        }

        return commaSeparated.toString();
    }

    /**
     * Save button action to handle input values.
     */
    private void setUpFrame() {
        this.setTitle("Add Positive and Negative Values");

        // Set the scroll pane properties for the positive scroll pane
        positiveScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Vertical scrollbar only when needed
        positiveScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Horizontal scrollbar only when needed

        // Set the scroll pane properties for the negative scroll pane
        negativeScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Vertical scrollbar only when needed
        negativeScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Horizontal scrollbar only when needed

        // Force a layout recalculation to ensure the content size is taken into account
        positiveScrollPane1.revalidate();
        negativeScrollPane2.revalidate();

        // Optionally, you can also force a repaint to ensure the UI is updated correctly
        positiveScrollPane1.repaint();
        negativeScrollPane2.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        addValueHeader = new javax.swing.JLabel();
        positiveLabel1 = new javax.swing.JLabel();
        negativeLabel2 = new javax.swing.JLabel();
        positiveScrollPane1 = new javax.swing.JScrollPane();
        positiveTextArea1 = new javax.swing.JTextArea();
        negativeScrollPane2 = new javax.swing.JScrollPane();
        negativeTextArea1 = new javax.swing.JTextArea();
        saveButton1 = new javax.swing.JButton();
        exitButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        addValueHeader.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addValueHeader.setText("Add Value");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(addValueHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addValueHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
        );

        positiveLabel1.setText("Positive Values");

        negativeLabel2.setText("Negative Values");

        positiveScrollPane1.setPreferredSize(new java.awt.Dimension(47, 146));

        positiveTextArea1.setColumns(20);
        positiveTextArea1.setRows(5);
        positiveTextArea1.setMaximumSize(new java.awt.Dimension(45, 90));
        positiveTextArea1.setMinimumSize(new java.awt.Dimension(45, 90));
        positiveTextArea1.setPreferredSize(new java.awt.Dimension(45, 90));
        positiveScrollPane1.setViewportView(positiveTextArea1);

        negativeScrollPane2.setPreferredSize(new java.awt.Dimension(47, 146));

        negativeTextArea1.setColumns(20);
        negativeTextArea1.setRows(5);
        negativeTextArea1.setMaximumSize(new java.awt.Dimension(45, 90));
        negativeTextArea1.setMinimumSize(new java.awt.Dimension(45, 90));
        negativeTextArea1.setPreferredSize(new java.awt.Dimension(45, 90));
        negativeScrollPane2.setViewportView(negativeTextArea1);

        saveButton1.setText("Save");
        saveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton1ActionPerformed(evt);
            }
        });

        exitButton1.setText("Exit");
        exitButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(positiveLabel1)
                            .addComponent(positiveScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(negativeLabel2)
                            .addComponent(negativeScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exitButton1))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positiveLabel1)
                    .addComponent(negativeLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(positiveScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(negativeScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton1)
                    .addComponent(exitButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Retrieve and process values from JTextAreas
        String positiveValues = getCommaSeparatedValues(positiveTextArea1);
        String negativeValues = getCommaSeparatedValues(negativeTextArea1);

        // Validate input
        if (positiveValues.trim().isEmpty() && negativeValues.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter at least one value.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Split values by commas
        String[] positiveArray = positiveValues.split(",");
        String[] negativeArray = negativeValues.split(",");

        // Pass data to Screen2 (assuming Screen2 is the parent)
        Screen2 parentFrame = (Screen2) this.getParent();
        for (String value : positiveArray) {
            if (!value.trim().isEmpty()) {
                parentFrame.addFieldValues("positive data", value.trim());
            }
        }
        for (String value : negativeArray) {
            if (!value.trim().isEmpty()) {
                parentFrame.addFieldValues("negative data", value.trim());
            }
        }

        // Show success message
        JOptionPane.showMessageDialog(this, "Values saved successfully!");

        // Close dialog
        this.dispose();
    }


    private void exitButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AddValuePopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddValuePopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddValuePopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddValuePopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddValuePopUp dialog = new AddValuePopUp(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addValueHeader;
    private javax.swing.JButton exitButton1;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel negativeLabel2;
    private javax.swing.JScrollPane negativeScrollPane2;
    private javax.swing.JTextArea negativeTextArea1;
    private javax.swing.JLabel positiveLabel1;
    private javax.swing.JScrollPane positiveScrollPane1;
    private javax.swing.JTextArea positiveTextArea1;
    private javax.swing.JButton saveButton1;
    // End of variables declaration//GEN-END:variables
}
