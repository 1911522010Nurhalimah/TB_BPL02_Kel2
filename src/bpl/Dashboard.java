package bpl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	
	public static String url = "jdbc:mysql://localhost/tb_bpl?serverTimezone=Asia/Jakarta";
	public static String user = "root";
	public static String password = "";
	public static ResultSet rs;
	public static Connection conn;
	public static Statement stmt;

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
	public Dashboard() {
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
		
		JPanel Panel1 = new JPanel();
		Panel1.setBackground(Color.WHITE);
		Panel1.setBounds(160, 100, 150, 150);
		contentPane.add(Panel1);
		
		JLabel Label1 = new JLabel("Pengelolaan User");
		Label1.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		Panel1.add(Label1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Panel1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ASUS\\Downloads\\user.png"));
		
		JButton Button1 = new JButton("Enter");
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Puser puser = new Puser();
				puser.setVisible(true);
				puser.showDataUser();
				dispose();
			}
		});
		Button1.setFont(new Font("Sitka Text", Font.BOLD, 16));
		Panel1.add(Button1);
		
		JPanel Panel2 = new JPanel();
		Panel2.setBackground(Color.WHITE);
		Panel2.setBounds(320, 100, 150, 150);
		contentPane.add(Panel2);
		
		JLabel Label2 = new JLabel("Pengelolaan Barang");
		Label2.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		Panel2.add(Label2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\ASUS\\Downloads\\barang.png"));
		Panel2.add(lblNewLabel_2);
		
		JButton Button2 = new JButton("Barang");
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pbarang pbarang = new Pbarang();
				pbarang.setVisible(true);
				pbarang.showDataBarang();
				dispose();
			}
		});
		Button2.setFont(new Font("Sitka Text", Font.BOLD, 16));
		Panel2.add(Button2);
		
		JPanel Panel3 = new JPanel();
		Panel3.setBackground(Color.WHITE);
		Panel3.setBounds(160, 261, 150, 150);
		contentPane.add(Panel3);
		
		JLabel Label3 = new JLabel("Transaksi Penjualan");
		Label3.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		Panel3.add(Label3);
		
		JLabel lblNewLabel_3 = new JLabel("");
		Panel3.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\ASUS\\Downloads\\transaksi.png"));
		
		JButton Button3 = new JButton("Transaksi");
		Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Tpenjualan().setVisible(true);
				dispose();
			}
		});
		Button3.setFont(new Font("Sitka Text", Font.BOLD, 16));
		Panel3.add(Button3);
		
		JPanel Panel4 = new JPanel();
		Panel4.setBackground(Color.WHITE);
		Panel4.setBounds(320, 261, 150, 150);
		contentPane.add(Panel4);
		
		JLabel Label4 = new JLabel("Laporan Jual/Untung");
		Label4.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		Panel4.add(Label4);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\ASUS\\Downloads\\laporan.png"));
		Panel4.add(lblNewLabel_4);
		
		JButton Button4 = new JButton("Laporan");
		Button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Lju().setVisible(true);
				dispose();
			}
		});
		Button4.setFont(new Font("Sitka Text", Font.BOLD, 16));
		Panel4.add(Button4);
		
		JPanel Panel5 = new JPanel();
		Panel5.setBackground(Color.WHITE);
		Panel5.setBounds(480, 261, 150, 150);
		contentPane.add(Panel5);
		
		JLabel Label5 = new JLabel("Contact US");
		Label5.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		Panel5.add(Label5);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\ASUS\\Downloads\\cp.png"));
		Panel5.add(lblNewLabel_5);
		
		JButton Button5 = new JButton("Contact");
		Button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Contact().setVisible(true);
				dispose();
			}
		});
		Button5.setFont(new Font("Sitka Text", Font.BOLD, 16));
		Panel5.add(Button5);
		
		JPanel Panel6 = new JPanel();
		Panel6.setBackground(Color.WHITE);
		Panel6.setBounds(480, 100, 150, 150);
		contentPane.add(Panel6);
		
		JLabel Label6 = new JLabel("Restock Barang");
		Label6.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		Panel6.add(Label6);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\ASUS\\Downloads\\box.png"));
		Panel6.add(lblNewLabel_6);
		
		JButton Button6 = new JButton("Restock");
		Button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rbarang pbarang = new Rbarang();
				pbarang.setVisible(true);
				pbarang.showDataBarang();
				dispose();
			}
		});
		Button6.setFont(new Font("Sitka Text", Font.BOLD, 16));
		Panel6.add(Button6);
	}
}
