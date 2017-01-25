/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vd_client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import vd_client.helper.SocketClient;
import vd_client.helper.globalVars;

/**
 *
 * @author pouya
 */
public class frm_startup extends javax.swing.JFrame {

    /**
     * Creates new form frm_startup
     */
    public frm_startup() {
        initComponents();
        this.setLocationRelativeTo(null);
        setPanelEnabled(pnl_authentication, false);


    }

    void setPanelEnabled(JPanel panel, Boolean isEnabled) {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (int i = 0; i < components.length; i++) {
            if (components[i].getClass().getName().equals("javax.swing.JPanel")) {
                setPanelEnabled((JPanel) components[i], isEnabled);
            }

            components[i].setEnabled(isEnabled);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txt_ip = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_port = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_connect = new javax.swing.JLabel();
        pnl_authentication = new javax.swing.JPanel();
        btn_login = new javax.swing.JButton();
        btn_register = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_passwd = new javax.swing.JPasswordField();
        txt_username = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Virtual Drive");
        setResizable(false);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1485294565_2.png"))); // NOI18N

        txt_ip.setText("0.0.0.0");
        txt_ip.setToolTipText("Server Address");

        jLabel4.setText("Server Address");

        txt_port.setText("3575");
        txt_port.setToolTipText("Server Port");

        jLabel5.setText("Server Port");

        btn_connect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1485294425_success.png"))); // NOI18N
        btn_connect.setToolTipText("Connect");
        btn_connect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_connect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_connectMouseClicked(evt);
            }
        });

        btn_login.setText("Login");
        btn_login.setToolTipText("Login");
        btn_login.setRequestFocusEnabled(false);
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        btn_register.setText("Register");
        btn_register.setToolTipText("Register");
        btn_register.setRequestFocusEnabled(false);
        btn_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registerActionPerformed(evt);
            }
        });

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        txt_passwd.setToolTipText("Password");

        txt_username.setToolTipText("Username");
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_authenticationLayout = new javax.swing.GroupLayout(pnl_authentication);
        pnl_authentication.setLayout(pnl_authenticationLayout);
        pnl_authenticationLayout.setHorizontalGroup(
            pnl_authenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_authenticationLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnl_authenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(pnl_authenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_authenticationLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_authenticationLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_passwd, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_authenticationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_authenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );
        pnl_authenticationLayout.setVerticalGroup(
            pnl_authenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_authenticationLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnl_authenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(9, 9, 9)
                .addGroup(pnl_authenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_passwd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_register, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_port)
                                    .addComponent(txt_ip, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_connect))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pnl_authentication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(150, 150, 150))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_port, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(btn_connect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(pnl_authentication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private SocketClient socketclient = null;

    public void connect_callback(boolean connected) {
        if (connected) {
            //   this.btn_login.setEnabled(true);
            // this.btn_register.setEnabled(true);
            JsonObject jsObj = new JsonObject();
            jsObj.addProperty("Object_ID", globalVars.Auth_Settings);
            String json = jsObj.toString();

            socketclient.SendData(json, globalVars.Auth_Settings, "authconfig_callback");

        } else {
            this.btn_connect.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Cannot connect to server", "Cannot connect to Server!", JOptionPane.ERROR_MESSAGE);
        }

    }
     public void authconfig_callback(boolean status, String msg) {

        if (status) {
              JsonObject jsobj = new Gson().fromJson(msg, JsonObject.class);
              boolean is_public = jsobj.get("is_public").getAsBoolean();
              boolean Allow_signup = jsobj.get("Allow_signup").getAsBoolean();
              
              if(is_public){
                  login_callback(true, "");                 
              }else{
                  setPanelEnabled(pnl_authentication, true);
                  if(!Allow_signup){
                      btn_register.setEnabled(false);
                  }
              }
              
        } else {
            JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private boolean CheckSocket() {
        if (socketclient == null || !socketclient.isConnected()) {

            if (txt_ip.getText().trim().equals("") || txt_port.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill Server Address and Port", "Missing", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

            String ip = txt_ip.getText();
            Integer port = Integer.parseInt(txt_port.getText());

            try {
                socketclient = new SocketClient(ip, port, this, "connect_callback");
                //   socketclient.setActive_form(this);

                return socketclient.isConnected();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        } else {
            return socketclient.isConnected();
        }
    }
    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed
        // TODO add your handling code here:

        String username = txt_username.getText();
        String passwd = String.valueOf(txt_passwd.getPassword());

        if (username.trim().equals("") || passwd.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JsonObject jsObj = new JsonObject();
        jsObj.addProperty("Object_ID", globalVars.USER_REGISTER);
        jsObj.addProperty("username", username);
        jsObj.addProperty("password", passwd);

        String json = jsObj.toString();

        socketclient.SendData(json, globalVars.USER_REGISTER, "register_callback");


    }//GEN-LAST:event_btn_registerActionPerformed

    public void register_callback(boolean status, String msg) {

        if (status) {
            JOptionPane.showMessageDialog(null, msg, "Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void login_callback(boolean status, String msg) {

        if (status) {
            JFrame main = new frm_main(socketclient,this);
            btn_connect.setEnabled(true);
            main.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
        String username = txt_username.getText();
        String passwd = String.valueOf(txt_passwd.getPassword());

        JsonObject jsObj = new JsonObject();
        jsObj.addProperty("Object_ID", globalVars.USER_LOGIN);
        jsObj.addProperty("username", username);
        jsObj.addProperty("password", passwd);

        String json = jsObj.toString();

        socketclient.SendData(json, globalVars.USER_LOGIN, "login_callback");


    }//GEN-LAST:event_btn_loginActionPerformed

    private void btn_connectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_connectMouseClicked
        // TODO add your handling code here:
        this.btn_connect.setEnabled(false);
        CheckSocket();
    }//GEN-LAST:event_btn_connectMouseClicked

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
            java.util.logging.Logger.getLogger(frm_startup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_startup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_startup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_startup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_startup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_connect;
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_register;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel pnl_authentication;
    private javax.swing.JTextField txt_ip;
    private javax.swing.JPasswordField txt_passwd;
    private javax.swing.JTextField txt_port;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}