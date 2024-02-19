/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quanlytaichinh.view;

import com.quanlytaichinh.controller.HomeViewController;
import com.quanlytaichinh.controller.LoginController;
import com.quanlytaichinh.dao.GiaoDichDao;
import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.model.LoginModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author x1 gen6
 */
public class themChiJFrame extends javax.swing.JFrame {

    /**
     * Creates new form themChiJFrame
     */
    
    public HomeViewController homeViewController;
    public HomeViewPro homeViewPro;
    public GiaoDichDao giaoDichDao;
    public LoginController loginController;
    public LoginModel loginModel;
    public LoginView loginView;
    public GiaoDichModel giaoDichModel;
    public SimpleDateFormat simpleDateFormat;
    public DefaultTableModel defaultTableLSVModel;
    
    
    
    public int loginId;
    
    public themChiJFrame() {
    }
    
     
    public themChiJFrame(HomeViewPro homeViewPro, int loginModel){
        
        initComponents();
        setLocationRelativeTo(null);
        
        this.homeViewPro = homeViewPro;
        
        loginId = loginModel;
        System.out.println("loggoedInAccount id themchi: " + loginId);
        
        homeViewController = new HomeViewController();
        giaoDichModel = new GiaoDichModel();
        
        thoiGianTGDTextField.setDateFormatString("yyyy-MM-dd");
        
        Date date = new Date();
        
        thoiGianTGDTextField.setDate(date);
    }
        
//    public void themGD(int accountId) {
//        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateTGD = thoiGianTGDTextField.getDate();
//        
//        String ngayTGD = simpleDateFormat.format(dateTGD);
//        String matHangTGD = matHangTGDTextField.getText();
//        String thanhTienTGD = thanhTienTGDTextField.getText();
//        String ghiChuTGD = ghiChuTGDTextField.getText();
//
//        try {
//            if (!matHangTGD.isEmpty() && !thanhTienTGD.isEmpty() && !ngayTGD.isEmpty()) {
//                if (!thanhTienTGD.matches(".*[a-zA-Z].*")) {
//                    DecimalFormat decimalFormat = new DecimalFormat();
//                    decimalFormat.setParseBigDecimal(true);
//                    BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(thanhTienTGD);
//
//                    // Set properties of the giaoDichModel object
//                    giaoDichModel.setDate(ngayTGD);
//                    giaoDichModel.setMatHang(matHangTGD);
//                    giaoDichModel.setThanhTien(bigDecimal.doubleValue());
//                    giaoDichModel.setGhiChu(ghiChuTGD);
//
//                    if (anUongRadioButton.isSelected()) {
//                        giaoDichModel.setHangMuc("Ăn Uống");
//                    } else if (quanAoRadioButton.isSelected()) {
//                        giaoDichModel.setHangMuc("Quần Áo");
//                    } else if (dvSinhHoatRadioButton.isSelected()) {
//                        giaoDichModel.setHangMuc("Dịch Vụ Sinh Hoạt");
//                    } else if (khacRadioButton.isSelected()) {
//                        giaoDichModel.setHangMuc("Khác");
//                    }
//                    giaoDichModel.setAccountId(accountId);
//
//                    homeViewController.addGiaoDichChi(giaoDichModel);
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
//        matHangTGDTextField.setText("");
//        thanhTienTGDTextField.setText("");
//        ghiChuTGDTextField.setText("");
//    }
    
    
    public void themGD(int accountId) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTGD = thoiGianTGDTextField.getDate();

        String ngayTGD = simpleDateFormat.format(dateTGD);
        String matHangTGD = matHangTGDTextField.getText();
        String thanhTienTGD = thanhTienTGDTextField.getText();
        String ghiChuTGD = ghiChuTGDTextField.getText();

