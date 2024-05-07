package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.DAO_ChiTietKhuyenMai;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class KhuyenMai implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(nullable = true)
	private String maKM;
	
	@Column(nullable = true)
	private String tenChuongTrinh;
	
	@Column(nullable = true)
	private Date ngayBatDau;
	
	@Column(nullable = true)
	private Date ngayKetThuc;
	
	@Column(nullable = true)
	private List<ChiTietKhuyenMai> ctkm;
	public KhuyenMai(String maKM) {
		
		this.maKM = maKM;
		
	}
	 
	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public String getTenChuongTrinh() {
		return tenChuongTrinh;
	}
	public void setTenChuongTrinh(String tenChuongTrinh) {
		this.tenChuongTrinh = tenChuongTrinh;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(java.sql.Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(java.sql.Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public KhuyenMai() {
		super();
		// TODO Auto-generated constructor stub
	}
	   public KhuyenMai(String maKM, String tenChuongTrinh, Date ngayBatDau, Date ngayKetThuc) {
	        this.maKM = maKM;
	        this.tenChuongTrinh = tenChuongTrinh;
	        this.ngayBatDau = ngayBatDau;
	        this.ngayKetThuc = ngayKetThuc;
	        this.ctkm = new ArrayList<>();
	    }
	@Override
	public String toString() {
		return "KhuyenMai [maKM=" + maKM + ", tenChuongTrinh=" + tenChuongTrinh + ", ngayBatDau=" + ngayBatDau
				+ ", ngayKetThuc=" + ngayKetThuc + "]";
	}
	public List<ChiTietKhuyenMai> getChiTietKhuyenMaiList() {
        return ctkm;
    }
	 
	public float layTileKhuyenMai(String maQuanAo, String maKhuyenMai) {
		DAO_ChiTietKhuyenMai dao = new DAO_ChiTietKhuyenMai();
		ctkm = dao.getAllChiTietKhuyenMai();
	    for (ChiTietKhuyenMai chiTiet : ctkm) {
	        if (chiTiet.getQuanAo().getMaQuanAo().equals(maQuanAo) &&
	            chiTiet.getMaKM().getMaKM().equals(maKhuyenMai)) {
	            return chiTiet.getTiLeKM();
	        }
	    }
	    
	    return 0; 
	}
	
}
