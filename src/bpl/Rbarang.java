package bpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.sql.*;
import java.util.Date;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Rbarang extends JFrame {

	
	private static String url = "jdbc:mysql://localhost/tb_bpl?serverTimezone=Asia/Jakarta";
	private static String user = "root";
	private static String password = "";
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String kodebarang;
	private String kb, nb ;
    private Integer s, j;
	
    /**
	 * Launch the application.
	 */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rbarang frame = new Rbarang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public void showDataBarang(){
		try{
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM barang";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
	   }catch(Exception a){
		   a.printStackTrace();
	 }
	}

	/**
	 * Create the frame.
	 */
	public Rbarang() {
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
		
		textField = new JTextField();
		textField.setBounds(263, 72, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				skuFocusLost(evt);
			}
		});
		
		textField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				skuKeyReleased(evt);
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setBounds(263, 102, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_1.setEnabled(false);
		
		textField_2 = new JTextField();
		textField_2.setBounds(263, 132, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_2.setEnabled(false);
		
		textField_3 = new JTextField();
		textField_3.setBounds(263, 162, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton RButton = new JButton("ReStock");
		RButton.setBounds(389, 72, 85, 21);
		contentPane.add(RButton);
		
		JLabel lblSku = new JLabel("No. Sku");
		lblSku.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSku.setForeground(Color.WHITE);
		lblSku.setBounds(146, 75, 62, 14);
		contentPane.add(lblSku);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setForeground(Color.WHITE);
		lblNama.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNama.setBounds(146, 105, 47, 14);
		contentPane.add(lblNama);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(146, 137, 62, 14);
		contentPane.add(lblStock);
		
		JLabel lblRestock = new JLabel("ReStock");
		lblRestock.setForeground(Color.WHITE);
		lblRestock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRestock.setBounds(146, 167, 67, 14);
		contentPane.add(lblRestock);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 191, 508, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblReStock_Barang = new JLabel("ReStock Barang");
		lblReStock_Barang.setForeground(Color.WHITE);
		lblReStock_Barang.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblReStock_Barang.setBounds(226, 9, 176, 30);
		contentPane.add(lblReStock_Barang);
		
		RButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAddActionPerformed(evt);
			}
		});
	}
	
	public void koneksi() {
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Koneksi Berhasil");
		}
		catch (Exception e){
			System.out.println("Koneksi Gagal\n" +e);
		}
	}
	
	public void clear() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
	}
	
	public void update() {
		try {
			String x=textField_2.getText();
            String y=textField_3.getText();
            kb=String.valueOf(textField.getText());
            Integer stk=Integer.parseInt(x);
            Integer rstk=Integer.parseInt(y);
			
            Integer hasil= stk+rstk;
			String sql="UPDATE barang SET stock='"+ hasil +"' WHERE sku = '"+ kb +"'";
			stmt=conn.createStatement();
            stmt.execute(sql);
		}
		catch(Exception e){
			
		}
	}
	
	private void skuFocusLost(java.awt.event.FocusEvent evt) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT*FROM barang WHERE sku='"+textField.getText()+"'");
			
			if (rs.next()) {
				textField_1.setText(rs.getString("nama"));
				textField_2.setText(rs.getString("stock"));
			}
			else {
				textField_1.setText(null);
				textField_2.setText(null);
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gagal Memuat Data \n Data Tidak Di Temukan "+e.getMessage());
		}
	}
	
	private void skuKeyReleased(java.awt.event.KeyEvent evt) {
		kodebarang=textField.getText();

			try {
				conn = DriverManager.getConnection(url, user, password);
				stmt=conn.createStatement();
				rs=stmt.executeQuery("SELECT*FROM barang WHERE sku='"+textField.getText()+"'");
				
				if (rs.next()) {
					textField_1.setText(rs.getString("nama"));
					textField_2.setText(rs.getString("stock"));
					
				}
				else {
					textField_1.setText(null);
	                textField_2.setText(null);
				}
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Gagal Memuat Data \n Data Tidak Di Temukan"+e.getMessage());
			}
	}
	
	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        try {
        		update();
        		JOptionPane.showMessageDialog(null, "ReStock Berhasil");
        		showDataBarang();
        		clear();
        }
        catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "ERROR ADD \n"+e.getMessage());
        }
	}

	private JTable table;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
}
