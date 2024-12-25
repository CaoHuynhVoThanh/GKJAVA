package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.SanPham_DAO;
import entities.HoaDon;
import entities.NhanVien;

public class frmThongKe extends JFrame {
    private JPanel contentPane;
    private JDateChooser dateChooser;
    private PieDataset currentPieDataset;
    private ChartPanel weeklyBarChartPanel;
    private ChartPanel monthlyBarChartPanel;
    private ArrayList<HoaDon> dsHD;
    NhanVien nv = new NhanVien("NV00000", "Admin", "Quản lý", LocalDate.parse("2024-01-01"), "hihi@gmail.com", "012345678a");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private PieDataset quarterDataset;
	private JPanel p_body_ThongKe;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConnectDB con = new ConnectDB();
                con.getInstance().connect();
                frmThongKe frame = new frmThongKe();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public frmThongKe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setSize(1536, 864);
        this.setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel p_head = new JPanel();
		p_head.setBackground(new Color(204, 51, 255));
		p_head.setBounds(0, 0, 1546, 100);
		contentPane.add(p_head);
		p_head.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(67, 0, 148, 100);
		p_head.add(logo);
		logo.setIcon(new ImageIcon("./src/img/logo.png"));
		
		JLabel avt = new JLabel("");
		avt.setIcon(new ImageIcon("./src/img/avt.png"));
		avt.setBounds(1397, 0, 81, 100);
		p_head.add(avt);
		
		JLabel lb_hoten = new JLabel();
		lb_hoten.setText(nv.getTenNV());
		lb_hoten.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_hoten.setForeground(new Color(255, 255, 0));
		lb_hoten.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lb_hoten.setBounds(1139, 26, 248, 27);
		p_head.add(lb_hoten);
		
		JLabel lb_chucvu = new JLabel();
		lb_chucvu.setText(nv.getChucVu());
		lb_chucvu.setForeground(new Color(255, 255, 255));
		lb_chucvu.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_chucvu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb_chucvu.setBounds(1243, 52, 142, 27);
		p_head.add(lb_chucvu);
		
		JPanel p_menu = new JPanel();
		p_menu.setBackground(new Color(153, 0, 204));
		p_menu.setForeground(new Color(153, 0, 204));
		p_menu.setBounds(0, 99, 256, 771);
		contentPane.add(p_menu);
		p_menu.setLayout(null);
		
		ImageIcon originalIcon = new ImageIcon("./src/img/payment.png");
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(35, 30, Image.SCALE_SMOOTH); 
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
		JMenuItem mi_tt = new JMenuItem("    THANH TOÁN");
		mi_tt.setIcon(scaledIcon);
		mi_tt.setHorizontalAlignment(SwingConstants.LEFT);
		mi_tt.setIconTextGap(0);
		mi_tt.setMargin(new Insets(0,0,0,0));
		mi_tt.setBackground(new Color(153, 0, 204));
		mi_tt.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mi_tt.setForeground(new Color(255, 255, 255));
		mi_tt.setBounds(25, 72, 231, 46);
		p_menu.add(mi_tt);
		
