package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import connect.ConnectDB;
import entity.LoaiQuanAo;
import entity.NhaCungCap;

public class DAO_LoaiQuanAo {
	List<LoaiQuanAo> loaiQuanAo = new ArrayList<>();
	public List<LoaiQuanAo> getAllLoaiQuanAo() {
		List<LoaiQuanAo> loaiQuanAo = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stmt1= null;
		try {
			String sql= "Select * from LoaiQuanAo";
			stmt1= con.createStatement();
			ResultSet rs= stmt1.executeQuery(sql);
			while(rs.next()) {
				loaiQuanAo.add(new LoaiQuanAo(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loaiQuanAo;
	}
	
	public boolean create(LoaiQuanAo loaiQuanAo) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into LoaiQuanAo values(?,?)");
			stmt.setString(1, loaiQuanAo.getMaLoai());
			stmt.setString(2, loaiQuanAo.getTenLoai());
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return n>0;
	}
	
	public boolean delete (String MaLoaiQuanAo) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from LoaiQuanAo where MaLoai = ?");
			stmt.setString(1, MaLoaiQuanAo);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return n >0;
	}
	
	public boolean suaLoaiQuanAo( LoaiQuanAo LoaiMoi) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt2= null;
		int n = 0;
		
			try {
				stmt2 = con.prepareStatement("update LoaiQuanAo set MaLoai=?, TenLoai=? where MaLoai=?");
				stmt2.setString(1, LoaiMoi.getMaLoai());
				stmt2.setString(2, LoaiMoi.getTenLoai());
				stmt2.setString(3, LoaiMoi.getMaLoai());
				n = stmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	return n>0;
	}

	public ArrayList<LoaiQuanAo> timTheoTen(String tenLoai) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from LoaiQuanAo where TenLoai like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,"%"+tenLoai+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String ten = rs.getString(2);
				loaiQuanAo.add(new LoaiQuanAo(ma, ten));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<LoaiQuanAo>) loaiQuanAo;
	}
	
	public ArrayList<LoaiQuanAo> timTheoTenVa(String tenLoai) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from LoaiQuanAo where TenLoai like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,"%"+tenLoai+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String ten = rs.getString(2);
				loaiQuanAo.add(new LoaiQuanAo(ma, ten));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<LoaiQuanAo>) loaiQuanAo;
	}
	public LoaiQuanAo getLoaiQuanAoByTen(String ten) {
		Connection con = ConnectDB.getInstance().getConnection();
		LoaiQuanAo loai = null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from LoaiQuanAo where TenLoai like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,ten);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String tenLoai = rs.getString(2);
				loai= new LoaiQuanAo(ma, tenLoai);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return loai;
	}
	public LoaiQuanAo getLoaiQuanAoByMa(String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		LoaiQuanAo loai = null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from LoaiQuanAo where MaLoai like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,ma);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maLoai= rs.getString(1);
				String tenLoai = rs.getString(2);
				loai= new LoaiQuanAo(maLoai, tenLoai);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return loai;
	}
	public boolean updateAllMaLoaiQuanAo(int deletedRowCount, String maLoaiQADaXoa) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "SELECT MaLoai FROM LoaiQuanAo WHERE MaLoai > ?";
		List<String> maQAList = new ArrayList<>();

		try {
			PreparedStatement stmt = null;
			stmt= con.prepareStatement(query);
		    stmt.setString(1, maLoaiQADaXoa);
		    ResultSet rs = stmt.executeQuery();

		    while (rs.next()) {
		    	maQAList.add(rs.getString("MaLoai"));
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    return false;
		}

	    for (String MaLoaiCu : maQAList) {
	        int maCu = Integer.parseInt(MaLoaiCu.substring(3));
	        int maMoi = maCu - deletedRowCount;
	        String newMaNCC = "ML" + String.format("%03d", maMoi);

	        String updateQuery = "UPDATE LoaiQuanAo SET MaLoai = ? WHERE MaLoai = ?";
	        try (PreparedStatement updateStatement = ConnectDB.getInstance().getConnection().prepareStatement(updateQuery)) {
	            updateStatement.setString(1, newMaNCC);
	            updateStatement.setString(2, MaLoaiCu);
	            updateStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    return true;
	}
}
