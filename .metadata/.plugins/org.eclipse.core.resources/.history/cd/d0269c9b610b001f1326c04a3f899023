package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class GUI_QuenMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnborder;
	private JTextField txtTendangnhap;
	private JTextField txtDiachiemail;
	private JTextField txtNhapmacode;
	private JTextField txtmacode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_QuenMatKhau frame = new GUI_QuenMatKhau();
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
	public GUI_QuenMatKhau() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1451, 914);
		pnborder = new JPanel();
		pnborder.setBackground(new Color(129, 192, 192));
		pnborder.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnborder);
		pnborder.setLayout(null);
		
		
		JLabel lbltieude = new JLabel("Nhập thông tin để đổi lại mật khẩu");
		lbltieude.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltieude.setBounds(580, 105, 504, 74);
		pnborder.add(lbltieude);
		
		Canvas canvas1 = new Canvas();
		canvas1.setBackground(Color.BLACK);
		canvas1.setBounds(0, 180, 1550, 2);
		pnborder.add(canvas1);
		
		JLabel lblQuenmk = new JLabel("Quên mật khẩu ?");
		lblQuenmk.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblQuenmk.setBounds(650, 0, 266, 94);
		pnborder.add(lblQuenmk);
		
		JLabel lblTendangnhap = new JLabel("Tên đăng nhập");
		lblTendangnhap.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTendangnhap.setBounds(100, 220, 197, 44);
		pnborder.add(lblTendangnhap);
		
		txtTendangnhap = new JTextField();
		txtTendangnhap.setBounds(100, 270, 350, 40);
		pnborder.add(txtTendangnhap);
		txtTendangnhap.setColumns(10);
		
		JLabel lblDiachiemail = new JLabel("Địa chỉ email");
		lblDiachiemail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiachiemail.setBounds(100, 350, 197, 44);
		pnborder.add(lblDiachiemail);
		
		txtDiachiemail = new JTextField();
		txtDiachiemail.setColumns(10);
		txtDiachiemail.setBounds(100, 400, 350, 40);
		pnborder.add(txtDiachiemail);
		
		txtNhapmacode = new JTextField();
		txtNhapmacode.setColumns(10);
		txtNhapmacode.setBounds(100, 530, 350, 40);
		pnborder.add(txtNhapmacode);
		
		JLabel lblNhapcode = new JLabel("Nhập mã code");
		lblNhapcode.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNhapcode.setBounds(100, 480, 197, 44);
		pnborder.add(lblNhapcode);
		
		txtmacode = new JTextField();
		txtmacode.setBounds(500,530, 180,40);
		pnborder.add(txtmacode);
		txtmacode.setColumns(10);
		
		JLabel lblDoiMa = new JLabel();
		lblDoiMa.setIcon(new ImageIcon(GUI_QuenMatKhau.class.getResource("/Image/icon_DoiMa.jpg")));
		lblDoiMa.setBounds(730, 530, 70, 40);
		lblDoiMa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			
			}
			
		});
		pnborder.add(lblDoiMa);
		
		JButton btnGui = new JButton("Gửi");
		btnGui.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGui.setBounds(100, 650, 158, 65);
		pnborder.add(btnGui);
	}
}
