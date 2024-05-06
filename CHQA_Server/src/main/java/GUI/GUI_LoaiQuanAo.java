package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_LoaiQuanAo;
import connect.ConnectDB;
import entity.LoaiQuanAo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI_LoaiQuanAo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTenLoaiQuanAo,txtTiemkiem,txtMaLoaiQuanAo;
	private JTable tblLoaiQuanAo;
	public DefaultTableModel modelLoaiQuanAo;
	private String ma;
	private List<LoaiQuanAo> listLoai;
	private GUI_CapNhatQuanAo quanAo;
	private GUI_DatHang banHang;
	private int deletedRowCount=1; 
	
	public GUI_LoaiQuanAo(GUI_CapNhatQuanAo quanAo, GUI_DatHang banHang) {
		setLayout(null);
		setBackground(new Color(0, 64, 64));
		JLabel lblTieude = new JLabel("Loại quần áo ");
		lblTieude.setForeground(new Color(135, 206, 235));
		lblTieude.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblTieude.setBounds(518, 0, 277, 50);
		add(lblTieude);
		
		this.quanAo= quanAo;
		this.banHang = banHang;
		
		//ConnectDB
 		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		//Tạo bảng
 		String [] tieude={"Mã loại quần áo","Tên loại quần áo"};
 		modelLoaiQuanAo = new DefaultTableModel(tieude,0);
 		JScrollPane scrThongTinLoaiQuanAo = new JScrollPane();
 		
 		scrThongTinLoaiQuanAo.setBounds(75, 382, 1139, 249);
		add(scrThongTinLoaiQuanAo);
		
		scrThongTinLoaiQuanAo.setViewportView(tblLoaiQuanAo = new  JTable(modelLoaiQuanAo));
		scrThongTinLoaiQuanAo.setViewportView(tblLoaiQuanAo);
		tblLoaiQuanAo.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {
 				int row = tblLoaiQuanAo.getSelectedRow();
 				txtMaLoaiQuanAo.setText(tblLoaiQuanAo.getValueAt(row, 0).toString());
 				txtTenLoaiQuanAo.setText(tblLoaiQuanAo.getValueAt(row, 1).toString());
 				
 			}
 		});
		JPanel pnlThongTinQuanAo = new JPanel();
		pnlThongTinQuanAo.setBounds(167, 57, 912, 257);
		add(pnlThongTinQuanAo);
		pnlThongTinQuanAo.setLayout(null);
		
		JLabel lblTenLoaiQuanAo = new JLabel("Tên loại quần áo ");
		lblTenLoaiQuanAo.setBounds(477, 102, 170, 25);
		pnlThongTinQuanAo.add(lblTenLoaiQuanAo);
		lblTenLoaiQuanAo.setFont(new Font("Tahoma", Font.ITALIC, 20));
		
		txtTenLoaiQuanAo = new JTextField();
		txtTenLoaiQuanAo.setFont(new Font("Arial", Font.PLAIN, 16));
	
		txtTenLoaiQuanAo.setBounds(644, 100, 216, 37);
		pnlThongTinQuanAo.add(txtTenLoaiQuanAo);
		txtTenLoaiQuanAo.setColumns(10);
		
		JLabel lblMaLoaiQuanAo = new JLabel("Mã loại quần áo ");
		lblMaLoaiQuanAo.setBounds(26, 100, 159, 37);
		pnlThongTinQuanAo.add(lblMaLoaiQuanAo);
		lblMaLoaiQuanAo.setFont(new Font("Tahoma", Font.ITALIC, 20));
		
		//Thêm quần áo
		JButton btnThem = new JButton("THÊM");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validData()) {
		            txtMaLoaiQuanAo.setText(taoMa());
		            themLoaiQuanAo();
		            quanAo.updateLaiComboLoaiQuanAo();
		            xoaTrang();
		        }
			}
		});
		btnThem.setBounds(119, 181, 113, 37);
		pnlThongTinQuanAo.add(btnThem);
		btnThem.setForeground(new Color(60, 179, 113));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnXoa = new JButton("XÓA");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaLoaiQuanAo();
				quanAo.updateLaiComboLoaiQuanAo();
				xoaTrang();
				
			}
		});
		btnXoa.setBounds(273, 181, 113, 37);
		pnlThongTinQuanAo.add(btnXoa);
		btnXoa.setForeground(new Color(60, 179, 113));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnSua = new JButton("SỬA");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblLoaiQuanAo.getSelectedRow();
		        if (row >= 0) {
		            if (validData()) {
		            	int n = JOptionPane.showConfirmDialog(null, "Chắc chắn sửa ?","Cảnh báo", JOptionPane.YES_NO_OPTION);
						if(n == JOptionPane.YES_OPTION) {
			            	suaLoaiQuanAo();
			            	quanAo.updateLaiComboLoaiQuanAo();
						}else return;
		            }
		        }else {
		        	JOptionPane.showMessageDialog(null, "Vui lòng chọn loại quần áo cần sửa !");
		        }
			}
		});
		btnSua.setBounds(436, 181, 113, 37);
		pnlThongTinQuanAo.add(btnSua);
		btnSua.setForeground(new Color(60, 179, 113));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnXoatrang = new JButton("XÓA TRẮNG");
		btnXoatrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnXoatrang.setBounds(603, 181, 152, 37);
		pnlThongTinQuanAo.add(btnXoatrang);
		btnXoatrang.setForeground(new Color(60, 179, 113));
		btnXoatrang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblTimKiemQuanAo = new JLabel("Tìm kiếm theo tên");
		lblTimKiemQuanAo.setBounds(211, 22, 196, 25);
		pnlThongTinQuanAo.add(lblTimKiemQuanAo);
		lblTimKiemQuanAo.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtTiemkiem = new JTextField();
		txtTiemkiem.setBounds(417, 19, 204, 33);
		pnlThongTinQuanAo.add(txtTiemkiem);
		txtTiemkiem.setColumns(10);
		
		JButton btnTimkiem = new JButton("");
		btnTimkiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemLoaiQuanAo();
			}
		});
		btnTimkiem.setSelectedIcon(new ImageIcon(GUI_CapNhatQuanAo.class.getResource("/Image/icon_TimKiem.png")));
		btnTimkiem.setBounds(632, 18, 46, 33);
		pnlThongTinQuanAo.add(btnTimkiem);
		btnTimkiem.setIcon(new ImageIcon(GUI_CapNhatQuanAo.class.getResource("/Image/icon_TimKiem.png")));
		
		txtMaLoaiQuanAo = new JTextField();
		txtMaLoaiQuanAo.setFont(new Font("Arial", Font.BOLD, 20));
		txtMaLoaiQuanAo.setColumns(10);
		txtMaLoaiQuanAo.setBounds(195, 100, 216, 37);
		txtMaLoaiQuanAo.setEditable(false);
		
		pnlThongTinQuanAo.add(txtMaLoaiQuanAo);
		
		JLabel lblDanhSachLoaiQuanAo = new JLabel("Danh sách loại quần áo");
		lblDanhSachLoaiQuanAo.setForeground(new Color(135, 206, 235));
		lblDanhSachLoaiQuanAo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDanhSachLoaiQuanAo.setBounds(75, 349, 234, 37);
		add(lblDanhSachLoaiQuanAo);
		updateData();
	
	}
	
	//Hàm thêm
	public void themLoaiQuanAo() {
		DAO_LoaiQuanAo loaiQuanAo_dao = new DAO_LoaiQuanAo();
		String maLoai = txtMaLoaiQuanAo.getText().trim();
		String tenLoai = txtTenLoaiQuanAo.getText().trim();
		LoaiQuanAo loaiQuanAo = new LoaiQuanAo(maLoai, tenLoai);
		if(loaiQuanAo_dao.create(loaiQuanAo)) {
			Object [] data = {maLoai,tenLoai};
			modelLoaiQuanAo.addRow(data);
		}
	}
	
	//Hàm cập nhật
	public void updateData() {
		DAO_LoaiQuanAo dao= new DAO_LoaiQuanAo();
		List<LoaiQuanAo> list = dao.getAllLoaiQuanAo();
		for(LoaiQuanAo lqa : list) {
			Object [] data = {lqa.getMaLoai(),lqa.getTenLoai()};
			modelLoaiQuanAo.addRow(data);
		}
		
	}
	
	public void xoaTrang() {
		txtMaLoaiQuanAo.setText("");
		txtTenLoaiQuanAo.setText("");
		txtTiemkiem.setText("");
	}
	
	//Hàm xóa
	public void xoaLoaiQuanAo() {
		DAO_LoaiQuanAo dao = new DAO_LoaiQuanAo();
		int row = tblLoaiQuanAo.getSelectedRow();
		if(row >=1) {
			int n = JOptionPane.showConfirmDialog(null, "Chắc chắn xóa ?", "Cảnh báo",JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION) {
				String maLoaiQuanAo = (String) tblLoaiQuanAo.getValueAt(row, 0);
				if(dao.delete(maLoaiQuanAo)) {
					modelLoaiQuanAo.removeRow(row);
					quanAo.updateLaiComboLoaiQuanAo();
					for (int i = row; i < modelLoaiQuanAo.getRowCount(); i++) {
						String MaLoai = modelLoaiQuanAo.getValueAt(i, 0).toString();
			            int maMoi = Integer.parseInt(MaLoai.substring(3)) - deletedRowCount;
			            String newMaLoai = "QA" + String.format("%03d", maMoi);
			            modelLoaiQuanAo.setValueAt(newMaLoai, i, 0);
					}
				   
					dao.updateAllMaLoaiQuanAo(deletedRowCount, maLoaiQuanAo);
				}
			}else return;
		}else {
			JOptionPane.showMessageDialog(null,"Vui lòng chọn loại quần áo cần xóa !");
		}
	}
	
	//Hàm sửa
	public void suaLoaiQuanAo() {
		DAO_LoaiQuanAo dao = new DAO_LoaiQuanAo();
		String maLoai= txtMaLoaiQuanAo.getText();
		String tenLoai= txtTenLoaiQuanAo.getText();
		LoaiQuanAo loai = new LoaiQuanAo(maLoai,tenLoai);
		if(dao.suaLoaiQuanAo(loai)) {
			JOptionPane.showMessageDialog(this, "Sửa thành công");
			String [] data = {maLoai,tenLoai};
			modelLoaiQuanAo.addRow(data);
			int row = tblLoaiQuanAo.getSelectedRow();
			modelLoaiQuanAo.removeRow(row);
		}

	}
	
	//Hàm tìm kiếm 
	public void timKiemLoaiQuanAo() {
		DAO_LoaiQuanAo dao = new DAO_LoaiQuanAo();
		List<LoaiQuanAo> list = dao.timTheoTen(txtTiemkiem.getText());
		modelLoaiQuanAo.getDataVector().removeAllElements();
		for (LoaiQuanAo l : list) {
			Object[] data = {l.getMaLoai(),l.getTenLoai()};
			modelLoaiQuanAo.addRow(data);

	}

	}
	
	private boolean validData() {
		String tenloai = txtTenLoaiQuanAo.getText().trim();
		String maLoai = txtMaLoaiQuanAo.getText().trim();
		
		if (tenloai.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên loại không được rỗng!");
			txtTenLoaiQuanAo.selectAll();
			txtTenLoaiQuanAo.requestFocus();
			return false;
		}else if (tenloai.matches(".*\\d.*")) {
	        JOptionPane.showMessageDialog(this, "Tên loại chỉ chứa kí tự chữ");
	        txtTenLoaiQuanAo.requestFocus();
	        txtTenLoaiQuanAo.selectAll();
	        return false;
	    } 
		else {

			if ((tenloai.matches(
					"^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Tên loại gồm chữ cái, có thể chứa khoảng trắng");
				txtTenLoaiQuanAo.requestFocus();
				txtTenLoaiQuanAo.selectAll();
				return false;
			}
		}

		
		return true;
	}
	
	public String taoMa() {

		DAO_LoaiQuanAo dao = new DAO_LoaiQuanAo();
		
		int n = dao.getAllLoaiQuanAo().size();
		if(n<9) {
		do {
			 n=n+1;
			
			ma = "ML00" + String.valueOf(n);
			listLoai = dao.getAllLoaiQuanAo();
		} while (listLoai.contains(ma));
		}else {
			do {
				 n=n+1;
				
				ma = "ML0" + String.valueOf(n);
				listLoai = dao.getAllLoaiQuanAo();
			} while (listLoai.contains(ma));
		}
		return ma;
	}

	
	}



