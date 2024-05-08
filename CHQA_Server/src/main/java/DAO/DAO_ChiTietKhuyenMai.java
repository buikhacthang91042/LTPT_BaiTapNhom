package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Query;


import connect.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietKhuyenMai;
import entity.KhuyenMai;
import entity.QuanAo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAO_ChiTietKhuyenMai {
	private EntityManager entityManager;
	public DAO_ChiTietKhuyenMai (EntityManager entityManager) {
		this.entityManager = entityManager; 
	}	
	List<ChiTietKhuyenMai> ctkm = new ArrayList<>();
	
	public List<ChiTietKhuyenMai> getAllChiTietKhuyenMai() {
		Query query = entityManager.createQuery( "SELECT ctkm FROM ChiTietKhuyenMai ctkm");
		return query.getResultList();
//		List<ChiTietKhuyenMai> ctkm = new ArrayList<>();
		
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		
//		String sql= "Select ctkm.MaKM, qa.MaQuanAo, qa.TenQuanAo, TiLeKM \r\n"
//				+ "from ChiTietKhuyenMai ctkm join QuanAo qa on ctkm.MaQuanAo = qa.MaQuanAo \r\n "
//				;
//		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
//				QuanAo qa = new QuanAo();
//				KhuyenMai km = new KhuyenMai();
//				km.setMaKM(rs.getString("maKM"));
//				
//				qa.setMaQuanAo(rs.getString("MaQuanAo"));
//				qa.setTenQuanAo(rs.getString("TenQuanAo"));
//				chiTietKhuyenMai.setTiLeKM(rs.getFloat("TiLeKM"));			
//				chiTietKhuyenMai.setQuanAo(qa);
//				chiTietKhuyenMai.setMaKM(km);
//				ctkm.add(chiTietKhuyenMai);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ctkm;
	}
	public List<ChiTietKhuyenMai> getCTKMByMa(String maKM) {
		Query query = entityManager.createQuery("Select ctkm From ChiTietKhuyenMai ctkm Where ctkm.maKH= :MaKH");
		query.setParameter("MaKM", maKM);
		return query.getResultList();
//		List<ChiTietKhuyenMai> ctkm = new ArrayList<>();
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		
//		String sql= "Select ctkm.MaKM, qa.MaQuanAo, qa.TenQuanAo, TiLeKM \r\n"
//				+ "from ChiTietKhuyenMai ctkm join QuanAo qa on ctkm.MaQuanAo = qa.MaQuanAo \r\n "
//				+ "where ctkm.MaKM= ?"
//				;
//		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setString(1, maKM);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
//				QuanAo qa = new QuanAo();
//				KhuyenMai km = new KhuyenMai();
//				km.setMaKM(rs.getString("maKM"));
//				
//				qa.setMaQuanAo(rs.getString("MaQuanAo"));
//				qa.setTenQuanAo(rs.getString("TenQuanAo"));
//				chiTietKhuyenMai.setTiLeKM(rs.getFloat("TiLeKM"));			
//				chiTietKhuyenMai.setQuanAo(qa);
//				chiTietKhuyenMai.setMaKM(km);
//				ctkm.add(chiTietKhuyenMai);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ctkm;
	}

	public boolean create(ChiTietKhuyenMai ctkm) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(ctkm);
			transaction.commit();
			return true;
		} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
		
//	    Connection con = ConnectDB.getInstance().getConnection();
//	    PreparedStatement stmt = null;
//	    int n = 0;
//
//	    try {
//	        stmt = con.prepareStatement("INSERT INTO ChiTietKhuyenMai(MaKM, MaQuanAo, TiLeKM) VALUES (?, ?, ?)");
//	        stmt.setString(1, ctkm.getMaKM().getMaKM());
//	        stmt.setString(2, ctkm.getQuanAo().getMaQuanAo());
//	        stmt.setFloat(3, ctkm.getTiLeKM());
//
//	        n = stmt.executeUpdate();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } 
//
//	    return n > 0;
	}
	
	public boolean delete(String maQuanAo, String maKM) {
		 EntityTransaction transaction = null;
	        try {
	            transaction = entityManager.getTransaction();
	            transaction.begin();
	            ChiTietKhuyenMai ctkm = entityManager.createQuery(
	                "SELECT c FROM ChiTietKhuyenMai c WHERE c.QuanAo.MaQuanAo = :maQuanAo AND c.ChiTietKhuyenMai.maKM = :maKM", ChiTietKhuyenMai.class)
	                .setParameter("maQuanAo", maQuanAo)
	                .setParameter("maKM", maKM)
	                .getSingleResult();

	            if (ctkm != null) {
	                entityManager.remove(ctkm);
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
//	    Connection con = ConnectDB.getInstance().getConnection();
//	    PreparedStatement stmt = null;
//	    int n = 0;
//
//	    try {
//	        stmt = con.prepareStatement("DELETE FROM ChiTietKhuyenMai WHERE MaQuanAo = ? AND MaKM = ? ");
//	        stmt.setString(1, maQuanAo);
//	        stmt.setString(2, maKM);
//	        
//
//	        n = stmt.executeUpdate();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//
//	    return n > 0;
	}
	public boolean deleteAllByMaKM(String maKM) {
		EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            ChiTietKhuyenMai km = entityManager.find(ChiTietKhuyenMai.class, maKM);
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
//        Connection con = null;
//        PreparedStatement stmt = null;
//        int n = 0;
//        try {
//            con = ConnectDB.getConnection();
//            String sql = "DELETE FROM ChiTietKhuyenMai WHERE MaKM = ?";
//            stmt = con.prepareStatement(sql);
//            stmt.setString(1, maKM);
//            n= stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return n > 0;
    }
	public boolean update(ChiTietKhuyenMai chiTietKhuyenMai) {
		EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(chiTietKhuyenMai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
//	    Connection con = ConnectDB.getInstance().getConnection();
//	    PreparedStatement stmt = null;
//	    int n = 0;
//
//	    try {
//	        stmt = con.prepareStatement("UPDATE ChiTietKhuyenMai SET TiLeKM = ? WHERE MaQuanAo = ? AND MaKM = ?");
//	        stmt.setFloat(1, chiTietKhuyenMai.getTiLeKM());
//	        stmt.setString(2, chiTietKhuyenMai.getQuanAo().getMaQuanAo());
//	        stmt.setString(3, chiTietKhuyenMai.getMaKM().getMaKM());
//
//	        n = stmt.executeUpdate();
//	    } catch (SQLException e) {
//	     
//
//	   
//	}
//	    return n > 0;

	}
}
