package com.quanlytaichinh.view;

import com.quanlytaichinh.controller.LoginController;
import com.quanlytaichinh.dao.JDBCConnection;
import com.quanlytaichinh.dao.LoginDao;
import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.model.LoginModel;
import com.quanlytaichinh.controller.HomeViewController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class LoginView extends javax.swing.JFrame {
    public int loggedInAccountId;
    public LoginModel loginModel;
    public LoginController loginController;
    public LoginDao loginDao;
    public GiaoDichModel giaoDichModel;
    public HomeViewController homeViewController;
    
    public LoginView() {
        try{
            initComponents();
            
            setLocationRelativeTo(null);
            
            loginModel = new LoginModel();
            loginController = new LoginController();
            loginDao = new LoginDao();
            
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dangKyDiaLog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        userDangKyTextField = new javax.swing.JTextField();
        passwordDangKyPasswordField = new javax.swing.JPasswordField();
        conPasswordDangKyPasswordField = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        thoatButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userLoginTextField = new javax.swing.JTextField();
        passwordLoginPasswordField = new javax.swing.JPasswordField();
        dangNhapButton = new javax.swing.JButton();
        taiKhoanMoiButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        dangKyDiaLog.setMinimumSize(new java.awt.Dimension(400, 400));
        dangKyDiaLog.setModal(true);
        dangKyDiaLog.setPreferredSize(new java.awt.Dimension(400, 400));
        dangKyDiaLog.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                dangKyDiaLogComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\x1 gen6\\Documents\\NetBeansProjects\\quanlytaichinh\\src\\main\\java\\com\\quanlytaichinh\\images\\Unknown person.png")); // NOI18N
        jLabel9.setText("  ĐĂNG KÝ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        dangKyDiaLog.getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Tài khoản");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Mật khẩu");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Xác nhận mật khẩu");

        userDangKyTextField.setBackground(new java.awt.Color(245, 246, 247));
        userDangKyTextField.setMargin(new java.awt.Insets(5, 5, 5, 5));

        passwordDangKyPasswordField.setBackground(new java.awt.Color(245, 246, 247));
        passwordDangKyPasswordField.setMargin(new java.awt.Insets(5, 5, 5, 5));

        conPasswordDangKyPasswordField.setBackground(new java.awt.Color(245, 246, 247));
        conPasswordDangKyPasswordField.setMargin(new java.awt.Insets(5, 5, 5, 5));

        jButton3.setBackground(new java.awt.Color(0, 164, 0));
        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("ĐĂNG KÝ");
        jButton3.setMargin(new java.awt.Insets(10, 20, 10, 20));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        thoatButton.setBackground(new java.awt.Color(255, 0, 51));
        thoatButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        thoatButton.setForeground(new java.awt.Color(255, 255, 255));
        thoatButton.setText("THOÁT");
        thoatButton.setMargin(new java.awt.Insets(10, 20, 10, 20));
        thoatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoatButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(thoatButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(70, 70, 70)
                        .addComponent(userDangKyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(74, 74, 74)
                        .addComponent(passwordDangKyPasswordField))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)
                        .addComponent(conPasswordDangKyPasswordField)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(userDangKyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(passwordDangKyPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(conPasswordDangKyPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(thoatButton)
                .addContainerGap())
        );

        dangKyDiaLog.getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 400));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\x1 gen6\\Documents\\NetBeansProjects\\quanlytaichinh\\src\\main\\java\\com\\quanlytaichinh\\images\\User.png")); // NOI18N
        jLabel2.setText("   LOGIN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Tài Khoản");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Mật Khẩu");
        jLabel4.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        userLoginTextField.setBackground(new java.awt.Color(245, 246, 247));
        userLoginTextField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        userLoginTextField.setMargin(new java.awt.Insets(5, 5, 5, 5));
        userLoginTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userLoginTextFieldActionPerformed(evt);
            }
        });

        passwordLoginPasswordField.setBackground(new java.awt.Color(245, 246, 247));
        passwordLoginPasswordField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        passwordLoginPasswordField.setMargin(new java.awt.Insets(5, 5, 5, 5));

        dangNhapButton.setBackground(new java.awt.Color(24, 119, 242));
        dangNhapButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dangNhapButton.setForeground(new java.awt.Color(255, 255, 255));
        dangNhapButton.setText("ĐĂNG NHẬP");
        dangNhapButton.setMargin(new java.awt.Insets(10, 20, 10, 20));
        dangNhapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangNhapButtonActionPerformed(evt);
            }
        });

        taiKhoanMoiButton.setBackground(new java.awt.Color(66, 183, 42));
        taiKhoanMoiButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        taiKhoanMoiButton.setForeground(new java.awt.Color(255, 255, 255));
        taiKhoanMoiButton.setText("TẠO TÀI KHOẢN MỚI");
        taiKhoanMoiButton.setMargin(new java.awt.Insets(10, 20, 10, 20));
        taiKhoanMoiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taiKhoanMoiButtonActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(218, 221, 225));
        jSeparator1.setForeground(new java.awt.Color(218, 221, 225));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 37, Short.MAX_VALUE)
                        .addComponent(taiKhoanMoiButton)
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userLoginTextField)
                            .addComponent(passwordLoginPasswordField))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dangNhapButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(userLoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(passwordLoginPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dangNhapButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(taiKhoanMoiButton)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        dangNhapButton.getAccessibleContext().setAccessibleName("ĐĂNG NHẬP");

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userLoginTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userLoginTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userLoginTextFieldActionPerformed

    private void dangNhapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangNhapButtonActionPerformed
     
    try {
        Connection connection = JDBCConnection.getJDBCConecction();
        String user = userLoginTextField.getText();
        String pass = passwordLoginPasswordField.getText();
        Statement stm = connection.createStatement();
        String sql = "SELECT * FROM accounts WHERE username = '" + user + "'" + " AND password = '" +  pass + "'";
        ResultSet rs = stm.executeQuery(sql);
        if(rs.next()){
            loggedInAccountId = rs.getInt("account_id");
            loginModel = new LoginModel(user, pass, loggedInAccountId);
            System.out.println("loggedInAccountId loginview: " + loggedInAccountId);
            // Now, set the accountId in your GiaoDichModel
            new HomeViewPro(loginModel).setVisible(true);
            this.dispose();
//            giaoDichModel.setAccountId(rs.getInt("account_id"));
            loginModel.setAccount_id(loggedInAccountId);
            
//            homeViewController.addGiaoDichThu(giaoDichModel);
//            giaoDichModel.setAccountId(loggedInAccountId);
        } else {
            JOptionPane.showMessageDialog(this, "Đăng nhập thất bại!");
            userLoginTextField.setText("");
            passwordLoginPasswordField.setText("");
        }
        connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_dangNhapButtonActionPerformed

    private void taiKhoanMoiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taiKhoanMoiButtonActionPerformed
        // TODO add your handling code here:
        dangKyDiaLog.setVisible(true);
    }//GEN-LAST:event_taiKhoanMoiButtonActionPerformed

    private void thoatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatButtonActionPerformed
        // TODO add your handling code here:
        dangKyDiaLog.setVisible(false);
    }//GEN-LAST:event_thoatButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dangKyDiaLog.setVisible(true);
        String username = userDangKyTextField.getText();
        String password = passwordDangKyPasswordField.getText();
        String conPassword = conPasswordDangKyPasswordField.getText();
        
        if (!username.isEmpty() && !password.isEmpty() 
            && username.matches("^[a-zA-Z0-9]*$") && password.equals(conPassword)) {
            loginModel.setUser(username);
            loginModel.setPassword(password);

            loginController.addUser(loginModel);
            userDangKyTextField.setText("");
            passwordDangKyPasswordField.setText("");
            conPasswordDangKyPasswordField.setText("");
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin đăng ký.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void dangKyDiaLogComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dangKyDiaLogComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_dangKyDiaLogComponentShown

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
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField conPasswordDangKyPasswordField;
    private javax.swing.JDialog dangKyDiaLog;
    private javax.swing.JButton dangNhapButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPasswordField passwordDangKyPasswordField;
    private javax.swing.JPasswordField passwordLoginPasswordField;
    private javax.swing.JButton taiKhoanMoiButton;
    private javax.swing.JButton thoatButton;
    private javax.swing.JTextField userDangKyTextField;
    private javax.swing.JTextField userLoginTextField;
    // End of variables declaration//GEN-END:variables
}
