/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.paoe.view.panel;

import co.edu.usbbog.paoe.view.frame.Principal;

/**
 *
 * @author Juan Nicolas
 */
public class PresentacionJPanel extends javax.swing.JPanel {

    private Principal principal;
    private IniciarSesionJPanel inicio;
    
    /**
     * Creates new form Presentacion
     */
    public PresentacionJPanel(Principal principal) {
        this.principal = principal;
        initComponents();   
        this.inicio = new IniciarSesionJPanel(principal);
        this.nombreUsuario.setText(inicio.darNombreUsuario());
    }
    
    private void irRegistrarJPanel() {
        principal.irRegistrarJPanel(this);
    }
    
    private void irAsesoriaJPanel() {
        principal.irAsesoriaJPanel(this);
    }
    
    private void irHistorialJPanel() {
        principal.irHistorialJPanel(this);
    }
    
    private void irIniciarSesionJPanel() {
        principal.irIniciarSesionJPanel(this);
    }
    
    private void irRegistrarPsicologoJPanel() {
        principal.irRegistrarPsicologoJPanel(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        boton1 = new co.edu.usbbog.paoe.view.image.Boton();
        boton2 = new co.edu.usbbog.paoe.view.image.Boton();
        boton3 = new co.edu.usbbog.paoe.view.image.Boton();
        nombreUsuario = new javax.swing.JLabel();
        boton4 = new co.edu.usbbog.paoe.view.image.Boton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Nunito", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("APPAOE");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, -1, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/usbbog/paoe/view/image/emociones.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/usbbog/paoe/view/image/river.jpg"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-8, 0, 1580, 90));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        boton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/usbbog/paoe/view/image/nota.png"))); // NOI18N
        boton1.setText(" Registrar Paciente");
        boton1.setColorHover(new java.awt.Color(204, 204, 204));
        boton1.setColorNormal(new java.awt.Color(214, 217, 223));
        boton1.setColorPressed(new java.awt.Color(153, 153, 153));
        boton1.setColorTextHover(new java.awt.Color(0, 0, 0));
        boton1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        boton1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        boton1.setFont(new java.awt.Font("Nunito", 1, 18)); // NOI18N
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });

        boton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/usbbog/paoe/view/image/emocion.png"))); // NOI18N
        boton2.setText("Asesoría");
        boton2.setColorHover(new java.awt.Color(204, 204, 204));
        boton2.setColorNormal(new java.awt.Color(214, 217, 223));
        boton2.setColorPressed(new java.awt.Color(153, 153, 153));
        boton2.setColorTextHover(new java.awt.Color(0, 0, 0));
        boton2.setColorTextNormal(new java.awt.Color(0, 0, 0));
        boton2.setColorTextPressed(new java.awt.Color(0, 0, 0));
        boton2.setFont(new java.awt.Font("Nunito", 1, 18)); // NOI18N
        boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });

        boton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/usbbog/paoe/view/image/dosier.png"))); // NOI18N
        boton3.setText("Historial");
        boton3.setColorHover(new java.awt.Color(204, 204, 204));
        boton3.setColorNormal(new java.awt.Color(214, 217, 223));
        boton3.setColorPressed(new java.awt.Color(153, 153, 153));
        boton3.setColorTextHover(new java.awt.Color(0, 0, 0));
        boton3.setColorTextNormal(new java.awt.Color(0, 0, 0));
        boton3.setColorTextPressed(new java.awt.Color(0, 0, 0));
        boton3.setFont(new java.awt.Font("Nunito", 1, 18)); // NOI18N
        boton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton3ActionPerformed(evt);
            }
        });

        nombreUsuario.setFont(new java.awt.Font("Nunito", 1, 15)); // NOI18N
        nombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreUsuario.setText("Psicólogo");
        nombreUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        boton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/usbbog/paoe/view/image/psicologo.png"))); // NOI18N
        boton4.setText("Registrar Psicólogo");
        boton4.setColorHover(new java.awt.Color(204, 204, 204));
        boton4.setColorNormal(new java.awt.Color(214, 217, 223));
        boton4.setColorPressed(new java.awt.Color(153, 153, 153));
        boton4.setColorTextHover(new java.awt.Color(0, 0, 0));
        boton4.setColorTextNormal(new java.awt.Color(0, 0, 0));
        boton4.setColorTextPressed(new java.awt.Color(0, 0, 0));
        boton4.setFont(new java.awt.Font("Nunito", 1, 18)); // NOI18N
        boton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton4ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/usbbog/paoe/view/image/apagar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(boton2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addComponent(boton3, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(boton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(nombreUsuario)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(boton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 82, 230, 740));

        jLabel5.setFont(new java.awt.Font("Nunito", 0, 18)); // NOI18N
        jLabel5.setText("La aplicación APPAOE le ayudará al psicólogo a tener un mejor control de la información de sus pacientes de la manera más accesible y segura.");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, -1, -1));

        jLabel6.setFont(new java.awt.Font("Nunito", 0, 18)); // NOI18N
        jLabel6.setText("Versión 1.0  © 2020 Todos los derechos reservados.");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, -1, -1));

        jLabel7.setFont(new java.awt.Font("Nunito", 0, 18)); // NOI18N
        jLabel7.setText("Desarrollada por:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 580, 140, -1));

        jLabel8.setFont(new java.awt.Font("Nunito", 0, 18)); // NOI18N
        jLabel8.setText("Juan Nicolás Montes Cetina");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 640, -1, -1));

        jLabel9.setFont(new java.awt.Font("Nunito", 0, 18)); // NOI18N
        jLabel9.setText("Juan Sebastián Rodríguez Correa");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 690, -1, -1));

        jLabel10.setFont(new java.awt.Font("Nunito", 0, 18)); // NOI18N
        jLabel10.setText("Santiago Echeverri Duque");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 740, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 1300, 620));

        jLabel4.setFont(new java.awt.Font("Nunito", 1, 24)); // NOI18N
        jLabel4.setText("Presentación de la Aplicación");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 120, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonMetro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro1ActionPerformed
        
    }//GEN-LAST:event_rSButtonMetro1ActionPerformed

    private void rSButtonMetro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro2ActionPerformed
        
    }//GEN-LAST:event_rSButtonMetro2ActionPerformed

    private void rSButtonMetro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro3ActionPerformed
        
    }//GEN-LAST:event_rSButtonMetro3ActionPerformed

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        irRegistrarJPanel();
    }//GEN-LAST:event_boton1ActionPerformed

    private void boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2ActionPerformed
        irAsesoriaJPanel();
    }//GEN-LAST:event_boton2ActionPerformed

    private void boton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton3ActionPerformed
        irHistorialJPanel();
    }//GEN-LAST:event_boton3ActionPerformed

    private void boton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton4ActionPerformed
       irRegistrarPsicologoJPanel();
    }//GEN-LAST:event_boton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        irIniciarSesionJPanel();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private co.edu.usbbog.paoe.view.image.Boton boton1;
    private co.edu.usbbog.paoe.view.image.Boton boton2;
    private co.edu.usbbog.paoe.view.image.Boton boton3;
    private co.edu.usbbog.paoe.view.image.Boton boton4;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel nombreUsuario;
    // End of variables declaration//GEN-END:variables
}
