/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.paoe.view.frame;

import co.edu.usbbog.paoe.view.panel.AsesoriaJPanel;

import co.edu.usbbog.paoe.view.panel.ContrasenaJPanel;
import co.edu.usbbog.paoe.view.panel.PresentacionJPanel;
import co.edu.usbbog.paoe.view.panel.RegistrarJPanel;
import co.edu.usbbog.paoe.view.panel.HistorialJPanel;
import co.edu.usbbog.paoe.view.panel.IniciarSesionJPanel;
import co.edu.usbbog.paoe.view.panel.RegistrarPsicologoJPanel;
import co.edu.usbbog.paoe.view.panel.TextPrompt;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Juan Nicolas
 */
public class Principal extends javax.swing.JFrame  {

    private PresentacionJPanel presentacionJPanel;
    protected RegistrarJPanel registrarJPanel;
    protected AsesoriaJPanel asesoriaJPanel;
    protected HistorialJPanel historialJPanel;
    protected IniciarSesionJPanel iniciarsesionJPanel;
    protected RegistrarPsicologoJPanel registrarPsicologoJPanel;
    protected ContrasenaJPanel contrasenaJPanel;
    
    
    
    
    /**
     * Creates new form Principal
     */
    public Principal() {
        this.setTitle("APPAOE");
        
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/co/edu/usbbog/paoe/view/image/emociones.png")).getImage());
        iniciar();
    }
    
    protected void iniciar() {
        iniciarsesionJPanel = new IniciarSesionJPanel(this);
        iniciarsesionJPanel.setVisible(true);
        add(iniciarsesionJPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    
    public void irPresentacionJPanel(JPanel jPanel) {
        remove(jPanel);
        presentacionJPanel = new PresentacionJPanel(this);
        presentacionJPanel.setVisible(true);
        add(presentacionJPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    
    public void irRegistrarJPanel(JPanel jPanel) {
        remove(jPanel);
        registrarJPanel = new RegistrarJPanel(this);
        registrarJPanel.setVisible(true);
        add(registrarJPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    public void irAsesoriaJPanel(JPanel jPanel) {
        remove(jPanel);
        asesoriaJPanel = new AsesoriaJPanel(this);
        asesoriaJPanel.setVisible(true);
        add(asesoriaJPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    public void irHistorialJPanel(JPanel jPanel) {
        remove(jPanel);
        historialJPanel = new HistorialJPanel(this);
        historialJPanel.setVisible(true);
        add(historialJPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    public void irRegistrarPsicologoJPanel(JPanel jPanel) {
        remove(jPanel);
        registrarPsicologoJPanel = new RegistrarPsicologoJPanel(this);
        registrarPsicologoJPanel.setVisible(true);
        add(registrarPsicologoJPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    public void irIniciarSesionJPanel(JPanel jPanel) {
        remove(jPanel);
        iniciarsesionJPanel = new IniciarSesionJPanel(this);
        iniciarsesionJPanel.setVisible(true);
        add(iniciarsesionJPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    
    public void irContrasenaJPanel(JPanel jPanel) {
        remove(jPanel);
        contrasenaJPanel = new ContrasenaJPanel(this);
        contrasenaJPanel.setVisible(true);
        add(contrasenaJPanel);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
