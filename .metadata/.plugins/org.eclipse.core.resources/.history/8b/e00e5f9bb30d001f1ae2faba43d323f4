package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class DAO_HoaDon {
	
	List<HoaDon> hoaDon = new ArrayList<>();
	public List<HoaDon> getAllHoaDon() {
	    List<HoaDon> hoaDonList = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement stmt1 = null;
	    ResultSet rs = null;

	    try {
	        con = ConnectDB.getInstance().getConnection();
	        String sql = "select MaHD, NgayMua, hd.MaNV, hd.MaKH, kh.TenKH,nv.TenNV, hd.TongTien "
	                + "from HoaDon hd "
	                + "join KhachHang kh ON hd.MaKH = kh.MaKH "
	                + "join NhanVien nv ON hd.MaNV = nv.MaNV";

	        stmt1 = con.prepareStatement(sql);
	        rs = stmt1.executeQuery();

	        while (rs.next()) {
	            HoaDon hd = new HoaDon();
	            KhachHang kh = new KhachHang();
	            NhanVien nv = new NhanVien();
	            hd.setMaHD(rs.getString("MaHD"));
	            hd.setNgayMua(rs.getDate("NgayMua"));
	            nv.setMaNV(rs.getString("MaNV"));
	            nv.setTenNV(rs.getString("TenNV"));
	            hd.setNV(nv);
	            kh.setMaKH(rs.getString("MaKH"));
	            kh.setHoTen(rs.getString("TenKH"));
	            hd.setKH(kh);
	            hd.setTongTien(rs.getFloat("TongTien"));
	            hoaDonList.add(hd);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt1 != null) stmt1.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return hoaDonList;
	}

	public void create(HoaDon hd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into HoaDon values(?,?,?,?,?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setDate(2,hd.getNgayMua());
			stmt.setString(3, hd.getNV().getMaNV());
			stmt.setString(4, hd.getKH().getMaKH());
			stmt.setBigDecimal(5, BigDecimal.valueOf(hd.getTongTien()));
			n= stmt.executeUpdate();
		} 
			
		catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
	public List<HoaDon> search(String maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		List<HoaDon> dsHD = new ArrayList<HoaDon>();
		PreparedStatement stmt = null;
		String sql = "select MaHD, hd.MaNV, TenNV, hd.MaKH ,TenKH, NgayMua,TongTien \r\n"
				+ "from HoaDon hd join KhachHang kh on hd.MaKH=kh.MaKH \r\n"
				+ "join NhanVien nv on hd.MaNV=nv.MaNV \r\n"
				+ "where MaHD like N'%"+maHD+"%'";
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon();
				NhanVien nv = new NhanVien();
				KhachHang kh = new KhachHang();
				
				hd.setMaHD(rs.getString("MaHD"));
				nv.setMaNV(rs.getString("MaNV"));
				nv.setTenNV(rs.getString("TenNV"));
				kh.setMaKH(rs.getString("MaKH"));
				kh.setHoTen(rs.getString("TenKH"));
				hd.setNgayMua(rs.getDate("NgayMua"));
				hd.setTongTien(rs.getFloat("TongTien"));
				hd.setNV(nv);
				hd.setKH(kh);
				dsHD.add(hd);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHD;
	}

	public HoaDon layHoaDonTheoMa(String maHD) throws Exception {
		Connection con = ConnectDB.getInstance().getConnection();
		HoaDon hd = new HoaDon();
		PreparedStatement stmt = null;
		String sql = "select MaHD, hd.MaNV, TenNV, hd.MaKH ,TenKH, NgayMua,TongTien from HoaDon hd join KhachHang kh on hd.MaKH=kh.MaKH "
					+ "join NhanVien nv on hd.MaNV=nv.MaNV where  hd.MaHD = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {

			NhanVien nv = new NhanVien();
			KhachHang kh = new KhachHang();

			hd.setMaHD(rs.getString("MaHD"));
			nv.setMaNV(rs.getString("MaNV"));
			nv.setTenNV(rs.getString("TenNV"));
			kh.setMaKH(rs.getString("MaKH"));
			kh.setHoTen(rs.getString("TenKH"));
			hd.setNgayMua(rs.getDate("NgayMua"));
			hd.setTongTien(rs.getFloat("TongTien"));
			hd.setNV(nv);
			hd.setKH(kh);}
			else {
				 System.out.println("Không tìm thấy hóa đơn với mã " + maHD);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hd;
	}

}
