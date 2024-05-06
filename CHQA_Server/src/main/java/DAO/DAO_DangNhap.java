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
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DAO_DangNhap {
	private EntityManager entityManager;
	public DAO_DangNhap(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	List<TaiKhoan> ds = new ArrayList<>();
	public List<TaiKhoan> getAllTK() {
	    List<TaiKhoan> ds = new ArrayList<>();
	    try {
	        TypedQuery<TaiKhoan> query = entityManager.createQuery("SELECT t FROM TaiKhoan t", TaiKhoan.class);
	        ds = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ds;
	}	
	
	public boolean themTK(String tk, String email, String mk) {    
	    try {
	        entityManager.getTransaction().begin();
	        TaiKhoan taiKhoan = new TaiKhoan(tk, email, mk);
	        entityManager.persist(taiKhoan);
	        entityManager.getTransaction().commit();
	        return true;
	    } catch (Exception e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	public List<TaiKhoan> dangNhap(String tk, String mk) {    
	    List<TaiKhoan> ds = new ArrayList<>();
	    try {
	        TypedQuery<TaiKhoan> query = entityManager.createQuery("SELECT t FROM TaiKhoan t WHERE t.taiKhoan = :tk AND t.matKhau = :mk", TaiKhoan.class);
	        query.setParameter("tk", tk);
	        query.setParameter("mk", mk);
	        ds = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ds;
	}

	public boolean kiemTraTonTai(String taiKhoan) {    
	    try {
	        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(t) FROM TaiKhoan t WHERE t.taiKhoan = :taiKhoan", Long.class);
	        query.setParameter("taiKhoan", taiKhoan);
	        Long count = query.getSingleResult();
	        return count > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean deleteByMaNV(String maNV) {
	    try {
	        entityManager.getTransaction().begin();
	        TypedQuery<TaiKhoan> query = entityManager.createQuery("DELETE FROM TaiKhoan t WHERE t.taiKhoan = :maNV", TaiKhoan.class);
	        query.setParameter("maNV", maNV);
	        int deletedCount = query.executeUpdate();
	        entityManager.getTransaction().commit();
	        return deletedCount > 0;
	    } catch (Exception e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

}
