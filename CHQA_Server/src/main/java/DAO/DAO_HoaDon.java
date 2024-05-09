package DAO;


import java.util.List;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DAO_HoaDon {
	private EntityManager entityManager;
	public DAO_HoaDon (EntityManager entityManager) {
		this.entityManager = entityManager; 
	}	
	 public List<HoaDon> getAllHoaDon() {
	        TypedQuery<HoaDon> query = entityManager.createQuery(
	            "SELECT hd FROM HoaDon hd JOIN FETCH hd.khachHang JOIN FETCH hd.nhanVien",
	            HoaDon.class
	        );
	        return query.getResultList();
	    }

	    public void create(HoaDon hd) {
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.persist(hd);
	            entityManager.getTransaction().commit();
	        } catch (Exception e) {
	            if (entityManager.getTransaction().isActive()) {
	                entityManager.getTransaction().rollback();
	            }
	            e.printStackTrace();
	        }
	    }

	    public List<HoaDon> search(String maHD) {
	        TypedQuery<HoaDon> query = entityManager.createQuery(
	            "SELECT hd FROM HoaDon hd JOIN FETCH hd.khachHang JOIN FETCH hd.nhanVien WHERE hd.MaHD LIKE :maHD",
	            HoaDon.class
	        );
	        query.setParameter("maHD", "%" + maHD + "%");
	        return query.getResultList();
	    }

	    public HoaDon layHoaDonTheoMa(String maHD) throws Exception {
	        HoaDon hd = entityManager.find(HoaDon.class, maHD);
	        if (hd == null) {
	            throw new Exception("Không tìm thấy hóa đơn với mã " + maHD);
	        }
	        return hd;
	    }

}