        try {
            if (!matHangTGD.isEmpty() && !thanhTienTGD.isEmpty() && !ngayTGD.isEmpty()) {
                if (!thanhTienTGD.matches(".*[a-zA-Z].*")) {
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setParseBigDecimal(true);
                    BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(thanhTienTGD);

                    // Check if a radio button is selected
                    if (anUongRadioButton.isSelected() || quanAoRadioButton.isSelected() || dvSinhHoatRadioButton.isSelected() || khacRadioButton.isSelected()) {
                        // Set properties of the giaoDichModel object
                        giaoDichModel.setDate(ngayTGD);
                        giaoDichModel.setMatHang(matHangTGD);
                        giaoDichModel.setThanhTien(bigDecimal.doubleValue());
                        giaoDichModel.setGhiChu(ghiChuTGD);

                        if (anUongRadioButton.isSelected()) {
                            giaoDichModel.setHangMuc("Ăn Uống");
                        } else if (quanAoRadioButton.isSelected()) {
                            giaoDichModel.setHangMuc("Quần Áo");
                        } else if (dvSinhHoatRadioButton.isSelected()) {
                            giaoDichModel.setHangMuc("Dịch Vụ Sinh Hoạt");
                        } else if (khacRadioButton.isSelected()) {
                            giaoDichModel.setHangMuc("Khác");
                        }
                        giaoDichModel.setAccountId(accountId);

                        homeViewController.addGiaoDichChi(giaoDichModel);

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
        matHangTGDTextField.setText("");
        thanhTienTGDTextField.setText("");
        ghiChuTGDTextField.setText("");
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
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        matHangTGDTextField = new javax.swing.JTextField();
        thanhTienTGDTextField = new javax.swing.JTextField();
        ghiChuTGDTextField = new javax.swing.JTextField();
        themTGDButton = new javax.swing.JButton();
        thoatTGDButton = new javax.swing.JButton();
        hangMucButton = new javax.swing.JLabel();
        quanAoRadioButton = new javax.swing.JRadioButton();
        anUongRadioButton = new javax.swing.JRadioButton();
        dvSinhHoatRadioButton = new javax.swing.JRadioButton();
        khacRadioButton = new javax.swing.JRadioButton();
        thoiGianTGDTextField = new com.toedter.calendar.JDateChooser();

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
                .addContainerGap(173, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(173, Short.MAX_VALUE))
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

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Mặt Hàng");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Thành Tiền");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Ghi Chú");

        themTGDButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        themTGDButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Accept.png"))); // NOI18N
        themTGDButton.setText("  THÊM");
        themTGDButton.setToolTipText("");
        themTGDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themTGDButtonActionPerformed(evt);
            }
        });

        thoatTGDButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        thoatTGDButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Delete.png"))); // NOI18N
        thoatTGDButton.setText("THOÁT");
        thoatTGDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoatTGDButtonActionPerformed(evt);
            }
        });

        hangMucButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        hangMucButton.setText("Hạng Mục");

        buttonGroup1.add(quanAoRadioButton);
        quanAoRadioButton.setText("Quần áo");

        buttonGroup1.add(anUongRadioButton);
        anUongRadioButton.setText("Ăn uống");

        buttonGroup1.add(dvSinhHoatRadioButton);
        dvSinhHoatRadioButton.setText("Dịch vụ sinh hoạt");
        dvSinhHoatRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dvSinhHoatRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(khacRadioButton);
        khacRadioButton.setText("Khác");

        javax.swing.GroupLayout bodyThemGiaoDichPanelLayout = new javax.swing.GroupLayout(bodyThemGiaoDichPanel);
        bodyThemGiaoDichPanel.setLayout(bodyThemGiaoDichPanelLayout);
        bodyThemGiaoDichPanelLayout.setHorizontalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addComponent(hangMucButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matHangTGDTextField)
                            .addComponent(thanhTienTGDTextField)
                            .addComponent(ghiChuTGDTextField)
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addComponent(anUongRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(quanAoRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(dvSinhHoatRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(khacRadioButton)
                                .addGap(0, 66, Short.MAX_VALUE))
                            .addComponent(thoiGianTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(thoatTGDButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(themTGDButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        bodyThemGiaoDichPanelLayout.setVerticalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thoiGianTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(matHangTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(thanhTienTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ghiChuTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hangMucButton)
                    .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(anUongRadioButton)
                        .addComponent(quanAoRadioButton)
                        .addComponent(dvSinhHoatRadioButton)
                        .addComponent(khacRadioButton)))
                .addGap(18, 18, 18)
                .addComponent(themTGDButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(thoatTGDButton)
                .addContainerGap())
        );

        getContentPane().add(bodyThemGiaoDichPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themTGDButtonActionPerformed
        System.out.println("loggoedInAccount id thembutton: " + loginId);
        themGD(loginId);
        homeViewPro.refreshTableChiData();
    }//GEN-LAST:event_themTGDButtonActionPerformed

    private void thoatTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatTGDButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_thoatTGDButtonActionPerformed

    private void dvSinhHoatRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dvSinhHoatRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dvSinhHoatRadioButtonActionPerformed

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
            java.util.logging.Logger.getLogger(themChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themChiJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton anUongRadioButton;
    private javax.swing.JPanel bodyThemGiaoDichPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton dvSinhHoatRadioButton;
    private javax.swing.JTextField ghiChuTGDTextField;
    private javax.swing.JLabel hangMucButton;
    private javax.swing.JPanel headerThemGiaoDichPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton khacRadioButton;
    private javax.swing.JTextField matHangTGDTextField;
    private javax.swing.JRadioButton quanAoRadioButton;
    private javax.swing.JTextField thanhTienTGDTextField;
    private javax.swing.JButton themTGDButton;
    private javax.swing.JButton thoatTGDButton;
    private com.toedter.calendar.JDateChooser thoiGianTGDTextField;
    // End of variables declaration//GEN-END:variables
}
