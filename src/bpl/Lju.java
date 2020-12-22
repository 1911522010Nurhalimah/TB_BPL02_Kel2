package bpl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.util.*;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Lju extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBoxBulan;
	private JComboBox comboBoxHari;
	
	public static String url = "jdbc:mysql://localhost/bpl?serverTimezone=Asia/Jakarta";
	public static String user = "root";
	public static String password = "";
	public static ResultSet rs;
	public static Connection conn;
	public static Statement stmt;
	private JTextField textField;
	

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

	public void fillComboBoxBulan() {
		
		try {
//			String sql ="SELECT transaksi.tanggal, transaksi_detail.No_resi, transaksi_detail.Sku, transaksi_detail.Jumlah*transaksi_detail.Harga AS total FROM transaksi, transaksi_detail ";
			String sql ="SELECT MONTHNAME(tanggal) AS bulan FROM transaksi GROUP BY bulan";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				comboBoxBulan.addItem(rs.getString("bulan"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fillComboBoxHari() {
		
		try {
			String sql ="SELECT tanggal FROM transaksi GROUP BY tanggal";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				comboBoxHari.addItem(rs.getString("tanggal"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshTable() {
		try {
			String sql ="SELECT transaksi.tanggal, transaksi_detail.No_resi, transaksi_detail.Sku, transaksi_detail.jumlah*transaksi_detail.harga AS total FROM transaksi, transaksi_detail ";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			table.setModel (DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void getSumTotalPenjualan() {
		int sum=0;
		for (int i=0; i< table.getRowCount(); i++) {
			sum= sum + Integer.parseInt(table.getValueAt(i, 6).toString());
		}
		textField.setText(Integer.toString(sum));
	}
	/**
	 * Create the frame.
	 */
	public Lju() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(75, 0, 130));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 136, 441);
		panel.setBackground(new Color(0, 0, 0));
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
		KButton.setBounds(146, 11, 47, 28);
		KButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard().setVisible(true);
				dispose();
			}
		});
		KButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(KButton);
		
		JLabel lblLaporan = new JLabel("Laporan");
		lblLaporan.setBounds(221, 9, 91, 30);
		lblLaporan.setForeground(Color.WHITE);
		lblLaporan.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblLaporan);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 150, 508, 230);
		scrollPane.setEnabled(false);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.setEnabled(false);
		table.setEnabled(false);
		
		JButton SButton = new JButton("Tampilkan");
		SButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql ="SELECT transaksi.tanggal, "
								+ "transaksi_detail.Sku, "
								+ "barang.Nama AS nama_barang, "
								+ "COUNT(transaksi_detail.Sku) AS banyak_transaksi, "
								+ "SUM(transaksi_detail.Jumlah) AS terjual, "
								+ "barang.stock-transaksi_detail.Jumlah AS sisa_stok, "
								+ "SUM(transaksi_detail.Harga) AS total_penjualan "
								+ "FROM transaksi_detail "
								+ "INNER JOIN barang ON transaksi_detail.Sku=barang.Sku "
								+ "INNER JOIN transaksi ON transaksi_detail.No_resi=transaksi.No_resi "
								+ "GROUP BY transaksi_detail.id ";
					
					stmt = conn.createStatement();
					rs=stmt.executeQuery(sql);
					table.setModel (DbUtils.resultSetToTableModel(rs));
					getSumTotalPenjualan();;
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		SButton.setBounds(430, 95, 89, 23);
		contentPane.add(SButton);
		
		JButton PButton = new JButton("Print");
		PButton.setBounds(554, 95, 89, 23);
		contentPane.add(PButton);
		
		JLabel lblNewLabel = new JLabel("Total Penjualan");
		lblNewLabel.setBounds(430, 405, 89, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String sql ="select SUM(transaksi_detail.Harga) AS total_penjualan "
								+ "from transaksi_detail"
								+ "where tanggal=?";
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1, (String)comboBoxBulan.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while (rs.next()) {
						textField.setText("total_penjualan");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		textField.setBounds(530, 400, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBoxBulan = new JComboBox();
		comboBoxBulan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql ="SELECT transaksi.tanggal, "
								+ "transaksi_detail.Sku, "
								+ "barang.Nama AS nama_barang, "
								+ "COUNT(transaksi_detail.Sku) as banyak_transaksi, "
								+ "SUM(transaksi_detail.Jumlah) as terjual, "
								+ "barang.stock-transaksi_detail.jumlah AS sisa_stok, "
								+ "SUM(transaksi_detail.harga) AS total_penjualan "
								+ "FROM transaksi_detail "
								+ "INNER JOIN barang ON transaksi_detail.Sku=barang.Sku "
								+ "INNER JOIN transaksi ON transaksi_detail.No_resi=transaksi.No_resi "
								+ "WHERE MONTHNAME(tanggal)=? "
								+ "GROUP BY transaksi_detail.Sku";
					
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, (String)comboBoxBulan.getSelectedItem());
					ResultSet rs=pst.executeQuery();

					table.setModel (DbUtils.resultSetToTableModel(rs));
					getSumTotalPenjualan();
					while (rs.next()) {

					}
					pst.close();
					rs.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBoxBulan.setBounds(162, 95, 89, 23);
		contentPane.add(comboBoxBulan);
		
		JComboBox comboBoxHari = new JComboBox();
		comboBoxHari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql ="SELECT transaksi.tanggal, "
								+ "transaksi_detail.Sku, "
								+ "barang.Nama AS nama_barang, "
								+ "COUNT(transaksi_detail.Sku) AS banyak_transaksi, "
								+ "SUM(transaksi_detail.Jumlah) AS terjual, "
								+ "barang.stock-transaksi_detail.jumlah AS sisa_stok, "
								+ "SUM(transaksi_detail.Harga) AS total_penjualan "
								+ "FROM transaksi_detail "
								+ "INNER JOIN barang ON transaksi_detail.Sku=barang.Sku "
								+ "INNER JOIN transaksi ON transaksi_detail.No_resi=transaksi.No_resi "
								+ "WHERE tanggal=? "
								+ "GROUP BY transaksi_detail.Sku";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, (String)comboBoxHari.getSelectedItem());
					ResultSet rs=pst.executeQuery();

					table.setModel (DbUtils.resultSetToTableModel(rs));
					getSumTotalPenjualan();
					
					pst.close();
					rs.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBoxHari.setBounds(280, 95, 89, 23);
		contentPane.add(comboBoxHari);
	}
}
