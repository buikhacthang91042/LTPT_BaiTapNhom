package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.NhanVien;

public class DAO_NhanVien {
	List<NhanVien> nv = new ArrayList<NhanVien>();
	public List<NhanVien> getAllNV() {
	List<NhanVien> nv = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stmt = null;
		try {
			String sql = "select * from NhanVien";
			stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nv;
	}

	public boolean create(NhanVien nv) {
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        stmt = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?)");
	        stmt.setString(1, nv.getMaNV());
	        stmt.setString(2, nv.getTenNV());
	        stmt.setString(3, nv.getNamSinh());
	        stmt.setString(4, nv.getGioiTinh());
	        stmt.setDate(5, new java.sql.Date(nv.getNgayVaolam().getTime()));
	        stmt.setString(6, nv.getsDT());
	        n = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return n > 0;
	}

	
	public boolean delete (String malop) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from NhanVien where MaNV = ?");
			stmt.setString(1, malop);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return n >0;
	}
	
	public boolean update( NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt2 = null;
		int n = 0;

		try {
			stmt2 = con
					.prepareStatement("update NhanVien set TenNV=?, NamSinh=?, GioiTinh=?, NgayVaoLam=?, Sdt= ? where MaNV=?");
			stmt2.setString(6, nv.getMaNV());
			stmt2.setString(1, nv.getTenNV());
			stmt2.setString(2, nv.getNamSinh());
			stmt2.setString(3, nv.getGioiTinh());
			stmt2.setDate(4, new java.sql.Date(nv.getNgayVaolam().getTime()));
			stmt2.setString(5, nv.getsDT());
			n = stmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n > 0;

	}
	public ArrayList<NhanVien> timTheoTen(String ten) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where TenNV like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,"%"+ten+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoSdt(String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where Sdt like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,"%"+sdt+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoMa(String maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where MaNV like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,maNV);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					String ma = rs.getString(1);
					String hoTen = rs.getString(2);
					String namSinh = rs.getString(3);
					String gioiTinh = rs.getString(4);
					Date ngayVaoLam = rs.getDate(5);
					String sDT = rs.getString(6);
					nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoGT(String gt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where GioiTinh like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,gt);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					String ma = rs.getString(1);
					String hoTen = rs.getString(2);
					String namSinh = rs.getString(3);
					String gioiTinh = rs.getString(4);
					Date ngayVaoLam = rs.getDate(5);
					String sDT = rs.getString(6);
					nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoNamSinh(String ns) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where NamSinh like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,"%"+ns+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoTenvaGT(String ten, String gt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where TenNV like ? and GioiTinh like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten+"%");
			stmt.setNString(2,gt);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoTenvaNamSinh(String ten, String ns) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where TenNV like ? and NamSinh like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten+"%");
			stmt.setNString(2,ns);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoTenvaSdt(String ten, String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where TenNV like ? and Sdt like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten+"%");
			stmt.setNString(2,"%"+sdt+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoNamSinhvaGT(String ns, String gt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where NamSinh like ? and GioiTinh like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,ns);
			stmt.setNString(2,gt);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoNamSinhvaSdt(String ns, String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where NamSinh like ? and Sdt like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,ns);
			stmt.setNString(2,"%"+sdt+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoTenvaNamSinhvaGT(String ten, String ns, String gt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where TenNV like ? and NamSinh like ? and GioiTinh like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten+"%");
			stmt.setNString(2,ns);
			stmt.setNString(3,gt);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoTenvaGTvaSdt(String ten, String gt, String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where TenNV like ? and GioiTinh like ? and Sdt like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten+"%");
			stmt.setNString(2,gt);
			stmt.setNString(3,"%"+sdt+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoTenvaNamSinhvaSdt(String ten, String ns, String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where TenNV like ? and NamSinh like ? and Sdt like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten+"%");
			stmt.setNString(2,ns);
			stmt.setNString(3,"%"+sdt+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoNamSinhvaGTvaSdt(String ns, String gt, String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where NamSinh like ? and GioiTinh like ? and Sdt like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,ns);
			stmt.setNString(2,gt);
			stmt.setNString(3,"%"+sdt+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoTenvaNamSinhvaGTvaSdt(String ten, String ns, String gt, String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where TenNV like ? and NamSinh like ? and GioiTinh like ? and Sdt like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten+"%");
			stmt.setNString(2,ns);
			stmt.setNString(3,gt);
			stmt.setNString(4,"%"+sdt+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	
	public ArrayList<NhanVien> timTheoAll(String maNV, String ten,String ns, String gt, String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhanVien where MaNV like ? and TenNV like ? and NamSinh like ? and GioiTinh like ? and Sdt like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,maNV);
			stmt.setNString(2,"%"+ten+"%");
			stmt.setNString(3,ns);
			stmt.setNString(4,gt);
			stmt.setNString(5,"%"+sdt+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String hoTen = rs.getString(2);
				String namSinh = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngayVaoLam = rs.getDate(5);
				String sDT = rs.getString(6);
				nv.add(new NhanVien(ma, hoTen, namSinh, gioiTinh, ngayVaoLam, sDT));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhanVien>) nv;
	}
	public NhanVien getNhanVien(String maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		NhanVien nv = new NhanVien();
		PreparedStatement stmt = null;
		String sql = "select * from nhanvien where manv='"+maNV+"'" ;
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				nv.setMaNV(rs.getString("MaNV"));
				nv.setTenNV(rs.getString("TenNV"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNamSinh(rs.getString("NamSinh"));
				nv.setNgayVaolam(rs.getDate("NgayVaoLam"));
				nv.setsDT(rs.getString("Sdt"));
				
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	
}

	

