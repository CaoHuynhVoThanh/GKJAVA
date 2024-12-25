package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.JobAttributes;

import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entities.NhanVien;
import entities.QuanLyNhanVien;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.border.EtchedBorder;
import javax.swing.JInternalFrame;

public class frmNhanVien extends JFrame implements ActionListener, MouseInputListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tftkemail;
	private JTextField tftkten;
	private JTable table;
	private JTextField tfma1;
	private JTextField tften1;
	private JTextField tfemail1;
	private JTextField tfma2;
	private JTextField tften2;
	private JTextField tfemail2;
	private JTextField tfsdt2;
	private JPasswordField tfmk1, tfmk2, tfmk3, tfmk4, tfmk5;
	JPanel p_body_tt;
	String[] chucvu = {"Tất cả", "Nhân viên", "Quản lý"};
	private JTextField tfsdt1;
	private JTextField tfns1;
	private JTextField tfns2;
	JComboBox<String> tfchucvu2, cbtkchucvu, cbchucvu1;
	QuanLyNhanVien ds = new QuanLyNhanVien();
	DefaultTableModel dtm;
	NhanVien nv = new NhanVien("NV00000", "Admin", "Quản lý", LocalDate.parse("2024-01-01"), "hihi@gmail.com", "012345678a");
	String[] cols = {"Mã nhân sự", "Tên nhân sự", "Chức vụ", "Ngày sinh", "Email", "Số điện thoại"};
	private JButton tbndoimk;
	private JButton btn_sua, btn_xoa, btn_them, btn_matkhau;
	private JTextField tftksdt;
	private ConnectDB con;
	JDialog dialog1, dialog2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNhanVien frame = new frmNhanVien();
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
	
	public void createChangePassDialog() {
		dialog2 = new JDialog(this, "Đổi mật khẩu", true);
		dialog2.getContentPane().setLayout(null);
		
		tfmk3 = new JPasswordField();
		tfmk3.setColumns(10);
		tfmk3.setBounds(32, 55, 497, 29);
		dialog2.getContentPane().add(tfmk3);
		
		JLabel them_lb_tennv = new JLabel("Mật khẩu cũ:");
		them_lb_tennv.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_tennv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_tennv.setBounds(32, 30, 120, 23);
		dialog2.getContentPane().add(them_lb_tennv);
		
		tfmk4 = new JPasswordField();
		tfmk4.setColumns(10);
		tfmk4.setBounds(32, 125, 497, 29);
		dialog2.getContentPane().add(tfmk4);
		
		JLabel them_lb_tennv2 = new JLabel("Mật khẩu mới:");
		them_lb_tennv2.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_tennv2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_tennv2.setBounds(32, 100, 170, 23);
		dialog2.getContentPane().add(them_lb_tennv2);
		
		tfmk5 = new JPasswordField();
		tfmk5.setColumns(10);
		tfmk5.setBounds(32, 195, 497, 29);
		dialog2.getContentPane().add(tfmk5);
		
		JLabel them_lb_tennv3 = new JLabel("Xác nhận mật khẩu mới:");
		them_lb_tennv3.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_tennv3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_tennv3.setBounds(32, 170, 230, 23);
		dialog2.getContentPane().add(them_lb_tennv3);
		
		
		tbndoimk = new JButton("Đổi");
		tbndoimk.addActionListener(this);
		tbndoimk.setForeground(Color.WHITE);
		tbndoimk.setFont(new Font("Tahoma", Font.BOLD, 16));
		tbndoimk.setBackground(new Color(0, 204, 51));
		tbndoimk.setBounds(298, 241, 148, 41);
		dialog2.getContentPane().add(tbndoimk);
		
		JButton them_btn_huy = new JButton("Hủy");
		them_btn_huy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog2.dispose();
				
			}
		});
		them_btn_huy.setForeground(Color.WHITE);
		them_btn_huy.setFont(new Font("Tahoma", Font.BOLD, 16));
		them_btn_huy.setBackground(new Color(255, 51, 51));
		them_btn_huy.setBounds(117, 241, 148, 41);
		dialog2.getContentPane().add(them_btn_huy);
		
		dialog2.setBounds(500, 200, 575, 341);
		dialog2.setVisible(true);
	}
	
	public void createThemDialog() {
		dialog1 = new JDialog(this, "Thêm nhân sự", true);
		dialog1.getContentPane().setLayout(null);
		
		tfma2 = new JTextField();
		tfma2.setColumns(10);
		tfma2.setBounds(32, 61, 301, 29);
		dialog1.getContentPane().add(tfma2);
		
		JLabel them_lb_manv = new JLabel("Mã nhân sự:");
		them_lb_manv.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_manv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_manv.setBounds(32, 28, 148, 23);
		dialog1.getContentPane().add(them_lb_manv);
		
		tften2 = new JTextField();
		tften2.setColumns(10);
		tften2.setBounds(32, 128, 233, 29);
		dialog1.getContentPane().add(tften2);
		
		JLabel them_lb_tennv = new JLabel("Tên nhân sự:");
		them_lb_tennv.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_tennv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_tennv.setBounds(32, 103, 120, 23);
		dialog1.getContentPane().add(them_lb_tennv);
		
		tfchucvu2 = new JComboBox<String>();
		tfchucvu2.addItem("Nhân viên");
		tfchucvu2.addItem("Quản lý");
		tfchucvu2.setBounds(394, 57, 109, 25);
		dialog1.getContentPane().add(tfchucvu2);
		
		JLabel them_lb_chucvu = new JLabel("Chức vụ:");
		them_lb_chucvu.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_chucvu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_chucvu.setBounds(394, 28, 90, 23);
		dialog1.getContentPane().add(them_lb_chucvu);
		
		tfemail2 = new JTextField();
		tfemail2.setColumns(10);
		tfemail2.setBounds(32, 202, 233, 29);
		dialog1.getContentPane().add(tfemail2);
		
		JLabel them_lb_email = new JLabel("Email:");
		them_lb_email.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_email.setBounds(32, 177, 102, 23);
		dialog1.getContentPane().add(them_lb_email);
		
		tfns2 = new JTextField();
		tfns2.setColumns(10);
		tfns2.setBounds(296, 128, 233, 29);
		dialog1.getContentPane().add(tfns2);
		
		JLabel them_lb_ngaysinh = new JLabel("Ngày sinh (yyyy-mm-dd):");
		them_lb_ngaysinh.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_ngaysinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_ngaysinh.setBounds(296, 103, 170, 23);
		dialog1.getContentPane().add(them_lb_ngaysinh);
		
		tfmk1 = new JPasswordField();
		tfmk1.setColumns(10);
		tfmk1.setBounds(32, 275, 233, 29);
		dialog1.getContentPane().add(tfmk1);
		
		JLabel them_lb_mk = new JLabel("Mật khẩu:");
		them_lb_mk.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_mk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_mk.setBounds(32, 250, 95, 23);
		dialog1.getContentPane().add(them_lb_mk);
		
		tfmk2 = new JPasswordField();
		tfmk2.setColumns(10);
		tfmk2.setBounds(296, 275, 233, 29);
		dialog1.getContentPane().add(tfmk2);
		
		JLabel them_lb_mk2 = new JLabel("Xác nhận mật khẩu:");
		them_lb_mk2.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_mk2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_mk2.setBounds(296, 250, 135, 23);
		dialog1.getContentPane().add(them_lb_mk2);
		
		JButton them_btn_them = new JButton("Thêm nhân sự");
		them_btn_them.addActionListener(this);
		them_btn_them.setForeground(Color.WHITE);
		them_btn_them.setFont(new Font("Tahoma", Font.BOLD, 16));
		them_btn_them.setBackground(new Color(0, 204, 51));
		them_btn_them.setBounds(298, 331, 148, 41);
		dialog1.getContentPane().add(them_btn_them);
		
		JButton them_btn_huy = new JButton("Hủy");
		them_btn_huy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog1.dispose();
				
			}
		});
		them_btn_huy.setForeground(Color.WHITE);
		them_btn_huy.setFont(new Font("Tahoma", Font.BOLD, 16));
		them_btn_huy.setBackground(new Color(255, 51, 51));
		them_btn_huy.setBounds(117, 331, 148, 41);
		dialog1.getContentPane().add(them_btn_huy);
		
		tfsdt2 = new JTextField();
		tfsdt2.setColumns(10);
		tfsdt2.setBounds(296, 202, 233, 29);
		dialog1.getContentPane().add(tfsdt2);
		
		JLabel them_lb_sdt = new JLabel("Số điện thoại:");
		them_lb_sdt.setHorizontalAlignment(SwingConstants.LEFT);
		them_lb_sdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		them_lb_sdt.setBounds(296, 177, 150, 23);
		dialog1.getContentPane().add(them_lb_sdt);
		dialog1.setBounds(500, 100, 575, 421);
		dialog1.setVisible(true);
	}
	
	public frmNhanVien() throws SQLException {
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
		
		/////////////

		p_body_tt = new JPanel();
		p_body_tt.setBackground(new Color(255, 255, 255));
		p_body_tt.setBorder(new EmptyBorder(0, 0, 0, 0));
		p_body_tt.setBounds(249, 99, 1302, 771);
		contentPane.add(p_body_tt);
		p_body_tt.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 190, 1030, 509);
		p_body_tt.add(scrollPane);
		
		dtm = new DefaultTableModel(cols, 0);
		table = new JTable();
		table.setModel(dtm);
		scrollPane.setViewportView(table);
		table.addMouseListener(this);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "B\u1ED9 l\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(1084, 190, 184, 509);
		p_body_tt.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblGi_1 = new JLabel("Tên nhân sự:");
		lblGi_1.setBounds(9, 26, 108, 23);
		panel_3.add(lblGi_1);
		lblGi_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGi_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tftkten = new JTextField();
		tftkten.setBounds(9, 49, 163, 29);
		panel_3.add(tftkten);
		tftkten.setColumns(10);
		
		JLabel lblNgunHng_1_1 = new JLabel("Chức vụ:");
		lblNgunHng_1_1.setBounds(9, 88, 95, 33);
		panel_3.add(lblNgunHng_1_1);
		lblNgunHng_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgunHng_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		cbtkchucvu = new JComboBox<String>(chucvu);
		cbtkchucvu.setBounds(9, 118, 163, 25);
		panel_3.add(cbtkchucvu);
		
		tftkemail = new JTextField();
		tftkemail.setBounds(9, 179, 161, 29);
		panel_3.add(tftkemail);
		tftkemail.setColumns(10);
		
		JLabel lblNgunHng_1 = new JLabel("Email:");
		lblNgunHng_1.setBounds(9, 153, 95, 23);
		panel_3.add(lblNgunHng_1);
		lblNgunHng_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgunHng_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton_2_1 = new JButton("Khôi phục");
		btnNewButton_2_1.addActionListener(this);
		btnNewButton_2_1.setBounds(28, 447, 131, 34);
		panel_3.add(btnNewButton_2_1);
		btnNewButton_2_1.setForeground(new Color(255, 255, 255));
		btnNewButton_2_1.setBackground(new Color(204, 0, 255));
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnNewButton_2 = new JButton("Tìm kiếm");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(28, 403, 131, 34);
		panel_3.add(btnNewButton_2);
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(204, 0, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("");
		panel.setBorder(null);
		panel.setBackground(new Color(204, 204, 204));
		panel.setBounds(28, 23, 1229, 146);
		p_body_tt.add(panel);
		
		tfma1 = new JTextField();
		tfma1.setColumns(10);
		tfma1.setBounds(129, 20, 191, 29);
		panel.add(tfma1);
		
		JLabel lblNewLabel = new JLabel("Mã:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(72, 26, 45, 23);
		panel.add(lblNewLabel);
		
		JLabel lblTnSp = new JLabel("Tên nhân sự:");
		lblTnSp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTnSp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnSp.setBounds(26, 59, 96, 23);
		panel.add(lblTnSp);
		
		tften1 = new JTextField();
		tften1.setColumns(10);
		tften1.setBounds(132, 59, 283, 29);
		panel.add(tften1);
		
		JLabel lblChcV = new JLabel("Chức vụ:");
		lblChcV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChcV.setBounds(62, 100, 57, 23);
		panel.add(lblChcV);
		
		tfemail1 = new JTextField();
		tfemail1.setColumns(10);
		tfemail1.setBounds(528, 59, 283, 29);
		panel.add(tfemail1);
		
		JLabel lblNgunHng = new JLabel("Email:");
		lblNgunHng.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgunHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgunHng.setBounds(465, 68, 57, 23);
		panel.add(lblNgunHng);
		
		btn_sua = new JButton("Chỉnh Sửa");
		btn_sua.addActionListener(this);
		btn_sua.setForeground(Color.WHITE);
		btn_sua.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_sua.setBackground(new Color(204, 0, 255));
		btn_sua.setBounds(849, 30, 154, 35);
		panel.add(btn_sua);
		
		JButton btnNewButton_3 = new JButton("Kiểm tra");
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.setBackground(new Color(204, 0, 255));
		btnNewButton_3.setBounds(330, 22, 85, 23);
		panel.add(btnNewButton_3);
		
		btn_xoa = new JButton("Xóa");
		btn_xoa.addActionListener(this);
		btn_xoa.setForeground(Color.WHITE);
		btn_xoa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_xoa.setBackground(new Color(204, 0, 255));
		btn_xoa.setBounds(849, 81, 154, 35);
		panel.add(btn_xoa);
		
		cbchucvu1 = new JComboBox<String>();
		cbchucvu1.addItem("Nhân viên");
		cbchucvu1.addItem("Quản lý");
		cbtkchucvu.setBounds(9, 118, 163, 25);
		
		tftksdt = new JTextField();
		tftksdt.setColumns(10);
		tftksdt.setBounds(9, 244, 161, 29);
		panel_3.add(tftksdt);
		
		JLabel lblNgunHng_1_2 = new JLabel("Số điện thoại:");
		lblNgunHng_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgunHng_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgunHng_1_2.setBounds(9, 218, 95, 23);
		panel_3.add(lblNgunHng_1_2);
		cbchucvu1.setBounds(129, 98, 191, 25);
		panel.add(cbchucvu1);
		
		btn_them = new JButton("Thêm");
		btn_them.addActionListener(this);
		btn_them.setForeground(Color.WHITE);
		btn_them.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_them.setBackground(new Color(204, 0, 255));
		btn_them.setBounds(1039, 25, 154, 35);
		panel.add(btn_them);
		
		btn_matkhau = new JButton("Đổi mật khẩu");
		btn_matkhau.addActionListener(this);
		btn_matkhau.setForeground(Color.WHITE);
		btn_matkhau.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_matkhau.setBackground(new Color(204, 0, 255));
		btn_matkhau.setBounds(1039, 81, 154, 35);
		panel.add(btn_matkhau);
		
		JLabel lblNgunHng_2 = new JLabel("SĐT:");
		lblNgunHng_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgunHng_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgunHng_2.setBounds(465, 100, 57, 23);
		panel.add(lblNgunHng_2);
		
		tfsdt1 = new JTextField();
		tfsdt1.setColumns(10);
		tfsdt1.setBounds(528, 98, 283, 29);
		panel.add(tfsdt1);
		
		tfns1 = new JTextField();
		tfns1.setColumns(10);
		tfns1.setBounds(528, 20, 283, 29);
		panel.add(tfns1);
		
		JLabel lblNgunHng_3 = new JLabel("Ngày sinh:");
		lblNgunHng_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgunHng_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgunHng_3.setBounds(440, 26, 85, 23);
		panel.add(lblNgunHng_3);
		
		checkRole();
		
		refresh();
	}
	
	public void checkRole() {
		if (Application.account.getChucVu().equals("Nhân viên")) {
			btn_sua.setEnabled(false);
			btn_them.setEnabled(false);
			btn_xoa.setEnabled(false);
			btn_matkhau.setEnabled(false);
		}
	}
	
	public void refresh(){
		String ten = tftkten.getText().trim();
		String role = (String) cbtkchucvu.getSelectedItem();
		String email = tftkemail.getText().trim();
		String sdt = tftksdt.getText().trim();
		
		String sql = "Select * from NhanVien Where chucVu=N'"+role+"' Order By maNV DESC";
		if (role.equals("Tất cả")) sql = "Select * from NhanVien Order By maNV DESC";
		
		ds.getDs().clear();
		for (NhanVien x:  NhanVien_DAO.filter(sql).getDs()) {
			if (x.getTenNV().contains(ten) && x.getEmail().contains(email) && x.getSdt().contains(sdt)) {
				ds.themNV(x);
			}
		}
		System.out.println(sql);
		dtm.setRowCount(0);
		for (NhanVien x: ds.getDs()) {
			dtm.addRow(x.toObj());
		}
	}
	
	public boolean isAddVal() {
		if (tfma2.getText().equals("")
			|| tften2.getText().equals("")
			|| tfns2.getText().equals("")
			|| tfemail2.getText().equals("")
			|| tfsdt2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				return false;
		}
		
		NhanVien nv = NhanVien_DAO.getNV(tfma2.getText());
		if (nv != null) {
			JOptionPane.showMessageDialog(null, "Trùng mã!");
			return false;
		}
		try {
			LocalDate ns = LocalDate.parse(tfns2.getText());
        } catch (DateTimeParseException e) {
        	JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ");
            return false; // Ngày không hợp lệ
        }
		String ck = "^NV[0-9]{5}$";
		Pattern p = Pattern.compile(ck);
		if (!p.matcher(tfma2.getText()).matches()){
			JOptionPane.showMessageDialog(null, "Mã phải có cú pháp NVXXXXX");
			return false;
		}
		ck = "^^(?:[A-ZÀ-Ỹ][a-zà-ỹ']+)(?: [A-ZÀ-Ỹ][a-zà-ỹ']+)*$";
		p = Pattern.compile(ck);
		if (!p.matcher(tften2.getText()).matches()){
			JOptionPane.showMessageDialog(null, "Tên phải bắt đầu bằng chữ hoa theo sau chữ thường");
			return false;
		}
		ck = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		p = Pattern.compile(ck);
		if (!p.matcher(tfns2.getText()).matches()){
			JOptionPane.showMessageDialog(null, "Ngày sinh phải theo cú pháp YYYY-MM-DD");
			return false;
		}
		ck = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
		p = Pattern.compile(ck);
		if (!p.matcher(tfemail2.getText()).matches()){
			JOptionPane.showMessageDialog(null, "Phải đúng cú pháp email");
			return false;
		}
		ck = "[0-9]{10}";
		p = Pattern.compile(ck);
		if (!p.matcher(tfsdt2.getText()).matches()){
			JOptionPane.showMessageDialog(null, "Số điện thoại phải là 10 chữ số");
			return false;
		}
		String mk1 = String.valueOf(tfmk1.getPassword());
		String mk2 = String.valueOf(tfmk2.getPassword());
		
		if (mk1.length()<8) {
			JOptionPane.showMessageDialog(null, "Mật khẩu phải có độ dài >=8");
			return false;
		}
		
		if (!mk1.equals(mk2) || mk1.equals("")) {
			JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu không khớp!");
			return false;
		}
		
		
		return true;
	}
	public boolean isEditVal() {
		if (table.getSelectedRow()!=-1) {
			if (tfma1.getText().equals("")
					|| tften1.getText().equals("")
					|| tfns1.getText().equals("")
					|| tfemail1.getText().equals("")
					|| tfsdt1.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
						return false;
				}
			
			String id = (String) table.getValueAt(table.getSelectedRow(), 0);
			if (!id.equals(tfma1.getText())) {
				JOptionPane.showMessageDialog(null, "Không được sửa mã!");
				return false;
			}
			String ck = "^NV[0-9]{5}$";
			Pattern p = Pattern.compile(ck);
			if (!p.matcher(tfma1.getText()).matches()){
				JOptionPane.showMessageDialog(null, "Mã phải có cú pháp NVXXXXX");
				return false;
			}
			ck = "^^(?:[A-ZÀ-Ỹ][a-zà-ỹ']+)(?: [A-ZÀ-Ỹ][a-zà-ỹ']+)*$";
			p = Pattern.compile(ck);
			if (!p.matcher(tften1.getText()).matches()){
				JOptionPane.showMessageDialog(null, "Tên phải bắt đầu bằng chữ hoa theo sau chữ thường");
				return false;
			}
			ck = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
			p = Pattern.compile(ck);
			if (!p.matcher(tfns1.getText()).matches()){
				JOptionPane.showMessageDialog(null, "Ngày sinh phải theo cú pháp YYYY-MM-DD");
				return false;
			}
			ck = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
			p = Pattern.compile(ck);
			if (!p.matcher(tfemail1.getText()).matches()){
				JOptionPane.showMessageDialog(null, "Phải đúng cú pháp email");
				return false;
			}
			ck = "[0-9]{10}";
			p = Pattern.compile(ck);
			if (!p.matcher(tfsdt1.getText()).matches()){
				JOptionPane.showMessageDialog(null, "Số điện thoại phải là 10 chữ số");
				return false;
			}

			return true;
		}
		return false;
	}
	
	public boolean isPassVal(){
		String mk0 = String.valueOf(tfmk3.getPassword());
		String mk1 = String.valueOf(tfmk4.getPassword());
		String mk2 = String.valueOf(tfmk5.getPassword());
		String ma = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
		if (!NhanVien_DAO.ckmk(ma, mk0)) {
			JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng");
			return false;
		}
		if (mk1.equals("")) {
			JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống!");
			return false;
		}
		if (!mk1.equals(mk2)) {
			JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu không khớp!");
			return false;
		}
		if (mk1.length()<8) {
			JOptionPane.showMessageDialog(null, "Mật khẩu phải có độ dài >=8");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		if (cmd.equals("Thêm")) {
			createThemDialog();
		}
		if (cmd.equals("Thêm nhân sự") && isAddVal()) {
			String ma = tfma2.getText();
			String ten = tften2.getText();
			String ns = tfns2.getText();
			String chucvu = (String) tfchucvu2.getSelectedItem();
			String email = tfemail2.getText();
			String sdt = tfsdt2.getText();
			String mk1 = String.valueOf(tfmk1.getPassword());
	
			NhanVien x = new NhanVien(ma, ten, chucvu, LocalDate.parse(ns), email, sdt);
			if (!Application.account.getTenNV().equals("Admin") && chucvu.equals("Quản lý")) {
				JOptionPane.showMessageDialog(null, "Chỉ Admin mới có thể cấp quyền quản lý");
			}
			else {
				NhanVien_DAO.addItem(x);		
				NhanVien_DAO.createPassword(ma, mk1);
				dialog1.dispose();
				refresh();
			}
		}
		
		if (cmd.equals("Xóa")) {
			int idx = table.getSelectedRow();
			if (idx!=-1) {
				if (((String) table.getValueAt(idx, 0)).equals("NV00000")) {
					JOptionPane.showMessageDialog(null, "Không thể xóa Admin!");
				}
				else if (Application.account.getMaNV().equals((String) table.getValueAt(idx, 0))) {
					if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa bản thân không?")==JOptionPane.YES_OPTION) {
						String id = (String) table.getValueAt(idx, 0);					
						NhanVien_DAO.removeNV(id);	
						JOptionPane.showMessageDialog(null, "Bye Bye :))");
						Application.account = new NhanVien("NV00000", "Admin", "Quản lý", LocalDate.parse("2004-02-29"), "hihi23@gmail.com", "012345678");
						Application.state.setSelectedItem("Logout");
					}
				}	
				else {
					String mess = "Bạn có muốn xóa "+((String) table.getValueAt(idx, 0))+" không?";
					if (JOptionPane.showConfirmDialog(null, mess)==JOptionPane.YES_OPTION) {
						String id = (String) table.getValueAt(idx, 0);					
						NhanVien_DAO.removeNV(id);	
						refresh();
					}
				}
			}
		}
		if (cmd.equals("Chỉnh Sửa") && isEditVal()) {
			String ma = tfma1.getText();
			String ten = tften1.getText();
			String cv = (String) cbchucvu1.getSelectedItem();
			String email = tfemail1.getText();
			String ns = tfns1.getText();
			String sdt = tfsdt1.getText();
			int idx = table.getSelectedRow();
			String chucvu = (String) table.getValueAt(idx, 0);
			if (!Application.account.getTenNV().equals("Admin") && !cv.equals(chucvu)) {
				JOptionPane.showMessageDialog(null, "Chỉ Admin mới có thể đổi chức vụ!");
			}
			else {
				NhanVien x = new NhanVien(ma, ten, cv, LocalDate.parse(ns), email, sdt);
				NhanVien_DAO.update(x);;			
				refresh();
			}
		}
		if (cmd.equals("Đổi mật khẩu") && table.getSelectedRow()!=-1) {
			createChangePassDialog();
		}
		if (cmd.equals("Đổi")) {
			if(isPassVal()) {
				NhanVien_DAO.changePassword(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)) , String.valueOf(tfmk5.getPassword()));			
				JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
				dialog2.dispose();
			}
		}
		if (cmd.equals("Tìm kiếm")) {
			refresh();
		
		}
		if (cmd.equals("Khôi phục")) {
			tftkten.setText("");
			tftkemail.setText("");
			tftksdt.setText("");
			cbtkchucvu.setSelectedIndex(0);
			
			refresh();
			
		}
		if (cmd.equals("Kiểm tra")) {
			String ma = tfma1.getText().trim();
			int idx = ds.timNV(ma);
			if (idx==-1) JOptionPane.showMessageDialog(null, "Mã không tồn tại!");
			else {
				tften1.setText(String.valueOf(table.getValueAt(idx, 1)));
				tfns1.setText(String.valueOf(table.getValueAt(idx, 3)));
				tfemail1.setText(String.valueOf(table.getValueAt(idx, 4)));
				cbchucvu1.setSelectedItem(String.valueOf(table.getValueAt(idx, 2)));
				tfsdt1.setText(String.valueOf(table.getValueAt(idx, 5)));
				table.setRowSelectionInterval(idx, idx);
				table.scrollRectToVisible(table.getCellRect(idx, 0, true));
			}
		}
	}
	
	public JPanel getPannel() {
		return p_body_tt;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int idx = table.getSelectedRow();
		if (idx!=-1) {
			tfma1.setText(String.valueOf(table.getValueAt(idx, 0)));
			tften1.setText(String.valueOf(table.getValueAt(idx, 1)));
			cbchucvu1.setSelectedItem(String.valueOf(table.getValueAt(idx, 2)));
			tfns1.setText(String.valueOf(table.getValueAt(idx, 3)));
			tfemail1.setText(String.valueOf(table.getValueAt(idx, 4)));
			tfsdt1.setText(String.valueOf(table.getValueAt(idx, 5)));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
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
}
