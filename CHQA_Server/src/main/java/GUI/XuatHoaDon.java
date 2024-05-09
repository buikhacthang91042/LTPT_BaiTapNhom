package GUI;

import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import DAO.DAO_ChiTietHoaDon;
import DAO.DAO_ChuyenDoi;
import DAO.DAO_HoaDon;
import DAO.DAO_KhachHang;
import DAO.DAO_NhanVien;
import DAO.DAO_QuanAo;
import DAO.EntityManagerFactoryUtil;
import connect.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.QuanAo;
import jakarta.persistence.EntityManager;
public class XuatHoaDon {
	private DAO_HoaDon hoaDon_dao;
	private DAO_NhanVien nhanVien_dao;
	private DAO_KhachHang khachHang_dao;
	private DAO_QuanAo quanAo_dao;
	private DAO_ChiTietHoaDon CTHD_dao;
	private DAO_ChuyenDoi chuyenDoi_dao;
	EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
    EntityManager entityManager = util.getEnManager();
	public XuatHoaDon(String mahd,String tienKhachTra,String tienTraLai) {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HoaDon hd = new HoaDon();
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		int inttienKhachTra = 0;
		int inttienTraLai = 0;

		try {
		    
		    String formattedTienKhachTra = tienKhachTra.trim().replace(",", "").replace("Đồng", "");
		    String formattedTienTraLai = tienTraLai.trim().replace(",", "").replace("Đồng", "");

		    // Chuyển đổi chuỗi thành số nguyên
		    inttienKhachTra = Integer.parseInt(formattedTienKhachTra);
		    inttienTraLai = Integer.parseInt(formattedTienTraLai);
		} catch (NumberFormatException ex) {
		   
		    ex.printStackTrace(); 
		}
		List<ChiTietHoaDon> dscthd = new ArrayList<ChiTietHoaDon>();
		
<<<<<<< HEAD
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		chuyenDoi_dao = new DAO_ChuyenDoi();
		hoaDon_dao = new DAO_HoaDon();
		nhanVien_dao = new DAO_NhanVien(entityManager);
=======

		chuyenDoi_dao = new DAO_ChuyenDoi();
		hoaDon_dao = new DAO_HoaDon(entityManager);
		nhanVien_dao = new DAO_NhanVien();
>>>>>>> e461a82c85da96ccf7dbe681e4e446baf43eb782
		khachHang_dao = new DAO_KhachHang(entityManager);
		CTHD_dao = new DAO_ChiTietHoaDon();
	

		
		try {
			hd = hoaDon_dao.layHoaDonTheoMa(mahd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		  kh = khachHang_dao.getKhachHang(hd.getKH().getMaKH());
		  nv = nhanVien_dao.getNhanVien(hd.getNV().getMaNV());
		  dscthd =CTHD_dao.getAllChiTietHoaDon(hd.getMaHD());
		 

		Document doc = new Document();
		try {

			BaseFont bf1 = BaseFont.createFont("font/timesbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			
			
			Font ftenCuaHang = new Font(bf1, 20);
			Font f1 = new Font(bf1, 10);
			Font f2 = new Font(bf1, 13);


			PdfWriter write = PdfWriter.getInstance(doc,
					new FileOutputStream("C:/JAVA/DANHSACHHOADON/" + mahd + "_" + kh.getMaKH() + ".pdf"));
			write.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
			doc.open();
			
			//Tên cửa hàng
			Paragraph tenCuaHang = new Paragraph("CỬA HÀNG QUẦN ÁO THỜI TRANG",ftenCuaHang);
			tenCuaHang.setAlignment(Paragraph.ALIGN_CENTER);
			tenCuaHang.setSpacingAfter(10f);
			doc.add(tenCuaHang);
			
			//Tạo đường kẻ
			LineSeparator line = new LineSeparator();
			doc.add(line);
			
			//Địa chỉ cửa hàng
			Paragraph diaChi = new Paragraph("Địa chỉ: Số 1 Nguyễn Văn Bảo, Quận Gò Vấp,Thành phố Hồ Chí Minh",f1);
			diaChi.setAlignment(Paragraph.ALIGN_CENTER);
			diaChi.setSpacingAfter(10f);
			doc.add(diaChi);
			
			//Thông tin mã hóa đơn
			LocalTime time = LocalTime.now();
			String formattedNgayMua = new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayMua());
			String s1 = String.format("Mã hóa đơn:%2s" + mahd + "%20s" +  
					"Ngày lập: %s%10s%02d:%02d:%02d", "", "", formattedNgayMua,"", time.getHour(), time.getMinute(), time.getSecond());
			String s2 = String.format(
					"Nhân viên:%6s%10s" ,"", nv.getTenNV());
			
			doc.add(new Paragraph(s1, f1));
			doc.add(new Paragraph(s2, f1));
			
			//Hóa đơn bán hàng
			Paragraph p2 = new Paragraph("HÓA ĐƠN BÁN HÀNG", f2);
			p2.setAlignment(Paragraph.ALIGN_CENTER);
			p2.setSpacingBefore(20f);
			doc.add(p2);
			
			

			String s3 = String.format("Mã khách hàng:%3s" + kh.getMaKH() + "%30s" + "Tên khách hàng:%3s" + kh.getHoTen() + "%20s"
			        + "Số điện thoại:%3s" + kh.getsDT(), "", "", "", "", "");
		
			doc.add(new Paragraph("\n"));
			doc.add(new Paragraph(s3, f1));
			doc.add(new Paragraph(String.format("Địa chỉ:%4s" + kh.getDiaChi(), ""), f1));

			//Tao bảng
			PdfPTable table = new PdfPTable(8);
			//Set căn chỉnh bảng
			table.setWidthPercentage(100);
			table.setSpacingBefore(5f);
			table.setSpacingAfter(20f);
			
			//Độ rộng cột trong bảng
			float[] colwidth = { 0.5f, 1f, 2f, 1f, 1f, 1f, 1f, 1f };
			table.setWidths(colwidth);
			
			//Tên cột
			PdfPCell c0 = new PdfPCell(new Paragraph("STT", f1));
			PdfPCell c1 = new PdfPCell(new Paragraph("Mã quần áo", f1));
			PdfPCell c2 = new PdfPCell(new Paragraph("Tên quần áo", f1));
			PdfPCell c3 = new PdfPCell(new Paragraph("ĐVT", f1));
			PdfPCell c4 = new PdfPCell(new Paragraph("Đơn giá", f1));
			PdfPCell c5 = new PdfPCell(new Paragraph("Số lượng", f1));
			PdfPCell c6 = new PdfPCell(new Paragraph("Khuyến mãi", f1));
			PdfPCell c7 = new PdfPCell(new Paragraph("Thành tiền", f1));
			
			//Căn chỉnh nội dung trong ô nằm ở giữa
			c0.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c7.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			//Không tạo khoảng trắng giữa các ô
			c0.setFixedHeight(30);
			c0.setBorderWidthRight(0f);
			c1.setBorderWidthRight(0f);
			c2.setBorderWidthRight(0f);
			c3.setBorderWidthRight(0f);
			c4.setBorderWidthRight(0f);
			c5.setBorderWidthRight(0f);
			c6.setBorderWidthRight(0f);
			c7.setBorderWidthRight(0f);
			
			//Thêm ô vào bảng
			table.addCell(c0);
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			table.addCell(c4);
			table.addCell(c5);
			table.addCell(c6);
			table.addCell(c7);
			
			//lấy dữ liệu chi tiết hóa đơn 
			int i = 1;
			for (ChiTietHoaDon ct : dscthd) {
				QuanAo s = ct.getQuanAo();
				PdfPCell c8 = new PdfPCell(new Paragraph(i++ + "", f1));
				PdfPCell c9 = new PdfPCell(new Paragraph(s.getMaQuanAo(), f1));
				PdfPCell c10 = new PdfPCell(new Paragraph(s.getTenQuanAo(), f1));
				PdfPCell c11 = new PdfPCell(new Paragraph("Cái", f1));
				PdfPCell c12 = new PdfPCell(new Paragraph( chuyenDoi_dao.DinhDangTien(ct.getGiaBan()), f1));
				PdfPCell c13 = new PdfPCell(new Paragraph(ct.getSoLuong() + "", f1));
				PdfPCell c14 = new PdfPCell(new Paragraph(ct.getQuanAo().getKm().layTileKhuyenMai(s.getMaQuanAo(), s.getKm().getMaKM()) + "%", f1));
				PdfPCell c15 = new PdfPCell(
						new Paragraph(chuyenDoi_dao.DinhDangTien(ct.getThanhTien()), f1));

				c8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c9.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c10.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c12.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c13.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c15.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				

				c8.setFixedHeight(30f);

				c8.setBorderWidthTop(0f);
				c8.setBorderWidthRight(0f);
				c9.setBorderWidthTop(0f);
				c9.setBorderWidthRight(0f);
				c10.setBorderWidthTop(0f);
				c10.setBorderWidthRight(0f);
				c11.setBorderWidthTop(0f);
				c11.setBorderWidthRight(0f);
				c12.setBorderWidthTop(0f);
				c12.setBorderWidthRight(0f);
				c13.setBorderWidthTop(0f);
				c13.setBorderWidthRight(0f);
				c14.setBorderWidthTop(0f);
				c15.setBorderWidthTop(0f);


				table.addCell(c8);
				table.addCell(c9);
				table.addCell(c10);
				table.addCell(c11);
				table.addCell(c12);
				table.addCell(c13);
				table.addCell(c14);
				table.addCell(c15);
				


			}
			doc.add(new Paragraph("\n"));
			doc.add(table);
			
			doc.add(line);
			
			doc.add(new Paragraph(
					String.format("%-30s %-144s" +  chuyenDoi_dao.DinhDangTien(hd.getTongTien()) + " Đồng", "Tổng tiền:", ""), f1));
			doc.add(new Paragraph("\n"));
			doc.add(new Paragraph(String.format("%-30s %-138s" + decimalFormat.format(inttienKhachTra) + " Đồng",
					"Tiền khách đưa:", ""), f1));
			doc.add(new Paragraph("\n"));
			doc.add(line);
			
			doc.add(new Paragraph(String.format("%-30s %-146s" + decimalFormat.format(inttienTraLai) + " Đồng",
					"Trả lại:", ""), f1));
				
			
			
			doc.add(new Paragraph("\n\n"));
			
			doc.add(new Paragraph("\n"));
			Paragraph p3 = new Paragraph(
					"Cảm ơn quý khách", f1);
			p3.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(p3);

			doc.close();
			write.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Không thể tạo hóa đơn");
			e.printStackTrace();
		}
	}
	
	  
}
