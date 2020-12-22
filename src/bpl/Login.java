package bpl;

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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private static String url = "jdbc:mysql://localhost/tb_bpl?serverTimezone=Asia/Jakarta";
	private static String user = "root";
	private static String password = "";
	private static ResultSet rs;
	private static Connection conn;
	private static Statement stmt;
	
	public class RandomString {
	    private char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
	    private StringBuilder stringBuilder = new StringBuilder();
	    private Random random = new Random();
	    private String output;

	    public String getRandom() {
	        for (int lenght = 0; lenght < 5; lenght++) {
	            Character character = chars[random.nextInt(chars.length)];
	            stringBuilder.append(character);
	        }
	        output = stringBuilder.toString();
	        stringBuilder.delete(0, 10);

	        return output;
	    }
	}
	
	private int salah=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel GLabel = new JLabel("");
		GLabel.setIcon(new ImageIcon(Login.class.getResource("/gambar/si_19.png")));
		GLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GLabel.setBounds(110, 25, 105, 105);
		contentPane.add(GLabel);
		
		JLabel ULabel = new JLabel("USERNAME");
		ULabel.setForeground(Color.WHITE);
		ULabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		ULabel.setBounds(46, 159, 92, 30);
		contentPane.add(ULabel);
		
		JLabel PLabel = new JLabel("PASSWORD");
		PLabel.setForeground(Color.WHITE);
		PLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		PLabel.setBounds(43, 238, 95, 30);
		contentPane.add(PLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(46, 200, 240, 30);
		contentPane.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		passwordField.setBounds(46, 269, 240, 30);
		contentPane.add(passwordField);
		
		JButton LButton = new JButton("Login");
		LButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				My_account my_account = new My_account();
				
				String Username = textField.getText();
				String Passwd = passwordField.getText();
				
				if(Username.equals("") || Passwd.equals("")) {
					JOptionPane.showMessageDialog(null, "Masukkan user/pass!");
				}else {
					try {
						conn = DriverManager.getConnection(url, user, password);				
						stmt = conn.createStatement();
						String sql = "SELECT * FROM user WHERE username='"+textField.getText()+"' AND password='"+passwordField.getText()+"'";
						rs = stmt.executeQuery(sql);
						
						if(rs.next()){
			                if(textField.getText().equals(rs.getString("username")) && passwordField.getText().equals(rs.getString("password"))){
			                	JOptionPane.showMessageDialog(null, "Login Berhasil!");
			                	String sql1 = "UPDATE user SET Login_terakhir=NOW() WHERE username='"+textField.getText()+"'";
			                	stmt.executeUpdate(sql1);
								new Dashboard().setVisible(true);
								dispose();
			                }
			            }else{
			            	if(salah < 3) {
			                    JOptionPane.showMessageDialog(null, "Invalid user/pass");
			                    salah++;
			            	}else {
			            		RandomString random = new RandomString();
			            		random.getRandom();
			            		passwordField.setText(random.getRandom());
			            		salah = 0;
			            		String sql2 = "UPDATE user SET password='"+passwordField.getText()+"' WHERE Username='"+textField.getText()+"'";
			            		stmt.executeUpdate(sql2);
			            		JOptionPane.showMessageDialog(null, "Pass telah berubah");
			            	}
			                }
					}catch(Exception a) {
						a.printStackTrace();
					}
				}
			}
		});
		LButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		LButton.setBounds(46, 310, 240, 30);
		contentPane.add(LButton);
		
		JButton RButton = new JButton("Registrasi");
		RButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registrasi().setVisible(true);
				dispose();
				
			}
		});
		RButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		RButton.setBounds(46, 351, 240, 30);
		contentPane.add(RButton);
	}

}
