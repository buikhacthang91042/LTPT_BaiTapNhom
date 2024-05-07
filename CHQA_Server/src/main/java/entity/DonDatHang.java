package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DonHangDatTruoc")
public class DonDatHang implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MaDonHang",columnDefinition = "nvarchar(255)")
	private String maDonHang;
	
	@Column(name = "NgayMua")
	private Date ngayMua;
	
	@ManyToOne
	@JoinColumn(name = "MaNV",columnDefinition = "nvarchar(255)")
	private NhanVien nhanVien;
	
	@ManyToOne
    @JoinColumn(name = "MaKH",columnDefinition = "nvarchar(255)")
    private KhachHang khachHang;
	
	@Column(name = "TongTien")
	private float tongTien;
	public DonDatHang(String maDonHang, Date ngayMua, NhanVien nV, KhachHang kH, float tongTien) {
		super();
		this.maDonHang = maDonHang;
		this.ngayMua = ngayMua;
		nhanVien = nV;
		khachHang = kH;
		this.tongTien = tongTien;
	}
	
	public DonDatHang(String maDonHang) {
		super();
		this.maDonHang = maDonHang;
	}
	
	
	public String getMaDonHang() {
		return maDonHang;
	}
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	public Date getNgayMua() {
		return ngayMua;
	}
	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}
	public NhanVien getNV() {
		return nhanVien;
	}
	public void setNV(NhanVien nV) {
		nhanVien = nV;
	}
	public KhachHang getKH() {
		return khachHang;
	}
	public void setKH(KhachHang kH) {
		khachHang = kH;
	}
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(khachHang, nhanVien, maDonHang, ngayMua, tongTien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonDatHang other = (DonDatHang) obj;
		return Objects.equals(khachHang, other.khachHang) && Objects.equals(nhanVien, other.nhanVien)
				&& Objects.equals(maDonHang, other.maDonHang) && Objects.equals(ngayMua, other.ngayMua)
				&& Float.floatToIntBits(tongTien) == Float.floatToIntBits(other.tongTien);
	}
	@Override
	public String toString() {
		return "DonDatHang [maDonHang=" + maDonHang + ", ngayMua=" + ngayMua + ", NV=" + nhanVien + ", KH=" + khachHang
				+ ", tongTien=" + tongTien + "]";
	}
	public DonDatHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
	
