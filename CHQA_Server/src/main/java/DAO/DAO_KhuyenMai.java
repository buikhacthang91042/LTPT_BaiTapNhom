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
import entity.KhuyenMai;
import entity.NhaCungCap;
import entity.NhanVien;


public class DAO_KhuyenMai {
	List<KhuyenMai> khuyenMai = new ArrayList<>();
	
	Connection con = ConnectDB.getInstance().getConnection();
	public List<KhuyenMai> getAllKhuyenMai() {
		List<KhuyenMai> khuyenMai = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stmt1= null;
		try {
			String sql= "Select * from KhuyenMai";
			stmt1= con.createStatement();
			ResultSet rs= stmt1.executeQuery(sql);
			while(rs.next()) {
				khuyenMai.add(new KhuyenMai(rs.getString(1), rs.getString(2),rs.getDate(3),rs.getDate(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return khuyenMai;
	}
	public boolean create(KhuyenMai KM) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into KhuyenMai values(?,?,?,?)");
			stmt.setString(1, KM.getMaKM());
			stmt.setString(2, KM.getTenChuongTrinh());
			stmt.setDate(3, new java.sql.Date(KM.getNgayBatDau().getTime()));
			stmt.setDate(4, new java.sql.Date(KM.getNgayKetThuc().getTime()));
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return n>0;
	}
	public boolean delete (String maKM) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from KhuyenMai where maKM = ?");
			stmt.setString(1, maKM);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return n >0;
	}
	public boolean update( KhuyenMai km) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt2 = null;
		int n = 0;

		try {
			stmt2 = con
					.prepareStatement("update KhuyenMai set tenChuongTrinh=?, ngayBatDau=?, ngayKetThuc=? where maKM=?");
						
			stmt2.setString(1, km.getTenChuongTrinh());
			stmt2.setDate(2, new java.sql.Date(km.getNgayBatDau().getTime()));
			stmt2.setDate(3, new java.sql.Date(km.getNgayKetThuc().getTime()));
			stmt2.setString(4, km.getMaKM());
			n = stmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n > 0;

	}
	
	public KhuyenMai getKhuyenMaiByMa(String maKM) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    KhuyenMai khuyenMai = null;

	    try {
	        // Kiểm tra và mở lại kết nối nếu đã đóng
	        if (ConnectDB.getInstance().getConnection().isClosed()) {
	            ConnectDB.getInstance().connect();
	        }

	        connection = ConnectDB.getInstance().getConnection();
	        String query = "SELECT * FROM KhuyenMai WHERE MaKM = ?";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, maKM);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            // Đọc dữ liệu từ ResultSet và tạo đối tượng KhuyenMai
	            String ma = resultSet.getString("MaKM");
	            String tenChuongTrinh = resultSet.getString("TenChuongTrinh");
	            Date ngayBatDau = resultSet.getDate("NgayBatDau");
	            Date ngayKetThuc = resultSet.getDate("NgayKetThuc");

	            khuyenMai = new KhuyenMai(ma, tenChuongTrinh, ngayBatDau, ngayKetThuc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Đóng kết nối và các tài nguyên
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            // Không đóng connection ở đây để tránh đóng khi còn sử dụng ở các phương thức khác
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return khuyenMai;
	}

	public Date getNgayKetThucByMaKhuyenMai(String maKhuyenMai) {
		Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
       
        Date ngayKetThuc = null;

        try {
        	 if (ConnectDB.getInstance().getConnection().isClosed()) {
 	            ConnectDB.getInstance().connect();
 	        }
            String sql = "SELECT ngayKetThuc FROM KhuyenMai WHERE maKM = ?";
            stmt= con.prepareStatement(sql);
            stmt.setString(1,maKhuyenMai);
            
			ResultSet rs= stmt.executeQuery();
            // Xử lý kết quả trả về
            if (rs.next()) {
                // Lấy giá trị ngày kết thúc từ cột "NgayKetThuc" trong kết quả truy vấn
                ngayKetThuc = rs.getDate("ngayKetThuc");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        

        return ngayKetThuc;
    }
	
	
	public List<String> getAllMaKhuyenMai() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> danhSachMaKM = new ArrayList<>();

        try {
            
            connection = ConnectDB.getInstance().getConnection();

            if (ConnectDB.getInstance().getConnection().isClosed()) {
	            ConnectDB.getInstance().connect();
	        }
            
            String sql = "SELECT maKM FROM KhuyenMai";

           
            preparedStatement = connection.prepareStatement(sql);

            
            resultSet = preparedStatement.executeQuery();

            
            while (resultSet.next()) {
                
                String maKhuyenMai = resultSet.getString("maKM");
                danhSachMaKM.add(maKhuyenMai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return danhSachMaKM;
    }
	public boolean updateAllMaKM(int deletedRowCount, String maKMDaXoa) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "SELECT maKM FROM KhuyenMai WHERE maKM > ?";
		List<String> maKMList = new ArrayList<>();

		try {
			PreparedStatement stmt = null;
			stmt= con.prepareStatement(query);
		    stmt.setString(1, maKMDaXoa);
		    ResultSet rs = stmt.executeQuery();

		    while (rs.next()) {
		    	maKMList.add(rs.getString("maKM"));
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    return false;
		}

	    for (String MaKMCu : maKMList) {
	        int maCu = Integer.parseInt(MaKMCu.substring(3));
	        int maMoi = maCu - deletedRowCount;
	        String newMaKM = "KM" + String.format("%03d", maMoi);

	        String updateQuery = "UPDATE KhuyenMai SET maKM = ? WHERE maKM = ?";
	        try (PreparedStatement updateStatement = ConnectDB.getInstance().getConnection().prepareStatement(updateQuery)) {
	            updateStatement.setString(1, newMaKM);
	            updateStatement.setString(2, MaKMCu);
	            updateStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    return true;
	}
}