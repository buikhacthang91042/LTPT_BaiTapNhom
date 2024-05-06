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
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import entity.QuanAo;

public class DAO_NhaCungCap {
	List<NhaCungCap> nhaCungCap = new ArrayList<>();
	public List<NhaCungCap> getAllNhaCungCap() {
		List<NhaCungCap> nhaCungCap = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stmt1= null;
		try {
			String sql= "Select * from NhaCungCap";
			stmt1= con.createStatement();
			ResultSet rs= stmt1.executeQuery(sql);
			while(rs.next()) {
				nhaCungCap.add(new NhaCungCap(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhaCungCap;
	}
	
	public boolean create(NhaCungCap nhaCungCap) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into NhaCungCap values(?,?,?,?)");
			stmt.setString(1, nhaCungCap.getMaNCC());
			stmt.setString(2, nhaCungCap.getTenNCC());
			stmt.setString(3, nhaCungCap.getDiaChi());
			stmt.setString(4, nhaCungCap.getsDT());
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return n>0;
	}
	
	public boolean delete (String MaNCC) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from NhaCungCap where MaNCC = ?");
			stmt.setString(1, MaNCC);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return n >0;
	}
	
	public boolean update( NhaCungCap nCC) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt2= null;
		int n = 0;
		
			try {
				stmt2 = con.prepareStatement("update NhaCungCap set MaNCC=?, TenNCC=?, DiaChi=?, SoDienThoai=? where MaNCC=?");
				stmt2.setString(1, nCC.getMaNCC());
				stmt2.setString(2, nCC.getTenNCC());
				stmt2.setString(3, nCC.getDiaChi());
				stmt2.setString(4, nCC.getsDT());
				stmt2.setString(5, nCC.getMaNCC());
				n = stmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	return n>0;
	}

	public ArrayList<NhaCungCap> timTheoMa(String maNCC) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where MaNCC = ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,maNCC);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String ten = rs.getString(2);
				String diaChi= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(ma, ten,diaChi, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	
	public ArrayList<NhaCungCap> timTheoSoDienThoai(String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where SoDienThoai like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,"%"+sdt+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String ten = rs.getString(2);
				String diaChi= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(ma, ten,diaChi, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	
	public ArrayList<NhaCungCap> timTheoTen(String ten) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where TenNCC like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,"%"+ten+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChi= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(ma, tenNCC,diaChi, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	

	
	public ArrayList<NhaCungCap> timTheoDiaChi(String diaChi) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from NhaCungCap where DiaChi like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,"%"+diaChi+ "%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(ma, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return (ArrayList<NhaCungCap>) nhaCungCap;
		}
	
	public ArrayList<NhaCungCap> timTheoTenvaDiaChi(String ten, String diaChi) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where TenNCC like ? and DiaChi like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten);
			stmt.setNString(2,"%"+diaChi+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(ma, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	
	public ArrayList<NhaCungCap> timTheoTenvaMa(String ten, String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where TenNCC like ? and MaNCC like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten);
			stmt.setNString(2,ma);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maNCC= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(maNCC, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	
	public ArrayList<NhaCungCap> timTheoTenvaSDT(String ten, String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where TenNCC like ? and SoDienThoai like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+ten);
			stmt.setNString(2,sdt);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(ma, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	
	public ArrayList<NhaCungCap> timTheoDiaChivaMa(String diaChi, String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where DiaChi like ? and MaNCC like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+diaChi+"%");
			stmt.setNString(2,ma);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maNCC= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(maNCC, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	public ArrayList<NhaCungCap> timTheoSDTvaMa(String sdt, String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where SoDienThoai like ? and MaNCC like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+sdt);
			stmt.setNString(2,ma);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maNCC= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(maNCC, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	
	public ArrayList<NhaCungCap> timTheoSDTvaDiaChi(String sdt, String diaChi) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where SoDienThoai like ? and DiaChi like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,"%"+sdt);
			stmt.setNString(2,"%"+diaChi+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(ma, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	
	public ArrayList<NhaCungCap> timTheoTenvaDiaChivaMa(String ten,String diaChi,String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where TenNCC like ? and DiaChi like ? and MaNCC like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,ten);
			stmt.setNString(2,"%"+diaChi+"%");
			stmt.setNString(3,ma);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maNCC= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(maNCC, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	
	public ArrayList<NhaCungCap> timTheoTenvaSDTvaMa(String ten,String sdt,String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where TenNCC like ? and SoDienThoai like ? and MaNCC like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,ten);
			stmt.setNString(2,sdt);
			stmt.setNString(3,ma);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maNCC= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(maNCC, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	
	public ArrayList<NhaCungCap> timTheoSDTvaMavaDiaChi(String sdt,String ma,String diaChi) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where SoDienThoai like ? and MaNCC like ? and DiaChi like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,sdt);
			stmt.setNString(2,ma);
			stmt.setNString(3,"%"+diaChi+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maNCC= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(maNCC, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	public ArrayList<NhaCungCap> timTheoTenvaDiaChivaMavaSDT(String ten,String diaChi,String ma,String sdt) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where TenNCC like ? and DiaChi like ? and MaNCC like ? and SoDienThoai like ?";
			stmt= con.prepareStatement(sql);
			stmt.setNString(1,ten);
			stmt.setNString(2,"%"+diaChi+"%");
			stmt.setNString(3,ma);
			stmt.setNString(4, sdt);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maNCC= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChiNCC= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap.add(new NhaCungCap(maNCC, tenNCC,diaChiNCC, soDienThoai));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return (ArrayList<NhaCungCap>) nhaCungCap;
	}
	public boolean updateAllMaNCC(int deletedRowCount, String maNCCDaXoa) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "SELECT MaNCC FROM NhaCungCap WHERE MaNCC >= ?";
		List<String> maNCCList = new ArrayList<>();

		try {
			PreparedStatement stmt = null;
			stmt= con.prepareStatement(query);
		    stmt.setString(1, maNCCDaXoa);
		    ResultSet rs = stmt.executeQuery();

		    while (rs.next()) {
		        maNCCList.add(rs.getString("MaNCC"));
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    return false;
		}

	    for (String MaNCCCu : maNCCList) {
	        int maCu = Integer.parseInt(MaNCCCu.substring(3));
	        int maMoi = maCu - deletedRowCount;
	        String newMaNCC = "NCC" + String.format("%03d", maMoi);

	        String updateQuery = "UPDATE NhaCungCap SET MaNCC = ? WHERE MaNCC = ?";
	        try (PreparedStatement updateStatement = ConnectDB.getInstance().getConnection().prepareStatement(updateQuery)) {
	            updateStatement.setString(1, newMaNCC);
	            updateStatement.setString(2, MaNCCCu);
	            updateStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    return true;
	}
	public NhaCungCap getNCCByTen(String ten) {
		Connection con = ConnectDB.getInstance().getConnection();
		NhaCungCap nhaCungCap = null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where TenNCC like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,ten);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ma= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChi= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap= new NhaCungCap(ma, tenNCC,diaChi, soDienThoai);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return nhaCungCap;
	}
	public NhaCungCap getNCCByMa(String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		NhaCungCap nhaCungCap = null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "select * from NhaCungCap where MaNCC like ?";
			stmt= con.prepareStatement(sql);
			stmt.setString(1,ma);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maNCC= rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChi= rs.getString(3);
				String soDienThoai = rs.getString(4);
				nhaCungCap= new NhaCungCap(maNCC, tenNCC,diaChi, soDienThoai);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	return nhaCungCap;
	}
	}
