package entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "ChiTietPhieuDatHangTruoc")
public class ChiTietDonDatHang implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MaDonHang",columnDefinition = "nvarchar(255)")
	private DonDatHang maDatHang;
	
	@ManyToOne
	@JoinColumn(name = "MaQuanAo",columnDefinition = "nvarchar(255)")
	private QuanAo quanAo;
	
	@Column(name = "SoLuong")
	private int soLuong;
	
	@Column(name = "GiaBan")
	private float giaBan;
	
	@Column(name = "ThanhTien")
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
