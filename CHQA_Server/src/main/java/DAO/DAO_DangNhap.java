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
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import entity.TaiKhoan;

public class DAO_DangNhap {
	
	List<TaiKhoan> ds = new ArrayList<>();
	public List<TaiKhoan> getAllTK() {
		List<TaiKhoan> ds = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stmt1= null;
		try {
			String sql= "Select * from TaiKhoan";
			stmt1= con.createStatement();
			ResultSet rs= stmt1.executeQuery(sql);
			while(rs.next()) {
				ds.add(new TaiKhoan(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	
	
	public boolean themTK(String tk, String email, String mk) {	
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt =null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into TaiKhoan values(?,?,?)");
			stmt.setString(1, tk);
			stmt.setString(2, email);
			stmt.setString(3, mk);
			
			n = stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0;
	}
	public ArrayList<TaiKhoan> dangNhap(String tk, String mk) {	
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql =("select * from TaiKhoan where TenDangNhap like ? and MatKhau like ?");
			stmt= con.prepareStatement(sql);
			stmt.setNString(1, tk);
			stmt.setNString(2, mk);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String taiKhoan= rs.getString(1);
				String matKhau = rs.getString(3);
			
				ds.add(new TaiKhoan(taiKhoan,matKhau));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ArrayList<TaiKhoan>) ds;
	}
	public boolean kiemTraTonTai(String taiKhoan) {	
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt =null;
		int n=0;
		try {
			stmt = con.prepareStatement("select count(*) from TaiKhoan where TenDangNhap = ?");
			stmt.setString(1, taiKhoan);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; 
            }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean deleteByMaNV(String maNV) {
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        stmt = con.prepareStatement("delete from TaiKhoan where TenDangNhap = ?");
	        stmt.setString(1, maNV);
	        n = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return n > 0;
	    }
}