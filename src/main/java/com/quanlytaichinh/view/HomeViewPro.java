package com.quanlytaichinh.view;

import com.quanlytaichinh.controller.HomeViewController;
import com.quanlytaichinh.controller.LoginController;
import com.quanlytaichinh.dao.GiaoDichDao;
import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.model.GiaoDichThuModel;
import com.quanlytaichinh.model.LoginModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
        
public class HomeViewPro extends javax.swing.JFrame {
    public HomeViewController homeViewController;
    public GiaoDichDao giaoDichDao;
    public LoginController loginController;
    public LoginModel loginModel;
    public LoginView loginView;
    public GiaoDichModel giaoDichModel;
    public DefaultTableModel defaultTableModel;
    public DefaultTableModel defaultTableThuModel;
    public DefaultTableModel timKiemTen;
    public DefaultTableModel timKiemTien;
    public DefaultTableModel timKiemNgay;
    public Timer t;
    public SimpleDateFormat simpleDateFormat;
    
    
    public int logId;
     
    public HomeViewPro(){}
    public HomeViewPro(LoginModel loginModel) {
        initComponents();
        
        setLocationRelativeTo(null);
        
        this.loginModel = loginModel;
        
        homeViewController = new HomeViewController();
        giaoDichModel = new GiaoDichModel();
        
        logId = loginModel.getAccount_id();
        System.out.println("loggoedInAccount id: " + logId);
        
        
        dateTime();
        times();
        
        
        showChiTKTableUser(logId);
        showThuTKTableUser(logId);
        findUsers(logId);
        findMoney(logId);
        findDate(logId);

        displayTen(logId);
        displayTien(logId);
        displayNgay(logId);
        
        thoiGianTGDTextField.setDateFormatString("yyyy-MM-dd");
        tuNgayTKTextField.setDateFormatString("yyyy-MM-dd");
        denNgayTKTextField.setDateFormatString("yyyy-MM-dd");
        
        Date date = new Date();
        thoiGianTGDTextField.setDate(date);
        tuNgayTKTextField.setDate(date);
        denNgayTKTextField.setDate(date);
    }
    
