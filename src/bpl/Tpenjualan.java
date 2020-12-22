package bpl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import java.sql.*;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Tpenjualan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	
	public static String url = "jdbc:mysql://localhost/tb_bpl?serverTimezone=Asia/Jakarta";
	public static String user = "root";
	public static String password = "";
	public static ResultSet rs;
	public static Connection conn;
	public static Statement stmt;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Tpenjualan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(75, 0, 130));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 136, 441);
		contentPane.add(panel);
		
		JLabel lblMiniMarket = new JLabel("Mini Market");
		panel.add(lblMiniMarket);
		lblMiniMarket.setForeground(new Color(65, 105, 225));
		lblMiniMarket.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		
		JLabel GLabel = new JLabel("");
		panel.add(GLabel);
		GLabel.setVerticalAlignment(SwingConstants.TOP);
		GLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/gambar/si_19.png")));
		GLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton AButton = new JButton("My Account");
		AButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new My_account().setVisible(true);
				dispose();
			}
		});
		panel.add(AButton);
		AButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton LButton = new JButton("Log Out");
		LButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		LButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(LButton);
		
		JButton KButton = new JButton("<-");
		KButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard().setVisible(true);
				dispose();
			}
		});
		KButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		KButton.setBounds(146, 11, 47, 28);
		contentPane.add(KButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(146, 50, 508, 183);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dateChooser.setBounds(76, 36, 95, 20);
		panel_1.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("No. Resi");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(12, 11, 44, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tanggal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(12, 36, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(76, 8, 95, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(12, 67, 58, 14);
		panel_1.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(76, 64, 95, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(12, 131, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(108, 131, 86, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(204, 131, 86, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(300, 131, 86, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(396, 131, 86, 20);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("No. Sku");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(12, 106, 41, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nama Barang");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(108, 106, 75, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Harga Jual");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(204, 106, 60, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Qty");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(300, 106, 20, 14);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Harga");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(396, 106, 34, 14);
		panel_1.add(lblNewLabel_7);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_7.setBounds(300, 36, 182, 45);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Total Pembelian");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(300, 11, 95, 14);
		panel_1.add(lblNewLabel_8);
		
		JButton TButton = new JButton("Tambah");
		TButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		TButton.setBounds(12, 160, 89, 23);
		panel_1.add(TButton);
		
		JButton HButton = new JButton("Hapus");
		HButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		HButton.setBounds(108, 160, 89, 23);
		panel_1.add(HButton);
		
		textField_8 = new JTextField();
		textField_8.setBounds(204, 64, 86, 20);
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("ID");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(204, 42, 46, 14);
		panel_1.add(lblNewLabel_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(146, 244, 508, 124);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		model =new DefaultTableModel();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Kode Sku", "Nama  Barang", "Harga Jual", "Qty", "Harga"
        	}
        ));
        model.addColumn("Kode Sku");
        model.addColumn("Nama Barang");
        model.addColumn("Harga Jual");
        model.addColumn("Qty");
        model.addColumn("Harga");
		
		JLabel lblTransaksi = new JLabel("Transaksi Penjualan");
		lblTransaksi.setForeground(Color.WHITE);
		lblTransaksi.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTransaksi.setBounds(223, 9, 221, 30);
		contentPane.add(lblTransaksi);
		
		textField_9 = new JTextField();
		textField_9.setBounds(195, 379, 86, 20);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(195, 410, 86, 20);
		contentPane.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Cash");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setBounds(146, 382, 27, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Kembali");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11.setForeground(Color.WHITE);
		lblNewLabel_11.setBounds(147, 413, 46, 14);
		contentPane.add(lblNewLabel_11);
		
		JButton SButton = new JButton("Simpan");
		SButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		SButton.setBounds(291, 409, 89, 23);
		contentPane.add(SButton);
		
		textField_11 = new JTextField();
		textField_11.setBounds(410, 379, 86, 20);
		contentPane.add(textField_11);
		textField_11.setColumns(10);
	}
}
