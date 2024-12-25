package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import java.awt.Color;

public class test2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test2 frame = new test2();
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
	public test2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(1536, 864);
		this.setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(319, 78, 873, 573);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 181, 851, 290);
		internalFrame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("THÊM VÀO DATA");
		btnNewButton.setBackground(new Color(0, 204, 102));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(445, 493, 160, 41);
		internalFrame.getContentPane().add(btnNewButton);
		
		JButton btnHy = new JButton("HỦY");
		btnHy.setForeground(new Color(255, 255, 255));
		btnHy.setBackground(new Color(255, 0, 0));
		btnHy.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHy.setBounds(269, 493, 148, 41);
		internalFrame.getContentPane().add(btnHy);
		
		textField = new JTextField();
		textField.setBounds(33, 48, 148, 33);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Mã SP:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(37, 21, 91, 24);
		internalFrame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(210, 48, 148, 33);
		internalFrame.getContentPane().add(textField_1);
		
		JLabel lblTnSp = new JLabel("Tên SP:");
		lblTnSp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnSp.setBounds(214, 21, 91, 24);
		internalFrame.getContentPane().add(lblTnSp);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(385, 48, 148, 33);
		internalFrame.getContentPane().add(textField_2);
		
		JLabel lblGiNhp = new JLabel("Giá nhập:");
		lblGiNhp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiNhp.setBounds(389, 21, 91, 24);
		internalFrame.getContentPane().add(lblGiNhp);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(681, 48, 148, 33);
		internalFrame.getContentPane().add(textField_3);
		
		JLabel lblNgunHng = new JLabel("Nguồn hàng:");
		lblNgunHng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgunHng.setBounds(681, 21, 118, 24);
		internalFrame.getContentPane().add(lblNgunHng);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(567, 48, 83, 33);
		internalFrame.getContentPane().add(spinner);
		
		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSLng.setBounds(567, 21, 118, 24);
		internalFrame.getContentPane().add(lblSLng);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(33, 125, 447, 33);
		internalFrame.getContentPane().add(textField_4);
		
		JLabel lblHocNhpng = new JLabel("Hoặc nhập đường đãn đến file .xml");
		lblHocNhpng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHocNhpng.setBounds(33, 100, 325, 24);
		internalFrame.getContentPane().add(lblHocNhpng);
		
		JButton btnNewButton_2 = new JButton("Nhập");
		btnNewButton_2.setBackground(new Color(153, 102, 255));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(490, 125, 91, 33);
		internalFrame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("THÊM");
		btnNewButton_2_1.setForeground(Color.WHITE);
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2_1.setBackground(new Color(153, 0, 255));
		btnNewButton_2_1.setBounds(651, 111, 148, 47);
		internalFrame.getContentPane().add(btnNewButton_2_1);
		internalFrame.setVisible(true);
	}
}
