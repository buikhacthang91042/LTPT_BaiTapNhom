package entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class HoaDon {
	@Id
	private String maHD;
	
	private Date ngayMua;
	
	@ManyToOne
    @JoinColumn(name = "MaNV")
    private NhanVien nhanVien;
	
	@ManyToOne
	@JoinColumn(name = "MaKH")
	private KhachHang khachHang;
	
	private float tongTien;
	

	public HoaDon(String maHD, Date ngay, NhanVien NV, KhachHang KH, float tongTien) {
		super();
		this.maHD = maHD;
		this.ngayMua =ngay;
		this.nhanVien = NV;
		this.khachHang = KH;
		this.tongTien = tongTien;
	}
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	
	public KhachHang getKH() {
		return khachHang;
	}
	public void setKH(KhachHang KH) {
		this.khachHang = KH;
	}
	public NhanVien getNV() {
		return nhanVien;
	}
	public void setNV(NhanVien NV) {
		this.nhanVien = NV;
	}
	public Date getNgayMua() {
		return ngayMua;
	}
	public void setNgayMua(java.sql.Date ngayMua) {
		this.ngayMua = ngayMua;
	}
	
	

	
	@Override
	public int hashCode() {
		return Objects.hash(maHD, khachHang, nhanVien, ngayMua);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD) && Objects.equals(khachHang, other.khachHang) && Objects.equals(nhanVien, other.nhanVien)
				&& Objects.equals(ngayMua, other.ngayMua);
	}



	
	
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayMua=" + ngayMua + ", NV=" + nhanVien + ", KH=" + khachHang + ", tongTien=" + tongTien
				+ "]";
	}
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public float tinhTongTien(List<ChiTietHoaDon> chiTietHoaDon) {
		float tongTien = 0;
		for (ChiTietHoaDon ctHD : chiTietHoaDon) {
			tongTien += (ctHD.tinhThanhTien(ctHD.getSoLuong(), ctHD.getQuanAo()));
		}
		return tongTien;
	}

}
