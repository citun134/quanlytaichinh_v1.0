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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author x1 gen6
 */
public class editThuJFrame extends javax.swing.JFrame {

    /**
     * Creates new form editThuJFrame
     */
    public HomeViewController homeViewController;
    public GiaoDichDao giaoDichDao;
    public LoginController loginController;
    public LoginModel loginModel;
    public LoginView loginView;
    public GiaoDichThuModel giaoDichThuModel;
    public HomeViewPro homeViewPro;
    public SimpleDateFormat simpleDateFormat;
    public int loginId;
    
    public editThuJFrame() {}
    
    public editThuJFrame(HomeViewPro homeViewPro, int logId){
        initComponents();
        setLocationRelativeTo(null);
        
        this.homeViewPro = homeViewPro;
        
        loginId = logId;
        System.out.println("loggoedInAccount id themthu: " + loginId);
        
        homeViewController = new HomeViewController();
        giaoDichThuModel = new GiaoDichThuModel();
        
        editGD(loginId);
        
//        thoiGianThuTGDTextField.setDateFormatString("yyyy-MM-dd");
//        
//        Date date = new Date();
//        
//        thoiGianThuTGDTextField.setDate(date);
        
// Kiểm tra nếu giaoDichModel không null và giaoDichModel.getDate() không rỗng
    if (giaoDichThuModel != null && giaoDichThuModel.getDate() != null && !giaoDichThuModel.getDate().isEmpty()) {
        try {
            // Chuyển đổi chuỗi ngày từ giaoDichModel.getDate() thành đối tượng Date
            SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = mysqlDateFormat.parse(giaoDichThuModel.getDate());

            // Tạo đối tượng SimpleDateFormat cho định dạng mới "dd-MM-yyyy"
            SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd-MM-yyyy");

            // Set giá trị của thoiGianTGDTextField1 bằng giá trị đã chuyển đổi
            thoiGianThuTGDTextField.setDate(parsedDate);

            // Cập nhật định dạng hiển thị của thoiGianTGDTextField1
            thoiGianThuTGDTextField.setDateFormatString("dd-MM-yyyy");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    } else {
        // Nếu giaoDichModel hoặc giaoDichModel.getDate() là null hoặc rỗng, xử lý tương ứng (ví dụ: hiển thị ngày hiện tại)
        Date currentDate = new Date();
        thoiGianThuTGDTextField.setDate(currentDate);
    }




    }
    
    public void editGD(int accountId){
        //DecimalFormat df = new DecimalFormat("###,###,###,###"); // Định dạng số theo dấu phẩy
        //String formattedThanhTien = df.format(giaoDichThuModel.getThanhTien());
            
        giaoDichThuModel = homeViewController.getInforUserThu(accountId);
        
//        SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        DecimalFormat df = new DecimalFormat("###,###,###,###,###");
        String formattedThanhTien = df.format(giaoDichThuModel.getThanhTien());
        
//        try{
            
//            Date date = mysqlDateFormat.parse(giaoDichThuModel.getDate());
//            String formattedDate = desiredDateFormat.format(date);
        
        thoiGianThuTGDTextField.setDateFormatString(giaoDichThuModel.getDate());
//        thoiGianThuTGDTextField.setDateFormatString(formattedDate);
//        thanhTienThuTGDTextField.setText(String.valueOf(giaoDichThuModel.getThanhTien()));
        thanhTienThuTGDTextField.setText(formattedThanhTien.replaceAll(",", ""));
        ghiChuThuTGDTextField.setText(giaoDichThuModel.getGhiChu());
        String hangMuc = giaoDichThuModel.getHangMuc(); // Retrieve the value from the model

        // Check the value and set the corresponding radio button to be selected
        if ("Lương".equals(hangMuc)) {
            luongRadioButton.setSelected(true);
        } else if ("Thưởng".equals(hangMuc)) {
            thuongRadioButton.setSelected(true);
        } else if ("Được cho/tặng".equals(hangMuc)) {
            choTangRadioButton.setSelected(true);
        } else if ("Khác".equals(hangMuc)) {
            khacThuRadioButton.setSelected(true);
        }
//        } catch(ParseException e) {
//            e.printStackTrace();
//        }
//        homeViewController.updateGiaoDichThu(giaoDichThuModel);
    };
    
    public void themGD(int accountId){
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayTGD = thoiGianThuTGDTextField.getDate();
//        String dateTGD = thoiGianThuTGDTextField.getText();
        String thanhTienTGD = thanhTienThuTGDTextField.getText();
        String ghiChuTGD = ghiChuThuTGDTextField.getText();
        String dateTGD = simpleDateFormat.format(ngayTGD);
        
        try {
            if (!thanhTienTGD.isEmpty() && !thanhTienTGD.isEmpty()) {
                if (!thanhTienTGD.matches(".*[a-zA-Z].*")) {
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setParseBigDecimal(true);
                    BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(thanhTienTGD);

                    // Set properties of the giaoDichModel object
                    giaoDichThuModel.setDate(dateTGD);
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
                    giaoDichThuModel.setId(accountId);

                    homeViewController.updateGiaoDichThu(giaoDichThuModel);

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
        bodyThemGiaoDichPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        thanhTienThuTGDTextField = new javax.swing.JTextField();
        ghiChuThuTGDTextField = new javax.swing.JTextField();
        suaThuTGDButton = new javax.swing.JButton();
        thoatThuTGDButton = new javax.swing.JButton();
        hangMucButton = new javax.swing.JLabel();
        thuongRadioButton = new javax.swing.JRadioButton();
        luongRadioButton = new javax.swing.JRadioButton();
        choTangRadioButton = new javax.swing.JRadioButton();
        khacThuRadioButton = new javax.swing.JRadioButton();
        thoiGianThuTGDTextField = new com.toedter.calendar.JDateChooser();

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

        bodyThemGiaoDichPanel.setBackground(new java.awt.Color(255, 255, 255));
        bodyThemGiaoDichPanel.setPreferredSize(new java.awt.Dimension(490, 400));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Thời Gian");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Thành Tiền");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Ghi Chú");

        suaThuTGDButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        suaThuTGDButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Accept.png"))); // NOI18N
        suaThuTGDButton.setText("SỬA");
        suaThuTGDButton.setToolTipText("");
        suaThuTGDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaThuTGDButtonActionPerformed(evt);
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
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(hangMucButton))
                        .addGap(34, 34, 34)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(thanhTienThuTGDTextField)
                            .addComponent(ghiChuThuTGDTextField)
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addComponent(luongRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(thuongRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(choTangRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(khacThuRadioButton)
                                .addGap(0, 149, Short.MAX_VALUE))
                            .addComponent(thoiGianThuTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(thoatThuTGDButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(suaThuTGDButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        bodyThemGiaoDichPanelLayout.setVerticalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(thoiGianThuTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(thanhTienThuTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ghiChuThuTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hangMucButton)
                    .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(luongRadioButton)
                        .addComponent(thuongRadioButton)
                        .addComponent(choTangRadioButton)
                        .addComponent(khacThuRadioButton)))
                .addGap(18, 18, 18)
                .addComponent(suaThuTGDButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(thoatThuTGDButton)
                .addContainerGap())
        );

        getContentPane().add(bodyThemGiaoDichPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void suaThuTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaThuTGDButtonActionPerformed
        System.out.println("loggoedInAccount id thembutton: " + loginId);
        themGD(loginId);
        homeViewPro.refreshTableThuData();
        homeViewPro.tongThu();
    }//GEN-LAST:event_suaThuTGDButtonActionPerformed

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
            java.util.logging.Logger.getLogger(editThuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editThuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editThuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editThuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editThuJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyThemGiaoDichPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton choTangRadioButton;
    private javax.swing.JTextField ghiChuThuTGDTextField;
    private javax.swing.JLabel hangMucButton;
    private javax.swing.JPanel headerThemGiaoDichPanel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton khacThuRadioButton;
    private javax.swing.JRadioButton luongRadioButton;
    private javax.swing.JButton suaThuTGDButton;
    private javax.swing.JTextField thanhTienThuTGDTextField;
    private javax.swing.JButton thoatThuTGDButton;
    private com.toedter.calendar.JDateChooser thoiGianThuTGDTextField;
    private javax.swing.JRadioButton thuongRadioButton;
    // End of variables declaration//GEN-END:variables
}
