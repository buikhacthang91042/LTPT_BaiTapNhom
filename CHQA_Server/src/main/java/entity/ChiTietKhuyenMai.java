package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ChiTietKhuyenMai implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@ManyToOne
	@JoinColumn(name = "MaKM", referencedColumnName = "MaKM", nullable = true,columnDefinition = "nvarchar(255)")
	private KhuyenMai maKM;
	
	@ManyToOne
    @JoinColumn(name = "MaQuanAo",nullable = true,columnDefinition = "nvarchar(255)")
	private QuanAo quanAo;
	
	@Column(name = "TiLeKM",nullable = true)
	private float tiLeKM;

	public ChiTietKhuyenMai(KhuyenMai maKM, QuanAo quanAo, float tiLeKM) {
		super();
		this.maKM = maKM;
		this.quanAo = quanAo;
		this.tiLeKM = tiLeKM;
	}

	public ChiTietKhuyenMai() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChiTietKhuyenMai [maKM=" + maKM + ", quanAo=" + quanAo + ", tiLeKM=" + tiLeKM + "]";
	}

	public KhuyenMai getMaKM() {
		return maKM;
	}

	public void setMaKM(KhuyenMai maKM) {
		this.maKM = maKM;
	}

	public QuanAo getQuanAo() {
		return quanAo;
	}

	public void setQuanAo(QuanAo quanAo) {
		this.quanAo = quanAo;
	}

	public float getTiLeKM() {
		return tiLeKM;
	}

	public void setTiLeKM(float tiLeKM) {
		this.tiLeKM = tiLeKM;
	}

	public static int maQATangdan(QuanAo a, QuanAo b) {
		return a.getMaQuanAo().compareTo(b.getMaQuanAo());
	}
}
