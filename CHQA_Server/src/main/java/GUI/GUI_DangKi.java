package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.DAO_DangNhap;
import connect.ConnectDB;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GUI_DangKi extends JFrame{
	private JTextField txtTaiKhoan;
	private JTextField txtGmail;
	private JTextField txtMatKhau;
	private JTextField txtXacNhan;
	private JPanel contentPane;
	public GUI_DangKi() {
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(100, 100, 990, 571);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//ConnectDB
 		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBounds(0, 0, 976, 524);
		contentPane.add(pnlThongTin);
		pnlThongTin.setLayout(null);
		
		JLabel lblDangKi = new JLabel("ĐĂNG KÍ");
		lblDangKi.setForeground(new Color(255, 0, 0));
		lblDangKi.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
		lblDangKi.setBounds(375, 34, 194, 57);
		pnlThongTin.add(lblDangKi);
		
		JLabel lblTaiKhoan = new JLabel("Tài khoản");
		lblTaiKhoan.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblTaiKhoan.setBounds(39, 160, 180, 35);
		pnlThongTin.add(lblTaiKhoan);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTaiKhoan.setBounds(154, 160, 240, 30);
		pnlThongTin.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		JLabel lblGmail = new JLabel("Gmail");
		lblGmail.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblGmail.setBounds(39, 261, 180, 35);
		pnlThongTin.add(lblGmail);
		
		txtGmail = new JTextField();
		txtGmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGmail.setColumns(10);
		txtGmail.setBounds(154, 261, 240, 30);
		pnlThongTin.add(txtGmail);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblMatKhau.setBounds(468, 160, 180, 35);
		pnlThongTin.add(lblMatKhau);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(667, 160, 240, 30);
		pnlThongTin.add(txtMatKhau);
		
		JLabel lblXacNhanMatKhau = new JLabel("Xác nhận mật khẩu");
		lblXacNhanMatKhau.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblXacNhanMatKhau.setBounds(468, 261, 180, 35);
		pnlThongTin.add(lblXacNhanMatKhau);
		
		txtXacNhan = new JTextField();
		txtXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtXacNhan.setColumns(10);
		txtXacNhan.setBounds(667, 261, 240, 30);
		pnlThongTin.add(txtXacNhan);
		
		JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				GUI_DangNhap dangNhap = new GUI_DangNhap();
				dangNhap.setVisible(true);
			}
		});
		btnDangNhap.setFont(new Font("Arial", Font.BOLD, 30));
		btnDangNhap.setBounds(195, 420, 199, 40);
		pnlThongTin.add(btnDangNhap);
		
		JButton btnDangKi = new JButton("Đăng kí");
		btnDangKi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAO_DangNhap dn = new DAO_DangNhap();
				if(validData()) {
					if (dn.kiemTraTonTai(txtTaiKhoan.getText())) {
	                    JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại !");
	                }else {
	                	if(txtMatKhau.getText().equals(txtXacNhan.getText())) {
						int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn đăng kí ?","Xác nhận",JOptionPane.YES_NO_OPTION);
							if(xacNhan!= JOptionPane.YES_OPTION) {
							return;
							}else {
							
							dn.themTK(txtTaiKhoan.getText(), txtGmail.getText(), txtMatKhau.getText());
							JOptionPane.showMessageDialog(null, "Đăng kí thành công");
						}
	                	}else {
						JOptionPane.showMessageDialog(null, "Mật khẩu không khớp");
					}
						
	                }
				}
			}
		});
		btnDangKi.setFont(new Font("Arial", Font.BOLD, 30));
		btnDangKi.setBounds(506, 420, 189, 40);
		pnlThongTin.add(btnDangKi);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI_DangKi dk=  new GUI_DangKi();
	}
	
	public boolean validData() {
		if(txtTaiKhoan.getText().equals("") ||  txtGmail.getText().equals("") || txtMatKhau.getText().equals("") || txtXacNhan.getText().equals("")  ) {
			JOptionPane.showMessageDialog(this,"Thông tin nhập không được bỏ trống !");
			return false;
		}else if(!txtTaiKhoan.getText().matches("^NV[0-9]{3}$")) {
			JOptionPane.showMessageDialog(this,"Tài khoản sai định dạng !");
			return false;
		}else if (!txtGmail.getText().matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
	        JOptionPane.showMessageDialog(this, "Gmail sai định dạng !");
	        return false;
		}
		return true;
	}
	
}