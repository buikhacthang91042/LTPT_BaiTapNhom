package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import connect.ConnectDB;
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class DAO_LoaiQuanAo {
	private EntityManager entityManager;
	public DAO_LoaiQuanAo(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	List<LoaiQuanAo> loaiQuanAo = new ArrayList<>();
	 public List<LoaiQuanAo> getAllLoaiQuanAo() {
	        Query query = entityManager.createQuery("SELECT l FROM LoaiQuanAo l");
	        return query.getResultList();
	    }

	
	 public boolean create(LoaiQuanAo loaiQuanAo) {
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.persist(loaiQuanAo);
	            entityManager.getTransaction().commit();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	 public boolean delete(String maLoaiQuanAo) {
	        LoaiQuanAo loaiQuanAo = entityManager.find(LoaiQuanAo.class, maLoaiQuanAo);
	        if (loaiQuanAo != null) {
	            try {
	                entityManager.getTransaction().begin();
	                entityManager.remove(loaiQuanAo);
	                entityManager.getTransaction().commit();
	                return true;
	            } catch (Exception e) {
	                e.printStackTrace();
	                return false;
	            }
	        }
	        return false;
	    }
	
	 public boolean suaLoaiQuanAo(LoaiQuanAo loaiMoi) {
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.merge(loaiMoi);
	            entityManager.getTransaction().commit();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	 public List<LoaiQuanAo> timTheoTen(String tenLoai) {
	        String jpql = "SELECT l FROM LoaiQuanAo l WHERE l.tenLoai LIKE :tenLoai";
	        TypedQuery<LoaiQuanAo> query = entityManager.createQuery(jpql, LoaiQuanAo.class);
	        query.setParameter("tenLoai", "%" + tenLoai + "%");
	        return query.getResultList();
	    }

	    public List<LoaiQuanAo> timTheoTenVa(String tenLoai) {
	        String jpql = "SELECT l FROM LoaiQuanAo l WHERE l.tenLoai LIKE :tenLoai";
	        TypedQuery<LoaiQuanAo> query = entityManager.createQuery(jpql, LoaiQuanAo.class);
	        query.setParameter("tenLoai", "%" + tenLoai + "%");
	        return query.getResultList();
	    }
	    
	public LoaiQuanAo getLoaiQuanAoByTen(String ten) {
        Query query = entityManager.createQuery("SELECT l FROM LoaiQuanAo l WHERE l.tenLoai = :ten");
        query.setParameter("ten", ten);
        List<LoaiQuanAo> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }
	public LoaiQuanAo getLoaiQuanAoByMa(String ma) {
        return entityManager.find(LoaiQuanAo.class, ma);
    }


    public boolean updateAllMaLoaiQuanAo(int deletedRowCount, String maLoaiQADaXoa) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            List<LoaiQuanAo> loaiQuanAoList = entityManager.createQuery("SELECT l FROM LoaiQuanAo l WHERE l.maLoai > :maLoai", LoaiQuanAo.class)
                    .setParameter("maLoai", maLoaiQADaXoa)
                    .getResultList();

            for (LoaiQuanAo loaiQuanAo : loaiQuanAoList) {
                int maCu = Integer.parseInt(loaiQuanAo.getMaLoai().substring(2)); // Lấy phần số trong MaLoai (loại bỏ ký tự "ML")
                int maMoi = maCu - deletedRowCount;
                String newMaLoai = "ML" + String.format("%03d", maMoi);

                loaiQuanAo.setMaLoai(newMaLoai);
                entityManager.merge(loaiQuanAo);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

}
