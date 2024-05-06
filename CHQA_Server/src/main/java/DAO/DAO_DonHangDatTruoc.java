package DAO;

import java.io.Console;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class DAO_DonHangDatTruoc {
	List<DonDatHang>donDatHang = new ArrayList<>();
	public List<DonDatHang> getAllDonDatHang() {
	    List<DonDatHang> donDatHanglist = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement stmt1 = null;
	    ResultSet rs = null;

	    try {
	        con = ConnectDB.getInstance().getConnection();
	        String sql = "select ddh.MaDonHang, ddh.NgayMua, ddh.MaNV, ddh.MaKH,kh.TenKH,nv.TenNV, ddh.TongTien "
	                + "from DonHangDatTruoc ddh "
	                + "join KhachHang kh ON ddh.MaKH = kh.MaKH "
	                + "join NhanVien nv ON ddh.MaNV = nv.MaNV";
	        				

	        stmt1 = con.prepareStatement(sql);
	        rs = stmt1.executeQuery();

	        while (rs.next()) {
	            DonDatHang ddh = new DonDatHang();
	            KhachHang kh = new KhachHang();
	            NhanVien nv = new NhanVien();
	            ddh.setMaDonHang(rs.getString("MaDonHang"));
	            ddh.setNgayMua(rs.getDate("NgayMua"));
	            nv.setMaNV(rs.getString("MaNV"));
	            nv.setTenNV(rs.getString("TenNV"));
	            ddh.setNV(nv);
	            kh.setMaKH(rs.getString("MaKH"));
	            kh.setHoTen(rs.getString("TenKH"));
	            ddh.setKH(kh);
	            ddh.setTongTien(rs.getFloat("TongTien"));
	            donDatHanglist.add(ddh);
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

	    return donDatHanglist;
	}
	
	public void create(DonDatHang ddh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n=0;
		System.out.println(ddh);
		try {
			stmt = con.prepareStatement("insert into DonHangDatTruoc values(?,?,?,?,?)");
			stmt.setString(1, ddh.getMaDonHang());
			stmt.setDate(2,  (Date) ddh.getNgayMua());
			stmt.setString(3, ddh.getNV().getMaNV());
			stmt.setString(4, ddh.getKH().getMaKH());
			stmt.setBigDecimal(5, BigDecimal.valueOf(ddh.getTongTien()));
			n= stmt.executeUpdate();
		} 
			
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public List<DonDatHang> search(String maDonHang) {
		Connection con = ConnectDB.getInstance().getConnection();
		List<DonDatHang> dsDDH = new ArrayList<DonDatHang>();
		PreparedStatement stmt = null;
		String sql = "select ddh.MaDonHang, ddh.MaNV, ddh.MaKH , ddh.NgayMua, ddh.TongTien \r\n"
				+ "from DonHangDatTruoc ddh join KhachHang kh on ddh.MaKH=kh.MaKH \r\n"
				+ "join NhanVien nv on ddh.MaNV=nv.MaNV \r\n"
				+ "where MaDonHang like N'%"+maDonHang+"%'";
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				DonDatHang ddh = new DonDatHang();
				NhanVien nv = new NhanVien();
				KhachHang kh = new KhachHang();
				
				ddh.setMaDonHang(rs.getString("MaDonHang"));
				nv.setMaNV(rs.getString("MaNV"));
				nv.setTenNV(rs.getString("TenNV"));
				kh.setMaKH(rs.getString("MaKH"));
				kh.setHoTen(rs.getString("TenKH"));
				ddh.setNgayMua(rs.getDate("NgayMua"));
				ddh.setTongTien(rs.getFloat("TongTien"));
				ddh.setNV(nv);
				ddh.setKH(kh);
				dsDDH.add(ddh);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDDH;
	}
	public DonDatHang layDonHangTheoMa(String maDH) throws Exception {
		Connection con = ConnectDB.getInstance().getConnection();
		DonDatHang dh = new DonDatHang();
		PreparedStatement stmt = null;
		String sql = "select MaDonHang, dh.MaNV, TenNV, dh.MaKH ,TenKH, NgayMua,TongTien from DonHangDatTruoc dh join KhachHang kh on dh.MaKH=kh.MaKH "
					+ "join NhanVien nv on dh.MaNV=nv.MaNV where  dh.MaDonHang = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maDH);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {

			NhanVien nv = new NhanVien();
			KhachHang kh = new KhachHang();

			dh.setMaDonHang(rs.getString("MaDonHang"));
			nv.setMaNV(rs.getString("MaNV"));
			nv.setTenNV(rs.getString("TenNV"));
			kh.setMaKH(rs.getString("MaKH"));
			kh.setHoTen(rs.getString("TenKH"));
			dh.setNgayMua(rs.getDate("NgayMua"));
			dh.setTongTien(rs.getFloat("TongTien"));
			dh.setNV(nv);
			dh.setKH(kh);}
			else {
				 System.out.println("Không tìm thấy đơn hàng với mã " + maDH);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dh;
	}
	public boolean delete (String MaDH) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmtChiTiet = null;
	    PreparedStatement stmtDonHang = null;
		int n = 0;
		try {
			 stmtChiTiet = con.prepareStatement("DELETE FROM ChiTietPhieuDatHangTruoc WHERE MaDonHang = ?");
		     stmtChiTiet.setString(1, MaDH);
		     stmtChiTiet.executeUpdate();

		     stmtDonHang = con.prepareStatement("DELETE FROM DonHangDatTruoc WHERE MaDonHang = ?");
		     stmtDonHang.setString(1, MaDH);
		     n = stmtDonHang.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return n >0;
	}

	
}
