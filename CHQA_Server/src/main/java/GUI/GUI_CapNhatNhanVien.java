package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_DangNhap;
import DAO.DAO_NhanVien;
import DAO.DAO_QuanAo;
import DAO.EntityManagerFactoryUtil;
import connect.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.toedter.calendar.JDateChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI_CapNhatNhanVien extends JPanel {
	private JTextField txtMaNhanVien,txtNamSinh,txtHoTen,txtSoDienThoai;
	private JDateChooser dcNgayVaoLam;
	private DefaultTableModel model;
	private JTable tblThongTinNhanVien;
	private JComboBox cboGioiTinh;
	private String ma;
	private List<NhanVien> list;
	EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
    EntityManager entityManager = util.getEnManager();
	public GUI_CapNhatNhanVien() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBackground(new Color(0, 64, 64));
		setLayout(null);

		JLabel lblTieuDe = new JLabel("Quản lí nhân viên");
		lblTieuDe.setBounds(450, 10, 454, 70);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 50));
		lblTieuDe.setForeground(new Color(135, 206, 235));
		add(lblTieuDe);

		JPanel pnlNhapThongTin = new JPanel();
		pnlNhapThongTin.setBounds(300, 100, 700, 150);
		add(pnlNhapThongTin);
		pnlNhapThongTin.setLayout(null);

		JLabel lblMaNhanVien = new JLabel("Mã nhân viên");
		lblMaNhanVien.setFont(new Font("Arial", Font.BOLD, 20));
		lblMaNhanVien.setBounds(10, 17, 136, 24);
		pnlNhapThongTin.add(lblMaNhanVien);

		JLabel lblHoTen = new JLabel("Họ tên");
		lblHoTen.setFont(new Font("Arial", Font.BOLD, 20));
		lblHoTen.setBounds(10, 68, 136, 24);
		pnlNhapThongTin.add(lblHoTen);

		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("Arial", Font.BOLD, 20));
		lblSoDienThoai.setBounds(10, 108, 136, 38);
		pnlNhapThongTin.add(lblSoDienThoai);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(161, 16, 124, 24);
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setText(taoMa());
		pnlNhapThongTin.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(161, 68, 124, 24);
		pnlNhapThongTin.add(txtHoTen);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(161, 115, 124, 24);
		pnlNhapThongTin.add(txtSoDienThoai);

		JLabel lblNamSinh = new JLabel("Năm sinh");
		lblNamSinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblNamSinh.setBounds(400, 17, 136, 24);
		pnlNhapThongTin.add(lblNamSinh);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblGioiTinh.setBounds(400, 62, 136, 24);
		pnlNhapThongTin.add(lblGioiTinh);

		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm");
		lblNgayVaoLam.setFont(new Font("Arial", Font.BOLD, 20));
		lblNgayVaoLam.setBounds(400, 108, 136, 24);
		pnlNhapThongTin.add(lblNgayVaoLam);

		txtNamSinh = new JTextField();
		txtNamSinh.setColumns(10);
		txtNamSinh.setBounds(555, 17, 124, 24);
		pnlNhapThongTin.add(txtNamSinh);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam",
				"Nữ" }));
		cboGioiTinh.setBounds(555, 62, 124, 21);
		pnlNhapThongTin.add(cboGioiTinh);

		dcNgayVaoLam = new JDateChooser();
		dcNgayVaoLam.setDateFormatString("yyyy-MM-dd");
		dcNgayVaoLam.setBounds(555, 108, 124, 24);
		pnlNhapThongTin.add(dcNgayVaoLam);

		String[] tieude = { "Mã nhân viên", "Họ tên", "Năm sinh", "Giới tính",
				"Ngày vào làm", "Số điện thoại" };
		model = new DefaultTableModel(tieude, 0);

		JScrollPane scrThongTinNhanVien = new JScrollPane();
		scrThongTinNhanVien.setBounds(97, 358, 1100, 300);
		add(scrThongTinNhanVien);
		scrThongTinNhanVien.setViewportView(tblThongTinNhanVien = new JTable(
				model));
		tblThongTinNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblThongTinNhanVien.getSelectedRow();
				NhanVien nhanVien = list.get(row);
				txtMaNhanVien.setText(nhanVien.getMaNV());
				txtHoTen.setText(nhanVien.getTenNV());
				txtNamSinh.setText(nhanVien.getNamSinh());
				cboGioiTinh.setSelectedItem(nhanVien.getGioiTinh());
				dcNgayVaoLam.setDate(nhanVien.getNgayVaolam());
				txtSoDienThoai.setText(nhanVien.getsDT());

			}
		});
		scrThongTinNhanVien.setViewportView(tblThongTinNhanVien);

		JButton btnThem = new JButton("THÊM");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validData()) {
					txtMaNhanVien.setText(taoMa());
					them();

				}

			}
		});
		btnThem.setFont(new Font("Arial", Font.BOLD, 20));
		btnThem.setBounds(328, 270, 108, 33);
		add(btnThem);

		JButton btnXoa = new JButton("XÓA");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoa();
			}
		});
		btnXoa.setFont(new Font("Arial", Font.BOLD, 20));
		btnXoa.setBounds(494, 270, 108, 33);
		add(btnXoa);

		JButton btnSua = new JButton("SỬA");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblThongTinNhanVien.getSelectedRow();

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(GUI_CapNhatNhanVien.this, "Vui lòng chọn thông tin để sửa.");
		        } else if (validDataSua()) {
		                sua();
		            }

			}
		});
		btnSua.setFont(new Font("Arial", Font.BOLD, 20));
		btnSua.setBounds(659, 270, 108, 33);
		add(btnSua);

		JButton btnXoaTrang = new JButton("XÓA TRẮNG");
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaNhanVien.setText("");
				txtHoTen.setText("");
				txtNamSinh.setText("");
				dcNgayVaoLam.setDate(null);
				;
				txtSoDienThoai.setText("");
			}
		});
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 20));
		btnXoaTrang.setBounds(822, 270, 157, 33);
		add(btnXoaTrang);

		JLabel lblDanhSachNhanVien = new JLabel("Danh sách nhân viên");
		lblDanhSachNhanVien.setForeground(new Color(135, 206, 235));
		lblDanhSachNhanVien.setFont(new Font("Arial", Font.BOLD | Font.ITALIC,
				20));
		lblDanhSachNhanVien.setBounds(97, 311, 222, 49);
		add(lblDanhSachNhanVien);

		setVisible(true);
		updateData();

	}

	public void them() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao_nv = new DAO_NhanVien(entityManager);
		String ma = txtMaNhanVien.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSoDienThoai.getText().trim();
		String gt = cboGioiTinh.getSelectedItem().toString();
		String namSinh = txtNamSinh.getText().trim();
		java.util.Date utilNgayVaoLam = dcNgayVaoLam.getDate(); // Lấy ngày từ
																// JDateChooser

		java.sql.Date ngayVaoLam = null; // Khai báo biến ngày vào làm

		// Kiểm tra nếu utilNgayVaoLam không null
		if (utilNgayVaoLam != null) {
			// Chuyển đổi từ java.util.Date sang java.sql.Date
			ngayVaoLam = new java.sql.Date(utilNgayVaoLam.getTime());
		}

		NhanVien nv = new NhanVien(ma, hoTen, namSinh, gt, ngayVaoLam, sdt);

		if (dao_nv.create(nv)) {
			updateData();
		}
	}

	public void sua() {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		String maNhanVien = txtMaNhanVien.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSoDienThoai.getText().trim();
		String namSinh = txtNamSinh.getText().trim();
		String gioiTinh = cboGioiTinh.getSelectedItem().toString();

		java.util.Date utilNgayVaoLam = dcNgayVaoLam.getDate(); // Lấy ngày từ JDateChooser

		java.sql.Date ngayVaoLam = null; // Khai báo biến ngày vào làm

		if (utilNgayVaoLam != null) {
			ngayVaoLam = new java.sql.Date(utilNgayVaoLam.getTime()); // Cập nhật giá trị ngày vào làm
		}

		NhanVien NV = new NhanVien(maNhanVien, hoTen, namSinh, gioiTinh,
				ngayVaoLam, sdt);
		if (dao.update(NV)) {
			JOptionPane.showMessageDialog(this, "Sửa thành công");
			Object[] data = { maNhanVien, hoTen, namSinh, gioiTinh, ngayVaoLam,
					sdt };
			model.addRow(data);
			int row = tblThongTinNhanVien.getSelectedRow();
			model.removeRow(row);
		}
	}

	public void updateData() {
		model.setRowCount(0);
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien da = new DAO_NhanVien(entityManager);
		List<NhanVien> list = da.getAllNV();
		for (NhanVien nv : list) {
			Object[] data = { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(),
					nv.getGioiTinh(), nv.getNgayVaolam(), nv.getsDT() };
			model.addRow(data);
		}

	}

	public void xoa() {
	    int row = tblThongTinNhanVien.getSelectedRow();
	    if (row >= 0) {
	        int response = JOptionPane.showConfirmDialog(this,
	                "Bạn có chắc chắn muốn xóa?",
	                "Xác nhận xóa",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE);
	        if (response == JOptionPane.YES_OPTION) {
	        	EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    	    EntityManager entityManager = util.getEnManager();
	    		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
	            DAO_DangNhap daoDN = new DAO_DangNhap(entityManager);
	            String maNV = (String) tblThongTinNhanVien.getValueAt(row, 0);
	            daoDN.deleteByMaNV(maNV);
	            if (dao.delete(maNV)) {
	            	model.removeRow(row);
	                
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
	    }
	}


	private boolean validData() {
	    String hoTen = txtHoTen.getText().trim();
	    String sdt = txtSoDienThoai.getText().trim();
	    String namSinh = txtNamSinh.getText().trim();

	    // Kiểm tra họ tên
	    if (hoTen.trim().equals("")) {
	        JOptionPane.showMessageDialog(this, "Họ tên nhân viên không được rỗng!");
	        txtHoTen.selectAll();
	        txtHoTen.requestFocus();
	        return false;
	    } else if (hoTen.matches(".*\\d.*")) {
	        JOptionPane.showMessageDialog(this, "Họ tên không chứa kí tự số!");
	        txtHoTen.requestFocus();
	        txtHoTen.selectAll();
	        return false;
	    } else {
	        if ((hoTen.matches("^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
	            JOptionPane.showMessageDialog(this, "Tên nhân viên gồm chữ cái, có thể chứa khoảng trắng");
	            txtHoTen.requestFocus();
	            txtHoTen.selectAll();
	            return false;
	        }
	    }

	    // Kiểm tra số điện thoại
	    if (sdt.trim().equals("")) {
	        JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng !");
	        txtSoDienThoai.selectAll();
	        txtSoDienThoai.requestFocus();
	        return false;
	    } else if (!sdt.matches("^[0-9]{10}$")) {
	        JOptionPane.showMessageDialog(this, "Số điện thoại chỉ chứa 10 kí tự số !");
	        txtSoDienThoai.requestFocus();
	        txtSoDienThoai.selectAll();
	        return false;
	    }

	    // Kiểm tra năm sinh và tuổi phải đủ 18 tuổi tính đến năm hiện tại
	    if (namSinh.trim().equals("")) {
	        JOptionPane.showMessageDialog(this, "Năm sinh không được rỗng !");
	        txtNamSinh.selectAll();
	        txtNamSinh.requestFocus();
	        return false;
	    } else if (!namSinh.matches("^[0-9]{4}$")) {
	        JOptionPane.showMessageDialog(this, "Năm sinh chỉ chứa 4 kí tự số !");
	        txtNamSinh.requestFocus();
	        txtNamSinh.selectAll();
	        return false;
	    } else {
	        int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
	        int namSinhInt = Integer.parseInt(namSinh);

	        if ((namHienTai - namSinhInt) < 18) {
	            JOptionPane.showMessageDialog(this, "Phải đủ 18 tuổi!");
	            txtNamSinh.requestFocus();
	            txtNamSinh.selectAll();
	            return false;
	        }
	    }

	    // Kiểm tra trùng số điện thoại
	    if (kiemTraTrungSoDienThoai(sdt)) {
	        JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
	        txtSoDienThoai.requestFocus();
	        txtSoDienThoai.selectAll();
	        return false;
	    }
	    
	 // Kiểm tra ngày vào làm
	    java.util.Date ngayVaoLam = dcNgayVaoLam.getDate();
	    java.util.Date ngayHienTai = new java.util.Date(); // Ngày hiện tại

	    if (ngayVaoLam != null && ngayVaoLam.after(ngayHienTai)) {
	        JOptionPane.showMessageDialog(this, "Ngày vào làm phải trước hoặc bằng ngày hiện tại!");
	        dcNgayVaoLam.requestFocus();
	        return false;
	    }

	    return true;
	}


	private boolean validDataSua() {
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSoDienThoai.getText().trim();
		String namSinh = txtNamSinh.getText().trim();

		if (hoTen.trim().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Họ tên nhân viên không được rỗng!");
			txtHoTen.selectAll();
			txtHoTen.requestFocus();
			return false;
		} else if (hoTen.matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(this, "Họ tên không chứa kí tự số!");
			txtHoTen.requestFocus();
			txtHoTen.selectAll();
			return false;
		} else {

			if ((hoTen
					.matches("^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this,
						"Tên nhân viên gồm chữ cái, có thể chứa khoảng trắng");
				txtHoTen.requestFocus();
				txtHoTen.selectAll();
				return false;
			}
		}

		if (sdt.trim().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại không được rỗng !");
			txtSoDienThoai.selectAll();
			txtSoDienThoai.requestFocus();
			return false;
		} else if (!sdt.matches("^[0-9]{10}$")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại chỉ chứa 10 kí tự số !");
			txtSoDienThoai.requestFocus();
			txtSoDienThoai.selectAll();
			return false;
		}

		 if (namSinh.trim().equals("")) {
		        JOptionPane.showMessageDialog(this, "Năm sinh không được rỗng !");
		        txtNamSinh.selectAll();
		        txtNamSinh.requestFocus();
		        return false;
		    } else if (!namSinh.matches("^[0-9]{4}$")) {
		        JOptionPane.showMessageDialog(this, "Năm sinh chỉ chứa 4 kí tự số !");
		        txtNamSinh.requestFocus();
		        txtNamSinh.selectAll();
		        return false;
		    } else {
		        int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
		        int namSinhInt = Integer.parseInt(namSinh);

		        if ((namHienTai - namSinhInt) < 18) {
		            JOptionPane.showMessageDialog(this, "Phải đủ 18 tuổi!");
		            txtNamSinh.requestFocus();
		            txtNamSinh.selectAll();
		            return false;
		        }
		    }
		// Kiểm tra ngày vào làm
		java.util.Date ngayVaoLam = dcNgayVaoLam.getDate();
		java.util.Date ngayHienTai = new java.util.Date(); // Ngày hiện tại

		if (ngayVaoLam != null && ngayVaoLam.after(ngayHienTai)) {
		    JOptionPane.showMessageDialog(this, "Ngày vào làm phải trước hoặc bằng ngày hiện tại!");
		    dcNgayVaoLam.requestFocus();
		    return false;
		}

		return true;
	}

	public boolean kiemTraTrungSoDienThoai(String sdt) {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);
		List<NhanVien> list = dao.getAllNV();

		for (NhanVien nv : list) {
			if (nv.getsDT().equals(sdt)) {
				return true;
			}
		}

		return false;
	}

	public String taoMa() {

		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
	    EntityManager entityManager = util.getEnManager();
		DAO_NhanVien dao = new DAO_NhanVien(entityManager);

		int n = dao.getAllNV().size();
		if (n < 9) {
			do {
				n = n + 1;

				ma = "NV00" + String.valueOf(n);
				list = dao.getAllNV();
			} while (list.contains(ma));

		} else if (n < 99) {
			do {
				n = n + 1;

				ma = "NV0" + String.valueOf(n);
				list = dao.getAllNV();
			} while (list.contains(ma));
		} else if (n < 999) {
			do {
				n = n + 1;

				ma = "NV" + String.valueOf(n);
				list = dao.getAllNV();
			} while (list.contains(ma));
		}
		return ma;
	}
}
