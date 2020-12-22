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

import java.util.*;
import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Pbarang extends JFrame {

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
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
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

	public void clear() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
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
	public Pbarang() {
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
		
		JLabel lblPengelolaanBarang = new JLabel("Pengelolaan Barang");
		lblPengelolaanBarang.setForeground(Color.WHITE);
		lblPengelolaanBarang.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblPengelolaanBarang.setBounds(246, 9, 221, 30);
		contentPane.add(lblPengelolaanBarang);
		
		textField = new JTextField();
		textField.setBounds(215, 300, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(215, 326, 86, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(215, 352, 86, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(215, 404, 86, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(215, 378, 86, 20);
		contentPane.add(textField_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 50, 508, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		JLabel lblNoSku = new JLabel("No. Sku");
		lblNoSku.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNoSku.setForeground(Color.WHITE);
		lblNoSku.setBounds(146, 303, 41, 14);
		contentPane.add(lblNoSku);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setForeground(Color.WHITE);
		lblNama.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNama.setBounds(146, 329, 41, 14);
		contentPane.add(lblNama);
		
		JLabel lblHargaJual = new JLabel("Harga jual");
		lblHargaJual.setForeground(Color.WHITE);
		lblHargaJual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHargaJual.setBounds(146, 406, 59, 14);
		contentPane.add(lblHargaJual);
		
		JLabel lblStok = new JLabel("Stok");
		lblStok.setForeground(Color.WHITE);
		lblStok.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStok.setBounds(146, 355, 41, 14);
		contentPane.add(lblStok);
		
		JLabel lblHargaBeli = new JLabel("Harga beli");
		lblHargaBeli.setForeground(Color.WHITE);
		lblHargaBeli.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHargaBeli.setBounds(146, 381, 59, 14);
		contentPane.add(lblHargaBeli);
		
		JButton TButton = new JButton("Tambah");
		TButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String no_sku = textField.getText();
				String nama = textField_1.getText();
				String stok = textField_2.getText();
				String hbeli = textField_3.getText();
				String hjual = textField_4.getText();
				Integer stk=Integer.parseInt(stok);
				Integer hb=Integer.parseInt(hbeli);
				Integer hj=Integer.parseInt(hjual);
				
				if(no_sku.equals("") || nama.equals("") || stok.equals("") || hbeli.equals("") || hjual.equals("")) {
					JOptionPane.showMessageDialog(null, "Error!");
				}else {
					try {
						conn = DriverManager.getConnection(url, user, password);
						List<String> barang = new ArrayList<>();
						
						barang.add(no_sku);
						barang.add(nama);
						barang.add(stok);
						barang.add(hbeli);
						barang.add(hjual);
						
					if(stk < 0 || hb < 0 || hj < 0) {
						JOptionPane.showMessageDialog(null, "Data barang gagal ditambahkan");
					}else {
						stmt = conn.createStatement();
						String sql = "INSERT INTO barang VALUES('"+barang.get(0)+"','"+barang.get(1)+"','"+barang.get(2)+"','"+barang.get(3)+"','"+barang.get(4)+"')";
						
						JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
						clear();
						
						int a = stmt.executeUpdate(sql);
						if(a>0) {
							showDataBarang();
						}
					}
						
					}catch(Exception a) {
						a.printStackTrace();
					}
				}
			}
		});
		TButton.setBounds(330, 299, 89, 23);
		contentPane.add(TButton);
		
		JButton EButton = new JButton("Edit");
		EButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String no_sku = textField.getText();
				String nama = textField_1.getText();
				String stok = textField_2.getText();
				String hbeli = textField_3.getText();
				String hjual = textField_4.getText();
				Integer stk=Integer.parseInt(stok);
				Integer hb=Integer.parseInt(hbeli);
				Integer hj=Integer.parseInt(hjual);
				
				if(nama.equals("") || stok.equals("") || hbeli.equals("") || hjual.equals("")) {
					JOptionPane.showMessageDialog(null, "Masukkan kode barang yang ingin diedit!");
				}else {
					try {
						conn = DriverManager.getConnection(url, user, password);
						List<String> barang = new ArrayList<>();
						
						barang.add(no_sku);
						barang.add(nama);
						barang.add(stok);
						barang.add(hbeli);
						barang.add(hjual);

					if(stk < 0 || hb < 0 || hj < 0) {
						JOptionPane.showMessageDialog(null, "Data barang gagal ditambahkan");
					}else {
						stmt = conn.createStatement();
						String sql = "UPDATE barang SET nama='"+barang.get(1)+"', stock='"+barang.get(2)+"', harga_beli='"+barang.get(3)+"', harga_jual='"+barang.get(4)+"' WHERE sku='"+barang.get(0)+"'";
						
					if(stmt.executeUpdate(sql) > 0){
		                	JOptionPane.showMessageDialog(null, "Barang yang diedit berhasil");
		                	showDataBarang();
		                	clear();
					}else{
		                   	 JOptionPane.showMessageDialog(null, "Barang yang diedit tidak ada");
		                    	showDataBarang();
		                		}
					}
						
					}catch(Exception a) {
						a.printStackTrace();
					}
				}
			}
		});
		EButton.setBounds(330, 325, 89, 23);
		contentPane.add(EButton);
		
		JButton HButton = new JButton("Hapus");
		HButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String no_sku = textField.getText();
				
				if(no_sku.equals("")) {
					JOptionPane.showMessageDialog(null, "Masukkan kode barang yang ingin dihapus!");
				}else {
					try {
						conn = DriverManager.getConnection(url, user, password);
						List<String> barang = new ArrayList<>();
						
						barang.add(no_sku);

						stmt = conn.createStatement();
						String sql = "DELETE FROM barang WHERE sku='"+barang.get(0)+"'";

						if(stmt.executeUpdate(sql) > 0){
		                	JOptionPane.showMessageDialog(null, "Barang yang dihapus berhasil");
		                	showDataBarang();
		                	clear();
						}else{
		                    JOptionPane.showMessageDialog(null, "Barang yang dihapus tidak ada");
		                    showDataBarang();
		                }
						
					}catch(Exception a) {
						a.printStackTrace();
					}
				}
			}
		});
		HButton.setBounds(330, 351, 89, 23);
		contentPane.add(HButton);
		
		JButton CButton = new JButton("Cari");
		CButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nama = textField_5.getText();
				
				if(nama.equals("")) {
					JOptionPane.showMessageDialog(null, "Masukkan kode barang yang ingin dicari");
				}else {
				try{
					conn = DriverManager.getConnection(url, user, password);
					List<String> barang = new ArrayList<>();
					
					barang.add(nama);
					
					String sql = "SELECT sku, nama, stock, harga_beli, harga_jual FROM barang WHERE nama='"+barang.get(0)+"'";
					stmt = conn.createStatement();
					rs=stmt.executeQuery(sql);
					if(rs.next()){
		                if(textField_5.getText().equals(rs.getString("nama")) ){
		                	rs=stmt.executeQuery(sql);
		                }
		            }else{
		                    JOptionPane.showMessageDialog(null, "Barang yang dicari tidak ada");
		                }
					table.setModel(DbUtils.resultSetToTableModel(rs));
			   }catch(Exception a){
				   a.printStackTrace();
			   		}
				}
			}
		});
		CButton.setBounds(565, 299, 89, 23);
		contentPane.add(CButton);
		
		JLabel lblPencarian = new JLabel("Pencarian");
		lblPencarian.setForeground(Color.WHITE);
		lblPencarian.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPencarian.setBounds(474, 300, 59, 14);
		contentPane.add(lblPencarian);
		
		textField_5 = new JTextField();
		textField_5.setBounds(474, 326, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton SButton = new JButton("Tampil");
		SButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showDataBarang();
			}
		});
		SButton.setBounds(565, 403, 89, 23);
		contentPane.add(SButton);
	}
}
