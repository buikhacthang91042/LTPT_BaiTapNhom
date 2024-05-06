package entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class ChiTietHoaDon {
	@Id
	private HoaDon mahoaDon;
	
	@ManyToOne
    @JoinColumn(name = "MaQuanAo")
	private QuanAo quanAo;
	private int soLuong;
	private float giaBan;
	private float thanhTien;


		
	

	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(giaBan, mahoaDon, soLuong, quanAo, thanhTien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return Double.doubleToLongBits(giaBan) == Double.doubleToLongBits(other.giaBan)
				&& Objects.equals(mahoaDon, other.mahoaDon) && soLuong == other.soLuong
				&& Objects.equals(quanAo, other.quanAo)
				&& Double.doubleToLongBits(thanhTien) == Double.doubleToLongBits(other.thanhTien);
	}
	public ChiTietHoaDon(HoaDon mahoaDon, QuanAo quanAo, int soLuong, float giaBan, float thanhTien) {
		super();
		this.mahoaDon = mahoaDon;
		this.quanAo = quanAo;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.thanhTien = thanhTien;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [mahoaDon=" + mahoaDon + ", tenQuanAo=" + quanAo + ", soLuong=" + soLuong + ", giaBan="
				+ giaBan + ", thanhTien=" + thanhTien + "]";
	}
	public QuanAo getQuanAo() {
		return quanAo;
	}
	public void setQuanAo(QuanAo quanAo) {
		this.quanAo = quanAo;
	}
	public float getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.mahoaDon = hoaDon;
	}
	public float getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(float thanhTien) {
		this.thanhTien = thanhTien;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public HoaDon getMahoaDon() {
		return mahoaDon;
	}
	public void setMahoaDon(HoaDon mahoaDon) {
		this.mahoaDon = mahoaDon;
	}
	
	public float tinhThanhTien(int soLuong,QuanAo quanAo) {
		float thanhTien = 0;
		thanhTien = soLuong * quanAo.getGia() ;
		return thanhTien;
	}
	
}
