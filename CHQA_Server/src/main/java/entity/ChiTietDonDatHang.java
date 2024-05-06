package entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class ChiTietDonDatHang {
	@Id
	private DonDatHang maDatHang;
	
	@ManyToOne
	@JoinColumn(name = "MaQuanAo")
	private QuanAo quanAo;
	
	private int soLuong;
	private float giaBan;
	private float thanhTien;
	public ChiTietDonDatHang(DonDatHang maDatHang, QuanAo quanAo, int soLuong, float giaBan, float thanhTien) {
		super();
		this.maDatHang = maDatHang;
		this.quanAo = quanAo;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.thanhTien = thanhTien;
	}
	public DonDatHang getMaDatHang() {
		return maDatHang;
	}
	public void setMaDatHang(DonDatHang maDatHang) {
		this.maDatHang = maDatHang;
	}
	public QuanAo getQuanAo() {
		return quanAo;
	}
	public void setQuanAo(QuanAo quanAo) {
		this.quanAo = quanAo;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public float getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}
	public float getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(float thanhTien) {
		this.thanhTien = thanhTien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(giaBan, maDatHang, quanAo, soLuong, thanhTien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietDonDatHang other = (ChiTietDonDatHang) obj;
		return Float.floatToIntBits(giaBan) == Float.floatToIntBits(other.giaBan)
				&& Objects.equals(maDatHang, other.maDatHang) && Objects.equals(quanAo, other.quanAo)
				&& soLuong == other.soLuong && Float.floatToIntBits(thanhTien) == Float.floatToIntBits(other.thanhTien);
	}
	@Override
	public String toString() {
		return "ChiTietDonDatHang [maDatHang=" + maDatHang + ", quanAo=" + quanAo + ", soLuong=" + soLuong + ", giaBan="
				+ giaBan + ", thanhTien=" + thanhTien + "]";
	}
	public ChiTietDonDatHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double tinhThanhTien(int soLuong,QuanAo quanAo) {
		double thanhTien = 0;
		thanhTien = soLuong * quanAo.getGia() ;
		return thanhTien;
	}

}