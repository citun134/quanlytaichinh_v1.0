package com.quanlytaichinh.view;

import com.quanlytaichinh.controller.HomeViewController;
import com.quanlytaichinh.controller.LoginController;
import com.quanlytaichinh.dao.GiaoDichDao;
import com.quanlytaichinh.model.SoTietKiemModel;
import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.model.GiaoDichThuModel;
import com.quanlytaichinh.model.LaiSuatVayModel;
import com.quanlytaichinh.model.LoginModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
        
public class HomeViewPro extends javax.swing.JFrame {
    public HomeViewController homeViewController;
    public GiaoDichDao giaoDichDao;
    
    public LoginController loginController;
    
    public SoTietKiemModel soTietKiemModel;
    public LaiSuatVayModel laiSuatVayModel;
    public LoginModel loginModel;
    public GiaoDichModel giaoDichModel;
    
    public LoginView loginView;
    public DefaultTableModel defaultTableModel;
    public DefaultTableModel defaultTableThuModel;
    public DefaultTableModel defaultTableSTKModel;
    public DefaultTableModel defaultTableLSVModel;
    public DefaultTableModel timKiemTen;
    public DefaultTableModel timKiemTien;
    public DefaultTableModel timKiemNgay;
    public Timer t;
    public SimpleDateFormat simpleDateFormat;
    
    
    public int logId;
    private HomeViewPro homeViewPro; 
    
    public HomeViewPro(){}
    public HomeViewPro(LoginModel loginModel) {
        initComponents();
        setLocationRelativeTo(null);
        
        homeViewPro = this;
        
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
        displaySTK(logId);
        displayLSV(logId);
        
        thoiGianTGDTextField.setDateFormatString("yyyy-MM-dd");
//        tuNgayTKTextField.setDateFormatString("yyyy-MM-dd");
//        denNgayTKTextField.setDateFormatString("yyyy-MM-dd");
        tuNgayTKTextField.setDateFormatString("dd-MM-yyyy");
        denNgayTKTextField.setDateFormatString("dd-MM-yyyy");
        
        tkTuyChonTuDateChooser.setDateFormatString("dd-MM-yyyy");
        tkTuyChonDenDateChooser.setDateFormatString("dd-MM-yyyy");


        
        Date date = new Date();
        thoiGianTGDTextField.setDate(date);
        tuNgayTKTextField.setDate(date);
        denNgayTKTextField.setDate(date);
        tkTuyChonTuDateChooser.setDate(date);
        tkTuyChonDenDateChooser.setDate(date);

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
        
        chiTable.getColumnModel().getColumn(1).setCellRenderer(new DateRenderer());

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
        
        thuTable.getColumnModel().getColumn(1).setCellRenderer(new DateRenderer()); 

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
    
    public void setSTKTable(List<SoTietKiemModel> allGiaoDich){
        for (SoTietKiemModel giaoDich : allGiaoDich) {
            DecimalFormat df = new DecimalFormat("###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedSoTienGui = df.format(giaoDich.getSoTienGui());
            String formattedSoTienLaiNhanDuoc = df.format(giaoDich.getSoTienLaiNhanDuoc());
            String formattedTongTienNhanDuoc = df.format(giaoDich.getTongTienNhanDuoc());
            
            defaultTableSTKModel.addRow(new Object[]{giaoDich.getTietKiemId(), giaoDich.getNgayGui(),
                    giaoDich.getTenNganHang(), formattedSoTienGui, giaoDich.getLaiSuatGui(),
                    formattedSoTienLaiNhanDuoc, formattedTongTienNhanDuoc, 
                    giaoDich.getKyHan()
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

    public void thongKeGiaoDichChi(JPanel jpanel, int year, String displayMode) {
        // Hiển thị 12 columns hoặc chia thành 4 quý
        List<GiaoDichModel> listItem = homeViewController.thongKeGiaoDichChi(logId, year);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian",
                "Tiền", dataset);

        // Create a map to store the data for each period (month or quarter)
        Map<String, Long> periodData = new HashMap<>();

        if ("Tháng".equals(displayMode)) {
            // Hiển thị 12 tháng
            for (int month = 1; month <= 12; month++) {
                periodData.put("tháng " + month, 0L);
            }

            // Populate the map with actual data
            for (GiaoDichModel item : listItem) {
                int month = item.getMonth();
                long totalMoney = item.getTotalMoney();
                String key = "tháng " + month;

                // Add the totalMoney to the corresponding month
                periodData.put(key, periodData.getOrDefault(key, 0L) + totalMoney);
            }

            // Add the data to the dataset
            for (int month = 1; month <= 12; month++) {
                String key = "tháng " + month;
                dataset.addValue(periodData.get(key), "Số tiền", key);
            }
        } else if ("Quý".equals(displayMode)) {
            // Chia thành 4 quý
            for (int quarter = 1; quarter <= 4; quarter++) {
                periodData.put("quý " + quarter, 0L);
            }

            // Populate the map with actual data
            for (GiaoDichModel item : listItem) {
                int month = item.getMonth();
                long totalMoney = item.getTotalMoney();
                int quarter = (month - 1) / 3 + 1;
                String key = "quý " + quarter;

                // Add the totalMoney to the corresponding quarter
                periodData.put(key, periodData.getOrDefault(key, 0L) + totalMoney);
            }

            // Add the data to the dataset
            for (int quarter = 1; quarter <= 4; quarter++) {
                String key = "quý " + quarter;
                dataset.addValue(periodData.get(key), "Số tiền", key);
            }
        }

        ChartPanel chartPanel = new ChartPanel(chart);

        // Thêm chartPanel vào JPanel
        jpanel.removeAll(); // Xóa bất kỳ thành phần hiện có trong JPanel
        jpanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để đặt ChartPanel
        jpanel.add(chartPanel, BorderLayout.CENTER); // Thêm ChartPanel vào JPanel ở vị trí trung tâm
        jpanel.revalidate(); // Cập nhật lại JPanel để hiển thị biểu đồ
    }

    
    public void thongKeGiaoDichChiSua(JPanel jpanel, String tu, String den) {
        // Hiển thị dữ liệu theo khoảng thời gian từ 'tu' đến 'den'
        List<GiaoDichModel> listItem = homeViewController.thongKeGiaoDichChiSua(logId, tu, den);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian",
                "Tiền", dataset);

        // Create a map to store the data for each month and year
        Map<String, Long> monthYearData = new TreeMap<>(new MonthYearComparator());

        // Populate the map with actual data
        for (GiaoDichModel item : listItem) {
            int year = item.getYear();
            int month = item.getMonth();
            long totalMoney = item.getTotalMoney();
            String key = String.format("%d/%02d", month, year);

            // Add the totalMoney to the corresponding month and year
            monthYearData.put(key, monthYearData.getOrDefault(key, 0L) + totalMoney);
        }

        // Add the data to the dataset
        for (String monthYear : monthYearData.keySet()) {
            dataset.addValue(monthYearData.get(monthYear), "Số tiền", monthYear);
        }

        ChartPanel chartPanel = new ChartPanel(chart);

        // Thêm chartPanel vào JPanel
        jpanel.removeAll(); // Xóa bất kỳ thành phần hiện có trong JPanel
        jpanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để đặt ChartPanel
        jpanel.add(chartPanel, BorderLayout.CENTER); // Thêm ChartPanel vào JPanel ở vị trí trung tâm
        jpanel.revalidate(); // Cập nhật lại JPanel để hiển thị biểu đồ
    }
    
    public void thongKeGiaoDichThuSua(JPanel jpanel, String tu, String den) {
        // Hiển thị dữ liệu theo khoảng thời gian từ 'tu' đến 'den'
        List<GiaoDichModel> listItem = homeViewController.thongKeGiaoDichThuSua(logId, tu, den);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian",
                "Tiền", dataset);

        // Create a map to store the data for each month and year
        Map<String, Long> monthYearData = new TreeMap<>(new MonthYearComparator());

        // Populate the map with actual data
        for (GiaoDichModel item : listItem) {
            int year = item.getYear();
            int month = item.getMonth();
            long totalMoney = item.getTotalMoney();
            String key = String.format("%d/%02d", month, year);

            // Add the totalMoney to the corresponding month and year
            monthYearData.put(key, monthYearData.getOrDefault(key, 0L) + totalMoney);
        }

        // Add the data to the dataset
        for (String monthYear : monthYearData.keySet()) {
            dataset.addValue(monthYearData.get(monthYear), "Số tiền", monthYear);
        }

        ChartPanel chartPanel = new ChartPanel(chart);

        // Thêm chartPanel vào JPanel
        jpanel.removeAll(); // Xóa bất kỳ thành phần hiện có trong JPanel
        jpanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để đặt ChartPanel
        jpanel.add(chartPanel, BorderLayout.CENTER); // Thêm ChartPanel vào JPanel ở vị trí trung tâm
        jpanel.revalidate(); // Cập nhật lại JPanel để hiển thị biểu đồ
    }
    
    public void thongKeGiaoDichThuChiSua(JPanel jpanel, String tu, String den) {
        // Hiển thị dữ liệu theo khoảng thời gian từ 'tu' đến 'den'
        List<GiaoDichModel> listItem = homeViewController.thongKeGiaoDichThuChiSua(logId, tu, den);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian",
                "Tiền", dataset);

        // Create a map to store the data for each month and year
        Map<String, Long> monthYearData = new TreeMap<>(new MonthYearComparator());

        // Populate the map with actual data
        for (GiaoDichModel item : listItem) {
            int year = item.getYear();
            int month = item.getMonth();
            long totalMoney = item.getTotalMoney();
            String key = String.format("%d/%02d", month, year);

            // Add the totalMoney to the corresponding month and year
            monthYearData.put(key, monthYearData.getOrDefault(key, 0L) + totalMoney);
        }

        // Add the data to the dataset
        for (String monthYear : monthYearData.keySet()) {
            dataset.addValue(monthYearData.get(monthYear), "Số tiền", monthYear);
        }

        ChartPanel chartPanel = new ChartPanel(chart);

        // Thêm chartPanel vào JPanel
        jpanel.removeAll(); // Xóa bất kỳ thành phần hiện có trong JPanel
        jpanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để đặt ChartPanel
        jpanel.add(chartPanel, BorderLayout.CENTER); // Thêm ChartPanel vào JPanel ở vị trí trung tâm
        jpanel.revalidate(); // Cập nhật lại JPanel để hiển thị biểu đồ
    }

    class MonthYearComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            String[] parts1 = o1.split("/");
            String[] parts2 = o2.split("/");

            int year1 = Integer.parseInt(parts1[1]);
            int month1 = Integer.parseInt(parts1[0]);
            int year2 = Integer.parseInt(parts2[1]);
            int month2 = Integer.parseInt(parts2[0]);

            if (year1 != year2) {
                return Integer.compare(year1, year2);
            }

            // If years are the same, compare months
            return Integer.compare(month1, month2);
        }
    }
    
    
//    public void thongKeSoTietKiem(JPanel jpanel, String tu, String den) {
//        List<SoTietKiemModel> soTietKiemList = homeViewController.layDanhSachSoTietKiem(logId, tu, den);
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian", "Tiền", dataset);
//
//        // Create a map to store the data for each month and year
//        Map<String, Double> monthYearData = new TreeMap<>(new MonthYearComparator());
//
//        // Populate the map with actual data
//        for (SoTietKiemModel item : soTietKiemList) {
//            int year = item.getYear();
//            int month = item.getMonth();
//            double totalMoney = calculateTotalMoneyByMonthAndYear(soTietKiemList, month, year);
//            String key = String.format("%d/%02d", month, year);
//
//            // Add the totalMoney to the corresponding month and year
//            monthYearData.put(key, totalMoney);
//        }
//
//        // Add the data to the dataset
//        for (String monthYear : monthYearData.keySet()) {
//            dataset.addValue(monthYearData.get(monthYear), "Số tiền", monthYear);
//        }
//
//        ChartPanel chartPanel = new ChartPanel(chart);
//
//        // Thêm chartPanel vào JPanel
//        jpanel.removeAll(); // Xóa bất kỳ thành phần hiện có trong JPanel
//        jpanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để đặt ChartPanel
//        jpanel.add(chartPanel, BorderLayout.CENTER); // Thêm ChartPanel vào JPanel ở vị trí trung tâm
//        jpanel.revalidate(); // Cập nhật lại JPanel để hiển thị biểu đồ
//    }
    
