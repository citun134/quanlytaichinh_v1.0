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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author x1 gen6
 */
public class editChiJFrame extends javax.swing.JFrame {

    /**
     * Creates new form editChiJFrame
     */
    
    public HomeViewController homeViewController;
    public GiaoDichDao giaoDichDao;
    public LoginController loginController;
    public LoginModel loginModel;
    public LoginView loginView;
    public GiaoDichModel giaoDichModel;
    public HomeViewPro homeViewPro;
    public SimpleDateFormat simpleDateFormat;
    
    public int loginId;
    
    public editChiJFrame() {}

    public editChiJFrame(HomeViewPro homeViewPro, int loginModel){
        initComponents();
        setLocationRelativeTo(null);
        
        this.homeViewPro = homeViewPro;
        
        loginId = loginModel;
        System.out.println("loggoedInAccount id themchi: " + loginId);
        
        homeViewController = new HomeViewController();
        giaoDichModel = new GiaoDichModel();
        
        editGD(loginId);

    // Kiểm tra nếu giaoDichModel không null và giaoDichModel.getDate() không rỗng
    if (giaoDichModel != null && giaoDichModel.getDate() != null && !giaoDichModel.getDate().isEmpty()) {
        try {
            // Chuyển đổi chuỗi ngày từ giaoDichModel.getDate() thành đối tượng Date
            SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = mysqlDateFormat.parse(giaoDichModel.getDate());

            // Tạo đối tượng SimpleDateFormat cho định dạng mới "dd-MM-yyyy"
            SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd-MM-yyyy");

            // Set giá trị của thoiGianTGDTextField1 bằng giá trị đã chuyển đổi
            thoiGianTGDTextField1.setDate(parsedDate);

            // Cập nhật định dạng hiển thị của thoiGianTGDTextField1
            thoiGianTGDTextField1.setDateFormatString("dd-MM-yyyy");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        } else {
            // Nếu giaoDichModel hoặc giaoDichModel.getDate() là null hoặc rỗng, xử lý tương ứng (ví dụ: hiển thị ngày hiện tại)
            Date currentDate = new Date();
            thoiGianTGDTextField1.setDate(currentDate);
        }


        
    }
    
    public void editGD(int accountId){
        giaoDichModel = homeViewController.getInforUser(accountId);
        
        DecimalFormat df = new DecimalFormat("###,###,###,###,###");
        
        String formattedThanhTien = df.format(giaoDichModel.getThanhTien());
        thoiGianTGDTextField1.setDateFormatString(giaoDichModel.getDate());
        matHangTGDTextField1.setText(giaoDichModel.getMatHang());
//        thanhTienTGDTextField1.setText(String.valueOf(giaoDichModel.getThanhTien()));
        thanhTienTGDTextField1.setText(formattedThanhTien.replaceAll(",", ""));
        ghiChuTGDTextField1.setText(giaoDichModel.getGhiChu());
        
        String hangMuc = giaoDichModel.getHangMuc(); // Retrieve the value from the model

        // Check the value and set the corresponding radio button to be selected
        if ("Ăn Uống".equals(hangMuc)) {
            anUongRadioButton1.setSelected(true);
        } else if ("Quần Áo".equals(hangMuc)) {
            quanAoRadioButton1.setSelected(true);
        } else if ("Dịch Vụ Sinh Hoạt".equals(hangMuc)) {
            dvSinhHoatRadioButton1.setSelected(true);
        } else if ("Tiền chi khác".equals(hangMuc)) {
            khacRadioButton1.setSelected(true);
        }
//        homeViewController.updateGiaoDichChi(giaoDichModel);
    };
    
    public void themGD(int accountId){
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String dateTGD = thoiGianTGDTextField1.getText();
        Date ngayTGD = thoiGianTGDTextField1.getDate();
        String matHangTGD = matHangTGDTextField1.getText();
        String thanhTienTGD = thanhTienTGDTextField1.getText();
        String ghiChuTGD = ghiChuTGDTextField1.getText();
        String dateTGD = simpleDateFormat.format(ngayTGD);
        
        try {
            if (!matHangTGD.isEmpty() && !thanhTienTGD.isEmpty()) {
                if (!thanhTienTGD.matches(".*[a-zA-Z].*")) {
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setParseBigDecimal(true);
                    BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(thanhTienTGD);

                    // Set properties of the giaoDichModel object
                    giaoDichModel.setDate(dateTGD);
                    giaoDichModel.setMatHang(matHangTGD);
                    giaoDichModel.setThanhTien(bigDecimal.doubleValue());
                    giaoDichModel.setGhiChu(ghiChuTGD);

                    if (anUongRadioButton1.isSelected()) {
                        giaoDichModel.setHangMuc("Ăn Uống");
                    } else if (quanAoRadioButton1.isSelected()) {
                        giaoDichModel.setHangMuc("Quần Áo");
                    } else if (dvSinhHoatRadioButton1.isSelected()) {
                        giaoDichModel.setHangMuc("Dịch Vụ Sinh Hoạt");
                    } else if (khacRadioButton1.isSelected()) {
                        giaoDichModel.setHangMuc("Khác");
                    }
                    giaoDichModel.setId(accountId);

                    homeViewController.updateGiaoDichChi(giaoDichModel);

                    JOptionPane.showMessageDialog(this, "Sửa thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Thất Bại! Thanh Tiền không hợp lệ.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sửa Thất Bại! Vui lòng nhập đầy đủ thông tin.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        headerThemGiaoDichPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        bodyThemGiaoDichPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        matHangTGDTextField1 = new javax.swing.JTextField();
        thanhTienTGDTextField1 = new javax.swing.JTextField();
        ghiChuTGDTextField1 = new javax.swing.JTextField();
        themTGDButton1 = new javax.swing.JButton();
        thoatTGDButton1 = new javax.swing.JButton();
        hangMucButton1 = new javax.swing.JLabel();
        quanAoRadioButton1 = new javax.swing.JRadioButton();
        anUongRadioButton1 = new javax.swing.JRadioButton();
        dvSinhHoatRadioButton1 = new javax.swing.JRadioButton();
        khacRadioButton1 = new javax.swing.JRadioButton();
        thoiGianTGDTextField1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerThemGiaoDichPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Notes.png"))); // NOI18N
        jLabel7.setText("CHỈNH SỬA GIAO DỊCH");

        javax.swing.GroupLayout headerThemGiaoDichPanel1Layout = new javax.swing.GroupLayout(headerThemGiaoDichPanel1);
        headerThemGiaoDichPanel1.setLayout(headerThemGiaoDichPanel1Layout);
        headerThemGiaoDichPanel1Layout.setHorizontalGroup(
            headerThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerThemGiaoDichPanel1Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        headerThemGiaoDichPanel1Layout.setVerticalGroup(
            headerThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerThemGiaoDichPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(headerThemGiaoDichPanel1, java.awt.BorderLayout.PAGE_START);

        bodyThemGiaoDichPanel1.setBackground(new java.awt.Color(255, 255, 255));
        bodyThemGiaoDichPanel1.setPreferredSize(new java.awt.Dimension(490, 400));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Thời Gian");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Mặt Hàng");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Thành Tiền");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("Ghi Chú");

        themTGDButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        themTGDButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Accept.png"))); // NOI18N
        themTGDButton1.setText("SỬA");
        themTGDButton1.setToolTipText("");
        themTGDButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themTGDButton1ActionPerformed(evt);
            }
        });

        thoatTGDButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        thoatTGDButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Delete.png"))); // NOI18N
        thoatTGDButton1.setText("THOÁT");
        thoatTGDButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoatTGDButton1ActionPerformed(evt);
            }
        });

        hangMucButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        hangMucButton1.setText("Danh Mục");

        buttonGroup1.add(quanAoRadioButton1);
        quanAoRadioButton1.setText("Quần áo");

        buttonGroup1.add(anUongRadioButton1);
        anUongRadioButton1.setText("Ăn uống");

        buttonGroup1.add(dvSinhHoatRadioButton1);
        dvSinhHoatRadioButton1.setText("Dịch vụ sinh hoạt");
        dvSinhHoatRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dvSinhHoatRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(khacRadioButton1);
        khacRadioButton1.setText("Tiền chi khác");

        javax.swing.GroupLayout bodyThemGiaoDichPanel1Layout = new javax.swing.GroupLayout(bodyThemGiaoDichPanel1);
        bodyThemGiaoDichPanel1.setLayout(bodyThemGiaoDichPanel1Layout);
        bodyThemGiaoDichPanel1Layout.setHorizontalGroup(
            bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyThemGiaoDichPanel1Layout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(hangMucButton1))
                        .addGap(34, 34, 34)
                        .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matHangTGDTextField1)
                            .addComponent(thanhTienTGDTextField1)
                            .addComponent(ghiChuTGDTextField1)
                            .addGroup(bodyThemGiaoDichPanel1Layout.createSequentialGroup()
                                .addComponent(anUongRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(quanAoRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(dvSinhHoatRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(khacRadioButton1)
                                .addGap(0, 80, Short.MAX_VALUE))
                            .addComponent(thoiGianTGDTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(themTGDButton1)
                        .addGap(134, 134, 134)
                        .addComponent(thoatTGDButton1)))
                .addContainerGap())
        );
        bodyThemGiaoDichPanel1Layout.setVerticalGroup(
            bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(thoiGianTGDTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(matHangTGDTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(thanhTienTGDTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ghiChuTGDTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hangMucButton1)
                    .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(anUongRadioButton1)
                        .addComponent(quanAoRadioButton1)
                        .addComponent(dvSinhHoatRadioButton1)
                        .addComponent(khacRadioButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(bodyThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themTGDButton1)
                    .addComponent(thoatTGDButton1))
                .addContainerGap())
        );

        getContentPane().add(bodyThemGiaoDichPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themTGDButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themTGDButton1ActionPerformed
        // TODO add your handling code here:
        themGD(loginId);
        homeViewPro.refreshTableChiData();
        homeViewPro.tongChi();
    }//GEN-LAST:event_themTGDButton1ActionPerformed

    private void thoatTGDButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatTGDButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_thoatTGDButton1ActionPerformed

    private void dvSinhHoatRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dvSinhHoatRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dvSinhHoatRadioButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(editChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editChiJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton anUongRadioButton1;
    private javax.swing.JPanel bodyThemGiaoDichPanel1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton dvSinhHoatRadioButton1;
    private javax.swing.JTextField ghiChuTGDTextField1;
    private javax.swing.JLabel hangMucButton1;
    private javax.swing.JPanel headerThemGiaoDichPanel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton khacRadioButton1;
    private javax.swing.JTextField matHangTGDTextField1;
    private javax.swing.JRadioButton quanAoRadioButton1;
    private javax.swing.JTextField thanhTienTGDTextField1;
    private javax.swing.JButton themTGDButton1;
    private javax.swing.JButton thoatTGDButton1;
    private com.toedter.calendar.JDateChooser thoiGianTGDTextField1;
    // End of variables declaration//GEN-END:variables
}
