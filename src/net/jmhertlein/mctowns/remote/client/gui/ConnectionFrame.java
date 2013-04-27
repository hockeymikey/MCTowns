/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jmhertlein.mctowns.remote.client.gui;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import net.jmhertlein.mctowns.remote.client.KeyLoader;
import net.jmhertlein.mctowns.remote.client.MCTConnectionManager;
import net.jmhertlein.mctowns.remote.client.NamedKeyPair;
import sun.misc.BASE64Encoder;

/**
 *
 * @author joshua
 */
public class ConnectionFrame extends javax.swing.JFrame {

    private KeyLoader keyLoader;
    private File rootKeysDir;
    private JFrame thisFrame;

    /**
     * Creates new form ConnectionFrame
     */
    public ConnectionFrame() {
        initComponents();
        setupFiles();
        keyLoader = new KeyLoader(rootKeysDir);

        updateKeyComboBox();
        updateServerComboBox();
        advancedPane.setVisible(false);
        pack();
        thisFrame = this;
    }

    private void setupFiles() {
        rootKeysDir = new File("keys");
        rootKeysDir.mkdir();
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
        hostnameDropDown = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        keypairDropDown = new javax.swing.JComboBox();
        manageKeysButton = new javax.swing.JButton();
        connectButton = new javax.swing.JButton();
        connectionProgressBar = new javax.swing.JProgressBar();
        advancedButton = new javax.swing.JButton();
        advancedPane = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverPubKeyArea = new javax.swing.JTextArea();
        portField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        connectionStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connect to a Server");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jLabel1.setText("Server Hostname:");

        hostnameDropDown.setEditable(true);
        hostnameDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostnameDropDownActionPerformed(evt);
            }
        });

        jLabel2.setText("Key Pair");

        manageKeysButton.setText("Manage Keys...");
        manageKeysButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageKeysButtonActionPerformed(evt);
            }
        });

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        advancedButton.setText("Advanced...");
        advancedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advancedButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Cached Public Key for Server:");

        serverPubKeyArea.setEditable(false);
        serverPubKeyArea.setColumns(20);
        serverPubKeyArea.setRows(5);
        jScrollPane1.setViewportView(serverPubKeyArea);

        javax.swing.GroupLayout advancedPaneLayout = new javax.swing.GroupLayout(advancedPane);
        advancedPane.setLayout(advancedPaneLayout);
        advancedPaneLayout.setHorizontalGroup(
            advancedPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedPaneLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        advancedPaneLayout.setVerticalGroup(
            advancedPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedPaneLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
        );

        portField.setText("3333");

        jLabel4.setText("Port No.:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(advancedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(advancedButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(connectionProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(connectionStatus)
                                .addGap(0, 102, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(keypairDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(manageKeysButton)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(hostnameDropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(connectButton)
                                .addGap(47, 47, 47)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostnameDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(connectButton)
                    .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(keypairDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageKeysButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(advancedButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(connectionStatus)
                        .addComponent(connectionProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(advancedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        Object selected = hostnameDropDown.getSelectedItem();
        if(selected == null)
            return;
        
        final String host = selected.toString();
        final int port;
        try {
            port = Integer.parseInt(portField.getText());
        } catch(NumberFormatException nfe) {
            portField.setText("Bad Port: \"" + portField.getText() + "\"");
            return;
        }

        final NamedKeyPair selectedKP = keyLoader.getLoadedKey(keypairDropDown.getSelectedItem().toString().trim());

        final MCTConnectionManager conMan = new MCTConnectionManager(host, port, selectedKP.getPubKey(), selectedKP.getPrivateKey());

        connectionProgressBar.setIndeterminate(true);

        SwingWorker x = new SwingWorker() {
            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    conMan.connect();
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionFrame.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                
                return true;
            }

            @Override
            protected void done() {
                connectionProgressBar.setIndeterminate(false);
                Boolean connected;
                try {
                    connected = (Boolean) get();
                } catch (        InterruptedException | ExecutionException ex) {
                    Logger.getLogger(ConnectionFrame.class.getName()).log(Level.SEVERE, null, ex);
                    connected = false;
                }
                
                if(connected) {
                    connectionStatus.setText("Connected.");
                } else
                    connectionStatus.setText("Error connecting.");
            }
            
        };
        
        x.execute();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void manageKeysButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageKeysButtonActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManageKeysFrame(keyLoader, thisFrame).setVisible(true);
            }
        });

        this.setVisible(false);
    }//GEN-LAST:event_manageKeysButtonActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        updateKeyComboBox();
    }//GEN-LAST:event_formWindowStateChanged

    private void advancedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advancedButtonActionPerformed
        if (advancedPane.isVisible())
            advancedPane.setVisible(false);
        else
            advancedPane.setVisible(true);

        pack();
    }//GEN-LAST:event_advancedButtonActionPerformed

    private void hostnameDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostnameDropDownActionPerformed
        Object selected = hostnameDropDown.getSelectedItem();
        if (selected == null)
            return;
        PublicKey serverPubKey = keyLoader.getLoadedServerPublicKey(selected.toString());
        if (serverPubKey != null)
            serverPubKeyArea.setText(new BASE64Encoder().encode(serverPubKey.getEncoded()));
    }//GEN-LAST:event_hostnameDropDownActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        updateKeyComboBox();
        updateServerComboBox();
    }//GEN-LAST:event_formWindowGainedFocus

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
            java.util.logging.Logger.getLogger(ConnectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton advancedButton;
    private javax.swing.JPanel advancedPane;
    private javax.swing.JButton connectButton;
    private javax.swing.JProgressBar connectionProgressBar;
    private javax.swing.JLabel connectionStatus;
    private javax.swing.JComboBox hostnameDropDown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox keypairDropDown;
    private javax.swing.JButton manageKeysButton;
    private javax.swing.JTextField portField;
    private javax.swing.JTextArea serverPubKeyArea;
    // End of variables declaration//GEN-END:variables

    private void updateKeyComboBox() {
        keypairDropDown.removeAllItems();
        for (NamedKeyPair pair : keyLoader.getLoadedPairs()) {
            keypairDropDown.addItem(pair.getName());
        }
    }

    private void updateServerComboBox() {
        hostnameDropDown.removeAllItems();
        for (String hostname : keyLoader.getListOfCachedServers()) {
            hostnameDropDown.addItem(hostname);
        }
    }
}
