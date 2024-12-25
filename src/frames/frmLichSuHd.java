package frames;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.RowFilter.Entry;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.ChiTietNhapHang_DAO;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import dao.PhieuNhapHang_DAO;
import dao.SanPham_DAO;
import entities.ChiTietHoaDon;
import entities.ChiTietNhapHang;
import entities.HoaDon;
import entities.NhanVien;
import entities.PhieuNhapHang;
import entities.SanPham;

public class frmLichSuHd extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTimLS;
	private JTable tableHD;
	private JTextField txtNgay;
	private JTextField txtThang;
	private JTextField txtNam;
	private DefaultTableModel tableModelHD;
	private DefaultTableModel tableModelNH;
	private JTable tableNH;
	private int selectedIndex;
	private JButton btnLoc;
	private JButton btnDatLai;
	private JButton btnTimLS;
	private JButton btnChiTiet;
	NhanVien nv = new NhanVien("NV00000", "Admin", "Quản lý", LocalDate.parse("2024-01-01"), "hihi@gmail.com", "012345678a");
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private TableRowSorter sorterHD;
	private TableRowSorter sorterPNH;
	private JPanel p_body_ls;
	ConnectDB con;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLichSuHd frame = new frmLichSuHd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	});
	}

	/**
	 * Create the frame.
	 */
	public frmLichSuHd() {
		con = new ConnectDB();
		con.getInstance().connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(1536, 864);
		this.setLocationRelativeTo(this);
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
		
		p_body_ls = new JPanel();
		p_body_ls.setBackground(new Color(255, 255, 255));
		p_body_ls.setBorder(new EmptyBorder(0, 0, 0, 0));
		p_body_ls.setBounds(254, 99, 1268, 728);
		contentPane.add(p_body_ls);
		p_body_ls.setLayout(null);
		
		JPanel pHeadLS = new JPanel();
		pHeadLS.setBounds(34, 37, 1207, 116);
		p_body_ls.add(pHeadLS);
		pHeadLS.setLayout(null);
		
		txtTimLS = new JTextField();
		txtTimLS.setBounds(264, 35, 559, 37);
		pHeadLS.add(txtTimLS);
		txtTimLS.setColumns(10);
		
		btnTimLS = new JButton("Tìm");
		btnTimLS.setBackground(new Color(153, 51, 255));
		btnTimLS.setForeground(new Color(255, 255, 255));
		btnTimLS.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTimLS.setBounds(866, 35, 112, 37);
		pHeadLS.add(btnTimLS);
		
		JLabel lblTimMaHD = new JLabel("Nhập mã hóa đơn");
		lblTimMaHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimMaHD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTimMaHD.setBounds(10, 34, 277, 37);
		pHeadLS.add(lblTimMaHD);
		
		btnChiTiet = new JButton("Chi tiết");
		btnChiTiet.setForeground(new Color(255, 255, 255));
		btnChiTiet.setBackground(new Color(0, 255, 102));
		btnChiTiet.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChiTiet.setBounds(1020, 35, 112, 37);
		pHeadLS.add(btnChiTiet);
		
		
		JPanel pContainer = new JPanel();
		pContainer.setBounds(34, 191, 1208, 537);
		p_body_ls.add(pContainer);
		pContainer.setLayout(null);
		
		JScrollPane scrollPaneHD = new JScrollPane();
		scrollPaneHD.setBounds(0, 0, 941, 516);
		
		String[] colnamesHD = {
				"Mã hoá đơn", "Tên nhân viên", "Thời gian", "Số lượng sản phẩm", "Thành tiền", "Phương thức TT"
		};
		tableModelHD = new DefaultTableModel(colnamesHD, 0);
		tableHD = new JTable(tableModelHD);
		scrollPaneHD.setViewportView(tableHD);
		
		JScrollPane scrollPaneNH = new JScrollPane();
		scrollPaneNH.setBounds(0, 0, 941, 516);
		
		String[] colnamesNH = {
				"Mã phiếu nhập hàng", "Tên nhân viên", "Thời gian", "Số lượng sản phẩm", "Thành tiền"
		};
		tableModelNH = new DefaultTableModel(colnamesNH, 0);
		tableNH = new JTable(tableModelNH);
		scrollPaneNH.setViewportView(tableNH);
		
		JPanel pLoc = new JPanel();
		pLoc.setBounds(940, 0, 268, 516);
		pContainer.add(pLoc);
		pLoc.setLayout(null);
		
		JPanel pHeadLoc = new JPanel();
		pHeadLoc.setBackground(new Color(0, 51, 255));
		pHeadLoc.setBounds(0, 0, 268, 47);
		pLoc.add(pHeadLoc);
		pHeadLoc.setLayout(null);
		
		JLabel lblHeadLoc = new JLabel("Lọc hóa đơn");
		lblHeadLoc.setBounds(80, 10, 111, 22);
		lblHeadLoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadLoc.setForeground(Color.WHITE);
		lblHeadLoc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHeadLoc.setBackground(new Color(51, 153, 51));
		pHeadLoc.add(lblHeadLoc);
		
		JLabel lblNgay = new JLabel("Ngày:");
		lblNgay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNgay.setBounds(10, 69, 69, 28);
		pLoc.add(lblNgay);
		
		txtNgay = new JTextField();
		txtNgay.setBounds(89, 69, 151, 28);
		pLoc.add(txtNgay);
		txtNgay.setColumns(10);
		
		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblThang.setBounds(10, 126, 69, 28);
		pLoc.add(lblThang);
		
		txtThang = new JTextField();
		txtThang.setColumns(10);
		txtThang.setBounds(89, 126, 151, 28);
		pLoc.add(txtThang);
		
		JLabel lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNam.setBounds(10, 182, 69, 28);
		pLoc.add(lblNam);
		
		txtNam = new JTextField();
		txtNam.setColumns(10);
		txtNam.setBounds(89, 182, 151, 28);
		pLoc.add(txtNam);
		
		btnLoc = new JButton("Lọc");
		btnLoc.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLoc.setBounds(10, 429, 109, 37);
		pLoc.add(btnLoc);
		
		btnDatLai = new JButton("Đặt lại");
		btnDatLai.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDatLai.setBounds(149, 429, 109, 37);
		pLoc.add(btnDatLai);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 941, 516);
		pContainer.add(tabbedPane);
		tabbedPane.addTab("Hóa đơn",scrollPaneHD);
		tabbedPane.addTab("Nhập hàng", scrollPaneNH);
		
		JTableHeader tableHeaderHD = tableHD.getTableHeader();
		tableHeaderHD.setFont(new Font("Arial", Font.BOLD, 16));
		
		JTableHeader tableHeaderNH = tableNH.getTableHeader();
		tableHeaderNH.setFont(new Font("Arial", Font.BOLD, 16));
		
		tableHD.getTableHeader().setReorderingAllowed(false);
		for (int z = 0; z < tableHD.getColumnModel().getColumnCount(); z++) {
	        TableColumn column = tableHD.getColumnModel().getColumn(z);
	        column.setResizable(false); // Tắt tính năng điều chỉnh độ rộng của cột
		}
		tableNH.getTableHeader().setReorderingAllowed(false);
		for (int z = 0; z < tableNH.getColumnModel().getColumnCount(); z++) {
	        TableColumn column = tableNH.getColumnModel().getColumn(z);
	        column.setResizable(false); // Tắt tính năng điều chỉnh độ rộng của cột
		}
		tabbedPane.addChangeListener(e -> {
		    selectedIndex = tabbedPane.getSelectedIndex();
		});
        
		btnTimLS.addActionListener(this);
		btnLoc.addActionListener(this);
		btnDatLai.addActionListener(this);
		btnDatLai.addActionListener(this);
		btnChiTiet.addActionListener(this);
		themHoaDon();
		themPhieuNhapHang();
	}
	public void themHoaDon() {
		ArrayList<HoaDon> dsHD = HoaDon_DAO.layTatCaHoaDon();
		for (HoaDon hoaDon : dsHD) {
			NhanVien nv;		
			nv = NhanVien_DAO.getNV(hoaDon.getMaNV());
			String[] dataRow = { hoaDon.getMaHD(), nv.getTenNV(),hoaDon.getThoiGian().format(formatter), hoaDon.getTongSoLuongSP() + "", hoaDon.getThanhTien() + "", hoaDon.getPhuongThucTT() };
	        tableModelHD.addRow(dataRow);
			
			
		}
	}
	public void themPhieuNhapHang() {
		ArrayList<PhieuNhapHang> dsPNH = PhieuNhapHang_DAO.layTatCaHoaDon();
		for (PhieuNhapHang pnh : dsPNH) {
			String[] dataRow = { pnh.getMaPNH(), pnh.getTenNV(), pnh.getThoiGian().format(formatter), pnh.getTongSoLuongSP() + "", pnh.getThanhTien() + "" };
	        tableModelNH.addRow(dataRow);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTimLS)) {
			String txtTim = txtTimLS.getText();
			if(!tim(txtTim)) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã");
			}
		} else if (o.equals(btnLoc)) {
			loc();
		} else if (o.equals(btnDatLai)) {
			datLaiTable();
		} else if (o.equals(btnChiTiet)) {
			xemChiTietHD();
		}
	}
	public boolean tim(String txtTim) {
		if (selectedIndex == 0) {
			for (int i = 0; i < tableHD.getRowCount(); i++) {
		        String cellValue = tableHD.getValueAt(i, 0).toString();
		        if (cellValue.contains(txtTim)) {
		            tableHD.setRowSelectionInterval(i, i);
		            tableHD.scrollRectToVisible(tableHD.getCellRect(i, 0, true)); 
		            return true;
			    }
			}
		} else {
			for (int i = 0; i < tableNH.getRowCount(); i++) {
			    for (int j = 0; j < tableNH.getColumnCount(); j++) {
			        String cellValue = tableNH.getValueAt(i, j).toString();
			        if (cellValue.contains(txtTim)) {
			            tableNH.setRowSelectionInterval(i, i);
			            tableNH.scrollRectToVisible(tableNH.getCellRect(i, 0, true)); // Cuộn đến hàng
			            return true;
			        }
			    }
			}
		}
		return false;
	}	
	public void loc() {
		String day = txtNgay.getText().trim();
        String month = txtThang.getText().trim();
        String year = txtNam.getText().trim();

        // Tạo bộ lọc tùy theo trường nào được nhập
        RowFilter<TableModel, Object> dateFilter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends TableModel, ? extends Object> entry) {
                try {
                    String dateStr = (String) entry.getValue(2); // Cột thứ 3 chứa ngày tháng
                    LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
                    // Lấy ngày, tháng, năm
                    int rowDay = dateTime.getDayOfMonth();
                    int rowMonth = dateTime.getMonthValue();
                    int rowYear = dateTime.getYear();
                    
                    boolean match = true;
                    if (!year.isEmpty()) {
                        match = match && rowYear == Integer.parseInt(year);
                    }
                    if (!month.isEmpty()) {
                        match = match && rowMonth == Integer.parseInt(month);
                    }
                    if (!day.isEmpty()) {
                        match = match && rowDay == Integer.parseInt(day);
                    }
                    return match;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        };
        if (selectedIndex == 0) {
        	sorterHD = new TableRowSorter<>(tableHD.getModel());
            tableHD.setRowSorter(sorterHD);
            sorterHD.setRowFilter(dateFilter);
            if (tableHD.getRowCount() == 0) {
            	JOptionPane.showMessageDialog(this, "Không có hóa đơn hợp lệ");
            	datLaiTable();
            }
        } else {
        	sorterPNH = new TableRowSorter<>(tableNH.getModel());
            tableNH.setRowSorter(sorterPNH);
            sorterPNH.setRowFilter(dateFilter);
            if (tableNH.getRowCount() == 0) {
            	JOptionPane.showMessageDialog(this, "Không có phiếu nhập hàng hợp lệ");
            	datLaiTable();
            }
        }
	}
	public void xoaTxtNgayThangNam() {
		txtNgay.setText("");
        txtThang.setText("");
        txtNam.setText("");
	}
	public void datLaiTable() {
		if (selectedIndex == 0) {
            sorterHD.setRowFilter(null);
            xoaTxtNgayThangNam();
		} else {
			sorterPNH.setRowFilter(null);
            xoaTxtNgayThangNam();
		}
	}
	
	public void xemChiTietHD() {
		int rowHD = tableHD.getSelectedRow();
		int rowPNH = tableNH.getSelectedRow();
		if (rowHD >= 0 || rowPNH >=0) {
			
			// Tạo JDialog mới
			JDialog dialog;
	        if (selectedIndex ==0) {
	        	dialog = new JDialog(this,"Chi tiết hóa đơn", true);
	        } else {
	        	dialog = new JDialog(this,"Chi tiết phiếu nhập hàng", true);
	        }
	        dialog.setSize(700, 500);
	        dialog.setResizable(false);
	        dialog.getContentPane().setLayout(new BorderLayout());
	
	        String[] colnames = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền"};
	        DefaultTableModel tableChiTietModel = new DefaultTableModel(colnames, 0);
	        JTable tableChiTiet = new JTable(tableChiTietModel);
	        dialog.getContentPane().add(new JScrollPane(tableChiTiet), BorderLayout.CENTER);
	        if (selectedIndex == 0) {
		        String maHD = tableHD.getValueAt(rowHD, 0).toString();
				ArrayList<ChiTietHoaDon> dsChiTietHD = ChiTietHoaDon_DAO.timChiTietHoaDon(maHD);
		        //thêm dữ liệu vào table
		        for (ChiTietHoaDon chiTietHD : dsChiTietHD) {
		        	SanPham sp = SanPham_DAO.timSanPham(chiTietHD.getMaSP());
		        	double tongTien = chiTietHD.getSoLuongSP() * chiTietHD.getDonGia();
		        	String[] data = {sp.getMaSP(), sp.getTenSP(), chiTietHD.getSoLuongSP()+"", chiTietHD.getDonGia()+"", tongTien+""};
		        	tableChiTietModel.addRow(data);
		        }
	        } else {
	        	String maPNH = tableNH.getValueAt(rowPNH, 0).toString();
				ArrayList<ChiTietNhapHang> dsChiTietPNH = ChiTietNhapHang_DAO.timChiTietNhapHang(maPNH);
		        //thêm dữ liệu vào table
		        for (ChiTietNhapHang chiTietPNH : dsChiTietPNH) {
		        	SanPham sp = SanPham_DAO.timSanPham(chiTietPNH.getMaSP());
		        	double tongTien = chiTietPNH.getSoLuongSP() * chiTietPNH.getDonGia();
		        	String[] data = {sp.getMaSP(), sp.getTenSP(), chiTietPNH.getSoLuongSP()+"", chiTietPNH.getDonGia()+"", tongTien+""};
		        	tableChiTietModel.addRow(data);
		        }
	        }
	        // Thêm nút OK vào JDialog
	        JButton okButton = new JButton("OK");
	        okButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dialog.dispose(); // Đóng JDialog khi nhấn OK
	            }
	        });
	        JPanel pQRSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        pQRSouth.add(okButton);
	        dialog.getContentPane().add(pQRSouth, BorderLayout.SOUTH);
	
	        dialog.setLocationRelativeTo(this);
	        dialog.setVisible(true);
	        
		} else {
			JOptionPane.showMessageDialog(this, "Chọn hóa đơn cần xem chi tiết");
		}
	}
	public JPanel getPanel() {
		return p_body_ls;
	}
}