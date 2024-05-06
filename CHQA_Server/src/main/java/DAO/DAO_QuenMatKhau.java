package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.KhachHang;
import entity.QuanAo;
import entity.TaiKhoan;

public class DAO_QuenMatKhau {
	List<TaiKhoan> dsTaiKhoan = new ArrayList<>();
	public ArrayList<TaiKhoan> getTK(String taikhoan) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from TaiKhoan where TenDangNhap like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,taikhoan);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String tenDangNhap= rs.getString(1);
				String email= rs.getString(2);
				String matKhau = rs.getString(3);
				
				
				dsTaiKhoan.add(new TaiKhoan(tenDangNhap,email,matKhau));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<TaiKhoan>) dsTaiKhoan;
	}
	
		
}