    public void showChiTKTableUser(int accountId){
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return true;
            }
        };
        
        chiTable.setModel(defaultTableModel);
        
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Thời Gian");
        defaultTableModel.addColumn("Mặt Hàng");
        defaultTableModel.addColumn("Thành Tiền");
        defaultTableModel.addColumn("Ghi Chú");
        defaultTableModel.addColumn("Hạng Mục");

        defaultTableModel.setRowCount(0);
        setTableData(homeViewController.getAllInforUser(accountId));
    }
    
    public void showThuTKTableUser(int accountId){
        defaultTableThuModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return true;
            }
        };
        
        thuTable.setModel(defaultTableThuModel);
        
        defaultTableThuModel.addColumn("ID");
        defaultTableThuModel.addColumn("Thời Gian");
        defaultTableThuModel.addColumn("Thành Tiền");
        defaultTableThuModel.addColumn("Ghi Chú");
        defaultTableThuModel.addColumn("Hạng Mục");

        defaultTableThuModel.setRowCount(0);
        setThuTableData(homeViewController.getAllInforUserThu(accountId));
    }
    
    
    public void setTableData(List<GiaoDichModel> allGiaoDich){
        for(GiaoDichModel giaoDich: allGiaoDich){
            DecimalFormat df = new DecimalFormat("###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedThanhTien = df.format(giaoDich.getThanhTien());
            
            defaultTableModel.addRow(new Object[] {giaoDich.getId(), giaoDich.getDate(),
                giaoDich.getMatHang(), formattedThanhTien, giaoDich.getGhiChu(),
                    giaoDich.getHangMuc(), giaoDich.getAccountId()});
        }
    }
    
    public void setThuTableData(List<GiaoDichThuModel> allGiaoDich){
        for(GiaoDichThuModel giaoDich: allGiaoDich){
            DecimalFormat df = new DecimalFormat("###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedThanhTien = df.format(giaoDich.getThanhTien());
            
            defaultTableThuModel.addRow(new Object[] {giaoDich.getId(), giaoDich.getDate(),
                formattedThanhTien, giaoDich.getGhiChu(),
                    giaoDich.getHangMuc(), giaoDich.getAccountId()});
        }
    }
    
    public final void findUsers(int accountId){
        List<GiaoDichModel> users = homeViewController.searchTenGiaoDich(tenTKTextField.getText(), accountId);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Thời Gian", "Mặt Hàng", "Thành Tiền", "Ghi Chú", "Hạng Mục"});
        Object[] row = new Object[6];
        
        for(int i = 0; i < users.size(); i++)
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getDate();
            row[2] = users.get(i).getMatHang();
            row[3] = users.get(i).getThanhTien();
            row[4] = users.get(i).getGhiChu();
            row[5] = users.get(i).getHangMuc();
            model.addRow(row);
        }
       tenTKTable.setModel(model);
       
    }

    
    public final void findMoney(int accountId){
        List<GiaoDichModel> users = homeViewController.searchTienGiaoDichThuChi(tuTienTKTextField.getText(), denTienTKTextField.getText(), accountId);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Thời Gian", "Mặt Hàng", "Thành Tiền", "Ghi Chú", "Hạng Mục"});
        Object[] row = new Object[6];
        
        DecimalFormat df = new DecimalFormat("###,###,###,###");
        
        for(int i = 0; i < users.size(); i++)
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getDate();
            row[2] = users.get(i).getMatHang();
//            row[3] = users.get(i).getThanhTien();
            row[3] = df.format(users.get(i).getThanhTien());        
            row[4] = users.get(i).getGhiChu();
            row[5] = users.get(i).getHangMuc();
            model.addRow(row);
        }
       tienTKTable.setModel(model);
    }
    
    public void findDate(int accountId){
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date tuNgayDate = tuNgayTKTextField.getDate();
        Date denNgayDate = denNgayTKTextField.getDate();
        
        DecimalFormat df = new DecimalFormat("###,###,###,###");

        if (tuNgayDate != null && denNgayDate != null) {
            String tuNgayTxt = simpleDateFormat.format(tuNgayDate);
            String denNgayTxt = simpleDateFormat.format(denNgayDate);

            List<GiaoDichModel> users = homeViewController.searchThoiGianGiaoDichThuChi(tuNgayTxt, denNgayTxt, accountId);

            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Thời Gian", "Mặt Hàng", "Thành Tiền", "Ghi Chú", "Hạng Mục"});
            Object[] row = new Object[6];

            for(int i = 0; i < users.size(); i++) {
                row[0] = users.get(i).getId();
                row[1] = users.get(i).getDate();
                row[2] = users.get(i).getMatHang();
//                row[3] = users.get(i).getThanhTien();
                row[3] = df.format(users.get(i).getThanhTien()); 
                row[4] = users.get(i).getGhiChu();
                row[5] = users.get(i).getHangMuc();
                model.addRow(row);
            }
            thoiGianTKTable.setModel(model);
        } else {
            // Handle the case where either tuNgayTKTextField or denNgayTKTextField returned null dates
            // You can display an error message or take appropriate action.
        }
    }

    
    public void themGD(int accountId){
//        String dateTGD = thoiGianTGDTextField.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateTGD = sdf.format(thoiGianTGDTextField.getDate());
        String matHangTGD = matHangTGDTextField.getText();
        String thanhTienTGD = thanhTienTGDTextField.getText();
        String ghiChuTGD = ghiChuTGDTextField.getText();
        
//        if(loginModel.getUser() == loginView.getName() )
        try {
            if (!matHangTGD.isEmpty() && !thanhTienTGD.isEmpty() && !dateTGD.isEmpty()) {
                // Kiểm tra xem thanhTienTGD không chứa chữ cái
                if (!thanhTienTGD.matches(".*[a-zA-Z].*")) {
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setParseBigDecimal(true);
                    BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(thanhTienTGD);

                    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    inputDateFormat.setLenient(false);
                    Date parsedDate = inputDateFormat.parse(dateTGD);

                    // Format the parsed date back to the desired format
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = outputDateFormat.format(parsedDate);

                    giaoDichModel.setDate(formattedDate);
                    giaoDichModel.setMatHang(matHangTGD);                                               
                    giaoDichModel.setThanhTien(bigDecimal.doubleValue());
                    giaoDichModel.setGhiChu(ghiChuTGD);
                    giaoDichModel.setAccountId(accountId);
                    
                    if (anUongRadioButton.isSelected()) {
                    giaoDichModel.setHangMuc("Ăn Uống");
                    } else if (quanAoRadioButton.isSelected()) {
                        giaoDichModel.setHangMuc("Quần Áo");
                    } else if (dvSinhHoatRadioButton.isSelected()) {
                        giaoDichModel.setHangMuc("Dịch Vụ Sinh Hoạt");
                    } else if (khacRadioButton.isSelected()) {
                        giaoDichModel.setHangMuc("Khác");
                    }
                    
                    homeViewController.addGiaoDichChi(giaoDichModel);
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    defaultTableModel.setRowCount(0);
                    setTableData(homeViewController.getAllInforUser(accountId));
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm Thất Bại! Thanh Tiền không hợp lệ.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Thất Bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        
//        thoiGianTGDTextField.setText("");
        matHangTGDTextField.setText("");
        thanhTienTGDTextField.setText("");
        ghiChuTGDTextField.setText("");
    }

    public void setDataToChartYear(JPanel jpanel, int year) {
        //Hiển thị 12 columns
        List<GiaoDichModel> listItem = homeViewController.getListByMoneyYear(logId, year);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian",
                "Tiền", dataset);

        // Create a map to store the data for each month
        Map<String, Long> monthData = new HashMap<>();

        // Initialize the map with 0 values for all 12 months
        for (int month = 1; month <= 12; month++) {
            monthData.put("tháng " + month, 0L);
        }

        // Populate the map with actual data
        for (GiaoDichModel item : listItem) {
//            int year = item.getYear();
            int month = item.getMonth();
            long totalMoney = item.getTotalMoney();
            String key = "tháng " + month;

            // Add the totalMoney to the corresponding month
            monthData.put(key, monthData.getOrDefault(key, 0L) + totalMoney);
        }

        // Add the data to the dataset
        for (int month = 1; month <= 12; month++) {
            String key = "tháng " + month;
            dataset.addValue(monthData.get(key), "Số tiền", key);
        }

        ChartPanel chartPanel = new ChartPanel(chart);

        // Thêm chartPanel vào JPanel
        jpanel.removeAll(); // Xóa bất kỳ thành phần hiện có trong JPanel
        jpanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để đặt ChartPanel
        jpanel.add(chartPanel, BorderLayout.CENTER); // Thêm ChartPanel vào JPanel ở vị trí trung tâm
        jpanel.revalidate(); // Cập nhật lại JPanel để hiển thị biểu đồ
    }
    
    public final void dateTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dd = sdf.format(date);
        showDateLabel.setText(dd);
    }
    
    public final void times(){
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                Date date = new Date();
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String tt = simpleDateFormat.format(date);
                showRealTimeLabel.setText(tt);
            }
        });
        t.start();
    }
    
    public void displayTen(int accountId) {

    // Create a DefaultTableModel for the second table (tenTKTable)
    DefaultTableModel defaultTableModelTen = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
        tenTKTable.setModel(defaultTableModelTen);

        defaultTableModelTen.addColumn("ID");
        defaultTableModelTen.addColumn("Thời Gian");
        defaultTableModelTen.addColumn("Mặt Hàng");
        defaultTableModelTen.addColumn("Thành Tiền");
        defaultTableModelTen.addColumn("Ghi Chú");
        defaultTableModelTen.addColumn("Hạng Mục");


        // Get the data from your controller or data source
        List<GiaoDichModel> allGiaoDich = homeViewController.getAllInforUserThuChi(accountId);

        // Populate both tables with the data
        for (GiaoDichModel giaoDich : allGiaoDich) {
            DecimalFormat df = new DecimalFormat("###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedThanhTien = df.format(giaoDich.getThanhTien());
            
            defaultTableModelTen.addRow(new Object[]{giaoDich.getId(), giaoDich.getDate(),
                    giaoDich.getMatHang(),formattedThanhTien, giaoDich.getGhiChu(), giaoDich.getHangMuc()});
        }
    }
    
    public void displayTien(int accountId) {

    // Create a DefaultTableModel for the second table (tenTKTable)
    DefaultTableModel defaultTableModelTen = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
        tienTKTable.setModel(defaultTableModelTen);

        defaultTableModelTen.addColumn("ID");
        defaultTableModelTen.addColumn("Thời Gian");
        defaultTableModelTen.addColumn("Mặt Hàng");
        defaultTableModelTen.addColumn("Thành Tiền");
        defaultTableModelTen.addColumn("Ghi Chú");
        defaultTableModelTen.addColumn("Hạng Mục");
        

        // Get the data from your controller or data source
        List<GiaoDichModel> allGiaoDich = homeViewController.getAllInforUserThuChi(accountId);

        // Populate both tables with the data
        for (GiaoDichModel giaoDich : allGiaoDich) {
            DecimalFormat df = new DecimalFormat("###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedThanhTien = df.format(giaoDich.getThanhTien());
            
            defaultTableModelTen.addRow(new Object[]{giaoDich.getId(), giaoDich.getDate(),
                    giaoDich.getMatHang(), formattedThanhTien, giaoDich.getGhiChu(), giaoDich.getHangMuc()});
        }
    }
   
   public void displayNgay(int accountId) {

    // Create a DefaultTableModel for the second table (tenTKTable)
    DefaultTableModel defaultTableModelTen = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    };
    
        thoiGianTKTable.setModel(defaultTableModelTen);

        defaultTableModelTen.addColumn("ID");
        defaultTableModelTen.addColumn("Thời Gian");
        defaultTableModelTen.addColumn("Mặt Hàng");
        defaultTableModelTen.addColumn("Thành Tiền");
        defaultTableModelTen.addColumn("Ghi Chú");
        defaultTableModelTen.addColumn("Hạng Mục");

        // Get the data from your controller or data source
        List<GiaoDichModel> allGiaoDich = homeViewController.getAllInforUserThuChi(accountId);

        // Populate both tables with the data
        for (GiaoDichModel giaoDich : allGiaoDich) {
            DecimalFormat df = new DecimalFormat("###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedThanhTien = df.format(giaoDich.getThanhTien());
            
            defaultTableModelTen.addRow(new Object[]{giaoDich.getId(), giaoDich.getDate(),
                    giaoDich.getMatHang(), formattedThanhTien, giaoDich.getGhiChu(), giaoDich.getHangMuc()});
        }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        themDialog = new javax.swing.JDialog();
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
        thoiGianTGDTextField = new com.toedter.calendar.JDateChooser();
        hangMucButton = new javax.swing.JLabel();
        quanAoRadioButton = new javax.swing.JRadioButton();
        anUongRadioButton = new javax.swing.JRadioButton();
        dvSinhHoatRadioButton = new javax.swing.JRadioButton();
        khacRadioButton = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        editChiDialog = new javax.swing.JDialog();
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
        thoiGianTGDTextField1 = new com.toedter.calendar.JDateChooser();
        hangMucButton1 = new javax.swing.JLabel();
        quanAoRadioButton1 = new javax.swing.JRadioButton();
        anUongRadioButton1 = new javax.swing.JRadioButton();
        dvSinhHoatRadioButton1 = new javax.swing.JRadioButton();
        khacRadioButton1 = new javax.swing.JRadioButton();
        headerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        showDateLabel = new javax.swing.JLabel();
        showRealTimeLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonPanel = new javax.swing.JPanel();
        giaoDichButton = new javax.swing.JButton();
        timKiemButton = new javax.swing.JButton();
        soTietKiemButton = new javax.swing.JButton();
        thongKeButton = new javax.swing.JButton();
        thoatHomeButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        timKiemPanel = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        tenTKTextField = new javax.swing.JTextField();
        tenTKButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tenTKTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tienTKTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tuTienTKTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        denTienTKTextField = new javax.swing.JTextField();
        tienTKButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        thoiGianTKTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ngayTKButton = new javax.swing.JButton();
        tuNgayTKTextField = new com.toedter.calendar.JDateChooser();
        denNgayTKTextField = new com.toedter.calendar.JDateChooser();
        thongKePanel = new javax.swing.JPanel();
        showTKPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        yearTextField = new javax.swing.JTextField();
        tkYearTKButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        thuTienPanel = new javax.swing.JPanel();
        giaoDichPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        themChiButton = new javax.swing.JButton();
        xoaChiButton = new javax.swing.JButton();
        suaChiButton = new javax.swing.JButton();
        xoaAllChiButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        chiTable = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        thuTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        themThuButton = new javax.swing.JButton();
        refreshThuButton = new javax.swing.JButton();
        suaThuButton = new javax.swing.JButton();
        xoaThuButton = new javax.swing.JButton();
        xoaAllThuButton = new javax.swing.JButton();

        themDialog.setMinimumSize(new java.awt.Dimension(400, 400));

        headerThemGiaoDichPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Notes.png"))); // NOI18N
        jLabel6.setText("THÊM GIAO DỊCH");

        javax.swing.GroupLayout headerThemGiaoDichPanelLayout = new javax.swing.GroupLayout(headerThemGiaoDichPanel);
        headerThemGiaoDichPanel.setLayout(headerThemGiaoDichPanelLayout);
        headerThemGiaoDichPanelLayout.setHorizontalGroup(
            headerThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(157, Short.MAX_VALUE))
        );
        headerThemGiaoDichPanelLayout.setVerticalGroup(
            headerThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerThemGiaoDichPanelLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        themDialog.getContentPane().add(headerThemGiaoDichPanel, java.awt.BorderLayout.PAGE_START);

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
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(hangMucButton))
                        .addGap(34, 34, 34)
                        .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matHangTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                            .addComponent(thanhTienTGDTextField)
                            .addComponent(ghiChuTGDTextField)
                            .addComponent(thoiGianTGDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bodyThemGiaoDichPanelLayout.createSequentialGroup()
                                .addComponent(anUongRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(quanAoRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(dvSinhHoatRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(khacRadioButton)
                                .addGap(0, 0, Short.MAX_VALUE))))
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
                .addContainerGap()
                .addGroup(bodyThemGiaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(thoiGianTGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(thoatTGDButton)
                .addContainerGap())
        );

        themDialog.getContentPane().add(bodyThemGiaoDichPanel, java.awt.BorderLayout.CENTER);

        editChiDialog.setMinimumSize(new java.awt.Dimension(400, 400));

        headerThemGiaoDichPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Notes.png"))); // NOI18N
        jLabel7.setText("THÊM GIAO DỊCH");

        javax.swing.GroupLayout headerThemGiaoDichPanel1Layout = new javax.swing.GroupLayout(headerThemGiaoDichPanel1);
        headerThemGiaoDichPanel1.setLayout(headerThemGiaoDichPanel1Layout);
        headerThemGiaoDichPanel1Layout.setHorizontalGroup(
            headerThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerThemGiaoDichPanel1Layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(157, Short.MAX_VALUE))
        );
        headerThemGiaoDichPanel1Layout.setVerticalGroup(
            headerThemGiaoDichPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerThemGiaoDichPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        editChiDialog.getContentPane().add(headerThemGiaoDichPanel1, java.awt.BorderLayout.PAGE_START);

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
        themTGDButton1.setText("  THÊM");
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

        hangMucButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        hangMucButton1.setText("Hạng Mục");

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
        khacRadioButton1.setText("Khác");

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
                            .addComponent(matHangTGDTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                            .addComponent(thanhTienTGDTextField1)
                            .addComponent(ghiChuTGDTextField1)
                            .addComponent(thoiGianTGDTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bodyThemGiaoDichPanel1Layout.createSequentialGroup()
                                .addComponent(anUongRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(quanAoRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(dvSinhHoatRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(khacRadioButton1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(thoatTGDButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyThemGiaoDichPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(themTGDButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGap(18, 18, 18)
                .addComponent(themTGDButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(thoatTGDButton1)
                .addContainerGap())
        );

        editChiDialog.getContentPane().add(bodyThemGiaoDichPanel1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerPanel.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Date");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Time");

        showDateLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        showDateLabel.setText("showUser");

        showRealTimeLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        showRealTimeLabel.setText("showDate");

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("QUẢN LÝ TÀI CHÍNH");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(415, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(414, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showDateLabel)
                            .addComponent(showRealTimeLabel))
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(showDateLabel))
                        .addGap(24, 24, 24)
                        .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(showRealTimeLabel)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

        buttonPanel.setBackground(new java.awt.Color(204, 204, 255));
        buttonPanel.setLayout(new java.awt.GridLayout(5, 0));

        giaoDichButton.setBackground(new java.awt.Color(204, 204, 255));
        giaoDichButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        giaoDichButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/List.png"))); // NOI18N
        giaoDichButton.setText("Giao Dịch");
        giaoDichButton.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        giaoDichButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giaoDichButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(giaoDichButton);

        timKiemButton.setBackground(new java.awt.Color(204, 204, 255));
        timKiemButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        timKiemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Search.png"))); // NOI18N
        timKiemButton.setText("Tìm Kiếm ");
        timKiemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(timKiemButton);

        soTietKiemButton.setBackground(new java.awt.Color(204, 204, 255));
        soTietKiemButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        soTietKiemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Price list.png"))); // NOI18N
        soTietKiemButton.setText("Sổ Tiết Kiệm");
        soTietKiemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soTietKiemButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(soTietKiemButton);

        thongKeButton.setBackground(new java.awt.Color(204, 204, 255));
        thongKeButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        thongKeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Statistics.png"))); // NOI18N
        thongKeButton.setText("Thống Kê");
        thongKeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thongKeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(thongKeButton);

        thoatHomeButton.setBackground(new java.awt.Color(204, 204, 255));
        thoatHomeButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        thoatHomeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Log out.png"))); // NOI18N
        thoatHomeButton.setText("   Thoát");
        thoatHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoatHomeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(thoatHomeButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.LINE_START);

        mainPanel.setBackground(new java.awt.Color(255, 204, 204));
        mainPanel.setLayout(new java.awt.CardLayout());

        timKiemPanel.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Mặt hàng");

        tenTKTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenTKTextFieldActionPerformed(evt);
            }
        });
        tenTKTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tenTKTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tenTKTextFieldKeyTyped(evt);
            }
        });

        tenTKButton.setBackground(new java.awt.Color(204, 204, 255));
        tenTKButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tenTKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Search.png"))); // NOI18N
        tenTKButton.setText("  Tìm kiếm");
        tenTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenTKButtonActionPerformed(evt);
            }
        });
        tenTKButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tenTKButtonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tenTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tenTKButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(tenTKButton)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 100));

        tenTKTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tenTKTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tenTKTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tenTKTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tenTKTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Tên", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));

        tienTKTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tienTKTable);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Từ");

        tuTienTKTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tuTienTKTextFieldKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Đến");

        denTienTKTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                denTienTKTextFieldKeyReleased(evt);
            }
        });

        tienTKButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tienTKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Search.png"))); // NOI18N
        tienTKButton.setText("  Tìm kiếm");
        tienTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tienTKButtonActionPerformed(evt);
            }
        });
        tienTKButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tienTKButtonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tuTienTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(denTienTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tienTKButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tuTienTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(denTienTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tienTKButton)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Tiền", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        thoiGianTKTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(thoiGianTKTable);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Từ");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Đến");

        ngayTKButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ngayTKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Search.png"))); // NOI18N
        ngayTKButton.setText("  Tìm kiếm");
        ngayTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayTKButtonActionPerformed(evt);
            }
        });
        ngayTKButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ngayTKButtonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tuNgayTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(denNgayTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ngayTKButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tuNgayTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(denNgayTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ngayTKButton)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Ngày, Tháng, Năm", jPanel5);

        javax.swing.GroupLayout timKiemPanelLayout = new javax.swing.GroupLayout(timKiemPanel);
        timKiemPanel.setLayout(timKiemPanelLayout);
        timKiemPanelLayout.setHorizontalGroup(
            timKiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timKiemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        timKiemPanelLayout.setVerticalGroup(
            timKiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timKiemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        mainPanel.add(timKiemPanel, "card3");

        thongKePanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout showTKPanelLayout = new javax.swing.GroupLayout(showTKPanel);
        showTKPanel.setLayout(showTKPanelLayout);
        showTKPanelLayout.setHorizontalGroup(
            showTKPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 976, Short.MAX_VALUE)
        );
        showTKPanelLayout.setVerticalGroup(
            showTKPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        yearTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearTextFieldActionPerformed(evt);
            }
        });

        tkYearTKButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tkYearTKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Search.png"))); // NOI18N
        tkYearTKButton.setText("Tìm kiếm");
        tkYearTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkYearTKButtonActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Năm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(363, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 362, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tkYearTKButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tkYearTKButton)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout thongKePanelLayout = new javax.swing.GroupLayout(thongKePanel);
        thongKePanel.setLayout(thongKePanelLayout);
        thongKePanelLayout.setHorizontalGroup(
            thongKePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongKePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showTKPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        thongKePanelLayout.setVerticalGroup(
            thongKePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKePanelLayout.createSequentialGroup()
                .addComponent(showTKPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainPanel.add(thongKePanel, "card4");

        javax.swing.GroupLayout thuTienPanelLayout = new javax.swing.GroupLayout(thuTienPanel);
        thuTienPanel.setLayout(thuTienPanelLayout);
        thuTienPanelLayout.setHorizontalGroup(
            thuTienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
        );
        thuTienPanelLayout.setVerticalGroup(
            thuTienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );

        mainPanel.add(thuTienPanel, "card5");

        giaoDichPanel.setBackground(new java.awt.Color(255, 255, 255));
        giaoDichPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                giaoDichPanelComponentShown(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(952, 85));

        themChiButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        themChiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Add.png"))); // NOI18N
        themChiButton.setText("  Thêm");
        themChiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themChiButtonActionPerformed(evt);
            }
        });

        xoaChiButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        xoaChiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Delete.png"))); // NOI18N
        xoaChiButton.setText("  Xóa");
        xoaChiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaChiButtonActionPerformed(evt);
            }
        });

        suaChiButton.setText("Sửa");
        suaChiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaChiButtonActionPerformed(evt);
            }
        });

        xoaAllChiButton.setText("Xóa ALL");
        xoaAllChiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaAllChiButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshButton)
                .addGap(18, 18, 18)
                .addComponent(themChiButton)
                .addGap(33, 33, 33)
                .addComponent(xoaChiButton)
                .addGap(28, 28, 28)
                .addComponent(suaChiButton)
                .addGap(18, 18, 18)
                .addComponent(xoaAllChiButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themChiButton)
                    .addComponent(xoaChiButton)
                    .addComponent(suaChiButton)
                    .addComponent(xoaAllChiButton)
                    .addComponent(refreshButton))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        chiTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(chiTable);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Mục Chi", jPanel9);

        thuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(thuTable);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        themThuButton.setText("Thêm");
        themThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themThuButtonActionPerformed(evt);
            }
        });

        refreshThuButton.setText("Refresh");
        refreshThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshThuButtonActionPerformed(evt);
            }
        });

        suaThuButton.setText("Sửa");
        suaThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaThuButtonActionPerformed(evt);
            }
        });

        xoaThuButton.setText("Xóa");
        xoaThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaThuButtonActionPerformed(evt);
            }
        });

        xoaAllThuButton.setText("Xóa tất cả");
        xoaAllThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaAllThuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(refreshThuButton)
                .addGap(18, 18, 18)
                .addComponent(themThuButton)
                .addGap(18, 18, 18)
                .addComponent(suaThuButton)
                .addGap(18, 18, 18)
                .addComponent(xoaThuButton)
                .addGap(18, 18, 18)
                .addComponent(xoaAllThuButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themThuButton)
                    .addComponent(refreshThuButton)
                    .addComponent(suaThuButton)
                    .addComponent(xoaThuButton)
                    .addComponent(xoaAllThuButton))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Mục Thu", jPanel11);

        javax.swing.GroupLayout giaoDichPanelLayout = new javax.swing.GroupLayout(giaoDichPanel);
        giaoDichPanel.setLayout(giaoDichPanelLayout);
        giaoDichPanelLayout.setHorizontalGroup(
            giaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(giaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        giaoDichPanelLayout.setVerticalGroup(
            giaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(giaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        mainPanel.add(giaoDichPanel, "card2");

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void thoatHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatHomeButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_thoatHomeButtonActionPerformed

    private void giaoDichPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_giaoDichPanelComponentShown

    }//GEN-LAST:event_giaoDichPanelComponentShown

    private void giaoDichButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giaoDichButtonActionPerformed
        mainPanel.removeAll();
        mainPanel.add(giaoDichPanel);
        mainPanel.revalidate();
        mainPanel.repaint();    
    }//GEN-LAST:event_giaoDichButtonActionPerformed

    private void timKiemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemButtonActionPerformed
        mainPanel.removeAll();
        mainPanel.add(timKiemPanel);
        mainPanel.revalidate();
        mainPanel.repaint();  
        displayTen(logId);
        displayNgay(logId);
        displayTien(logId);
    }//GEN-LAST:event_timKiemButtonActionPerformed

    private void thongKeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thongKeButtonActionPerformed
//        setDataToChart(showTKPanel);
        mainPanel.removeAll();
        mainPanel.add(thongKePanel);
        mainPanel.revalidate();
        mainPanel.repaint();  
//        
//        String year = yearTextField.getText();
//        setDataToChartYear(showTKPanel, Integer.parseInt(year));
    }//GEN-LAST:event_thongKeButtonActionPerformed

    private void thoatTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatTGDButtonActionPerformed
//        themDialog.setVisible(false);
    }//GEN-LAST:event_thoatTGDButtonActionPerformed

    private void themTGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themTGDButtonActionPerformed
//        themGD(logId);
    }//GEN-LAST:event_themTGDButtonActionPerformed

    private void tenTKTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenTKTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tenTKTableMouseClicked

    private void tenTKTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenTKTextFieldActionPerformed
//        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(defaultTableModel);
//        tenTKTable.setRowSorter(tableRowSorter);
//        tableRowSorter.setRowFilter(RowFilter.regexFilter(tenTKTextField.getText()));
    }//GEN-LAST:event_tenTKTextFieldActionPerformed

    private void tienTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tienTKButtonActionPerformed
        try {
            findMoney(logId);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tienTKButtonActionPerformed

    private void themChiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themChiButtonActionPerformed
//        new themChiJFrame().setVisible(true);
        System.out.println("button chi"+logId);
        new themChiJFrame(logId).setVisible(true);
    }//GEN-LAST:event_themChiButtonActionPerformed

    private void tenTKTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tenTKTextFieldKeyReleased
        displayTen(logId);
//        DefaultTableModel obj = (DefaultTableModel) tenTKTable.getModel();
//        TableRowSorter<DefaultTableModel> tableRS = new TableRowSorter<>(obj);
//        tenTKTable.setRowSorter(tableRS);
//        tableRS.setRowFilter(RowFilter.regexFilter(tenTKTextField.getText()));
    }//GEN-LAST:event_tenTKTextFieldKeyReleased

    private void xoaChiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaChiButtonActionPerformed
        // TODO add your handling code here:
//        if(){};
        int row = chiTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(HomeViewPro.this, "Vui long chon user truoc", "Loi", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Ban chac chan muon xoa khong");
            if (confirm == JOptionPane.YES_OPTION){
                int userId = Integer.parseInt(String.valueOf(chiTable.getValueAt(row, 0)));
                homeViewController.deleteGiaoDichChi(userId);
                defaultTableModel.setRowCount(0);
                setTableData(homeViewController.getAllInforUser(logId));
            }
        }
    }//GEN-LAST:event_xoaChiButtonActionPerformed

    private void tenTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenTKButtonActionPerformed
        // TODO add your handling code here:
        try {
            
            findUsers(logId);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tenTKButtonActionPerformed

    private void tenTKButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tenTKButtonKeyReleased
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(defaultTableModel);
        tenTKTable.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(tenTKTextField.getText()));
    }//GEN-LAST:event_tenTKButtonKeyReleased

    private void ngayTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayTKButtonActionPerformed
        // TODO add your handling code here:
        try {
            findDate(logId);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_ngayTKButtonActionPerformed

    private void tienTKButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tienTKButtonKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tienTKButtonKeyReleased

    private void ngayTKButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ngayTKButtonKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ngayTKButtonKeyReleased

    private void tuTienTKTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tuTienTKTextFieldKeyReleased
        // TODO add your handling code here:
        displayTien(logId);
//        DefaultTableModel obj = (DefaultTableModel) tienTKTable.getModel();
//        TableRowSorter<DefaultTableModel> tableRS = new TableRowSorter<>(obj);
//        tienTKTable.setRowSorter(tableRS);
//        tableRS.setRowFilter(RowFilter.regexFilter(tuTienTKTextField.getText()));
    }//GEN-LAST:event_tuTienTKTextFieldKeyReleased

    private void denTienTKTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_denTienTKTextFieldKeyReleased
        // TODO add your handling code here:
        displayTien(logId);