    private boolean isDateBeforeOrEqual(int year1, int month1, int year2, int month2, int day2) {
    if (year1 < year2) {
        return true;
    } else if (year1 == year2) {
        if (month1 < month2) {
            return true;
        } else if (month1 == month2) {
            return day2 >= 1; // Adjust based on your requirements
        }
    }
    return false;
}
    
    public void thongKeSoTietKiemYear(JPanel jpanel, int yearInput, String displayMode) {
    List<SoTietKiemModel> soTietKiemList = homeViewController.layDanhSachSoTietKiemToanBo(logId);

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian", "Tiền", dataset);

    // Xác định tháng bắt đầu và kết thúc
    int startYear = yearInput;
    int startMonth = 1;
    int startDay = 1;
    int endYear = yearInput;
    int endMonth = 12;

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
    int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    // Tạo map để lưu trữ dữ liệu
    Map<String, Double> dataMap = new TreeMap<>(new MonthYearComparator());

    // Lấy dữ liệu theo tháng hoặc quý
    if ("Tháng".equals(displayMode)) {
        // Lặp qua tất cả các tháng trong khoảng thời gian đã chọn
        for (int year = startYear; year <= endYear; year++) {
            for (int month = startMonth; month <= endMonth; month++) {
                String key = String.format("%d/%02d", month, year);
                double totalMoney = 0.0;

                // Chỉ tính totalMoney cho tháng hiện tại hoặc trước tháng hiện tại
                if (year < currentYear || (year == currentYear && month <= currentMonth)) {
                    // Chỉ tính totalMoney nếu tháng và năm không lớn hơn ngày hiện tại
                    if (!(year == currentYear && month > currentMonth) || (year == currentYear && month == currentMonth && startDay <= currentDay)) {
                        totalMoney = calculateTotalMoneyByMonthAndYear(soTietKiemList, month, year, currentDay);
                    }
                }

                dataMap.put(key, totalMoney);
            }
        }
    } else if ("Quý".equals(displayMode)) {
    // Lặp qua tất cả các quý trong khoảng thời gian đã chọn
    for (int year = startYear; year <= endYear; year++) {
        for (int quarter = 1; quarter <= 4; quarter++) {
            String key = String.format("Quý %d", quarter);
            int startQuarterMonth = (quarter - 1) * 3 + 1;
            int endQuarterMonth = quarter * 3;

            // Ensure endQuarterMonth does not exceed 12
            if (endQuarterMonth > 12) {
                endQuarterMonth = 12;
            }

            // Calculate totalMoney for the current quarter and year
            double totalMoney = 0.0;

            // Check if the current date is after the specified date or if the quarter is in the future
            if (isDateBeforeOrEqual(year, endQuarterMonth, currentYear, currentMonth, currentDay) || (year > currentYear || (year == currentYear && endQuarterMonth > currentMonth))) {
                // Loop through the months within the quarter
                for (int month = startQuarterMonth; month <= Math.min(endQuarterMonth, currentMonth); month++) {
                    // Calculate totalMoney for each month within the quarter
                    totalMoney += calculateTotalMoneyByMonthAndYear(soTietKiemList, month, year, currentDay);
                }

                // Add the calculated value to the dataset
                dataset.addValue(totalMoney, "Số tiền", key);
            }
        }
    }
} else {
        // Xử lý trường hợp không hợp lệ (displayMode khác "Tháng" hoặc "Quý")
        throw new IllegalArgumentException("displayMode must be either \"Tháng\" or \"Quý\"");
    }

    // Thêm dữ liệu vào dataset
    for (String key : dataMap.keySet()) {
        double totalMoney = dataMap.get(key);
        dataset.addValue(totalMoney, "Số tiền", key);
    }

    // Sử dụng StandardCategoryToolTipGenerator để tránh số mũ (e)
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    StandardCategoryToolTipGenerator toolTipGenerator = new StandardCategoryToolTipGenerator(
            StandardCategoryToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT_STRING,
            new DecimalFormat("###,###,###,###")
    );
    plot.getRenderer().setBaseToolTipGenerator(toolTipGenerator);

    ChartPanel chartPanel = new ChartPanel(chart);

    // Thêm chartPanel vào JPanel
    jpanel.removeAll();
    jpanel.setLayout(new BorderLayout());
    jpanel.add(chartPanel, BorderLayout.CENTER);
    jpanel.revalidate();
}


    public void thongKeLaiSuatVayYear(JPanel jpanel, int year, String displayMode) {
    List<LaiSuatVayModel> laiSuatVayList = homeViewController.layDanhSachLaiSuatVayToanBo(logId);

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian", "Tiền", dataset);

    // Xác định tháng bắt đầu và kết thúc
    int startYear = year;
    int startMonth = 1;

    int endYear = 12;
    int endMonth = year;

    // Tạo map để lưu trữ dữ liệu
    Map<String, Double> dataMap = new TreeMap<>(new MonthYearComparator());

    // Lấy dữ liệu theo tháng hoặc quý
    if ("Tháng".equals(displayMode)) {
        // Lặp qua tất cả các tháng trong khoảng thời gian đã chọn
//        for (int year = startYear; year <= endYear; year++) {
            for (int month = startMonth; month <= 12; month++) {
                String key = String.format("%d/%02d", month, year);
                double totalMoney = calculateTotalMoneyByMonthAndYearLSV(laiSuatVayList, month, year);
                dataMap.put(key, totalMoney);   

                if (year == endYear && month == endMonth) {
                    break; // Dừng vòng lặp khi đã đến tháng cuối cùng
                }
//            }
            startMonth = 1; // Đặt lại tháng bắt đầu thành 1 cho năm tiếp theo
        }
    } else if ("Quý".equals(displayMode)) {
    // Lặp qua tất cả các quý trong khoảng thời gian đã chọn
//    for (int currentYear = startYear; currentYear <= endYear; currentYear++) {
        for (int quarter = 1; quarter <= 4; quarter++) {
            String key = String.format("Quý %d", quarter);
            int startQuarterMonth = (quarter - 1) * 3 + 1;
            int endQuarterMonth = quarter * 3;
            
            // Ensure endQuarterMonth does not exceed 12
            if (endQuarterMonth > 12) {
                endQuarterMonth = 12;
            }
            
            // Calculate totalMoney for the current quarter and year
            double totalMoney = calculateTotalMoneyByQuarterLSV(laiSuatVayList, startQuarterMonth, endQuarterMonth, year);
            
            // Add the calculated value to the dataset
            dataset.addValue(totalMoney, "Số tiền", key);

            if (year == endYear && quarter == (endMonth - 1) / 3 + 1) {
                break; // Dừng vòng lặp khi đã đến quý cuối cùng
//            }
        }
    }
}
 else {
        // Xử lý trường hợp không hợp lệ (displayMode khác "Tháng" hoặc "Quý")
        throw new IllegalArgumentException("displayMode must be either \"Tháng\" or \"Quý\"");
    }

    // Thêm dữ liệu vào dataset
    for (String key : dataMap.keySet()) {
        double totalMoney = dataMap.get(key);
        dataset.addValue(totalMoney, "Số tiền", key);
    }

    // Sử dụng StandardCategoryToolTipGenerator để tránh số mũ (e)
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    StandardCategoryToolTipGenerator toolTipGenerator = new StandardCategoryToolTipGenerator(
            StandardCategoryToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT_STRING,
            new DecimalFormat("###,###,###,###")
    );
    plot.getRenderer().setBaseToolTipGenerator(toolTipGenerator);

    ChartPanel chartPanel = new ChartPanel(chart);

    // Thêm chartPanel vào JPanel
    jpanel.removeAll();
    jpanel.setLayout(new BorderLayout());
    jpanel.add(chartPanel, BorderLayout.CENTER);
    jpanel.revalidate();
}    
    
    
private double calculateTotalMoneyByQuarter(List<SoTietKiemModel> soTietKiemList, int startMonth, int endMonth, int year) {
    double totalMoney = 0;
    int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    // Iterate only over the months within the specified quarter
    for (int month = startMonth; month <= endMonth && month <= 12; month++) {
        totalMoney += calculateTotalMoneyByMonthAndYear(soTietKiemList, month, year, currentDay);
    }
    return totalMoney;
}
    
