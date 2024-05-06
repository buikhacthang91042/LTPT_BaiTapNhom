package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import DAO.DAO_QuenMatKhau;
import connect.ConnectDB;
import entity.TaiKhoan;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLSocketFactory;

import java.util.Properties;
public class GUI_QuenMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField txtTendangnhap;
	private JTextField txtDiachiemail;
	private JTextField txtNhapmacode;
	private JTextField txtMaCode;


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

	public GUI_QuenMatKhau() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1451, 914);
		pnlContent = new JPanel();
		pnlContent.setBackground(new Color(129, 192, 192));
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel lbltieude = new JLabel("Nhập thông tin để đổi lại mật khẩu");
		lbltieude.setFont(new Font("Arial", Font.BOLD, 24));
		lbltieude.setBounds(580, 105, 504, 74);
		pnlContent.add(lbltieude);
		
		Canvas canvas1 = new Canvas();
		canvas1.setBackground(Color.BLACK);
		canvas1.setBounds(0, 180, 1550, 2);
		pnlContent.add(canvas1);
		
		JLabel lblQuenmk = new JLabel("Quên mật khẩu ?");
		lblQuenmk.setFont(new Font("Arial", Font.BOLD, 29));
		lblQuenmk.setBounds(650, 0, 266, 94);
		pnlContent.add(lblQuenmk);
		
		JLabel lblTendangnhap = new JLabel("Tên đăng nhập");
		lblTendangnhap.setFont(new Font("Arial", Font.BOLD, 17));
		lblTendangnhap.setBounds(100, 220, 197, 44);
		pnlContent.add(lblTendangnhap);
		
		txtTendangnhap = new JTextField();
		txtTendangnhap.setFont(new Font("Arial", Font.PLAIN, 24));
		txtTendangnhap.setBounds(100, 270, 350, 40);
		pnlContent.add(txtTendangnhap);
		txtTendangnhap.setColumns(10);
		
		JLabel lblDiachiemail = new JLabel("Địa chỉ email");
		lblDiachiemail.setFont(new Font("Arial", Font.BOLD, 17));
		lblDiachiemail.setBounds(100, 350, 197, 44);
		pnlContent.add(lblDiachiemail);
		
		txtDiachiemail = new JTextField();
		txtDiachiemail.setFont(new Font("Arial", Font.PLAIN, 24));
		txtDiachiemail.setColumns(10);
		txtDiachiemail.setBounds(100, 400, 350, 40);
		pnlContent.add(txtDiachiemail);
		
		txtNhapmacode = new JTextField();
		txtNhapmacode.setFont(new Font("Arial", Font.PLAIN, 24));
		txtNhapmacode.setColumns(10);
		txtNhapmacode.setBounds(100, 530, 350, 40);
		pnlContent.add(txtNhapmacode);
		
		JLabel lblNhapcode = new JLabel("Nhập mã code");
		lblNhapcode.setFont(new Font("Arial", Font.BOLD, 17));
		lblNhapcode.setBounds(100, 480, 197, 44);
		pnlContent.add(lblNhapcode);
		
		txtMaCode = new JTextField();
		txtMaCode.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaCode.setBackground(new Color(192, 192, 192));
		txtMaCode.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		txtMaCode.setEditable(false);
		txtMaCode.setBounds(500,530, 79,40);
		pnlContent.add(txtMaCode);
		txtMaCode.setColumns(10);
		txtMaCode.setText(taoMaNgauNhien());
		
		JLabel lblDoiMa = new JLabel();
		lblDoiMa.setIcon(new ImageIcon(GUI_QuenMatKhau.class.getResource("/Image/icon_DoiMa.jpg")));
		lblDoiMa.setBounds(589, 530, 70, 40);
		lblDoiMa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtMaCode.setText(taoMaNgauNhien());
			}
			
		});
		pnlContent.add(lblDoiMa);
		
		JButton btnGui = new JButton("Gửi");
		btnGui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ktraMaCode();
				ktraEmail();
				
				
			}
		});
		btnGui.setFont(new Font("Arial", Font.BOLD, 20));
		btnGui.setBounds(100, 650, 158, 65);
		pnlContent.add(btnGui);
	}
	public String taoMaNgauNhien() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
	public void ktraEmail() {
		String tenDangNhap = txtTendangnhap.getText();
		String email = txtDiachiemail.getText();
		DAO_QuenMatKhau dao = new DAO_QuenMatKhau();
		List<TaiKhoan> list = dao.getTK(tenDangNhap);
		for (TaiKhoan taiKhoan : list) {
			if(taiKhoan.getGmail().equalsIgnoreCase(email)) {
				JOptionPane.showMessageDialog(null, "Đã gửi mật khẩu qua Email !");
				guiEmail(txtTendangnhap.getText(),txtDiachiemail.getText(), taiKhoan.getMatKhau());
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Sai địa chỉ Email");
			}
		}
	}
	public void ktraMaCode() {
		String maCodeNhap = txtNhapmacode.getText();
	    String maCodeHienTai = txtMaCode.getText();
	    if (maCodeNhap.equalsIgnoreCase(maCodeHienTai)) {
	    	return;
	    }else {
	    	JOptionPane.showMessageDialog(null, "Sai mã code");
	    }
	}
	public void guiEmail(String tenDangNhap, String email, String matKhau) {
       String username = "cuahangthoitrangnhom14@gmail.com"; //địa chỉ email 
       String password = "tvux afcz osqz gdvg"; //mật khẩu 
       ArrayList<String> arr = new ArrayList<String>();
       arr.add(email);
       try {
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", 587);
        


       


        Session s = Session.getInstance(p,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

       
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
            msg.setSubject("Mật khẩu cho tài khoản " + tenDangNhap);
            msg.setText("Chào bạn,\n\nMật khẩu của tài khoản " + tenDangNhap + " là: " + matKhau);
            Transport.send(msg);

            System.out.println("Đã gửi email thành công!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