//        DefaultTableModel obj = (DefaultTableModel) tienTKTable.getModel();
//        TableRowSorter<DefaultTableModel> tableRS = new TableRowSorter<>(obj);
//        tienTKTable.setRowSorter(tableRS);
//        tableRS.setRowFilter(RowFilter.regexFilter(denTienTKTextField.getText()));
    }//GEN-LAST:event_denTienTKTextFieldKeyReleased

    private void yearTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearTextFieldActionPerformed

    private void tkYearTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkYearTKButtonActionPerformed
        String year = yearTextField.getText();
        setDataToChartYear(showTKPanel, Integer.parseInt(year));
    }//GEN-LAST:event_tkYearTKButtonActionPerformed

    private void tenTKTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tenTKTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tenTKTextFieldKeyTyped

    private void xoaAllChiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaAllChiButtonActionPerformed
        // TODO add your handling code here:
        int row = chiTable.getSelectedRow();

            int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa tất cả dữ liệu không?");
            if (confirm == JOptionPane.YES_OPTION) {
                // Xóa tất cả dữ liệu trong bảng
                homeViewController.deleteAllGiaoDichChi();

                // Cập nhật bảng sau khi xóa
                defaultTableModel.setRowCount(0);
                setTableData(homeViewController.getAllInforUser(logId));
            }
     
    }//GEN-LAST:event_xoaAllChiButtonActionPerformed

    private void suaChiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaChiButtonActionPerformed
        // TODO add your handling code here:
         int row = chiTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn giao dịch trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                int giaoDichId = Integer.valueOf(String.valueOf(chiTable.getValueAt(row, 0)));
                new editChiJFrame(giaoDichId).setVisible(true);
            }
        
    }//GEN-LAST:event_suaChiButtonActionPerformed

    private void soTietKiemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soTietKiemButtonActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.add(thongKePanel);
        mainPanel.revalidate();
        mainPanel.repaint(); 
    }//GEN-LAST:event_soTietKiemButtonActionPerformed

    private void dvSinhHoatRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dvSinhHoatRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dvSinhHoatRadioButtonActionPerformed

    private void themTGDButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themTGDButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_themTGDButton1ActionPerformed

    private void thoatTGDButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatTGDButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thoatTGDButton1ActionPerformed

    private void dvSinhHoatRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dvSinhHoatRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dvSinhHoatRadioButton1ActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        defaultTableModel.setRowCount(0);
        setTableData(homeViewController.getAllInforUser(logId));
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void themThuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themThuButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("button thu " + logId);
        new themThuJFrame(logId).setVisible(true);
    }//GEN-LAST:event_themThuButtonActionPerformed

    private void refreshThuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshThuButtonActionPerformed
        // TODO add your handling code here:
        defaultTableThuModel.setRowCount(0);
        setThuTableData(homeViewController.getAllInforUserThu(logId));
    }//GEN-LAST:event_refreshThuButtonActionPerformed

    private void suaThuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaThuButtonActionPerformed
        // TODO add your handling code here:
        int row = thuTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn giao dịch trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                int giaoDichId = Integer.valueOf(String.valueOf(thuTable.getValueAt(row, 0)));
                new editThuJFrame(giaoDichId).setVisible(true);
        }
    }//GEN-LAST:event_suaThuButtonActionPerformed

    private void xoaThuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaThuButtonActionPerformed
        // TODO add your handling code here:
        int row = thuTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa không!");
            if (confirm == JOptionPane.YES_OPTION){
                int userId = Integer.parseInt(String.valueOf(thuTable.getValueAt(row, 0)));
                homeViewController.deleteGiaoDichThu(userId);
                defaultTableThuModel.setRowCount(0);
                setThuTableData(homeViewController.getAllInforUserThu(logId));
            }
        }
    }//GEN-LAST:event_xoaThuButtonActionPerformed

    private void xoaAllThuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaAllThuButtonActionPerformed
        // TODO add your handling code here:
        int row = thuTable.getSelectedRow();

            int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa tất cả dữ liệu không?");
            if (confirm == JOptionPane.YES_OPTION) {
                // Xóa tất cả dữ liệu trong bảng
                homeViewController.deleteAllGiaoDichThu();

                // Cập nhật bảng sau khi xóa
                defaultTableThuModel.setRowCount(0);
                setThuTableData(homeViewController.getAllInforUserThu(logId));
        }
    }//GEN-LAST:event_xoaAllThuButtonActionPerformed

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
            java.util.logging.Logger.getLogger(HomeViewPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeViewPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeViewPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeViewPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeViewPro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton anUongRadioButton;
    private javax.swing.JRadioButton anUongRadioButton1;
    private javax.swing.JPanel bodyThemGiaoDichPanel;
    private javax.swing.JPanel bodyThemGiaoDichPanel1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTable chiTable;
    private com.toedter.calendar.JDateChooser denNgayTKTextField;
    private javax.swing.JTextField denTienTKTextField;
    private javax.swing.JRadioButton dvSinhHoatRadioButton;
    private javax.swing.JRadioButton dvSinhHoatRadioButton1;
    private javax.swing.JDialog editChiDialog;
    private javax.swing.JTextField ghiChuTGDTextField;
    private javax.swing.JTextField ghiChuTGDTextField1;
    private javax.swing.JButton giaoDichButton;
    private javax.swing.JPanel giaoDichPanel;
    private javax.swing.JLabel hangMucButton;
    private javax.swing.JLabel hangMucButton1;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel headerThemGiaoDichPanel;
    private javax.swing.JPanel headerThemGiaoDichPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton khacRadioButton;
    private javax.swing.JRadioButton khacRadioButton1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField matHangTGDTextField;
    private javax.swing.JTextField matHangTGDTextField1;
    private javax.swing.JButton ngayTKButton;
    private javax.swing.JRadioButton quanAoRadioButton;
    private javax.swing.JRadioButton quanAoRadioButton1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton refreshThuButton;
    private javax.swing.JLabel showDateLabel;
    private javax.swing.JLabel showRealTimeLabel;
    private javax.swing.JPanel showTKPanel;
    private javax.swing.JButton soTietKiemButton;
    private javax.swing.JButton suaChiButton;
    private javax.swing.JButton suaThuButton;
    private javax.swing.JButton tenTKButton;
    private javax.swing.JTable tenTKTable;
    private javax.swing.JTextField tenTKTextField;
    private javax.swing.JTextField thanhTienTGDTextField;
    private javax.swing.JTextField thanhTienTGDTextField1;
    private javax.swing.JButton themChiButton;
    private javax.swing.JDialog themDialog;
    private javax.swing.JButton themTGDButton;
    private javax.swing.JButton themTGDButton1;
    private javax.swing.JButton themThuButton;
    private javax.swing.JButton thoatHomeButton;
    private javax.swing.JButton thoatTGDButton;
    private javax.swing.JButton thoatTGDButton1;
    private com.toedter.calendar.JDateChooser thoiGianTGDTextField;
    private com.toedter.calendar.JDateChooser thoiGianTGDTextField1;
    private javax.swing.JTable thoiGianTKTable;
    private javax.swing.JButton thongKeButton;
    private javax.swing.JPanel thongKePanel;
    private javax.swing.JTable thuTable;
    private javax.swing.JPanel thuTienPanel;
    private javax.swing.JButton tienTKButton;
    private javax.swing.JTable tienTKTable;
    private javax.swing.JButton timKiemButton;
    private javax.swing.JPanel timKiemPanel;
    private javax.swing.JButton tkYearTKButton;
    private com.toedter.calendar.JDateChooser tuNgayTKTextField;
    private javax.swing.JTextField tuTienTKTextField;
    private javax.swing.JButton xoaAllChiButton;
    private javax.swing.JButton xoaAllThuButton;
    private javax.swing.JButton xoaChiButton;
    private javax.swing.JButton xoaThuButton;
    private javax.swing.JTextField yearTextField;
    // End of variables declaration//GEN-END:variables
}