    private double calculateTotalMoneyByQuarterLSV(List<LaiSuatVayModel> laiSuatVayList, int startMonth, int endMonth, int year) {
        double totalMoney = 0;
        for (int month = startMonth; month <= endMonth; month++) {
            totalMoney += calculateTotalMoneyByMonthAndYearLSV(laiSuatVayList, month, year);
        }
        return totalMoney;
    }
    
    public void thongKeSoTietKiem(JPanel jpanel, String tu, String den) {
    List<SoTietKiemModel> soTietKiemList = homeViewController.layDanhSachSoTietKiemToanBo(logId);

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian", "Tiền", dataset);

    // Xác định tháng bắt đầu và kết thúc
    // Lấy tháng và năm từ chuỗi tu theo định dạng yyyy-MM-dd
    int startYear = Integer.parseInt(tu.substring(0, 4));
    int startMonth = Integer.parseInt(tu.substring(5, 7));
    int startDay = 1; 
    // Lấy tháng và năm từ chuỗi den theo định dạng yyyy-MM-dd
    int endYear = Integer.parseInt(den.substring(0, 4));
    int endMonth = Integer.parseInt(den.substring(5, 7));
    int endDay = Integer.parseInt(den.substring(8, 10));

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
    int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    // Tạo map để lưu trữ dữ liệu cho từng tháng và năm
    Map<String, Double> monthYearData = new TreeMap<>(new MonthYearComparator());

    // Lặp qua tất cả các tháng trong khoảng thời gian đã chọn
    for (int year = startYear; year <= endYear; year++) {
        for (int month = startMonth; month <= 12; month++) {
            String key = String.format("%d/%02d", month, year);
            double totalMoney = 0.0;

            // Chỉ tính totalMoney cho tháng hiện tại hoặc trước tháng hiện tại
            if (year < endYear || (year == endYear && month <= currentMonth)) {
                // Chỉ tính totalMoney nếu tháng và năm không lớn hơn ngày hiện tại
                if (!(year == currentYear && month > currentMonth) || (year == currentYear && month == currentMonth && startDay <= currentDay)) {
                    totalMoney = calculateTotalMoneyByMonthAndYear(soTietKiemList, month, year, currentDay);
                }
            }

            monthYearData.put(key, totalMoney);

            if (year == endYear && month == endMonth) {
                break; // Dừng vòng lặp khi đã đến tháng cuối cùng
            }
        }
        startMonth = 1; // Đặt lại tháng bắt đầu thành 1 cho năm tiếp theo
    }

    // Add the data to the dataset
    for (String monthYear : monthYearData.keySet()) {
        double totalMoney = monthYearData.get(monthYear);
        dataset.addValue(totalMoney, "Số tiền", monthYear);
    }

    // Sử dụng StandardCategoryToolTipGenerator để tránh số mũ (e)
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    StandardCategoryToolTipGenerator toolTipGenerator = new StandardCategoryToolTipGenerator(
            StandardCategoryToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT_STRING,
            new DecimalFormat("###,###,###,###")
    );
    plot.getRenderer().setBaseToolTipGenerator(toolTipGenerator);

    ChartPanel chartPanel = new ChartPanel(chart);

    // Thêm chartPanel vào JPanel
    jpanel.removeAll(); // Xóa bất kỳ thành phần hiện có trong JPanel
    jpanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để đặt ChartPanel
    jpanel.add(chartPanel, BorderLayout.CENTER); // Thêm ChartPanel vào JPanel ở vị trí trung tâm
    jpanel.revalidate(); // Cập nhật lại JPanel để hiển thị biểu đồ
}

public double calculateTotalMoneyByMonthAndYear(List<SoTietKiemModel> list, int targetMonth, int targetYear, int currentDay) {
    double totalMoney = 0;

    for (SoTietKiemModel item : list) {
        int startMonth = item.getMonth();
        int startYear = item.getYear();
        double kyHan = item.getKyHan();
        double tongTienNhanDuoc = item.getSoTienLaiNhanDuoc();
        int itemDay = item.getDay();

        // Tính số tháng tăng dần dựa trên kỳ hạn
        for (int i = 0; i < kyHan; i++) {
            int currentMonth = startMonth + 1 + i;
            int currentYear = startYear;
            // Nếu vượt quá 12 tháng, chuyển sang năm tiếp theo
            if (currentMonth > 12) {
                currentMonth -= 12;
                currentYear++;
            }

            // Kiểm tra xem có phải tháng và năm cần tính không
            if (currentMonth == targetMonth && currentYear == targetYear && itemDay <= currentDay) {
                totalMoney += tongTienNhanDuoc;
                break; // Dừng vòng lặp khi đã cộng dồn vào tổng
            }
        }
    }

    return totalMoney;
}

    
    public void thongKeLaiSuatVay(JPanel jpanel, String tu, String den) {
        List<LaiSuatVayModel> soTietKiemList = homeViewController.layDanhSachLaiSuatVayToanBo(logId);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian", "Tiền", dataset);

        // Xác định tháng bắt đầu và kết thúc
        // Lấy tháng và năm từ chuỗi tu theo định dạng yyyy-MM-dd
        int startYear = Integer.parseInt(tu.substring(0, 4));
        int startMonth = Integer.parseInt(tu.substring(5, 7));

        // Lấy tháng và năm từ chuỗi den theo định dạng yyyy-MM-dd
        int endYear = Integer.parseInt(den.substring(0, 4));
        int endMonth = Integer.parseInt(den.substring(5, 7));


        // Tạo map để lưu trữ dữ liệu cho từng tháng và năm
        Map<String, Double> monthYearData = new TreeMap<>(new MonthYearComparator());

        // Lặp qua tất cả các tháng trong khoảng thời gian đã chọn
        for (int year = startYear; year <= endYear; year++) {
            for (int month = startMonth; month <= 12; month++) {
                String key = String.format("%d/%02d", month, year);
                double totalMoney = calculateTotalMoneyByMonthAndYearLSV(soTietKiemList, month, year);
                monthYearData.put(key, totalMoney);

                if (year == endYear && month == endMonth) {
                    break; // Dừng vòng lặp khi đã đến tháng cuối cùng
                }
            }
            startMonth = 1; // Đặt lại tháng bắt đầu thành 1 cho năm tiếp theo
        }

        // Add the data to the dataset
//        for (String monthYear : monthYearData.keySet()) {
//            dataset.addValue(monthYearData.get(monthYear), "Số tiền", monthYear);
//        }

        //DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###.##"); // Định dạng số không có dấu phẩy và không có số thập phân
        for (String monthYear : monthYearData.keySet()) {
        double totalMoney = monthYearData.get(monthYear);
        dataset.addValue(totalMoney, "Số tiền", monthYear);
    }

    // Sử dụng StandardCategoryToolTipGenerator để tránh số mũ (e)
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        StandardCategoryToolTipGenerator toolTipGenerator = new StandardCategoryToolTipGenerator(
                StandardCategoryToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT_STRING,
                new DecimalFormat("###,###,###,###")
        );
        plot.getRenderer().setBaseToolTipGenerator(toolTipGenerator);



        ChartPanel chartPanel = new ChartPanel(chart);

        // Thêm chartPanel vào JPanel
        jpanel.removeAll(); // Xóa bất kỳ thành phần hiện có trong JPanel
        jpanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để đặt ChartPanel
        jpanel.add(chartPanel, BorderLayout.CENTER); // Thêm ChartPanel vào JPanel ở vị trí trung tâm
        jpanel.revalidate(); // Cập nhật lại JPanel để hiển thị biểu đồ
    }


    public double calculateTotalMoneyByMonthAndYearLSV(List<LaiSuatVayModel> list, int targetMonth, int targetYear) {
        double totalMoney = 0;

        for (LaiSuatVayModel item : list) {
            int startMonth = item.getMonth();
            int startYear = item.getYear();
            double kyHan = item.getThoiGianVay();
            double tongTienNhanDuoc = item.getSoTienPhaiTraHangThang();

            // Tính số tháng tăng dần dựa trên kỳ hạn
            for (int i = 0; i < kyHan; i++) {
                int currentMonth = startMonth + 1 + i;
                int currentYear = startYear;

                // Nếu vượt quá 12 tháng, chuyển sang năm tiếp theo
                if (currentMonth > 12) {
                    currentMonth -= 12;
                    currentYear++;
                }

                // Kiểm tra xem có phải tháng và năm cần tính không
                if (currentMonth == targetMonth && currentYear == targetYear) {
                    totalMoney += tongTienNhanDuoc;
                    break;  // Dừng vòng lặp khi đã cộng dồn vào tổng
                }
            }
        }

        return totalMoney;
    }
    
    public void thongKeGiaoDichThu(JPanel jpanel, int year, String displayMode) {
        //Hiển thị 12 columns
        List<GiaoDichModel> listItem = homeViewController.thongKeGiaoDichThu(logId, year);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian",
                "Tiền", dataset);

