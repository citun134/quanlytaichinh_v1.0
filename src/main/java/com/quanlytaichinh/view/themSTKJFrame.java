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
import com.quanlytaichinh.model.SoTietKiemModel;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author x1 gen6
 */
public class themSTKJFrame extends javax.swing.JFrame {

    /**
     * Creates new form themSTKJFrame
     */
    public HomeViewController homeViewController;
    public HomeViewPro homeViewPro;
    public GiaoDichDao giaoDichDao;
    public LoginController loginController;
    public SoTietKiemModel soTietKiemModel;
    public LoginModel loginModel;
    public LoginView loginView;
    public GiaoDichThuModel giaoDichThuModel;
    public SimpleDateFormat simpleDateFormat;
    
    public int loginId;
    
    public themSTKJFrame() {
        initComponents();
    }
    
    public themSTKJFrame(HomeViewPro homeViewPro, int logId) {
        initComponents();
        setLocationRelativeTo(null);
        
        this.homeViewPro = homeViewPro;
        loginId = logId;
        
        System.out.println("loggoedInAccount id themThu: " + loginId);
        
        homeViewController = new HomeViewController();
        soTietKiemModel = new SoTietKiemModel();
        
        ngayGuiTGDTextField.setDateFormatString("dd-MM-yyyy");
        
        Date date = new Date();
        
        ngayGuiTGDTextField.setDate(date);
    }
    
    public double tinhSoTienLaiNhanDuoc(double soTienGui, double laiSuat, String kyHan) {
    // Convert kyHan từ String sang double
        double kyHanDouble = Double.parseDouble(kyHan);

        // Tính số tiền lãi nhận được
        double soTienLaiNhanDuoc = (laiSuat / 100) * soTienGui * (kyHanDouble / 12);

        return soTienLaiNhanDuoc;
    }
    
    public double tinhTongTienNhanDuoc(double soTienGui, double laiSuat, String kyHan) {
    // Gọi hàm tinhSoTienLaiNhanDuoc để tính số tiền lãi nhận được
        double soTienLaiNhanDuoc = tinhSoTienLaiNhanDuoc(soTienGui, laiSuat, kyHan);

        // Tính tổng số tiền nhận được
        double tongTienNhanDuoc = soTienGui + soTienLaiNhanDuoc;

        return tongTienNhanDuoc;
    }

    
public void themGD(int accountId) {
    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateTGD = ngayGuiTGDTextField.getDate();

    String ngayTGD = simpleDateFormat.format(dateTGD);
    String tenNganHangTGD = tenNganHangTGDTextField.getText();
    String soTienGuiTGD = soTienGuiTGDTextField.getText();
    double laiSuatTGD = laiSuatGuiTGDSlider.getValue();
    String kyHanTGD = kyHanTGDTextField.getText();

    try {
        if (!ngayTGD.isEmpty() && !soTienGuiTGD.isEmpty() && !kyHanTGD.isEmpty()) {
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setParseBigDecimal(true);
            
            // Check if kyHanTGDTextField is not empty before parsing
            if (!kyHanTGD.trim().isEmpty()) {
                BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(soTienGuiTGD);

                double kyHan = Double.parseDouble(kyHanTGD);

                double soTienLai = tinhSoTienLaiNhanDuoc(bigDecimal.doubleValue(), laiSuatTGD, kyHanTGD);
                double tongTien = tinhTongTienNhanDuoc(bigDecimal.doubleValue(), laiSuatTGD, kyHanTGD);

                soTietKiemModel.setNgayGui(ngayTGD);
                soTietKiemModel.setTenNganHang(tenNganHangTGD);
                soTietKiemModel.setSoTienGui(bigDecimal.doubleValue());
                soTietKiemModel.setLaiSuatGui(laiSuatTGD);
                soTietKiemModel.setKyHan(kyHan);
                soTietKiemModel.setSoTienLaiNhanDuoc(soTienLai);
                soTietKiemModel.setTongTienNhanDuoc(tongTien);

                soTietKiemModel.setAccountId(accountId);

                homeViewController.addGiaoDichSTK(soTietKiemModel);

                JOptionPane.showMessageDialog(this, "Thêm thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Kỳ hạn không được để trống!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại! Vui lòng nhập đầy đủ thông tin.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, e.getMessage());
    }

    // Clear text fields after adding a new record
    tenNganHangTGDTextField.setText("");
    soTienGuiTGDTextField.setText("");
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerThemGiaoDichPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        bodyThemGiaoDichPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        soTienGuiTGDTextField = new javax.swing.JTextField();
        kyHanTGDTextField = new javax.swing.JTextField();
        themTGDButton = new javax.swing.JButton();
        thoatTGDButton = new javax.swing.JButton();
        laiSuatGuiTGDSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tenNganHangTGDTextField = new javax.swing.JTextField();
        ngayGuiTGDTextField = new com.toedter.calendar.JDateChooser();

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
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(155, Short.MAX_VALUE))
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
        jLabel8.setText("Số tiền gửi");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Lãi suất (%/năm)");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Kỳ hạn");

        soTienGuiTGDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soTienGuiTGDTextFieldActionPerformed(evt);
            }
        });

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

        laiSuatGuiTGDSlider.setBackground(new java.awt.Color(255, 255, 255));
        laiSuatGuiTGDSlider.setMajorTickSpacing(2);
        laiSuatGuiTGDSlider.setMaximum(10);
        laiSuatGuiTGDSlider.setMinorTickSpacing(1);
        laiSuatGuiTGDSlider.setPaintLabels(true);
        laiSuatGuiTGDSlider.setPaintTicks(true);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Ngày gửi");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Tên ngân hàng");

        javax.swing.GroupLayout bodyThemGiaoDichPanelLayout = new javax.swing.GroupLayout(bodyThemGiaoDichPanel);
        bodyThemGiaoDichPanel.setLayout(bodyThemGiaoDichPanelLayout);
        bodyThemGiaoDichPanelLayout.setHorizontalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(themTGDButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(thoatTGDButton))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(kyHanTGDTextField))
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(laiSuatGuiTGDSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(34, 34, 34)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tenNganHangTGDTextField)
                            .addComponent(soTienGuiTGDTextField)
                            .addComponent(ngayGuiTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        bodyThemGiaoDichPanelLayout.setVerticalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayGuiTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tenNganHangTGDTextField)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soTienGuiTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(laiSuatGuiTGDSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kyHanTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(themTGDButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(thoatTGDButton)
                .addContainerGap())
        );

        getContentPane().add(bodyThemGiaoDichPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themTGDButtonActionPerformed
        System.out.println("loggoedInAccount id themSTKbutton: " + loginId);
        themGD(loginId);
        homeViewPro.refreshTableSTKData();
    }//GEN-LAST:event_themTGDButtonActionPerformed

    private void thoatTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatTGDButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_thoatTGDButtonActionPerformed

    private void soTienGuiTGDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soTienGuiTGDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soTienGuiTGDTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(themSTKJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themSTKJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themSTKJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themSTKJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themSTKJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyThemGiaoDichPanel;
    private javax.swing.JPanel headerThemGiaoDichPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField kyHanTGDTextField;
    private javax.swing.JSlider laiSuatGuiTGDSlider;
    private com.toedter.calendar.JDateChooser ngayGuiTGDTextField;
    private javax.swing.JTextField soTienGuiTGDTextField;
    private javax.swing.JTextField tenNganHangTGDTextField;
    private javax.swing.JButton themTGDButton;
    private javax.swing.JButton thoatTGDButton;
    // End of variables declaration//GEN-END:variables
}
