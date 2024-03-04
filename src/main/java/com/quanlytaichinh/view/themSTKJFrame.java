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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    
    public Map<String, Double> interestRates;
    
    
    
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
        
        vbcThemSTKjLabel.setText("");
        
        interestRates = new HashMap<>();
        interestRates.put("1", 0.02);
        interestRates.put("2", 0.02);
        interestRates.put("3", 0.023);
        interestRates.put("4", 0.023);
        interestRates.put("5", 0.023);
        interestRates.put("6", 0.033);
        interestRates.put("7", 0.033);
        interestRates.put("8", 0.033);
        interestRates.put("9", 0.033);
        interestRates.put("10", 0.033);
        interestRates.put("11", 0.033);
        interestRates.put("12", 0.048);
        interestRates.put("13", 0.048);
        interestRates.put("15", 0.048);
        interestRates.put("18", 0.048);
        interestRates.put("24", 0.05);
        interestRates.put("36", 0.05);
        
       
        
        kyHanTGDTextField.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Xử lý sự kiện khi một mục được chọn
            // Get the selected item
            String kyHanTGD = kyHanTGDTextField.getSelectedItem().toString().trim();

            // Check if the interestRates map contains the key
            if (interestRates.containsKey(kyHanTGD)) {
                // Get the corresponding value from interestRates map
                double laiSuatTGD = interestRates.get(kyHanTGD);

                // Now you can use laiSuatTGD as needed
                updateInterestLabel();
            } else {
                // Handle the case where kyHanTGD is not found in the map
            }
        }
    });
        
        
        laiSuatSTKjLabel.setText("2.0%");
        
        soLaijLabel.setText("0");
            tongTienjLabel.setText("0");
//            vbcThemSTKjLabel.setText("");
        
        
        soTienGuiTGDTextField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateLabels();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateLabels();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateLabels();
        }

        private void updateLabels() {
            String soTienGuiStr = soTienGuiTGDTextField.getText().trim();
            String kyHanTGD = (String) kyHanTGDTextField.getSelectedItem();

            if (!soTienGuiStr.isEmpty()) {
                double soTienGui = Double.parseDouble(soTienGuiStr);
                double laiSuatTGD = interestRates.get(kyHanTGD)*100;

                double soTienLai = tinhSoTienLaiNhanDuoc(soTienGui, laiSuatTGD, kyHanTGD);
                double tongTien = tinhTongTienNhanDuoc(soTienGui, laiSuatTGD, kyHanTGD);

                // Sử dụng DecimalFormat để định dạng số
                DecimalFormat df = new DecimalFormat("###,###,###,###");

                soLaijLabel.setText(df.format(soTienLai));
                
                tongTienjLabel.setText(df.format(tongTien));
                
                vbcThemSTKjLabel.setText(readNumberToText(tongTien) + " đồng");
            } else {
                // Xử lý khi soTienGuiStr trống
                soLaijLabel.setText("0");
                tongTienjLabel.setText("0");
            }
        }
        });
        
        
        kyHanTGDTextField.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateLabels();
        }

        private void updateLabels() {
            String soTienGuiStr = soTienGuiTGDTextField.getText().trim();
            String kyHanTGD = (String) kyHanTGDTextField.getSelectedItem();

            if (!soTienGuiStr.isEmpty()) {
                double soTienGui = Double.parseDouble(soTienGuiStr);
                double laiSuatTGD = interestRates.get(kyHanTGD)*100;

                double soTienLai = tinhSoTienLaiNhanDuoc(soTienGui, laiSuatTGD, kyHanTGD);
                double tongTien = tinhTongTienNhanDuoc(soTienGui, laiSuatTGD, kyHanTGD);

                // Sử dụng DecimalFormat để định dạng số
                DecimalFormat df = new DecimalFormat("###,###,###,###");

                soLaijLabel.setText(df.format(soTienLai));
                tongTienjLabel.setText(df.format(tongTien));
                vbcThemSTKjLabel.setText(readNumberToText(tongTien) + " đồng");
            } else {
                // Xử lý khi soTienGuiStr trống
                soLaijLabel.setText("0");
                tongTienjLabel.setText("0");
                
            }
        }
        });

        
        

        

    }
    
    
    public static String readNumberToText(double number) {
    String[] units = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    String[] tens = {"", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};
    String[] hundreds = {"", "một trăm", "hai trăm", "ba trăm", "bốn trăm", "năm trăm", "sáu trăm", "bảy trăm", "tám trăm", "chín trăm"};
    String[] thousands = {"", "nghìn", "triệu", "tỷ", "nghìn tỷ", "triệu tỷ"};

    StringBuilder sb = new StringBuilder();
    boolean isFirstIteration = true;

    for (int index = 0; number > 0 && index < thousands.length; index++) {
        double currentNumber = number % 1000;
        String currentText = readThreeDigits(currentNumber, units, tens, hundreds);

        // Inside the loop where you append currentText and thousands[index]
if (currentNumber > 0) {
    if (!isFirstIteration) {
        // If the number is exactly 1000, 1 million, etc., don't append currentText
        if (!currentText.isEmpty()) {
            sb.insert(0, currentText + " " + thousands[index] + " ");
        }
    } else {
        if (!currentText.isEmpty()) {
            sb.insert(0, currentText + " ");
            isFirstIteration = false;
        }
    }
}


        number /= 1000;
    }

    // Xử lý trường hợp số 0
    if (sb.length() == 0) {
        sb.append("không");
    }

    return sb.toString().trim();
}