        // Create a map to store the data for each period (month or quarter)
        Map<String, Long> periodData = new HashMap<>();

        if ("Tháng".equals(displayMode)) {
            // Hiển thị 12 tháng
            for (int month = 1; month <= 12; month++) {
                periodData.put("tháng " + month, 0L);
            }

            // Populate the map with actual data
            for (GiaoDichModel item : listItem) {
                int month = item.getMonth();
                long totalMoney = item.getTotalMoney();
                String key = "tháng " + month;

                // Add the totalMoney to the corresponding month
                periodData.put(key, periodData.getOrDefault(key, 0L) + totalMoney);
            }

            // Add the data to the dataset
            for (int month = 1; month <= 12; month++) {
                String key = "tháng " + month;
                dataset.addValue(periodData.get(key), "Số tiền", key);
            }
        } else if ("Quý".equals(displayMode)) {
            // Chia thành 4 quý
            for (int quarter = 1; quarter <= 4; quarter++) {
                periodData.put("quý " + quarter, 0L);
            }

            // Populate the map with actual data
            for (GiaoDichModel item : listItem) {
                int month = item.getMonth();
                long totalMoney = item.getTotalMoney();
                int quarter = (month - 1) / 3 + 1;
                String key = "quý " + quarter;

                // Add the totalMoney to the corresponding quarter
                periodData.put(key, periodData.getOrDefault(key, 0L) + totalMoney);
            }

            // Add the data to the dataset
            for (int quarter = 1; quarter <= 4; quarter++) {
                String key = "quý " + quarter;
                dataset.addValue(periodData.get(key), "Số tiền", key);
            }
        }

        ChartPanel chartPanel = new ChartPanel(chart);

