package entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NhaCungCap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MaNCC")
	private String maNCC;
	
	@Column(name = "TenNCC")
	private String tenNCC;
	
	@Column(name = "DiaChi")
	private String diaChi;
	
	@Column(name = "SoDienThoai")
	private String sDT;
	
	public NhaCungCap( String maNCC) {
		this.maNCC = maNCC;
		
	}
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getsDT() {
		return sDT;
	}
	public void setsDT(String sDT) {
		this.sDT = sDT;
	}
	public NhaCungCap(String maNCC, String tenNCC, String diaChi, String sDT) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.sDT = sDT;
	}
	@Override
	public int hashCode() {
		return Objects.hash(diaChi, maNCC, sDT, tenNCC);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		return Objects.equals(diaChi, other.diaChi) && Objects.equals(maNCC, other.maNCC)
				&& Objects.equals(sDT, other.sDT) && Objects.equals(tenNCC, other.tenNCC);
	}
	@Override
	public String toString() {
		return "Nhacungcap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", sDT=" + sDT + "]";
	}
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