private static String readThreeDigits(double number, String[] units, String[] tens, String[] hundreds) {
    StringBuilder sb = new StringBuilder();

    int hundred = (int) (number / 100);
    int ten = (int) (number % 100 / 10);
    int unit = (int) (number % 10);

    if (hundred > 0) {
        sb.append(hundreds[hundred] + " ");
    }

    if (ten == 1) {
        sb.append("mười ");
    } else if (ten > 1) {
        sb.append(tens[ten] + " ");
    }

    if (unit > 0) {
        sb.append(units[unit]);
    }

    return sb.toString().trim();
}

    
    private void updateInterestLabel() {
        // Lấy giá trị được chọn từ combobox
        String selectedInterest = (String) kyHanTGDTextField.getSelectedItem();

        double laiSuatTGD = 0.0;
        
        // Kiểm tra xem giá trị có hợp lệ không và cập nhật label
        if (interestRates.containsKey(selectedInterest)) {
            double interestRate = interestRates.get(selectedInterest);
            String formattedInterestRate = String.format("%.1f%%", interestRate *100);
            laiSuatSTKjLabel.setText(formattedInterestRate);
            laiSuatTGD = interestRates.get(selectedInterest);
        } else {
            laiSuatSTKjLabel.setText("Invalid interest rate");
        }
    }
    
    public double tinhSoTienLaiNhanDuocMoiThang(double soTienGui, double laiSuat, String kyHan) {
    // Convert kyHan từ String sang double
        double kyHanDouble = Double.parseDouble(kyHan);

        // Tính số tiền lãi nhận được
        double soTienLaiNhanDuoc = (laiSuat / 100) * soTienGui * (kyHanDouble / 12);

        return soTienLaiNhanDuoc;
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
    String tenNganHangTGD = (String) tenNganHangTGDTextField.getSelectedItem();
    String soTienGuiTGD = soTienGuiTGDTextField.getText();
    
    String kyHanTGD = (String) kyHanTGDTextField.getSelectedItem();
    double laiSuatTGD = 0.0; // Giá trị mặc định hoặc giá trị khác nếu cần

    if (interestRates.containsKey(kyHanTGD)) {
        laiSuatTGD = interestRates.get(kyHanTGD)*100.0;
        System.out.println(laiSuatTGD);
    }

    try {
        if (!ngayTGD.isEmpty() && !soTienGuiTGD.isEmpty() && !kyHanTGD.isEmpty()) {
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setParseBigDecimal(true);

            // Check if kyHanTGDTextField is not empty before parsing
            if (!kyHanTGD.trim().isEmpty()) {
                // Parse soTienGuiTGD as BigDecimal
                BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(soTienGuiTGD);
                
                // Convert BigDecimal to double
                double soTienGui = bigDecimal.doubleValue();

                double kyHan = Double.parseDouble(kyHanTGD);

                double soTienLaiMoiThang = tinhSoTienLaiNhanDuocMoiThang(soTienGui, laiSuatTGD, kyHanTGD);
                double soTienLai = tinhSoTienLaiNhanDuoc(soTienGui, laiSuatTGD, kyHanTGD);
                double tongTien = tinhTongTienNhanDuoc(soTienGui, laiSuatTGD, kyHanTGD);

                soTietKiemModel.setNgayGui(ngayTGD);
                soTietKiemModel.setTenNganHang(tenNganHangTGD);
                soTietKiemModel.setSoTienGui(soTienGui);
                soTietKiemModel.setLaiSuatGui(laiSuatTGD);
                soTietKiemModel.setKyHan(kyHan);
                soTietKiemModel.setSoTienLaiNhanDuoc(soTienLaiMoiThang);
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
        soTienGuiTGDTextField = new javax.swing.JTextField();
        themTGDButton = new javax.swing.JButton();
        thoatTGDButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ngayGuiTGDTextField = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        kyHanTGDTextField = new javax.swing.JComboBox<>();
        tenNganHangTGDTextField = new javax.swing.JComboBox<>();
        laiSuatSTKjLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        soLaijLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tongTienjLabel = new javax.swing.JLabel();
        vbcThemSTKjLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

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
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(215, Short.MAX_VALUE))
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

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Ngày gửi");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Tên ngân hàng");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Kỳ hạn");

        kyHanTGDTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "15", "18", "24", "36" }));

        tenNganHangTGDTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BIDV", "Vietcombank", "Agribank", "Vietinbank", "MB", "Techcombank", "ACB", "BaoVietBank", "Sacombank", "Saigonbank", "SHB", "TPBank" }));
        tenNganHangTGDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenNganHangTGDTextFieldActionPerformed(evt);
            }
        });

        laiSuatSTKjLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        laiSuatSTKjLabel.setText("jLabel4");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Số tiền lãi tạm tính:");

        soLaijLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        soLaijLabel.setText("jLabel5");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Tổng số tiền gốc + lãi:");

        tongTienjLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tongTienjLabel.setText("jLabel7");

        vbcThemSTKjLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        vbcThemSTKjLabel.setText("jLabel7");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Viết bằng chữ:");

        javax.swing.GroupLayout bodyThemGiaoDichPanelLayout = new javax.swing.GroupLayout(bodyThemGiaoDichPanel);
        bodyThemGiaoDichPanel.setLayout(bodyThemGiaoDichPanelLayout);
        bodyThemGiaoDichPanelLayout.setHorizontalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(34, 34, 34)
                                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(soTienGuiTGDTextField)
                                    .addComponent(ngayGuiTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tenNganHangTGDTextField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(laiSuatSTKjLabel))
                                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(soLaijLabel)
                                            .addComponent(vbcThemSTKjLabel)
                                            .addComponent(tongTienjLabel))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(87, 87, 87)
                                .addComponent(kyHanTGDTextField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(themTGDButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                        .addComponent(thoatTGDButton)))
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
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenNganHangTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soTienGuiTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kyHanTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(laiSuatSTKjLabel))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(soLaijLabel))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tongTienjLabel))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vbcThemSTKjLabel)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themTGDButton)
                    .addComponent(thoatTGDButton))
                .addContainerGap())
        );

        getContentPane().add(bodyThemGiaoDichPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themTGDButtonActionPerformed
        System.out.println("loggoedInAccount id themSTKbutton: " + loginId);
        themGD(loginId);
        homeViewPro.refreshTableSTKData();
        homeViewPro.tongSoTietKiem();
    }//GEN-LAST:event_themTGDButtonActionPerformed

    private void thoatTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatTGDButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_thoatTGDButtonActionPerformed

    private void soTienGuiTGDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soTienGuiTGDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soTienGuiTGDTextFieldActionPerformed

    private void tenNganHangTGDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenNganHangTGDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenNganHangTGDTextFieldActionPerformed

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> kyHanTGDTextField;
    private javax.swing.JLabel laiSuatSTKjLabel;
    private com.toedter.calendar.JDateChooser ngayGuiTGDTextField;
    private javax.swing.JLabel soLaijLabel;
    private javax.swing.JTextField soTienGuiTGDTextField;
    private javax.swing.JComboBox<String> tenNganHangTGDTextField;
    private javax.swing.JButton themTGDButton;
    private javax.swing.JButton thoatTGDButton;
    private javax.swing.JLabel tongTienjLabel;
    private javax.swing.JLabel vbcThemSTKjLabel;
    // End of variables declaration//GEN-END:variables
}