        // Thêm chartPanel vào JPanel
        jpanel.removeAll(); // Xóa bất kỳ thành phần hiện có trong JPanel
        jpanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để đặt ChartPanel
        jpanel.add(chartPanel, BorderLayout.CENTER); // Thêm ChartPanel vào JPanel ở vị trí trung tâm
        jpanel.revalidate(); // Cập nhật lại JPanel để hiển thị biểu đồ
    }
    
    public void thongKeGiaoDichThuChi(JPanel jpanel, int year, String displayMode) {
        //Hiển thị 12 columns
        List<GiaoDichModel> listItem = homeViewController.thongKeGiaoDichThuChi(logId, year);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ", "Thời Gian",
                "Tiền", dataset);

        // Create a map to store the data for each period (month or quarter)
        Map<String, Long> periodData = new HashMap<>();

        if ("Tháng".equals(displayMode)) {
            // Hiển thị 12 tháng
            for (int month = 1; month <= 12; month++) {
                periodData.put("tháng " + month, 0L);
            }

            // Populate the map with actual data
            for (GiaoDichModel item : listItem) {
                int month = item.getMonth();
                long totalMoney = item.getTotalMoney();
                String key = "tháng " + month;

                // Add the totalMoney to the corresponding month
                periodData.put(key, periodData.getOrDefault(key, 0L) + totalMoney);
            }

            // Add the data to the dataset
            for (int month = 1; month <= 12; month++) {
                String key = "tháng " + month;
                dataset.addValue(periodData.get(key), "Số tiền", key);
            }
        } else if ("Quý".equals(displayMode)) {
            // Chia thành 4 quý
            for (int quarter = 1; quarter <= 4; quarter++) {
                periodData.put("quý " + quarter, 0L);
            }

            // Populate the map with actual data
            for (GiaoDichModel item : listItem) {
                int month = item.getMonth();
                long totalMoney = item.getTotalMoney();
                int quarter = (month - 1) / 3 + 1;
                String key = "quý " + quarter;

                // Add the totalMoney to the corresponding quarter
                periodData.put(key, periodData.getOrDefault(key, 0L) + totalMoney);
            }

            // Add the data to the dataset
            for (int quarter = 1; quarter <= 4; quarter++) {
                String key = "quý " + quarter;
                dataset.addValue(periodData.get(key), "Số tiền", key);
            }
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
        
        tenTKTable.getColumnModel().getColumn(1).setCellRenderer(new DateRenderer());

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
        
        tienTKTable.getColumnModel().getColumn(1).setCellRenderer(new DateRenderer());
        

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
        
//        thoiGianTKTable.getColumnModel().getColumn(2).setCellRenderer(new DateRenderer());
        defaultTableModelTen.addColumn("ID");
        defaultTableModelTen.addColumn("Thời Gian");
        defaultTableModelTen.addColumn("Mặt Hàng");
        defaultTableModelTen.addColumn("Thành Tiền");
        defaultTableModelTen.addColumn("Ghi Chú");
        defaultTableModelTen.addColumn("Hạng Mục");

        thoiGianTKTable.getColumnModel().getColumn(1).setCellRenderer(new DateRenderer());
        
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
   
    public void displaySTK(int accountId) {
       
    // Create a DefaultTableModel for the second table (tenTKTable)
    defaultTableSTKModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
        soTietKiemTable.setModel(defaultTableSTKModel);

        defaultTableSTKModel.addColumn("ID");
        defaultTableSTKModel.addColumn("Ngày Gửi");
        defaultTableSTKModel.addColumn("Tên Ngân Hàng");
        defaultTableSTKModel.addColumn("Số Tiền Gửi");
        defaultTableSTKModel.addColumn("Lãi Suất Gửi");
        defaultTableSTKModel.addColumn("Số Tiền Lãi Nhận Được");
        defaultTableSTKModel.addColumn("Tổng Tiền Nhận Được");
        defaultTableSTKModel.addColumn("Kỳ Hạn");
        
        soTietKiemTable.getColumnModel().getColumn(1).setCellRenderer(new DateRenderer());

        // Get the data from your controller or data source
        List<SoTietKiemModel> allGiaoDich = homeViewController.getAllInforUserSTK(accountId);

        // Populate both tables with the data
        for (SoTietKiemModel giaoDich : allGiaoDich) {
            DecimalFormat df = new DecimalFormat("###,###,###,###"); // Định dạng số theo dấu phẩy
            String formattedSoTienGui = df.format(giaoDich.getSoTienGui());
            String formattedSoTienLaiNhanDuoc = df.format(giaoDich.getSoTienLaiNhanDuoc());
            String formattedTongTienNhanDuoc = df.format(giaoDich.getTongTienNhanDuoc());
            
            defaultTableSTKModel.addRow(new Object[]{giaoDich.getTietKiemId(), giaoDich.getNgayGui(),
                    giaoDich.getTenNganHang(), formattedSoTienGui, giaoDich.getLaiSuatGui(),
                    formattedSoTienLaiNhanDuoc, formattedTongTienNhanDuoc, 
                    giaoDich.getKyHan()
            });
        }
    }
    
    public void displayLSV(int accountId) {
       
    // Create a DefaultTableModel for the second table (tenTKTable)
    defaultTableLSVModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
        laiSuatVayTable.setModel(defaultTableLSVModel);

    
        defaultTableLSVModel.addColumn("ID");
        defaultTableLSVModel.addColumn("Tên Ngân Hàng");
        defaultTableLSVModel.addColumn("Giá Trị BĐS");
        defaultTableLSVModel.addColumn("Số Tiền Vay");
        defaultTableLSVModel.addColumn("Thời Gian Vay");
        defaultTableLSVModel.addColumn("Lãi Suất");
        defaultTableLSVModel.addColumn("Ngày Giải Ngân");
        defaultTableLSVModel.addColumn("Số Tiền Trả Mỗi Tháng");
        defaultTableLSVModel.addColumn("Tổng Số Tiền ");

        laiSuatVayTable.getColumnModel().getColumn(6).setCellRenderer(new DateRenderer());

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
    
    public void updateTableChi() {
        defaultTableLSVModel.setRowCount(0);
        setTableData(homeViewController.getAllInforUser(logId));
    }
    
    public static void openFile(String file){
        try{
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    
    public static void exportTableToExcel(JTable table) {
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(null);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".csv");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Sheet1");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table.getColumnName(i));
                }

                for (int j = 0; j < table.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1); // Skip header row
                    for (int k = 0; k < table.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table.getValueAt(j, k) != null) {
                            cell.setCellValue(table.getValueAt(j, k).toString());
                        }
                    }
                }

                try (FileOutputStream out = new FileOutputStream(saveFile)) {
                    wb.write(out);
                }
                
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }
    
    public void refreshTableChiData() {
        defaultTableModel.setRowCount(0);
        setTableData(homeViewController.getAllInforUser(logId));
    }
    
    public void refreshTableThuData() {
        defaultTableThuModel.setRowCount(0);
        setThuTableData(homeViewController.getAllInforUserThu(logId));
    }
    
    public void refreshTableSTKData() {
        defaultTableSTKModel.setRowCount(0);
        setSTKTable(homeViewController.getAllInforUserSTK(logId));
    }
    
    public void refreshTableLSVData() {
        defaultTableLSVModel.setRowCount(0);
        setLSVTable(homeViewController.getAllInforUserLSV(logId));
    }
    
    private static class DateRenderer extends DefaultTableCellRenderer {
        private final SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        private final SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        @Override
        protected void setValue(Object value) {
            try {
                if (value instanceof String) {
                    // Chuyển đổi chuỗi ngày từ MySQL Date sang Date
                    Date date = mysqlDateFormat.parse((String) value);

                    // Chuyển đổi Date sang chuỗi ngày theo định dạng mong muốn
                    value = desiredDateFormat.format(date);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            super.setValue(value);
        }
    }
    
    private void displayInformationInTable(SoTietKiemModel soTietKiemModel) {
    DefaultTableModel model = (DefaultTableModel) inforSTKjTable.getModel();

    // Check if the column names already match, if not, set them
    if (!model.getColumnName(0).equals("Số Lần") || !model.getColumnName(1).equals("Ngày Gửi") || !model.getColumnName(2).equals("Số Tiền Lãi Nhận Được")) {
        model.setColumnIdentifiers(new Object[]{"Số Lần", "Ngày Gửi", "Số Tiền Lãi Nhận Được"}); // Set column names
    }

    // Get the initial values
    String ngayGuiString = soTietKiemModel.getNgayGui();
    double soTienLai = soTietKiemModel.getSoTienLaiNhanDuoc();
    int kyHan = (int) soTietKiemModel.getKyHan();

    // Ensure ngayGuiString is not null
    if (ngayGuiString != null) {
        // Parse ngayGuiString to LocalDate
        LocalDate ngayGuiDate = parseLocalDate(ngayGuiString);

        // Proceed only if ngayGuiDate is not null
        if (ngayGuiDate != null) {
            // Get today's date
            LocalDate today = LocalDate.now();

            // Previous date for comparison
            LocalDate previousDate = ngayGuiDate;

            // Add rows based on kyHan, checking for exceeding current date and 1 month gap
            for (int i = 1; i <= kyHan; i++) {
                // Calculate the incremented date
                LocalDate incrementedDate = ngayGuiDate.plusMonths(i);

                // Check if date exceeds today or less than 1 month gap
                if (incrementedDate.isAfter(today) || (incrementedDate.isBefore(previousDate) && !incrementedDate.isEqual(previousDate.plusMonths(1)))) {
                    model.setValueAt("", i-1, 0); // Set empty value for column 0
                    model.setValueAt("", i-1, 1); // Set empty value for column 1
                    model.setValueAt("", i-1, 2); // Set empty value for column 2
                    
                } else {
                    // Set values directly in the table model
                    model.setValueAt(i, i - 1, 0);
                    model.setValueAt(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(incrementedDate), i - 1, 1);

                    // Format soTienLai with DecimalFormat
                    DecimalFormat df = new DecimalFormat("###,###,###,###");
                    String formattedSoTienLai = df.format(soTienLai);
                    model.setValueAt(formattedSoTienLai, i - 1, 2);

                    previousDate = incrementedDate;
                }
            }
            for (int i = kyHan; i < model.getRowCount(); i++) {
                model.setValueAt("", i, 0);
                model.setValueAt("", i, 1);
                model.setValueAt("", i, 2);
            }
        } else {
            // Handle the case where ngayGuiDate is null
            System.err.println("Failed to parse ngayGuiString to LocalDate.");
        }
    } else {
        // Handle the case where ngayGuiString is null
        System.err.println("ngayGuiString is null.");
    }
}

// Helper method to parse String to LocalDate
private LocalDate parseLocalDate(String dateString) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    } catch (DateTimeParseException e) {
        e.printStackTrace(); // Handle the parsing exception according to your needs
        return null;
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        tkDialog = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        tkTuyChoComboBox = new javax.swing.JComboBox<>();
        tkTuyChonTuDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tkTuyChonDenDateChooser = new com.toedter.calendar.JDateChooser();
        tkTKDiaLogButton = new javax.swing.JButton();
        tkThoatTuyChonButton = new javax.swing.JButton();
        stkDialog = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inforSTKjTable = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lsvDialog = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        soTienTraSTKTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        inTenButton = new javax.swing.JButton();
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
        inTienButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        thoiGianTKTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ngayTKButton = new javax.swing.JButton();
        tuNgayTKTextField = new com.toedter.calendar.JDateChooser();
        denNgayTKTextField = new com.toedter.calendar.JDateChooser();
        inNgayThangNamButton = new javax.swing.JButton();
        thongKePanel = new javax.swing.JPanel();
        showTKPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        tkYearTKButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        yearTKTextField = new javax.swing.JTextField();
        thangQuyComboBox = new javax.swing.JComboBox<>();
        tkTuyChonButton = new javax.swing.JButton();
        tkThangQuyComboBox = new javax.swing.JComboBox<>();
        giaoDichPanel = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        themChiButton = new javax.swing.JButton();
        suaChiButton = new javax.swing.JButton();
        xoaChiButton = new javax.swing.JButton();
        xoaAllChiButton = new javax.swing.JButton();
        inMucChiButton = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        chiTable = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        themThuButton = new javax.swing.JButton();
        suaThuButton = new javax.swing.JButton();
        xoaThuButton = new javax.swing.JButton();
        xoaAllThuButton = new javax.swing.JButton();
        inMucThuButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        thuTable = new javax.swing.JTable();
        soTietKiemPanel = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        themSTKButton = new javax.swing.JButton();
        suaSTKButton = new javax.swing.JButton();
        xoaSTKButton = new javax.swing.JButton();
        xoaAllSTKButton = new javax.swing.JButton();
        inSTKButton = new javax.swing.JButton();
        inforSTKButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        soTietKiemTable = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        themLSVButton = new javax.swing.JButton();
        suaLSVButton = new javax.swing.JButton();
        xoaLSVButton = new javax.swing.JButton();
        xoaAllLSVButton = new javax.swing.JButton();
        inLSVButton = new javax.swing.JButton();
        inforLSVButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        laiSuatVayTable = new javax.swing.JTable();

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

        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel9.setInheritsPopupMenu(true);
        jPanel9.setPreferredSize(new java.awt.Dimension(330, 152));

        tkTuyChoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mục chi", "Mục thu", "Mục thu&chi", "Sổ tiết kiệm", "Lãi suất vay" }));

        jLabel22.setText("Từ");

        jLabel23.setText("Đến");

        tkTKDiaLogButton.setText("Tìm Kiếm");
        tkTKDiaLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkTKDiaLogButtonActionPerformed(evt);
            }
        });

        tkThoatTuyChonButton.setText("Thoát");
        tkThoatTuyChonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkThoatTuyChonButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(tkThoatTuyChonButton)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tkTuyChoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tkTuyChonTuDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tkTuyChonDenDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(32, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tkTKDiaLogButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(tkTuyChoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(tkTuyChonTuDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(tkTuyChonDenDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tkTKDiaLogButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tkThoatTuyChonButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout tkDialogLayout = new javax.swing.GroupLayout(tkDialog.getContentPane());
        tkDialog.getContentPane().setLayout(tkDialogLayout);
        tkDialogLayout.setHorizontalGroup(
            tkDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tkDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addContainerGap())
        );
        tkDialogLayout.setVerticalGroup(
            tkDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tkDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        inforSTKjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        inforSTKjTable.setEditingRow(1);
        jScrollPane2.setViewportView(inforSTKjTable);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );

        stkDialog.getContentPane().add(jPanel10, java.awt.BorderLayout.CENTER);

        jLabel5.setText("SỐ TIỀN ĐÃ NHẬN ĐƯỢC MỖI THÁNG");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        stkDialog.getContentPane().add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jLabel21.setText("Số Tiền Đã Đưa Mỗi Tháng");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(36, 36, 36))
        );

        jLabel24.setText("Số Tiền Trả");

        jButton1.setText("Thêm");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(soTienTraSTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jButton1)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(soTienTraSTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout lsvDialogLayout = new javax.swing.GroupLayout(lsvDialog.getContentPane());
        lsvDialog.getContentPane().setLayout(lsvDialogLayout);
        lsvDialogLayout.setHorizontalGroup(
            lsvDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lsvDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lsvDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        lsvDialogLayout.setVerticalGroup(
            lsvDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lsvDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));

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
                .addContainerGap(315, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(316, Short.MAX_VALUE))
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

        tenTKButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        inTenButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inTenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Print preview.png"))); // NOI18N
        inTenButton.setText("In");
        inTenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inTenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(246, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tenTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 243, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tenTKButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inTenButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenTKButton)
                    .addComponent(inTenButton))
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
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

        tienTKButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        inTienButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inTienButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Print preview.png"))); // NOI18N
        inTienButton.setText("In");
        inTienButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inTienButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(232, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tuTienTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(denTienTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tienTKButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inTienButton))
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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tienTKButton)
                    .addComponent(inTienButton))
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
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        ngayTKButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        inNgayThangNamButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inNgayThangNamButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Print preview.png"))); // NOI18N
        inNgayThangNamButton.setText("In");
        inNgayThangNamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inNgayThangNamButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tuNgayTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(denNgayTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ngayTKButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inNgayThangNamButton))
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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngayTKButton)
                    .addComponent(inNgayThangNamButton))
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
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        showTKPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout showTKPanelLayout = new javax.swing.GroupLayout(showTKPanel);
        showTKPanel.setLayout(showTKPanelLayout);
        showTKPanelLayout.setHorizontalGroup(
            showTKPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );
        showTKPanelLayout.setVerticalGroup(
            showTKPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tkYearTKButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tkYearTKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Search.png"))); // NOI18N
        tkYearTKButton.setText("Tìm kiếm");
        tkYearTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkYearTKButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Năm");

        yearTKTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearTKTextFieldActionPerformed(evt);
            }
        });

        thangQuyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Quý" }));
        thangQuyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thangQuyComboBoxActionPerformed(evt);
            }
        });

        tkTuyChonButton.setText("Tùy Chọn");
        tkTuyChonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkTuyChonButtonActionPerformed(evt);
            }
        });

        tkThangQuyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mục chi", "Mục thu", "Mục thu&chi", "Sổ tiết kiệm", "Lãi suất vay" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(tkThangQuyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(thangQuyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tkTuyChonButton))
                            .addComponent(yearTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tkYearTKButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tkThangQuyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thangQuyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tkTuyChonButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(yearTKTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tkYearTKButton)
                .addGap(12, 12, 12))
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

        giaoDichPanel.setBackground(new java.awt.Color(255, 255, 255));
        giaoDichPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                giaoDichPanelComponentShown(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        themChiButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        themChiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Add.png"))); // NOI18N
        themChiButton.setText("Thêm");
        themChiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themChiButtonActionPerformed(evt);
            }
        });

        suaChiButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        suaChiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Edit.png"))); // NOI18N
        suaChiButton.setText("Sửa");
        suaChiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaChiButtonActionPerformed(evt);
            }
        });

        xoaChiButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        xoaChiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Delete.png"))); // NOI18N
        xoaChiButton.setText("Xóa");
        xoaChiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaChiButtonActionPerformed(evt);
            }
        });

        xoaAllChiButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        xoaAllChiButton.setText("Xóa tất cả");
        xoaAllChiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaAllChiButtonActionPerformed(evt);
            }
        });

        inMucChiButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inMucChiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Print preview.png"))); // NOI18N
        inMucChiButton.setText("In");
        inMucChiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inMucChiButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inMucChiButton)
                .addGap(18, 18, 18)
                .addComponent(themChiButton)
                .addGap(18, 18, 18)
                .addComponent(suaChiButton)
                .addGap(18, 18, 18)
                .addComponent(xoaChiButton)
                .addGap(18, 18, 18)
                .addComponent(xoaAllChiButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themChiButton)
                    .addComponent(suaChiButton)
                    .addComponent(xoaAllChiButton)
                    .addComponent(inMucChiButton)
                    .addComponent(xoaChiButton))
                .addContainerGap(37, Short.MAX_VALUE))
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
        jScrollPane7.setViewportView(chiTable);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Mục Chi", jPanel15);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        themThuButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        themThuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Add.png"))); // NOI18N
        themThuButton.setText("Thêm");
        themThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themThuButtonActionPerformed(evt);
            }
        });

        suaThuButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        suaThuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Edit.png"))); // NOI18N
        suaThuButton.setText("Sửa");
        suaThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaThuButtonActionPerformed(evt);
            }
        });

        xoaThuButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        xoaThuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Delete.png"))); // NOI18N
        xoaThuButton.setText("Xóa");
        xoaThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaThuButtonActionPerformed(evt);
            }
        });

        xoaAllThuButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        xoaAllThuButton.setText("Xóa tất cả");
        xoaAllThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaAllThuButtonActionPerformed(evt);
            }
        });

        inMucThuButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inMucThuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Print preview.png"))); // NOI18N
        inMucThuButton.setText("In");
        inMucThuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inMucThuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inMucThuButton)
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
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themThuButton)
                    .addComponent(suaThuButton)
                    .addComponent(xoaThuButton)
                    .addComponent(xoaAllThuButton)
                    .addComponent(inMucThuButton))
                .addContainerGap(36, Short.MAX_VALUE))
        );

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
        jScrollPane8.setViewportView(thuTable);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Mục Thu", jPanel16);

        javax.swing.GroupLayout giaoDichPanelLayout = new javax.swing.GroupLayout(giaoDichPanel);
        giaoDichPanel.setLayout(giaoDichPanelLayout);
        giaoDichPanelLayout.setHorizontalGroup(
            giaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(giaoDichPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        giaoDichPanelLayout.setVerticalGroup(
            giaoDichPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, giaoDichPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        mainPanel.add(giaoDichPanel, "card2");

        soTietKiemPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        themSTKButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        themSTKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Add.png"))); // NOI18N
        themSTKButton.setText("Thêm");
        themSTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themSTKButtonActionPerformed(evt);
            }
        });

        suaSTKButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        suaSTKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Edit.png"))); // NOI18N
        suaSTKButton.setText("Sửa");
        suaSTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaSTKButtonActionPerformed(evt);
            }
        });

        xoaSTKButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        xoaSTKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Delete.png"))); // NOI18N
        xoaSTKButton.setText("Xóa");
        xoaSTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaSTKButtonActionPerformed(evt);
            }
        });

        xoaAllSTKButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        xoaAllSTKButton.setText("Xóa tất cả");
        xoaAllSTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaAllSTKButtonActionPerformed(evt);
            }
        });

        inSTKButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inSTKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Print preview.png"))); // NOI18N
        inSTKButton.setText("In");
        inSTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inSTKButtonActionPerformed(evt);
            }
        });

        inforSTKButton.setText("Info");
        inforSTKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inforSTKButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inSTKButton)
                .addGap(18, 18, 18)
                .addComponent(themSTKButton)
                .addGap(18, 18, 18)
                .addComponent(suaSTKButton)
                .addGap(18, 18, 18)
                .addComponent(xoaSTKButton)
                .addGap(18, 18, 18)
                .addComponent(xoaAllSTKButton)
                .addGap(18, 18, 18)
                .addComponent(inforSTKButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themSTKButton)
                    .addComponent(suaSTKButton)
                    .addComponent(xoaSTKButton)
                    .addComponent(xoaAllSTKButton)
                    .addComponent(inSTKButton)
                    .addComponent(inforSTKButton))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        soTietKiemTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(soTietKiemTable);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Sổ Tiết Kiệm", jPanel13);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        themLSVButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        themLSVButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Add.png"))); // NOI18N
        themLSVButton.setText("Thêm");
        themLSVButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themLSVButtonActionPerformed(evt);
            }
        });

        suaLSVButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        suaLSVButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Edit.png"))); // NOI18N
        suaLSVButton.setText("Sửa");
        suaLSVButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaLSVButtonActionPerformed(evt);
            }
        });

        xoaLSVButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        xoaLSVButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Delete.png"))); // NOI18N
        xoaLSVButton.setText("Xóa");
        xoaLSVButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaLSVButtonActionPerformed(evt);
            }
        });

        xoaAllLSVButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        xoaAllLSVButton.setText("Xóa tất cả");
        xoaAllLSVButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaAllLSVButtonActionPerformed(evt);
            }
        });

        inLSVButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inLSVButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytaichinh/images/Print preview.png"))); // NOI18N
        inLSVButton.setText("In");
        inLSVButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inLSVButtonActionPerformed(evt);
            }
        });

        inforLSVButton.setText("Infor");
        inforLSVButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inforLSVButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inLSVButton)
                .addGap(18, 18, 18)
                .addComponent(themLSVButton)
                .addGap(18, 18, 18)
                .addComponent(suaLSVButton)
                .addGap(18, 18, 18)
                .addComponent(xoaLSVButton)
                .addGap(18, 18, 18)
                .addComponent(xoaAllLSVButton)
                .addGap(18, 18, 18)
                .addComponent(inforLSVButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themLSVButton)
                    .addComponent(suaLSVButton)
                    .addComponent(xoaLSVButton)
                    .addComponent(xoaAllLSVButton)
                    .addComponent(inLSVButton)
                    .addComponent(inforLSVButton))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        laiSuatVayTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(laiSuatVayTable);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Lãi Suất Vay", jPanel19);

        javax.swing.GroupLayout soTietKiemPanelLayout = new javax.swing.GroupLayout(soTietKiemPanel);
        soTietKiemPanel.setLayout(soTietKiemPanelLayout);
        soTietKiemPanelLayout.setHorizontalGroup(
            soTietKiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(soTietKiemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        soTietKiemPanelLayout.setVerticalGroup(
            soTietKiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(soTietKiemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        mainPanel.add(soTietKiemPanel, "card6");

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

    private void tenTKTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tenTKTextFieldKeyReleased
        displayTen(logId);
//        DefaultTableModel obj = (DefaultTableModel) tenTKTable.getModel();
//        TableRowSorter<DefaultTableModel> tableRS = new TableRowSorter<>(obj);
//        tenTKTable.setRowSorter(tableRS);
//        tableRS.setRowFilter(RowFilter.regexFilter(tenTKTextField.getText()));
    }//GEN-LAST:event_tenTKTextFieldKeyReleased

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

    private void tkYearTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkYearTKButtonActionPerformed
//       
        
        String displayMode = (String) tkThangQuyComboBox.getSelectedItem();
        String thangQuy = (String) thangQuyComboBox.getSelectedItem();
        String year = yearTKTextField.getText();
        
        if (("Mục chi".equals(displayMode))) {
                if ("Tháng".equals(thangQuy) || "Quý".equals(thangQuy)) {
                    try {
                        int intYear = Integer.parseInt(year);

                        // Gọi hàm thống kê với tham số year và displayMode
                        thongKeGiaoDichChi(showTKPanel, intYear, thangQuy);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Năm không hợp lệ!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chọn chế độ thống kê là 'tháng' hoặc 'quý'");
                }
        }
        if (("Lãi suất vay".equals(displayMode))) {
//            thongKeLaiSuatVay(showTKPanel, tuNgayTxt, denNgayTxt);
            if ("Tháng".equals(thangQuy) || "Quý".equals(thangQuy)) {
                    try {
                        int intYear = Integer.parseInt(year);

                        // Gọi hàm thống kê với tham số year và displayMode
                        thongKeLaiSuatVayYear(showTKPanel, intYear, thangQuy);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Năm không hợp lệ!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chọn chế độ thống kê là 'tháng' hoặc 'quý'");
                }
        }
        if (("Sổ tiết kiệm".equals(displayMode))){
//            thongKeSoTietKiem(showTKPanel, tuNgayTxt, denNgayTxt);
            if ("Tháng".equals(thangQuy) || "Quý".equals(thangQuy)) {
                    try {
                        int intYear = Integer.parseInt(year);

                        // Gọi hàm thống kê với tham số year và displayMode
                        thongKeSoTietKiemYear(showTKPanel, intYear, thangQuy);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Năm không hợp lệ!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chọn chế độ thống kê là 'tháng' hoặc 'quý'");
                }
        }
        if (("Mục thu&chi".equals(displayMode))) {
//            thongKeGiaoDichThuChiSua(showTKPanel, tuNgayTxt, denNgayTxt);
            if ("Tháng".equals(thangQuy) || "Quý".equals(thangQuy)) {
                    try {
                        int intYear = Integer.parseInt(year);

                        // Gọi hàm thống kê với tham số year và displayMode
                        thongKeGiaoDichThuChi(showTKPanel, intYear, thangQuy);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Năm không hợp lệ!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chọn chế độ thống kê là 'tháng' hoặc 'quý'");
                }
        } 
        if (("Mục thu".equals(displayMode))){
//            thongKeGiaoDichThuSua(showTKPanel, tuNgayTxt, denNgayTxt);
            if ("Tháng".equals(thangQuy) || "Quý".equals(thangQuy)) {
                    try {
                        int intYear = Integer.parseInt(year);

                        // Gọi hàm thống kê với tham số year và displayMode
                        thongKeGiaoDichThu(showTKPanel, intYear, thangQuy);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Năm không hợp lệ!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chọn chế độ thống kê là 'tháng' hoặc 'quý'");
                }
        };
        
//        if ("tháng".equals(displayMode) || "quý".equals(displayMode)) {
//            try {
//                int intYear = Integer.parseInt(year);
//
//                // Gọi hàm thống kê với tham số year và displayMode
//                thongKeGiaoDichChi(showTKPanel, intYear, displayMode);
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(this, "Năm không hợp lệ!");
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Chọn chế độ thống kê là 'tháng' hoặc 'quý'");
//        }

//        thongKeGiaoDichChi(showTKPanel, Integer.parseInt(year));
    }//GEN-LAST:event_tkYearTKButtonActionPerformed

    private void tenTKTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tenTKTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tenTKTextFieldKeyTyped

    private void soTietKiemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soTietKiemButtonActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.add(soTietKiemPanel);
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

    private void xoaAllChiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaAllChiButtonActionPerformed
        // TODO add your handling code here:
        int row = chiTable.getSelectedRow();

            int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa tất cả dữ liệu không?");
            if (confirm == JOptionPane.YES_OPTION) {
                // Xóa tất cả dữ liệu trong bảng
                homeViewController.deleteAllGiaoDichChi(logId);

                // Cập nhật bảng sau khi xóa
                defaultTableModel.setRowCount(0);
                setTableData(homeViewController.getAllInforUser(logId));
        }
    }//GEN-LAST:event_xoaAllChiButtonActionPerformed

    private void themChiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themChiButtonActionPerformed
        // TODO add your handling code here:
//        themChiJFrame themChiFrame = new themChiJFrame(logId);
        System.out.println("button chi"+logId);
//        themChiFrame.setVisible(true);
        new themChiJFrame(homeViewPro, logId).setVisible(true);
    }//GEN-LAST:event_themChiButtonActionPerformed

    private void suaChiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaChiButtonActionPerformed
        // TODO add your handling code here:
        int row = soTietKiemTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn giao dịch trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                int giaoDichId = Integer.valueOf(String.valueOf(chiTable.getValueAt(row, 0)));
                new editChiJFrame(homeViewPro, giaoDichId).setVisible(true);
            }
    }//GEN-LAST:event_suaChiButtonActionPerformed

    private void xoaChiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaChiButtonActionPerformed
        // TODO add your handling code here:
        int row = chiTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn giao dịch trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa dữ liệu không?");
            if (confirm == JOptionPane.YES_OPTION){
                int userId = Integer.parseInt(String.valueOf(chiTable.getValueAt(row, 0)));
                homeViewController.deleteGiaoDichChi(userId);
                defaultTableModel.setRowCount(0);
                setTableData(homeViewController.getAllInforUser(logId));
            }
        }
    }//GEN-LAST:event_xoaChiButtonActionPerformed

    private void themThuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themThuButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("button thu " + logId);
        new themThuJFrame(homeViewPro, logId).setVisible(true);
    }//GEN-LAST:event_themThuButtonActionPerformed

    private void suaThuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaThuButtonActionPerformed
        // TODO add your handling code here:
        int row = thuTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn giao dịch trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                int giaoDichId = Integer.valueOf(String.valueOf(thuTable.getValueAt(row, 0)));
                new editThuJFrame(homeViewPro, giaoDichId).setVisible(true);
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
        // TODO add your handling code here     
        int row = thuTable.getSelectedRow();

            int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa tất cả dữ liệu không?");
            if (confirm == JOptionPane.YES_OPTION) {
                // Xóa tất cả dữ liệu trong bảng
                homeViewController.deleteAllGiaoDichThu(logId);

                // Cập nhật bảng sau khi xóa
                defaultTableThuModel.setRowCount(0);
                setThuTableData(homeViewController.getAllInforUserThu(logId));
        }
    }//GEN-LAST:event_xoaAllThuButtonActionPerformed

    private void themSTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themSTKButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("button them stk " + logId);
        new themSTKJFrame(homeViewPro, logId).setVisible(true);
    }//GEN-LAST:event_themSTKButtonActionPerformed

    private void suaSTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaSTKButtonActionPerformed
        // TODO add your handling code here:
        int row = soTietKiemTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn giao dịch trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                int giaoDichId = Integer.valueOf(String.valueOf(soTietKiemTable.getValueAt(row, 0)));
                new editSTKJFrame(homeViewPro, giaoDichId).setVisible(true);
        }
    }//GEN-LAST:event_suaSTKButtonActionPerformed

    private void xoaSTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaSTKButtonActionPerformed
        // TODO add your handling code here:
        int row = soTietKiemTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa không!");
            if (confirm == JOptionPane.YES_OPTION){
                int userId = Integer.parseInt(String.valueOf(soTietKiemTable.getValueAt(row, 0)));
                homeViewController.deleteSoTietKiem(userId);
                defaultTableSTKModel.setRowCount(0);
                setSTKTable(homeViewController.getAllInforUserSTK(logId));
            }
        }
    }//GEN-LAST:event_xoaSTKButtonActionPerformed

    private void xoaAllSTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaAllSTKButtonActionPerformed
        // TODO add your handling code here:
        int row = soTietKiemTable.getSelectedRow();

        int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa tất cả dữ liệu không?");
        if (confirm == JOptionPane.YES_OPTION) {
                // Xóa tất cả dữ liệu trong bảng
            homeViewController.deleteAllSoTietKiem(logId);

                // Cập nhật bảng sau khi xóa
            defaultTableSTKModel.setRowCount(0);
            setSTKTable(homeViewController.getAllInforUserSTK(logId));
        }
    }//GEN-LAST:event_xoaAllSTKButtonActionPerformed

    private void themLSVButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themLSVButtonActionPerformed
        // TODO add your handling code here:
