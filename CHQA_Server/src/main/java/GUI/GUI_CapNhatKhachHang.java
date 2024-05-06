package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_KhachHang;
import DAO.DAO_LoaiQuanAo;
import DAO.DAO_NhaCungCap;
import DAO.DAO_QuanAo;
import connect.ConnectDB;
import entity.KhachHang;
import entity.LoaiQuanAo;
import entity.NhaCungCap;
import entity.QuanAo;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI_CapNhatKhachHang extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMakhachhang,txtHoten,txtSdt,txtNamsinh,txtDiaChi;
	private JTable tblThongTinKhachHang;
	private DefaultTableModel modelKhachHang;
	private JComboBox cboGioitinh;
	private String ma;
	private List<KhachHang> kh;
	public GUI_CapNhatKhachHang() {
		setLayout(null);
		setBackground(new Color(0, 64, 64));

		//ConnectDB
 		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblTieuDe = new JLabel("Quản lí khách hàng");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblTieuDe.setForeground(new Color(135, 206, 235));
		lblTieuDe.setBounds(472, 11, 362, 80);
		add(lblTieuDe);
		
		String gioiTinh[]= {"Nam","Nữ"};
		
		
		
		tblThongTinKhachHang = new JTable();
		 String[] tieude = {
				"Mã khách hàng", "Họ và tên", "Năm Sinh", "Số điện thoại ", "Giới tính","Địa chỉ"
			};
		
		
		modelKhachHang = new DefaultTableModel(tieude,0);
		JScrollPane scrThongTinKhachHang = new JScrollPane();
		scrThongTinKhachHang.setBounds(82, 386, 1116, 202);
		add(scrThongTinKhachHang);
			
		scrThongTinKhachHang.setViewportView(tblThongTinKhachHang = new  JTable(modelKhachHang));
		tblThongTinKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  int row = tblThongTinKhachHang.getSelectedRow();
				  txtMakhachhang.setText(tblThongTinKhachHang.getValueAt(row, 0).toString());
				  txtHoten.setText(tblThongTinKhachHang.getValueAt(row, 1).toString());
				  txtNamsinh.setText(tblThongTinKhachHang.getValueAt(row, 2).toString());
				  txtSdt.setText(tblThongTinKhachHang.getValueAt(row, 3).toString());
				  cboGioitinh.setSelectedItem(tblThongTinKhachHang.getValueAt(row, 4).toString());
				  txtDiaChi.setText(tblThongTinKhachHang.getValueAt(row, 5).toString());
				  
			}
		});
		scrThongTinKhachHang.setViewportView(tblThongTinKhachHang);
		
		JPanel pnlNhapThongTinKhachHang = new JPanel();
		pnlNhapThongTinKhachHang.setBounds(103, 87, 1066, 244);
		add(pnlNhapThongTinKhachHang);
		pnlNhapThongTinKhachHang.setLayout(null);
		
		JLabel lblMakhachhang = new JLabel("Mã khách hàng");
		lblMakhachhang.setBounds(45, 28, 142, 22);
		pnlNhapThongTinKhachHang.add(lblMakhachhang);
		lblMakhachhang.setFont(new Font("Arial", Font.BOLD, 18));
		
		txtMakhachhang = new JTextField();
		txtMakhachhang.setFont(new Font("Arial", Font.BOLD, 16));
		txtMakhachhang.setBounds(217, 24, 245, 26);
		txtMakhachhang.setEditable(false);
		
		pnlNhapThongTinKhachHang.add(txtMakhachhang);
		txtMakhachhang.setColumns(10);
		
		JLabel lblHoten = new JLabel("Họ và tên");
		lblHoten.setBounds(45, 64, 108, 37);
		pnlNhapThongTinKhachHang.add(lblHoten);
		lblHoten.setFont(new Font("Arial", Font.BOLD, 18));
		
		txtHoten = new JTextField();
		txtHoten.setFont(new Font("Arial", Font.PLAIN, 14));
		txtHoten.setBounds(217, 70, 245, 26);
		pnlNhapThongTinKhachHang.add(txtHoten);
		txtHoten.setColumns(10);
		
		JLabel lblSodienthoai = new JLabel("Số điện thoại");
		lblSodienthoai.setBounds(45, 115, 144, 32);
		pnlNhapThongTinKhachHang.add(lblSodienthoai);
		lblSodienthoai.setFont(new Font("Arial", Font.BOLD, 18));
		
		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSdt.setBounds(217, 118, 245, 26);
		pnlNhapThongTinKhachHang.add(txtSdt);
		txtSdt.setColumns(10);
		
		JLabel lblNamsinh = new JLabel("Năm sinh");
		lblNamsinh.setBounds(632, 19, 98, 41);
		pnlNhapThongTinKhachHang.add(lblNamsinh);
		lblNamsinh.setFont(new Font("Arial", Font.BOLD, 18));
		
		txtNamsinh = new JTextField();
		txtNamsinh.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNamsinh.setBounds(755, 24, 245, 26);
		pnlNhapThongTinKhachHang.add(txtNamsinh);
		txtNamsinh.setColumns(10);
		
		JLabel lblGioitinh = new JLabel("Giới tính");
		lblGioitinh.setBounds(632, 62, 98, 41);
		pnlNhapThongTinKhachHang.add(lblGioitinh);
		lblGioitinh.setFont(new Font("Arial", Font.BOLD, 18));
		cboGioitinh = new JComboBox(gioiTinh);
		cboGioitinh.setBounds(755, 68, 98, 26);
		pnlNhapThongTinKhachHang.add(cboGioitinh);
		cboGioitinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboGioitinh.setToolTipText("");
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setBounds(632, 115, 144, 32);
		pnlNhapThongTinKhachHang.add(lblDiaChi);
		lblDiaChi.setFont(new Font("Arial", Font.BOLD, 18));
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDiaChi.setBounds(755, 120, 245, 26);
		pnlNhapThongTinKhachHang.add(txtDiaChi);
		txtDiaChi.setColumns(10);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				      if (validData()) {
			            txtMakhachhang.setText(taoMa());
			            them();
			        }

			}
		});
		btnThem.setBounds(75, 185, 171, 49);
		pnlNhapThongTinKhachHang.add(btnThem);
		btnThem.setForeground(new Color(0, 255, 0));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBounds(307, 185, 171, 49);
		pnlNhapThongTinKhachHang.add(btnXoa);
		btnXoa.setForeground(Color.GREEN);
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xoaKhachHang();
			}
		});
		
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validDataSua()) {
	                suaKhachHang();
	            }
			}
		});
		btnSua.setBounds(559, 185, 171, 49);
		pnlNhapThongTinKhachHang.add(btnSua);
		btnSua.setForeground(Color.GREEN);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnXoatrang = new JButton("Xóa trắng");
		btnXoatrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		            xoaTrang();
		               
				}
			
		});
		btnXoatrang.setBounds(801, 185, 171, 49);
		pnlNhapThongTinKhachHang.add(btnXoatrang);
		btnXoatrang.setForeground(Color.GREEN);
		btnXoatrang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblDanhSachKhachHang = new JLabel("Danh sách khách hàng");
		lblDanhSachKhachHang.setForeground(new Color(135, 206, 235));
		lblDanhSachKhachHang.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDanhSachKhachHang.setBounds(81, 341, 231, 55);
		add(lblDanhSachKhachHang);
		
		updateData();
		
	}
	public void them() {
		DAO_KhachHang DAO = new DAO_KhachHang();
		String ma = txtMakhachhang.getText().trim();
		String ten = txtHoten.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String sdt = txtSdt.getText().trim();
		String gioitinh = cboGioitinh.getSelectedItem().toString();
		String namsinh = txtNamsinh.getText().trim();
		KhachHang kh = new KhachHang(ma,ten,namsinh,sdt,gioitinh,diachi);
		if(DAO.themKH(kh)) {
			String [] data = {ma,ten,namsinh,sdt,gioitinh,diachi};
			modelKhachHang.addRow(data);
		}
	}
	public void updateData() {
		DAO_KhachHang dao= new DAO_KhachHang();
		List<KhachHang> list = dao.getAllKhachHang();
		for(KhachHang kh : list) {
			Object [] data = {kh.getMaKH(),kh.getHoTen(),kh.getNamSinh(),kh.getsDT(),kh.getGioiTinh(),kh.getDiaChi()};
			modelKhachHang.addRow(data);
		}
		
	}
	
	public void updateLaiData() {
		DAO_KhachHang dao= new DAO_KhachHang();
		modelKhachHang.setRowCount(0);
		List<KhachHang> list = dao.getAllKhachHang();
		for(KhachHang kh : list) {
			Object [] data = {kh.getMaKH(),kh.getHoTen(),kh.getNamSinh(),kh.getsDT(),kh.getGioiTinh(),kh.getDiaChi()};
			modelKhachHang.addRow(data);
		}
		
	}
	
	public void xoaTrang() {
		txtHoten.setText("");
		txtMakhachhang.setText("");
		txtDiaChi.setText("");
		txtNamsinh.setText("");
		txtSdt.setText("");
	}
	
	public void xoaKhachHang() {
		DAO_KhachHang dao = new DAO_KhachHang();
		int row = tblThongTinKhachHang.getSelectedRow();
		if(row >=1) {
			int n = JOptionPane.showConfirmDialog(null, "Chắc chắn xóa ?", "Cảnh báo",JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION) {
				String maKhachHang = (String) tblThongTinKhachHang.getValueAt(row, 0);
				if(dao.delete(maKhachHang)) {
					modelKhachHang.removeRow(row);
				}
			}else return;
		}else {
			JOptionPane.showMessageDialog(null,"Vui lòng chọn khách hàng cần xóa");
		}

	}
	public void suaKhachHang() {
		DAO_KhachHang dao = new DAO_KhachHang();
		String ma = txtMakhachhang.getText().trim();
		String ten = txtHoten.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String sdt = txtSdt.getText().trim();
		String gioitinh = cboGioitinh.getSelectedItem().toString();
		String namsinh = txtNamsinh.getText().trim();
		KhachHang kh = new KhachHang(ma,ten,namsinh,sdt,gioitinh,diachi);
		if(dao.update(kh)) {
			JOptionPane.showMessageDialog(this, "Sửa thành công");
			String [] data = {ma,ten,namsinh,sdt,gioitinh,diachi};
			modelKhachHang.addRow(data);
			int row = tblThongTinKhachHang.getSelectedRow();
			modelKhachHang.removeRow(row);
		}

	}
	private boolean validData() {
		String hoTen = txtHoten.getText().trim();
		String sdt = txtSdt.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String namSinh = txtNamsinh.getText().trim();

		if (hoTen.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Họ tên khách hàng trống!");
			txtHoten.selectAll();
			txtHoten.requestFocus();
			return false;
		} else {

			if ((hoTen.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Tên khách hàng gồm chữ cái, có thể chứa khoảng trắng");
				txtHoten.requestFocus();
				txtHoten.selectAll();
				return false;
			}
		}

		if (sdt.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng trống !");
			txtSdt.selectAll();
			txtSdt.requestFocus();
			return false;
		} else  {

			if (!(sdt.matches("^[0-9]{10}$"))) {
				JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng gồm 10 số");
				txtSdt.requestFocus();
				txtSdt.selectAll();
				return false;
			}else if (kiemTraTrungSoDienThoai(sdt)) {
		        JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
		        txtSdt.requestFocus();
		        txtSdt.selectAll();
		        return false;
		    }
		}

		if (diaChi.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được trống!");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		} else {

			if (diaChi.matches(
					"^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]")) {
				JOptionPane.showMessageDialog(this, "Địa chỉ gồm chữ cái, số,...");
				txtDiaChi.requestFocus();
				txtDiaChi.selectAll();
				return false;

			}
		}
		if (namSinh.trim().equals("")) {
			JOptionPane.showMessageDialog(this, " Năm sinh khách hàng trống!");
			txtNamsinh.selectAll();
			txtNamsinh.requestFocus();
			return false;
		} else {

			if (!namSinh.matches("^[0-9]{4}$")) {
				JOptionPane.showMessageDialog(this, " Năm sinh khách hàng gồm 4 số");
				txtNamsinh.requestFocus();
				txtNamsinh.selectAll();
				return false;
			}
		}

		return true;
	}
	private boolean validDataSua() {
		String hoTen = txtHoten.getText().trim();
		String sdt = txtSdt.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String namSinh = txtNamsinh.getText().trim();

		if (hoTen.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Họ tên khách hàng trống!");
			txtHoten.selectAll();
			txtHoten.requestFocus();
			return false;
		} else {

			if ((hoTen.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Tên khách hàng gồm chữ cái, có thể chứa khoảng trắng");
				txtHoten.requestFocus();
				txtHoten.selectAll();
				return false;
			}
		}

		if (sdt.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng trống !");
			txtSdt.selectAll();
			txtSdt.requestFocus();
			return false;
		} else  {

			if (!(sdt.matches("^[0-9]{10}$"))) {
				JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng gồm 10 số");
				txtSdt.requestFocus();
				txtSdt.selectAll();
				return false;
			}
		}

		if (diaChi.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được trống!");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		} else {

			if (diaChi.matches(
					"^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]")) {
				JOptionPane.showMessageDialog(this, "Địa chỉ gồm chữ cái, số,...");
				txtDiaChi.requestFocus();
				txtDiaChi.selectAll();
				return false;

			}
		}
		if (namSinh.trim().equals("")) {
			JOptionPane.showMessageDialog(this, " Năm sinh khách hàng trống!");
			txtNamsinh.selectAll();
			txtNamsinh.requestFocus();
			return false;
		} else {

			if (!namSinh.matches("^[0-9]{4}$")) {
				JOptionPane.showMessageDialog(this, " Năm sinh khách hàng gồm 4 số");
				txtNamsinh.requestFocus();
				txtNamsinh.selectAll();
				return false;
			}
		}

		return true;
	}
	
	public boolean kiemTraTrungSoDienThoai(String sdt) {
        DAO_KhachHang dao = new DAO_KhachHang();
        List<KhachHang> list = dao.getAllKhachHang();
        
        for (KhachHang kh : list) {
            if (kh.getsDT().equals(sdt)) {
                return true;
            }
        }
        
        return false; 
    }
	
	public String taoMa() {

		DAO_KhachHang dao = new DAO_KhachHang();
		
		int n = dao.getAllKhachHang().size();
		if(n<9) {
		do {
			 n=n+1;
			
			ma = "KH00" + String.valueOf(n);
			kh = dao.getAllKhachHang();
		} while (kh.contains(ma));
		
	}else if(n<99) {
		do {
			 n=n+1;
			
			ma = "KH0" + String.valueOf(n);
			kh = dao.getAllKhachHang();
		} while (kh.contains(ma));
	}
	else if(n<999) {
		do {
			 n=n+1;
			
			ma = "KH" + String.valueOf(n);
			kh = dao.getAllKhachHang();
		} while (kh.contains(ma));
	}
		return ma;
	}
}


