package bpl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.util.*;
import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Puser extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private static String url = "jdbc:mysql://localhost/tb_bpl?serverTimezone=Asia/Jakarta";
	private static String user = "root";
	private static String password = "";
	private static ResultSet rs;
	private static Connection conn;
	private static Statement stmt;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JPasswordField passwordField;

	public void showDataUser(){
		try{
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT Username, Login_terakhir, Email FROM user";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
	   }catch(Exception a){
		   a.printStackTrace();
	 }
	}
	
	public void clear() {
		textField.setText("");
		textField_1.setText("");
		passwordField.setText("");
	}
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
	public Puser() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 121, 508, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.setEnabled(false);
		table.setEnabled(false);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(248, 318, 240, 30);
		contentPane.add(textField);
		
		JButton TButton = new JButton("Create");
		TButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username = textField.getText();
				String Email = textField_1.getText();
				String Passwd = passwordField.getText();
				
				if(Username.equals("") || Email.equals("") || Passwd.equals("")) {
					JOptionPane.showMessageDialog(null, "Error!");
				}else {
					try {
						conn = DriverManager.getConnection(url, user, password);
						List<String> user = new ArrayList<>();
						
						user.add(Username);
						user.add(Email);
						user.add(Passwd);
						
						stmt = conn.createStatement();
						String sql = "INSERT INTO user VALUES('"+user.get(0)+"',NULL,'"+user.get(1)+"','"+user.get(2)+"')";
						
						JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
						
						int a = stmt.executeUpdate(sql);
						if(a>0) {
							showDataUser();
							clear();
						}
						
					}catch(Exception a) {
						a.printStackTrace();
					}
				}
			}
		});
		TButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		TButton.setBounds(562, 319, 92, 30);
		contentPane.add(TButton);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(248, 359, 240, 30);
		contentPane.add(textField_1);
		
		JButton SButton = new JButton("Show");
		SButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDataUser();
			}
		});
		SButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		SButton.setBounds(146, 80, 92, 30);
		contentPane.add(SButton);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(146, 319, 92, 30);
		contentPane.add(lblUsername);
		
		JButton EButton = new JButton("Edit");
		EButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username = textField.getText();
				String Email = textField_1.getText();
				String Passwd = passwordField.getText();
				
				if(Email.equals("") || Passwd.equals("")) {
					JOptionPane.showMessageDialog(null, "Masukkan data yang ingin diedit!");
				}else {
					try {
						conn = DriverManager.getConnection(url, user, password);
						List<String> user = new ArrayList<>();
						
						user.add(Username);
						user.add(Email);
						user.add(Passwd);

						String sql = "UPDATE user SET Login_terakhir=NOW(), Email='"+user.get(1)+"', Password='"+user.get(2)+"' WHERE Username='"+user.get(0)+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);

						if(stmt.executeUpdate(sql) > 0 ){
		                	JOptionPane.showMessageDialog(null, "Data yang diedit berhasil");
		                	showDataUser();
		                	clear();
						}else{
		                    JOptionPane.showMessageDialog(null, "Data yang diedit tidak ada");
		                    showDataUser();
		                }
						
					}catch(Exception a) {
						a.printStackTrace();
					}
				}
			}
		});
		EButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		EButton.setBounds(562, 360, 92, 30);
		contentPane.add(EButton);
		
		JButton HButton = new JButton("Delete");
		HButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username = textField.getText();
				
				if(Username.equals("")) {
					JOptionPane.showMessageDialog(null, "Masukkan nama yang ingin dihapus!");
				}else {
					try {
						conn = DriverManager.getConnection(url, user, password);
						List<String> user = new ArrayList<>();
						
						user.add(Username);

						String sql = "DELETE FROM user WHERE Username='"+user.get(0)+"'";
						stmt = conn.createStatement();
						
						if(stmt.executeUpdate(sql) > 0){
			                	JOptionPane.showMessageDialog(null, "Data yang dihapus berhasil");
			                	showDataUser();
			                	clear();
			            }else{
			                    JOptionPane.showMessageDialog(null, "Data yang dihapus tidak ada");
			                    showDataUser();
			                }
						
					}catch(Exception a) {
						a.printStackTrace();
					}
				}
			}
		});
		HButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		HButton.setBounds(562, 401, 92, 30);
		contentPane.add(HButton);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(146, 359, 92, 30);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(146, 400, 92, 30);
		contentPane.add(lblPassword);
		
		JLabel lblPencarian = new JLabel("Pencarian");
		lblPencarian.setForeground(Color.WHITE);
		lblPencarian.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPencarian.setBounds(460, 40, 92, 30);
		contentPane.add(lblPencarian);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(460, 79, 136, 30);
		contentPane.add(textField_3);
		
		JButton CButton = new JButton("Cari");
		CButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username = textField_3.getText();
				
				if(Username.equals("")) {
					JOptionPane.showMessageDialog(null, "Masukkan nama yang ingin dicari");
				}else {
				try{
					conn = DriverManager.getConnection(url, user, password);
					List<String> user = new ArrayList<>();
					
					user.add(Username);
					
					String sql = "SELECT Username, Login_terakhir, Email FROM user WHERE Username='"+user.get(0)+"'";
					stmt = conn.createStatement();
					rs=stmt.executeQuery(sql);
					
					if(rs.next()){
		                if(textField_3.getText().equals(rs.getString("Username")) ){
		                	rs=stmt.executeQuery(sql);
		                }
		            }else{
		                    JOptionPane.showMessageDialog(null, "Nama yang dicari tidak ada");
		                }
					table.setModel(DbUtils.resultSetToTableModel(rs));
			   }catch(Exception a){
				   a.printStackTrace();
			   		}
				}
			}
		});
		CButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		CButton.setBounds(562, 40, 92, 30);
		contentPane.add(CButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		passwordField.setBounds(248, 400, 240, 30);
		contentPane.add(passwordField);
		
		JLabel lblJudul = new JLabel("Pengelolaan User");
		lblJudul.setForeground(Color.WHITE);
		lblJudul.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblJudul.setBounds(248, 9, 192, 30);
		contentPane.add(lblJudul);
	}
}
