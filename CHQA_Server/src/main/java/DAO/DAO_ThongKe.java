package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connect.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class DAO_ThongKe {

	// ================================================= Thống kê doanh thu
	// Hàm thống kê tổng tiền theo khoảng thời gian
	public double hienThiDoanhThuTheoNgay(Date trc, Date sau) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT SUM(TongTien) FROM HoaDon WHERE NgayMua BETWEEN ? AND ?";
			stmt = con.prepareStatement(sql);

			// Sử dụng setDate để đặt giá trị cho ngày
			stmt.setDate(1, new java.sql.Date(trc.getTime()));
			stmt.setDate(2, new java.sql.Date(sau.getTime()));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				double revenue = rs.getDouble(1);
				return revenue;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Hàm lấy 'TongTien' và 'NgayMua' của từng 'NgayMua'
	public List<Object[]> layTongTienVaNgayTheoNgay(Date trc, Date sau) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT NgayMua, SUM(TongTien) FROM HoaDon WHERE NgayMua BETWEEN ? AND ? GROUP BY NgayMua";
			stmt = con.prepareStatement(sql);

			// Sử dụng setDate để đặt giá trị cho ngày
			stmt.setDate(1, new java.sql.Date(trc.getTime()));
			stmt.setDate(2, new java.sql.Date(sau.getTime()));

			ResultSet rs = stmt.executeQuery();
			List<Object[]> result = new ArrayList<>();

			while (rs.next()) {
				Date ngayMua = rs.getDate(1);
				double revenue = rs.getDouble(2);
				Object[] rowData = { ngayMua, revenue };
				result.add(rowData);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<HoaDon> layThongTinHoaDonTrongKhoangNgay(Date trc, Date sau) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT hd.MaHD, hd.NgayMua, hd.MaNV, hd.MaKH, kh.TenKH, nv.TenNV, hd.TongTien "
					+ "FROM HoaDon hd "
					+ "JOIN KhachHang kh ON hd.MaKH = kh.MaKH "
					+ "JOIN NhanVien nv ON hd.MaNV = nv.MaNV "
					+ "WHERE hd.NgayMua BETWEEN ? AND ?";
			stmt = con.prepareStatement(sql);

			// Sử dụng setDate để đặt giá trị cho ngày
			stmt.setDate(1, new java.sql.Date(trc.getTime()));
			stmt.setDate(2, new java.sql.Date(sau.getTime()));

			ResultSet rs = stmt.executeQuery();
			List<HoaDon> hoaDonList = new ArrayList<>();

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

				hd.setTongTien((float) rs.getDouble("TongTien"));

				hoaDonList.add(hd);
			}

			return hoaDonList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	// ============================================== Thống kê quần áo bán chạy
	// Hàm lấy thông tin mã quần áo bán chạy nhất
	public List<Object[]> layThongTinMaQABanChayNhat(Date trc, Date sau) {
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement stmt = null;

	    try {
	        String sql = "WITH RankedResults AS ("
	                + "SELECT "
	                + "CTHD.MaQuanAo, "
	                + "QA.TenQuanAo, "
	                + "SUM(CTHD.SoLuong) AS TongSoLuong, "
	                + "NC.TenNCC, "
	                + "DENSE_RANK() OVER (ORDER BY SUM(CTHD.SoLuong) DESC) AS Rnk "
	                + "FROM " + "HoaDon HD " + "JOIN "
	                + "ChiTietHoaDon CTHD ON HD.MaHD = CTHD.MaHD " + "JOIN "
	                + "QuanAo QA ON CTHD.MaQuanAo = QA.MaQuanAo " + "JOIN "
	                + "NhaCungCap NC ON QA.NCC = NC.MaNCC "
	                + "WHERE "
	                + "HD.NgayMua BETWEEN ? AND ? " + "GROUP BY "
	                + "CTHD.MaQuanAo, QA.TenQuanAo, NC.TenNCC" + ") "
	                + "SELECT " + "MaQuanAo, " + "TenQuanAo, "
	                + "TongSoLuong, " + "TenNCC " + "FROM " + "RankedResults "
	                + "WHERE " + "Rnk = 1";

	        stmt = con.prepareStatement(sql);
	        stmt.setDate(1, new java.sql.Date(trc.getTime()));
	        stmt.setDate(2, new java.sql.Date(sau.getTime()));

	        ResultSet rs = stmt.executeQuery();
	        List<Object[]> result = new ArrayList<>();

	        while (rs.next()) {
	            String maQuanAo = rs.getString("MaQuanAo");
	            String tenQuanAo = rs.getString("TenQuanAo");
	            int tongSoLuong = rs.getInt("TongSoLuong");
	            String tenNCC = rs.getString("TenNCC");

	            Object[] rowData = { maQuanAo, tenQuanAo, tongSoLuong, tenNCC };
	            result.add(rowData);
	        }

	        return result;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null)
	                stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}


	// Hàm thống kê số lương sản phẩm theo khoảng thời gian
	public List<Object[]> layTongSoLuongTheoTenQuanAo(Date trc, Date sau) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT CTHD.TenQuanAo, SUM(CTHD.SoLuong) AS TongSoLuong "
					+ "FROM HoaDon HD "
					+ "JOIN ChiTietHoaDon CTHD ON HD.MaHD = CTHD.MaHD "
					+ "WHERE HD.NgayMua BETWEEN ? AND ? "
					+ "GROUP BY CTHD.TenQuanAo";
			stmt = con.prepareStatement(sql);

			stmt.setDate(1, new java.sql.Date(trc.getTime()));
			stmt.setDate(2, new java.sql.Date(sau.getTime()));

			ResultSet rs = stmt.executeQuery();
			List<Object[]> result = new ArrayList<>();

			while (rs.next()) {
				String tenQuanAo = rs.getString("TenQuanAo");
				int tongSoLuong = rs.getInt("TongSoLuong");
				Object[] rowData = { tenQuanAo, tongSoLuong };
				result.add(rowData);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// ========================================================= Thống kê khách
// hàng mua nhiều nhất
	// Hàm lấy thông tin khách hàng có tổng giá trị cao nhất
	public List<Object[]> layThongTinKhachHangCoTongGiaTriCaoNhat(Date trc,
			Date sau) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "WITH RankedResults AS (" + "SELECT " + "HD.MaKH, "
					+ "SUM(HD.TongTien) AS TongGiaTri, "
					+ "RANK() OVER (ORDER BY SUM(HD.TongTien) DESC) AS Rnk "
					+ "FROM " + "HoaDon HD " + "WHERE "
					+ "HD.NgayMua BETWEEN ? AND ? " + "GROUP BY " + "HD.MaKH"
					+ ")" + "SELECT " + "RR.MaKH, " + "KH.TenKH, "
					+ "RR.TongGiaTri, " + "KH.SoDienThoai " + "FROM "
					+ "RankedResults RR " + "JOIN "
					+ "KhachHang KH ON RR.MaKH = KH.MaKH " + "WHERE "
					+ "Rnk = 1 " + "ORDER BY " + "RR.TongGiaTri DESC";

			stmt = con.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(trc.getTime()));
			stmt.setDate(2, new java.sql.Date(sau.getTime()));

			ResultSet rs = stmt.executeQuery();
			List<Object[]> result = new ArrayList<>();

			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString("TenKH");
				String soDienThoai = rs.getString("SoDienThoai");
				int tongGiaTri = rs.getInt("TongGiaTri");

				Object[] rowData = { maKH, tenKH, soDienThoai, tongGiaTri };
				result.add(rowData);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// Hàm lấy thông tin khách hàng có tần xuất mua cao cao nhất
	public List<Object[]> layThongTinKhachHangCoTanSuatMuaCaoNhat(Date trc,
			Date sau) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "WITH FrequencyResults AS ("
					+ "SELECT HD.MaKH, KH.TenKH, KH.SoDienThoai, COUNT(*) AS Frequency "
					+ "FROM HoaDon HD "
					+ "JOIN KhachHang KH ON HD.MaKH = KH.MaKH "
					+ "WHERE HD.NgayMua BETWEEN ? AND ? "
					+ "GROUP BY HD.MaKH, KH.TenKH, KH.SoDienThoai "
					+ ")"
					+ "SELECT MaKH, TenKH, SoDienThoai, Frequency "
					+ "FROM ("
					+ "SELECT *, RANK() OVER (ORDER BY Frequency DESC) AS FrequencyRank "
					+ "FROM FrequencyResults " + ") RankedResults "
					+ "WHERE FrequencyRank = 1";

			stmt = con.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(trc.getTime()));
			stmt.setDate(2, new java.sql.Date(sau.getTime()));

			ResultSet rs = stmt.executeQuery();
			List<Object[]> result = new ArrayList<>();

			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString("TenKH");
				String soDienThoai = rs.getString("SoDienThoai");
				int frequency = rs.getInt("Frequency");
				Object[] rowData = { maKH, tenKH, soDienThoai, frequency };
				result.add(rowData);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// Hàm lấy tần suất và giá trị mua
	public List<Object[]> layThongTinKhachHangTanSuatMuaVaTongGiaTri(Date trc,
			Date sau) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "WITH TongGiaTri AS (" + "SELECT " + "HD.MaKH, "
					+ "SUM(HD.TongTien) AS TongGiaTri " + "FROM "
					+ "HoaDon HD " + "WHERE " + "HD.NgayMua BETWEEN ? AND ? "
					+ "GROUP BY " + "HD.MaKH" + ")," + "TongFrequency AS ("
					+ "SELECT " + "HD.MaKH, " + "COUNT(*) AS Frequency "
					+ "FROM " + "HoaDon HD " + "WHERE "
					+ "HD.NgayMua BETWEEN ? AND ? " + "GROUP BY " + "HD.MaKH"
					+ ")" + "SELECT " + "TG.MaKH, " + "KH.TenKH, "
					+ "KH.SoDienThoai, " + "TF.Frequency AS SoLanDaMua, "
					+ "TG.TongGiaTri " + "FROM " + "TongGiaTri TG " + "JOIN "
					+ "TongFrequency TF ON TG.MaKH = TF.MaKH " + "JOIN "
					+ "KhachHang KH ON TG.MaKH = KH.MaKH";

			stmt = con.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(trc.getTime()));
			stmt.setDate(2, new java.sql.Date(sau.getTime()));
			stmt.setDate(3, new java.sql.Date(trc.getTime()));
			stmt.setDate(4, new java.sql.Date(sau.getTime()));

			ResultSet rs = stmt.executeQuery();
			List<Object[]> result = new ArrayList<>();

			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString("TenKH");
				String soDienThoai = rs.getString("SoDienThoai");
				int soLanDaMua = rs.getInt("SoLanDaMua");
				int tongGiaTri = rs.getInt("TongGiaTri");

				Object[] rowData = { maKH, tenKH, soDienThoai, soLanDaMua,
						tongGiaTri };
				result.add(rowData);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// Hàm lấy tần xuất và ngày mua của khách hàng có tần xuất mua cao nhất
	public List<Object[]> layNgayMuaVaFrequencyCaoNhatTheoMaKH(Date trc,
			Date sau) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "WITH FrequencyResults AS ("
					+ "SELECT HD.MaKH, HD.NgayMua, COUNT(*) AS Frequency "
					+ "FROM HoaDon HD "
					+ "JOIN KhachHang KH ON HD.MaKH = KH.MaKH "
					+ "WHERE HD.NgayMua BETWEEN ? AND ? "
					+ "GROUP BY HD.MaKH, HD.NgayMua"
					+ "),"
					+ "SumFrequencyPerMaKH AS ("
					+ "SELECT MaKH, SUM(Frequency) AS TotalFrequency "
					+ "FROM FrequencyResults "
					+ "GROUP BY MaKH"
					+ "),"
					+ "MaxFrequencyMaKH AS ("
					+ "SELECT MaKH, ROW_NUMBER() OVER (ORDER BY TotalFrequency DESC) AS RowNum "
					+ "FROM SumFrequencyPerMaKH" + ")"
					+ "SELECT FR.NgayMua, FR.Frequency "
					+ "FROM FrequencyResults FR "
					+ "JOIN MaxFrequencyMaKH MM ON FR.MaKH = MM.MaKH "
					+ "WHERE MM.RowNum = 1";

			stmt = con.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(trc.getTime()));
			stmt.setDate(2, new java.sql.Date(sau.getTime()));

			ResultSet rs = stmt.executeQuery();
			List<Object[]> result = new ArrayList<>();

			while (rs.next()) {
				Date ngayMua = rs.getDate("NgayMua");
				int frequency = rs.getInt("Frequency");
				Object[] rowData = { ngayMua, frequency };
				result.add(rowData);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// ==================================================== Thống kê cuối ngày
	// Hàm thống kê tổng tiền ngày hiện tại
	public double hienThiDoanhThuHienTai() {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT SUM(TongTien) FROM HoaDon WHERE CAST(NgayMua AS DATE) = CAST(GETDATE() AS DATE)";
			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				double revenue = rs.getDouble(1);
				return revenue;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Hàm lấy số lượng sản phẩm đã bán ra trong ngày
	public int hienThiSoLuongHienTai() {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT SUM(CHD.SoLuong) AS TongSoLuong " +
		             "FROM ChiTietHoaDon CHD " +
		             "INNER JOIN HoaDon HD ON CHD.MaHD = HD.MaHD " +
		             "WHERE CAST(HD.NgayMua AS DATE) = CAST(GETDATE() AS DATE)";

			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int revenue = rs.getInt(1);
				return revenue;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//Hàm lấy thông tin báo cáo
	public List<Object[]> layThongTinQuanAo() {
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement stmt = null;

	    try {
	        String sql = "SELECT " +
                    "QA.MaQuanAo, " +
                    "QA.TenQuanAo, " +
                    "QA.SoLuongHienTai, " +
                    "CASE " +
                    "WHEN CAST(QA.NgayNhap AS DATE) = CAST(GETDATE() AS DATE) " +
                    "THEN GREATEST(QA.SoLuongHienTai + COALESCE(CHD.TongSoLuong, 0) - QA.SoLuongCu, 0) " +
                    "ELSE 0 " +
                    "END AS SoLuongNhapVao, " +
                    "    CHD.TongSoLuong, " +
                    "    CHD.ThanhTien " +
                    "    FROM " +
                    "QuanAo QA " +
                    "LEFT JOIN ( " +
                    "SELECT " +
                    "CDH.MaQuanAo, " +
                    "SUM(CDH.SoLuong) AS TongSoLuong, " +
                    "SUM(CDH.ThanhTien) AS ThanhTien " +
                    "FROM " +
                    "ChiTietHoaDon CDH " + 
                    "JOIN " +
                    "HoaDon HD ON CDH.MaHD = HD.MaHD " +
                    "WHERE " +
                    " CAST(HD.NgayMua AS DATE) = CAST(GETDATE() AS DATE)" +
                    "GROUP BY " +
                    "CDH.MaQuanAo " +
                    ") CHD ON QA.MaQuanAo = CHD.MaQuanAo;";

	        stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        List<Object[]> result = new ArrayList<>();

	        while (rs.next()) {
	            String maQuanAo = rs.getString("MaQuanAo");
	            String tenQuanAo = rs.getString("TenQuanAo");
	            int soLuongHienTai = rs.getInt("SoLuongHienTai");
	            int soLuongNhapVao = rs.getInt("SoLuongNhapVao");
	            int tongSoLuong = rs.getInt("TongSoLuong");
	            int thanhTien = rs.getInt("ThanhTien");

	            Object[] rowData = { maQuanAo, tenQuanAo, soLuongHienTai, soLuongNhapVao, tongSoLuong, thanhTien };
	            result.add(rowData);
	        }

	        return result;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null)
	                stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}



}