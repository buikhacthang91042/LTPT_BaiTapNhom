package GUI;

import javax.swing.JPanel;

import java.awt.Rectangle;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_ChuyenDoi;
import DAO.DAO_LoaiQuanAo;
import DAO.DAO_NhaCungCap;
import DAO.DAO_QuanAo;
import DAO.EntityManagerFactoryUtil;
import connect.ConnectDB;
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import entity.QuanAo;
import jakarta.persistence.EntityManager;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class GUI_TimKiemQuanAo extends JPanel {
	private JTextField txtTenQuanAo;
	private JComboBox cboHang,cboMaQuanAo,cboLoaiQuanAo;
	private DefaultTableModel modelThongTinQuanAo;
	private JTable tblThongTinQuanAo;
	EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
    EntityManager entityManager = util.getEnManager();
	public GUI_TimKiemQuanAo() {
		setBounds(new Rectangle(0, 0, 1308, 678));
		setBackground(new Color(0, 64, 64));
		setLayout(null);
		
		JPanel pnForm = new JPanel();
		pnForm.setBounds(71, 58, 1168, 200);
		add(pnForm);
		pnForm.setLayout(null);
		
		//ConnectDB
 		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblTenQuanAo = new JLabel("Tên quần áo");
		lblTenQuanAo.setFont(new Font("Arial", Font.BOLD, 22));
		lblTenQuanAo.setBounds(79, 30, 225, 32);
		pnForm.add(lblTenQuanAo);
		
		txtTenQuanAo = new JTextField();
		txtTenQuanAo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTenQuanAo.setColumns(10);
		txtTenQuanAo.setBounds(353, 30, 225, 32);
		pnForm.add(txtTenQuanAo);
		
		JLabel lblHang = new JLabel("Hãng quần áo");
		lblHang.setFont(new Font("Arial", Font.BOLD, 22));
		lblHang.setBounds(78, 84, 225, 32);
		pnForm.add(lblHang);
		
		cboHang = new JComboBox();
		cboHang.setModel(new DefaultComboBoxModel(new String[] {"Không"}));
		cboHang.setFont(new Font("Arial", Font.PLAIN, 22));
		cboHang.setBounds(353, 84, 225, 32);
		pnForm.add(cboHang);
		
		JLabel lblLoaiQuanAo = new JLabel("Loại quần áo");
		lblLoaiQuanAo.setFont(new Font("Arial", Font.BOLD, 22));
		lblLoaiQuanAo.setBounds(79, 144, 225, 32);
		pnForm.add(lblLoaiQuanAo);
		
		 cboLoaiQuanAo = new JComboBox();
		 cboLoaiQuanAo.setModel(new DefaultComboBoxModel(new String[] {"Không"}));
		cboLoaiQuanAo.setFont(new Font("Arial", Font.PLAIN, 22));
		cboLoaiQuanAo.setBounds(354, 144, 225, 32);
		pnForm.add(cboLoaiQuanAo);
		
		JLabel lblMaSP = new JLabel("Mã quần áo");
		lblMaSP.setFont(new Font("Arial", Font.BOLD, 22));
		lblMaSP.setBounds(691, 30, 155, 32);
		pnForm.add(lblMaSP);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( ( !txtTenQuanAo.equals("")) && (cboHang.getSelectedItem().equals("Không")) && (cboLoaiQuanAo.getSelectedItem().equals("Không")) 
						&&  (cboMaQuanAo.getSelectedItem().equals("Không")) ) {
					timTheoTen();
					
				}
				else if( ( txtTenQuanAo.getText().isEmpty()) && (cboLoaiQuanAo.getSelectedItem().toString().equals("Không")) 
						&&  (cboMaQuanAo.getSelectedItem().toString().equals("Không")) ) {
					timTheoHang();
					
				}
				else if( ( txtTenQuanAo.getText().isEmpty()) && (cboHang.getSelectedItem().toString().equals("Không")) 
						&&  (cboMaQuanAo.getSelectedItem().toString().equals("Không")) ) {
					timTheoLoai();
					
				}
				else if( ( txtTenQuanAo.getText().isEmpty()) && (cboHang.getSelectedItem().toString().equals("Không")) 
						&&  (cboLoaiQuanAo.getSelectedItem().toString().equals("Không")) ) {
					timTheoMa();
					
				}
				else if( ( !txtTenQuanAo.getText().isEmpty()) && (cboMaQuanAo.getSelectedItem().toString().equals("Không")) 
						&&  (cboLoaiQuanAo.getSelectedItem().toString().equals("Không")) ) {
					timTheoTenvaHang();
					
				}
				else if( ( !txtTenQuanAo.getText().isEmpty()) && (cboMaQuanAo.getSelectedItem().toString().equals("Không")) 
						&&  (cboHang.getSelectedItem().toString().equals("Không")) ) {
					timTheoTenvaLoai();
					
				}
				else if( ( !txtTenQuanAo.getText().isEmpty()) && (cboLoaiQuanAo.getSelectedItem().toString().equals("Không")) 
						&&  (cboHang.getSelectedItem().toString().equals("Không")) ) {
					timTheoTenvaMa();
					
				}
				else if( ( txtTenQuanAo.getText().isEmpty()) && (cboMaQuanAo.getSelectedItem().toString().equals("Không")) 
						 ) {
					timTheoHangvaLoai();
					
				}
				else if( ( txtTenQuanAo.getText().isEmpty()) && (cboLoaiQuanAo.getSelectedItem().toString().equals("Không")) 
						 ) {
					timTheoHangvaMa();
					
				}
				else if( ( txtTenQuanAo.getText().isEmpty()) && (cboHang.getSelectedItem().toString().equals("Không")) 
						 ) {
					timTheoLoaivaMa();
					
				}
				else if( ( !txtTenQuanAo.getText().isEmpty()) && (cboMaQuanAo.getSelectedItem().toString().equals("Không")) 
						 ) {
					timTheoTenvaHangvaLoai();;
					
				}
				else if( ( !txtTenQuanAo.getText().isEmpty()) && (cboLoaiQuanAo.getSelectedItem().toString().equals("Không")) 
						 ) {
					timTheoTenvaHangvaMa();
					
				}
				else if( ( txtTenQuanAo.getText().isEmpty())) {
					timTheoLoaivaHangvaMa();
					
				}
				else {
					timTheoTenvaLoaivaHangvaMa();
				}
			}
		});
		btnTimKiem.setBackground(Color.ORANGE);
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setBounds(691, 117, 180, 30);
		pnForm.add(btnTimKiem);
		
		cboMaQuanAo = new JComboBox();
		cboMaQuanAo.setModel(new DefaultComboBoxModel(new String[] {"Không"}));
		cboMaQuanAo.setFont(new Font("Arial", Font.PLAIN, 22));
		cboMaQuanAo.setBounds(856, 30, 225, 32);
		pnForm.add(cboMaQuanAo);
		
		String[] tieude = {
				"Mã quần áo", "Tên quần áo ", "Tên nhà cung cấp", "Loại quần áo", "Kích thước", "Số lượng tồn","Khuyến mãi","Giá"
		};
		modelThongTinQuanAo = new DefaultTableModel(tieude,0);
		JScrollPane scrThongTinQuanAo = new JScrollPane();
		scrThongTinQuanAo.setBounds(38, 311, 1232, 322);
		add(scrThongTinQuanAo);
		scrThongTinQuanAo.setViewportView(tblThongTinQuanAo = new  JTable(modelThongTinQuanAo));
		scrThongTinQuanAo.setViewportView(tblThongTinQuanAo);
		
		JLabel lblTieuDeTimKiemQuanAo = new JLabel("Tìm kiếm quần áo");
		lblTieuDeTimKiemQuanAo.setForeground(new Color(135, 206, 235));
		lblTieuDeTimKiemQuanAo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblTieuDeTimKiemQuanAo.setBounds(463, 10, 295, 35);
		add(lblTieuDeTimKiemQuanAo);
		
		JLabel lblDanhSchQun = new JLabel("Danh sách quần áo");
		lblDanhSchQun.setForeground(new Color(135, 206, 235));
		lblDanhSchQun.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDanhSchQun.setBounds(38, 278, 189, 35);
		add(lblDanhSchQun);
		updateComboNhaCungCap();
		updateComboLoaiQuanAo();
		updateComboMaQuanAo();
		updateData();
	}
	
	//các hàm update
	public void updateComboMaQuanAo() {
		DAO_QuanAo dao= new DAO_QuanAo(entityManager);
		for (QuanAo qa : dao.getAllQuanAo()) {
			cboMaQuanAo.addItem(qa.getMaQuanAo());
		}
	}
	
	public void updateLaiComboMaQuanAo() {
		cboMaQuanAo.removeAllItems();
		DAO_QuanAo dao= new DAO_QuanAo(entityManager);
		for (QuanAo qa : dao.getAllQuanAo()) {
			cboMaQuanAo.addItem(qa.getMaQuanAo());
		}
	}
	public void updateComboLoaiQuanAo() {
		DAO_LoaiQuanAo dao = new DAO_LoaiQuanAo(entityManager);
		for(LoaiQuanAo loai : dao.getAllLoaiQuanAo()) {
			cboLoaiQuanAo.addItem(loai.getTenLoai());
		}
	}
	public void updateComboNhaCungCap() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap();
		for(NhaCungCap loai : dao.getAllNhaCungCap()) {
			cboHang.addItem(loai.getTenNCC());
		}
	}
	public void updateLaiComboNhaCungCap() {
		cboHang.removeAllItems();
		DAO_NhaCungCap dao = new DAO_NhaCungCap();
		for(NhaCungCap loai : dao.getAllNhaCungCap()) {
			cboHang.addItem(loai.getTenNCC());
		}
	}
	public void updateData() {
		DAO_QuanAo dao= new DAO_QuanAo(entityManager);
		DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<QuanAo> list = dao.getAllQuanAo();
		for(QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongCu(),quanAo.getSoLuongHienTai(),dateFormat.format(quanAo.getNgayNhap()),quanAo.getKm().getMaKM(),chuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);
		}
		
	}
	public void timTheoTen() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		List<QuanAo> list = dao.timTheoTen(txtTenQuanAo.getText());
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	
	public void timTheoHang() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
		DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
		String hang = cboHang.getSelectedItem().toString();
		NhaCungCap nhaCC = daoNCC.getNCCByTen(hang);
		List<QuanAo> list = dao.timTheoHang(nhaCC.getMaNCC());
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	public void timTheoLoai() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
		String loai = cboLoaiQuanAo.getSelectedItem().toString();
		LoaiQuanAo loaiQA = daoLoai.getLoaiQuanAoByTen(loai);
		List<QuanAo> list = dao.timTheoLoai(loaiQA.getMaLoai());
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			
			LoaiQuanAo loai1= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai1.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	public void timTheoMa() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		String ma = cboMaQuanAo.getSelectedItem().toString();
		List<QuanAo> list = dao.timTheoMa(ma);
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	public void timTheoTenvaHang() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
		String ten = txtTenQuanAo.getText();
		String hang = cboHang.getSelectedItem().toString();
		NhaCungCap NCC1 = daoNCC.getNCCByTen(hang);
		List<QuanAo> list = dao.timTheoTenvaHang(ten,NCC1.getMaNCC());
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}
	}
	
	public void timTheoTenvaLoai() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
		String ten = txtTenQuanAo.getText();
		String loai = cboLoaiQuanAo.getSelectedItem().toString();
		LoaiQuanAo loaiQA = daoLoai.getLoaiQuanAoByTen(loai);
		List<QuanAo> list = dao.timTheoTenvaLoai(ten,loaiQA.getMaLoai());
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			
			LoaiQuanAo loai1= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai1.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	
	public void timTheoTenvaMa() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		String ten = txtTenQuanAo.getText();
		String ma = cboMaQuanAo.getSelectedItem().toString();
		List<QuanAo> list = dao.timTheoTenvaMa(ten,ma);
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	public void timTheoHangvaLoai() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
		DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
		String loai = cboLoaiQuanAo.getSelectedItem().toString();
		String hang = cboHang.getSelectedItem().toString();
		NhaCungCap nhaCC = daoNCC.getNCCByTen(hang);
		LoaiQuanAo loaiQA =daoLoai.getLoaiQuanAoByTen(loai);
		List<QuanAo> list = dao.timTheoHangvaLoai(nhaCC.getMaNCC(),loaiQA.getMaLoai());
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			LoaiQuanAo loai1= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai1.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	public void timTheoHangvaMa() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
		String ma = cboMaQuanAo.getSelectedItem().toString();
		String hang = cboHang.getSelectedItem().toString();
		NhaCungCap nhaCC = daoNCC.getNCCByTen(hang);
		List<QuanAo> list = dao.timTheoHangvaMa(nhaCC.getMaNCC(),ma);
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	public void timTheoLoaivaMa() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
		String ma = cboMaQuanAo.getSelectedItem().toString();
		String loai = cboLoaiQuanAo.getSelectedItem().toString();
		LoaiQuanAo loaiQA =daoLoai.getLoaiQuanAoByTen(loai);
		List<QuanAo> list = dao.timTheoLoaivaMa(loaiQA.getMaLoai(),ma);
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			
			LoaiQuanAo loai1= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai1.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}
	}
	public void timTheoTenvaHangvaLoai() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
		DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
		
		String ten = txtTenQuanAo.getText().toString();
		String hang = cboHang.getSelectedItem().toString();
		String loai = cboLoaiQuanAo.getSelectedItem().toString();
		NhaCungCap nhaCC = daoNCC.getNCCByTen(hang);
		LoaiQuanAo loaiQA =daoLoai.getLoaiQuanAoByTen(loai);
		List<QuanAo> list = dao.timTheoTenvaHangvaLoai(ten,nhaCC.getMaNCC(),loaiQA.getMaLoai());
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			
			LoaiQuanAo loai1= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai1.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	
	public void timTheoTenvaHangvaMa() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
		String ten = txtTenQuanAo.getText().toString();
		String hang = cboHang.getSelectedItem().toString();
		String ma = cboMaQuanAo.getSelectedItem().toString();
		NhaCungCap NCC = daoNCC.getNCCByTen(hang);
		List<QuanAo> list = dao.timTheoTenvaHangvaMa(ten,NCC.getMaNCC(),ma);
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
			LoaiQuanAo loai= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	public void timTheoLoaivaHangvaMa() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
		DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
		String ma = cboMaQuanAo.getSelectedItem().toString();
		String hang = cboHang.getSelectedItem().toString();
		String loai = cboLoaiQuanAo.getSelectedItem().toString();
		NhaCungCap nhaCC = daoNCC.getNCCByTen(hang);
		LoaiQuanAo loaiQA =daoLoai.getLoaiQuanAoByTen(loai);
		
		List<QuanAo> list = dao.timTheoLoaivaHangvaMa(loaiQA.getMaLoai(),nhaCC.getMaNCC(),ma);
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
			
			LoaiQuanAo loai1= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai1.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
	public void timTheoTenvaLoaivaHangvaMa() {
		DAO_QuanAo dao = new DAO_QuanAo(entityManager);
		DAO_LoaiQuanAo daoLoai= new DAO_LoaiQuanAo(entityManager);
		DAO_NhaCungCap daoNCC= new DAO_NhaCungCap();
		String ten = txtTenQuanAo.getText();
		String ma = cboMaQuanAo.getSelectedItem().toString();
		String hang = cboHang.getSelectedItem().toString();
		String loai = cboLoaiQuanAo.getSelectedItem().toString();
		NhaCungCap nhaCC = daoNCC.getNCCByTen(hang);
		LoaiQuanAo loaiQA =daoLoai.getLoaiQuanAoByTen(loai);
				
		List<QuanAo> list = dao.timTheoTenvaLoaivaHangvaMa(ten,loaiQA.getMaLoai(),nhaCC.getMaNCC(),ma);
		modelThongTinQuanAo.getDataVector().removeAllElements();
		for (QuanAo quanAo : list) {
			
			NhaCungCap ncc= daoNCC.getNCCByMa(quanAo.getNCC().getMaNCC());
		
			LoaiQuanAo loai1= daoLoai.getLoaiQuanAoByMa(quanAo.getLoaiQuanAo().getMaLoai());
			
			Object [] data = {quanAo.getMaQuanAo(),quanAo.getTenQuanAo(),ncc.getTenNCC(),loai1.getTenLoai(),quanAo.getKinhThuoc(),quanAo.getSoLuongHienTai(),quanAo.getKm().getMaKM(), DAO_ChuyenDoi.DinhDangTien(quanAo.getGia())};
			modelThongTinQuanAo.addRow(data);

	}

	}
}
