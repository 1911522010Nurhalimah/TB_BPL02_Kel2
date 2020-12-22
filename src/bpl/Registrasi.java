package bpl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.*;
import java.sql.*;
	
public class Registrasi extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	
	private static String url = "jdbc:mysql://localhost/tb_bpl?serverTimezone=Asia/Jakarta";
	private static String user = "root";
	private static String password = "";
	private static ResultSet rs;
	private static Connection conn;
	private static Statement stmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrasi frame = new Registrasi();
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
	public Registrasi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel GLabel = new JLabel("");
		GLabel.setIcon(new ImageIcon(Registrasi.class.getResource("/gambar/si_19.png")));
		GLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GLabel.setBounds(110, 25, 105, 105);
		contentPane.add(GLabel);
		
		JLabel ULabel = new JLabel("USERNAME");
		ULabel.setForeground(Color.WHITE);
		ULabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		ULabel.setBounds(41, 141, 92, 30);
		contentPane.add(ULabel);
		
		JLabel ELabel = new JLabel("EMAIL");
		ELabel.setForeground(Color.WHITE);
		ELabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		ELabel.setBounds(41, 213, 92, 30);
		contentPane.add(ELabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(41, 172, 240, 30);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(41, 244, 240, 30);
		contentPane.add(textField_1);
		
		JLabel PLabel = new JLabel("PASSWORD");
		PLabel.setForeground(Color.WHITE);
		PLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		PLabel.setBounds(41, 285, 95, 30);
		contentPane.add(PLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		passwordField.setBounds(41, 316, 240, 30);
		contentPane.add(passwordField);
		
		JButton RButton = new JButton("Registrasi");
		RButton.addActionListener(new ActionListener() {
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
						
						JOptionPane.showMessageDialog(null, "Registrasi Berhasil!");
						new Login().setVisible(true);
						dispose();
						
						int a = stmt.executeUpdate(sql);
						if(a>0) {
						System.out.println("Data Telah Tersimpan");
						}						
					}catch(Exception a) {
						a.printStackTrace();
					}
				}
							
			}
		});
		RButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		RButton.setBounds(41, 360, 240, 30);
		contentPane.add(RButton);
	}

}
