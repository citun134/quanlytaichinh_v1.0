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
public class editSTKJFrame extends javax.swing.JFrame {

    /**
     * Creates new form editSTKJFrame
     */
    
    public HomeViewController homeViewController;
    public GiaoDichDao giaoDichDao;
    public LoginController loginController;
    public LoginModel loginModel;
    public LoginView loginView;
    public GiaoDichThuModel giaoDichThuModel;
    public SoTietKiemModel soTietKiemModel;
    public SimpleDateFormat simpleDateFormat;
    public HomeViewPro homeViewPro;
    
    public int loginId;
    
    public editSTKJFrame() {
    }
    
    public editSTKJFrame(HomeViewPro homeViewPro, int logId){
        initComponents();
        setLocationRelativeTo(null);
        
        this.homeViewPro = homeViewPro;
        
        loginId = logId;
        System.out.println("loggoedInAccount id themchi: " + loginId);
        
        homeViewController = new HomeViewController();
        soTietKiemModel = new SoTietKiemModel();
//        
//        ngayGuiTGDTextField.setDateFormatString("dd-MM-yyyy");
//        
//        Date date = new Date();
//        
//        ngayGuiTGDTextField.setDate(date);
        
        editGD(loginId);
        
        // Kiểm tra nếu giaoDichModel không null và giaoDichModel.getDate() không rỗng
        if (soTietKiemModel != null && soTietKiemModel.getNgayGui() != null && !soTietKiemModel.getNgayGui().isEmpty()) {
            try {
                // Chuyển đổi chuỗi ngày từ giaoDichModel.getDate() thành đối tượng Date
                SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = mysqlDateFormat.parse(soTietKiemModel.getNgayGui());

                // Tạo đối tượng SimpleDateFormat cho định dạng mới "dd-MM-yyyy"
                SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                // Set giá trị của thoiGianTGDTextField1 bằng giá trị đã chuyển đổi
                ngayGuiTGDTextField.setDate(parsedDate);

                // Cập nhật định dạng hiển thị của thoiGianTGDTextField1
                ngayGuiTGDTextField.setDateFormatString("dd-MM-yyyy");

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            // Nếu giaoDichModel hoặc giaoDichModel.getDate() là null hoặc rỗng, xử lý tương ứng (ví dụ: hiển thị ngày hiện tại)
            Date currentDate = new Date();
            ngayGuiTGDTextField.setDate(currentDate);
        }

    }
    
//    public void editGD(int accountId){
//        soTietKiemModel = homeViewController.getInforUserSTK(accountId);
//        
//        DecimalFormat df = new DecimalFormat("###,###,###,###,###"); // Định dạng số theo dấu phẩy
//        String formattedSoTienGui = df.format(soTietKiemModel.getSoTienGui());
//        String formattedKyHan = df.format(soTietKiemModel.getKyHan());
//        int laiSuatGui = (int) soTietKiemModel.getLaiSuatGui();
//
//        
//        ngayGuiTGDTextField.setDateFormatString(soTietKiemModel.getNgayGui());
//        tenNganHangTGDTextField.setText(soTietKiemModel.getTenNganHang());
//        soTienGuiTGDTextField.setText(formattedSoTienGui);
//        laiSuatGuiTGDSlider.setValue(laiSuatGui);
//        kyHanTGDTextField.setText(formattedKyHan);
//        
////        homeViewController.updateGiaoDichThu(giaoDichThuModel);
//    };
    
    public void editGD(int accountId) {
        soTietKiemModel = homeViewController.getInforUserSTK(accountId);

        if (soTietKiemModel != null) {
            DecimalFormat df = new DecimalFormat("###,###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedSoTienGui = df.format(soTietKiemModel.getSoTienGui());
            String formattedKyHan = df.format(soTietKiemModel.getKyHan());
            int laiSuatGui = (int) soTietKiemModel.getLaiSuatGui();

            ngayGuiTGDTextField.setDateFormatString(soTietKiemModel.getNgayGui());
            tenNganHangTGDTextField.setText(soTietKiemModel.getTenNganHang());

            // Xóa dấu phẩy trước khi đặt giá trị vào JTextField
            soTienGuiTGDTextField.setText(formattedSoTienGui.replaceAll(",", ""));

            laiSuatGuiTGDSlider.setValue(laiSuatGui);
            kyHanTGDTextField.setSelectedItem(formattedKyHan);
        } else {
            // Xử lý trường hợp không tìm thấy soTietKiemModel
            System.out.println("Không tìm thấy thông tin sổ tiết kiệm cho accountId: " + accountId);
        }
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
        String kyHanTGD = (String) kyHanTGDTextField.getSelectedItem();
        
        double soTienLai = tinhSoTienLaiNhanDuoc(Double.parseDouble(soTienGuiTGD), laiSuatTGD, kyHanTGD);
        double tongTien = tinhTongTienNhanDuoc(Double.parseDouble(soTienGuiTGD), laiSuatTGD, kyHanTGD);

        
        try {
            if (!ngayTGD.isEmpty()) {
                DecimalFormat decimalFormat = new DecimalFormat();
                decimalFormat.setParseBigDecimal(true);
                BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(soTienGuiTGD);
                
                double kyHan = Double.parseDouble(kyHanTGD);
                
                soTietKiemModel.setNgayGui(ngayTGD);
                soTietKiemModel.setTenNganHang(tenNganHangTGD);
                soTietKiemModel.setSoTienGui(bigDecimal.doubleValue());
                soTietKiemModel.setLaiSuatGui(laiSuatTGD);
                soTietKiemModel.setKyHan(kyHan);
                soTietKiemModel.setSoTienLaiNhanDuoc(soTienLai);
                soTietKiemModel.setTongTienNhanDuoc(tongTien);
                    
                soTietKiemModel.setAccountId(accountId);

                homeViewController.updateSoTietKiem(soTietKiemModel);

                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                
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

        headerThemGiaoDichPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        bodyThemGiaoDichPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        soTienGuiTGDTextField = new javax.swing.JTextField();
        themTGDButton = new javax.swing.JButton();
        thoatTGDButton = new javax.swing.JButton();
        laiSuatGuiTGDSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tenNganHangTGDTextField = new javax.swing.JTextField();
        ngayGuiTGDTextField = new com.toedter.calendar.JDateChooser();
        kyHanTGDTextField = new javax.swing.JComboBox<>();

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
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(171, Short.MAX_VALUE))
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
        themTGDButton.setText("SỬA");
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

        kyHanTGDTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "15", "18", "24", "36" }));

        javax.swing.GroupLayout bodyThemGiaoDichPanelLayout = new javax.swing.GroupLayout(bodyThemGiaoDichPanel);
        bodyThemGiaoDichPanel.setLayout(bodyThemGiaoDichPanelLayout);
        bodyThemGiaoDichPanelLayout.setHorizontalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(18, 18, 18)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(laiSuatGuiTGDSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                            .addComponent(kyHanTGDTextField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(34, 34, 34)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tenNganHangTGDTextField)
                            .addComponent(soTienGuiTGDTextField)
                            .addComponent(ngayGuiTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(themTGDButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    private void soTienGuiTGDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soTienGuiTGDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soTienGuiTGDTextFieldActionPerformed

    private void themTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themTGDButtonActionPerformed
//        System.out.println("loggoedInAccount id themSTKbutton: " + loginId);
        themGD(loginId);
        homeViewPro.refreshTableSTKData();
    }//GEN-LAST:event_themTGDButtonActionPerformed

    private void thoatTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatTGDButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_thoatTGDButtonActionPerformed

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
            java.util.logging.Logger.getLogger(editSTKJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editSTKJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editSTKJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editSTKJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editSTKJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyThemGiaoDichPanel;
    private javax.swing.JPanel headerThemGiaoDichPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> kyHanTGDTextField;
    private javax.swing.JSlider laiSuatGuiTGDSlider;
    private com.toedter.calendar.JDateChooser ngayGuiTGDTextField;
    private javax.swing.JTextField soTienGuiTGDTextField;
    private javax.swing.JTextField tenNganHangTGDTextField;
    private javax.swing.JButton themTGDButton;
    private javax.swing.JButton thoatTGDButton;
    // End of variables declaration//GEN-END:variables
}
