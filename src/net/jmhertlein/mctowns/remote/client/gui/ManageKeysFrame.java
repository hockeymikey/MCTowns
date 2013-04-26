/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jmhertlein.mctowns.remote.client.gui;

import java.security.KeyPair;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import net.jmhertlein.core.crypto.CryptoManager;
import net.jmhertlein.mctowns.remote.client.KeyLoader;
import net.jmhertlein.mctowns.remote.client.NamedKeyPair;

/**
 *
 * @author joshua
 */
public class ManageKeysFrame extends javax.swing.JFrame {

    private final KeyLoader keyLoader;

    /**
     * Creates new form ManageKeysFrame
     */
    public ManageKeysFrame(KeyLoader keyLoader) {
        initComponents();
        this.keyLoader = keyLoader;
        updateKeyComboBox();
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        keyPairComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        genKeyPane = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        newPairLabelField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        newPairSizeComboBox = new javax.swing.JComboBox();
        genNewKeyPairButton = new javax.swing.JButton();
        keyGenProgressBar = new javax.swing.JProgressBar();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        pubKeyArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        privateKeyArea = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        keyPairComboBox.setToolTipText("");
        keyPairComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyPairComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Key Pair");

        jLabel3.setText("Key Pair Label:");

        jLabel4.setText("Key Length (bits)");

        newPairSizeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "512", "1024", "2048", "4096" }));

        genNewKeyPairButton.setText("Generate");
        genNewKeyPairButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genNewKeyPairButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout genKeyPaneLayout = new javax.swing.GroupLayout(genKeyPane);
        genKeyPane.setLayout(genKeyPaneLayout);
        genKeyPaneLayout.setHorizontalGroup(
            genKeyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genKeyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(genKeyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(keyGenProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(genKeyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(genKeyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(newPairSizeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newPairLabelField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(genKeyPaneLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(genNewKeyPairButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        genKeyPaneLayout.setVerticalGroup(
            genKeyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genKeyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(genKeyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPairLabelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(genKeyPaneLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(genKeyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(newPairSizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(genKeyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genNewKeyPairButton)
                    .addComponent(keyGenProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pubKeyArea.setColumns(20);
        pubKeyArea.setRows(5);
        jScrollPane2.setViewportView(pubKeyArea);

        jTabbedPane1.addTab("Public Key", jScrollPane2);

        privateKeyArea.setColumns(20);
        privateKeyArea.setRows(5);
        jScrollPane3.setViewportView(privateKeyArea);

        jTabbedPane1.addTab("Private Key", jScrollPane3);

        jButton2.setText("Generate Key...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(keyPairComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2)
                            .addComponent(genKeyPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 304, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyPairComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genKeyPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void keyPairComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyPairComboBoxActionPerformed
        if (keyPairComboBox.getSelectedItem() == null)
            return;

        String selectedPairName = keyPairComboBox.getSelectedItem().toString();

        NamedKeyPair pair = keyLoader.getLoadedKey(selectedPairName);

        pubKeyArea.setText(pair.getPublicEncoded());

        privateKeyArea.setText(pair.getPrivateEncoded());
    }//GEN-LAST:event_keyPairComboBoxActionPerformed

    
    private void updateKeyComboBox() {
        keyPairComboBox.removeAllItems();
        for(NamedKeyPair pair : keyLoader.getLoadedPairs()) {
            keyPairComboBox.addItem(pair.getName());
        }
    }
    
    private void genNewKeyPairButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genNewKeyPairButtonActionPerformed
        keyGenProgressBar.setIndeterminate(true);
        genNewKeyPairButton.setEnabled(false);
        final String label = newPairLabelField.getText().trim();
        final int bits = Integer.parseInt(newPairSizeComboBox.getSelectedItem().toString());
        System.out.println(bits);

        SwingWorker x = new SwingWorker() {
            @Override
            protected KeyPair doInBackground() throws Exception {
                return CryptoManager.newRSAKeyPair(bits);
            }

            @Override
            protected void done() {
                try {
                    keyLoader.persistAndLoadNewKeyPair(label, (KeyPair) get());
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(ManageKeysFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyPairComboBox.addItem(label);
                keyGenProgressBar.setIndeterminate(false);
                genNewKeyPairButton.setEnabled(true);
            }
        };
        
        x.execute();


    }//GEN-LAST:event_genNewKeyPairButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel genKeyPane;
    private javax.swing.JButton genNewKeyPairButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JProgressBar keyGenProgressBar;
    private javax.swing.JComboBox keyPairComboBox;
    private javax.swing.JTextField newPairLabelField;
    private javax.swing.JComboBox newPairSizeComboBox;
    private javax.swing.JTextArea privateKeyArea;
    private javax.swing.JTextArea pubKeyArea;
    // End of variables declaration//GEN-END:variables
}
