package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI_TrangChu extends JFrame{

	private JPanel contentPane;
	private JMenuItem menuItemTrangChu;
	private JMenuItem menuItem_SP;
	private JMenuItem menuItem_KH;
	private JMenuItem menuItemThoat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_TrangChu frame = new GUI_TrangChu();
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
	public GUI_TrangChu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1443, 800);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("img\\vpp.jpg"));
		lblNewLabel_2.setBounds(166, 345, 364, 216);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setIcon(new ImageIcon("img\\book.png"));
		lblNewLabel.setBounds(36, 385, 619, 328);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("Nhà Sách");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Monotype Corsiva", Font.BOLD, 70));
		lblNewLabel_3.setBounds(140, 106, 297, 97);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Thiên Văn");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Monotype Corsiva", Font.BOLD, 70));
		lblNewLabel_3_1.setBounds(415, 189, 312, 97);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblHome = new JLabel("\r\n");
		lblHome.setIcon(new ImageIcon("img\\home.jpg"));
		lblHome.setBounds(0, 0, 1429, 771);
		contentPane.add(lblHome);
	}
	public JPanel getTrangChu() {
		return (JPanel) this.getContentPane();
	}
	
}
