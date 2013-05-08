/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jmhertlein.mctowns.remote.client.gui;

import java.awt.CardLayout;
import javax.swing.UIManager;

/**
 *
 * @author joshua
 */
public class MetaFrame extends javax.swing.JFrame {
    private CardLayout contentPanelLayout;
    /**
     * Creates new form MetaFrame
     */
    public MetaFrame() {
        initComponents();
        this.contentPanel.add(new OverviewPanel(), overviewMenuButton.getText());
        this.contentPanel.add(new TownViewPanel(), townMenuButton.getText());
        contentPanelLayout = (CardLayout) contentPanel.getLayout();
        this.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuButtonsGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        overviewMenuButton = new javax.swing.JToggleButton();
        townMenuButton = new javax.swing.JToggleButton();
        territoryMenuButton = new javax.swing.JToggleButton();
        plotMenuButton = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        securityMenuButton = new javax.swing.JToggleButton();
        globalSearchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MCTowns Remote Client");

        jToolBar1.setBackground(new java.awt.Color(185, 185, 185));
        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setForeground(new java.awt.Color(254, 254, 254));
        jToolBar1.setRollover(true);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        menuButtonsGroup.add(overviewMenuButton);
        overviewMenuButton.setSelected(true);
        overviewMenuButton.setText("Overview");
        overviewMenuButton.setFocusable(false);
        overviewMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        overviewMenuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        overviewMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overviewMenuButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(overviewMenuButton);

        menuButtonsGroup.add(townMenuButton);
        townMenuButton.setText("Town");
        townMenuButton.setFocusable(false);
        townMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        townMenuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        townMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                townMenuButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(townMenuButton);

        menuButtonsGroup.add(territoryMenuButton);
        territoryMenuButton.setText("Territory");
        territoryMenuButton.setFocusable(false);
        territoryMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        territoryMenuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(territoryMenuButton);

        menuButtonsGroup.add(plotMenuButton);
        plotMenuButton.setText("Plot");
        plotMenuButton.setFocusable(false);
        plotMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotMenuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(plotMenuButton);

        menuButtonsGroup.add(jToggleButton1);
        jToggleButton1.setText("Player");
        jToggleButton1.setFocusable(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jToggleButton1);

        menuButtonsGroup.add(securityMenuButton);
        securityMenuButton.setText("Security");
        securityMenuButton.setFocusable(false);
        securityMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        securityMenuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(securityMenuButton);

        jLabel1.setText("Search:");
        jLabel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(globalSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(globalSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        contentPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1PropertyChange

    private void overviewMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overviewMenuButtonActionPerformed
        contentPanelLayout.show(contentPanel, overviewMenuButton.getText());
        globalSearchField.setEnabled(false);
    }//GEN-LAST:event_overviewMenuButtonActionPerformed

    private void townMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_townMenuButtonActionPerformed
        contentPanelLayout.show(contentPanel, townMenuButton.getText());
        globalSearchField.setEnabled(true);
    }//GEN-LAST:event_townMenuButtonActionPerformed

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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MetaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MetaFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JTextField globalSearchField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.ButtonGroup menuButtonsGroup;
    private javax.swing.JToggleButton overviewMenuButton;
    private javax.swing.JToggleButton plotMenuButton;
    private javax.swing.JToggleButton securityMenuButton;
    private javax.swing.JToggleButton territoryMenuButton;
    private javax.swing.JToggleButton townMenuButton;
    // End of variables declaration//GEN-END:variables

}
