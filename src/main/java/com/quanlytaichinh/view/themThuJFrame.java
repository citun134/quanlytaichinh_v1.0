/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quanlytaichinh.view;

import com.quanlytaichinh.controller.HomeViewController;
import com.quanlytaichinh.controller.LoginController;
import com.quanlytaichinh.dao.GiaoDichDao;
import com.quanlytaichinh.model.GiaoDichThuModel;
import com.quanlytaichinh.model.LoginModel;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class themThuJFrame extends javax.swing.JFrame {

    /**
     * Creates new form themThuJFrame
     */
    
    public HomeViewController homeViewController;
    public HomeViewPro homeViewPro;
    public GiaoDichDao giaoDichDao;
    public LoginController loginController;
    public LoginModel loginModel;
    public LoginView loginView;
    public GiaoDichThuModel giaoDichThuModel;
    public SimpleDateFormat simpleDateFormat;
    
    public int loginId;
    
    public themThuJFrame() {}
    
    public themThuJFrame(HomeViewPro homeViewPro, int logId){
        initComponents();
        setLocationRelativeTo(null);
        
        this.homeViewPro = homeViewPro;
        loginId = logId;
        
        System.out.println("loggoedInAccount id themThu: " + loginId);
        
        homeViewController = new HomeViewController();
        giaoDichThuModel = new GiaoDichThuModel();
        
        thoiGianThuTGDTextField.setDateFormatString("yyyy-MM-dd");
        
        Date date = new Date();
        
        thoiGianThuTGDTextField.setDate(date);
    }
    
//    public void themGD(int accountId) {
//        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateTGD = thoiGianThuTGDTextField.getDate();
//        
//        String ngayTGD = simpleDateFormat.format(dateTGD);
//        String thanhTienTGD = thanhTienThuTGDTextField.getText();
//        String ghiChuTGD = ghiChuThuTGDTextField.getText();
//
//        try {
//            if (!thanhTienTGD.isEmpty() && !ngayTGD.isEmpty()) {
//                if (!thanhTienTGD.matches(".*[a-zA-Z].*")) {
//                    DecimalFormat decimalFormat = new DecimalFormat();
//                    decimalFormat.setParseBigDecimal(true);
//                    BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(thanhTienTGD);
//
//                    // Set properties of the giaoDichModel object
//                    giaoDichThuModel.setDate(ngayTGD);
//                    giaoDichThuModel.setThanhTien(bigDecimal.doubleValue());
//                    giaoDichThuModel.setGhiChu(ghiChuTGD);
//
//                    if (luongRadioButton.isSelected()) {
//                        giaoDichThuModel.setHangMuc("Lương");
//                    } else if (thuongRadioButton.isSelected()) {
//                        giaoDichThuModel.setHangMuc("Thưởng");
//                    } else if (choTangRadioButton.isSelected()) {
//                        giaoDichThuModel.setHangMuc("Được cho/tặng");
//                    } else if (khacThuRadioButton.isSelected()) {
//                        giaoDichThuModel.setHangMuc("Khác");
//                    }
//                    giaoDichThuModel.setAccountId(accountId);
//
//                    homeViewController.addGiaoDichThu(giaoDichThuModel);
//
//                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Thêm Thất Bại! Thanh Tiền không hợp lệ.");
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "Thêm Thất Bại! Vui lòng nhập đầy đủ thông tin.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//
//        // Clear text fields after adding a new record
//        thanhTienThuTGDTextField.setText("");
//        ghiChuThuTGDTextField.setText("");
//    }

    
    
    public void themGD(int accountId) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTGD = thoiGianThuTGDTextField.getDate();

        String ngayTGD = simpleDateFormat.format(dateTGD);
        String thanhTienTGD = thanhTienThuTGDTextField.getText();
        String ghiChuTGD = ghiChuThuTGDTextField.getText();

        try {
            if (!thanhTienTGD.isEmpty() && !ngayTGD.isEmpty()) {
                if (!thanhTienTGD.matches(".*[a-zA-Z].*")) {
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setParseBigDecimal(true);
                    BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(thanhTienTGD);

                    // Check if a radio button is selected
                    if (luongRadioButton.isSelected() || thuongRadioButton.isSelected() || choTangRadioButton.isSelected() || khacThuRadioButton.isSelected()) {
                        // Set properties of the giaoDichModel object
                        giaoDichThuModel.setDate(ngayTGD);
                        giaoDichThuModel.setThanhTien(bigDecimal.doubleValue());
                        giaoDichThuModel.setGhiChu(ghiChuTGD);

                        if (luongRadioButton.isSelected()) {
                            giaoDichThuModel.setHangMuc("Lương");
                        } else if (thuongRadioButton.isSelected()) {
                            giaoDichThuModel.setHangMuc("Thưởng");
                        } else if (choTangRadioButton.isSelected()) {
                            giaoDichThuModel.setHangMuc("Được cho/tặng");
                        } else if (khacThuRadioButton.isSelected()) {
                            giaoDichThuModel.setHangMuc("Khác");
                        }
                        giaoDichThuModel.setAccountId(accountId);

                        homeViewController.addGiaoDichThu(giaoDichThuModel);

                        JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm Thất Bại! Vui lòng chọn một hạng mục.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm Thất Bại! Thanh Tiền không hợp lệ.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Thất Bại! Vui lòng nhập đầy đủ thông tin.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        // Clear text fields after adding a new record
        thanhTienThuTGDTextField.setText("");
        ghiChuThuTGDTextField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        headerThemGiaoDichPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        bodyThemGiaoDichPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        thanhTienThuTGDTextField = new javax.swing.JTextField();
        ghiChuThuTGDTextField = new javax.swing.JTextField();
        themThuTGDButton = new javax.swing.JButton();
        thoatThuTGDButton = new javax.swing.JButton();
        hangMucButton = new javax.swing.JLabel();
        thuongRadioButton = new javax.swing.JRadioButton();
        luongRadioButton = new javax.swing.JRadioButton();
        choTangRadioButton = new javax.swing.JRadioButton();
        khacThuRadioButton = new javax.swing.JRadioButton();
        thoiGianThuTGDTextField = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerThemGiaoDichPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Notes.png"))); // NOI18N
        jLabel6.setText("THÊM GIAO DỊCH");

        javax.swing.GroupLayout headerThemGiaoDichPanelLayout = new javax.swing.GroupLayout(headerThemGiaoDichPanel);
        headerThemGiaoDichPanel.setLayout(headerThemGiaoDichPanelLayout);
        headerThemGiaoDichPanelLayout.setHorizontalGroup(
            headerThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        headerThemGiaoDichPanelLayout.setVerticalGroup(
            headerThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(headerThemGiaoDichPanel, java.awt.BorderLayout.PAGE_START);

        bodyThemGiaoDichPanel.setBackground(new java.awt.Color(255, 255, 255));
        bodyThemGiaoDichPanel.setPreferredSize(new java.awt.Dimension(490, 400));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Thời Gian");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Thành Tiền");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Ghi Chú");

        themThuTGDButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        themThuTGDButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Accept.png"))); // NOI18N
        themThuTGDButton.setText("  THÊM");
        themThuTGDButton.setToolTipText("");
        themThuTGDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themThuTGDButtonActionPerformed(evt);
            }
        });

        thoatThuTGDButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        thoatThuTGDButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Delete.png"))); // NOI18N
        thoatThuTGDButton.setText("THOÁT");
        thoatThuTGDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoatThuTGDButtonActionPerformed(evt);
            }
        });

        hangMucButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        hangMucButton.setText("Hạng Mục");

        buttonGroup1.add(thuongRadioButton);
        thuongRadioButton.setText("Thưởng");

        buttonGroup1.add(luongRadioButton);
        luongRadioButton.setText("Lương");

        buttonGroup1.add(choTangRadioButton);
        choTangRadioButton.setText("Được cho/tặng");
        choTangRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choTangRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(khacThuRadioButton);
        khacThuRadioButton.setText("Khác");

        javax.swing.GroupLayout bodyThemGiaoDichPanelLayout = new javax.swing.GroupLayout(bodyThemGiaoDichPanel);
        bodyThemGiaoDichPanel.setLayout(bodyThemGiaoDichPanelLayout);
        bodyThemGiaoDichPanelLayout.setHorizontalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hangMucButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(thanhTienThuTGDTextField)
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addComponent(luongRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(thuongRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(choTangRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(khacThuRadioButton)
                                .addGap(0, 81, Short.MAX_VALUE))
                            .addComponent(thoiGianThuTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ghiChuThuTGDTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(thoatThuTGDButton)))
                .addContainerGap())
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(themThuTGDButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bodyThemGiaoDichPanelLayout.setVerticalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(thoiGianThuTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thanhTienThuTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ghiChuThuTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hangMucButton)
                    .addComponent(luongRadioButton)
                    .addComponent(thuongRadioButton)
                    .addComponent(choTangRadioButton)
                    .addComponent(khacThuRadioButton))
                .addGap(40, 40, 40)
                .addComponent(themThuTGDButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(thoatThuTGDButton)
                .addContainerGap())
        );

        getContentPane().add(bodyThemGiaoDichPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themThuTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themThuTGDButtonActionPerformed
        System.out.println("loggoedInAccount id thembutton: " + loginId);
        themGD(loginId);
        homeViewPro.refreshTableThuData();
    }//GEN-LAST:event_themThuTGDButtonActionPerformed

    private void thoatThuTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatThuTGDButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_thoatThuTGDButtonActionPerformed

    private void choTangRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choTangRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choTangRadioButtonActionPerformed

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
            java.util.logging.Logger.getLogger(themThuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themThuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themThuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themThuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themThuJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyThemGiaoDichPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton choTangRadioButton;
    private javax.swing.JTextField ghiChuThuTGDTextField;
    private javax.swing.JLabel hangMucButton;
    private javax.swing.JPanel headerThemGiaoDichPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton khacThuRadioButton;
    private javax.swing.JRadioButton luongRadioButton;
    private javax.swing.JTextField thanhTienThuTGDTextField;
    private javax.swing.JButton themThuTGDButton;
    private javax.swing.JButton thoatThuTGDButton;
    private com.toedter.calendar.JDateChooser thoiGianThuTGDTextField;
    private javax.swing.JRadioButton thuongRadioButton;
    // End of variables declaration//GEN-END:variables
}
