package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

import DAO.DAO_ChiTietKhuyenMai;
import DAO.DAO_ChuyenDoi;
import DAO.DAO_KhuyenMai;
import DAO.DAO_LoaiQuanAo;
import DAO.DAO_NhaCungCap;
import DAO.DAO_QuanAo;
import connect.ConnectDB;
import entity.KhuyenMai;
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import entity.QuanAo;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.TextField;

public class GUI_CapNhatQuanAo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTenQuanAo,txtDonGia,txtSoLuong,txtTimKiem,txtNgayNhap,txtMaQuanAo;
	private JTable tblQuanAo;
	private JComboBox<String> cboLoaiQuanAo,cboNCC,cboKichCo;
	private DefaultTableModel modelThongTinQuanAo;
	private String ma;
	private List<QuanAo> list;
	private JLabel lblTiLeKhuyenMai,lblNgayNhap,lblHinhAnh;
	private GUI_DatHang guiDatHang;
	private GUI_TimKiemQuanAo timKiemQuanAo;
	private int deletedRowCount=1; 
	private DAO_ChuyenDoi chuyenDoi;
	private Date ngay;
	
	public GUI_CapNhatQuanAo( GUI_TimKiemQuanAo timKiemQuanAo) {
		this.guiDatHang = guiDatHang;
		this.timKiemQuanAo= timKiemQuanAo;
		setLayout(null);
		setBackground(new Color(0, 64, 64));
		
		//Đặt tên tiêu đề cho giao diện
		JLabel lblTieude = new JLabel("Quản lí quần áo ");
		lblTieude.setForeground(new Color(135, 206, 235));
		lblTieude.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblTieude.setBounds(518, 0, 277, 50);
		add(lblTieude);
		
		//ConnectDb
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Tạo bảng danh sách quần áo		
		 String[] tieude = {
				"Mã quần áo", "Tên quần áo ", "Tên nhà cung cấp", "Loại quần áo", "Kích thước", "Số lượng cũ","Số lượng hiện tại","Ngày nhập","Khuyến Mãi","Giá"
		};
		modelThongTinQuanAo = new DefaultTableModel(tieude,0);
		JScrollPane scrThongTinQuanAo = new JScrollPane();
		scrThongTinQuanAo.setBounds(69, 425, 1172, 249);
		add(scrThongTinQuanAo);
			
		scrThongTinQuanAo.setViewportView(tblQuanAo = new  JTable(modelThongTinQuanAo));
		scrThongTinQuanAo.setViewportView(tblQuanAo);
		tblQuanAo.addMouseListener(new MouseAdapter() {
	 		@Override
	 		public void mouseClicked(MouseEvent e) {
							
				  int row = tblQuanAo.getSelectedRow();
				  txtMaQuanAo.setText(tblQuanAo.getValueAt(row, 0).toString());
				  txtTenQuanAo.setText(tblQuanAo.getValueAt(row, 1).toString());
				  cboNCC.setSelectedItem(tblQuanAo.getValueAt(row, 2).toString());
				  cboLoaiQuanAo.setSelectedItem(tblQuanAo.getValueAt(row, 3).toString());
				  cboKichCo.setSelectedItem(tblQuanAo.getValueAt(row, 4).toString());
				  txtSoLuong.setText(tblQuanAo.getValueAt(row, 6).toString());
				  txtDonGia.setText(tblQuanAo.getValueAt(row, 9).toString());
				  lblTiLeKhuyenMai.setText(tblQuanAo.getValueAt(row, 8).toString());
				  DAO_QuanAo dao = new DAO_QuanAo();
				  QuanAo qa = dao.getQuanAoByMa(txtMaQuanAo.getText());
				  String duongDanHinhAnh = qa.getHinhAnh();
				  ImageIcon icon = new ImageIcon(duongDanHinhAnh);
				  lblHinhAnh.setIcon(icon);
	 			}
	 		});
		
		//Tạo panel chứa các ô để nhập dữ liệu
		JPanel pnlThongTinQuanAo = new JPanel();
		pnlThongTinQuanAo.setBounds(63, 48, 929, 323);
		add(pnlThongTinQuanAo);
		pnlThongTinQuanAo.setLayout(null);
		
		JLabel lblMaQuanAo = new JLabel("Mã quần áo ");
		lblMaQuanAo.setBounds(10, 75, 118, 25);
		pnlThongTinQuanAo.add(lblMaQuanAo);
		lblMaQuanAo.setFont(new Font("Tahoma", Font.ITALIC, 20));
		
		txtMaQuanAo = new JTextField();
		txtMaQuanAo.setFont(new Font("Arial", Font.BOLD, 16));
		txtMaQuanAo.setColumns(10);
		txtMaQuanAo.setBounds(145, 63, 216, 37);
		txtMaQuanAo.setEditable(false);
		pnlThongTinQuanAo.add(txtMaQuanAo);
		
		txtTenQuanAo = new JTextField();
		txtTenQuanAo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTenQuanAo.setBounds(145, 134, 216, 37);
		pnlThongTinQuanAo.add(txtTenQuanAo);
		txtTenQuanAo.setColumns(10);
		
		JLabel lblDonGia = new JLabel("Giá");
		lblDonGia.setBounds(10, 197, 82, 37);
		pnlThongTinQuanAo.add(lblDonGia);
		lblDonGia.setFont(new Font("Tahoma", Font.ITALIC, 20));
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDonGia.setBounds(145, 201, 216, 37);
		pnlThongTinQuanAo.add(txtDonGia);
		txtDonGia.setColumns(10);
		
		JLabel lblSoLuong = new JLabel("Số lượng ");
		lblSoLuong.setBounds(10, 264, 90, 37);
		pnlThongTinQuanAo.add(lblSoLuong);
		lblSoLuong.setFont(new Font("Tahoma", Font.ITALIC, 20));
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSoLuong.setBounds(145, 268, 216, 37);
		pnlThongTinQuanAo.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JLabel lblLoaiQuanAo = new JLabel("Loại quần áo ");
		lblLoaiQuanAo.setBounds(488, 69, 127, 37);
		pnlThongTinQuanAo.add(lblLoaiQuanAo);
		lblLoaiQuanAo.setFont(new Font("Tahoma", Font.ITALIC, 20));
		
		cboLoaiQuanAo = new JComboBox<String>();
		cboLoaiQuanAo.setFont(new Font("Arial", Font.PLAIN, 16));
		cboLoaiQuanAo.setBounds(625, 75, 170, 33);
		pnlThongTinQuanAo.add(cboLoaiQuanAo);
		
		JLabel lblKichCo = new JLabel("Kích thước");
		lblKichCo.setBounds(488, 116, 124, 37);
		pnlThongTinQuanAo.add(lblKichCo);
		lblKichCo.setFont(new Font("Tahoma", Font.ITALIC, 20));
		
		cboKichCo = new JComboBox();
		cboKichCo.setFont(new Font("Arial", Font.PLAIN, 16));
		cboKichCo.setModel(new DefaultComboBoxModel(new String[] {"Không", "S", "M", "L", "XL", "2XL", "3XL"}));
		cboKichCo.setBounds(625, 118, 170, 33);
		pnlThongTinQuanAo.add(cboKichCo);
		
		JLabel lblNCC = new JLabel("Nhà cung cấp");
		lblNCC.setBounds(488, 163, 141, 37);
		pnlThongTinQuanAo.add(lblNCC);
		lblNCC.setFont(new Font("Tahoma", Font.ITALIC, 20));
		
		cboNCC = new JComboBox<String>();
		cboNCC.setFont(new Font("Arial", Font.PLAIN, 16));
		cboNCC.setBounds(625, 161, 170, 33);
		pnlThongTinQuanAo.add(cboNCC);
		
		//Các nút chức năng
		JButton btnThem = new JButton("THÊM");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (validData()) {
		            txtMaQuanAo.setText(taoMa());
		            themQuanAo();
		            timKiemQuanAo.updateLaiComboMaQuanAo();
		            xoaTrang();
		        }
		       

			}
		});
		btnThem.setBounds(396, 268, 113, 37);
		pnlThongTinQuanAo.add(btnThem);
		btnThem.setForeground(new Color(60, 179, 113));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnXoa = new JButton("XÓA");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						xoaQuanAo();
						xoaTrang();
				
			}
		});
		btnXoa.setBounds(516, 268, 113, 37);
		pnlThongTinQuanAo.add(btnXoa);
		btnXoa.setForeground(new Color(60, 179, 113));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnSua = new JButton("SỬA");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblQuanAo.getSelectedRow();
		        if (row >= 0) {
		            if (validData()) {
		            	int n = JOptionPane.showConfirmDialog(null, "Chắc chắn sửa ?","Cảnh báo", JOptionPane.YES_NO_OPTION);
						if(n == JOptionPane.YES_OPTION) {
							suaQuanAo();
						}else return;
		            }
		        }
		       
		    }

			});
		btnSua.setBounds(642, 268, 113, 37);
		pnlThongTinQuanAo.add(btnSua);
		btnSua.setForeground(new Color(60, 179, 113));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnXoaTrang = new JButton("XÓA TRẮNG");
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnXoaTrang.setBounds(765, 268, 152, 37);
		pnlThongTinQuanAo.add(btnXoaTrang);
		btnXoaTrang.setForeground(new Color(60, 179, 113));
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblTimKiemQuanAo = new JLabel("Tìm kiếm theo tên");
		lblTimKiemQuanAo.setBounds(230, 21, 188, 25);
		pnlThongTinQuanAo.add(lblTimKiemQuanAo);
		lblTimKiemQuanAo.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(417, 19, 204, 33);
		pnlThongTinQuanAo.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimkiem = new JButton("");
		btnTimkiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemQuanAo();
			}
		});
		btnTimkiem.setSelectedIcon(new ImageIcon(GUI_CapNhatQuanAo.class.getResource("/Image/icon_TimKiem.png")));
		btnTimkiem.setBounds(632, 18, 46, 33);
		pnlThongTinQuanAo.add(btnTimkiem);
		btnTimkiem.setIcon(new ImageIcon(GUI_CapNhatQuanAo.class.getResource("/Image/icon_TimKiem.png")));
		
		JLabel lblTenQuanAo = new JLabel("Tên quần áo ");
		lblTenQuanAo.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblTenQuanAo.setBounds(10, 136, 118, 25);
		pnlThongTinQuanAo.add(lblTenQuanAo);
		
		JLabel lblTaiLai = new JLabel("");
		lblTaiLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modelThongTinQuanAo.setRowCount(0);
				updateData();
			}
		});
		lblTaiLai.setIcon(new ImageIcon(GUI_CapNhatQuanAo.class.getResource("/Image/refresh.png")));
		lblTaiLai.setBounds(905, 0, 24, 25);
		pnlThongTinQuanAo.add(lblTaiLai);
		
		JLabel lblKhuyenMai = new JLabel("Khuyến mãi:");
		lblKhuyenMai.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblKhuyenMai.setBounds(723, 19, 127, 37);
		pnlThongTinQuanAo.add(lblKhuyenMai);
		
		lblTiLeKhuyenMai = new JLabel("Không");
		lblTiLeKhuyenMai.setForeground(new Color(255, 0, 0));
		lblTiLeKhuyenMai.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblTiLeKhuyenMai.setBounds(847, 28, 60, 23);
		pnlThongTinQuanAo.add(lblTiLeKhuyenMai);
		
		JButton btnChonAnh = new JButton("CHỌN ẢNH");
		btnChonAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chonAnh();
			}
		});
		btnChonAnh.setForeground(new Color(60, 179, 113));
		btnChonAnh.setFont(new Font("Arial", Font.PLAIN, 14));
		btnChonAnh.setBounds(10, 10, 118, 36);
		pnlThongTinQuanAo.add(btnChonAnh);
		lblHinhAnh = new JLabel("");
		lblHinhAnh.setBounds(1002, 62, 245, 292);
		add(lblHinhAnh);
		
		lblNgayNhap = new JLabel("Ngày nhập");
		lblNgayNhap.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNgayNhap.setBounds(488, 213, 141, 37);
		pnlThongTinQuanAo.add(lblNgayNhap);
		
		txtNgayNhap = new JTextField();
		txtNgayNhap.setEditable(false);
		txtNgayNhap.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNgayNhap.setBounds(625, 215, 170, 33);
		pnlThongTinQuanAo.add(txtNgayNhap);
		txtNgayNhap.setColumns(10);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());
		txtNgayNhap.setText(currentDate);
	
		
		JLabel lblDanhSachQuanAo = new JLabel("Danh sách quần áo");
		lblDanhSachQuanAo.setForeground(new Color(135, 206, 235));
		lblDanhSachQuanAo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDanhSachQuanAo.setBounds(69, 393, 199, 37);
		add(lblDanhSachQuanAo);
		

		
		updateComboLoaiQuanAo();
		updateComboNhaCungCap();
		updateData();
		updateLaiSoLuong();
		updateLaiComboNhaCungCap();
		updateLaiMaKM();
		kiemTraThoiHanKM();
	}
	
	//Các hàm cập nhật
	public void updateComboLoaiQuanAo() {
		DAO_LoaiQuanAo dao = new DAO_LoaiQuanAo();
		for(LoaiQuanAo loai : dao.getAllLoaiQuanAo()) {
			cboLoaiQuanAo.addItem(loai.getTenLoai());
		}
	}
	
	public void updateComboNhaCungCap() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap();
		for(NhaCungCap loai : dao.getAllNhaCungCap()) {
			cboNCC.addItem(loai.getTenNCC());
		}
	}
		
	public void updateLaiComboNhaCungCap() {
		cboNCC.removeAllItems();
		DAO_NhaCungCap dao = new DAO_NhaCungCap();
		for(NhaCungCap loai : dao.getAllNhaCungCap()) {
			cboNCC.addItem(loai.getTenNCC());
	}
}
	
	public void updateLaiComboLoaiQuanAo() {
		cboLoaiQuanAo.removeAllItems();
		DAO_LoaiQuanAo dao = new DAO_LoaiQuanAo();
		for(LoaiQuanAo loai : dao.getAllLoaiQuanAo()) {
			cboLoaiQuanAo.addItem(loai.getTenLoai());
		}
	}
	
	public void updateData() {
		DAO_QuanAo dao= new DAO_QuanAo();
		DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<QuanAo> list = dao.getAllQuanAo();
		for(QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo();
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongCu(),quanAo.getSoLuongHienTai(),dateFormat.format(quanAo.getNgayNhap()),quanAo.getKm().getMaKM(),chuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);
		}
		
	}
	public void updateLaiSoLuong() {
		DAO_QuanAo dao= new DAO_QuanAo();
		DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<QuanAo> list = dao.getAllQuanAo();
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for(QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo();
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongCu(),quanAo.getSoLuongHienTai(),dateFormat.format(quanAo.getNgayNhap()),quanAo.getKm().getMaKM(),chuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);
		}
		
	}
	public void updateLaiMaKM() {
		DAO_QuanAo dao= new DAO_QuanAo();
		DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<QuanAo> list = dao.getAllQuanAo();
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for(QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo();
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongCu(),quanAo.getSoLuongHienTai(),dateFormat.format(quanAo.getNgayNhap()),quanAo.getKm().getMaKM(),chuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);
		}
		
	}
	
	//Các hàm thêm
	public void themQuanAo() {
		DAO_QuanAo dao = new DAO_QuanAo();
		DAO_NhaCungCap daoNCC = new DAO_NhaCungCap();
		DAO_LoaiQuanAo daoLoai = new DAO_LoaiQuanAo();
		String maQuanAo = txtMaQuanAo.getText().trim();
		String tenQuanAo = txtTenQuanAo.getText().trim();
		String tenNhaCC = cboNCC.getSelectedItem().toString();
		String loaiQuanAo = cboLoaiQuanAo.getSelectedItem().toString();
		String kichThuoc = cboKichCo.getSelectedItem().toString();
		String soLuongTon = txtSoLuong.getText().trim();
		String gia = txtDonGia.getText().trim().replace(",", "");
		String ngayNhap = txtNgayNhap.getText().trim();
		NhaCungCap ncc = daoNCC.getNCCByTen(tenNhaCC);
		LoaiQuanAo loai = daoLoai.getLoaiQuanAoByTen(loaiQuanAo);
		
		//Kiểm tra nếu khác rỗng
		if (!ngayNhap.isEmpty()) {
            try {
            	//Chuyển định dạng
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsedDate = dateFormat.parse(ngayNhap);
                ngay = new Date(parsedDate.getTime());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
		//Lấy hình ảnh từ lblHinhAnh và lấy đường dần
		Icon icon = lblHinhAnh.getIcon();
	    String duongDanHinhAnh = "";
	    
	    //Kiểm tra có thuộc lớp ImageIcon hay không
	    if (icon instanceof ImageIcon) {
	        
	        ImageIcon imageIcon = (ImageIcon) icon;
	        duongDanHinhAnh = imageIcon.getDescription(); //Lấy đường dần
	    }

		String khuyenMai = "Không";
		KhuyenMai km = new KhuyenMai(khuyenMai);
		QuanAo quanAo = new QuanAo(maQuanAo,tenQuanAo,new NhaCungCap(ncc.getMaNCC()),new LoaiQuanAo(loai.getMaLoai()) ,kichThuoc,0,Integer.parseInt(soLuongTon),ngay,km , Float.parseFloat(gia),duongDanHinhAnh); 
		if(dao.create(quanAo)) {
			String [] data = {maQuanAo,tenQuanAo,tenNhaCC,loaiQuanAo,kichThuoc,"0",soLuongTon,ngayNhap,khuyenMai,txtDonGia.getText()};
			modelThongTinQuanAo.addRow(data);
			modelThongTinQuanAo.fireTableDataChanged();
		}
	}
	
	
	//Các hàm xóa
	public void xoaTrang() {
		txtMaQuanAo.setText("");
		txtTenQuanAo.setText("");
		txtSoLuong.setText("");
		txtDonGia.setText("");
		cboKichCo.setSelectedItem("Không");
		cboLoaiQuanAo.setSelectedItem("Áo");
		lblHinhAnh.setIcon(null);
	}
	
	public void xoaQuanAo() {
		DAO_QuanAo dao = new DAO_QuanAo();
		int row = tblQuanAo.getSelectedRow();
		if(row >=0) {
			int n = JOptionPane.showConfirmDialog(null, "Chắc chắn xóa ? ", "Cảnh báo", JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION) {
				String maQuanAo =  tblQuanAo.getValueAt(row, 0).toString();
				if(dao.delete(maQuanAo)) {
					modelThongTinQuanAo.removeRow(row);
					for (int i = row; i < modelThongTinQuanAo.getRowCount(); i++) {
						String MaQA = modelThongTinQuanAo.getValueAt(i, 0).toString();
			            int maMoi = Integer.parseInt(MaQA.substring(3)) - deletedRowCount;
			            String newMaQA = "QA" + String.format("%03d", maMoi);
			            modelThongTinQuanAo.setValueAt(newMaQA, i, 0);
					}
			   
				dao.updateAllMaQuanAo(deletedRowCount, maQuanAo);
				}	
		
			}	
		}else {
			JOptionPane.showMessageDialog(null,"Vui lòng chọn quần áo cần xóa !");
		}
	}

	//Các hàm sửa
	 public void suaQuanAo() { 
		  DAO_QuanAo dao = new DAO_QuanAo();
		  DAO_NhaCungCap daoNCC = new DAO_NhaCungCap();
		  DAO_LoaiQuanAo daoLoai = new DAO_LoaiQuanAo();
		  int row = tblQuanAo.getSelectedRow();
		  String maQuanAo  = txtMaQuanAo.getText().trim();
		  String tenQuanAo = txtTenQuanAo.getText().trim(); 
		  String tenNhaCC = cboNCC.getSelectedItem().toString(); 
		  String loaiQuanAo = cboLoaiQuanAo.getSelectedItem().toString(); 
		  String kichThuoc = cboKichCo.getSelectedItem().toString();
		  String soLuongCu;
		  String soLuongSua = txtSoLuong.getText().trim();
		  String soLuongHienTai = tblQuanAo.getValueAt(row, 6).toString();
		  
		  if(!soLuongSua.equals(soLuongHienTai)) {
			  soLuongCu = soLuongHienTai;
		  }else {
			  soLuongCu= tblQuanAo.getValueAt(row, 5).toString();
		  }
		  String gia = txtDonGia.getText().trim().replace(",", "");
		  String KhuyenMai = lblTiLeKhuyenMai.getText();
		  KhuyenMai km = new KhuyenMai(KhuyenMai);
		  Icon icon = lblHinhAnh.getIcon();
		  String duongDanHinhAnh = "";
		  NhaCungCap ncc = daoNCC.getNCCByTen(tenNhaCC);
		  LoaiQuanAo loai = daoLoai.getLoaiQuanAoByTen(loaiQuanAo);
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	      String currentDate = sdf.format(new Date());
	      txtNgayNhap.setText(currentDate);
		    if (icon instanceof ImageIcon) {
		        ImageIcon imageIcon = (ImageIcon) icon;
		        duongDanHinhAnh = imageIcon.getDescription();
		    }
		    
	        try {	             
	             java.util.Date parsedDate = sdf.parse(txtNgayNhap.getText());
                 ngay = new Date(parsedDate.getTime());
	        } catch (ParseException e1) {
	                e1.printStackTrace();
	        }
	        
		  QuanAo quanAo = new QuanAo(maQuanAo,tenQuanAo,new NhaCungCap(ncc.getMaNCC()),new LoaiQuanAo(loai.getMaLoai())
				  ,kichThuoc,Integer.parseInt(soLuongCu),Integer.parseInt(soLuongHienTai),ngay,km ,Float.parseFloat(gia),duongDanHinhAnh);
		  if(dao.update(quanAo)) {
			  JOptionPane.showMessageDialog(this, "Sửa thành công"); 
			  String [] data = {maQuanAo,tenQuanAo,tenNhaCC,loaiQuanAo,kichThuoc,soLuongCu,soLuongHienTai,txtNgayNhap.getText(),KhuyenMai,txtDonGia.getText()};
			  modelThongTinQuanAo.addRow(data);
			  modelThongTinQuanAo.removeRow(row); 
		  }
	  
	  }
	
	//Hàm tìm kiếm theo tên
	public void timKiemQuanAo() {
		DAO_QuanAo dao = new DAO_QuanAo();
		List<QuanAo> list = dao.timTheoTen(txtTimKiem.getText());
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			Object[] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),quanAo.getNCC().getTenNCC(),quanAo.getLoaiQuanAo().getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongCu(),quanAo.getSoLuongHienTai(),quanAo.getNgayNhap(),quanAo.getKm().getMaKM(),chuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

		}

	}
	
	//Hàm tạo mã quần áo tự động
	public String taoMa() {
		DAO_QuanAo dao = new DAO_QuanAo();
		int n = dao.getAllQuanAo().size();
		if(n<9) {
			do {
				 n=n+1;
				
				ma = "QA00" + String.valueOf(n);
				list = dao.getAllQuanAo();
			} while (list.contains(ma));
		
		}else if(n<99) {
			do {
				 n=n+1;
				
				ma = "QA0" + String.valueOf(n);
				list = dao.getAllQuanAo();
			} while (list.contains(ma));
		}else if(n<999) {
			do {
				 n=n+1;
				
				ma = "QA" + String.valueOf(n);
				list = dao.getAllQuanAo();
			} while (list.contains(ma));
		}
		return ma;
	}
	
	//Hàm kiểm tra dữ liệu
	private boolean validData() {
		String tenquanao = txtTenQuanAo.getText().trim();
		String soluong = txtSoLuong.getText().trim();
		String gia = txtDonGia.getText().trim();

		if (tenquanao.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên quần áo không được rỗng!");
			txtTenQuanAo.selectAll();
			txtTenQuanAo.requestFocus();
			return false;
		}else if (tenquanao.matches(".*\\d.*")) {
	        JOptionPane.showMessageDialog(this, "Tên quần áo không chứa kí tự số");
	        txtTenQuanAo.requestFocus();
	        txtTenQuanAo.selectAll();
	        return false;
	    } 
		else {

			if ((tenquanao.matches(
					"^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Tên quần áo gồm chữ cái, có thể chứa khoảng trắng");
				txtTenQuanAo.requestFocus();
				txtTenQuanAo.selectAll();
				return false;
			}
		}

		if (soluong.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số lượng không được rỗng!");
			txtSoLuong.selectAll();
			txtSoLuong.requestFocus();
			return false;
		} else if (!soluong.matches("^[0-9]+")) {
	        JOptionPane.showMessageDialog(this, "Số lượng chỉ chứa kí tự số");
	        txtSoLuong.requestFocus();
	        txtSoLuong.selectAll();
	        return false;
	    }

		
		if (gia.trim().equals("")) {
			JOptionPane.showMessageDialog(this, " Giá quần áo không được rỗng!");
			txtDonGia.selectAll();
			txtDonGia.requestFocus();
			return false;
		} else if (gia.matches("^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]")) {
	        JOptionPane.showMessageDialog(this, "Giá chỉ chứa kí tự số");
	        txtDonGia.requestFocus();
	        txtDonGia.selectAll();
	        return false;
	    }

		return true;
	}
	
	
	public void kiemTraThoiHanKM() {
		 DAO_KhuyenMai daoKM = new DAO_KhuyenMai();
		 List<String> danhSachMaKM = daoKM.getAllMaKhuyenMai();
		 int rowCount = modelThongTinQuanAo.getRowCount();
		 for (int i = 0; i < rowCount; i++) {
		        String maKhuyenMaiTrongBang =  tblQuanAo.getValueAt(i, 6).toString(); 
		        if (danhSachMaKM.contains(maKhuyenMaiTrongBang)) {
		            
		            Date ngayKetThuc = daoKM.getNgayKetThucByMaKhuyenMai(maKhuyenMaiTrongBang); 
		            Date ngayHienTai = new Date(); 
		            if (ngayKetThuc != null && ngayKetThuc.before(ngayHienTai)) {
		                tblQuanAo.setValueAt("Không", i, 6);
		                DAO_QuanAo dao_QuanAo = new DAO_QuanAo();
		                dao_QuanAo.updateMaKM(tblQuanAo.getValueAt(i, 0).toString(), "Không");
		                DAO_ChiTietKhuyenMai dao_ctkm = new DAO_ChiTietKhuyenMai();
		                dao_ctkm.delete(tblQuanAo.getValueAt(i, 0).toString(), maKhuyenMaiTrongBang);
		               
		            }
		        }
		 }
	}
	private void chonAnh() {
        JFileChooser fileChooser = new JFileChooser();
        
        // Chỉ chọn những file có đuôi sau
        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh files", "jpg", "jpeg", "png", "gif"));
        
        //Hiển thị khung để chọn file và lưu kết quả
        int n = fileChooser.showOpenDialog(this);
        
        //Kiểm tra có chọn hay chưa
        if (n == JFileChooser.APPROVE_OPTION) {
        	
        	//Lấy file đã chọn
            File fileChon = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(fileChon.getAbsolutePath());
            lblHinhAnh.setIcon(imageIcon);
        }
    }
}

