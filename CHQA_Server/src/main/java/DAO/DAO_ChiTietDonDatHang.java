package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.ChiTietDonDatHang;
import entity.QuanAo;
import entity.DonDatHang;
import entity.KhuyenMai;

public class DAO_ChiTietDonDatHang {

	public List<ChiTietDonDatHang> getAllChiTietDonDatHang(String maDatTruoc) {
		Connection con = ConnectDB.getInstance().getConnection();
		List<ChiTietDonDatHang> dsCTDDH = new ArrayList<ChiTietDonDatHang>();
		String sql = "SELECT qa.MaQuanAo, qa.TenQuanAo,qa.MaKM ,SoLuong, GiaBan, ThanhTien "
	            + "FROM ChiTietPhieuDatHangTruoc ctdh "
				+ "JOIN QuanAo qa ON ctdh.MaQuanAo = qa.MaQuanAo "
	            + "JOIN DonHangDatTruoc ddh ON ctdh.MaDonHang = ddh.MaDonHang "
	            + "WHERE ddh.MaDonHang = '"+maDatTruoc+"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietDonDatHang chiTietDonDatHang = new ChiTietDonDatHang();
				QuanAo qa = new QuanAo();
				KhuyenMai km = new KhuyenMai();
				km.setMaKM(rs.getString("MaKM"));
				qa.setMaQuanAo(rs.getString("MaQuanAo"));
				qa.setTenQuanAo(rs.getString("TenQuanAo"));
				qa.setKm(km);
				chiTietDonDatHang.setSoLuong(rs.getInt("SoLuong"));			
				chiTietDonDatHang.setGiaBan(rs.getFloat("GiaBan"));
				chiTietDonDatHang.setThanhTien(rs.getFloat("ThanhTien"));
				chiTietDonDatHang.setQuanAo(qa);
				dsCTDDH.add(chiTietDonDatHang);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsCTDDH;
	}
	
	public void create(ChiTietDonDatHang ctddh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n=0;
		System.out.println(ctddh);
		try {
			stmt = con.prepareStatement("insert into ChiTietPhieuDatHangTruoc values(?,?,?,?,?,?)");
			stmt.setString(1, ctddh.getMaDatHang().getMaDonHang());
			stmt.setString(2,ctddh.getQuanAo().getMaQuanAo());
			stmt.setString(3,ctddh.getQuanAo().getTenQuanAo());
			stmt.setInt(4, ctddh.getSoLuong());
			stmt.setDouble(5, ctddh.getGiaBan());
			stmt.setBigDecimal(6, BigDecimal.valueOf(ctddh.getThanhTien()));
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