		originalIcon = new ImageIcon("./src/img/history.png");
        img = originalIcon.getImage();
        scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); 
        scaledIcon = new ImageIcon(scaledImg);
		JMenuItem mi_ls = new JMenuItem("    LỊCH SỬ");
		mi_ls.setIcon(scaledIcon);
		mi_ls.setBackground(new Color(153, 0, 204));
		mi_ls.setHorizontalAlignment(SwingConstants.LEFT);
		mi_ls.setForeground(Color.WHITE);
		mi_ls.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mi_ls.setBounds(25, 150, 231, 46);
		p_menu.add(mi_ls);
		
		originalIcon = new ImageIcon("./src/img/storage.png");
        img = originalIcon.getImage();
        scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); 
        scaledIcon = new ImageIcon(scaledImg);
		JMenuItem mi_kh = new JMenuItem("    KHO HÀNG");
		mi_kh.setIcon(scaledIcon);
		mi_kh.setBackground(new Color(153, 0, 204));
		mi_kh.setHorizontalAlignment(SwingConstants.LEFT);
		mi_kh.setForeground(Color.WHITE);
		mi_kh.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mi_kh.setBounds(25, 234, 231, 46);
		p_menu.add(mi_kh);
		
		originalIcon = new ImageIcon("./src/img/group.png");
        img = originalIcon.getImage();
        scaledImg = img.getScaledInstance(35, 30, Image.SCALE_SMOOTH); 
        scaledIcon = new ImageIcon(scaledImg);
		JMenuItem mi_nv = new JMenuItem("    NHÂN VIÊN");
		mi_nv.setIcon(scaledIcon);
		mi_nv.setBackground(new Color(153, 0, 204));
		mi_nv.setHorizontalAlignment(SwingConstants.LEFT);
		mi_nv.setForeground(Color.WHITE);
		mi_nv.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mi_nv.setBounds(25, 317, 231, 46);
		p_menu.add(mi_nv);
		
		originalIcon = new ImageIcon("./src/img/statistics.png");
        img = originalIcon.getImage();
        scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); 
        scaledIcon = new ImageIcon(scaledImg);
		JMenuItem mi_tk = new JMenuItem("    THỐNG KÊ");
		mi_tk.setIcon(scaledIcon);
		mi_tk.setBackground(new Color(153, 0, 204));
		mi_tk.setHorizontalAlignment(SwingConstants.LEFT);
		mi_tk.setForeground(Color.WHITE);
		mi_tk.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mi_tk.setBounds(25, 402, 231, 46);
		p_menu.add(mi_tk);
		
		JButton btnNewButton = new JButton("ĐĂNG XUẤT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 0, 51));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(29, 656, 198, 46);
		p_menu.add(btnNewButton);
        
        p_body_ThongKe = new JPanel();
		p_body_ThongKe.setBorder(new EmptyBorder(0, 0, 0, 0));
		p_body_ThongKe.setBounds(254, 99, 1268, 728);
		contentPane.add(p_body_ThongKe);
		p_body_ThongKe.setLayout(null);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(26, 370, 200, 30);
        dateChooser.setDate(new Date());
        p_body_ThongKe.add(dateChooser);

        JButton btnUpdate = new JButton("Cập nhật biểu đồ");
        btnUpdate.setBounds(264, 370, 146, 30);
        p_body_ThongKe.add(btnUpdate); 
        
        JButton btnTopProductsPie = new JButton("Top 10 SP bán chạy (Ngày)");
        btnTopProductsPie.setBounds(476, 370, 206, 30);
        p_body_ThongKe.add(btnTopProductsPie);

        JButton btnTopProductsBarWeek = new JButton("Top 10 SP bán chạy (Tuần)");
        btnTopProductsBarWeek.setBounds(732, 370, 200, 30);
        p_body_ThongKe.add(btnTopProductsBarWeek);

        JButton btnTopProductsBarMonth = new JButton("Top 10 SP bán chạy (Tháng)");
        btnTopProductsBarMonth.setBounds(978, 370, 200, 30);
        p_body_ThongKe.add(btnTopProductsBarMonth);

        Date date = new Date();
        dsHD = HoaDon_DAO.layTatCaHoaDon(); // Lấy danh sách hóa đơn
        currentPieDataset = createPieDataset(date);
        ChartPanel pieChartPanel = new ChartPanel(createPieChart(currentPieDataset));
        pieChartPanel.setBounds(27, 24, 383, 325);
        p_body_ThongKe.add(pieChartPanel);

        ArrayList<HoaDon> hoaDonsInWeek = timHDtrongTuan(date, dsHD);
        JFreeChart weeklyBarChart = createBarChart(createWeeklyDataset(hoaDonsInWeek), "Doanh thu theo tuần", "Ngày", "Doanh thu");
        weeklyBarChartPanel = new ChartPanel(weeklyBarChart);
        weeklyBarChartPanel.setBounds(430, 24, 820, 325);
        p_body_ThongKe.add(weeklyBarChartPanel);

        int selectedYear = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).getYear();
        ArrayList<HoaDon> hoaDonsInMonth = timHDTheoThang(selectedYear, dsHD);
        JFreeChart monthlyBarChart = createBarChart(createQuarterlyDataset(hoaDonsInMonth), "Doanh thu theo tháng", "Tháng", "Doanh thu");
        monthlyBarChartPanel = new ChartPanel(monthlyBarChart);
        monthlyBarChartPanel.setBounds(28, 420, 830, 308);
        p_body_ThongKe.add(monthlyBarChartPanel);
        
        
        quarterDataset = createQuarterlyPieDataset(createQuarterlyDataset(hoaDonsInMonth));
        ChartPanel pieChartQuarterpanel = new ChartPanel(createPieChartQuarter(quarterDataset));
        pieChartQuarterpanel.setBounds(877, 420, 373, 308);
        p_body_ThongKe.add(pieChartQuarterpanel);

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = dateChooser.getDate();
                if (selectedDate != null) {
                    
                	currentPieDataset = createPieDataset(selectedDate); 
                    pieChartPanel.setChart(createPieChart(currentPieDataset));

                    
                    ArrayList<HoaDon> hoaDonsInWeek = timHDtrongTuan(selectedDate, dsHD);
                    weeklyBarChartPanel.setChart(createBarChart(createWeeklyDataset(hoaDonsInWeek), "Doanh thu theo tuần", "Ngày", "Doanh thu"));

                    
                    int selectedYear = LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault()).getYear();
                    ArrayList<HoaDon> hoaDonsInMonth = timHDTheoThang(selectedYear, dsHD);
                    monthlyBarChartPanel.setChart(createBarChart(createQuarterlyDataset(hoaDonsInMonth), "Doanh thu theo tháng", "Tháng", "Doanh thu"));
                    
                    
                    quarterDataset = createQuarterlyPieDataset(createQuarterlyDataset(hoaDonsInMonth));
                    pieChartQuarterpanel.setChart(createPieChartQuarter(quarterDataset));
                } else {
                    JOptionPane.showMessageDialog(frmThongKe.this, "Vui lòng chọn ngày!");
                }
            }
        });
        btnTopProductsPie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String date = dateFormat.format(dateChooser.getDate());
                ArrayList<String> topProducts = SanPham_DAO.laytop5SPTrongNgay(date);
                showTopProducts("Top 5 sản phẩm bán chạy trong ngày", topProducts);
            }
        });

        
        btnTopProductsBarWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String date = dateFormat.format(dateChooser.getDate());
            	ArrayList<String> topProducts = SanPham_DAO.layTop10SPTrongTuan(date);
                showTopProducts("Top 10 sản phẩm bán chạy trong tuần", topProducts);
            }
        });

        
        btnTopProductsBarMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String date = dateFormat.format(dateChooser.getDate());
            	ArrayList<String> topProducts = SanPham_DAO.laytop5SPTrongThang(date);
                showTopProducts("Top 5 sản phẩm bán chạy trong tháng", topProducts);
            }
        });
    }

    private void showTopProducts(String title, ArrayList<String> products) {
    	 
        JDialog dialog = new JDialog(this,title, true);
        dialog.setSize(500, 270);
        dialog.setResizable(false); 
        dialog.getContentPane().setLayout(new BorderLayout());
        
        String[] colnames= {"Top","Mã sản phẩm", "Tên sản phẩm", "Số lượng sản phẩm"};
        DefaultTableModel tableModel = new DefaultTableModel(colnames, 0);
        JTable table = new JTable(tableModel);
        dialog.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        
        for (String product : products) {
        	String[] data = product.split(",");
        	tableModel.addRow(data);
        }
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); 
            }
        });
        JPanel pQRSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pQRSouth.add(okButton);
        dialog.getContentPane().add(pQRSouth, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

    }
    private ArrayList<HoaDon> timHDtrongTuan(Date selectedDate, ArrayList<HoaDon> dsHD) {
        ArrayList<HoaDon> hoaDonsInWeek = new ArrayList<>();
        LocalDateTime startOfWeek = LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault())
                .with(DayOfWeek.MONDAY)
                .with(LocalTime.MIN);
        LocalDateTime endOfWeek = startOfWeek.plusDays(6).with(LocalTime.MAX); 

        for (HoaDon hd : dsHD) {
            LocalDateTime thoiGian = hd.getThoiGian();
            if (!(thoiGian.isBefore(startOfWeek) || thoiGian.isAfter(endOfWeek))) {
                hoaDonsInWeek.add(hd);
            }
        }
        return hoaDonsInWeek;
    }
    
    private ArrayList<HoaDon> timHDTheoThang(int year, ArrayList<HoaDon> dsHD) {
        ArrayList<HoaDon> hoaDonsInYear = new ArrayList<>();
        for (HoaDon hd : dsHD) {
            if (hd.getThoiGian().getYear() == year) {
                hoaDonsInYear.add(hd);
            }
        }
        return hoaDonsInYear;
    }
    
    private JFreeChart createPieChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("Biểu đồ doanh thu trong ngày", dataset, true, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
        return chart;
    }
    private JFreeChart createPieChartQuarter(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("Biểu đồ doanh thu trong quý", dataset, true, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})")); 
        return chart;
    }
    private PieDataset createPieDataset(Date date) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        
        double doanhThuCaSang = 0.0;
        double doanhThuCaChieu = 0.0;
        double doanhThuCaToi = 0.0;

        LocalDateTime selectedDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate().atStartOfDay();

        for (HoaDon hd : dsHD) {
            LocalDateTime thoiGian = hd.getThoiGian();
            LocalDateTime hoaDonDate = thoiGian.toLocalDate().atStartOfDay();

            if (hoaDonDate.equals(selectedDate)) {
                int hour = thoiGian.getHour();
                if (hour >= 8 && hour < 12) {
                    doanhThuCaSang += hd.getThanhTien();
                } else if (hour >= 12 && hour < 18) {
                    doanhThuCaChieu += hd.getThanhTien();
                } else if (hour >= 18 && hour < 24) {
                    doanhThuCaToi += hd.getThanhTien();
                }
            }
        }

        dataset.setValue("Ca sáng", doanhThuCaSang);
        dataset.setValue("Ca chiều", doanhThuCaChieu);
        dataset.setValue("Ca tối", doanhThuCaToi);
        return dataset;
    }
    private JFreeChart createBarChart(CategoryDataset dataset, String title, String categoryAxisLabel, String valueAxisLabel) {
    	JFreeChart chart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setLabel(""); 

        BarRenderer renderer = new BarRenderer();
        renderer.setSeriesPaint(0, new Color(65,105,225)); 
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        renderer.setBaseItemLabelsVisible(true); 
        renderer.setShadowVisible(false);
        plot.setRenderer(renderer);
        
        return chart;
    }

    private CategoryDataset createWeeklyDataset(ArrayList<HoaDon> dsHD) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Double> weeklyData = new LinkedHashMap<>();
        Arrays.asList("Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật").forEach(day -> weeklyData.put(day, 0.0));

        for (HoaDon hd : dsHD) {
            LocalDateTime date = hd.getThoiGian();
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String dayLabel = dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.forLanguageTag("vi-VN"));
            weeklyData.put(dayLabel, weeklyData.get(dayLabel) + hd.getThanhTien());
        }

        for (Map.Entry<String, Double> entry : weeklyData.entrySet()) {
            dataset.addValue(entry.getValue(), "Doanh thu", entry.getKey());
        }
        return dataset;
    }
    public PieDataset createQuarterlyPieDataset(CategoryDataset categoryDataset) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        
        
        double[] quarterlyRevenue = new double[4];
        
        
        for (int monthIndex = 0; monthIndex < 12; monthIndex++) {
            Number revenue = categoryDataset.getValue("Doanh thu", "Th " + (monthIndex + 1));
            if (revenue != null) {
                int quarterIndex = monthIndex / 3; 
                quarterlyRevenue[quarterIndex] += revenue.doubleValue();
            }
        }
        
        
        String[] quarters = {"Quý 1", "Quý 2", "Quý 3", "Quý 4"};
        for (int i = 0; i < 4; i++) {
            pieDataset.setValue(quarters[i], quarterlyRevenue[i]);
        }
        
        return pieDataset;
    }

    private CategoryDataset createQuarterlyDataset(ArrayList<HoaDon> dsHD) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double[] monthlyRevenue = new double[12];

        
        for (HoaDon hd : dsHD) {
            LocalDateTime date = hd.getThoiGian();
            int monthIndex = date.getMonthValue() - 1; 
            monthlyRevenue[monthIndex] += hd.getThanhTien();
        }

        
        String[] months = {"Th 1", "Th 2", "Th 3", "Th 4", "Th 5", "Th 6", 
                           "Th 7", "Th 8", "Th 9", "Th 10", "Th 11", "Th 12"};

        for (int i = 0; i < 12; i++) {
            dataset.addValue(monthlyRevenue[i], "Doanh thu", months[i]);
        }

        return dataset;
    }

    
    public JPanel getPanel() {
    	return this.p_body_ThongKe;
    }
}