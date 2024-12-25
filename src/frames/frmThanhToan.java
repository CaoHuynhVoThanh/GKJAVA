package frames;
// có chỉnh sửa
import java.awt.EventQueue;
import javax.swing.table.TableColumn;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.SanPham_DAO;
import entities.ChiTietHoaDon;
import entities.HoaDon;
import entities.NhanVien;
import entities.SanPham;
import fileReader.XuatHoaDon;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;

public class frmThanhToan extends JFrame implements ActionListener, TableModelListener, FocusListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtThemTT;
	private JTable tableTT;
	private JButton btnTienMat;
	private JButton btnViDienTu;
	private JButton btnTheNganHang;
	private JButton btnThemTT;
	private DefaultTableModel tableModelTT;
	private JButton btnXoa;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btn0;
	private JButton btn00;
	private JButton btn000;
	private JButton btnCham;
	private JButton btnCE;
	private JButton btnC;
	private JLabel lblKqTongSo;
	private JLabel lblKqTongCong;
	private JTextField currentTextField;
	private JTextField textSoLuong;
	private JRadioButton rdInHD;
	private JPanel p_body_tt;
	NhanVien nv = new NhanVien("NV00000", "Admin", "Quản lý", LocalDate.parse("2024-01-01"), "hihi@gmail.com", "012345678a");
	ConnectDB con;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmThanhToan frame = new frmThanhToan();
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
	public frmThanhToan() {
		con = new ConnectDB();
		con.getInstance().connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(1536, 864);
		this.setLocationRelativeTo(this);
		JPanel contentPane = new JPanel();
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
		
		p_body_tt = new JPanel();
		p_body_tt.setBackground(new Color(255, 255, 255));
		p_body_tt.setBorder(new EmptyBorder(0, 0, 0, 0));
		p_body_tt.setBounds(250, 99, 1262, 728);
		contentPane.add(p_body_tt);
		p_body_tt.setLayout(null);
		
		JPanel pHeadTT = new JPanel();
		pHeadTT.setBounds(40, 25, 1187, 74);
		p_body_tt.add(pHeadTT);
		pHeadTT.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel pDiaChi = new JPanel();
		pHeadTT.add(pDiaChi);
		pDiaChi.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Địa chỉ: Số 12 Nguyễn Văn Bảo, Phường 4,<br>Gò Vấp, Hồ Chí Minh</html>");
		lblNewLabel.setBounds(20, 10, 435, 54);
		pDiaChi.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel pThoiGian = new JPanel();
		pHeadTT.add(pThoiGian);
		pThoiGian.setLayout(null);
		
		JLabel lblNgay = new JLabel();
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNgay.setBounds(21, 10, 182, 25);
		pThoiGian.add(lblNgay);
		
		JLabel lblGio = new JLabel();
		lblGio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGio.setBounds(21, 39, 229, 25);
		pThoiGian.add(lblGio);
		
		// Định dạng ngày và giờ
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Cập nhật ngày và giờ mỗi giây
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Lấy ngày và giờ hiện tại
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();

                // Cập nhật JLabel
                lblNgay.setText("Ngày: " + currentDate.format(dateFormatter));
                lblGio.setText("Thời gian: " + currentTime.format(timeFormatter));
            }
        }, 0, 1000); // Cập nhật mỗi giây (1000ms)
		
		JPanel pHoaDon = new JPanel();
		pHoaDon.setBounds(40, 120, 804, 598);
		p_body_tt.add(pHoaDon);
		pHoaDon.setLayout(null);
		
		JLabel lblMaTT = new JLabel("Mã sản phẩm");
		lblMaTT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaTT.setBounds(51, 19, 119, 31);
		pHoaDon.add(lblMaTT);
		
		txtThemTT = new JTextField();
		txtThemTT.setBounds(167, 17, 387, 42);
		pHoaDon.add(txtThemTT);
		txtThemTT.setColumns(10);
		
		btnThemTT = new JButton("Thêm");
		btnThemTT.setForeground(new Color(255, 255, 255));
		btnThemTT.setBackground(new Color(0, 204, 51));
		btnThemTT.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThemTT.setBounds(575, 19, 78, 40);
		pHoaDon.add(btnThemTT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 79, 695, 498);
		pHoaDon.add(scrollPane);
		
		String[] colnames = {
				"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
		};
		tableModelTT = new DefaultTableModel(colnames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột "Số lượng" (cột thứ 2 - index là 2)
                return column == 2;
            }
        };
		tableTT = new JTable(tableModelTT);
		tableTT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(tableTT);
        
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBackground(new Color(255, 51, 0));
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setBounds(670, 19, 78, 40);
		pHoaDon.add(btnXoa);
		JTableHeader tableHeader = tableTT.getTableHeader();
		tableHeader.setFont(new Font("Arial", Font.BOLD, 18));
		tableHeader.setBackground(new Color(204, 11, 255));
		tableHeader.setForeground(Color.WHITE);
		tableTT.getTableHeader().setReorderingAllowed(false);
		for (int i = 0; i < tableTT.getColumnModel().getColumnCount(); i++) {
            TableColumn column = tableTT.getColumnModel().getColumn(i);
            column.setResizable(false); // Tắt tính năng điều chỉnh độ rộng của cột
		}
		
		JPanel pTongTien = new JPanel();
		pTongTien.setBounds(868, 472, 359, 247);
		p_body_tt.add(pTongTien);
		pTongTien.setLayout(null);
		
		JLabel lblThanhToan = new JLabel("THANH TOÁN");
		lblThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhToan.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblThanhToan.setBounds(89, 10, 188, 33);
		pTongTien.add(lblThanhToan);
		
		JLabel lblTongSo = new JLabel("Tổng số:");
		lblTongSo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongSo.setBounds(32, 46, 79, 20);
		pTongTien.add(lblTongSo);
		
		JLabel lblTongCong = new JLabel("TỔNG CỘNG:");
		lblTongCong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongCong.setBounds(32, 76, 131, 26);
		pTongTien.add(lblTongCong);
		
		JLabel lblPhuongThucTT = new JLabel("Chọn phương thức thanh toán");
		lblPhuongThucTT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhuongThucTT.setBounds(68, 135, 246, 26);
		pTongTien.add(lblPhuongThucTT);
		
		JPanel pPhuongThucTT = new JPanel();
		pPhuongThucTT.setBounds(20, 171, 318, 53);
		pTongTien.add(pPhuongThucTT);
		pPhuongThucTT.setLayout(null);
		
		btnTienMat = new JButton("Tiền");
		btnTienMat.setForeground(new Color(255, 255, 255));
		btnTienMat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTienMat.setBounds(0, 0, 96, 53);
		btnTienMat.setBackground(new Color(0, 255, 0));
		pPhuongThucTT.add(btnTienMat);
		
		btnViDienTu = new JButton("Ví");
		btnViDienTu.setForeground(new Color(255, 255, 255));
		btnViDienTu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnViDienTu.setBounds(112, 0, 96, 53);
		btnViDienTu.setBackground(new Color(255, 0, 255));
		pPhuongThucTT.add(btnViDienTu);
		
		btnTheNganHang = new JButton("Thẻ");
		btnTheNganHang.setForeground(new Color(255, 255, 255));
		btnTheNganHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTheNganHang.setBounds(222, 0, 96, 53);
		btnTheNganHang.setBackground(new Color(128, 0, 255));
		pPhuongThucTT.add(btnTheNganHang);
		
		rdInHD = new JRadioButton("In hóa đơn khi thanh toán");
		rdInHD.setHorizontalAlignment(SwingConstants.CENTER);
		rdInHD.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		rdInHD.setBounds(52, 108, 254, 21);
		pTongTien.add(rdInHD);
		
		lblKqTongSo = new JLabel("0");
		lblKqTongSo.setBounds(121, 46, 159, 20);
		lblKqTongSo.setFont(new Font("Tahoma", Font.BOLD, 16));
		pTongTien.add(lblKqTongSo);
		
		lblKqTongCong = new JLabel("0.0");
		lblKqTongCong.setBounds(176, 76, 130, 26);
		lblKqTongCong.setFont(new Font("Tahoma", Font.BOLD, 18));
		pTongTien.add(lblKqTongCong);
		
		JPanel panel = new JPanel();
		panel.setBounds(869, 120, 356, 316);
		p_body_tt.add(panel);
		panel.setLayout(null);
		
		JPanel pBanPhim = new JPanel();
		pBanPhim.setBounds(37, 27, 282, 256);
		panel.add(pBanPhim);
		pBanPhim.setLayout(new GridLayout(5, 3, 10, 10));
		JButton[] buttonNumbers = {
                new JButton("1"),
                new JButton("2"),
                new JButton("3"),
                new JButton("4"),
                new JButton("5"),
                new JButton("6"),
                new JButton("7"),
                new JButton("8"),
                new JButton("9"),
                new JButton("0"),
                new JButton("00"),
                new JButton("000"),
                new JButton("CE"),
                new JButton("C"),
                new JButton("OK")
        };
		TableColumn soLuongColumn = tableTT.getColumnModel().getColumn(2); // "Số lượng" is the 3rd column (index 2)
        textSoLuong = new JTextField();
        soLuongColumn.setCellEditor(new DefaultCellEditor(textSoLuong));
        
        for (JButton button : buttonNumbers) {
            button.addActionListener(new ButtonClickListener());
            button.setFont(new Font("Tahoma", Font.PLAIN, 22));
    		button.setBackground(new Color(255, 255, 255));
    		pBanPhim.add(button);
        }
        
		btnThemTT.addActionListener(this);
		btnTienMat.addActionListener(this);
		btnXoa.addActionListener(this);
		tableModelTT.addTableModelListener(this);
        txtThemTT.addFocusListener(this);
        textSoLuong.addFocusListener(this);
        btnTheNganHang.addActionListener(this);
        btnViDienTu.addActionListener(this);
	}
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            // Lấy nhãn của nút vừa được nhấn
            String command = e.getActionCommand();
            
            // Kiểm tra xem có JTextField nào đang focus không
            if (currentTextField != null) {
            	
                if (command.equals("C")) {
                    // Xóa 1 ký tự cuối cùng trong text field
                    String currentText = currentTextField.getText();
                    if (!currentText.isEmpty()) {
                        currentTextField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                } else if (command.equals("CE")) {
                    // Xóa toàn bộ nội dung trong text field
                    currentTextField.setText("");
                } else if (command.equals("OK")) {
                	if (tableTT.isEditing()) {
                        tableTT.getCellEditor().stopCellEditing();  // Dừng chỉnh sửa ô hiện tại
                    }
                } else {
                    // Thêm ký tự vào text field đang focus
                    currentTextField.setText(currentTextField.getText() + command);
                }
         
            }
            
        }
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemTT)) {
			themSanPham();
		} else if (o.equals(btnTienMat)) {
			thanhToanBangTien();
		} else if (o.equals(btnXoa)) {
			xoa();
		} else if (o.equals(btnTheNganHang)) {
			thanhToanBangThe();
		} else if (o.equals(btnViDienTu)) {
			thanhToanBangVi();
		}
	}
	public double layTongTien(JTable table) {
        double total = 0.0;
        for (int row = 0; row < table.getRowCount(); row++) {
            // Lấy giá trị của cột "Thành Tiền" tại hàng hiện tại
            String value = table.getValueAt(row, 4).toString();
            total += Double.parseDouble(value);
        }
        
        return total;
    }
	public int layTongSoLuong(JTable table) {
        int total = 0;
        for (int row = 0; row < table.getRowCount(); row++) {
            // Lấy giá trị của cột "Đơn Giá" tại hàng hiện tại
            String value = table.getValueAt(row, 2).toString();
            total += Double.parseDouble(value);
        }
        
        return total;
    }
	public void themSanPham() {
		String maSP = txtThemTT.getText();
		SanPham sp = SanPham_DAO.timSanPham(maSP);
		if (sp != null && sp.getSoLuongTon() > 0) {
	        // Duyệt qua kết quả trả về
            int rowIndex = kiemTraMaSPTrongBang(sp.getMaSP());
            if (rowIndex >= 0) {
                // Nếu mã sản phẩm đã tồn tại, tăng số lượng và cập nhật thành tiền
                int soLuongHienTai = Integer.parseInt((String) tableModelTT.getValueAt(rowIndex, 2));
                soLuongHienTai++;
                if (soLuongHienTai > sp.getSoLuongTon()) {
                	soLuongHienTai = 1;
                	JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng tồn");
                }
                tableModelTT.setValueAt(soLuongHienTai + "", rowIndex, 2);
                double thanhTienMoi = sp.getGiaBan() * soLuongHienTai;
                tableModelTT.setValueAt(thanhTienMoi + "", rowIndex, 4);
                
            } else {
                // Nếu mã sản phẩm chưa tồn tại, thêm mới sản phẩm vào bảng
                Object[] dataRow = { sp.getMaSP(), sp.getTenSP(), "1", sp.getGiaBan() + "",  + sp.getGiaBan() + "" };
                tableModelTT.addRow(dataRow);
            }
        } else {
        	if (sp==null) JOptionPane.showMessageDialog(this, "không tồn tại mã sản phẩm"); 
        	else JOptionPane.showMessageDialog(this, "sản phẩm đã bán hết");
		}    
		
	}
	private int kiemTraMaSPTrongBang(String maSP) {
	    for (int i = 0; i < tableModelTT.getRowCount(); i++) {
	        String maTrongBang = (String) tableModelTT.getValueAt(i, 0);
	        if (maTrongBang.equals(maSP)) {
	            return i; // Trả về chỉ số dòng nếu mã sản phẩm tồn tại
	        }
	    }
	    return -1; // Trả về -1 nếu không tìm thấy mã sản phẩm
	}
	public void layChiTietHoaDonTable(String maHD) {
		for (int row=0; row<tableTT.getRowCount(); row++) {
			String maSP = tableTT.getValueAt(row, 0).toString();
			int soLuong = Integer.parseInt(tableTT.getValueAt(row, 2).toString());
			double donGia = Double.parseDouble(tableTT.getValueAt(row, 3).toString());
			ChiTietHoaDon chiTietHD = new ChiTietHoaDon(maHD, maSP, soLuong, donGia);
			ChiTietHoaDon_DAO.themChiTietHoaDon(chiTietHD);
			
			SanPham sp = SanPham_DAO.timSanPham(maSP);
			SanPham_DAO.capNhatSoLuongTon(sp.getSoLuongTon() - soLuong, maSP);
		}
		 
	}
	public HoaDon layHoaDonTuTable(String pTTT) {
		int soLuongHD = HoaDon_DAO.layTatCaHoaDon().size();
        int maHD = soLuongHD + 1;
        String maNV = Application.account.getMaNV();
        LocalDateTime thoiGian = LocalDateTime.now();
        int tongSoLuong = layTongSoLuong(tableTT);
        double thanhTien = layTongTien(tableTT);
        String phuongThucTT = pTTT;
        HoaDon hd = new HoaDon(maHD,  maNV, thoiGian, tongSoLuong, thanhTien, phuongThucTT);
        return hd;
	}
	public void thanhToanBangTien() {
		String input = JOptionPane.showInputDialog(this, "Nhập số tiền nhận:", "Số tiền nhận", JOptionPane.PLAIN_MESSAGE);
        // Kiểm tra nếu người dùng không hủy
        if (input != null && !input.isEmpty()) {
            try {
                double soTienNhan = Double.parseDouble(input);
                double tongTien = layTongTien(tableTT);
                if (soTienNhan >= tongTien) {
                    double tienThua = soTienNhan - tongTien;
                    JOptionPane.showMessageDialog(this, "Thanh toán thành công!\nTiền thừa: " + tienThua);
                    HoaDon hd = layHoaDonTuTable("Tiền mặt");
                    HoaDon_DAO.themHoaDon(hd);
                    layChiTietHoaDonTable(hd.getMaHD());
                    if (rdInHD.isSelected()) {
	                    try {
	                    	XuatHoaDon.writeInvoiceToFile(ChiTietHoaDon_DAO.timChiTietHoaDon(hd.getMaHD()), hd);
	                    } catch (IOException e) {
	                    	System.err.println("Lỗi khi ghi hóa đơn vào file: " + e.getMessage());
	                    } catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                    xoaTatCaRowTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Số tiền nhận không đủ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập một số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
	}
	public void thanhToanBangThe() {
		int hoiNhac = JOptionPane.showConfirmDialog(this, "Hãy đưa thẻ vào máy quét", "Lưu ý", JOptionPane.YES_NO_OPTION);
		if (hoiNhac == JOptionPane.YES_NO_OPTION) {
			HoaDon hd = layHoaDonTuTable("Thẻ ngân hàng");
            HoaDon_DAO.themHoaDon(hd);
            layChiTietHoaDonTable(hd.getMaHD());
            if (rdInHD.isSelected()) {
                try {
                	XuatHoaDon.writeInvoiceToFile(ChiTietHoaDon_DAO.timChiTietHoaDon(hd.getMaHD()), hd);
                } catch (IOException e) {
                	System.err.println("Lỗi khi ghi hóa đơn vào file: " + e.getMessage());
                } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            xoaTatCaRowTable();
		}
	}
	public void xoa() {
		int row = tableTT.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "bạn phải chọn dòng cần xóa");
		} else {
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Lưu ý", JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_NO_OPTION) {
				tableModelTT.removeTableModelListener(this);
				tableModelTT.removeRow(row);
				tableModelTT.addTableModelListener(this);
			}
		}
	}
	public void thanhToanBangVi() {
        // Tạo JDialog mới
        JDialog dialog = new JDialog(this,"Mã QR", true);
        dialog.setSize(400, 400);
        dialog.setResizable(false); // Không cho phép thay đổi kích thước
        dialog.getContentPane().setLayout(new BorderLayout());

        // Thêm ảnh vào JDialog
        ImageIcon imageIcon = new ImageIcon("./src/img/qr.jpg"); // Đường dẫn tới ảnh
        JLabel imageLabel = new JLabel(imageIcon);
        dialog.getContentPane().add(imageLabel, BorderLayout.CENTER);

        // Thêm nút OK vào JDialog
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	HoaDon hd = layHoaDonTuTable("Ví điện tử");
                HoaDon_DAO.themHoaDon(hd);
            	layChiTietHoaDonTable(hd.getMaHD());
            	if (rdInHD.isSelected()) {
                    try {
                    	XuatHoaDon.writeInvoiceToFile(ChiTietHoaDon_DAO.timChiTietHoaDon(hd.getMaHD()), hd);
                    } catch (IOException ex) {
                    	System.err.println("Lỗi khi ghi hóa đơn vào file: " + ex.getMessage());
                    } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            	xoaTatCaRowTable();
                dialog.dispose(); // Đóng JDialog khi nhấn OK
            }
        });
        JPanel pQRSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pQRSouth.add(okButton);
        dialog.getContentPane().add(pQRSouth, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        
    }
	public void xoaTatCaRowTable() {
		tableModelTT.removeTableModelListener(this);
        tableModelTT.setRowCount(0);
        tableModelTT.addTableModelListener(this);
	}
	@Override
	public void tableChanged(TableModelEvent e) {
		Object o = e.getSource();
		if (o.equals(tableModelTT) ) {
			if (e.getType() == TableModelEvent.UPDATE) {
				int row = e.getFirstRow();
				System.out.println(row);
				if ((tableTT.getValueAt(row, 2).toString().trim().equals(""))) {
					tableTT.setValueAt("1", row, 2);
				}
				double giaBan = Double.parseDouble(tableTT.getValueAt(row, 3).toString().trim());
				int soLuongSP = Integer.parseInt(tableTT.getValueAt(row, 2).toString().trim());
				double thanhTienMoi = giaBan * soLuongSP;
				System.out.println(thanhTienMoi);
				tableModelTT.removeTableModelListener(this);
				tableTT.setValueAt((giaBan * soLuongSP)+"", row, 4);
				tableModelTT.addTableModelListener(this);
				String maSP = tableTT.getValueAt(row, 0).toString();
				SanPham sp = SanPham_DAO.timSanPham(maSP);
				System.out.println(textSoLuong.getText().trim().isEmpty());
				if (soLuongSP > sp.getSoLuongTon()) {
					tableTT.setValueAt("1", row, 2);
					tableTT.setValueAt(sp.getGiaBan()+"", row, 4);
					JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng tồn");
				}
			}
			int tongSo = layTongSoLuong(tableTT);
        	double tongCong = layTongTien(tableTT);
            lblKqTongSo.setText(tongSo+"");
        	lblKqTongCong.setText(tongCong+"");
		}
		
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		 if (e.getSource() instanceof JTextField) {
	            currentTextField = (JTextField) e.getSource();
	        }
	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}

	public JPanel getPanel() {
		return p_body_tt;
	}
}