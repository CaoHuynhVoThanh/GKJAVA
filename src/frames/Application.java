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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import entities.NhanVien;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.border.EtchedBorder;
import javax.swing.JSpinner;

public class Application extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel p_body;
	JMenuItem mi_tt, mi_ls, mi_kh, mi_nv, mi_cl, mi_tk;
	private JLabel lb_hoten = new JLabel();
	private JLabel lb_chucvu = new JLabel();
	public static JComboBox<String> state = new JComboBox<>(new String[]{"Logout", "Login"}); 
	public static NhanVien account = new NhanVien("NV00000", "Admin", "Quản lý", LocalDate.parse("2024-01-01"), "hihi@gmail.com", "012345678");
	ConnectDB con;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
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
	public Application() throws SQLException {
		con = new ConnectDB();
		con.getInstance().connect();
		state.addActionListener(this);
		this.setTitle("QUẢN LÝ BÁN HÀNG CỦA HÀNG TIỆN LỢI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(1536, 864);
		this.setLocationRelativeTo(this);
		this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            con.getInstance().disconnect();
	        }
	    });
		login();
			
		//
	}
	
	public JPanel head() {
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
		
		lb_hoten.setText(account.getTenNV());
		lb_hoten.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_hoten.setForeground(new Color(255, 255, 0));
		lb_hoten.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lb_hoten.setBounds(1139, 26, 248, 27);
		p_head.add(lb_hoten);
		
		lb_chucvu.setText(account.getChucVu());
		lb_chucvu.setForeground(new Color(255, 255, 255));
		lb_chucvu.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_chucvu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb_chucvu.setBounds(1243, 52, 142, 27);
		p_head.add(lb_chucvu);
		return p_head;
	}
	
	public JPanel menu() {
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
		mi_tt = new JMenuItem("    THANH TOÁN");
		mi_tt.addActionListener(this);
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
		mi_ls = new JMenuItem("    LỊCH SỬ");
		mi_ls.addActionListener(this);
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
		mi_kh = new JMenuItem("    KHO HÀNG");
		mi_kh.addActionListener(this);
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
		mi_nv = new JMenuItem("    NHÂN SỰ");
		mi_nv.addActionListener(this);
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
		mi_tk = new JMenuItem("    THỐNG KÊ");
		mi_tk.addActionListener(this);
		mi_tk.setIcon(scaledIcon);
		mi_tk.setBackground(new Color(153, 0, 204));
		mi_tk.setHorizontalAlignment(SwingConstants.LEFT);
		mi_tk.setForeground(Color.WHITE);
		mi_tk.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mi_tk.setBounds(25, 395, 231, 90);
		p_menu.add(mi_tk);
		
		mi_tt.setOpaque(true);
		mi_tt.setBackground(p_menu.getBackground());
		mi_tt.setBorderPainted(false);
		mi_ls.setOpaque(true);
		mi_ls.setBackground(p_menu.getBackground());
		mi_ls.setBorderPainted(false);
		mi_kh.setOpaque(true);
		mi_kh.setBackground(p_menu.getBackground());
		mi_kh.setBorderPainted(false);
		mi_nv.setOpaque(true);
		mi_nv.setBackground(p_menu.getBackground());
		mi_nv.setBorderPainted(false);
		mi_tk.setOpaque(true);
		mi_tk.setBackground(p_menu.getBackground());
		mi_tk.setBorderPainted(false);
		
		JButton btnlogout = new JButton("ĐĂNG XUẤT");
		btnlogout.addActionListener(this);
		btnlogout.setForeground(new Color(255, 255, 255));
		btnlogout.setBackground(new Color(255, 0, 51));
		btnlogout.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnlogout.setBounds(29, 656, 198, 46);
		p_menu.add(btnlogout);
		return p_menu;
	}
	
	public JPanel body() {
		p_body = new JPanel(); 
		p_body.setBackground(new Color(255, 255, 255));
		p_body.setBorder(new EmptyBorder(0, 0, 0, 0));
		p_body.setBounds(249, 99, 1302, 771);
		contentPane.add(p_body);
		p_body.setLayout(null);
		return p_body;
	}
	
    public void init(JPanel panel) {
    	contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(head());
		contentPane.add(menu());
		contentPane.add(panel);
		contentPane.revalidate();
		contentPane.repaint();
    }
    
    public void login() {
    	frmLogin lg = new frmLogin();
    	contentPane = new JPanel();
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	frmLogin.dn_btndn.addActionListener(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(lg.getPanel());
		contentPane.revalidate();
		contentPane.repaint();
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand().trim();
		System.out.println(cmd);
		
		if (cmd.equals("ĐĂNG NHẬP")) {
			if (frmLogin.checkDN()) {
				System.out.println(state.getSelectedItem());
				state.setSelectedItem("Login");
				System.out.println(state.getSelectedItem());
			}
			
		}
		if (cmd.equals("ĐĂNG XUẤT")) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?")==JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Bye Bye :))");
				account = new NhanVien("NV00000", "Admin", "Quản lý", LocalDate.parse("2004-02-29"), "hihi23@gmail.com", "012345678");
				lb_hoten.setText(account.getTenNV());
				lb_chucvu.setText(account.getChucVu());
				login();
			}
		}
		if (cmd.equals("THANH TOÁN")) {
			frmThanhToan frame;
			frame = new frmThanhToan();
			init(frame.getPanel());
			mi_tt.setBackground(new Color(204, 51, 255));
		}
		if (cmd.equals("LỊCH SỬ")) {
			frmLichSuHd frame;
			frame = new frmLichSuHd();
			init(frame.getPanel());
			mi_ls.setBackground(new Color(204, 51, 255));
		}
		if (cmd.equals("NHÂN SỰ")) {
			frmNhanVien frame;
			try {
				frame = new frmNhanVien();
				init(frame.getPannel());
				mi_nv.setBackground(new Color(204, 51, 255));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if (cmd.equals("KHO HÀNG")) {
			frmKhoHang frame;
			try {
				frame = new frmKhoHang();
				init(frame.getPannel());
				mi_kh.setBackground(new Color(204, 51, 255));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (cmd.equals("THỐNG KÊ")) {
			frmThongKe frame;
			frame = new frmThongKe();
			init(frame.getPanel());
			mi_tk.setBackground(new Color(204, 51, 255));
		}
		
		if (cmd.equals("comboBoxChanged")) {
			if (state.getSelectedItem().equals("Logout")) {
				login();
			}
			if (state.getSelectedItem().equals("Login")) {
				String mess = "Xin chào "+account.getTenNV()+"!";
				JOptionPane.showMessageDialog(null, mess);
				frmThanhToan frame;	
				lb_hoten.setText(account.getTenNV());
				lb_chucvu.setText(account.getChucVu());
				frame = new frmThanhToan();
				init(frame.getPanel());
				mi_tt.setBackground(new Color(204, 51, 255));
			}
		}
	}

}
