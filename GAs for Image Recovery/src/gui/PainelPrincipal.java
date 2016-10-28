/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import bio.BioProcess;
import java.awt.image.ImageFilter;
import java.io.File;
import java.util.ArrayList;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFileChooser;
import processing.ProcessImage;

/**
 *
 * @author Igor Pig
 */
public class PainelPrincipal extends javax.swing.JFrame {

    
    DisplayPrincipal principal;
    PlanarImage original;
    ArrayList<PlanarImage> imgs = new ArrayList<>();
    /**
     * Creates new form PainelPrincipal
     */
    public PainelPrincipal() {
        super("GAs 4 Image Retrieval");
        initComponents();
        
        principal = new DisplayPrincipal();
        painelDeImagem.add(principal);
        painelDeImagem.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        openButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        compareButton = new javax.swing.JButton();
        painelDeImagem = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        openButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open.png"))); // NOI18N
        openButton.setText("Open Image");
        openButton.setFocusable(false);
        openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(openButton);

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        addButton.setText("Add Gallery");
        addButton.setFocusable(false);
        addButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addButton);

        compareButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/compare.png"))); // NOI18N
        compareButton.setText("Compute");
        compareButton.setFocusable(false);
        compareButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        compareButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        compareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(compareButton);

        javax.swing.GroupLayout painelDeImagemLayout = new javax.swing.GroupLayout(painelDeImagem);
        painelDeImagem.setLayout(painelDeImagemLayout);
        painelDeImagemLayout.setHorizontalGroup(
            painelDeImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );
        painelDeImagemLayout.setVerticalGroup(
            painelDeImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(painelDeImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(painelDeImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        
        String path = chooser.getSelectedFile().getAbsolutePath();
        
        imgs.add(ProcessImage.convertImageToGrayScale(JAI.create("fileload", path)));
    }//GEN-LAST:event_addButtonActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        
        String path = chooser.getSelectedFile().getAbsolutePath();
        
        original = JAI.create("fileload", path);
        original = ProcessImage.convertImageToGrayScale(original);
        principal.setImage(original);
    }//GEN-LAST:event_openButtonActionPerformed

    private void compareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareButtonActionPerformed
        BioProcess bio = new BioProcess();
        bio.startBioProcess(original, imgs, 50, 10, 50,(original.getWidth()*original.getHeight())/2,0.9);
    }//GEN-LAST:event_compareButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PainelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PainelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PainelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PainelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PainelPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton compareButton;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton openButton;
    private javax.swing.JPanel painelDeImagem;
    // End of variables declaration//GEN-END:variables
}
