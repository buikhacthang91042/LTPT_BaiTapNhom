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
import entity.KhuyenMai;
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import entity.QuanAo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class DAO_NhaCungCap {
	private EntityManager entityManager;

	public DAO_NhaCungCap(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	List<NhaCungCap> nhaCungCap = new ArrayList<>();

	public List<NhaCungCap> getAllNhaCungCap() {
		TypedQuery<NhaCungCap> query = entityManager.createQuery("SELECT n FROM NhaCungCap n", NhaCungCap.class);
		return query.getResultList();
	}

	public boolean create(NhaCungCap nhaCungCap) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(nhaCungCap);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean delete(String maNCC) {
		try {
			NhaCungCap ncc = entityManager.find(NhaCungCap.class, maNCC);
			if (ncc != null) {
				entityManager.getTransaction().begin();
				entityManager.remove(ncc);
				entityManager.getTransaction().commit();
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean update(NhaCungCap nCC) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(nCC);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public List<NhaCungCap> timTheoMa(String maNCC) {
		Query query = entityManager.createQuery("SELECT n FROM NhaCungCap n WHERE n.maNCC = :maNCC");
		query.setParameter("maNCC", maNCC);
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoSoDienThoai(String sdt) {
		Query query = entityManager.createQuery("SELECT n FROM NhaCungCap n WHERE n.soDienThoai LIKE :sdt");
		query.setParameter("sdt", "%" + sdt + "%");
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoTen(String ten) {
		Query query = entityManager.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :ten");
		query.setParameter("ten", "%" + ten + "%");
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoDiaChi(String diaChi) {
		Query query = entityManager.createQuery("SELECT n FROM NhaCungCap n WHERE n.diaChi LIKE :diaChi");
		query.setParameter("diaChi", "%" + diaChi + "%");
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoTenvaDiaChi(String ten, String diaChi) {
		Query query = entityManager
				.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :ten AND n.diaChi LIKE :diaChi");
		query.setParameter("ten", "%" + ten + "%");
		query.setParameter("diaChi", "%" + diaChi + "%");
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoTenvaMa(String ten, String ma) {
		Query query = entityManager
				.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :ten AND n.maNCC LIKE :ma");
		query.setParameter("ten", "%" + ten + "%");
		query.setParameter("ma", ma);
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoTenvaSDT(String ten, String sdt) {
		Query query = entityManager
				.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :ten AND n.soDienThoai LIKE :sdt");
		query.setParameter("ten", "%" + ten + "%");
		query.setParameter("sdt", "%" + sdt + "%");
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoDiaChivaMa(String diaChi, String ma) {
		Query query = entityManager
				.createQuery("SELECT n FROM NhaCungCap n WHERE n.diaChi LIKE :diaChi AND n.maNCC LIKE :ma");
		query.setParameter("diaChi", "%" + diaChi + "%");
		query.setParameter("ma", ma);
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoSDTvaMa(String sdt, String ma) {
		Query query = entityManager
				.createQuery("SELECT n FROM NhaCungCap n WHERE n.soDienThoai LIKE :sdt AND n.maNCC LIKE :ma");
		query.setParameter("sdt", "%" + sdt + "%");
		query.setParameter("ma", ma);
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoSDTvaDiaChi(String sdt, String diaChi) {
		Query query = entityManager
				.createQuery("SELECT n FROM NhaCungCap n WHERE n.soDienThoai LIKE :sdt AND n.diaChi LIKE :diaChi");
		query.setParameter("sdt", "%" + sdt + "%");
		query.setParameter("diaChi", "%" + diaChi + "%");
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoTenvaDiaChivaMa(String ten, String diaChi, String ma) {
		Query query = entityManager.createQuery(
				"SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :ten AND n.diaChi LIKE :diaChi AND n.maNCC LIKE :ma");
		query.setParameter("ten", "%" + ten + "%");
		query.setParameter("diaChi", "%" + diaChi + "%");
		query.setParameter("ma", ma);
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoTenvaSDTvaMa(String ten, String sdt, String ma) {
		Query query = entityManager.createQuery(
				"SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :ten AND n.soDienThoai LIKE :sdt AND n.maNCC LIKE :ma");
		query.setParameter("ten", "%" + ten + "%");
		query.setParameter("sdt", "%" + sdt + "%");
		query.setParameter("ma", ma);
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoSDTvaMavaDiaChi(String sdt, String ma, String diaChi) {
		Query query = entityManager.createQuery(
				"SELECT n FROM NhaCungCap n WHERE n.soDienThoai LIKE :sdt AND n.maNCC LIKE :ma AND n.diaChi LIKE :diaChi");
		query.setParameter("sdt", "%" + sdt + "%");
		query.setParameter("ma", ma);
		query.setParameter("diaChi", "%" + diaChi + "%");
		return query.getResultList();
	}

	public List<NhaCungCap> timTheoTenvaDiaChivaMavaSDT(String ten, String diaChi, String ma, String sdt) {
		Query query = entityManager.createQuery(
				"SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :ten AND n.diaChi LIKE :diaChi AND n.maNCC LIKE :ma AND n.soDienThoai LIKE :sdt");
		query.setParameter("ten", "%" + ten + "%");
		query.setParameter("diaChi", "%" + diaChi + "%");
		query.setParameter("ma", ma);
		query.setParameter("sdt", "%" + sdt + "%");
		return query.getResultList();
	}

	public boolean updateAllMaNCC(int deletedRowCount, String maNCCDaXoa) {
		List<String> maNCCList = entityManager
				.createQuery("SELECT n.maNCC FROM NhaCungCap n WHERE n.maNCC >= :maNCCDaXoa", String.class)
				.setParameter("maNCCDaXoa", maNCCDaXoa).getResultList();

		entityManager.getTransaction().begin();
		for (String MaNCCCu : maNCCList) {
			int maCu = Integer.parseInt(MaNCCCu.substring(3));
			int maMoi = maCu - deletedRowCount;
			String newMaNCC = "NCC" + String.format("%03d", maMoi);

			Query updateQuery = entityManager
					.createQuery("UPDATE NhaCungCap n SET n.maNCC = :newMaNCC WHERE n.maNCC = :maNCCCu")
					.setParameter("newMaNCC", newMaNCC).setParameter("maNCCCu", MaNCCCu);
			updateQuery.executeUpdate();
		}
		entityManager.getTransaction().commit();
		return true;
	}

	public NhaCungCap getNCCByTen(String ten) {
		TypedQuery<NhaCungCap> query = entityManager.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNCC = :ten",
				NhaCungCap.class);
		query.setParameter("ten", ten);
		return query.getSingleResult();
	}

	public NhaCungCap getNCCByMa(String ma) {
		return entityManager.find(NhaCungCap.class, ma);
	}
}
