package GUI;

import javax.swing.JPanel;

import java.awt.Rectangle;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.SystemColor;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_NhanVien;
import DAO.DAO_QuanAo;
import DAO.EntityManagerFactoryUtil;
import connect.ConnectDB;

import javax.swing.DefaultComboBoxModel;

import entity.NhanVien;
import entity.QuanAo;
import jakarta.persistence.EntityManager;

public class GUI_TimKiemNhanVien extends JPanel {
	private JTextField txtTenNV;
	private JTextField txtSdt;
	private JTextField txtNamSinh;
	private DefaultTableModel model;
	private JTable tblThongTin;
	private JComboBox cboGioiTinh, cboMaNhanVien;

	public GUI_TimKiemNhanVien() {
		setBounds(new Rectangle(0, 0, 1308, 678));
		setLayout(null);
		setBackground(new Color(0, 64, 64));
		JPanel pnForm = new JPanel();
		pnForm.setBounds(134, 56, 1019, 200);
		add(pnForm);
		pnForm.setLayout(null);

		// ConnectDB
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblTenNhanVien = new JLabel("Tên nhân viên");
		lblTenNhanVien.setFont(new Font("Arial", Font.BOLD, 15));
		lblTenNhanVien.setBounds(77, 35, 225, 32);
		pnForm.add(lblTenNhanVien);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(217, 35, 225, 32);
		pnForm.add(txtTenNV);

		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setFont(new Font("Arial", Font.BOLD, 15));
		lblSdt.setBounds(77, 96, 225, 32);
		pnForm.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSdt.setColumns(10);
		txtSdt.setBounds(217, 96, 225, 32);
		pnForm.add(txtSdt);

		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setFont(new Font("Arial", Font.BOLD, 15));
		lblMaNV.setBounds(533, 96, 225, 32);
		pnForm.add(lblMaNV);

		JLabel lblNamSinh = new JLabel("Năm sinh");
		lblNamSinh.setFont(new Font("Arial", Font.BOLD, 15));
		lblNamSinh.setBounds(77, 155, 225, 32);
		pnForm.add(lblNamSinh);

		txtNamSinh = new JTextField();
		txtNamSinh.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNamSinh.setColumns(10);
		txtNamSinh.setBounds(217, 155, 225, 32);
		pnForm.add(txtNamSinh);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setFont(new Font("Arial", Font.BOLD, 14));
		lblGioiTinh.setBounds(533, 35, 90, 32);
		pnForm.add(lblGioiTinh);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Arial", Font.PLAIN, 12));
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Không", "Nam", "Nữ"}));
	
		cboGioiTinh.setBounds(672, 35, 225, 32);
		pnForm.add(cboGioiTinh);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setForeground(UIManager.getColor("Button.light"));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtTenNV.getText().isEmpty()
		                && txtSdt.getText().isEmpty()
		                && txtNamSinh.getText().isEmpty()
		                && cboMaNhanVien.getSelectedItem().toString().equals("Không")
		                && cboGioiTinh.getSelectedItem().toString().equals("Không")) {
					updateData();
		            JOptionPane.showMessageDialog(GUI_TimKiemNhanVien.this, "Vui lòng nhập thông tin tìm kiếm.");
		        }
				
				else if ((txtTenNV.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("KhÃ´ng"))
						&& (cboGioiTinh.getSelectedItem().toString()
								.equals("KhÃ´ng lá»±a chá»n"))
						&& (txtSdt.getText().isEmpty())
						&& (txtNamSinh.getText().isEmpty())) {
					updateData();

				}