//        HomeViewPro homeViewProInstance = new HomeViewPro(loginModel);
//        themLSVJFrame frame = new themLSVJFrame(logId, homeViewProInstance);
        
        new themLSVJFrame(homeViewPro,logId).setVisible(true);
    }//GEN-LAST:event_themLSVButtonActionPerformed

    private void suaLSVButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaLSVButtonActionPerformed
        // TODO add your handling code here:
        int row = laiSuatVayTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn giao dịch trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                int giaoDichId = Integer.valueOf(String.valueOf(laiSuatVayTable.getValueAt(row, 0)));
                new editLSVJFrame(homeViewPro, giaoDichId).setVisible(true);
        }
    }//GEN-LAST:event_suaLSVButtonActionPerformed

    private void xoaLSVButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaLSVButtonActionPerformed
        // TODO add your handling code here:
        int row = laiSuatVayTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa không!");
            if (confirm == JOptionPane.YES_OPTION){
                int userId = Integer.parseInt(String.valueOf(laiSuatVayTable.getValueAt(row, 0)));
                homeViewController.deleteLaiSuatVay(userId);
                defaultTableLSVModel.setRowCount(0);
            setLSVTable(homeViewController.getAllInforUserLSV(logId));
            }
        }
    }//GEN-LAST:event_xoaLSVButtonActionPerformed

    private void xoaAllLSVButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaAllLSVButtonActionPerformed
        // TODO add your handling code here:
        int row = laiSuatVayTable.getSelectedRow();

        int confirm = JOptionPane.showConfirmDialog(HomeViewPro.this, "Bạn chắc chắn muốn xóa tất cả dữ liệu không?");
        if (confirm == JOptionPane.YES_OPTION) {
                // Xóa tất cả dữ liệu trong bảng
            homeViewController.deleteAllLaiSuatVay(logId);

                // Cập nhật bảng sau khi xóa
            defaultTableLSVModel.setRowCount(0);
            setLSVTable(homeViewController.getAllInforUserLSV(logId));
        }
    }//GEN-LAST:event_xoaAllLSVButtonActionPerformed

    private void inMucChiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inMucChiButtonActionPerformed
        // TODO add your handling code here:
        exportTableToExcel(chiTable);
    }//GEN-LAST:event_inMucChiButtonActionPerformed

    private void inNgayThangNamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inNgayThangNamButtonActionPerformed
        // TODO add your handling code here:
        exportTableToExcel(thoiGianTKTable);
    }//GEN-LAST:event_inNgayThangNamButtonActionPerformed

    private void inMucThuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inMucThuButtonActionPerformed
        // TODO add your handling code here:
        exportTableToExcel(thuTable);
    }//GEN-LAST:event_inMucThuButtonActionPerformed

    private void inTenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inTenButtonActionPerformed
        // TODO add your handling code here:
        exportTableToExcel(tenTKTable);
    }//GEN-LAST:event_inTenButtonActionPerformed

    private void inTienButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inTienButtonActionPerformed
        // TODO add your handling code here:
        exportTableToExcel(tienTKTable);
    }//GEN-LAST:event_inTienButtonActionPerformed

    private void inLSVButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inLSVButtonActionPerformed
        // TODO add your handling code here:
        exportTableToExcel(laiSuatVayTable);
    }//GEN-LAST:event_inLSVButtonActionPerformed

    private void inSTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inSTKButtonActionPerformed
        // TODO add your handling code here:
        exportTableToExcel(soTietKiemTable);
    }//GEN-LAST:event_inSTKButtonActionPerformed

    private void yearTKTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTKTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearTKTextFieldActionPerformed

    private void thangQuyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thangQuyComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thangQuyComboBoxActionPerformed

    private void tkTuyChonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkTuyChonButtonActionPerformed
        // TODO add your handling code here:
        tkDialog.setVisible(true);
        tkDialog.setSize(500, 300);
        tkDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_tkTuyChonButtonActionPerformed

    private void tkTKDiaLogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkTKDiaLogButtonActionPerformed
        // TODO add your handling code here:
        String displayMode = (String) tkTuyChoComboBox.getSelectedItem();
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date tuNgayTK = tkTuyChonTuDateChooser.getDate();
        Date denNgayTK = tkTuyChonDenDateChooser.getDate();
        
        String tuNgayTxt = simpleDateFormat.format(tuNgayTK);
        String denNgayTxt = simpleDateFormat.format(denNgayTK);
        
        if ("Mục chi".equals(displayMode)) {
            thongKeGiaoDichChiSua(showTKPanel, tuNgayTxt, denNgayTxt);
        } else if ("Lãi suất vay".equals(displayMode)) {
            thongKeLaiSuatVay(showTKPanel, tuNgayTxt, denNgayTxt);
        } else if ("Sổ tiết kiệm".equals(displayMode)){
            thongKeSoTietKiem(showTKPanel, tuNgayTxt, denNgayTxt);
        } else if ("Mục thu&chi".equals(displayMode)) {
            thongKeGiaoDichThuChiSua(showTKPanel, tuNgayTxt, denNgayTxt);
        } else if ("Mục thu".equals(displayMode)){
            thongKeGiaoDichThuSua(showTKPanel, tuNgayTxt, denNgayTxt);
        };
    }//GEN-LAST:event_tkTKDiaLogButtonActionPerformed

    private void tkThoatTuyChonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkThoatTuyChonButtonActionPerformed
        // TODO add your handling code here:
        tkDialog.setVisible(false);
    }//GEN-LAST:event_tkThoatTuyChonButtonActionPerformed

    private void inforSTKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inforSTKButtonActionPerformed
        // TODO add your handling code here:
        int row = soTietKiemTable.getSelectedRow();
