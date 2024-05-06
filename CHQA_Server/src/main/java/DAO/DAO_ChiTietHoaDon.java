package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.QuanAo;

public class DAO_ChiTietHoaDon {
	
	public List<ChiTietHoaDon> getAllChiTietHoaDon(String maHD) {
	    Connection con = ConnectDB.getInstance().getConnection();
	    List<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
	    String sql = "select qa.MaQuanAo, qa.TenQuanAo, qa.MaKM, ct.SoLuong, ct.GiaBan, ct.ThanhTien "
	            + "from ChiTietHoaDon ct "
	            + "join QuanAo qa ON ct.MaQuanAo = qa.MaQuanAo "
	            + "where ct.MaHD = ?";
	    try {
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, maHD);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
	            QuanAo qa = new QuanAo();
	            KhuyenMai km = new KhuyenMai();
	            km.setMaKM(rs.getString("MaKM"));
	            qa.setMaQuanAo(rs.getString("MaQuanAo"));
	            qa.setTenQuanAo(rs.getString("TenQuanAo"));
	            qa.setKm(km);
	            chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));
	            chiTietHoaDon.setGiaBan(rs.getFloat("GiaBan"));
	            chiTietHoaDon.setThanhTien(rs.getFloat("ThanhTien"));
	            chiTietHoaDon.setQuanAo(qa);
	            dsCTHD.add(chiTietHoaDon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsCTHD;
	}


	
	public void create(ChiTietHoaDon cthd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?,?,?)");
			stmt.setString(1, cthd.getMahoaDon().getMaHD());
			stmt.setString(2,cthd.getQuanAo().getMaQuanAo());
			stmt.setString(3,cthd.getQuanAo().getTenQuanAo());
			stmt.setInt(4, cthd.getSoLuong());
			stmt.setDouble(5, cthd.getGiaBan());
			stmt.setBigDecimal(6, BigDecimal.valueOf(cthd.getThanhTien()));
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
}
