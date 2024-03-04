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
import static com.quanlytaichinh.view.themLSVJFrame.soTienNo;
import static com.quanlytaichinh.view.themLSVJFrame.tinhSoTienPhaiTraHangThang;
import static com.quanlytaichinh.view.themLSVJFrame.tinhTongSoTienPhaiTra;
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

public class editLSVJFrame extends javax.swing.JFrame {

    /**
     * Creates new form editLSVJFrame
     */
    public HomeViewController homeViewController;
    public GiaoDichDao giaoDichDao;
    public LoginController loginController;
    public LoginModel loginModel;
    public LoginView loginView;
    public LaiSuatVayModel laiSuatVayModel;
    public GiaoDichThuModel giaoDichThuModel;
    public SoTietKiemModel soTietKiemModel;
    public SimpleDateFormat simpleDateFormat;
    public HomeViewPro homeViewPro;
    
    private DefaultTableModel defaultTableSTKModel;
    
    public Map<String, Double> interestRates;
    public int loginId;
    
    public editLSVJFrame() {
    }
    
    public editLSVJFrame(HomeViewPro homeViewPro, int logId) {
        initComponents();
        setLocationRelativeTo(null);
        
        this.homeViewPro = homeViewPro;
        
        loginId = logId;
        System.out.println("loggoedInAccount id themchi: " + loginId);
        
        homeViewController = new HomeViewController();
        laiSuatVayModel = new LaiSuatVayModel();
        
//        ngayGiaiNganLSVjDateChooser.setDateFormatString("yyyy-MM-dd");
//        
//        Date date = new Date();
//        
//        ngayGiaiNganLSVjDateChooser.setDate(date);
        
        editGD(loginId);
        
        
        // Kiểm tra nếu giaoDichModel không null và giaoDichModel.getDate() không rỗng
    if (laiSuatVayModel != null && laiSuatVayModel.getNgayGiaiNgan() != null && !laiSuatVayModel.getNgayGiaiNgan().isEmpty()) {
        try {
            // Chuyển đổi chuỗi ngày từ giaoDichModel.getDate() thành đối tượng Date
            SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = mysqlDateFormat.parse(laiSuatVayModel.getNgayGiaiNgan());

            // Tạo đối tượng SimpleDateFormat cho định dạng mới "dd-MM-yyyy"
            SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd-MM-yyyy");

            // Set giá trị của thoiGianTGDTextField1 bằng giá trị đã chuyển đổi
            ngayGiaiNganLSVjDateChooser.setDate(parsedDate);

            // Cập nhật định dạng hiển thị của thoiGianTGDTextField1
            ngayGiaiNganLSVjDateChooser.setDateFormatString("dd-MM-yyyy");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    } else {
        // Nếu giaoDichModel hoặc giaoDichModel.getDate() là null hoặc rỗng, xử lý tương ứng (ví dụ: hiển thị ngày hiện tại)
        Date currentDate = new Date();
        ngayGiaiNganLSVjDateChooser.setDate(currentDate);
    }
    
    
    
    interestRates = new HashMap<>();
        interestRates.put("1", 0.065);
        interestRates.put("2", 0.065);
        interestRates.put("3", 0.066);
        interestRates.put("4", 0.067);
        interestRates.put("5", 0.067);
        interestRates.put("6", 0.068);
        interestRates.put("7", 0.069);
        interestRates.put("8", 0.069);
        interestRates.put("9", 0.070);
        interestRates.put("10", 0.071);
        interestRates.put("11", 0.071);
        interestRates.put("12", 0.072);
        interestRates.put("13", 0.073);
        interestRates.put("15", 0.073);
        interestRates.put("18", 0.075);
        interestRates.put("24", 0.078);
        interestRates.put("36", 0.08);
        
//        laiSuatVayjLabel.setText("6.5%");
        
        tongSoTienjLabel.setText("0");
        soTienNojLabel.setText("0");
        
        double sotienvay = laiSuatVayModel.getSoTienVay();
            double tongSoTien = laiSuatVayModel.getTongLaiPhaiTra();
            double tienNo = tongSoTien - sotienvay;
            DecimalFormat df = new DecimalFormat("###,###,###,###");
            
            soTienNojLabel.setText(df.format(tienNo));
            tongSoTienjLabel.setText(df.format(tongSoTien));
            vbcEditLSVjLabel.setText(readNumberToText(tongSoTien) + " đồng");
        
        
        thoiGianVayLSVTGDTextField.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Xử lý sự kiện khi một mục được chọn
            // Get the selected item
            String kyHanTGD = thoiGianVayLSVTGDTextField.getSelectedItem().toString().trim();

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
        
        
        soTienVayLSVTGDTextField.getDocument().addDocumentListener(new DocumentListener() {
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
            String soTienGuiStr = soTienVayLSVTGDTextField.getText().trim();
            String kyHanTGD = (String) thoiGianVayLSVTGDTextField.getSelectedItem();

            if (!soTienGuiStr.isEmpty()) {
                double soTienGui = Double.parseDouble(soTienGuiStr);
                double kyHan = Double.parseDouble(kyHanTGD);
                double laiSuatTGD = interestRates.get(kyHanTGD)*100;
                
                double tongSoTienPhaiTra = tinhTongSoTienPhaiTra(soTienGui, laiSuatTGD, kyHan);
                double tienNo = soTienNo(tongSoTienPhaiTra, soTienGui);

                
                
                // Sử dụng DecimalFormat để định dạng số
                DecimalFormat df = new DecimalFormat("###,###,###,###");

                tongSoTienjLabel.setText(df.format(tongSoTienPhaiTra));
                soTienNojLabel.setText(df.format(tienNo));
                vbcEditLSVjLabel.setText(readNumberToText(tongSoTienPhaiTra) + " đồng");
            } else {
                // Xử lý khi soTienGuiStr trống
//                soLaijLabel.setText("0");
                tongSoTienjLabel.setText("0");
                soTienNojLabel.setText("0");
                vbcEditLSVjLabel.setText("");
            }
        }
        });
        
        thoiGianVayLSVTGDTextField.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateLabels();
        }

