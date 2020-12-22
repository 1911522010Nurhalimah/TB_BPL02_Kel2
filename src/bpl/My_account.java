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

public class My_account extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contact frame = new Contact();
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
	public My_account() {
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
		
		JLabel Label1 = new JLabel("Password : ");
		Label1.setForeground(Color.WHITE);
		Label1.setFont(new Font("Tahoma", Font.BOLD, 16));
		Label1.setBounds(250, 210, 98, 30);
		contentPane.add(Label1);
		
		JLabel Label2 = new JLabel("Username : ");
		Label2.setForeground(Color.WHITE);
		Label2.setFont(new Font("Tahoma", Font.BOLD, 16));
		Label2.setBounds(250, 169, 98, 30);
		contentPane.add(Label2);
		
		JButton KButton = new JButton("Kembali");
		KButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Dashboard().setVisible(true);
				dispose();
			}
		});
		KButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		KButton.setBounds(293, 330, 223, 30);
		contentPane.add(KButton);
		
		JLabel Label3 = new JLabel("");
		Label3.setForeground(Color.WHITE);
		Label3.setFont(new Font("Tahoma", Font.BOLD, 16));
		Label3.setBounds(358, 169, 98, 30);
		contentPane.add(Label3);
		
		JLabel Label4 = new JLabel("");
		Label4.setForeground(Color.WHITE);
		Label4.setFont(new Font("Tahoma", Font.BOLD, 16));
		Label4.setBounds(358, 210, 98, 30);
		contentPane.add(Label4);
	}
}
