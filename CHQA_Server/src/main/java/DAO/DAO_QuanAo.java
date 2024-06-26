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
import entity.QuanAo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import entity.NhaCungCap;
import entity.KhuyenMai;
import entity.LoaiQuanAo;
public class DAO_QuanAo {
	private EntityManager entityManager;
	public DAO_QuanAo(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	List<QuanAo> dsQuanAo = new ArrayList<>();
	public List<QuanAo> getAllQuanAo() {
        return entityManager.createQuery("SELECT qa FROM QuanAo qa", QuanAo.class).getResultList();
    }
	 public List<QuanAo> getAllQuanAoByMaKM(String maKM) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.km.maKM = :maKM", QuanAo.class);
	        query.setParameter("maKM", maKM);
	        return query.getResultList();
	    }

	 public boolean create(QuanAo quanAo) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            entityManager.persist(quanAo);
	            transaction.commit();
	            return true;
	        } catch (Exception ex) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            ex.printStackTrace();
	            return false;
	        }
	    }
	
	 public boolean delete(String MaQuanAo) {
	        QuanAo quanAo = entityManager.find(QuanAo.class, MaQuanAo);
	        if (quanAo != null) {
	            entityManager.remove(quanAo);
	            return true;
	        }
	        return false;
	    }
	
	 public boolean update(QuanAo quanAo) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            entityManager.merge(quanAo);
	            transaction.commit();
	            return true;
	        } catch (Exception ex) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            ex.printStackTrace();
	            return false;
	        }
	    }

	 public List<QuanAo> timTheoTen(String ten) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.tenQuanAo LIKE :ten", QuanAo.class);
	        query.setParameter("ten", "%" + ten + "%");
	        return query.getResultList();
	    }
	 
	   public List<QuanAo> timTheoHang(String ma) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.nhaCC = :ma");
	        query.setParameter("ma", ma);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoLoai(String loai) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.loaiQuanAo = :loai");
	        query.setParameter("loai", "%" + loai);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoMa(String ma) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.maQuanAo = :ma");
	        query.setParameter("ma", ma);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoTenvaHang(String ten, String hang) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.tenQuanAo LIKE :ten AND qa.nhaCC = :hang");
	        query.setParameter("ten", "%" + ten);
	        query.setParameter("hang", hang);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoTenvaLoai(String ten, String loai) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.tenQuanAo LIKE :ten AND qa.loaiQuanAo = :loai");
	        query.setParameter("ten", "%" + ten);
	        query.setParameter("loai", loai);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoTenvaMa(String ten, String ma) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.tenQuanAo LIKE :ten AND qa.maQuanAo = :ma");
	        query.setParameter("ten", "%" + ten);
	        query.setParameter("ma", ma);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoHangvaLoai(String hang, String loai) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.nhaCC = :hang AND qa.loaiQuanAo = :loai");
	        query.setParameter("hang", hang);
	        query.setParameter("loai", loai);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoHangvaMa(String hang, String ma) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.nhaCC = :hang AND qa.maQuanAo = :ma");
	        query.setParameter("hang", hang);
	        query.setParameter("ma", ma);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoLoaivaMa(String loai, String ma) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.loaiQuanAo = :loai AND qa.maQuanAo = :ma");
	        query.setParameter("loai", loai);
	        query.setParameter("ma", ma);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoTenvaHangvaLoai(String ten, String hang, String loai) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.tenQuanAo LIKE :ten AND qa.nhaCC = :hang AND qa.loaiQuanAo = :loai");
	        query.setParameter("ten", "%" + ten);
	        query.setParameter("hang", hang);
	        query.setParameter("loai", loai);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoTenvaHangvaMa(String ten, String hang, String ma) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.tenQuanAo LIKE :ten AND qa.nhaCC = :hang AND qa.maQuanAo = :ma");
	        query.setParameter("ten", "%" + ten);
	        query.setParameter("hang", hang);
	        query.setParameter("ma", ma);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoLoaivaHangvaMa(String loai, String hang, String ma) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.loaiQuanAo = :loai AND qa.nhaCC = :hang AND qa.maQuanAo = :ma");
	        query.setParameter("loai", loai);
	        query.setParameter("hang", hang);
	        query.setParameter("ma", ma);
	        return query.getResultList();
	    }

	    public List<QuanAo> timTheoTenvaLoaivaHangvaMa(String ten, String loai, String hang, String ma) {
	        Query query = entityManager.createQuery("SELECT qa FROM QuanAo qa WHERE qa.tenQuanAo LIKE :ten AND qa.loaiQuanAo = :loai AND qa.nhaCC = :hang AND qa.maQuanAo = :ma");
	        query.setParameter("ten", "%" + ten);
	        query.setParameter("loai", loai);
	        query.setParameter("hang", hang);
	        query.setParameter("ma", ma);
	        return query.getResultList();
	    }
	
	 public boolean updateSoLuongCon(int soLuong, String tenQuanAo) {
	        try {
	            Query query = entityManager.createQuery("UPDATE QuanAo qa SET qa.soLuongHienTai = :soLuong WHERE qa.tenQuanAo = :tenQuanAo");
	            query.setParameter("soLuong", soLuong);
	            query.setParameter("tenQuanAo", tenQuanAo);
	            entityManager.getTransaction().begin();
	            int rowsUpdated = query.executeUpdate();
	            entityManager.getTransaction().commit();
	            return rowsUpdated > 0;
	        } catch (Exception ex) {
	            if (entityManager.getTransaction().isActive()) {
	                entityManager.getTransaction().rollback();
	            }
	            ex.printStackTrace();
	            return false;
	        }
	    }
	 public QuanAo getQuanAoByMa(String ma) {
	        return entityManager.find(QuanAo.class, ma);
	    }
	 public boolean updateMaKM(String maQuanAo, String maKM) {
	        Query query = entityManager.createQuery("UPDATE QuanAo qa SET qa.km.maKM = :maKM WHERE qa.maQuanAo = :maQuanAo");
	        query.setParameter("maKM", maKM);
	        query.setParameter("maQuanAo", maQuanAo);
	        entityManager.getTransaction().begin();
	        int rowsUpdated = query.executeUpdate();
	        entityManager.getTransaction().commit();
	        return rowsUpdated > 0;
	    }
	 public boolean updateAllMaQuanAo(int deletedRowCount, String maQADaXoa) {
	        EntityTransaction transaction = null;
	        try {
	            transaction = entityManager.getTransaction();
	            transaction.begin();

	            Query query = entityManager.createQuery("SELECT qa.maQuanAo FROM QuanAo qa WHERE qa.maQuanAo > :maQADaXoa");
	            query.setParameter("maQADaXoa", maQADaXoa);
	            List<String> maQAList = query.getResultList();

	            for (String MaQACu : maQAList) {
	                int maCu = Integer.parseInt(MaQACu.substring(3));
	                int maMoi = maCu - deletedRowCount;
	                String newMaNCC = "QA" + String.format("%03d", maMoi);

	                Query updateQuery = entityManager.createQuery("UPDATE QuanAo qa SET qa.maQuanAo = :newMaNCC WHERE qa.maQuanAo = :maQuanAo");
	                updateQuery.setParameter("newMaNCC", newMaNCC);
	                updateQuery.setParameter("maQuanAo", MaQACu);
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