        private void updateLabels() {
            String soTienGuiStr = soTienVayLSVTGDTextField.getText().trim();
            String kyHanTGD = (String) thoiGianVayLSVTGDTextField.getSelectedItem();

            if (!soTienGuiStr.isEmpty()) {
                double soTienGui = Double.parseDouble(soTienGuiStr);
                double kyHan = Double.parseDouble(kyHanTGD);
                double laiSuatTGD = interestRates.get(kyHanTGD)*100;

                double tongSoTienPhaiTra = tinhTongSoTienPhaiTra(soTienGui, laiSuatTGD, kyHan);
                double tienNo = soTienNo(tongSoTienPhaiTra, soTienGui);

                // Sử dụng DecimalFormat để định dạng số
                DecimalFormat df = new DecimalFormat("###,###,###,###");

                tongSoTienjLabel.setText(df.format(tongSoTienPhaiTra));
                
                soTienNojLabel.setText(df.format(tienNo));
                vbcEditLSVjLabel.setText(readNumberToText(tongSoTienPhaiTra) + " đồng");
                
            } else {
                // Xử lý khi soTienGuiStr trống
//                soLaijLabel.setText("0");
                tongSoTienjLabel.setText("0");
                soTienNojLabel.setText("0");
                vbcEditLSVjLabel.setText("");

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
        String selectedInterest = (String) thoiGianVayLSVTGDTextField.getSelectedItem();

        double laiSuatTGD = 0.0;
        
        // Kiểm tra xem giá trị có hợp lệ không và cập nhật label
        if (interestRates.containsKey(selectedInterest)) {
            double interestRate = interestRates.get(selectedInterest);
            String formattedInterestRate = String.format("%.1f%%", interestRate *100);
            laiSuatVayjLabel.setText(formattedInterestRate);
            laiSuatTGD = interestRates.get(selectedInterest);
        } else {
            laiSuatVayjLabel.setText("Invalid interest rate");
        }
    }
    
    
    
    public void editGD(int accountId) {
        laiSuatVayModel = homeViewController.getInforUserLSV(accountId);

        if (laiSuatVayModel != null) {
            DecimalFormat df = new DecimalFormat("###,###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedGiaTriBDS = df.format(laiSuatVayModel.getGiaTriBatDongSan());
            String formattedSoTienVay = df.format(laiSuatVayModel.getSoTienVay());
            String formattedSoTienTraHangThang = df.format(laiSuatVayModel.getSoTienPhaiTraHangThang());
            String formattedTongLaiPhaiTra = df.format(laiSuatVayModel.getTongLaiPhaiTra());
            String thoiGianVay = df.format(laiSuatVayModel.getThoiGianVay());
            
            String laiSuatVay = String.valueOf(laiSuatVayModel.getLaiSuat());

            tenNganHangLSVTGDTextField.setSelectedItem(laiSuatVayModel.getTenNganHangLSV());

            // Xóa dấu phẩy trước khi đặt giá trị vào JTextField
            giaTriBDSLSVTGDTextField.setText(formattedGiaTriBDS.replaceAll(",", ""));
            soTienVayLSVTGDTextField.setText(formattedSoTienVay.replaceAll(",", ""));
            thoiGianVayLSVTGDTextField.setSelectedItem(thoiGianVay);
            String formattedInterestRate = String.format("%.1f%%", Double.parseDouble(laiSuatVay));
            
            laiSuatVayjLabel.setText(formattedInterestRate);
            
            ngayGiaiNganLSVjDateChooser.setDateFormatString(laiSuatVayModel.getNgayGiaiNgan());
        } else {
            // Xử lý trường hợp không tìm thấy soTietKiemModel
            System.out.println("Không tìm thấy thông tin sổ tiết kiệm cho accountId: " + accountId);
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
//        double tongSoTienTra = tinhSoTienPhaiTraHangThang(soTienVay, laiSuat, thoiGianVay) * soThangVay;

        // Tổng số tiền phải trả - lãi giảm dần
        // Lãi suất hàng tháng
        double laiSuatHangThang = (laiSuat / 100) / 12;

        // Số tiền lãi phải trả hàng tháng
        double tienLaiHangThang = soTienVay * laiSuatHangThang;

        // Số tiền phải trả hàng tháng
        double tienHangThang = soTienVay / soThangVay + tienLaiHangThang;

        // Tổng số tiền phải trả
        double tongSoTienTra = tienHangThang * soThangVay;

        return tongSoTienTra;
    }
    
    public static double soTienNo(double tongSoTienTra, double soTienVay) {
        return tongSoTienTra - soTienVay;
    }
    
    public void themGD(int accountId) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTGD = ngayGiaiNganLSVjDateChooser.getDate();

        String tenNganHangTGD = (String) tenNganHangLSVTGDTextField.getSelectedItem();
        String giaTriBDSTGD = giaTriBDSLSVTGDTextField.getText();
        String soTienVayTGD = soTienVayLSVTGDTextField.getText();
        String thoiGianVayTGD = (String) thoiGianVayLSVTGDTextField.getSelectedItem();
        
        double laiSuatTGD = 0.0; // Giá trị mặc định hoặc giá trị khác nếu cần

    if (interestRates.containsKey(thoiGianVayTGD)) {
        laiSuatTGD = interestRates.get(thoiGianVayTGD)*100.0;
        System.out.println(laiSuatTGD);
    }
        
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

                        homeViewController.updateLaiSuatVay(laiSuatVayModel);

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
//        tenNganHangLSVTGDTextField.setText("");
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

        headerThemGiaoDichPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        bodyThemGiaoDichPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        themTGDButton = new javax.swing.JButton();
        thoatTGDButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        giaTriBDSLSVTGDTextField = new javax.swing.JTextField();
        soTienVayLSVTGDTextField = new javax.swing.JTextField();
        ngayGiaiNganLSVjDateChooser = new com.toedter.calendar.JDateChooser();
        thoiGianVayLSVTGDTextField = new javax.swing.JComboBox<>();
        tenNganHangLSVTGDTextField = new javax.swing.JComboBox<>();
        laiSuatVayjLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        soTienNojLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tongSoTienjLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        vbcEditLSVjLabel = new javax.swing.JLabel();

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
                .addContainerGap(206, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(207, Short.MAX_VALUE))
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
        jLabel8.setText("Thời gian vay");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Lãi suất (%/năm)");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Ngày giải ngân");

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

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Tên ngân hàng");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Giá trị bất động sản");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Số tiền vay");

        thoiGianVayLSVTGDTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "15", "18", "24", "36" }));

        tenNganHangLSVTGDTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BIDV", "Vietcombank", "Agribank", "Vietinbank", "MB", "Techcombank", "ACB", "BaoVietBank", "Sacombank", "Saigonbank", "SHB", "TPBank" }));

        laiSuatVayjLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        laiSuatVayjLabel.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Số tiền lãi tạm tính:");

        soTienNojLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        soTienNojLabel.setText("jLabel5");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Tổng số tiền gốc + lãi:");

        tongSoTienjLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tongSoTienjLabel.setText("jLabel11");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Viết bằng chữ:");

        vbcEditLSVjLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vbcEditLSVjLabel.setText("jLabel11");

        javax.swing.GroupLayout bodyThemGiaoDichPanelLayout = new javax.swing.GroupLayout(bodyThemGiaoDichPanel);
        bodyThemGiaoDichPanel.setLayout(bodyThemGiaoDichPanelLayout);
        bodyThemGiaoDichPanelLayout.setHorizontalGroup(
            bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(giaTriBDSLSVTGDTextField)
                            .addComponent(tenNganHangLSVTGDTextField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(41, 41, 41)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(thoiGianVayLSVTGDTextField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(soTienVayLSVTGDTextField)))
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ngayGiaiNganLSVjDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addComponent(tongSoTienjLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                .addComponent(themTGDButton)
                                .addGap(169, 169, 169)
                                .addComponent(thoatTGDButton))
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(laiSuatVayjLabel)
                                    .addComponent(soTienNojLabel)
                                    .addComponent(vbcEditLSVjLabel))
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thoiGianVayLSVTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayGiaiNganLSVjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(laiSuatVayjLabel))
                        .addGap(18, 18, 18)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(soTienNojLabel))
                        .addGap(18, 18, 18)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tongSoTienjLabel))
                        .addGap(18, 18, 18)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(vbcEditLSVjLabel))
                        .addContainerGap(59, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(themTGDButton)
                            .addComponent(thoatTGDButton))
                        .addContainerGap())))
        );

        getContentPane().add(bodyThemGiaoDichPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themTGDButtonActionPerformed
        //        System.out.println("loggoedInAccount id themSTKbutton: " + loginId);
        themGD(loginId);
        homeViewPro.refreshTableLSVData();
        homeViewPro.tongLaiSuatVay();
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
            java.util.logging.Logger.getLogger(editLSVJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editLSVJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editLSVJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editLSVJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editLSVJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyThemGiaoDichPanel;
    private javax.swing.JTextField giaTriBDSLSVTGDTextField;
    private javax.swing.JPanel headerThemGiaoDichPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel laiSuatVayjLabel;
    private com.toedter.calendar.JDateChooser ngayGiaiNganLSVjDateChooser;
    private javax.swing.JLabel soTienNojLabel;
    private javax.swing.JTextField soTienVayLSVTGDTextField;
    private javax.swing.JComboBox<String> tenNganHangLSVTGDTextField;
    private javax.swing.JButton themTGDButton;
    private javax.swing.JButton thoatTGDButton;
    private javax.swing.JComboBox<String> thoiGianVayLSVTGDTextField;
    private javax.swing.JLabel tongSoTienjLabel;
    private javax.swing.JLabel vbcEditLSVjLabel;
    // End of variables declaration//GEN-END:variables
}