				else if ((!txtTenNV.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (cboGioiTinh.getSelectedItem().toString()
								.equals("Không"))
						&& (txtSdt.getText().isEmpty())
						&& (txtNamSinh.getText().isEmpty())) {
					timTheoTen();

				}

				else if ((!txtSdt.getText().isEmpty())
						&& (txtTenNV.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (cboGioiTinh.getSelectedItem().toString()
								.equals("Không"))
						&& (txtNamSinh.getText().isEmpty())) {
					timTheoSdt();

				}

				else if ((txtSdt.getText().isEmpty())
						&& (txtTenNV.getText().isEmpty())
						&& (cboGioiTinh.getSelectedItem().toString()
								.equals("Không"))
						&& (txtNamSinh.getText().isEmpty())) {
					timTheoMa();

				}

				else if ((txtSdt.getText().isEmpty())
						&& (txtTenNV.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (txtNamSinh.getText().isEmpty())) {
					timTheoGT();

				}

				else if ((!txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (cboGioiTinh.getSelectedItem().toString()
								.equals("Không"))
						&& (txtSdt.getText().isEmpty())
						&& (txtTenNV.getText().isEmpty())) {
					timTheoNamSinh();

				}

				else if ((txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (txtSdt.getText().isEmpty())
						&& (!txtTenNV.getText().isEmpty())) {
					timTheoTenvaGT();

				}

				else if ((!txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (cboGioiTinh.getSelectedItem().toString()
								.equals("Không"))
						&& (txtSdt.getText().isEmpty())
						&& (!txtTenNV.getText().isEmpty())) {
					timTheoTenvaNamSinh();

				}

				else if ((txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (cboGioiTinh.getSelectedItem().toString()
								.equals("Không"))
						&& (!txtSdt.getText().isEmpty())
						&& (!txtTenNV.getText().isEmpty())) {
					timTheoTenvaSdt();

				}
				
				else if ((!txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (txtSdt.getText().isEmpty())
						&& (txtTenNV.getText().isEmpty())) {
					timTheoNamSinhvaGioiTinh();

				}
				
				else if ((!txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (cboGioiTinh.getSelectedItem().toString()
								.equals("Không"))
						&& (!txtSdt.getText().isEmpty())
						&& (txtTenNV.getText().isEmpty())) {
					timTheoNamSinhvaSdt();

				}
				
				else if ((!txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (txtSdt.getText().isEmpty())
						&& (!txtTenNV.getText().isEmpty())) {
					timTheoTenvaNamSinhvaGioiTinh();

				}
				
				else if ((txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (!txtSdt.getText().isEmpty())
						&& (!txtTenNV.getText().isEmpty())) {
					timTheoTenvaGioiTinhvaSdt();

				}
				
				else if ((!txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (cboGioiTinh.getSelectedItem().toString()
								.equals("Không"))
						&& (!txtSdt.getText().isEmpty())
						&& (!txtTenNV.getText().isEmpty())) {
					timTheoTenvaNamSinhvaSdt();

				}
				
				else if ((!txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (!txtSdt.getText().isEmpty())
						&& (txtTenNV.getText().isEmpty())) {
					timTheoNamSinhvaGioiTinhvaSdt();

				}
				
				else if ((!txtNamSinh.getText().isEmpty())
						&& (cboMaNhanVien.getSelectedItem().toString()
								.equals("Không"))
						&& (!txtSdt.getText().isEmpty())
						&& (!txtTenNV.getText().isEmpty())) {
					timTheoTenvaNamSinhvaGioiTinhvaSdt();

				}
				
				else {
					timTheoAll();

				}

			}
		});

		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setBackground(new Color(102, 102, 255));
		btnTimKiem.setBounds(673, 155, 180, 32);
		pnForm.add(btnTimKiem);

		cboMaNhanVien = new JComboBox();
		cboMaNhanVien.setFont(new Font("Arial", Font.PLAIN, 12));
		cboMaNhanVien.setModel(new DefaultComboBoxModel(new String[] {"Không"}));
		cboMaNhanVien.setBounds(672, 96, 225, 32);
		pnForm.add(cboMaNhanVien);

		JScrollPane scrThongTinNhanVien = new JScrollPane();
		scrThongTinNhanVien.setBounds(54, 341, 1195, 310);
		add(scrThongTinNhanVien);

		String[] tieude = { "Mã nhân viên", "Tên nhân viên", "Năm sinh",
				"Giới tính", "Ngày vào làm", "Số điện thoại" };
		model = new DefaultTableModel(tieude, 0);
		JScrollPane scrThongTin = new JScrollPane();
		scrThongTinNhanVien.setBounds(38, 311, 1232, 322);
		add(scrThongTinNhanVien);
		scrThongTinNhanVien.setViewportView(tblThongTin = new JTable(model));
		scrThongTinNhanVien.setViewportView(tblThongTin);

		JLabel lblTieuDe = new JLabel("Tìm kiếm nhân viên");
		lblTieuDe.setForeground(new Color(135, 206, 235));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblTieuDe.setBounds(527, 10, 224, 50);
		add(lblTieuDe);

		JLabel lblTieuDeThongTin = new JLabel("Danh sách nhân viên");
		lblTieuDeThongTin.setForeground(new Color(135, 206, 235));
		lblTieuDeThongTin
				.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblTieuDeThongTin.setBounds(38, 274, 224, 50);
		add(lblTieuDeThongTin);

		updateComboMaNV();
		updateData();

	}

	public void updateComboMaNV() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		for (NhanVien nv : dao.getAllNV()) {
			cboMaNhanVien.addItem(nv.getMaNV());
		}
	}

	public void updateData() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien da = new DAO_NhanVien(entityManager);
		List<NhanVien> list = da.getAllNV();
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);
		}

	}

	public void timTheoTen() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		List<NhanVien> list = dao.timTheoTen(txtTenNV.getText());
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);
		}
	}

	public void timTheoNamSinh() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		List<NhanVien> list = dao.timTheoNamSinh(txtNamSinh.getText());
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);
		}
	}

	public void timTheoSdt() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		List<NhanVien> list = dao.timTheoSdt(txtSdt.getText());
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);
		}
	}

	public void timTheoMa() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ma = cboMaNhanVien.getSelectedItem().toString();
		List<NhanVien> list = dao.timTheoMa(ma);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}

	}

	public void timTheoGT() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String gt = cboGioiTinh.getSelectedItem().toString();
		List<NhanVien> list = dao.timTheoGT(gt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}

	}

	public void timTheoTenvaGT() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ten = txtTenNV.getText();
		String gt = cboGioiTinh.getSelectedItem().toString();
		List<NhanVien> list = dao.timTheoTenvaGT(ten, gt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoTenvaNamSinh() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ten = txtTenNV.getText();
		String ns = txtNamSinh.getText();
		List<NhanVien> list = dao.timTheoTenvaNamSinh(ten, ns);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoTenvaSdt() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ten = txtTenNV.getText();
		String sdt = txtSdt.getText();
		List<NhanVien> list = dao.timTheoTenvaSdt(ten, sdt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoNamSinhvaGioiTinh() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ns = txtNamSinh.getText();
		String gt = cboGioiTinh.getSelectedItem().toString();
		List<NhanVien> list = dao.timTheoNamSinhvaGT(ns, gt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoNamSinhvaSdt() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ns = txtNamSinh.getText();
		String sdt = txtSdt.getText();
		List<NhanVien> list = dao.timTheoNamSinhvaSdt(ns, sdt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoTenvaNamSinhvaGioiTinh() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ten = txtTenNV.getText();
		String ns = txtNamSinh.getText();
		String gt = cboGioiTinh.getSelectedItem().toString();
		List<NhanVien> list = dao.timTheoTenvaNamSinhvaGT(ten, ns, gt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoTenvaGioiTinhvaSdt() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ten = txtTenNV.getText();
		String gt = cboGioiTinh.getSelectedItem().toString();
		String sdt = txtSdt.getText();
		List<NhanVien> list = dao.timTheoTenvaGTvaSdt(ten, gt, sdt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoTenvaNamSinhvaSdt() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ten = txtTenNV.getText();
		String ns = txtNamSinh.getText();
		String sdt = txtSdt.getText();
		List<NhanVien> list = dao.timTheoTenvaNamSinhvaSdt(ten, ns, sdt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoNamSinhvaGioiTinhvaSdt() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ns = txtNamSinh.getText();
		String gt = cboGioiTinh.getSelectedItem().toString();
		String sdt = txtSdt.getText();
		List<NhanVien> list = dao.timTheoNamSinhvaGTvaSdt(ns, gt, sdt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoTenvaNamSinhvaGioiTinhvaSdt() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ten = txtTenNV.getText();
		String ns = txtNamSinh.getText();
		String gt = cboGioiTinh.getSelectedItem().toString();
		String sdt = txtSdt.getText();
		List<NhanVien> list = dao
				.timTheoTenvaNamSinhvaGTvaSdt(ten, ns, gt, sdt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

	public void timTheoAll() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String ma = cboMaNhanVien.getSelectedItem().toString();
		String ten = txtTenNV.getText();
		String ns = txtNamSinh.getText();
		String gt = cboGioiTinh.getSelectedItem().toString();
		String sdt = txtSdt.getText();
		List<NhanVien> list = dao.timTheoAll(ma, ten, ns, gt, sdt);
		model.getDataVector().removeAllElements();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);

		}
	}

}
