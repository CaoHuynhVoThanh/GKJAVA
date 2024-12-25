package frames;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import dao.NhanVien_DAO;
import entities.NhanVien;
import fileReader.BackgroundReader;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class frmLogin extends JFrame implements ActionListener{
	private JPanel contentPane;
	private BackgroundReader br;
	private static JTextField tfdnten;
	private static JPasswordField tfdnmk;
	public static JButton dn_btndn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
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
	public frmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(1536, 864);
		this.setLocationRelativeTo(this);
		contentPane = new JPanel();
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		br = new BackgroundReader();
		br.setLocation(0, 0);
		br.setSize(1541, 827);
		contentPane.add(br);
		br.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 102, 204));
		panel.setBounds(538, 214, 536, 364);
		br.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(259, -1, 277, 367);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("ĐĂNG NHẬP");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(60, 42, 174, 48);
		panel_1.add(lblNewLabel_1);
		
		tfdnten = new JTextField();
		tfdnten.setColumns(10);
		tfdnten.setBounds(34, 141, 215, 35);
		panel_1.add(tfdnten);
		
		JLabel lblNewLabel_2 = new JLabel("Mã nhân sự:");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(34, 106, 161, 29);
		panel_1.add(lblNewLabel_2);
		
		tfdnmk = new JPasswordField();
		tfdnmk.setColumns(10);
		tfdnmk.setBounds(34, 221, 215, 35);
		panel_1.add(tfdnmk);
		
		JLabel lblNewLabel_2_1 = new JLabel("Mật khẩu:");
		lblNewLabel_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(34, 186, 161, 29);
		panel_1.add(lblNewLabel_2_1);
		
		dn_btndn = new JButton("ĐĂNG NHẬP");
		dn_btndn.addActionListener(this);
		dn_btndn.setForeground(Color.WHITE);
		dn_btndn.setFont(new Font("Tahoma", Font.BOLD, 14));
		dn_btndn.setBackground(new Color(0, 204, 102));
		dn_btndn.setBounds(60, 282, 161, 35);
		panel_1.add(dn_btndn);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmLogin.class.getResource("/img/logo.png")));
		lblNewLabel.setBounds(70, 110, 135, 135);
		panel.add(lblNewLabel);
	}
	public static boolean checkDN() {
		String ten = tfdnten.getText();
		String mk =String.valueOf(tfdnmk.getPassword());
		NhanVien x=null;
	
		x = NhanVien_DAO.checkLog(ten, mk);
		if (x==null) {
			JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không đúng!");
			return false;
		}
		else Application.account=x;
		
		return true;
	}
	
	public JPanel getPanel() {
		return this.br;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		
	}
}
