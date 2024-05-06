package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_ChiTietDonDatHang;
import DAO.DAO_ChiTietHoaDon;
import DAO.DAO_ChuyenDoi;
import DAO.DAO_DonHangDatTruoc;
import DAO.DAO_HoaDon;
import DAO.DAO_KhachHang;
import DAO.DAO_LoaiQuanAo;
import DAO.DAO_NhaCungCap;
import DAO.DAO_NhanVien;
import DAO.DAO_QuanAo;
import connect.ConnectDB;
import entity.ChiTietDonDatHang;
import entity.ChiTietHoaDon;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.QuanAo;

import javax.swing.JComboBox;
import java.awt.Cursor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class GUI_DatHang extends JPanel {
	private JTextField txtMaQuanAo,txtSoLuong,txtTenKhachHang,txtNhapSoDienThoai,txtTenNhanVien,txtMaKH,txtDiaChi,txtNgayMua,txtSoDienThoai,txtNamSinh;
	private DefaultTableModel modelThongTinPhieuDat, modelDsQuanAo;
	private JTable tblThongTinDonHang,tblDsQuanAo;
	private JComboBox cboGioiTinh;
	private JLabel lblTien,lblHinhAnh;
	private Date ngay;
	private String ma,tenNhanVien;
	private List<HoaDon> list;
	private List<KhachHang> kh;
	private List<DonDatHang> dh;
	private JButton btnTimQuanAo, btnThemKHMoi,btnTaoMoi,btnThem,btnXoa,btnTimKH,btnSua ;
	private JTextField txtTienKhachTra;
	private JTextField txtTienTraLai;
	private GUI_CapNhatQuanAo guiCapNhatQuanAo;
	private GUI_CapNhatNhaCungCap guiNhaCungCap;
	private GUI_KhuyenMai km;
	private GUI_CapNhatKhachHang khachHang;
	private DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
	private JTextField txtMaChiTiet;
	private JTextField txtTenQuanAo;
	private JTextField txtLoaiQuanAo;
	private JTextField txtNCC;
	private JTextField txtKichThuoc;
	private JTextField txtSoLuongCT;
	private JTextField txtGia;
	 public GUI_DatHang(GUI_CapNhatQuanAo guiCapNhatQuanAo, GUI_KhuyenMai km,GUI_CapNhatKhachHang khachHang) {
			  	this.guiCapNhatQuanAo = guiCapNhatQuanAo;
			  	this.km = km;
			  	this.khachHang = khachHang;
		 		//ConnectDB
		 		try {
					ConnectDB.getInstance().connect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	setBackground(new Color(0, 64, 64));
		    	setLayout(null);
		    	
		    	DAO_ChuyenDoi ChuyenDoi = new DAO_ChuyenDoi();
		    	DAO_HoaDon hoaDon = new DAO_HoaDon();
		    	
				
				
				
				String [] tieude={"Mã quần áo", "Tên quần áo ", "Số lượng", "Đơn giá","Khuyến mãi", "Thành tiền"};
				modelThongTinPhieuDat = new DefaultTableModel(tieude,0);
				
				JScrollPane scrThongTinDonHang = new JScrollPane();
				scrThongTinDonHang.setBounds(506, 295, 764, 206);
				add(scrThongTinDonHang);
				scrThongTinDonHang.setViewportView(tblThongTinDonHang = new  JTable(modelThongTinPhieuDat));
				tblThongTinDonHang.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = tblThongTinDonHang.getSelectedRow();
						txtSoLuong.setText(tblThongTinDonHang.getValueAt(row, 2).toString());
						String maQuanAo = tblThongTinDonHang.getValueAt(row, 0).toString();
						for (int i = 0; i < tblDsQuanAo.getRowCount(); i++) {
				            String maQuanAoTblDsQuanAo = tblDsQuanAo.getValueAt(i, 0).toString();
				            if (maQuanAo.equals(maQuanAoTblDsQuanAo)) {
				                
				                tblDsQuanAo.setRowSelectionInterval(i, i);
				                break;
				            }
				        }
					}
				});
				scrThongTinDonHang.setViewportView(tblThongTinDonHang);
				
				btnThem = new JButton("THÊM");
				btnThem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int row = tblDsQuanAo.getSelectedRow();
						if(row >=0 ) {
							if(txtSoLuong.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng !");
							}else {
			                  if(kiemTraSoLuongCon()) {
								updateDSDonMua();
								capNhatSoLuongSauKhiThem();
								txtSoLuong.setText("");
								lblTien.setText(TinhTongTien(tblThongTinDonHang)+ " Đồng");
								btnXoa.setEnabled(true);
								btnSua.setEnabled(true);
								
			                  }
							}
						}else {
							JOptionPane.showMessageDialog(null, "Vui lòng chọn quần áo cần mua !");
						}
					}
				});
				btnThem.setFont(new Font("Arial", Font.BOLD, 20));
				btnThem.setBounds(159, 358, 108, 26);
				add(btnThem);
				
				btnXoa = new JButton("XÓA");
				btnXoa.setEnabled(false);
				btnXoa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int row = tblThongTinDonHang.getSelectedRow();
						if(row >=0 ) {
							capNhatSoLuongSauKhiXoa();
							xoaQuanAo();
							lblTien.setText(TinhTongTien(tblThongTinDonHang)+ " Đồng");
							tinhTienTraLai();
							if(modelThongTinPhieuDat.getRowCount() == 0 ) {
								btnXoa.setEnabled(false);
								btnSua.setEnabled(false);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Vui lòng chọn quần áo cần xóa khỏi đơn !");
						}
					}
				});
				btnXoa.setFont(new Font("Arial", Font.BOLD, 20));
				btnXoa.setBounds(277, 358, 108, 26);
				add(btnXoa);
				
				JPanel pnlChiTietQuanAo = new JPanel();
				pnlChiTietQuanAo.setForeground(new Color(255, 255, 255));
				pnlChiTietQuanAo.setBackground(new Color(0, 128, 64));
				pnlChiTietQuanAo.setBounds(10, 410, 490, 292);
				pnlChiTietQuanAo.setLayout(null);
				pnlChiTietQuanAo.setVisible(false);;
				add(pnlChiTietQuanAo);
				
				lblHinhAnh = new JLabel("");
				lblHinhAnh.setBounds(0, 0, 245, 292);
				pnlChiTietQuanAo.add(lblHinhAnh);
				
				JLabel lblMaQuanAo = new JLabel("Mã quần áo:");
				lblMaQuanAo.setForeground(new Color(255, 255, 0));
				lblMaQuanAo.setFont(new Font("Arial", Font.BOLD, 15));
				lblMaQuanAo.setBounds(255, 10, 97, 24);
				pnlChiTietQuanAo.add(lblMaQuanAo);
				
				JLabel lblTenQuanAo = new JLabel("Tên quần áo:");
				lblTenQuanAo.setForeground(new Color(255, 255, 0));
				lblTenQuanAo.setFont(new Font("Arial", Font.BOLD, 15));
				lblTenQuanAo.setBounds(255, 50, 108, 24);
				pnlChiTietQuanAo.add(lblTenQuanAo);
				
				JLabel lblLoaiQuanAo = new JLabel("Loại quần áo:");
				lblLoaiQuanAo.setForeground(new Color(255, 255, 0));
				lblLoaiQuanAo.setFont(new Font("Arial", Font.BOLD, 15));
				lblLoaiQuanAo.setBounds(255, 90, 108, 24);
				pnlChiTietQuanAo.add(lblLoaiQuanAo);
				
				JLabel lblNhaCungCap = new JLabel("Nhà cung cấp:");
				lblNhaCungCap.setForeground(new Color(255, 255, 0));
				lblNhaCungCap.setFont(new Font("Arial", Font.BOLD, 15));
				lblNhaCungCap.setBounds(255, 130, 108, 24);
				pnlChiTietQuanAo.add(lblNhaCungCap);
				
				JLabel lblKichThuoc = new JLabel("Kích thước:");
				lblKichThuoc.setForeground(new Color(255, 255, 0));
				lblKichThuoc.setFont(new Font("Arial", Font.BOLD, 15));
				lblKichThuoc.setBounds(255, 170, 97, 24);
				pnlChiTietQuanAo.add(lblKichThuoc);
				
				JLabel lblSLChiTiet = new JLabel("Số Lượng:");
				lblSLChiTiet.setForeground(new Color(255, 255, 0));
				lblSLChiTiet.setFont(new Font("Arial", Font.BOLD, 15));
				lblSLChiTiet.setBounds(255, 210, 84, 24);
				pnlChiTietQuanAo.add(lblSLChiTiet);
				
				JLabel lblGia = new JLabel("Giá:");
				lblGia.setForeground(new Color(255, 255, 0));
				lblGia.setFont(new Font("Arial", Font.BOLD, 15));
				lblGia.setBounds(255, 250, 84, 24);
				pnlChiTietQuanAo.add(lblGia);
				
				txtMaChiTiet = new JTextField();
				txtMaChiTiet.setForeground(new Color(255, 255, 255));
				txtMaChiTiet.setBackground(new Color(0, 128, 64));
				txtMaChiTiet.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
				txtMaChiTiet.setEditable(false);
				txtMaChiTiet.setColumns(10);
				txtMaChiTiet.setBorder(null);
				txtMaChiTiet.setBounds(353, 13, 122, 20);
				pnlChiTietQuanAo.add(txtMaChiTiet);
				
				txtTenQuanAo = new JTextField();
				txtTenQuanAo.setForeground(new Color(255, 255, 255));
				txtTenQuanAo.setBackground(new Color(0, 128, 64));
				txtTenQuanAo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
				txtTenQuanAo.setEditable(false);
				txtTenQuanAo.setColumns(10);
				txtTenQuanAo.setBorder(null);
				txtTenQuanAo.setBounds(355, 53, 135, 20);
				pnlChiTietQuanAo.add(txtTenQuanAo);
				
				txtLoaiQuanAo = new JTextField();
				txtLoaiQuanAo.setForeground(new Color(255, 255, 255));
				txtLoaiQuanAo.setBackground(new Color(0, 128, 64));
				txtLoaiQuanAo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
				txtLoaiQuanAo.setEditable(false);
				txtLoaiQuanAo.setColumns(10);
				txtLoaiQuanAo.setBorder(null);
				txtLoaiQuanAo.setBounds(359, 93, 122, 20);
				pnlChiTietQuanAo.add(txtLoaiQuanAo);
				
				txtNCC = new JTextField();
				txtNCC.setForeground(new Color(255, 255, 255));
				txtNCC.setBackground(new Color(0, 128, 64));
				txtNCC.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
				txtNCC.setEditable(false);
				txtNCC.setColumns(10);
				txtNCC.setBorder(null);
				txtNCC.setBounds(366, 133, 124, 20);
				pnlChiTietQuanAo.add(txtNCC);
				
				txtKichThuoc = new JTextField();
				txtKichThuoc.setForeground(new Color(255, 255, 255));
				txtKichThuoc.setBackground(new Color(0, 128, 64));
				txtKichThuoc.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
				txtKichThuoc.setEditable(false);
				txtKichThuoc.setColumns(10);
				txtKichThuoc.setBorder(null);
				txtKichThuoc.setBounds(353, 173, 122, 20);
				pnlChiTietQuanAo.add(txtKichThuoc);
				
				txtSoLuongCT = new JTextField();
				txtSoLuongCT.setForeground(new Color(255, 255, 255));
				txtSoLuongCT.setBackground(new Color(0, 128, 64));
				txtSoLuongCT.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
				txtSoLuongCT.setEditable(false);
				txtSoLuongCT.setColumns(10);
				txtSoLuongCT.setBorder(null);
				txtSoLuongCT.setBounds(346, 213, 122, 20);
				pnlChiTietQuanAo.add(txtSoLuongCT);
				
				txtGia = new JTextField();
				txtGia.setForeground(new Color(255, 255, 255));
				txtGia.setBackground(new Color(0, 128, 64));
				txtGia.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
				txtGia.setEditable(false);
				txtGia.setColumns(10);
				txtGia.setBorder(null);
				txtGia.setBounds(290, 252, 122, 20);
				pnlChiTietQuanAo.add(txtGia);
				
				
				
				JScrollPane scrDsQuanAo = new JScrollPane();
				scrDsQuanAo.setBounds(10, 53, 490, 300);
				add(scrDsQuanAo);
				
				String [] tieuDeDsQuanAo={"Mã quần áo", "Tên quần áo ", "Tên nhà cung cấp", "Số lượng tồn","Mã KM","Giá"};
				modelDsQuanAo = new DefaultTableModel(tieuDeDsQuanAo,0);
				scrDsQuanAo.setViewportView(tblDsQuanAo = new  JTable(modelDsQuanAo));
				tblDsQuanAo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						 pnlChiTietQuanAo.setVisible(true);
						 int row = tblDsQuanAo.getSelectedRow();
						 DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi(); 
						  txtMaQuanAo.setText(tblDsQuanAo.getValueAt(row, 0).toString());
						  DAO_QuanAo dao = new DAO_QuanAo();
						  QuanAo qa = dao.getQuanAoByMa(txtMaQuanAo.getText());
						  String duongDanHinhAnh = qa.getHinhAnh();
						  ImageIcon icon = new ImageIcon(duongDanHinhAnh);
						  lblHinhAnh.setIcon(icon);
						  txtMaChiTiet.setText(qa.getMaQuanAo());
						  txtTenQuanAo.setText(qa.getTenQuanAo());
						  txtGia.setText(chuyenDoi.DinhDangTien(qa.getGia()));
						  String maLoai = qa.getLoaiQuanAo().getMaLoai();
						  DAO_LoaiQuanAo dao_loai = new DAO_LoaiQuanAo();
						  LoaiQuanAo loai = dao_loai.getLoaiQuanAoByMa(maLoai);
						  txtLoaiQuanAo.setText(loai.getTenLoai());
						  
						  String maNCC = qa.getNCC().getMaNCC();
						  DAO_NhaCungCap dao_ncc = new DAO_NhaCungCap();
						  NhaCungCap ncc = dao_ncc.getNCCByMa(maNCC);
						  txtNCC.setText(ncc.getTenNCC());
						  
						  txtKichThuoc.setText(qa.getKinhThuoc());
						  txtSoLuongCT.setText(String.valueOf(qa.getSoLuongHienTai()));
						  
					}
				});
				scrDsQuanAo.setViewportView(tblDsQuanAo);
				
				JLabel lblSoLuong = new JLabel("Số lượng");
				lblSoLuong.setForeground(new Color(255, 0, 0));
				lblSoLuong.setFont(new Font("Arial", Font.BOLD, 16));
				lblSoLuong.setBounds(10, 354, 83, 27);
				add(lblSoLuong);
				
				txtSoLuong = new JTextField();
				txtSoLuong.setBounds(86, 358, 63, 19);
				add(txtSoLuong);
				txtSoLuong.setColumns(10);
				
				JLabel lblTimKiemQuanAo = new JLabel("Tìm kiếm quần áo");
				lblTimKiemQuanAo.setForeground(new Color(255, 0, 0));
				lblTimKiemQuanAo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				lblTimKiemQuanAo.setBounds(10, 10, 139, 33);
				add(lblTimKiemQuanAo);
				
				JLabel lblThanhToan = new JLabel("Thanh Toán");
				lblThanhToan.setForeground(Color.RED);
				lblThanhToan.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				lblThanhToan.setBounds(506, 500, 173, 33);
				add(lblThanhToan);
				
				JPanel pnlThanhToan = new JPanel();
				pnlThanhToan.setBounds(506, 530, 764, 172);
				add(pnlThanhToan);
				pnlThanhToan.setLayout(null);
				
				JLabel lblThanhTien = new JLabel("Thành tiền:");
				lblThanhTien.setForeground(Color.RED);
				lblThanhTien.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
				lblThanhTien.setBounds(10, 10, 112, 40);
				pnlThanhToan.add(lblThanhTien);
				
				JButton btnDatHang = new JButton("THANH TOÁN");
				btnDatHang.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(validDataThem()) {
							int n = 0;
						themHoaDon();
						btnTaoMoi.setEnabled(true);
						guiCapNhatQuanAo.updateLaiSoLuong();
						XuatHoaDon xuathoaDon = new XuatHoaDon(layMaHD(),txtTienKhachTra.getText().replace(",", "").replace("Đồng",""),txtTienTraLai.getText().replace(",", "").replace("Đồng",""));
						JOptionPane.showMessageDialog(null ,"Thanh toán thành công !");
						modelDsQuanAo.getDataVector().removeAllElements();
						updateDSQuanAo();
						String soDienThoai = txtSoDienThoai.getText();
						sendMessage("+84"+soDienThoai.substring(1));
						System.out.println("+84"+soDienThoai.substring(1));
						}
					}
				});
				btnDatHang.setBackground(new Color(255, 0, 0));
				btnDatHang.setFont(new Font("Arial", Font.BOLD, 20));
				btnDatHang.setBounds(424, 15, 185, 44);
				pnlThanhToan.add(btnDatHang);
				
				JButton btnDatHangTruoc = new JButton("Đặt hàng trước");
				btnDatHangTruoc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						datHangTruoc();
						JOptionPane.showMessageDialog(null, "Đã thêm vào danh sách đơn hàng đặt trước !");
						btnTaoMoi.setEnabled(true);
						guiCapNhatQuanAo.updateLaiSoLuong();
						String soDienThoai = txtSoDienThoai.getText();
						sendMessage1("+84"+soDienThoai.substring(1));
						System.out.println("+84"+soDienThoai.substring(1));
					}
				});
				btnDatHangTruoc.setBackground(new Color(255, 128, 0));
				btnDatHangTruoc.setFont(new Font("Arial", Font.BOLD, 20));
				btnDatHangTruoc.setBounds(424, 91, 185, 44);
				pnlThanhToan.add(btnDatHangTruoc);
				
				lblTien = new JLabel("0 Đồng");
				lblTien.setForeground(new Color(255, 0, 0));
				lblTien.setBackground(new Color(255, 0, 0));
				lblTien.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
				lblTien.setBounds(132, 15, 158, 30);
				pnlThanhToan.add(lblTien);
				
				JLabel lblTienKhachTra = new JLabel("Tiền khách trả:");
				lblTienKhachTra.setForeground(new Color(0, 128, 128));
				lblTienKhachTra.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
				lblTienKhachTra.setBounds(10, 65, 152, 40);
				pnlThanhToan.add(lblTienKhachTra);
				
				txtTienKhachTra = new JTextField();
				txtTienKhachTra.setForeground(new Color(64, 128, 128));
				txtTienKhachTra.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						tinhTienTraLai();
					}
				});
				txtTienKhachTra.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
				txtTienKhachTra.setBounds(160, 69, 96, 26);
				pnlThanhToan.add(txtTienKhachTra);
				txtTienKhachTra.setColumns(10);
				
				JLabel lblTienTraLai = new JLabel("Tiền trả lại:");
				lblTienTraLai.setForeground(new Color(0, 128, 128));
				lblTienTraLai.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
				lblTienTraLai.setBounds(10, 110, 152, 40);
				pnlThanhToan.add(lblTienTraLai);
				
				txtTienTraLai = new JTextField();
				txtTienTraLai.setForeground(new Color(64, 128, 128));
				txtTienTraLai.setBorder(null);
				txtTienTraLai.setEditable(false);
				txtTienTraLai.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
				txtTienTraLai.setColumns(10);
				txtTienTraLai.setBounds(126, 117, 164, 26);
				pnlThanhToan.add(txtTienTraLai);
				
				JLabel lblHoaDonMuaQuanAo = new JLabel("Hóa đơn mua quần áo");
				lblHoaDonMuaQuanAo.setForeground(Color.RED);
				lblHoaDonMuaQuanAo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				lblHoaDonMuaQuanAo.setBounds(506, 10, 173, 33);
				add(lblHoaDonMuaQuanAo);
				
				JPanel pnlThongTinHoaDon = new JPanel();
				pnlThongTinHoaDon.setBounds(507, 39, 760, 246);
				add(pnlThongTinHoaDon);
				pnlThongTinHoaDon.setLayout(null);
				
				JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
				lblTenKhachHang.setBounds(10, 44, 127, 24);
				pnlThongTinHoaDon.add(lblTenKhachHang);
				lblTenKhachHang.setFont(new Font("Arial", Font.BOLD, 16));
				
				txtTenKhachHang = new JTextField();
				txtTenKhachHang.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				txtTenKhachHang.setBorder(null);
				txtTenKhachHang.setBounds(147, 44, 139, 24);
				pnlThongTinHoaDon.add(txtTenKhachHang);
				txtTenKhachHang.setColumns(10);
				
				JLabel lblTenNhanVien = new JLabel("Tên nhân viên:");
				lblTenNhanVien.setFont(new Font("Arial", Font.BOLD, 16));
				lblTenNhanVien.setBounds(10, 10, 121, 24);
				pnlThongTinHoaDon.add(lblTenNhanVien);
				
				txtTenNhanVien = new JTextField();
				txtTenNhanVien.setBorder(null);
				txtTenNhanVien.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				txtTenNhanVien.setEditable(false);
				txtTenNhanVien.setColumns(10);
				txtTenNhanVien.setBounds(124, 10, 159, 24);
				txtTenNhanVien.setText(layTenNhanVien());
				pnlThongTinHoaDon.add(txtTenNhanVien);
				
				JLabel lblTimTheoSoDienThoai = new JLabel("Nhập số điện thoại để tìm");
				lblTimTheoSoDienThoai.setBounds(322, 10, 204, 24);
				pnlThongTinHoaDon.add(lblTimTheoSoDienThoai);
				lblTimTheoSoDienThoai.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				
				txtNhapSoDienThoai = new JTextField();
				txtNhapSoDienThoai.setBounds(525, 13, 159, 24);
				pnlThongTinHoaDon.add(txtNhapSoDienThoai);
				txtNhapSoDienThoai.setColumns(10);
				
				JLabel lblMaKH = new JLabel("Mã khách hàng:");
				lblMaKH.setFont(new Font("Arial", Font.BOLD, 16));
				lblMaKH.setBounds(10, 82, 175, 24);
				pnlThongTinHoaDon.add(lblMaKH);
				
				txtMaKH = new JTextField();
				txtMaKH.setBorder(null);
				txtMaKH.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				txtMaKH.setColumns(10);
				txtMaKH.setBounds(147, 82, 101, 24);
				pnlThongTinHoaDon.add(txtMaKH);
				
				txtDiaChi = new JTextField();
				txtDiaChi.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				txtDiaChi.setBackground(new Color(255, 255, 255));
				txtDiaChi.setBorder(null);
				txtDiaChi.setColumns(10);
				txtDiaChi.setBounds(76, 167, 672, 24);
				pnlThongTinHoaDon.add(txtDiaChi);
				
				btnTimKH = new JButton("TÌM");
				btnTimKH.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						timKHtheoSoDienThoai();
					}
				});
				btnTimKH.setBounds(686, 11, 62, 28);
				pnlThongTinHoaDon.add(btnTimKH);
				btnTimKH.setFont(new Font("Arial", Font.BOLD, 14));
				
				JLabel lblNgayMua = new JLabel("Ngày mua:");
				lblNgayMua.setFont(new Font("Arial", Font.BOLD, 16));
				lblNgayMua.setBounds(322, 44, 107, 24);
				pnlThongTinHoaDon.add(lblNgayMua);
				
				txtNgayMua = new JTextField();
				txtNgayMua.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				txtNgayMua.setBorder(null);
				txtNgayMua.setColumns(10);
				txtNgayMua.setText((ngayHienTai()));
				txtNgayMua.setEditable(false);
				txtNgayMua.setBounds(407, 44, 159, 24);
				pnlThongTinHoaDon.add(txtNgayMua);
				
				btnTaoMoi = new JButton("TẠO MỚI");
				btnTaoMoi.setEnabled(false);
				btnTaoMoi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modelThongTinPhieuDat.setRowCount(0);
						
						txtNhapSoDienThoai.setText("");
						txtMaKH.setText("");
						txtTenKhachHang.setText("");
						txtMaKH.setEditable(false);
						txtSoLuong.setText("");
						txtDiaChi.setText("");
						txtDiaChi.setEditable(false);
						txtSoDienThoai.setText("");
						txtSoDienThoai.setEditable(false);
						txtNamSinh.setText("");
						txtNamSinh.setEditable(false);
						lblTien.setText("0 Đồng");
						txtTienKhachTra.setText("");
						txtTienTraLai.setText("");
						txtMaQuanAo.setText("");
						lblHinhAnh.setIcon(null);
						txtMaChiTiet.setText("");
						txtTenQuanAo.setText("");
						txtGia.setText("");
						txtLoaiQuanAo.setText("");
						txtNCC.setText("");
						txtKichThuoc.setText("");
						txtSoLuongCT.setText("");
						cboGioiTinh.setEditable(false);
						btnTaoMoi.setEnabled(false);
						btnThemKHMoi.setEnabled(false);
					}
				});
				btnTaoMoi.setFont(new Font("Arial", Font.BOLD, 14));
				btnTaoMoi.setBounds(247, 201, 182, 34);
				pnlThongTinHoaDon.add(btnTaoMoi);
				
				JLabel lblSDT = new JLabel("Số điện thoại:");
				lblSDT.setFont(new Font("Arial", Font.BOLD, 16));
				lblSDT.setBounds(322, 82, 127, 24);
				pnlThongTinHoaDon.add(lblSDT);
				
				JLabel lblGioiTInh = new JLabel("Giới tính:");
				lblGioiTInh.setFont(new Font("Arial", Font.BOLD, 16));
				lblGioiTInh.setBounds(10, 120, 101, 24);
				pnlThongTinHoaDon.add(lblGioiTInh);
				
				txtSoDienThoai = new JTextField();
				txtSoDienThoai.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				txtSoDienThoai.setColumns(10);
				txtSoDienThoai.setBorder(null);
				txtSoDienThoai.setBounds(437, 82, 139, 24);
				pnlThongTinHoaDon.add(txtSoDienThoai);
				
				JLabel lblNamSinh = new JLabel("Năm sinh:");
				lblNamSinh.setFont(new Font("Arial", Font.BOLD, 16));
				lblNamSinh.setBounds(594, 82, 89, 24);
				pnlThongTinHoaDon.add(lblNamSinh);
				
				txtNamSinh = new JTextField();
				txtNamSinh.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				txtNamSinh.setColumns(10);
				txtNamSinh.setBorder(null);
				txtNamSinh.setBounds(672, 82, 76, 24);
				pnlThongTinHoaDon.add(txtNamSinh);
				
				JLabel lblDiaChi = new JLabel("Địa chỉ:");
				lblDiaChi.setFont(new Font("Arial", Font.BOLD, 16));
				lblDiaChi.setBounds(10, 166, 101, 24);
				pnlThongTinHoaDon.add(lblDiaChi);
				
				btnThemKHMoi = new JButton("THÊM KHÁCH HÀNG MỚI");
				btnThemKHMoi.setEnabled(false);
				btnThemKHMoi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DAO_KhachHang DAO = new DAO_KhachHang();
						String ma = taoMaKH();
						
						String ten = txtTenKhachHang.getText().trim();
						String diachi = txtDiaChi.getText().trim();
						String sdt = txtSoDienThoai.getText().trim();
						String gioitinh = cboGioiTinh.getSelectedItem().toString();
						String namsinh = txtNamSinh.getText().trim();
						KhachHang kh = new KhachHang(ma,ten,namsinh,sdt,gioitinh,diachi);
						if(DAO.themKH(kh)) {
							JOptionPane.showMessageDialog(GUI_DatHang.this, "Thêm thành công" );
							txtMaKH.setText(ma);
							khachHang.updateLaiData();
						}
						
					}
				});
				btnThemKHMoi.setFont(new Font("Arial", Font.BOLD, 14));
				btnThemKHMoi.setBounds(10, 201, 216, 34);
				pnlThongTinHoaDon.add(btnThemKHMoi);
				
				cboGioiTinh = new JComboBox();
				cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
				cboGioiTinh.setBounds(92, 120, 121, 24);
				pnlThongTinHoaDon.add(cboGioiTinh);
				
				txtTenKhachHang.setEditable(false);
				txtDiaChi.setEditable(false);
				txtSoDienThoai.setEditable(false);
				txtNamSinh.setEditable(false);
				txtMaKH.setEditable(false);
				btnThemKHMoi.setEnabled(false);
				cboGioiTinh.setEnabled(false);
				
				JLabel lblCapNhatLaiDsQuanAo = new JLabel("");
				lblCapNhatLaiDsQuanAo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						capNhatLaiDsQuanAo();
					}
				});
				lblCapNhatLaiDsQuanAo.setFont(new Font("Symbol", Font.BOLD | Font.ITALIC, 16));
				lblCapNhatLaiDsQuanAo.setIcon(new ImageIcon(GUI_DatHang.class.getResource("/Image/refresh.png")));
				lblCapNhatLaiDsQuanAo.setBounds(465, 23, 24, 20);
				add(lblCapNhatLaiDsQuanAo);
				
				btnSua = new JButton("SỬA");
				btnSua.setEnabled(false);
				btnSua.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int row = tblThongTinDonHang.getSelectedRow();
						if(row >=0 ) {
							suaSoLuong();
						}else {
							JOptionPane.showMessageDialog(null, "Vui lòng chọn quần áo cần sửa số lượng !");
						}
					}
				});
				btnSua.setFont(new Font("Arial", Font.BOLD, 20));
				btnSua.setBounds(395, 358, 98, 26);
				add(btnSua);
				
				txtMaQuanAo = new JTextField();
				txtMaQuanAo.setBounds(154, 17, 124, 24);
				add(txtMaQuanAo);
				txtMaQuanAo.setColumns(10);
				
				btnTimQuanAo = new JButton("TÌM");
				btnTimQuanAo.setBounds(288, 13, 92, 30);
				add(btnTimQuanAo);
				btnTimQuanAo.setBackground(new Color(153, 204, 204));
				btnTimQuanAo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						timTheoMa();
					}
				});
				btnTimQuanAo.setFont(new Font("Arial", Font.BOLD, 20));
				
				
				
								
				updateDSQuanAo();
				
				
		    	capNhatLaiDsQuanAo();
		    	
		    	setVisible(true);
		    	
}
	 //Sửa số lượng
	public void suaSoLuong() {
		 int row = tblThongTinDonHang.getSelectedRow();
		 int soLuongTrongDon = Integer.parseInt(tblThongTinDonHang.getValueAt(row, 2).toString());
		 int soLuongSua = Integer.parseInt(txtSoLuong.getText());
		 DAO_QuanAo dao = new DAO_QuanAo();
		 QuanAo quanAo = dao.getQuanAoByMa(tblThongTinDonHang.getValueAt(row, 0).toString());
		 int row1 = tblDsQuanAo.getSelectedRow();
		 List<QuanAo> list = dao.timTheoTen(tblDsQuanAo.getValueAt(row,1).toString());
		 if(soLuongTrongDon > soLuongSua) {
			 tblThongTinDonHang.setValueAt(soLuongSua, row, 2);
				
					for (QuanAo qa : list) {
						int soLuongHienTai = qa.getSoLuongHienTai();
						
						int soLuongConSauKhiMua = soLuongHienTai +( soLuongTrongDon - soLuongSua );
						dao.updateSoLuongCon(soLuongConSauKhiMua,tblDsQuanAo.getValueAt(row,1).toString());
					}
					guiCapNhatQuanAo.updateLaiSoLuong();
		 }else if(soLuongTrongDon == soLuongSua) {
			 JOptionPane.showMessageDialog(this, "Số lượng vẫn giữ nguyên !");
		 }else {
			 
			 if(soLuongSua < (soLuongTrongDon+quanAo.getSoLuongHienTai())) {
				 tblThongTinDonHang.setValueAt(soLuongSua, row, 2);
				 for (QuanAo qa : list) {
					 	int soLuongHienTai = qa.getSoLuongHienTai();
						int soLuongConSauKhiMua = soLuongHienTai - (soLuongSua - soLuongTrongDon );
						dao.updateSoLuongCon(soLuongConSauKhiMua,tblDsQuanAo.getValueAt(row,1).toString());
					}
					guiCapNhatQuanAo.updateLaiSoLuong();
			 }else {
				 JOptionPane.showMessageDialog(null,"Chỉ còn " + "" + (quanAo.getSoLuongHienTai()+soLuongTrongDon) + " " + quanAo.getTenQuanAo() );
			 }
		 }
		 
		 
	 }
	 
	//update
	 
	 public void updateDSQuanAo() {
			DAO_QuanAo dao= new DAO_QuanAo();
			List<QuanAo> list = dao.getAllQuanAo();
			for(QuanAo quanAo : list) {
				DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
				NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
				DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo();
				LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
				
				Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), chuyenDoi.DinhDangTien(quanAo.getGia())};
				modelDsQuanAo.addRow(data);
			}
			
		}

		public void updateDSDonMua() {
			DAO_QuanAo dao= new DAO_QuanAo();
			DAO_ChuyenDoi ChuyenDoi = new DAO_ChuyenDoi();
			int row = tblDsQuanAo.getSelectedRow();
			String maQuanAoThem = tblDsQuanAo.getValueAt(row, 0).toString();
			String maKhuyenMai = tblDsQuanAo.getValueAt(row,4).toString();
			int sl = Integer.parseInt(txtSoLuong.getText());
			boolean timThayQuanAo = true;
	        // Lấy tỉ lệ khuyến mãi từ tblQAKM trong GUI_KhuyenMai
	      
			if (!maKhuyenMai.equals("Không")) {
				float tiLeKM = km.layTileKhuyenMaiTuGUIKhuyenMai(maQuanAoThem, maKhuyenMai);
				
				System.out.println("Ti le la " + tiLeKM);

				List<QuanAo> list = dao.timTheoMa(maQuanAoThem);
				for(QuanAo quanAo : list) {
					float giaSauKM = quanAo.getGia() - (quanAo.getGia() * tiLeKM / 100);
					if(tblThongTinDonHang.getRowCount()==0) {
						Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),sl,ChuyenDoi.DinhDangTien(quanAo.getGia()),quanAo.getKm().layTileKhuyenMai(maQuanAoThem, maKhuyenMai)+"%",ChuyenDoi.DinhDangTien(giaSauKM * sl)};
						modelThongTinPhieuDat.addRow(data);
					}else {
					for (int i = 0; i < tblThongTinDonHang.getRowCount(); i++) {
						int soLuongTrongDonMua = Integer.parseInt(tblThongTinDonHang.getValueAt(i, 2).toString());
						int soLuongThem = Integer.parseInt(txtSoLuong.getText());
						if(maQuanAoThem.equalsIgnoreCase(tblThongTinDonHang.getValueAt(i,0).toString())) {
							tblThongTinDonHang.setValueAt(soLuongTrongDonMua+soLuongThem, i, 2);
							soLuongTrongDonMua = Integer.parseInt(tblThongTinDonHang.getValueAt(i, 2).toString());
							tblThongTinDonHang.setValueAt(ChuyenDoi.DinhDangTien(giaSauKM * soLuongTrongDonMua), i, 5);
							lblTien.setText(TinhTongTien(tblThongTinDonHang)+ " Đồng");
							tinhTienTraLai();
							timThayQuanAo = true;
							break;
						}
						else {
							timThayQuanAo = false;
						}
						
					}
					if(timThayQuanAo == false) {
						Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),sl,ChuyenDoi.DinhDangTien(quanAo.getGia()),quanAo.getKm().layTileKhuyenMai(maQuanAoThem, maKhuyenMai)+"%",ChuyenDoi.DinhDangTien(giaSauKM * sl)};
						modelThongTinPhieuDat.addRow(data);
					}
					}
				}
			}else {
				List<QuanAo> list = dao.timTheoMa(maQuanAoThem);
				for(QuanAo quanAo : list) {
					timThayQuanAo = false;
					for (int i = 0; i < tblThongTinDonHang.getRowCount(); i++) {
						int soLuongTrongDonMua = Integer.parseInt(tblThongTinDonHang.getValueAt(i, 2).toString());
						int soLuongThem = Integer.parseInt(txtSoLuong.getText());
						if(maQuanAoThem.equalsIgnoreCase(tblThongTinDonHang.getValueAt(i,0).toString())) {
							tblThongTinDonHang.setValueAt(soLuongTrongDonMua+soLuongThem, i, 2);
							soLuongTrongDonMua = Integer.parseInt(tblThongTinDonHang.getValueAt(i, 2).toString());
							tblThongTinDonHang.setValueAt(quanAo.getGia()*soLuongTrongDonMua, i, 5);
							lblTien.setText(TinhTongTien(tblThongTinDonHang)+ " Đồng");
							tinhTienTraLai();
							timThayQuanAo = true;
							
						}
						
						
					}
					if(!timThayQuanAo) {
						Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),sl,ChuyenDoi.DinhDangTien(quanAo.getGia()),quanAo.getKm().getMaKM(),ChuyenDoi.DinhDangTien(quanAo.getGia() * sl)};
						modelThongTinPhieuDat.addRow(data);
					}
				}
			}
			
		}
		public void xoaQuanAo() {
			DAO_QuanAo dao = new DAO_QuanAo();
			int row = tblThongTinDonHang.getSelectedRow();
			modelThongTinPhieuDat.removeRow(row);
		
		}
		//Hàm cập nhật lại table quần áo sau khi cập nhật bên GUI_QuanAo
		public void capNhatLaiDsQuanAo() {
			
			modelDsQuanAo.setRowCount(0);
			DAO_QuanAo dao= new DAO_QuanAo();
			List<QuanAo> list = dao.getAllQuanAo();
			for(QuanAo quanAo : list) {
				DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
				NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
				DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo();
				LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
				
				Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), chuyenDoi.DinhDangTien(quanAo.getGia())};
				modelDsQuanAo.addRow(data);
			}
			modelDsQuanAo.fireTableDataChanged();
		}
		
		
		//Cập nhật lại số lượng quần áo
			public void capNhatSoLuongSauKhiThem() {
				DAO_QuanAo dao = new DAO_QuanAo();
				int row = tblDsQuanAo.getSelectedRow();
				
					List<QuanAo> list = dao.timTheoTen(tblDsQuanAo.getValueAt(row,1).toString());
					for (QuanAo qa : list) {
						int soLuongHienTai = qa.getSoLuongHienTai();
						int soLuongMua = Integer.parseInt(txtSoLuong.getText());
						int soLuongConSauKhiMua = soLuongHienTai - soLuongMua;
						dao.updateSoLuongCon(soLuongConSauKhiMua,tblDsQuanAo.getValueAt(row,1).toString());
					}
					guiCapNhatQuanAo.updateLaiSoLuong();
				}
			
			
			
			public void capNhatSoLuongSauKhiXoa() {
				DAO_QuanAo dao = new DAO_QuanAo();
				int row = tblThongTinDonHang.getSelectedRow();
				List<QuanAo> list = dao.timTheoTen(tblThongTinDonHang.getValueAt(row,1).toString());
					for (QuanAo qa : list) {
						int soLuongHienTai = qa.getSoLuongHienTai();
						int soLuongMua = Integer.parseInt(tblThongTinDonHang.getValueAt(row, 2).toString());
						int soLuongConSauKhiXoa = soLuongHienTai + soLuongMua;
						dao.updateSoLuongCon(soLuongConSauKhiXoa,tblThongTinDonHang.getValueAt(row,1).toString());
					}
				guiCapNhatQuanAo.updateLaiSoLuong();
				
			}
		 
		//Hàm thêm hóa đơn mới
		public void themHoaDon() {
			DAO_ChuyenDoi ChuyenDoi = new DAO_ChuyenDoi();
			DAO_HoaDon hoaDon = new DAO_HoaDon();
			String maHD = taoMa();
			String tenNV = txtTenNhanVien.getText();
			String maNV = GUI_DangNhap.txtTenDangNhap.getText();
			String MaKH = txtMaKH.getText();
			String hoTen = txtTenKhachHang.getText().trim();
			String sdt = txtSoDienThoai.getText().trim();
			String diaChi = txtDiaChi.getText().trim();
			String namSinh = txtNamSinh.getText().trim();
			String gioiTinh = cboGioiTinh.getSelectedItem().toString();
			String ngayMua = txtNgayMua.getText().trim();
			KhachHang kh = new KhachHang(MaKH, hoTen, namSinh, sdt, gioiTinh, diaChi);
			if (!ngayMua.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date parsedDate = dateFormat.parse(ngayMua);

                    // Convert to java.sql.Date
                    ngay = new Date(parsedDate.getTime());
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
			
			float TongTien = ChuyenDoi.ChuyenTien(lblTien.getText());
			NhanVien nv = new NhanVien(maNV);
			HoaDon hd = new HoaDon(maHD,ngay,nv,kh,TongTien);
			hoaDon.create(hd);
			
			String maQuanAo,tenQuanAo;
			DAO_ChiTietHoaDon dao= new DAO_ChiTietHoaDon();
			for (int i = 0; i < tblThongTinDonHang.getRowCount(); i++) {
				
				maQuanAo = tblThongTinDonHang.getValueAt(i, 0).toString();
				tenQuanAo= tblThongTinDonHang.getValueAt(i, 1).toString();
				QuanAo quanAo = new QuanAo(maQuanAo,tenQuanAo);
				float GiaBan = ChuyenDoi.ChuyenTien(tblThongTinDonHang.getValueAt(i,3).toString());
				
				int soLuong= Integer.parseInt(tblThongTinDonHang.getValueAt(i,2).toString());
				float thanhTien= ChuyenDoi.ChuyenTien(tblThongTinDonHang.getValueAt(i,5).toString());
				ChiTietHoaDon cthd = new ChiTietHoaDon(hd, quanAo, soLuong, GiaBan, thanhTien);
				dao.create(cthd);
			}
		}
		
		
		//Tìm kiếm sản phẩm 
		public void timTheoTen() {
			DAO_QuanAo dao = new DAO_QuanAo();
			List<QuanAo> list = dao.timTheoTen(txtMaQuanAo.getText());
			modelDsQuanAo.getDataVector().removeAllElements();
			for (QuanAo quanAo : list) {
				DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
				NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
				DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo();
				LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
				
				Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), chuyenDoi.DinhDangTien(quanAo.getGia())};
				modelDsQuanAo.addRow(data);

		}

		}
		
		
		public void timTheoMa() {
			DAO_QuanAo dao = new DAO_QuanAo();
			String ma = txtMaQuanAo.getText();
			List<QuanAo> list = dao.timTheoMa(ma);
			modelDsQuanAo.getDataVector().removeAllElements();
			for (QuanAo quanAo : list) {
				DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
				NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
				DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo();
				LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
				
				Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), chuyenDoi.DinhDangTien(quanAo.getGia())};
				modelDsQuanAo.addRow(data);

		}

		}
		
		
		public void timKHtheoSoDienThoai() {
			DAO_KhachHang dao = new DAO_KhachHang();
			String soDienThoai = txtNhapSoDienThoai.getText().trim();
			
			List<KhachHang> list = dao.timTheoSoDienThoai(soDienThoai);
			if(list.isEmpty()) {
				txtMaKH.setEditable(false);
				
				btnThemKHMoi.setEnabled(true);
				JOptionPane.showMessageDialog(this, "Không tồn tại khách hàng");
				txtDiaChi.setText("");
				cboGioiTinh.setSelectedItem("Nam");
				txtNamSinh.setText("");
				txtTenKhachHang.setText("");
				txtSoDienThoai.setText("");
				txtDiaChi.setEditable(true);
				txtTenKhachHang.setEditable(true);
				txtSoDienThoai.setEditable(true);
				txtNamSinh.setEditable(true);
				cboGioiTinh.setEnabled(true);
			}
			else {
			for (KhachHang kh : list) {
				txtTenKhachHang.setText(kh.getHoTen());
				txtMaKH.setText(kh.getMaKH());
				txtDiaChi.setText(kh.getDiaChi());
				txtSoDienThoai.setText(kh.getsDT());
				cboGioiTinh.setSelectedItem(kh.getGioiTinh());
				txtNamSinh.setText(kh.getNamSinh());
				
				//Không được sửa
				txtTenKhachHang.setEditable(false);
				txtDiaChi.setEditable(false);
				txtSoDienThoai.setEditable(false);
				txtNamSinh.setEditable(false);
				txtMaKH.setEditable(false);
				btnThemKHMoi.setEnabled(false);
				cboGioiTinh.setEnabled(false);
			}
		}
		}
		
		//Hàm lấy ngày hiện hành trên laptop
		public String ngayHienTai() {
			LocalDate ngay = LocalDate.now();
			  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        return ngay.format(formatter);
		}
		
		//Tạo mã tăng tự động theo thứ tự
		public String taoMa() {

			DAO_HoaDon dao = new DAO_HoaDon();
			
			int n = dao.getAllHoaDon().size();
			if(n<9) {
			do {
				 n=n+1;
				
				ma = "HD00" + String.valueOf(n);
				list = dao.getAllHoaDon();
			} while (list.contains(ma));
			
		}else if(n<99) {
			do {
				 n=n+1;
				
				ma = "HD0" + String.valueOf(n);
				list = dao.getAllHoaDon();
			} while (list.contains(ma));
		}
		else if(n<999) {
			do {
				 n=n+1;
				
				ma = "HD" + String.valueOf(n);
				list = dao.getAllHoaDon();
			} while (list.contains(ma));
		}
			return ma;
		}
		public String layMaHD() {

			DAO_HoaDon dao = new DAO_HoaDon();
			
			int n = dao.getAllHoaDon().size();
			if(n<=9) {
			do {
				 n=n;
				
				ma = "HD00" + String.valueOf(n);
				list = dao.getAllHoaDon();
			} while (list.contains(ma));
			
		}else if(n<=99) {
			do {
				 n=n;
				
				ma = "HD0" + String.valueOf(n);
				list = dao.getAllHoaDon();
			} while (list.contains(ma));
		}
		else if(n<999) {
			do {
				 n=n;
				
				ma = "HD" + String.valueOf(n);
				list = dao.getAllHoaDon();
			} while (list.contains(ma));
		}
			return ma;
		}
		public String taoMaKH() {

			DAO_KhachHang dao = new DAO_KhachHang();
			
			int n = dao.getAllKhachHang().size();
			if(n<9) {
			do {
				 n=n+1;
				
				ma = "KH00" + String.valueOf(n);
				kh = dao.getAllKhachHang();
			} while (kh.contains(ma));
			
		}else if(n<=99) {
			do {
				 n=n+1;
				
				ma = "KH0" + String.valueOf(n);
				kh = dao.getAllKhachHang();
			} while (kh.contains(ma));
		}
		else if(n<=999) {
			do {
				 n=n+1;
				
				ma = "KH" + String.valueOf(n);
				kh = dao.getAllKhachHang();
			} while (kh.contains(ma));
		}
			return ma;
		}
		
		
		public String taoMaDH() {

			DAO_DonHangDatTruoc dao = new DAO_DonHangDatTruoc();
			
			int n = dao.getAllDonDatHang().size();
			if(n<9) {
			do {
				 n=n+1;
				
				ma = "DH00" + String.valueOf(n);
				dh = dao.getAllDonDatHang();
			} while (dh.contains(ma));
			
		}else if(n<99) {
			do {
				 n=n+1;
				
				ma = "DH0" + String.valueOf(n);
				dh = dao.getAllDonDatHang();
			} while (dh.contains(ma));
		}
		else if(n<999) {
			do {
				 n=n+1;
				
				ma = "DH" + String.valueOf(n);
				dh = dao.getAllDonDatHang();
			} while (dh.contains(ma));
		}
			return ma;
		}
		//Hàm kiểm tra số lượng trong kho
		public boolean kiemTraSoLuongCon() {
			int row = tblDsQuanAo.getSelectedRow();
			int soLuongMua = Integer.parseInt(txtSoLuong.getText());
			String tenQuanAo = tblDsQuanAo.getValueAt(row, 1).toString();
			String maQuanAo = tblDsQuanAo.getValueAt(row, 0).toString();
			DAO_QuanAo dao = new DAO_QuanAo();
			QuanAo qa = dao.getQuanAoByMa(maQuanAo);
			if(soLuongMua > qa.getSoLuongHienTai()) {
				if(qa.getSoLuongHienTai() == 0 ) {
					JOptionPane.showMessageDialog(null,tenQuanAo + " hết hàng");
					return false;
				}else {
					JOptionPane.showMessageDialog(null,"Chỉ còn " + "" + qa.getSoLuongHienTai() + " " + tenQuanAo );
				return false;
				}
			}else return true;
		}
		
		
		
		//Hàm lấy tên nhân viên
		public String layTenNhanVien() {
			DAO_NhanVien dao = new DAO_NhanVien();
			List<NhanVien> list = dao.timTheoMa(GUI_DangNhap.txtTenDangNhap.getText());
			for (NhanVien nhanVien : list) {
					tenNhanVien= nhanVien.getTenNV();
				}
				return tenNhanVien;
			}
		
		//Hàm tính tiền
		public String TinhTongTien(JTable tbl) {
			DAO_ChuyenDoi ChuyenDoi = new DAO_ChuyenDoi();
			float tongTien=0;
			for (int i = 0; i < tbl.getRowCount(); i++) {
				tongTien += ChuyenDoi.ChuyenTien(tbl.getValueAt(i, 5).toString());
			}
			return ChuyenDoi.DinhDangTien(tongTien);
		}
		
		
		public void tinhTienTraLai() {
			DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
			if (txtTienKhachTra.getText().trim().matches("[0-9]+")) {
				
				 float tienKhachTra = Float.parseFloat(txtTienKhachTra.getText().replace(",", ""));
				 System.out.println(tienKhachTra);
				float tongTien = Float.parseFloat(lblTien.getText().replace(",", "").replace("Đồng",""));
				float tientra = tienKhachTra-tongTien;
				System.out.println(tientra);
					if (tienKhachTra >= tongTien) {
						txtTienTraLai.setText(chuyenDoi.DinhDangTien(tienKhachTra - tongTien) + " Đồng");
					}
			}else if(txtTienKhachTra.getText().trim().matches("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(null, "Tiền khách trả phải là số và lớn hơn Thành Tiền");
				}
		}
		
		//Các hàm validData
		public boolean validDataThem() {
			if( txtDiaChi.getText().trim().equals("")
					|| txtNamSinh.getText().trim().equals("") || txtSoDienThoai.getText().trim().equals("")
					|| txtTenKhachHang.getText().trim().equals("")
					|| cboGioiTinh.getSelectedItem().equals("")) {
				JOptionPane.showMessageDialog(null, "Thông tin khách hàng còn thiếu !");
				return false;
				
			}else if(modelThongTinPhieuDat.getRowCount() ==0 ){
				JOptionPane.showMessageDialog(null, "Chưa có sản phẩm trong đơn mua !");
				return false;
			}else if(txtTienKhachTra.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền khách trả !");
				return false;
			}
			else return true;
				
		}
		
		//Đặt hang trước
		public void datHangTruoc() {
			 DAO_ChuyenDoi ChuyenDoi = new  DAO_ChuyenDoi(); 
			 String maDH = taoMaDH();
			 LocalDate ngay = LocalDate.now();
			 Date dateNgay = Date.valueOf(ngay);
			 NhanVien nv = new NhanVien(GUI_DangNhap.txtTenDangNhap.getText());
			 KhachHang kh = new KhachHang(txtMaKH.getText());
			 float TongTien = ChuyenDoi.ChuyenTien(lblTien.getText());
			 DonDatHang donMoi = new DonDatHang(maDH, dateNgay, nv, kh, TongTien);
		
			 DAO_DonHangDatTruoc donMoi2 = new DAO_DonHangDatTruoc();
			 donMoi2.create(donMoi);
//			 System.out.println(donMoi);
			 
			 	int rowCount = modelThongTinPhieuDat.getRowCount();
		        int columnCount = modelThongTinPhieuDat.getColumnCount();
		        for (int row = 0; row < rowCount; row++) {
		        	DAO_ChiTietDonDatHang chitiet = new DAO_ChiTietDonDatHang();
		            Object[] values = new Object[columnCount];
		            for (int column = 0; column < columnCount; column++) {
		                Object value = modelThongTinPhieuDat.getValueAt(row, column);
		                values[column] = value;
		            }
		         
		            QuanAo quanAo = new QuanAo((String) values[0], (String) values[1]);
		            int soLuong = (int) values[2];

		            
		            String fortmatPrice = ((String)values[3]).replaceAll(",", "");
		            float giaBan = Float.parseFloat(fortmatPrice);
		            
		            String fortmatTotal = ((String)values[5]).replaceAll(",", "");
		            float thanhTien = Float.parseFloat(fortmatTotal);
		           
		            
		            ChiTietDonDatHang khoi = new ChiTietDonDatHang(donMoi, quanAo, soLuong, giaBan, thanhTien);

		            chitiet.create(khoi);
		        }
		 }
		public static void sendMessage(String soDienThoai) {
	        // Khởi tạo Twilio
	        Twilio.init("ACe54e9eff44c37b4275ff723f16463a62", "4bfa06b5365698c1d629014cef2e80df");
	        
	        try {
	            // Gửi tin nhắn
	            Message message = Message.creator(
	                    new PhoneNumber(soDienThoai),
	                    new PhoneNumber("+13853556034"),
	                    "Cảm ơn quý khách đã mua hàng ở cửa hàng quần áo AM"
	            ).create();

	            
	        } catch (Exception e) {
	            
	        }
	    }
		public static void sendMessage1(String soDienThoai) {
	        // Khởi tạo Twilio
	        Twilio.init("ACe54e9eff44c37b4275ff723f16463a62", "4bfa06b5365698c1d629014cef2e80df");

	        try {
	            // Gửi tin nhắn
	            Message message = Message.creator(
	                    new PhoneNumber(soDienThoai),
	                    new PhoneNumber("+13853556034"),
	                    "Cảm ơn quý khách , Quý Khách đã đặt hàng thành công"
	            ).create();

	            
	        } catch (Exception e) {
	            
	        }
	    }
}
