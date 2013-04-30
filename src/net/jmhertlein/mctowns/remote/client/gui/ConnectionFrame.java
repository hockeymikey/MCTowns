/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jmhertlein.mctowns.remote.client.gui;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.PopupFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import net.jmhertlein.mctowns.remote.AuthenticationAttemptRejectedException;
import net.jmhertlein.mctowns.remote.RemoteAction;
import net.jmhertlein.mctowns.remote.ServerTrustException;
import net.jmhertlein.mctowns.remote.client.ActionFailReason;
import net.jmhertlein.mctowns.remote.client.KeyLoader;
import net.jmhertlein.mctowns.remote.client.MCTClientProtocol;
import net.jmhertlein.mctowns.remote.client.NamedKeyPair;
import sun.misc.BASE64Encoder;

/**
 *
 * @author joshua
 */
public class ConnectionFrame extends javax.swing.JFrame {
    private static final String SERVER_FAIL_CHALLENGE_MESSAGE = "The server presented the expected public key, but was unable to pass our authentication challenge.\n"
            + ". This may be because:\n" + 
            "* The server operator re-created the server's encryption keys. (Unlikely to cause this error.)\n" +
            "* You are being redirected to a potentially malicious server (Man-In-The-Middle Attack). (MUCH MORE LIKELY)\n" + 
            "No security breach has occurred, but this is a very serious security concern. You should not connect to this server.\n"
            + "If you want to continue connecting anyway, please clear the server's cached public key\n"
            + "under the \"Advanced\" button.",
            
            
            SERVER_KEY_MISMATCH_MESSAGE = "The server's identity has changed. This may be because:\n" + 
            "* The server operator re-created the server's encryption keys.\n" +
            "* You are being redirected to a potentially malicious server (Man-In-The-Middle Attack).\n" + 
            "No security breach has occurred. You should contact the server operator and\n"
            + "ask them if this is expected.\n"
            + "If you want to continue connecting anyway, please clear the server's cached public key\n"
            + "under the \"Advanced\" button.";

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
        keyPairDropDown = new javax.swing.JComboBox();
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
        connectionStatusLabel = new javax.swing.JLabel();
        conStatusField = new javax.swing.JTextField();

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

        jLabel2.setText("Identity:");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        advancedPaneLayout.setVerticalGroup(
            advancedPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedPaneLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
        );

        portField.setText("3333");
        portField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Port No.:");

        connectionStatusLabel.setText("Connection Status:");

        conStatusField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connectionProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(advancedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(connectionStatusLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(conStatusField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hostnameDropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keyPairDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(advancedButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageKeysButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostnameDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(keyPairDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(connectButton)
                        .addComponent(advancedButton)
                        .addComponent(manageKeysButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(conStatusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectionStatusLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectionProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(advancedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        Object selected = hostnameDropDown.getSelectedItem();
        if(selected == null) {
            conStatusField.setText("Must specify hostname.");
            return;
        }
        
        final String host = selected.toString();
        final int port;
        try {
            port = Integer.parseInt(portField.getText());
        } catch(NumberFormatException nfe) {
            conStatusField.setText("Bad Port: \"" + portField.getText() + "\"");
            return;
        }
        
        Object selectedKPObject = keyPairDropDown.getSelectedItem();
        if(selectedKPObject == null) {
            conStatusField.setText("Must select identity.");
            return;
        }

        final NamedKeyPair selectedKP = keyLoader.getLoadedKey(keyPairDropDown.getSelectedItem().toString().trim());
        
        final String username = selectedKP.getName();

        connectionProgressBar.setIndeterminate(true);

        SwingWorker x = new SwingWorker() {
            @Override
            protected ActionFailReason doInBackground() {
                MCTClientProtocol protocol = new MCTClientProtocol(host, port, keyLoader, username, selectedKP.getPubKey(), selectedKP.getPrivateKey());
                try {
                    protocol.submitAction(RemoteAction.KEY_EXCHANGE);
                } catch (UnknownHostException ex) {
                    return ActionFailReason.UNKNOWN_HOST;
                } catch (IOException ex) {
                    return ActionFailReason.CONNECTION_REFUSED;
                } catch (ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                    return ActionFailReason.FATAL_ERROR;
                } catch(AuthenticationAttemptRejectedException ex) {
                    return ActionFailReason.CLIENT_FAILED_SERVER_CHALLENGE;
                } catch(ServerTrustException ex) {
                    return ex.getReason();
                }
                
                return ActionFailReason.NO_FAILURE;
            }

            @Override
            protected void done() {
                connectionProgressBar.setIndeterminate(false);
                ActionFailReason failReason;
                try {
                    failReason = (ActionFailReason) get();
                } catch (        InterruptedException | ExecutionException ex) {
                    Logger.getLogger(ConnectionFrame.class.getName()).log(Level.SEVERE, null, ex);
                    failReason = null;
                }
                if(failReason == null) {
                    conStatusField.setText("Unknown error.");
                    return;
                }
                switch(failReason) {
                    case CLIENT_FAILED_SERVER_CHALLENGE:
                        conStatusField.setText("Failed server authentication challenge.");
                        break;
                    case SERVER_FAILED_CLIENT_CHALLENGE:
                        conStatusField.setText("Server failed our authentication challenge.");
                        JOptionPane.showMessageDialog(null, SERVER_FAIL_CHALLENGE_MESSAGE, "Possible Security Issue", JOptionPane.ERROR_MESSAGE);
                        break;
                    case SERVER_PUBLIC_KEY_MISMATCH:
                        conStatusField.setText("Server public key does not match cached key.");
                        JOptionPane.showMessageDialog(null, SERVER_KEY_MISMATCH_MESSAGE, "Possible Security Issue", JOptionPane.ERROR_MESSAGE);
                        break;
                    case CONNECTION_REFUSED:
                        conStatusField.setText("Connection refused.");
                        break;
                    case FATAL_ERROR:
                        conStatusField.setText("Unknown error.");
                        break;
                    case UNKNOWN_HOST:
                        conStatusField.setText("Unknown host.");
                        break;
                    case NO_FAILURE:
                        conStatusField.setText("Connection successful.");
                        break;
                }
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

    private void portFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portFieldActionPerformed

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
    private javax.swing.JTextField conStatusField;
    private javax.swing.JButton connectButton;
    private javax.swing.JProgressBar connectionProgressBar;
    private javax.swing.JLabel connectionStatusLabel;
    private javax.swing.JComboBox hostnameDropDown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox keyPairDropDown;
    private javax.swing.JButton manageKeysButton;
    private javax.swing.JTextField portField;
    private javax.swing.JTextArea serverPubKeyArea;
    // End of variables declaration//GEN-END:variables

    private void updateKeyComboBox() {
        keyPairDropDown.removeAllItems();
        for (NamedKeyPair pair : keyLoader.getLoadedPairs()) {
            keyPairDropDown.addItem(pair.getName());
        }
    }

    private void updateServerComboBox() {
        hostnameDropDown.removeAllItems();
        for (String hostname : keyLoader.getListOfCachedServers()) {
            hostnameDropDown.addItem(hostname);
        }
    }
}
