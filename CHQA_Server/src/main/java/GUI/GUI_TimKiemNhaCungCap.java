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

import DAO.DAO_KhachHang;
import DAO.DAO_NhaCungCap;
import DAO.EntityManagerFactoryUtil;
import connect.ConnectDB;
import entity.KhachHang;
import entity.NhaCungCap;
import jakarta.persistence.EntityManager;

import javax.swing.DefaultComboBoxModel;

public class GUI_TimKiemNhaCungCap extends JPanel {
	private JTextField txtTenNCC;
	private JTextField txtSdt;
	private JTextField txtDiaChi;
	private JComboBox cboMaNhaCungCap;
	private DefaultTableModel modelTimKiemNhaCC;
	private JTable tblNhaCC;
	private GUI_CapNhatNhaCungCap quanLiNhaCC;
	EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
    EntityManager entityManager = util.getEnManager();
	public GUI_TimKiemNhaCungCap() {
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

		JLabel lblTenNhaCungCap = new JLabel("Tên nhà cung cấp");
		lblTenNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenNhaCungCap.setBounds(77, 35, 225, 32);
		pnForm.add(lblTenNhaCungCap);

		txtTenNCC = new JTextField();
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(217, 35, 225, 32);
		pnForm.add(txtTenNCC);

		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSdt.setBounds(77, 96, 225, 32);
		pnForm.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setColumns(10);
		txtSdt.setBounds(217, 96, 225, 32);
		pnForm.add(txtSdt);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDiaChi.setBounds(533, 96, 225, 32);
		pnForm.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(673, 96, 225, 32);
		pnForm.add(txtDiaChi);

		JLabel lblMaNhaCungCap = new JLabel("Mã nhà cung cấp");
		lblMaNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaNhaCungCap.setBounds(533, 35, 129, 32);
		pnForm.add(lblMaNhaCungCap);

		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.setForeground(UIManager.getColor("Button.light"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtTenNCC.getText().isEmpty() && txtSdt.getText().isEmpty()
						&& txtDiaChi.getText().isEmpty()
						&& cboMaNhaCungCap.getSelectedItem().equals("Không")) {
					updateLaiData();
					// Show a message indicating that search data is required
					JOptionPane.showMessageDialog(GUI_TimKiemNhaCungCap.this,
							"Vui lòng nhập dữ liệu tìm kiếm");
				}

				else if ((!txtTenNCC.equals(""))
						&& (cboMaNhaCungCap.getSelectedItem().equals("Không"))
						&& (txtSdt.getText().isEmpty())
						&& txtDiaChi.getText().isEmpty()) {
					timTheoTen();

				} else if ((txtTenNCC.getText().isEmpty())
						&& (txtSdt.getText().isEmpty())
						&& (cboMaNhaCungCap.getSelectedItem().toString()
								.equals("Không"))) {
					timTheoDiaChi();

				} else if ((txtTenNCC.getText().isEmpty())
						&& (cboMaNhaCungCap.getSelectedItem().equals("Không"))
						&& (txtDiaChi.getText().isEmpty())) {
					timTheoSDT();

				} else if ((txtTenNCC.getText().isEmpty())
						&& (txtSdt.getText().isEmpty())
						&& (txtDiaChi.getText().isEmpty())) {
					timTheoMa();

				} else if ((!txtTenNCC.equals(""))
						&& txtDiaChi.getText().isEmpty()
						&& txtSdt.getText().isEmpty()) {
					timTheoTenvaMa();

				} else if ((!txtTenNCC.equals(""))
						&& (cboMaNhaCungCap.getSelectedItem().equals("Không"))
						&& txtSdt.getText().isEmpty()) {
					timTheoTenvaDiaChi();

				} else if ((!txtTenNCC.equals(""))
						&& ((txtDiaChi.getText().isEmpty()) && (cboMaNhaCungCap
								.getSelectedItem().equals("Không")))) {
					timTheoTenvaSDT();

				} else if (txtTenNCC.getText().isEmpty()
						&& (cboMaNhaCungCap.getSelectedItem().equals("Không"))) {
					timTheoSDTvaDiaChi();

				} else if ((txtTenNCC.getText().isEmpty())
						&& (txtDiaChi.getText().isEmpty())) {
					timTheoSDTvaMa();

				} else if ((txtSdt.getText().isEmpty())
						&& txtTenNCC.getText().isEmpty()) {
					timTheoDiaChivaMa();

				} else if (txtSdt.getText().isEmpty()) {
					timTheoTenvaDiaChivaMa();

				} else if (txtDiaChi.getText().isEmpty()) {
					timTheoTenvaSDTvaMa();

				} else if (txtTenNCC.getText().isEmpty()) {
					timTheoSDTvaMavadiaChi();

				} else {
					timTheoTenvaDiaChivaMavaSDT();
				}
			}
		});

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(102, 102, 255));
		btnNewButton.setBounds(673, 158, 180, 32);
		pnForm.add(btnNewButton);

		cboMaNhaCungCap = new JComboBox();
		cboMaNhaCungCap.setModel(new DefaultComboBoxModel(
				new String[] { "Không" }));
		cboMaNhaCungCap.setBounds(673, 43, 85, 21);
		pnForm.add(cboMaNhaCungCap);

		String[] tieude = { "Mã nhà cung cấp", "Tên nhà cung cấp",
				"Số điện thoại", "Địa chỉ" };

		modelTimKiemNhaCC = new DefaultTableModel(tieude, 0);
		JScrollPane scrThongTinNhaCC = new JScrollPane();
		scrThongTinNhaCC.setBounds(54, 341, 1195, 310);
		add(scrThongTinNhaCC);

		scrThongTinNhaCC.setViewportView(tblNhaCC = new JTable(
				modelTimKiemNhaCC));
		scrThongTinNhaCC.setViewportView(tblNhaCC);

		JLabel lblTieuDe = new JLabel("Tìm kiếm nhà cung cấp");
		lblTieuDe.setForeground(new Color(135, 206, 235));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblTieuDe.setBounds(527, 10, 224, 50);
		add(lblTieuDe);

		JLabel lblTieuDeThongTin = new JLabel("Danh sách nhà cung cấp");
		lblTieuDeThongTin.setForeground(new Color(135, 206, 235));
		lblTieuDeThongTin
				.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblTieuDeThongTin.setBounds(51, 295, 245, 50);
		add(lblTieuDeThongTin);

		updateComboMaNhaCungCap();
		updateLaiComboMaNhaCungCap();

		updateData();
	}

	public void updateData() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		List<NhaCungCap> list = dao.getAllNhaCungCap();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}

	}

	public void updateLaiData() {
		modelTimKiemNhaCC.setRowCount(0);
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		List<NhaCungCap> list = dao.getAllNhaCungCap();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}

	}

	public void updateComboMaNhaCungCap() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		for (NhaCungCap loai : dao.getAllNhaCungCap()) {
			cboMaNhaCungCap.addItem(loai.getMaNCC());
		}
	}

	public void updateLaiComboMaNhaCungCap() {
		cboMaNhaCungCap.removeAllItems();
		cboMaNhaCungCap.addItem("Không");
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		for (NhaCungCap loai : dao.getAllNhaCungCap()) {
			cboMaNhaCungCap.addItem(loai.getMaNCC());
		}
	}

	// Các hàm tìm
	public void timTheoTen() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String ten = txtTenNCC.getText();

		List<NhaCungCap> list = dao.timTheoTen(ten);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoSDT() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String sdt = txtSdt.getText();

		List<NhaCungCap> list = dao.timTheoSoDienThoai(sdt);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoMa() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String ma = cboMaNhaCungCap.getSelectedItem().toString();

		List<NhaCungCap> list = dao.timTheoMa(ma);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoDiaChi() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String diaChi = txtDiaChi.getText();

		List<NhaCungCap> list = dao.timTheoDiaChi(diaChi);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoTenvaDiaChi() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String diaChi = txtDiaChi.getText();
		String ten = txtTenNCC.getText();

		List<NhaCungCap> list = dao.timTheoTenvaDiaChi(ten, diaChi);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoTenvaMa() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String ma = cboMaNhaCungCap.getSelectedItem().toString();
		String ten = txtTenNCC.getText();

		List<NhaCungCap> list = dao.timTheoTenvaMa(ten, ma);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoTenvaSDT() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String sdt = txtSdt.getText();
		String ten = txtTenNCC.getText();

		List<NhaCungCap> list = dao.timTheoTenvaSDT(ten, sdt);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoSDTvaMa() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String sdt = txtSdt.getText();
		String ma = cboMaNhaCungCap.getSelectedItem().toString();

		List<NhaCungCap> list = dao.timTheoSDTvaMa(sdt, ma);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoSDTvaDiaChi() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String sdt = txtSdt.getText();
		String diaChi = txtDiaChi.getText();

		List<NhaCungCap> list = dao.timTheoSDTvaDiaChi(sdt, diaChi);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoDiaChivaMa() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String diaChi = txtDiaChi.getText();
		String ma = cboMaNhaCungCap.getSelectedItem().toString();

		List<NhaCungCap> list = dao.timTheoDiaChivaMa(diaChi, ma);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoTenvaDiaChivaMa() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String diaChi = txtDiaChi.getText();
		String ma = cboMaNhaCungCap.getSelectedItem().toString();

		String ten = txtTenNCC.getText();
		List<NhaCungCap> list = dao.timTheoTenvaDiaChivaMa(ten, diaChi, ma);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoTenvaSDTvaMa() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String sdt = txtSdt.getText();
		String ma = cboMaNhaCungCap.getSelectedItem().toString();

		String ten = txtTenNCC.getText();
		List<NhaCungCap> list = dao.timTheoTenvaSDTvaMa(ten, sdt, ma);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoSDTvaMavadiaChi() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String sdt = txtSdt.getText();
		String ma = cboMaNhaCungCap.getSelectedItem().toString();

		String diaChi = txtDiaChi.getText();
		List<NhaCungCap> list = dao.timTheoSDTvaMavaDiaChi(ma, sdt, diaChi);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);
		}
	}

	public void timTheoTenvaDiaChivaMavaSDT() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap(entityManager);
		String sdt = txtSdt.getText();
		String ma = cboMaNhaCungCap.getSelectedItem().toString();
		String ten = txtTenNCC.getText();
		String diaChi = txtDiaChi.getText();
		List<NhaCungCap> list = dao.timTheoTenvaDiaChivaMavaSDT(ten, diaChi,
				ma, sdt);
		modelTimKiemNhaCC.getDataVector().removeAllElements();
		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getsDT(),
					ncc.getDiaChi() };
			modelTimKiemNhaCC.addRow(data);

		}
	}
}
