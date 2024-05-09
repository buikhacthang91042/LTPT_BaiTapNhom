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
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class DAO_NhanVien {
	private EntityManager entityManager;
	public DAO_NhanVien (EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	List<NhanVien> nv = new ArrayList<NhanVien>();
	
	public List<NhanVien> getAllNV() {
		Query query = entityManager.createQuery("SELECT nv FROM NhanVien nv");
		return query.getResultList();
	}

	public boolean create(NhanVien nv) {
	    EntityTransaction transaction = null;
	    try  {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(nv);
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

	
	public boolean delete (String manv) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			NhanVien kh = entityManager.find(NhanVien.class, manv);
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
	
	public boolean update( NhanVien nv) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(nv);
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
	public ArrayList<NhanVien> timTheoTen(String ten) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
	            "SELECT nv FROM NhanVien nv WHERE nv.tenNV = :ten", NhanVien.class);
	        query.setParameter("ten", ten);
	        List<NhanVien> resultList = query.getResultList();
	        return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
	    
	}
	
	public ArrayList<NhanVien> timTheoSdt(String sdt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
	            "SELECT nv FROM NhanVien nv WHERE nv.sDT = :sdt", NhanVien.class);
	        query.setParameter("sdt", sdt);
	        List<NhanVien> resultList = query.getResultList();
	        return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoMa(String maNV) {
			TypedQuery<NhanVien> query = entityManager.createQuery(
	            "SELECT nv FROM NhanVien nv WHERE nv.sDT = :sdt", NhanVien.class);
	        query.setParameter("maNV", maNV );
	        List<NhanVien> resultList = query.getResultList();
	        return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoGT(String gt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.gioiTinh = :gioiTinh", NhanVien.class);

		    // Đặt tham số cho truy vấn, ở đây tham số là giới tính nhân viên
		    query.setParameter("gioiTinh", gt);

		    // Thực thi truy vấn và nhận danh sách kết quả
		    List<NhanVien> resultList = query.getResultList();

		    // Chuyển danh sách List thành ArrayList và trả về
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoNamSinh(String ns) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.namSinh = :namSinh", NhanVien.class);

		    // Đặt tham số cho truy vấn, ở đây tham số là năm sinh
		    query.setParameter("namSinh", ns);

		    // Thực thi truy vấn và nhận danh sách kết quả
		    List<NhanVien> resultList = query.getResultList();

		    // Chuyển danh sách List thành ArrayList và trả về
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoTenvaGT(String ten, String gt) {
			TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.tenNV = :ten AND nv.gioiTinh = :gioiTinh", NhanVien.class);

		    // Đặt các tham số cho truy vấn
		    query.setParameter("ten", ten);
		    query.setParameter("gioiTinh", gt);

		    // Thực thi truy vấn và nhận danh sách kết quả
		    List<NhanVien> resultList = query.getResultList();

		    // Chuyển danh sách List thành ArrayList và trả về
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoTenvaNamSinh(String ten, String ns) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.tenNV = :ten AND nv.namSinh = :namSinh", NhanVien.class);

		    // Đặt các tham số cho truy vấn
		    query.setParameter("ten", ten);
		    query.setParameter("namSinh", ns);

		    // Thực thi truy vấn và nhận danh sách kết quả
		    List<NhanVien> resultList = query.getResultList();

		    // Chuyển danh sách List thành ArrayList và trả về
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoTenvaSdt(String ten, String sdt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.tenNV = :ten AND nv.sDT = :sdt", NhanVien.class);

		    // Đặt các tham số cho truy vấn
		    query.setParameter("ten", ten);
		    query.setParameter("sdt", sdt);

		    // Thực thi truy vấn và nhận danh sách kết quả
		    List<NhanVien> resultList = query.getResultList();

		    // Chuyển danh sách List thành ArrayList và trả về
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoNamSinhvaGT(String ns, String gt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.namSinh = :namSinh AND nv.gioiTinh = :gioiTinh", NhanVien.class);

		    // Đặt các tham số cho truy vấn
		    query.setParameter("namSinh", ns);
		    query.setParameter("gioiTinh", gt);

		    // Thực thi truy vấn và nhận danh sách kết quả
		    List<NhanVien> resultList = query.getResultList();

		    // Chuyển danh sách List thành ArrayList và trả về
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoNamSinhvaSdt(String ns, String sdt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.namSinh = :namSinh AND nv.sDT = :sdt", NhanVien.class);

		    // Set parameters for the query
		    query.setParameter("namSinh", ns);
		    query.setParameter("sdt", sdt);

		    // Execute the query and receive the list of results
		    List<NhanVien> resultList = query.getResultList();

		    // Convert the List to ArrayList and return
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoTenvaNamSinhvaGT(String ten, String ns, String gt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.tenNV = :ten AND nv.namSinh = :namSinh AND nv.gioiTinh = :gioiTinh", NhanVien.class);

		    // Set parameters for the query
		    query.setParameter("ten", ten);
		    query.setParameter("namSinh", ns);
		    query.setParameter("gioiTinh", gt);

		    // Execute the query and get the list of results
		    List<NhanVien> resultList = query.getResultList();

		    // Convert the List to ArrayList and return
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoTenvaGTvaSdt(String ten, String gt, String sdt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.tenNV = :ten AND nv.gioiTinh = :gioiTinh AND nv.sDT = :sdt", NhanVien.class);

		    // Set parameters for the query
		    query.setParameter("ten", ten);
		    query.setParameter("gioiTinh", gt);
		    query.setParameter("sdt", sdt);

		    // Execute the query and retrieve the list of results
		    List<NhanVien> resultList = query.getResultList();

		    // Convert the List to ArrayList and return
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoTenvaNamSinhvaSdt(String ten, String ns, String sdt) {
		 TypedQuery<NhanVien> query = entityManager.createQuery(
			        "SELECT nv FROM NhanVien nv WHERE nv.tenNV = :ten AND nv.namSinh = :namSinh AND nv.sDT = :sdt", NhanVien.class);
			    query.setParameter("ten", ten);
			    query.setParameter("namSinh", ns);
			    query.setParameter("sdt", sdt);
			    List<NhanVien> resultList = query.getResultList();
		 return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoNamSinhvaGTvaSdt(String ns, String gt, String sdt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.namSinh = :namSinh AND nv.gioiTinh = :gioiTinh AND nv.sDT = :sdt", NhanVien.class);
		    query.setParameter("namSinh", ns);
		    query.setParameter("gioiTinh", gt);
		    query.setParameter("sdt", sdt);
		    List<NhanVien> resultList = query.getResultList();
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoTenvaNamSinhvaGTvaSdt(String ten, String ns, String gt, String sdt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.tenNV = :ten AND nv.namSinh = :namSinh AND nv.gioiTinh = :gioiTinh AND nv.sDT = :sdt", NhanVien.class);
		    query.setParameter("ten", ten);
		    query.setParameter("namSinh", ns);
		    query.setParameter("gioiTinh", gt);
		    query.setParameter("sdt", sdt);
		    List<NhanVien> resultList = query.getResultList();
		    return new ArrayList<>(resultList);
	}
	
	public ArrayList<NhanVien> timTheoAll(String maNV, String ten,String ns, String gt, String sdt) {
		TypedQuery<NhanVien> query = entityManager.createQuery(
		        "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNV AND nv.tenNV = :ten AND nv.namSinh = :namSinh AND nv.gioiTinh = :gioiTinh AND nv.sDT = :sdt", NhanVien.class);
		    query.setParameter("maNV", maNV);
		    query.setParameter("ten", ten);
		    query.setParameter("namSinh", ns);
		    query.setParameter("gioiTinh", gt);
		    query.setParameter("sdt", sdt);
		    List<NhanVien> resultList = query.getResultList();
		    return new ArrayList<>(resultList);
	}
	public NhanVien getNhanVien(String maNV) {
		return entityManager.find(NhanVien.class, maNV);
	}
	
}

	

