/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quanlytaichinh.view;

/**
 *
 * @author x1 gen6
 */

import com.quanlytaichinh.controller.HomeViewController;
import com.quanlytaichinh.controller.LoginController;
import com.quanlytaichinh.dao.GiaoDichDao;
import com.quanlytaichinh.model.GiaoDichThuModel;
import com.quanlytaichinh.model.LoginModel;
import com.quanlytaichinh.model.SoTietKiemModel;
import com.quanlytaichinh.model.LaiSuatVayModel;
import static com.quanlytaichinh.view.editLSVJFrame.tinhSoTienPhaiTraHangThang;
import static com.quanlytaichinh.view.editLSVJFrame.tinhTongSoTienPhaiTra;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class themLSVJFrame extends javax.swing.JFrame {

    /**
     * Creates new form themLSVJFrame
     */
    
    public HomeViewController homeViewController;
    public HomeViewPro homeViewPro;
    public GiaoDichDao giaoDichDao;
    public LoginController loginController;
    public SoTietKiemModel soTietKiemModel;
    public LaiSuatVayModel laiSuatVayModel;
    public LoginModel loginModel;
    public LoginView loginView;
    public GiaoDichThuModel giaoDichThuModel;
    public SimpleDateFormat simpleDateFormat;
    
    private HomeViewPro destinationFrame;
    private DefaultTableModel defaultTableLSVModel;
    
    public int loginId;
    
    public themLSVJFrame() {
        initComponents();
    }
    
    public themLSVJFrame(HomeViewPro homeViewPro, int logId){
        initComponents();
        setLocationRelativeTo(null);
        this.homeViewPro = homeViewPro;
        
        loginId = logId;
        
        System.out.println("loggoedInAccount id themThu: " + loginId);
        
        homeViewController = new HomeViewController();
        laiSuatVayModel = new LaiSuatVayModel();
        
        ngayGiaiNganLSVjDateChooser.setDateFormatString("dd-MM-yyyy");
        
        Date date = new Date();
        
        ngayGiaiNganLSVjDateChooser.setDate(date);
        
    }
    
    public void displayLSV(int accountId) {
       
    // Create a DefaultTableModel for the second table (tenTKTable)
    defaultTableLSVModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
        destinationFrame.laiSuatVayTable.setModel(defaultTableLSVModel);

    
        defaultTableLSVModel.addColumn("ID");
        defaultTableLSVModel.addColumn("Tên Ngân Hàng");
        defaultTableLSVModel.addColumn("Giá Trị BĐS");
        defaultTableLSVModel.addColumn("Số Tiền Vay");
        defaultTableLSVModel.addColumn("Thời Gian Vay");
        defaultTableLSVModel.addColumn("Lãi Suất");
        defaultTableLSVModel.addColumn("Ngày Giải Ngân");
        defaultTableLSVModel.addColumn("Số Tiền Trả Mỗi Tháng");
        defaultTableLSVModel.addColumn("Tổng Số Tiền ");


        // Get the data from your controller or data source
        List<LaiSuatVayModel> allGiaoDich = homeViewController.getAllInforUserLSV(accountId);

        // Populate both tables with the data
        for (LaiSuatVayModel giaoDich : allGiaoDich) {
            DecimalFormat df = new DecimalFormat("###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedGiaTriBDS = df.format(giaoDich.getGiaTriBatDongSan());
            String formattedSoTienVay = df.format(giaoDich.getSoTienVay());
            String formattedSoTienTraHangThang = df.format(giaoDich.getSoTienPhaiTraHangThang());
            String formattedTongLaiPhaiTra = df.format(giaoDich.getTongLaiPhaiTra());
            
            defaultTableLSVModel.addRow(new Object[]{giaoDich.getLaiSuatVayId(), giaoDich.getTenNganHangLSV(),
                    formattedGiaTriBDS, formattedSoTienVay, giaoDich.getThoiGianVay(),
                    giaoDich.getLaiSuat(), giaoDich.getNgayGiaiNgan(),  
                    formattedSoTienTraHangThang, formattedTongLaiPhaiTra
            });
        }
    }
    
    public void setLSVTable(List<LaiSuatVayModel> allGiaoDich){
        for (LaiSuatVayModel giaoDich : allGiaoDich) {
            DecimalFormat df = new DecimalFormat("###,###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedGiaTriBDS = df.format(giaoDich.getGiaTriBatDongSan());
            String formattedSoTienVay = df.format(giaoDich.getSoTienVay());
            String formattedSoTienTraHangThang = df.format(giaoDich.getSoTienPhaiTraHangThang());
            String formattedTongLaiPhaiTra = df.format(giaoDich.getTongLaiPhaiTra());
            
            defaultTableLSVModel.addRow(new Object[]{giaoDich.getLaiSuatVayId(), giaoDich.getTenNganHangLSV(),
                    formattedGiaTriBDS, formattedSoTienVay, giaoDich.getThoiGianVay(),
                    giaoDich.getLaiSuat(), giaoDich.getNgayGiaiNgan(),  
                    formattedSoTienTraHangThang, formattedTongLaiPhaiTra
            });
        }
    }
    
    public static double tinhSoTienPhaiTraHangThang(double soTienVay, double laiSuat, double thoiGianVay) {
        int soThangVay = (int) thoiGianVay;

        // Lãi suất hàng tháng
        double laiSuatHangThang = (laiSuat / 100) / 12;

        // Số tiền hàng tháng cố định
//        double soTienHangThang = (soTienVay * laiSuatHangThang) / (1 - Math.pow(1 + laiSuatHangThang, -soThangVay));
        // Số tiền hàng tháng cố định - lãi giảm dần
        double soTienHangThang = soTienVay / soThangVay + soTienVay * laiSuatHangThang;

        return soTienHangThang;
    }
    
    public static double tinhTongSoTienPhaiTra(double soTienVay, double laiSuat, double thoiGianVay) {
        int soThangVay = (int) thoiGianVay;

        // Tổng số tiền phải trả
        double laiSuatHangThang = (laiSuat / 100) / 12;

        // Số tiền lãi phải trả hàng tháng
        double tienLaiHangThang = soTienVay * laiSuatHangThang;

        // Số tiền phải trả hàng tháng
        double tienHangThang = soTienVay / soThangVay + tienLaiHangThang;

        // Tổng số tiền phải trả
        double tongSoTienTra = tienHangThang * soThangVay;

        return tongSoTienTra;
    }
    
    public void themGD(int accountId) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTGD = ngayGiaiNganLSVjDateChooser.getDate();

        String tenNganHangTGD = tenNganHangLSVTGDTextField.getText();
        String giaTriBDSTGD = giaTriBDSLSVTGDTextField.getText();
        String soTienVayTGD = soTienVayLSVTGDTextField.getText();
        String thoiGianVayTGD = (String) thoiGianVayLSVTGDTextField.getSelectedItem();
        double laiSuatTGD = laiSuatLSVTGDSlider.getValue();
        String ngayTGD = simpleDateFormat.format(dateTGD);

        try {
            if (!ngayTGD.isEmpty() && !giaTriBDSTGD.isEmpty() && !soTienVayTGD.isEmpty() && !thoiGianVayTGD.isEmpty()) {
                DecimalFormat decimalFormat = new DecimalFormat();
                decimalFormat.setParseBigDecimal(true);

                // Check if the strings are not empty before parsing
                if (!giaTriBDSTGD.trim().isEmpty() && !soTienVayTGD.trim().isEmpty() && !thoiGianVayTGD.trim().isEmpty()) {
                    BigDecimal BDSBigDecimal = (BigDecimal) decimalFormat.parse(giaTriBDSTGD);
                    BigDecimal STVBigDecimal = (BigDecimal) decimalFormat.parse(soTienVayTGD);
                    BigDecimal ThoiGianVayBigDecimal = (BigDecimal) decimalFormat.parse(thoiGianVayTGD);

                    // Add the condition to check if loan amount is less than or equal to property value
                    if (STVBigDecimal.doubleValue() <= BDSBigDecimal.doubleValue()) {
                        double tienHangThang = tinhSoTienPhaiTraHangThang(Double.parseDouble(soTienVayTGD),
                laiSuatTGD, Double.parseDouble(thoiGianVayTGD));
        
        double tinhTong = tinhTongSoTienPhaiTra(Double.parseDouble(soTienVayTGD), 
                laiSuatTGD, Double.parseDouble(thoiGianVayTGD));
                        laiSuatVayModel.setTenNganHangLSV(tenNganHangTGD);
                        laiSuatVayModel.setGiaTriBatDongSan(BDSBigDecimal.doubleValue());
                        laiSuatVayModel.setSoTienVay(STVBigDecimal.doubleValue());
                        laiSuatVayModel.setThoiGianVay(ThoiGianVayBigDecimal.doubleValue());
                        laiSuatVayModel.setLaiSuat(laiSuatTGD);
                        laiSuatVayModel.setNgayGiaiNgan(ngayTGD);
                        laiSuatVayModel.setSoTienPhaiTraHangThang(tienHangThang);
                        laiSuatVayModel.setTongLaiPhaiTra(tinhTong);

                        laiSuatVayModel.setAccountId(accountId);

                        homeViewController.addGiaoDichLSV(laiSuatVayModel);

                        JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Số tiền vay phải nhỏ hơn hoặc bằng giá trị BĐS!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm Thất Bại! Vui lòng nhập đầy đủ thông tin.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Thất Bại! Vui lòng nhập đầy đủ thông tin.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tenNganHangLSVTGDTextField.setText("");
        giaTriBDSLSVTGDTextField.setText("");
        soTienVayLSVTGDTextField.setText("");
//        thoiGianVayLSVTGDTextField.set("");  // Set thoiGianVayTGDTextField to an empty string
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
        themTGDButton = new javax.swing.JButton();
        thoatTGDButton = new javax.swing.JButton();
        laiSuatLSVTGDSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        tenNganHangLSVTGDTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        giaTriBDSLSVTGDTextField = new javax.swing.JTextField();
        soTienVayLSVTGDTextField = new javax.swing.JTextField();
        ngayGiaiNganLSVjDateChooser = new com.toedter.calendar.JDateChooser();
        thoiGianVayLSVTGDTextField = new javax.swing.JComboBox<>();

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
        jLabel8.setText("Thời gian vay");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Lãi suất (%/năm)");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Ngày giải ngân");

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

        laiSuatLSVTGDSlider.setBackground(new java.awt.Color(255, 255, 255));
        laiSuatLSVTGDSlider.setMajorTickSpacing(2);
        laiSuatLSVTGDSlider.setMaximum(10);
        laiSuatLSVTGDSlider.setMinorTickSpacing(1);
        laiSuatLSVTGDSlider.setPaintLabels(true);
        laiSuatLSVTGDSlider.setPaintTicks(true);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Tên ngân hàng");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Giá trị bất động sản");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Số tiền vay");

        thoiGianVayLSVTGDTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "15", "24", "36" }));

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
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(19, 19, 19)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(giaTriBDSLSVTGDTextField)
                            .addComponent(tenNganHangLSVTGDTextField)))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(themTGDButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(34, 34, 34)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(soTienVayLSVTGDTextField)
                            .addComponent(thoiGianVayLSVTGDTextField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(laiSuatLSVTGDSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                            .addComponent(ngayGiaiNganLSVjDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        bodyThemGiaoDichPanelLayout.setVerticalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenNganHangLSVTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(giaTriBDSLSVTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soTienVayLSVTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thoiGianVayLSVTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(laiSuatLSVTGDSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayGiaiNganLSVjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(themTGDButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(thoatTGDButton)
                .addContainerGap())
        );

        getContentPane().add(bodyThemGiaoDichPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themTGDButtonActionPerformed
//        System.out.println("loggoedInAccount id themSTKbutton: " + loginId);
        themGD(loginId);
        homeViewPro.refreshTableLSVData();
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
            java.util.logging.Logger.getLogger(themLSVJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themLSVJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themLSVJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themLSVJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themLSVJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyThemGiaoDichPanel;
    private javax.swing.JTextField giaTriBDSLSVTGDTextField;
    private javax.swing.JPanel headerThemGiaoDichPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSlider laiSuatLSVTGDSlider;
    private com.toedter.calendar.JDateChooser ngayGiaiNganLSVjDateChooser;
    private javax.swing.JTextField soTienVayLSVTGDTextField;
    private javax.swing.JTextField tenNganHangLSVTGDTextField;
    private javax.swing.JButton themTGDButton;
    private javax.swing.JButton thoatTGDButton;
    private javax.swing.JComboBox<String> thoiGianVayLSVTGDTextField;
    // End of variables declaration//GEN-END:variables
}
