package GUI;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connect.ConnectDB;

import java.awt.Panel;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JButton;


public class GUI_HoaDon extends javax.swing.JPanel {
	private JTextField txtTenNguoiMua;
	private JTable table;


    public GUI_HoaDon() {
    	setBounds(new Rectangle(0, 0, 1308, 678));
        setLayout(null);
        setBackground(new Color(0, 64, 64));
        
      //ConnectDB
 		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 92, 1265, 416);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				 "Mã hóa đơn", "Mã quần áo", "Tên khách hàng", "Mã nhân viên","Tên quần áo","Thành tiền"
				}
		));
		
		JLabel lblDanhSachHoaDon = new JLabel("Danh sách hóa đơn");
		lblDanhSachHoaDon.setForeground(new Color(135, 206, 235));
		lblDanhSachHoaDon.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDanhSachHoaDon.setBounds(21, 60, 195, 32);
		add(lblDanhSachHoaDon);
		
		JLabel lblTimKiemHoaDon = new JLabel("Tìm kiếm hóa đơn");
		lblTimKiemHoaDon.setForeground(new Color(135, 206, 235));
		lblTimKiemHoaDon.setBounds(21, 551, 180, 32);
		add(lblTimKiemHoaDon);
		lblTimKiemHoaDon.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		
		txtTenNguoiMua = new JTextField();
		txtTenNguoiMua.setBounds(211, 551, 225, 32);
		add(txtTenNguoiMua);
		txtTenNguoiMua.setColumns(10);
		
		JButton btnTimKiemHoaDon = new JButton("TÌM");
		btnTimKiemHoaDon.setBackground(new Color(0, 64, 0));
		btnTimKiemHoaDon.setForeground(new Color(255, 255, 255));
		btnTimKiemHoaDon.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTimKiemHoaDon.setBounds(462, 551, 104, 30);
		add(btnTimKiemHoaDon);
		
		JButton btnInHoaDon = new JButton("IN HÓA ĐƠN");
		btnInHoaDon.setForeground(Color.WHITE);
		btnInHoaDon.setFont(new Font("Arial", Font.PLAIN, 20));
		btnInHoaDon.setBackground(new Color(0, 64, 0));
		btnInHoaDon.setBounds(597, 551, 180, 30);
		add(btnInHoaDon);
    }
}
