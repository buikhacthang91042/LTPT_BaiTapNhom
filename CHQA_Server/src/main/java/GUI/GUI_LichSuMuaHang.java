package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_ChiTietHoaDon;
import DAO.DAO_ChuyenDoi;
import DAO.DAO_HoaDon;
import DAO.DAO_LoaiQuanAo;
import DAO.DAO_NhaCungCap;
import DAO.DAO_NhanVien;
import DAO.DAO_QuanAo;
import DAO.EntityManagerFactoryUtil;
import connect.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import entity.NhanVien;
import jakarta.persistence.EntityManager;

import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.ImageIcon;

public class GUI_LichSuMuaHang extends JPanel {

	private DefaultTableModel modelHoaDon,modelChiTietHoaDon;
	private String ma;
	private List<HoaDon> list;
	private JTable tblHoaDon, tblChiTietHoaDon;
	private JTextField txtTimTheoMa;
	EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
    EntityManager entityManager = util.getEnManager();
	public  GUI_LichSuMuaHang() {
		// TODO Auto-generated constructor stub

		setBackground(new Color(0, 64, 64));
		setLayout(null);
	
		JLabel lblLichSuMuaHang = new JLabel("Lịch sử mua hàng");
		lblLichSuMuaHang.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblLichSuMuaHang.setBounds(464, 0, 366, 69);
		lblLichSuMuaHang.setForeground(new Color(135, 206, 235));
		add(lblLichSuMuaHang);
		
		
		//ConnectDB
 		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String [] tieude={"Mã hóa đơn","Ngày mua","Mã nhân viên","Tên nhân viên","Mã khách hàng","Tên khách hàng","Tổng tiền"};
 		modelHoaDon = new DefaultTableModel(tieude,0);
 		JScrollPane scrHoaDon = new JScrollPane();
		scrHoaDon.setBounds(10, 67, 757, 465);
		add(scrHoaDon);
		
		scrHoaDon.setViewportView(tblHoaDon = new  JTable(modelHoaDon));
		scrHoaDon.setViewportView(tblHoaDon);
		
		JButton btnXemChiTiet = new JButton("XEM CHI TIẾT");
		btnXemChiTiet.setBounds(787, 534, 159, 42);
		add(btnXemChiTiet);
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblHoaDon.getSelectedRow();
				if(row >= 0 ) {
					modelChiTietHoaDon.setRowCount(0);
					updateDataChiTiet();
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đươn cần xem chi tiết !");
				}
			}
		});
		btnXemChiTiet.setForeground(new Color(0, 0, 0));
		btnXemChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXemChiTiet.setBackground(new Color(0, 250, 154));
		
		JLabel lblNewLabel = new JLabel("Tìm hóa đơn");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(25, 542, 128, 24);
		add(lblNewLabel);
		
		String [] tieude1={"Mã quần áo","Tên quần áo","Số Lượng","Giá bán","Khuyến mãi","Thành tiền"};
 		modelChiTietHoaDon = new DefaultTableModel(tieude1,0);
		JScrollPane scrChiTietHoaDon = new JScrollPane();
		scrChiTietHoaDon.setViewportView(tblChiTietHoaDon = new  JTable(modelChiTietHoaDon));
		scrChiTietHoaDon.setViewportView(tblChiTietHoaDon);
		scrChiTietHoaDon.setBounds(787, 67, 489, 465);
		add(scrChiTietHoaDon);
		
		txtTimTheoMa = new JTextField();
		txtTimTheoMa.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTimTheoMa.setBounds(165, 542, 120, 24);
		add(txtTimTheoMa);
		txtTimTheoMa.setColumns(10);
		
		JButton btnTim = new JButton("TÌM");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelChiTietHoaDon.setRowCount(0);
				timTheoMa();
				
				
			}
		});
		btnTim.setForeground(new Color(0, 0, 0));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTim.setBackground(new Color(0, 250, 154));
		btnTim.setBounds(295, 542, 120, 24);
		add(btnTim);
		
		JLabel lblUpdateHoaDon = new JLabel("");
		lblUpdateHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modelHoaDon.setRowCount(0);
				updateData();
			}
		});
		lblUpdateHoaDon.setIcon(new ImageIcon(GUI_LichSuMuaHang.class.getResource("/Image/refresh.png")));
		lblUpdateHoaDon.setBounds(10, 43, 31, 24);
		add(lblUpdateHoaDon);
		updateData();
		

	}
	
	//Các hàm cập nhật
	public void updateData() {
		DAO_HoaDon dao = new DAO_HoaDon(entityManager);
		DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
		List<HoaDon> list = dao.getAllHoaDon();
		for(HoaDon hd : list) {
			Object [] data = {hd.getMaHD(),hd.getNgayMua(),hd.getNV().getMaNV(),hd.getNV().getTenNV(),hd.getKH().getMaKH(),hd.getKH().getHoTen(),chuyenDoi.DinhDangTien(hd.getTongTien())};
			modelHoaDon.addRow(data);
	}
	
	}
	public void updateDataChiTiet() {
		DAO_ChiTietHoaDon dao = new DAO_ChiTietHoaDon();
		DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
		int row = tblHoaDon.getSelectedRow();
		List<ChiTietHoaDon> list = dao.getAllChiTietHoaDon(tblHoaDon.getValueAt(row, 0).toString());
		for(ChiTietHoaDon cthd : list) {
			Object [] data = {cthd.getQuanAo().getMaQuanAo(),cthd.getQuanAo().getTenQuanAo(),cthd.getSoLuong(),chuyenDoi.DinhDangTien(cthd.getGiaBan()),cthd.getQuanAo().getKm().layTileKhuyenMai(cthd.getQuanAo().getMaQuanAo(),cthd.getQuanAo().getKm().getMaKM())+ "%",chuyenDoi.DinhDangTien(cthd.getThanhTien())};
			modelChiTietHoaDon.addRow(data);
	}
	}
	
	//Hàm tìm
	public void timTheoMa() {
		DAO_HoaDon dao = new DAO_HoaDon(entityManager);
		DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
		List<HoaDon> list = dao.search(txtTimTheoMa.getText());
		modelHoaDon.setRowCount(0);
		for(HoaDon hd : list) {
			Object [] data = {hd.getMaHD(),hd.getNgayMua(),hd.getNV().getMaNV(),hd.getNV().getTenNV(),hd.getKH().getMaKH(),hd.getKH().getHoTen(),chuyenDoi.DinhDangTien(hd.getTongTien())};
			modelHoaDon.addRow(data);
	}
	}
}

