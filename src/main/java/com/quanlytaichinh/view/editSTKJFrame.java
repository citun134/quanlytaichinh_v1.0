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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    
    public Map<String, Double> interestRates;
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
        
//        vbcEditSTkjLabel.setText("");
        
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
        
//            .setText("2.0%");
        
//        soTienLaijLabel.setText("0");
//        tongLaijLabel.setText("0");
        
        double sotienlai = soTietKiemModel.getSoTienLaiNhanDuoc();
            double tongSoTien = soTietKiemModel.getTongTienNhanDuoc();
            
            DecimalFormat df = new DecimalFormat("###,###,###,###");
            
            soTienLaijLabel.setText(df.format(sotienlai));
            tongLaijLabel.setText(df.format(tongSoTien));
            vbcEditSTkjLabel.setText(readNumberToText(tongSoTien) + " đồng");
        
        
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

            soTienLaijLabel.setText(df.format(soTienLai));
            tongLaijLabel.setText(df.format(tongTien));
            vbcEditSTkjLabel.setText(readNumberToText(tongTien) + " đồng");
        } else {
            // Xử lý khi soTienGuiStr trống
            soTienLaijLabel.setText("0");
            tongLaijLabel.setText("0");
            vbcEditSTkjLabel.setText("");
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
        
        double sotienlai = soTietKiemModel.getSoTienLaiNhanDuoc();
            double tongSoTien = soTietKiemModel.getTongTienNhanDuoc();
            
            DecimalFormat df = new DecimalFormat("###,###,###,###");
            
//            soTienLaijLabel.setText(df.format(sotienlai));
//            tongLaijLabel.setText(df.format(tongSoTien));

        if (!soTienGuiStr.isEmpty()) {
            double soTienGui = Double.parseDouble(soTienGuiStr);
            double laiSuatTGD = interestRates.get(kyHanTGD)*100;

            double soTienLai = tinhSoTienLaiNhanDuoc(soTienGui, laiSuatTGD, kyHanTGD);
            double tongTien = tinhTongTienNhanDuoc(soTienGui, laiSuatTGD, kyHanTGD);

            // Sử dụng DecimalFormat để định dạng số
//            DecimalFormat df = new DecimalFormat("###,###,###,###");

            soTienLaijLabel.setText(df.format(soTienLai));
            tongLaijLabel.setText(df.format(tongTien));
            vbcEditSTkjLabel.setText(readNumberToText(tongTien) + " đồng");
        } else {
            // Xử lý khi soTienGuiStr trống
//            double sotienlai = soTietKiemModel.getSoTienLaiNhanDuoc();
//            double tongSoTien = soTietKiemModel.getTongTienNhanDuoc();
//            
//            DecimalFormat df = new DecimalFormat("###,###,###,###");
//            
            soTienLaijLabel.setText(df.format("0"));
            tongLaijLabel.setText(df.format("0"));
            vbcEditSTkjLabel.setText("");
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

        // Check if there are three trailing zeros, append "nghìn"
        if (number >= 1000 && number % 1000 == 0) {
            sb.insert(0, "nghìn ");
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
            editSoLaijLabel.setText(formattedInterestRate);
            laiSuatTGD = interestRates.get(selectedInterest);
        } else {
            editSoLaijLabel.setText("Invalid interest rate");
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
            String laiSuatGui = String.valueOf(soTietKiemModel.getLaiSuatGui());

            ngayGuiTGDTextField.setDateFormatString(soTietKiemModel.getNgayGui());
            tenNganHangTGDTextField.setSelectedItem(soTietKiemModel.getTenNganHang());

            // Xóa dấu phẩy trước khi đặt giá trị vào JTextField
            soTienGuiTGDTextField.setText(formattedSoTienGui.replaceAll(",", ""));
            String formattedInterestRate = String.format("%.1f%%", Double.parseDouble(laiSuatGui));
            editSoLaijLabel.setText(formattedInterestRate);

            
            kyHanTGDTextField.setSelectedItem(formattedKyHan);
        } else {
            // Xử lý trường hợp không tìm thấy soTietKiemModel
            System.out.println("Không tìm thấy thông tin sổ tiết kiệm cho accountId: " + accountId);
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
        
        double soTienLaiMoiThang = tinhSoTienLaiNhanDuocMoiThang(Double.parseDouble(soTienGuiTGD), laiSuatTGD, kyHanTGD);

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
                soTietKiemModel.setSoTienLaiNhanDuoc(soTienLaiMoiThang);
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
//        tenNganHangTGDTextField.setText("");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ngayGuiTGDTextField = new com.toedter.calendar.JDateChooser();
        kyHanTGDTextField = new javax.swing.JComboBox<>();
        tenNganHangTGDTextField = new javax.swing.JComboBox<>();
        editSoLaijLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        soTienLaijLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tongLaijLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        vbcEditSTkjLabel = new javax.swing.JLabel();

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
                .addContainerGap(228, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(228, Short.MAX_VALUE))
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

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Ngày gửi");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Tên ngân hàng");

        kyHanTGDTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "15", "18", "24", "36" }));

        tenNganHangTGDTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BIDV", "Vietcombank", "Agribank", "Vietinbank", "MB", "Techcombank", "ACB", "BaoVietBank", "Sacombank", "Saigonbank", "SHB", "TPBank" }));
        tenNganHangTGDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenNganHangTGDTextFieldActionPerformed(evt);
            }
        });

        editSoLaijLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        editSoLaijLabel.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Số tiền lãi tạm tính:");

        soTienLaijLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        soTienLaijLabel.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Tổng số tiền gốc + lãi:");

        tongLaijLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tongLaijLabel.setText("jLabel6");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Viết bằng chữ:");

        vbcEditSTkjLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vbcEditSTkjLabel.setText("jLabel6");

        javax.swing.GroupLayout bodyThemGiaoDichPanelLayout = new javax.swing.GroupLayout(bodyThemGiaoDichPanel);
        bodyThemGiaoDichPanel.setLayout(bodyThemGiaoDichPanelLayout);
        bodyThemGiaoDichPanelLayout.setHorizontalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addComponent(editSoLaijLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(kyHanTGDTextField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(47, 47, 47)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tenNganHangTGDTextField, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(soTienGuiTGDTextField)
                            .addComponent(ngayGuiTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(vbcEditSTkjLabel)
                                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                                .addGap(102, 102, 102)
                                                .addComponent(themTGDButton)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                                        .addComponent(thoatTGDButton))
                                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                        .addComponent(tongLaijLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(soTienLaijLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editSoLaijLabel))
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(soTienLaijLabel))
                        .addGap(18, 18, 18)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tongLaijLabel))
                        .addGap(18, 18, 18)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(vbcEditSTkjLabel))
                        .addGap(0, 58, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(themTGDButton)
                            .addComponent(thoatTGDButton))))
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
        homeViewPro.tongSoTietKiem();

    }//GEN-LAST:event_themTGDButtonActionPerformed

    private void thoatTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatTGDButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_thoatTGDButtonActionPerformed

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
    private javax.swing.JLabel editSoLaijLabel;
    private javax.swing.JPanel headerThemGiaoDichPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> kyHanTGDTextField;
    private com.toedter.calendar.JDateChooser ngayGuiTGDTextField;
    private javax.swing.JTextField soTienGuiTGDTextField;
    private javax.swing.JLabel soTienLaijLabel;
    private javax.swing.JComboBox<String> tenNganHangTGDTextField;
    private javax.swing.JButton themTGDButton;
    private javax.swing.JButton thoatTGDButton;
    private javax.swing.JLabel tongLaijLabel;
    private javax.swing.JLabel vbcEditSTkjLabel;
    // End of variables declaration//GEN-END:variables
}
