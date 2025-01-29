/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.elixr.apitestutility;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a pop-up dialog for deleting values from positive and
 * negative data lists. The user can select multiple values from both lists and
 * remove them. The updated lists are then passed back to the parent frame.
 */
public class DeleteValuePopUp extends javax.swing.JDialog {

    private static final Logger logger = LoggerFactory.getLogger(DeleteValuePopUp.class);
    private final DefaultListModel<String> positiveList = new DefaultListModel<>();
    private final DefaultListModel<String> negativeList = new DefaultListModel<>();

    /**
     * Creates a new DeleteValuePopUp dialog.
     *
     * @param parent The parent frame that owns this dialog.
     * @param modal Specifies whether the dialog is modal.
     * @param positiveValues An array of strings representing positive values.
     * @param negativeValues An array of strings representing negative values.
     */
    public DeleteValuePopUp(java.awt.Frame parent, boolean modal,
            String[] positiveValues, String[] negativeValues) {
        super(parent, modal);
        logger.info("Initializing DeleteValuePopUp dialog.");
        initComponents();
        setUpFrame();
        setUpList(positiveValues, negativeValues);
    }

    private DeleteValuePopUp(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Configures the frame's scroll panes to display scrollbars as needed.
     */
    private void setUpFrame() {
        logger.info("Setting up frame properties for DeleteValuePopUp dialog.");
        positiveScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        negativeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        logger.info("Frame properties set up successfully.");
    }

    /**
     * Populates the positive and negative lists with the provided values and
     * sets up their selection modes.
     *
     * @param positiveValues An array of strings representing positive values.
     * @param negativeValues An array of strings representing negative values.
     */
    private void setUpList(String[] positiveValues, String[] negativeValues) {
        logger.info("Setting up value lists for DeleteValuePopUp.");

        logger.debug("Adding positive values to the list.");
        for (String items : positiveValues) {
            logger.debug("Adding positive value: {}", items);
            positiveList.addElement(items);
        }
        positiveValueList.setModel(positiveList);
        positiveValueList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        logger.debug("Adding negative values to the list.");
        for (String items : negativeValues) {
            negativeList.addElement(items);
        }
        negativeValueList.setModel(negativeList);
        negativeValueList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        logger.info("Value lists set up successfully.");
    }

    /**
     * Handles the action performed when the "Delete" button is clicked. Removes
     * the selected items from both positive and negative lists and updates the
     * parent frame with the modified lists.
     *
     * @param evt The event triggered by clicking the "Delete" button.
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        logger.info("Delete button clicked. Deleting selected values.");

        int[] selectedPositiveIndex = positiveValueList.getSelectedIndices();
        logger.debug("Selected positive indices: {}", selectedPositiveIndex);

        for (int i : selectedPositiveIndex) {
            System.out.println("selected indx :" + i);
        }
        for (int i = selectedPositiveIndex.length - 1; i >= 0; i--) {
            logger.debug("Removing positive value at index: {}", selectedPositiveIndex[i]);
            positiveList.remove(selectedPositiveIndex[i]);
        }

        int[] selectedNegativeIndex = negativeValueList.getSelectedIndices();
        logger.debug("Selected negative indices: {}", selectedNegativeIndex);

        for (int i = selectedNegativeIndex.length - 1; i >= 0; i--) {
            logger.debug("Removing negative value at index: {}", selectedNegativeIndex[i]);
            negativeList.remove(selectedNegativeIndex[i]);
        }

        logger.info("Updating parent screen with new values.");
        Screen2 screen2 = (Screen2) this.getParent();
        screen2.updaTableValues(positiveList, negativeList);

        logger.info("Values deleted successfully. Closing DeleteValuePopUp dialog.");
        this.dispose();
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
        deleteValueHeader = new javax.swing.JLabel();
        positiveScrollPane = new javax.swing.JScrollPane();
        positiveValueList = new javax.swing.JList<>();
        negativeScrollPane = new javax.swing.JScrollPane();
        negativeValueList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        deleteValueHeader.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteValueHeader.setText("Delete Value");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(deleteValueHeader)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(deleteValueHeader))
        );

        positiveValueList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        positiveScrollPane.setViewportView(positiveValueList);

        negativeValueList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        negativeScrollPane.setViewportView(negativeValueList);

        jLabel1.setText("Positive Values");

        jLabel2.setText("Negative Values");

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(deleteButton)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(exitBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(positiveScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(negativeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(positiveScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(negativeScrollPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(exitBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the action performed when the "Exit" button is clicked. Closes
     * the dialog and disposes of its resources.
     *
     * @param evt The event triggered by clicking the "Exit" button.
     */
    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        logger.info("Exit button clicked. Closing DeleteValuePopUp dialog.");
        dispose();
    }//GEN-LAST:event_exitBtnActionPerformed

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
            java.util.logging.Logger.getLogger(DeleteValuePopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteValuePopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteValuePopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteValuePopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DeleteValuePopUp dialog = new DeleteValuePopUp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel deleteValueHeader;
    private javax.swing.JButton exitBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane negativeScrollPane;
    private javax.swing.JList<String> negativeValueList;
    private javax.swing.JScrollPane positiveScrollPane;
    private javax.swing.JList<String> positiveValueList;
    // End of variables declaration//GEN-END:variables
}
