package entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LoaiQuanAo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MaLoai")
	private String maLoai;
	
	@Column(name = "TenLoai")
	private String tenLoai;
	
	
	public LoaiQuanAo(String maLoai) {
		this.maLoai = maLoai;
		
	}
	public LoaiQuanAo(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLoai, tenLoai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiQuanAo other = (LoaiQuanAo) obj;
		return Objects.equals(maLoai, other.maLoai) && Objects.equals(tenLoai, other.tenLoai);
	}
	@Override
	public String toString() {
		return "Loaisanpham [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}
	public LoaiQuanAo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