if (row == -1) {
    JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn giao dịch trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
} else {
    int tietKiemIdColumnIndex = 0; // Assuming TietKiemId is in the first column
    Object tietKiemIdObject = soTietKiemTable.getValueAt(row, tietKiemIdColumnIndex);

    // Check if the value is not null before proceeding
    if (tietKiemIdObject != null) {
        int tietKiemId = Integer.parseInt(String.valueOf(tietKiemIdObject));

        // Ensure that giaoDichDao is not null before using it
        if (giaoDichDao != null) {
            SoTietKiemModel soTietKiemModel = giaoDichDao.getInforUserSTK(tietKiemId);

            // Check if the fetched model is not null
            if (soTietKiemModel != null) {
                // Gọi phương thức để hiển thị thông tin trong inforSTKjTable
                displayInformationInTable(soTietKiemModel);

                // Hiển thị stkDialog
                stkDialog.setVisible(true);
                stkDialog.setSize(500, 300);
                stkDialog.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Không thể lấy thông tin giao dịch", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Automatically initialize giaoDichDao if it is null
            giaoDichDao = new GiaoDichDao(); // Replace this with your initialization logic

            // Continue with the rest of the code
            SoTietKiemModel soTietKiemModel = giaoDichDao.getInforUserSTK(tietKiemId);

            // Check if the fetched model is not null
            if (soTietKiemModel != null) {
                // Gọi phương thức để hiển thị thông tin trong inforSTKjTable
                displayInformationInTable(soTietKiemModel);

                // Hiển thị stkDialog
                stkDialog.setVisible(true);
                stkDialog.setSize(500, 300);
                stkDialog.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Không thể lấy thông tin giao dịch", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(HomeViewPro.this, "Giá trị TietKiemId là null", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    }//GEN-LAST:event_inforSTKButtonActionPerformed

    private void inforLSVButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inforLSVButtonActionPerformed
        // TODO add your handling code here:
        int row = soTietKiemTable.getSelectedRow();
if (row == -1) {
    JOptionPane.showMessageDialog(HomeViewPro.this, "Vui lòng chọn giao dịch trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
} else {
    int tietKiemIdColumnIndex = 0; // Assuming TietKiemId is in the first column
    Object tietKiemIdObject = soTietKiemTable.getValueAt(row, tietKiemIdColumnIndex);

    // Check if the value is not null before proceeding
    if (tietKiemIdObject != null) {
        int tietKiemId = Integer.parseInt(String.valueOf(tietKiemIdObject));

        // Ensure that giaoDichDao is not null before using it
        if (giaoDichDao != null) {
            SoTietKiemModel soTietKiemModel = giaoDichDao.getInforUserSTK(tietKiemId);

            // Check if the fetched model is not null
            if (soTietKiemModel != null) {
                // Gọi phương thức để hiển thị thông tin trong inforSTKjTable
                displayInformationInTable(soTietKiemModel);

                // Hiển thị stkDialog
                stkDialog.setVisible(true);
                stkDialog.setSize(500, 300);
                stkDialog.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Không thể lấy thông tin giao dịch", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Automatically initialize giaoDichDao if it is null
            giaoDichDao = new GiaoDichDao(); // Replace this with your initialization logic

            // Continue with the rest of the code
            SoTietKiemModel soTietKiemModel = giaoDichDao.getInforUserSTK(tietKiemId);

            // Check if the fetched model is not null
            if (soTietKiemModel != null) {
                // Gọi phương thức để hiển thị thông tin trong inforSTKjTable
                displayInformationInTable(soTietKiemModel);

                // Hiển thị stkDialog
                stkDialog.setVisible(true);
                stkDialog.setSize(500, 300);
                stkDialog.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(HomeViewPro.this, "Không thể lấy thông tin giao dịch", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(HomeViewPro.this, "Giá trị TietKiemId là null", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    }//GEN-LAST:event_inforLSVButtonActionPerformed

    
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
    private javax.swing.ButtonGroup buttonGroup2;
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
    private javax.swing.JButton inLSVButton;
    private javax.swing.JButton inMucChiButton;
    private javax.swing.JButton inMucThuButton;
    private javax.swing.JButton inNgayThangNamButton;
    private javax.swing.JButton inSTKButton;
    private javax.swing.JButton inTenButton;
    private javax.swing.JButton inTienButton;
    private javax.swing.JButton inforLSVButton;
    private javax.swing.JButton inforSTKButton;
    private javax.swing.JTable inforSTKjTable;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton khacRadioButton;
    private javax.swing.JRadioButton khacRadioButton1;
    public javax.swing.JTable laiSuatVayTable;
    private javax.swing.JDialog lsvDialog;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField matHangTGDTextField;
    private javax.swing.JTextField matHangTGDTextField1;
    private javax.swing.JButton ngayTKButton;
    private javax.swing.JRadioButton quanAoRadioButton;
    private javax.swing.JRadioButton quanAoRadioButton1;
    private javax.swing.JLabel showDateLabel;
    private javax.swing.JLabel showRealTimeLabel;
    private javax.swing.JPanel showTKPanel;
    private javax.swing.JTextField soTienTraSTKTextField;
    private javax.swing.JButton soTietKiemButton;
    private javax.swing.JPanel soTietKiemPanel;
    private javax.swing.JTable soTietKiemTable;
    private javax.swing.JDialog stkDialog;
    private javax.swing.JButton suaChiButton;
    private javax.swing.JButton suaLSVButton;
    private javax.swing.JButton suaSTKButton;
    private javax.swing.JButton suaThuButton;
    private javax.swing.JButton tenTKButton;
    private javax.swing.JTable tenTKTable;
    private javax.swing.JTextField tenTKTextField;
    private javax.swing.JComboBox<String> thangQuyComboBox;
    private javax.swing.JTextField thanhTienTGDTextField;
    private javax.swing.JTextField thanhTienTGDTextField1;
    private javax.swing.JButton themChiButton;
    private javax.swing.JDialog themDialog;
    private javax.swing.JButton themLSVButton;
    private javax.swing.JButton themSTKButton;
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
    private javax.swing.JButton tienTKButton;
    private javax.swing.JTable tienTKTable;
    private javax.swing.JButton timKiemButton;
    private javax.swing.JPanel timKiemPanel;
    private javax.swing.JDialog tkDialog;
    private javax.swing.JButton tkTKDiaLogButton;
    private javax.swing.JComboBox<String> tkThangQuyComboBox;
    private javax.swing.JButton tkThoatTuyChonButton;
    private javax.swing.JComboBox<String> tkTuyChoComboBox;
    private javax.swing.JButton tkTuyChonButton;
    private com.toedter.calendar.JDateChooser tkTuyChonDenDateChooser;
    private com.toedter.calendar.JDateChooser tkTuyChonTuDateChooser;
    private javax.swing.JButton tkYearTKButton;
    private com.toedter.calendar.JDateChooser tuNgayTKTextField;
    private javax.swing.JTextField tuTienTKTextField;
    private javax.swing.JButton xoaAllChiButton;
    private javax.swing.JButton xoaAllLSVButton;
    private javax.swing.JButton xoaAllSTKButton;
    private javax.swing.JButton xoaAllThuButton;
    private javax.swing.JButton xoaChiButton;
    private javax.swing.JButton xoaLSVButton;
    private javax.swing.JButton xoaSTKButton;
    private javax.swing.JButton xoaThuButton;
    private javax.swing.JTextField yearTKTextField;
    // End of variables declaration//GEN-END:variables
}
