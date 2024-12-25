package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhoHang_DAO;
import dao.SanPham_DAO;
import entities.NhanVien;
import entities.QuanLySanPham;
import entities.SanPham;
import fileReader.XMLReader;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.border.EtchedBorder;
import javax.swing.JSpinner;

public class frmKhoHang extends JFrame implements ActionListener, MouseInputListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private QuanLySanPham ds = new QuanLySanPham();
	private QuanLySanPham ds2 = new QuanLySanPham();
	private JTextField tftk_gia1;
	private JTextField tftk;
	private JTextField tftk_nguon;
	private JTextField tftk_gia2;
	private JTable table, tb;
	NhanVien nv = new NhanVien("NV00000", "Admin", "Quản lý", LocalDate.parse("2024-01-01"), "hihi@gmail.com", "012345678a");
	private DefaultTableModel dtm, minidtm;
	JComboBox<String> cbTinhTrang;
	JComboBox<String> cbsapxep;
	JPanel p_body_kh;
	
	String[] cols = {"Mã SP", "Tên sản phẩm", "Giá nhập", "Giá bán", "Số lượng tồn", "Tình trạng", "Nhà cung cấp", "Ngày cập nhật"};
	String[] choice = {"Tất cả", "Còn hàng", "Thiếu hàng", "Hết hàng"};
	
    private JTextField tfnguon1;
    private JTextField tfgia1;
    private JTextField tften1;
    private JTextField tfma1;
    private JTextField tfma2, tften2, tfgia2, tfnguon2, tflink;
    JSpinner tfsl1, tfsl2;
    JButton btnThemData, btnHuy, btnSearch, btnsua, btnxoa;
    ConnectDB con;
    JDialog dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmKhoHang frame = new frmKhoHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public void createThemDialog() {
		dialog = new JDialog(this, "Thêm sản phẩm", true);
		dialog.setBounds(319, 78, 873, 573);
//		contentPane.add(internalFrame);
		dialog.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 188, 815, 271);
		dialog.getContentPane().add(scrollPane);
		String[] cols2 = {"Mã SP", "Tên sản phẩm", "Giá nhập", "Giá bán", "Số lượng", "Nhà cung cấp"};
		tb = new JTable();
		minidtm = new DefaultTableModel(cols2, 0);
		tb.setModel(minidtm);
		tb.addMouseListener(this);
		scrollPane.setViewportView(tb);
		
		btnThemData = new JButton("THÊM VÀO DATA");
		btnThemData.addActionListener(this);
		btnThemData.setBackground(new Color(0, 204, 102));
		btnThemData.setForeground(new Color(255, 255, 255));
		btnThemData.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThemData.setBounds(445, 483, 160, 41);
		dialog.getContentPane().add(btnThemData);
		
		btnHuy = new JButton("HỦY");
		btnHuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				
			}
		});
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setBackground(new Color(255, 0, 0));
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuy.setBounds(269, 483, 148, 41);
		dialog.getContentPane().add(btnHuy);
		
		JLabel lblNewLabel = new JLabel("Mã SP:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(37, 21, 91, 24);
		dialog.getContentPane().add(lblNewLabel);
		
		tfma2 = new JTextField();
		tfma2.setBounds(33, 48, 148, 33);
		dialog.getContentPane().add(tfma2);
		tfma2.setColumns(10);
		
		tften2 = new JTextField();
		tften2.setColumns(10);
		tften2.setBounds(210, 48, 148, 33);
		dialog.getContentPane().add(tften2);
		
		JLabel lblTnSp = new JLabel("Tên SP:");
		lblTnSp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnSp.setBounds(214, 21, 91, 24);
		dialog.getContentPane().add(lblTnSp);
		
		tfgia2 = new JTextField();
		tfgia2.setColumns(10);
		tfgia2.setBounds(385, 48, 148, 33);
		dialog.getContentPane().add(tfgia2);
		
		JLabel lblGiNhp = new JLabel("Giá nhập:");
		lblGiNhp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiNhp.setBounds(389, 21, 91, 24);
		dialog.getContentPane().add(lblGiNhp);
		
		tfnguon2 = new JTextField();
		tfnguon2.setColumns(10);
		tfnguon2.setBounds(681, 48, 148, 33);
		dialog.getContentPane().add(tfnguon2);
		
		JLabel lblNgunHng = new JLabel("Nguồn hàng:");
		lblNgunHng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgunHng.setBounds(681, 21, 118, 24);
		dialog.getContentPane().add(lblNgunHng);
		
		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSLng.setBounds(567, 21, 118, 24);
		dialog.getContentPane().add(lblSLng);
		
		tfsl2 = new JSpinner();
		tfsl2.setBounds(567, 48, 83, 33);
		dialog.getContentPane().add(tfsl2);
		
		tflink = new JTextField();
		tflink.setColumns(10);
		tflink.setBounds(33, 125, 447, 33);
		dialog.getContentPane().add(tflink);
		
		JLabel lblHocNhpng = new JLabel("Hoặc nhập đường đãn đến file .xml");
		lblHocNhpng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHocNhpng.setBounds(33, 100, 325, 24);
		dialog.getContentPane().add(lblHocNhpng);
		
		JButton btnNewButton_2 = new JButton("Nhập");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBackground(new Color(153, 102, 255));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(490, 125, 91, 33);
		dialog.getContentPane().add(btnNewButton_2);
		
		JButton btnxoa2 = new JButton("XÓA");
		btnxoa2.addActionListener(this);
		btnxoa2.setForeground(Color.WHITE);
		btnxoa2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnxoa2.setBackground(new Color(153, 0, 255));
		btnxoa2.setBounds(601, 111, 100, 47);
		dialog.getContentPane().add(btnxoa2);
		
		JButton btnthem2 = new JButton("THÊM");
		btnthem2.addActionListener(this);
		btnthem2.setForeground(Color.WHITE);
		btnthem2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnthem2.setBackground(new Color(153, 0, 255));
		btnthem2.setBounds(721, 111, 100, 47);
		dialog.getContentPane().add(btnthem2);
		dialog.setVisible(true);
	}
	
	public frmKhoHang() throws SQLException {
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
		mi_tt.setBounds(25, 26, 231, 90);
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
		mi_ls.setBounds(25, 118, 231, 90);
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
		mi_kh.setBounds(25, 210, 231, 90);
		p_menu.add(mi_kh);
		
		originalIcon = new ImageIcon("./src/img/group.png");
        img = originalIcon.getImage();
        scaledImg = img.getScaledInstance(35, 30, Image.SCALE_SMOOTH); 
        scaledIcon = new ImageIcon(scaledImg);
		JMenuItem mi_nv = new JMenuItem("    NHÂN SỰ");
		mi_nv.setIcon(scaledIcon);
		mi_nv.setBackground(new Color(153, 0, 204));
		mi_nv.setHorizontalAlignment(SwingConstants.LEFT);
		mi_nv.setForeground(Color.WHITE);
		mi_nv.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mi_nv.setBounds(25, 303, 231, 90);
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
		mi_tk.setBounds(25, 395, 231, 90);
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
		
		//
		
		p_body_kh = new JPanel();
		p_body_kh.setBounds(255, 99, 1302, 771);
		contentPane.add(p_body_kh);
		p_body_kh.setBackground(new Color(255, 255, 255));
		p_body_kh.setBorder(new EmptyBorder(0, 0, 0, 0));
		p_body_kh.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setLayout(null);
		panel_1.setToolTipText("");
		panel_1.setBackground(new Color(204, 204, 204));
		panel_1.setBounds(23, 25, 1245, 112);
		p_body_kh.add(panel_1);
		
		btnsua = new JButton("Chỉnh Sửa");
		btnsua.addActionListener(this);
		btnsua.setForeground(Color.WHITE);
		btnsua.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnsua.setBackground(new Color(204, 0, 255));
		btnsua.setBounds(938, 10, 154, 35);
		panel_1.add(btnsua);
		
		btnxoa = new JButton("Xóa");
		btnxoa.addActionListener(this);
		btnxoa.setForeground(Color.WHITE);
		btnxoa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnxoa.setBackground(new Color(204, 0, 255));
		btnxoa.setBounds(938, 55, 154, 35);
		panel_1.add(btnxoa);
		
		tfnguon1 = new JTextField();
		tfnguon1.setColumns(10);
		tfnguon1.setBounds(786, 45, 125, 29);
		panel_1.add(tfnguon1);
		
		JLabel lblNgunHng = new JLabel("Nguồn hàng:");
		lblNgunHng.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgunHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgunHng.setBounds(786, 23, 95, 23);
		panel_1.add(lblNgunHng);
		
		tfsl1 = new JSpinner();
		tfsl1.setBounds(649, 45, 102, 29);
		panel_1.add(tfsl1);
		
		JLabel lblGi_1_2_2_1 = new JLabel("Số lượng:");
		lblGi_1_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGi_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGi_1_2_2_1.setBounds(649, 20, 102, 23);
		panel_1.add(lblGi_1_2_2_1);
		
		tfgia1 = new JTextField();
		tfgia1.setColumns(10);
		tfgia1.setBounds(495, 45, 120, 29);
		panel_1.add(tfgia1);
		
		JLabel lblGi = new JLabel("Giá:");
		lblGi.setHorizontalAlignment(SwingConstants.LEFT);
		lblGi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGi.setBounds(495, 23, 45, 23);
		panel_1.add(lblGi);
		
		JButton btncheck = new JButton("Kiểm tra");
		btncheck.addActionListener(this);
		btncheck.setForeground(Color.WHITE);
		btncheck.setFont(new Font("Tahoma", Font.BOLD, 12));
		btncheck.setBackground(new Color(204, 0, 255));
		btncheck.setBounds(175, 47, 85, 23);
		panel_1.add(btncheck);
		
		tften1 = new JTextField();
		tften1.setColumns(10);
		tften1.setBounds(292, 45, 164, 29);
		panel_1.add(tften1);
		
		JLabel lblTnSp = new JLabel("Tên SP:");
		lblTnSp.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnSp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnSp.setBounds(292, 23, 58, 23);
		panel_1.add(lblTnSp);
		
		tfma1 = new JTextField();
		tfma1.setColumns(10);
		tfma1.setBounds(20, 45, 145, 29);
		panel_1.add(tfma1);
		
		JLabel lblNewLabel = new JLabel("Mã:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 23, 45, 23);
		panel_1.add(lblNewLabel);
		
		JButton btnthem = new JButton("THÊM SP");
		btnthem.addActionListener(this);
		btnthem.setBackground(new Color(0, 204, 0));
		btnthem.setForeground(new Color(255, 255, 255));
		btnthem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnthem.setBounds(1115, 10, 102, 80);
		panel_1.add(btnthem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 228, 1035, 471);
		p_body_kh.add(scrollPane);
		
		table = new JTable();
		dtm = new DefaultTableModel(cols, 0);
		
		table.setModel(dtm);
		table.addMouseListener(this);
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "B\u1ED9 l\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(1084, 156, 184, 543);
		p_body_kh.add(panel_3);
		panel_3.setLayout(null);
		
		tftk_gia1 = new JTextField();
		tftk_gia1.setBounds(46, 124, 127, 29);
		panel_3.add(tftk_gia1);
		tftk_gia1.setColumns(10);
		
		JLabel lblGi_1_1 = new JLabel("Từ:");
		lblGi_1_1.setBounds(10, 130, 45, 23);
		panel_3.add(lblGi_1_1);
		lblGi_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGi_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblGi_1 = new JLabel("Giá:");
		lblGi_1.setBounds(12, 97, 45, 23);
		panel_3.add(lblGi_1);
		lblGi_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGi_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tftk_gia2 = new JTextField();
		tftk_gia2.setBounds(46, 163, 127, 29);
		panel_3.add(tftk_gia2);
		tftk_gia2.setColumns(10);
		
		JLabel lblGi_1_1_1 = new JLabel("Đến:");
		lblGi_1_1_1.setBounds(12, 169, 45, 23);
		panel_3.add(lblGi_1_1_1);
		lblGi_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGi_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tftk_nguon = new JTextField();
		tftk_nguon.setBounds(10, 235, 163, 29);
		panel_3.add(tftk_nguon);
		tftk_nguon.setColumns(10);
		
		JLabel lblNgunHng_1 = new JLabel("Nguồn hàng:");
		lblNgunHng_1.setBounds(10, 210, 95, 23);
		panel_3.add(lblNgunHng_1);
		lblNgunHng_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgunHng_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnreset = new JButton("Khôi phục");
		btnreset.addActionListener(this);
		btnreset.setBounds(28, 447, 131, 34);
		panel_3.add(btnreset);
		btnreset.setForeground(new Color(255, 255, 255));
		btnreset.setBackground(new Color(204, 0, 255));
		btnreset.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btntk = new JButton("Tìm kiếm");
		btntk.addActionListener(this);
		btntk.setBounds(28, 403, 131, 34);
		panel_3.add(btntk);
		btntk.setForeground(new Color(255, 255, 255));
		btntk.setBackground(new Color(204, 0, 255));
		btntk.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		cbTinhTrang = new JComboBox<String>(choice);
		cbTinhTrang.setBounds(10, 58, 163, 29);
		panel_3.add(cbTinhTrang);
		
		JLabel lblNgunHng_1_1 = new JLabel("Tình trạng:");
		lblNgunHng_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgunHng_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgunHng_1_1.setBounds(10, 30, 95, 23);
		panel_3.add(lblNgunHng_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(23, 156, 1035, 52);
		p_body_kh.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Sắp xếp theo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(31, 10, 125, 32);
		panel_2.add(lblNewLabel_1);
		
		cbsapxep = new JComboBox<String>();
		cbsapxep.addActionListener(this);
		cbsapxep.addItem("Ngày thêm mới nhất");
		cbsapxep.addItem("Ngày thêm cũ nhất");
		cbsapxep.addItem("Giá tăng dần");
		cbsapxep.addItem("Giá giảm dần");
		cbsapxep.addItem("Số lượng tăng dần");
		cbsapxep.addItem("Số lượng giảm dần");
		cbsapxep.setBounds(146, 10, 246, 29);
		panel_2.add(cbsapxep);
		
		tftk = new JTextField();
		tftk.setColumns(10);
		tftk.setBounds(541, 10, 265, 29);
		panel_2.add(tftk);
		
		JLabel lblNgunHng_1_1_1 = new JLabel("Từ khóa:");
		lblNgunHng_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgunHng_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgunHng_1_1_1.setBounds(469, 15, 95, 23);
		panel_2.add(lblNgunHng_1_1_1);
		
		btnSearch = new JButton("Tìm");
		btnSearch.addActionListener(this);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBackground(new Color(0, 51, 255));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBounds(827, 10, 85, 29);
		panel_2.add(btnSearch);
		checkRole();
		refresh();
	}
	///
	public void refresh() {
        String dk = (String)cbsapxep.getSelectedItem();
        
        String sql ="Select * from SanPham Where";
        String cond2 = " Order By";
        
        if (dk.equals("Ngày thêm mới nhất")) cond2+=" ngayCapNhat ASC";
        else if (dk.equals("Ngày thêm cũ nhất")) cond2+=" ngayCapNhat DESC";
        else if (dk.equals("Giá tăng dần")) cond2+=" giaBan DESC";
        else if (dk.equals("Giá giảm dần")) cond2+=" giaBan ASC";
        else if (dk.equals("Số lượng tăng dần")) cond2+=" soLuongTon DESC";
        else if (dk.equals("Số lượng giảm dần")) cond2+=" soLuongTon ASC";
        
        String con1 = String.valueOf(cbTinhTrang.getSelectedItem());
        String con2 = tftk_gia1.getText().trim();
        String con3 = tftk_gia2.getText().trim();
        String con4 = tftk_nguon.getText().trim();
        
        String cond="";
        
		if (con1.equals("Còn hàng")) cond+=" (soLuongTon>5) ";
		else if (con1.equals("Thiếu hàng")) cond+=" (soLuongTon<=5) and (soLuongTon>=1) "; 
		else if (con1.equals("Hết hàng")) cond+=" (soLuongTon=0) ";

        if (!con2.equals("")) {
        	if (!cond.equals("")) {
        		cond+=" and";
        	}
        	cond+=" (giaBan>="+con2+")";
        }
        if (!con3.equals("")) {
        	if (!cond.equals("")) {
        		cond+=" and";
        	}
        	cond+=" (giaBan<="+con3+")";
        }
        if (!con4.equals("")) {
        	if (!cond.equals("")) {
        		cond+=" and";
        	}
        	cond+=" (nhaCungCap= '"+con4+"')";
        }
        
        if (cond.equals("")) {
        	cond+=" 1=1";
        }
        System.out.println(sql+cond+cond2);
        ds = KhoHang_DAO.filter(sql+cond+cond2);
        dtm.setRowCount(0);
        for (SanPham x: ds.getDs()) {
        	dtm.addRow(x.toObj());
        }
	}
	///

	
	public void tim(){
        String sql = "Select * from SanPham Where ";
        String con1 = String.valueOf(cbTinhTrang.getSelectedItem());
        String con2 = tftk_gia1.getText().trim();
        String con3 = tftk_gia2.getText().trim();
        String con4 = tftk_nguon.getText().trim();
        
        String cond = "";

		if (con1.equals("Còn hàng")) cond+=" (soLuongTon>5) ";
		else if (con1.equals("Thiếu hàng")) cond+=" (soLuongTon<=5) and (soLuongTon>=1) "; 
		else if (con1.equals("Hết hàng")) cond+=" (soLuongTon=0) ";

        if (!con2.equals("")) {
        	if (!cond.equals("")) {
        		cond+=" and";
        	}
        	cond+=" (giaBan>="+con2+")";
        }
        if (!con3.equals("")) {
        	if (!cond.equals("")) {
        		cond+=" and";
        	}
        	cond+=" (giaBan<="+con3+")";
        }
        if (!con4.equals("")) {
        	if (!cond.equals("")) {
        		cond+=" and";
        	}
        	cond+=" (nhaCungCap= '"+con4+"')";
        }
        
        if (cond.equals("")) {
        	cond+="1=1";
        }
        
        System.out.println(sql+cond);
        dtm.setRowCount(0);
        ds = KhoHang_DAO.filter(sql+cond);
        refresh();
	}
	
	public void setValues() {	
		int idx = table.getSelectedRow();
		tfma1.setText(String.valueOf(table.getValueAt(idx, 0)));
		tften1.setText(String.valueOf(table.getValueAt(idx, 1)));
		tfgia1.setText(String.valueOf(table.getValueAt(idx, 2)));
		tfsl1.setValue(table.getValueAt(idx, 4));
		tfnguon1.setText(String.valueOf(table.getValueAt(idx, 6)));
		System.out.println(""+table.getValueAt(idx, 0)+ table.getValueAt(idx, 1)+ table.getValueAt(idx, 2)+ table.getValueAt(idx, 3)+ table.getValueAt(idx, 4)+ table.getValueAt(idx, 5)+ table.getValueAt(idx, 6));
	}
	
	public void checkRole() {
		if (Application.account.getChucVu().equals("Nhân viên")) {
			btnsua.setEnabled(false);
			btnxoa.setEnabled(false);
		}
	}
	
	public boolean isEditValid() {
		if (tfma1.getText().equals("")
				|| tften1.getText().equals("")
				|| tfgia1.getText().equals("")
				|| tfnguon1.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				return false;
			}
		if (table.getSelectedRow()==-1) return false;
		String ck_ma = "^SP[0-9]{5}";
		Pattern p = Pattern.compile(ck_ma);
		String ma = tfma1.getText();
		if (!p.matcher(ma).matches()) {
			JOptionPane.showMessageDialog(null, "Mã phải theo cú pháp SPXXXXX (X từ 0 đến 9)");
			return false;
		}
		try {
			Double d = Double.parseDouble(tfgia1.getText());
			if (d<=0) {
				JOptionPane.showMessageDialog(null, "Giá phải >0");
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Giá phải là số");
			return false;
		}
		
		return true;
	}
	
	public boolean isAddValid() {
		
		if (tfma2.getText().equals("")
			|| tften2.getText().equals("")
			|| tfgia2.getText().equals("")
			|| tfnguon2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
			return false;
		}
		
		String ma = tfma2.getText();
		String ten = tften2.getText();
		int sl = (int) tfsl2.getValue();
		String nhacc = tfnguon2.getText();
		Double gia = Double.parseDouble(tfgia2.getText());
		
		SanPham x = SanPham_DAO.timSanPham(ma);
//		System.out.println(x.getMaSP()+":"+ma);
//		System.out.println(x.getTenSP()+":"+ten);
//		System.out.println(x.getGiaNhap()+":"+gia);
//		System.out.println(x.getNhaCC()+":"+nhacc);
		
		if (x!=null && !x.same(new SanPham(ma, ten, gia, gia+gia*SanPham.profit, sl, nhacc, LocalDate.now()))) {
			JOptionPane.showMessageDialog(null, "Sản phẩm "+ma+" đã tồn tại!");
			return false;
		}
		
		String ck_ma = "^SP[0-9]{5}";
		Pattern p = Pattern.compile(ck_ma);
		if (!p.matcher(ma).matches()) {
			JOptionPane.showMessageDialog(null, "Mã phải theo cú pháp SPXXXXX (X từ 0 đến 9)");
			return false;
		}
		try {
			Double d = Double.parseDouble(tfgia2.getText());
			if (d<=0) {
				JOptionPane.showMessageDialog(null, "Giá phải >0");
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Giá phải là số");
			return false;
		}
		try {
			int d = (int) tfsl2.getValue();
			if (d<=0) {
				JOptionPane.showMessageDialog(null, "Số lượng phải >0");
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Số lượng phải là số");
			return false;
		}
		
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table.getSelectedRow()!=-1) {
			setValues();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		
		if (cmd.equals("THÊM") && isAddValid()) {
			boolean flag = false;
			String id = tfma2.getText();
	        String name = tften2.getText();
	        Double price = Double.parseDouble(tfgia2.getText());
	        int sl1 = (Integer) tfsl2.getValue();
	        String nguon = tfnguon2.getText();
	        LocalDate date = LocalDate.now();
	        SanPham tmp = new SanPham(id, name, price, price+price*SanPham.profit, sl1, nguon, date);
	        for (SanPham x: ds2.getDs()) {
				if (x.same(tmp)) {	
					int idx = ds2.getDs().indexOf(x);
					tmp.setSoLuongTon(sl1+x.getSoLuongTon());
					ds2.getDs().set(idx, tmp);
					flag = true;
					break;
				}
			}
	        
	        if (flag == false) {
	        	ds2.themSP(new SanPham(id, name, price, price+price*SanPham.profit, sl1, nguon, date));
	        }
			minidtm.setRowCount(0);
			for (SanPham x: ds2.getDs()) {
				minidtm.addRow(x.toObj2());
			}
		}
		
		if (cmd.equals("XÓA")) {		
			int idx = tb.getSelectedRow();
			if (idx!=-1) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?")==JOptionPane.YES_OPTION) {
					String id = (String) tb.getValueAt(idx, 0);
					ds2.xoaSP(id);
					minidtm.setRowCount(0);
					for (SanPham x: ds2.getDs()) {
						minidtm.addRow(x.toObj2());
					}
				}
			}			
		}
		if (cmd.equals("Kiểm tra")) {
			String ma = tfma1.getText().trim();
			if (!ma.equals("")) {
				int idx = ds.timSP(ma);
				System.out.println(idx);
				if (idx!=-1) {
					table.setRowSelectionInterval(idx, idx);
					table.scrollRectToVisible(table.getCellRect(idx, 0, true));
					setValues();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Sản phẩm không tồn tại!");
			}
		}

		if (cmd.equals("Chỉnh Sửa") && isEditValid()) {
			int idx = table.getSelectedRow();
			String ma = String.valueOf(table.getValueAt(idx, 0));
			if (!ma.equals(tfma1.getText().trim())) {
				JOptionPane.showMessageDialog(null, "Không được sửa mã!");
				tfma1.setText(String.valueOf(table.getValueAt(idx, 0)));
			}
			else {
				
		        String id = tfma1.getText();
		        String name = tften1.getText();
		        Double price = Double.parseDouble(tfgia1.getText());
		        int slt = (int) tfsl1.getValue();
		        String nguon = tfnguon1.getText();
		        LocalDate date = LocalDate.now();
		        KhoHang_DAO.update(new SanPham(id, name, price, price+price*SanPham.profit, slt, nguon, date));
		        refresh();
			}
		}
		if (cmd.equals("Tìm kiếm")) {		
			tim();	
		}
		if (cmd.equals("Khôi phục")) {
			cbTinhTrang.setSelectedIndex(0);
			tftk_gia1.setText("");
			tftk_gia2.setText("");
			tftk_nguon.setText("");	
			refresh();
			
		}
		if (cmd.equals("comboBoxChanged")) {		
				refresh();
		}
		if (cmd.equals("THÊM SP")) {
			createThemDialog();
			ds2.getDs().clear();
		}
		
		if (cmd.equals("Xóa")) {
			int idx = table.getSelectedRow();
			System.out.println(idx);
			if (idx!=-1) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?")==JOptionPane.YES_OPTION) {
					String id = (String) table.getValueAt(idx, 0);
					KhoHang_DAO.removeItem(id);
					refresh();		
				}
			}
		}

		if (cmd.equals("THÊM VÀO DATA")) {
			if (ds2.getTongSo()!=0) {
				for (SanPham x: ds2.getDs()) {
					if (SanPham_DAO.timSanPham(x.getMaSP())==null) {			
						KhoHang_DAO.addItem(x);
					}
				}		
				KhoHang_DAO.sinhHoaDonNhap(ds2);
				dialog.dispose();
				refresh();
			}
		}
		if (cmd.equals("Tìm")) {	
			String tk = tftk.getText().trim();
			if (!tk.equals("")) {
				cbTinhTrang.setSelectedIndex(0);
				tftk_gia1.setText("");
				tftk_gia2.setText("");
				tftk_nguon.setText("");	
				refresh();
				QuanLySanPham tmp = new QuanLySanPham();
				for (SanPham x: ds.getDs()) {
					if (x.getTenSP().contains(tk)) {
						tmp.themSP(x);
					}
				}
				ds = tmp;
			}
			else {
				tim();
			}
			dtm.setRowCount(0);
			for (SanPham k: ds.getDs()) {
				dtm.addRow(k.toObj());
			}
		}
		if (cmd.equals("Nhập")) {
			String url = tflink.getText();
			for (SanPham x: XMLReader.ReadXML(url).getDs()) {
				if (!ds2.has(x)) {
					System.out.println("Hihi");
					ds2.themSP(x);
				}
				else {
					System.out.println("Huhu");
					ds2.suaSL(x);
				}
			}
			minidtm.setRowCount(0);
			for (SanPham x: ds2.getDs()) {
				minidtm.addRow(x.toObj2());
			}
			
		}
		
	}
	public JPanel getPannel() {
		return p_body_kh;
	}
}
