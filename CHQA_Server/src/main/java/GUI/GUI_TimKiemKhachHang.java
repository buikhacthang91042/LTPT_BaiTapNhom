package GUI;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_KhachHang;
import DAO.DAO_QuanAo;
import DAO.EntityManagerFactoryUtil;
import connect.ConnectDB;
import entity.KhachHang;
import entity.QuanAo;
import jakarta.persistence.EntityManager;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class GUI_TimKiemKhachHang extends JPanel {
	private JTextField txtTenKH;
	private JTextField txtSdt;
	private JComboBox cboGioiTinh, cboMaKhachHang;
	private DefaultTableModel modelKhachHang;
	private JTable tblThongTinKhachHang;

	public GUI_TimKiemKhachHang() {
		setBackground(new Color(0, 64, 64));
		setBounds(new Rectangle(0, 0, 1308, 678));
		setLayout(null);

		// ConnectDB
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel pnlNhapThongTinKhachHang = new JPanel();
		pnlNhapThongTinKhachHang.setBounds(110, 71, 1054, 200);
		add(pnlNhapThongTinKhachHang);
		pnlNhapThongTinKhachHang.setLayout(null);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setFont(new Font("Arial", Font.BOLD, 15));
		lblTenKhachHang.setBounds(97, 33, 225, 32);
		pnlNhapThongTinKhachHang.add(lblTenKhachHang);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(237, 33, 225, 32);
		pnlNhapThongTinKhachHang.add(txtTenKH);

		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setFont(new Font("Arial", Font.BOLD, 15));
		lblSdt.setBounds(97, 101, 225, 32);
		pnlNhapThongTinKhachHang.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSdt.setColumns(10);
		txtSdt.setBounds(237, 101, 225, 32);
		pnlNhapThongTinKhachHang.add(txtSdt);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setFont(new Font("Arial", Font.BOLD, 15));
		lblGioiTinh.setBounds(607, 101, 225, 32);
		pnlNhapThongTinKhachHang.add(lblGioiTinh);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Arial", Font.PLAIN, 12));
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Không" }));
		cboGioiTinh.setBounds(762, 103, 225, 32);
		pnlNhapThongTinKhachHang.add(cboGioiTinh);
		cboGioiTinh.addItem("Nam");
		cboGioiTinh.addItem("Nữ");

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!txtTenKH.getText().isEmpty()) && (cboGioiTinh.getSelectedItem().equals("Không"))
						&& (txtSdt.getText().isEmpty())
						&& (cboMaKhachHang.getSelectedItem().toString().equals("Không"))) {
					timTheoTen();

				} else if ((txtTenKH.getText().isEmpty()) && (txtSdt.getText().isEmpty())
						&& (cboMaKhachHang.getSelectedItem().toString().equals("Không"))) {
					timTheoGioiTinh();

				} else if ((txtTenKH.getText().isEmpty()) && (cboGioiTinh.getSelectedItem().equals("Không"))
						&& (cboMaKhachHang.getSelectedItem().toString().equals("Không"))) {
					timTheoSDT();

				} else if ((txtTenKH.getText().isEmpty()) && (cboGioiTinh.getSelectedItem().equals("Không"))
						&& (txtSdt.getText().isEmpty())) {
					timTheoMa();

				} else if ((!txtTenKH.equals("")) && (cboGioiTinh.getSelectedItem().toString().equals("Không"))
						&& txtSdt.getText().isEmpty()) {
					timTheoTenvaMa();

				} else if ((!txtTenKH.equals("")) && (cboMaKhachHang.getSelectedItem().equals("Không"))
						&& (txtSdt.getText().isEmpty())) {
					timTheoTenvaGioiTinh();

				} else if ((!txtTenKH.getText().isEmpty()) && (!txtSdt.getText().isEmpty())
						&& (cboGioiTinh.getSelectedItem().toString().equals("Không"))
						&& (cboMaKhachHang.getSelectedItem().toString().equals("Không"))) {
					timTheoTenvaSDT();

				} else if ((cboMaKhachHang.getSelectedItem().equals("Không"))
						&& (!txtSdt.equals("") && (txtTenKH.getText().isEmpty()))) {
					timTheoSDTvaGioiTinh();

				} else if ((!txtSdt.equals("")) && (txtTenKH.getText().isEmpty())
						&& (cboGioiTinh.getSelectedItem().toString().equals("Không"))) {
					timTheoSDTvaMa();

				} else if ((txtSdt.getText().isEmpty()) && (txtTenKH.getText().isEmpty())) {
					timTheoGioiTinhvaMa();

				} else if ((!txtTenKH.equals("")) && (txtSdt.getText().isEmpty())) {
					timTheoTenvaGioiTinhvaMa();

				} else if ((!txtTenKH.equals("")) && (!txtSdt.equals(""))
						&& (cboGioiTinh.getSelectedItem().toString().equals("Không"))) {
					timTheoTenvaSDTvaMa();

				} else if ((!txtSdt.equals("") && (txtTenKH.getText().isEmpty()))) {
					timTheoSDTvaMavaGioiTinh();

				} else {
					timTheoTenvaGioiTinhvaMavaSDT();
				}

			}
		});
		btnTimKiem.setBackground(new Color(51, 204, 153));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setBounds(762, 158, 180, 32);
		pnlNhapThongTinKhachHang.add(btnTimKiem);

		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setFont(new Font("Arial", Font.BOLD, 15));
		lblMaKH.setBounds(607, 33, 225, 32);
		pnlNhapThongTinKhachHang.add(lblMaKH);

		cboMaKhachHang = new JComboBox();
		cboMaKhachHang.setFont(new Font("Arial", Font.PLAIN, 12));
		cboMaKhachHang.setModel(new DefaultComboBoxModel(new String[] { "Không" }));
		cboMaKhachHang.setBounds(762, 33, 225, 32);
		pnlNhapThongTinKhachHang.add(cboMaKhachHang);

		tblThongTinKhachHang = new JTable();
		String[] tieude = { "Mã khách hàng", "Họ và tên", "Năm sinh", "Số điện thoại", "Giới tính", "Địa chỉ" };

		modelKhachHang = new DefaultTableModel(tieude, 0);
		JScrollPane scrThongTinKhachHang = new JScrollPane();
		scrThongTinKhachHang.setBounds(82, 386, 1116, 202);
		add(scrThongTinKhachHang);

		scrThongTinKhachHang.setViewportView(tblThongTinKhachHang = new JTable(modelKhachHang));
		scrThongTinKhachHang.setViewportView(tblThongTinKhachHang);

		JLabel lblTimKiemKhachHang = new JLabel("Tìm kiếm khách hàng");
		lblTimKiemKhachHang.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblTimKiemKhachHang.setForeground(new Color(135, 206, 235));
		lblTimKiemKhachHang.setBounds(477, 20, 386, 41);
		add(lblTimKiemKhachHang);

		JLabel lblDanhSachKhachHang = new JLabel("Danh sách khách hàng");
		lblDanhSachKhachHang.setForeground(new Color(135, 206, 235));
		lblDanhSachKhachHang.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDanhSachKhachHang.setBounds(82, 335, 230, 41);
		add(lblDanhSachKhachHang);

		updateComboMaKH();
		updateData();
	}

	public void updateData() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		List<KhachHang> list = dao.getAllKhachHang();
		for (KhachHang kh : list) {
			Object[] data = { kh.getMaKH(), kh.getHoTen(), kh.getNamSinh(), kh.getsDT(), kh.getGioiTinh(),
					kh.getDiaChi() };
			modelKhachHang.addRow(data);
		}

	}

	public void updateComboMaKH() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		for (KhachHang kh : dao.getAllKhachHang()) {
			cboMaKhachHang.addItem(kh.getMaKH());
		}
	}

	public void timTheoTen() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		List<KhachHang> list = dao.timTheoTen(txtTenKH.getText());
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);
		}
	}

	public void timTheoSDT() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		List<KhachHang> list = dao.timTheoSoDienThoai(txtSdt.getText());
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);
		}
	}

	public void timTheoMa() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String ma = cboMaKhachHang.getSelectedItem().toString();
		List<KhachHang> list = dao.timTheoMa(ma);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);
		}
	}

	public void timTheoGioiTinh() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String gioitinh = cboGioiTinh.getSelectedItem().toString();
		List<KhachHang> list = dao.timTheoGioiTinh(gioitinh);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);
		}
	}

	public void timTheoTenvaGioiTinh() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String ten = txtTenKH.getText();
		String gioitinh = cboGioiTinh.getSelectedItem().toString();
		List<KhachHang> list = dao.timTheoTenvaGioiTinh(ten, gioitinh);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

	public void timTheoTenvaMa() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String ten = txtTenKH.getText();
		String ma = cboMaKhachHang.getSelectedItem().toString();
		List<KhachHang> list = dao.timTheoTenvaMa(ten, ma);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

	public void timTheoTenvaSDT() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String ten = txtTenKH.getText();
		String sdt = txtSdt.getText();
		List<KhachHang> list = dao.timTheoTenvaSDT(ten, sdt);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

	public void timTheoSDTvaMa() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String sdt = txtSdt.getText();
		String ma = cboMaKhachHang.getSelectedItem().toString();
		List<KhachHang> list = dao.timTheoSDTvaMa(sdt, ma);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

	public void timTheoSDTvaGioiTinh() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String sdt = txtSdt.getText();
		String gioitinh = cboGioiTinh.getSelectedItem().toString();
		List<KhachHang> list = dao.timTheoSDTvaGioiTinh(sdt, gioitinh);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

	public void timTheoGioiTinhvaMa() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String gioitinh = cboGioiTinh.getSelectedItem().toString();
		String ma = cboMaKhachHang.getSelectedItem().toString();
		List<KhachHang> list = dao.timTheogioivaMa(gioitinh, ma);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

	public void timTheoTenvaGioiTinhvaMa() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String gioitinh = cboGioiTinh.getSelectedItem().toString();
		String ma = cboMaKhachHang.getSelectedItem().toString();
		String ten = txtTenKH.getText();
		List<KhachHang> list = dao.timTheoTenvaGioiTinhvaMa(gioitinh, ten, ma);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

	public void timTheoTenvaSDTvaMa() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String sdt = txtSdt.getText();
		String ma = cboMaKhachHang.getSelectedItem().toString();
		String ten = txtTenKH.getText();
		List<KhachHang> list = dao.timTheoTenvaGioiTinhvaMa(sdt, ma, ten);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

	public void timTheoSDTvaMavaGioiTinh() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String sdt = txtSdt.getText();
		String ma = cboMaKhachHang.getSelectedItem().toString();
		String gioitinh = cboGioiTinh.getSelectedItem().toString();
		List<KhachHang> list = dao.timTheoSDTvaMavaGioiTinh(sdt, ma, gioitinh);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

	public void timTheoTenvaGioiTinhvaMavaSDT() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
    
		DAO_KhachHang dao = new DAO_KhachHang(entityManager);
		String sdt = txtSdt.getText();
		String ma = cboMaKhachHang.getSelectedItem().toString();
		String ten = txtTenKH.getText();
		String gioitinh = cboGioiTinh.getSelectedItem().toString();
		List<KhachHang> list = dao.timTheoTenvaGioiTinhvaMavaSDT(sdt, ma, ten, gioitinh);
		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang khachhang : list) {
			Object[] data = { khachhang.getMaKH(), khachhang.getHoTen(), khachhang.getNamSinh(), khachhang.getsDT(),
					khachhang.getGioiTinh(), khachhang.getDiaChi() };
			modelKhachHang.addRow(data);

		}
	}

}
