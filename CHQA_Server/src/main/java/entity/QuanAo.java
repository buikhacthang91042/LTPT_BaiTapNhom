package entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class QuanAo {
	@Id
	private String maQuanAo;
	
	private String tenQuanAo;
	
	@ManyToOne
	@JoinColumn(name = "MaNCC")
	private NhaCungCap nhaCungCap;
	
	@ManyToOne
	@JoinColumn(name = "loaiQuanAo")
	private LoaiQuanAo loaiQuanAo;
	private String kinhThuoc;
	private int soLuongCu;
	private int soLuongHienTai;
	private Date ngayNhap;
	
	
	@ManyToOne
    @JoinColumn(name = "MaKM") 
    private KhuyenMai khuyenMai;
	
	private float gia;
	private String hinhAnh;
	
	
	
	public QuanAo(String maQuanAo, String tenQuanAo, NhaCungCap NCC, LoaiQuanAo loaiQuanAo, String kinhThuoc,
			int soLuongCu, int soLuongHienTai, Date ngayNhap, KhuyenMai km, float gia, String hinhAnh) {
		super();
		this.maQuanAo = maQuanAo;
		this.tenQuanAo = tenQuanAo;
		this.nhaCungCap = NCC;
		this.loaiQuanAo = loaiQuanAo;
		this.kinhThuoc = kinhThuoc;
		this.soLuongCu = soLuongCu;
		this.soLuongHienTai = soLuongHienTai;
		this.ngayNhap = ngayNhap;
		this.khuyenMai = km;
		this.gia = gia;
		this.hinhAnh = hinhAnh;
	}




	public QuanAo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuanAo(String maQuanAo) {
		
		this.maQuanAo = maQuanAo;
		
	}
		
	public QuanAo(String maQuanAo, String tenQuanAo) {
		// TODO Auto-generated constructor stub
		this.maQuanAo = maQuanAo;
		this.tenQuanAo = tenQuanAo;
	}




	public String getMaQuanAo() {
		return maQuanAo;
	}




	public void setMaQuanAo(String maQuanAo) {
		this.maQuanAo = maQuanAo;
	}




	public String getTenQuanAo() {
		return tenQuanAo;
	}




	public void setTenQuanAo(String tenQuanAo) {
		this.tenQuanAo = tenQuanAo;
	}




	public NhaCungCap getNCC() {
		return nhaCungCap;
	}




	public void setNCC(NhaCungCap NCC) {
		this.nhaCungCap = NCC;
	}




	public LoaiQuanAo getLoaiQuanAo() {
		return loaiQuanAo;
	}




	public void setLoaiQuanAo(LoaiQuanAo loaiQuanAo) {
		this.loaiQuanAo = loaiQuanAo;
	}




	public String getKinhThuoc() {
		return kinhThuoc;
	}




	public void setKinhThuoc(String kinhThuoc) {
		this.kinhThuoc = kinhThuoc;
	}




	public int getSoLuongCu() {
		return soLuongCu;
	}




	public void setSoLuongCu(int soLuongCu) {
		this.soLuongCu = soLuongCu;
	}




	public int getSoLuongHienTai() {
		return soLuongHienTai;
	}




	public void setSoLuongHienTai(int soLuongHienTai) {
		this.soLuongHienTai = soLuongHienTai;
	}




	public Date getNgayNhap() {
		return ngayNhap;
	}




	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}




	public KhuyenMai getKm() {
		return khuyenMai;
	}




	public void setKm(KhuyenMai km) {
		this.khuyenMai = km;
	}




	public float getGia() {
		return gia;
	}




	public void setGia(float gia) {
		this.gia = gia;
	}




	public String getHinhAnh() {
		return hinhAnh;
	}




	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}




	@Override
	public String toString() {
		return "QuanAo [maQuanAo=" + maQuanAo + ", tenQuanAo=" + tenQuanAo + ", tenNCC=" + nhaCungCap + ", loaiQuanAo="
				+ loaiQuanAo + ", kinhThuoc=" + kinhThuoc + ", soLuongCu=" + soLuongCu + ", soLuongHienTai="
				+ soLuongHienTai + ", ngayNhap=" + ngayNhap + ", km=" + khuyenMai + ", gia=" + gia + ", hinhAnh=" + hinhAnh
				+ "]";
	}
	


	

}
