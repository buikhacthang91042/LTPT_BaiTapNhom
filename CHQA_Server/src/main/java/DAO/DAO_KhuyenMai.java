package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Query;
import connect.ConnectDB;
import entity.KhuyenMai;
import entity.NhaCungCap;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class DAO_KhuyenMai {
	private EntityManager entityManager;
	public DAO_KhuyenMai(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	List<KhuyenMai> khuyenMai = new ArrayList<>();
	 public List<KhuyenMai> getAllKhuyenMai() {
	        Query query = entityManager.createQuery("SELECT km FROM KhuyenMai km");
	        return query.getResultList();
	    }
	 public boolean create(KhuyenMai KM) {
	        EntityTransaction transaction = null;
	        try {
	            transaction = entityManager.getTransaction();
	            transaction.begin();
	            entityManager.persist(KM);
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

	 public boolean delete(String maKM) {
	        EntityTransaction transaction = null;
	        try {
	            transaction = entityManager.getTransaction();
	            transaction.begin();
	            KhuyenMai km = entityManager.find(KhuyenMai.class, maKM);
	            if (km != null) {
	                entityManager.remove(km);
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
	 public boolean update(KhuyenMai km) {
	        EntityTransaction transaction = null;
	        try {
	            transaction = entityManager.getTransaction();
	            transaction.begin();
	            entityManager.merge(km);
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
	
	 public KhuyenMai getKhuyenMaiByMa(String maKM) {
	        return entityManager.find(KhuyenMai.class, maKM);
	    }

	 public Date getNgayKetThucByMaKhuyenMai(String maKhuyenMai) {
	        KhuyenMai khuyenMai = entityManager.find(KhuyenMai.class, maKhuyenMai);
	        if (khuyenMai != null) {
	            return (Date) khuyenMai.getNgayKetThuc();
	        }
	        return null;
	    }

	
	 public List<String> getAllMaKhuyenMai() {
	        Query query = entityManager.createQuery("SELECT km.maKM FROM KhuyenMai km");
	        return query.getResultList();
	    }
	 public boolean updateAllMaKM(int deletedRowCount, String maKMDaXoa) {
	        EntityTransaction transaction = null;
	        try {
	            transaction = entityManager.getTransaction();
	            transaction.begin();
	            Query query = entityManager.createQuery("SELECT km.maKM FROM KhuyenMai km WHERE km.maKM > :maKM");
	            query.setParameter("maKM", maKMDaXoa);
	            List<String> maKMList = query.getResultList();

	            for (String MaKMCu : maKMList) {
	                int maCu = Integer.parseInt(MaKMCu.substring(3));
	                int maMoi = maCu - deletedRowCount;
	                String newMaKM = "KM" + String.format("%03d", maMoi);

	                Query updateQuery = entityManager.createQuery("UPDATE KhuyenMai km SET km.maKM = :newMaKM WHERE km.maKM = :oldMaKM");
	                updateQuery.setParameter("newMaKM", newMaKM);
	                updateQuery.setParameter("oldMaKM", MaKMCu);
	                updateQuery.executeUpdate();
	            }

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
}
