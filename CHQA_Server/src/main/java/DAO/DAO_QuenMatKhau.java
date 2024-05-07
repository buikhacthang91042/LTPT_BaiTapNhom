package DAO;
import java.util.ArrayList;
import java.util.List;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
public class DAO_QuenMatKhau {
	private EntityManager entityManager;
	public DAO_QuenMatKhau(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	List<TaiKhoan> dsTaiKhoan = new ArrayList<>();
	
	public List<TaiKhoan> getTK(String taikhoan) {
        TypedQuery<TaiKhoan> query = entityManager.createQuery(
            "SELECT tk FROM TaiKhoan tk WHERE tk.taiKhoan LIKE :taikhoan", TaiKhoan.class);
        query.setParameter("taikhoan", taikhoan);
        return query.getResultList();
    }
		
}
