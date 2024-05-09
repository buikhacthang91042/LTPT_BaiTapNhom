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
import entity.QuanAo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class DAO_KhachHang {
	private EntityManager entityManager;
	public DAO_KhachHang (EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	List<KhachHang> dsKhachHang = new ArrayList<>();
	
	public List<KhachHang> getAllKhachHang() {
		Query query = entityManager.createQuery("SELECT kh FROM KhachHang kh");
		return query.getResultList();
	}
	public boolean themKH(KhachHang kh) {	
		EntityTransaction transaction = null;
		try  {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(kh);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
		}
	}
	
	public boolean delete (String MaKH) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			KhachHang kh = entityManager.find(KhachHang.class, MaKH);
			if (kh != null) {
				entityManager.remove(kh);
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
        return false;
    }
	}
	
	public boolean update( KhachHang kh) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(kh);
			transaction.commit();
			return true;
		} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
	}
	
//	public ArrayList<KhachHang> timTheoSoDienThoai(String sdt) {
//		TypedQuery<KhachHang> query = entityManager.createQuery("SELECT kh FROM KhachHang kh WHERE kh.SoDienThoai= :sdt");
//		query.setParameter("sdt", sdt);
//		return ArrayList<>(query.getResultList());
//	}
    public ArrayList<KhachHang> timTheoSoDienThoai(String sdt) {
        // Sử dụng TypedQuery để tránh cảnh báo unchecked cast
        TypedQuery<KhachHang> query = entityManager.createQuery(
            "SELECT kh FROM KhachHang kh WHERE kh.sDT = :sDT", KhachHang.class);
        query.setParameter("sDT", sdt);
        List<KhachHang> resultList = query.getResultList();
        return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
    }
	
	public ArrayList<KhachHang> timTheoTen(String ten) {
		TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.hoTen = :ten", KhachHang.class);
	        query.setParameter("ten", ten);
	        List<KhachHang> resultList = query.getResultList();
	        return new ArrayList<>(resultList);
	}
	
	public ArrayList<KhachHang> timTheoMa(String ma) {
		TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.maKH = :ma", KhachHang.class);
	        query.setParameter("ma", ma);
	        List<KhachHang> resultList = query.getResultList();
	        return new ArrayList<>(resultList);
	}
	
	public ArrayList<KhachHang> timTheoGioiTinh(String gioitinh) {
		TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.gioiTinh = :gt", KhachHang.class);
	        query.setParameter("gt", gioitinh);
	        List<KhachHang> resultList = query.getResultList();
	        return new ArrayList<>(resultList);
		}
	
	 public ArrayList<KhachHang> timTheoTenvaGioiTinh(String ten, String gioitinh) {
	        // Tạo truy vấn JPA để tìm khách hàng dựa trên tên và giới tính
	        TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.hoTen = :ten AND kh.gioiTinh = :gioiTinh", KhachHang.class);
	        query.setParameter("ten", ten);
	        query.setParameter("gioiTinh", gioitinh);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	    }
	
	public ArrayList<KhachHang> timTheoTenvaMa(String ten, String ma) {
		TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.hoTen = :ten AND kh.maKH = :ma", KhachHang.class);
	        query.setParameter("ten", ten);
	        query.setParameter("ma", ma);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	}
	
	public ArrayList<KhachHang> timTheoTenvaSDT(String ten, String sdt) {
		TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.hoTen = :ten AND kh.sDT = :sDT", KhachHang.class);
	        query.setParameter("ten", ten);
	        query.setParameter("sDT", sdt);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	}
	
	public ArrayList<KhachHang> timTheogioivaMa(String gioitinh, String ma) {
		TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.gioiTinh = :gioitinh AND kh.maKH = :ma ", KhachHang.class);
	       
	        query.setParameter("gioitinh", gioitinh);
	        query.setParameter("ma", ma);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	}
	public ArrayList<KhachHang> timTheoSDTvaMa(String sdt, String ma) {
		TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.sDT = :sdt AND kh.maKH = :ma ", KhachHang.class);
	       
	        query.setParameter("sdt", sdt);
	        query.setParameter("ma", ma);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	}
	
	public ArrayList<KhachHang> timTheoSDTvaGioiTinh(String sdt, String gioitinh) {
		TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.sDT = :sdt AND kh.gioiTinh = :gt ", KhachHang.class);
	       
	        query.setParameter("sdt", sdt);
	        query.setParameter("gt", gioitinh);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	}
	
	 public ArrayList<KhachHang> timTheoTenvaGioiTinhvaMa(String ten, String gioitinh, String ma) {
	        // Tạo truy vấn JPA để tìm khách hàng dựa trên tên, giới tính và mã
	        TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.hoTen = :ten AND kh.gioiTinh = :gioitinh AND kh.maKH = :ma", KhachHang.class);
	        query.setParameter("ten", ten);
	        query.setParameter("gioitinh", gioitinh);
	        query.setParameter("ma", ma);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	    }
	
	 public ArrayList<KhachHang> timTheoTenvaSDTvaMa(String ten, String sdt, String ma) {
	        // Tạo truy vấn JPA để tìm khách hàng dựa trên tên, số điện thoại và mã
	        TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.hoTen = :ten AND kh.sDT = :sdt AND kh.maKH = :ma", KhachHang.class);
	        query.setParameter("ten", ten);
	        query.setParameter("sdt", sdt);
	        query.setParameter("ma", ma);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	    }
	
	 public ArrayList<KhachHang> timTheoSDTvaMavaGioiTinh(String sdt, String ma, String gioitinh) {
	        // Tạo truy vấn JPA để tìm khách hàng dựa trên số điện thoại, mã và giới tính
	        TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.sDT = :sdt AND kh.maKH = :ma AND kh.gioiTinh = :gioitinh", KhachHang.class);
	        query.setParameter("sdt", sdt);
	        query.setParameter("ma", ma);
	        query.setParameter("gioitinh", gioitinh);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	    }
	 public ArrayList<KhachHang> timTheoTenvaGioiTinhvaMavaSDT(String ten, String gioitinh, String ma, String sdt) {
	        // Tạo truy vấn JPA để tìm khách hàng dựa trên tên, giới tính, mã và số điện thoại
	        TypedQuery<KhachHang> query = entityManager.createQuery(
	            "SELECT kh FROM KhachHang kh WHERE kh.hoTen = :ten AND kh.gioiTinh = :gioitinh AND kh.maKH = :ma AND kh.sDT = :sdt", KhachHang.class);
	        query.setParameter("ten", ten);
	        query.setParameter("gioitinh", gioitinh);
	        query.setParameter("ma", ma);
	        query.setParameter("sdt", sdt);

	        // Lấy danh sách kết quả
	        List<KhachHang> resultList = query.getResultList();

	        // Chuyển đổi List thành ArrayList và trả về
	        return new ArrayList<>(resultList);
	    }
	public KhachHang getKhachHang(String maKH) {
		return entityManager.find(KhachHang.class, maKH);
	}

}

	


