package entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "MaNV")
	private String maNV;
	
	@Column(name = "TenNV")
	private String tenNV;
	
	@Column(name = "NamSinh")
	private String namSinh;
	
	@Column(name = "GioiTinh")
	private String gioiTinh;
	
	@Column(name = "NgayVaoLam")
	private Date ngayVaolam;
	
	@Column(name = "Sdt")
	private String sDT;
	
	public NhanVien(String maNV) {
		this.maNV = maNV;
	
	}
	
	public NhanVien(String maNV, String tenNV, String namSinh, String gioiTinh, Date ngayVaolam, String sDT) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.namSinh = namSinh;
		this.gioiTinh = gioiTinh;
		this.ngayVaolam = ngayVaolam;
		this.sDT = sDT;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getNamSinh() {
		return namSinh;
	}
	public void setNamSinh(String namSinh) {
		this.namSinh = namSinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public Date getNgayVaolam() {
		return ngayVaolam;
	}
	public void setNgayVaolam(java.sql.Date ngayVaolam) {
		this.ngayVaolam = ngayVaolam;
	}
	public String getsDT() {
		return sDT;
	}
	public void setsDT(String sDT) {
		this.sDT = sDT;
	}
	@Override
	public int hashCode() {
		return Objects.hash(gioiTinh, maNV, namSinh, ngayVaolam, sDT, tenNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(gioiTinh, other.gioiTinh) && Objects.equals(maNV, other.maNV) && namSinh == other.namSinh
				&& Objects.equals(ngayVaolam, other.ngayVaolam) && Objects.equals(sDT, other.sDT)
				&& Objects.equals(tenNV, other.tenNV);
	}
	@Override
	public String toString() {
		return "Nhanvien [maNV=" + maNV + ", tenNV=" + tenNV + ", namSinh=" + namSinh + ", gioiTinh=" + gioiTinh
				+ ", ngayVaolam=" + ngayVaolam + ", sDT=" + sDT + "]";
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
