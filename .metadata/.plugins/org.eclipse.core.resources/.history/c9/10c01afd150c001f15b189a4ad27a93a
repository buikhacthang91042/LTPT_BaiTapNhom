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

import DAO.DAO_LoaiQuanAo;
import DAO.DAO_NhaCungCap;
import DAO.DAO_NhanVien;
import DAO.DAO_QuanAo;
import connect.ConnectDB;
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import entity.NhanVien;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI_CapNhatNhaCungCap extends JPanel {

	private JTextField txtMaNCC,txtTenNCC,txtDiachi,txtSdt;
	private JTable tblThongTinNhaCC;
	private DefaultTableModel modelThongTinNhaCungCap;
	private String ma;
	private List<NhaCungCap> list;
	private GUI_CapNhatQuanAo quanAo;
	private GUI_DatHang datHang;
	private GUI_TimKiemNhaCungCap timKiemNhaCC;
	private GUI_TimKiemQuanAo timKiemQuanAo;
	private int deletedRowCount = 1;

	public GUI_CapNhatNhaCungCap(GUI_CapNhatQuanAo quanAo, GUI_DatHang datHang,
			GUI_TimKiemNhaCungCap timKiemNhaCC, GUI_TimKiemQuanAo timKiemQuanAo) {
		setBackground(new Color(0, 64, 64));
		setLayout(null);
		
		this.quanAo = quanAo;
		this.datHang = datHang;
		this.timKiemNhaCC = timKiemNhaCC;
		this.timKiemQuanAo = timKiemQuanAo;
		
		JLabel lblNhacungcap = new JLabel("Nhà cung cấp");
		lblNhacungcap.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblNhacungcap.setBounds(512, 0, 256, 69);
		lblNhacungcap.setForeground(new Color(135, 206, 235));
		add(lblNhacungcap);

		// ConnectDB
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] tieude = { "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ",
				"Số điện thoại" };
		modelThongTinNhaCungCap = new DefaultTableModel(tieude, 0);
		JScrollPane scrThongTinNhaCungCap = new JScrollPane();
		scrThongTinNhaCungCap.setBounds(75, 415, 1126, 251);
		add(scrThongTinNhaCungCap);

		scrThongTinNhaCungCap.setViewportView(tblThongTinNhaCC = new JTable(
				modelThongTinNhaCungCap));
		scrThongTinNhaCungCap.setViewportView(tblThongTinNhaCC);
		tblThongTinNhaCC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = tblThongTinNhaCC.getSelectedRow();
				txtMaNCC.setText(tblThongTinNhaCC.getValueAt(row, 0).toString());
				txtTenNCC.setText(tblThongTinNhaCC.getValueAt(row, 1)
						.toString());
				txtDiachi.setText(tblThongTinNhaCC.getValueAt(row, 2)
						.toString());
				txtSdt.setText(tblThongTinNhaCC.getValueAt(row, 3).toString());

			}
		});

		JPanel pnlNhapThongTinNCC = new JPanel();
		pnlNhapThongTinNCC.setBounds(181, 79, 912, 267);
		add(pnlNhapThongTinNCC);
		pnlNhapThongTinNCC.setLayout(null);

		JButton btnthem = new JButton("THÊM");
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validData()) {
					txtMaNCC.setText(taoMa());
					themNhaCC();

					quanAo.updateLaiComboNhaCungCap();
					
					timKiemNhaCC.updateLaiComboMaNhaCungCap();
					timKiemNhaCC.updateLaiData();
					timKiemQuanAo.updateLaiComboNhaCungCap();
					xoaTrang();
				}
			}
		});
		btnthem.setBounds(81, 193, 101, 42);
		pnlNhapThongTinNCC.add(btnthem);
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnthem.setForeground(new Color(50, 205, 50));
		btnthem.setBackground(new Color(0, 250, 154));

		JButton btnxoa = new JButton("XÓA");
		btnxoa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int row = tblThongTinNhaCC.getSelectedRow();
		        if (row == -1) {
		            JOptionPane.showMessageDialog(GUI_CapNhatNhaCungCap.this, "Vui lòng chọn nhà cung cấp cần xóa!");
		        } else {
		            int confirmed = JOptionPane.showConfirmDialog(GUI_CapNhatNhaCungCap.this,
		                    "Bạn có chắc chắn muốn xóa nhà cung cấp này không?", "Cảnh báo",
		                    JOptionPane.YES_NO_OPTION);

		            if (confirmed == JOptionPane.YES_OPTION) {
		                
		                xoaNhaCungCap();
		                quanAo.updateLaiComboNhaCungCap();
		                
		                timKiemNhaCC.updateLaiComboMaNhaCungCap();
		                timKiemNhaCC.updateLaiData();
		                timKiemQuanAo.updateLaiComboNhaCungCap();
		            }
		            xoaTrang();
		        }
		    }
		});

		btnxoa.setBounds(257, 193, 101, 42);
		pnlNhapThongTinNCC.add(btnxoa);
		btnxoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnxoa.setForeground(new Color(50, 205, 50));
		btnxoa.setBackground(new Color(0, 250, 154));

		JButton btnsua = new JButton("SỬA");
		btnsua.setBounds(500, 193, 101, 42);
		pnlNhapThongTinNCC.add(btnsua);
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblThongTinNhaCC.getSelectedRow();
				if (row >= 0) {
					if (validDataSua()) {
						int n = JOptionPane.showConfirmDialog(null, "Chắc chắn sửa ?","Cảnh báo", JOptionPane.YES_NO_OPTION);
						if(n == JOptionPane.YES_OPTION) {
							suaNhaCungCap();
							quanAo.updateLaiComboNhaCungCap();
							
							timKiemNhaCC.updateLaiData();
							timKiemQuanAo.updateLaiComboNhaCungCap();
						}else return;
					}
				} else if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần sửa");
				}
			}
		});
		btnsua.setForeground(new Color(50, 205, 50));
		btnsua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnsua.setBackground(new Color(0, 250, 154));

		JButton btnxoatrang = new JButton("XÓA TRẮNG");
		btnxoatrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnxoatrang.setBounds(680, 193, 123, 42);
		pnlNhapThongTinNCC.add(btnxoatrang);
		btnxoatrang.setForeground(new Color(50, 205, 50));
		btnxoatrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnxoatrang.setBackground(new Color(0, 250, 154));

		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp");
		lblMaNCC.setBounds(47, 10, 145, 51);
		pnlNhapThongTinNCC.add(lblMaNCC);
		lblMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Arial", Font.BOLD, 20));
		txtMaNCC.setBounds(197, 13, 240, 51);
		txtMaNCC.setEditable(false);
		pnlNhapThongTinNCC.add(txtMaNCC);
		txtMaNCC.setColumns(10);

		JLabel lblDiachi = new JLabel("Địa chỉ");
		lblDiachi.setBounds(500, 10, 145, 51);
		pnlNhapThongTinNCC.add(lblDiachi);
		lblDiachi.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtDiachi = new JTextField();
		txtDiachi.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDiachi.setBounds(618, 13, 240, 51);
		pnlNhapThongTinNCC.add(txtDiachi);
		txtDiachi.setColumns(10);

		JLabel lblTenNCC = new JLabel("Tên nhà cung cấp");
		lblTenNCC.setBounds(47, 105, 151, 51);
		pnlNhapThongTinNCC.add(lblTenNCC);
		lblTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtTenNCC = new JTextField();
		txtTenNCC.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTenNCC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtTenNCC.getText().equals("")) {
					txtMaNCC.setText(taoMa());
				}
			}
		});
		txtTenNCC.setBounds(197, 108, 240, 51);
		pnlNhapThongTinNCC.add(txtTenNCC);
		txtTenNCC.setColumns(10);

		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setBounds(499, 105, 145, 51);
		pnlNhapThongTinNCC.add(lblSdt);
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSdt.setBounds(618, 108, 240, 51);
		pnlNhapThongTinNCC.add(txtSdt);
		txtSdt.setColumns(10);

		JLabel lblDanhSchNh = new JLabel("Danh sách nhà cung cấp");
		lblDanhSchNh.setForeground(new Color(135, 206, 235));
		lblDanhSchNh.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDanhSchNh.setBounds(75, 372, 256, 49);
		add(lblDanhSchNh);
		updateData();

	}
	
	//Hàm thêm
	public void themNhaCC() {
		DAO_NhaCungCap NhaCC_dao = new DAO_NhaCungCap();
		String maNCC = txtMaNCC.getText().trim();
		String tenNCC = txtTenNCC.getText().trim();
		String diaChi = txtDiachi.getText().trim();
		String soDienThoai = txtSdt.getText().trim();
		NhaCungCap NCC = new NhaCungCap(maNCC, tenNCC, diaChi, soDienThoai);
		if (NhaCC_dao.create(NCC)) {
			Object[] data = { maNCC, tenNCC, diaChi, soDienThoai };
			modelThongTinNhaCungCap.addRow(data);
		}
	}

	//Hàm kiểm tra
	public boolean kiemTraTrungSoDienThoai(String sdt) {
		DAO_NhaCungCap dao = new DAO_NhaCungCap();
		List<NhaCungCap> list = dao.getAllNhaCungCap();

		for (NhaCungCap ncc : list) {
			if (ncc.getsDT().equals(sdt)) {
				return true;
			}
		}

		return false;
	}

	private boolean validData() {
		String Tenncc = txtTenNCC.getText().trim();
		String sdt = txtSdt.getText().trim();
		String diaChi = txtDiachi.getText().trim();

		if (Tenncc.trim().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Tên nhà cung cấp không được rỗng!");
			txtTenNCC.selectAll();
			txtTenNCC.requestFocus();
			return false;
		} else if (Tenncc.matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(this,
					"Tên nhà cung cấp chỉ chứa kí tự chữ");
			txtTenNCC.requestFocus();
			txtTenNCC.selectAll();
			return false;
		} else {

			if ((Tenncc
					.matches("^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane
						.showMessageDialog(this,
								"Tên nhà cung cấp gồm chữ cái, có thể chứa khoảng trắng");
				txtTenNCC.requestFocus();
				txtTenNCC.selectAll();
				return false;
			}
		}

		if (sdt.trim().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại nhà cung cấp không được rỗng !");
			txtSdt.selectAll();
			txtSdt.requestFocus();
			return false;
		} else if (!sdt.matches("^[0-9]{10}$")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại chỉ chứa 10 kí tự số");
			txtSdt.requestFocus();
			txtSdt.selectAll();
			return false;
		} else if (kiemTraTrungSoDienThoai(sdt)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
			txtSdt.requestFocus();
			txtSdt.selectAll();
			return false;
		}

		if (diaChi.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng!");
			txtDiachi.selectAll();
			txtDiachi.requestFocus();
			return false;
		} 

		return true;
	}

	private boolean validDataSua() {
		String Tenncc = txtTenNCC.getText().trim();
		String sdt = txtSdt.getText().trim();
		String diaChi = txtDiachi.getText().trim();

		if (Tenncc.trim().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Tên nhà cung cấp không được rỗng!");
			txtTenNCC.selectAll();
			txtTenNCC.requestFocus();
			return false;
		} else if (Tenncc.matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(this,
					"Tên nhà cung cấp chỉ chứa kí tự chữ");
			txtTenNCC.requestFocus();
			txtTenNCC.selectAll();
			return false;
		} else {

			if ((Tenncc
					.matches("^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane
						.showMessageDialog(this,
								"Tên nhà cung cấp gồm chữ cái, có thể chứa khoảng trắng");
				txtTenNCC.requestFocus();
				txtTenNCC.selectAll();
				return false;
			}
		}

		if (sdt.trim().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại nhà cung cấp không được rỗng !");
			txtSdt.selectAll();
			txtSdt.requestFocus();
			return false;
		} else if (!sdt.matches("^[0-9]{10}$")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại chỉ chứa 10 kí tự số");
			txtSdt.requestFocus();
			txtSdt.selectAll();
			return false;
		}
		if (diaChi.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng!");
			txtDiachi.selectAll();
			txtDiachi.requestFocus();
			return false;
		} else {

			if (diaChi
					.matches("^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]")) {
				JOptionPane
						.showMessageDialog(this,
								"Địa chỉ nhà cung cấp gồm chữ cái, có thể chứa khoảng trắng");
				txtDiachi.requestFocus();
				txtDiachi.selectAll();
				return false;

			}
		}

		return true;
	}
	//Hàm cập nhật
	public void updateData() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap();
		List<NhaCungCap> list = dao.getAllNhaCungCap();

		for (NhaCungCap ncc : list) {
			Object[] data = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(),
					ncc.getsDT() };
			modelThongTinNhaCungCap.addRow(data);
		}

	}
	
	//Hàm xóa
	public void xoaTrang() {
		txtMaNCC.setText("");
		txtTenNCC.setText("");
		txtDiachi.setText("");
		txtSdt.setText("");
	}

	public void xoaNhaCungCap() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap();
		int row = tblThongTinNhaCC.getSelectedRow();
		String maNCC = tblThongTinNhaCC.getValueAt(row, 0).toString();
		if (dao.delete(maNCC)) {
			modelThongTinNhaCungCap.removeRow(row);

			// Cập nhật lại maNCC của các dòng từ dòng đã xóa trở đi
			for (int i = row; i < modelThongTinNhaCungCap.getRowCount(); i++) {
				String currentMaNCC = (String) modelThongTinNhaCungCap
						.getValueAt(i, 0);
				int newNumber = Integer.parseInt(currentMaNCC.substring(3))
						- deletedRowCount;
				String newMaNCC = "NCC" + String.format("%03d", newNumber);
				modelThongTinNhaCungCap.setValueAt(newMaNCC, i, 0);
			}

			// Cập nhật toàn bộ mã NCC trong cơ sở dữ liệu
			dao.updateAllMaNCC(deletedRowCount, maNCC);
		}
	}
	
	//Hàm sửa
	public void suaNhaCungCap() {
		DAO_NhaCungCap dao = new DAO_NhaCungCap();
		String maNCC = txtMaNCC.getText().trim();
		String tenNCC = txtTenNCC.getText().trim();
		String diaChi = txtDiachi.getText().trim();
		String soDienThoai = txtSdt.getText().trim();
		NhaCungCap NCC = new NhaCungCap(maNCC, tenNCC, diaChi, soDienThoai);
		if (dao.update(NCC)) {
			JOptionPane.showMessageDialog(this, "Sửa thành công");
			String[] data = { maNCC, tenNCC, diaChi, soDienThoai };
			modelThongTinNhaCungCap.addRow(data);
			int row = tblThongTinNhaCC.getSelectedRow();
			modelThongTinNhaCungCap.removeRow(row);
		}

	}

	
	
	

	// Tạo mã tăng tự động
	public String taoMa() {

		DAO_NhaCungCap dao = new DAO_NhaCungCap();

		int n = dao.getAllNhaCungCap().size();
		if (n < 9) {
			do {
				n = n + 1;

				ma = "NCC00" + String.valueOf(n);
				list = dao.getAllNhaCungCap();
			} while (list.contains(ma));

		} else if (n < 99) {
			do {
				n = n + 1;

				ma = "NCC0" + String.valueOf(n);
				list = dao.getAllNhaCungCap();
			} while (list.contains(ma));
		} else if (n < 999) {
			do {
				n = n + 1;

				ma = "NCC" + String.valueOf(n);
				list = dao.getAllNhaCungCap();
			} while (list.contains(ma));
		}
		return ma;
	}
}
