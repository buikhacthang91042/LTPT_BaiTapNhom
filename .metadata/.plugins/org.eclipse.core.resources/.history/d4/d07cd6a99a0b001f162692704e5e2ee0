package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.toedter.calendar.JDateChooser;

import DAO.DAO_ChiTietKhuyenMai;
import DAO.DAO_KhuyenMai;
import DAO.DAO_NhaCungCap;
import DAO.DAO_NhanVien;
import DAO.DAO_QuanAo;
import connect.ConnectDB;
import entity.ChiTietKhuyenMai;
import entity.KhuyenMai;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.QuanAo;

import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Panel;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

public class GUI_KhuyenMai extends JPanel {
	private JTextField txtMaKhuyenMai,txtTenCT,txtTiLeKM,txtTiLeKmNhieu;
	private JDateChooser ngayBatDau, ngayKetThuc;
	private JTable tblDsKM,tblQaKM;
	private DefaultTableModel modelDSKhuyenMai, modelQAKM;
	private JComboBox cboMaKM,cboMaQuanAoNhieu1,cboMaQuanAoNhieu2,cboMaQuanAo,cboMaKMNhieu;
	private String ma;
	private GUI_CapNhatQuanAo quanAo;
	private List<KhuyenMai> list;
	private int deletedRowCount=1; 
	public GUI_KhuyenMai(GUI_CapNhatQuanAo quanAo) {
		this.quanAo = quanAo;
 		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(null);
		
		JPanel pnlContent = new JPanel();
		pnlContent.setBounds(0, 0, 1329, 779);
		add(pnlContent);
		pnlContent.setLayout(null);
		pnlContent.setBackground(new Color(0, 64, 64));
		JPanel pnlLeft = new JPanel();
		pnlLeft.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pnlLeft.setBackground(new Color(192, 192, 192));
		pnlLeft.setBounds(10, 59, 503, 620);
		pnlContent.add(pnlLeft);
		pnlLeft.setLayout(null);
		
		JLabel lblMaKM = new JLabel("Mã khuyến mãi");
		lblMaKM.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaKM.setBounds(10, 10, 123, 30);
		pnlLeft.add(lblMaKM);
		
		txtMaKhuyenMai = new JTextField();
		txtMaKhuyenMai.setEditable(false);
		txtMaKhuyenMai.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMaKhuyenMai.setBounds(156, 14, 123, 24);
		pnlLeft.add(txtMaKhuyenMai);
		
		txtMaKhuyenMai.setColumns(10);
		
		JLabel lblTenCT = new JLabel("Tên chương trình");
		lblTenCT.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenCT.setBounds(10, 50, 134, 30);
		pnlLeft.add(lblTenCT);
		
		txtTenCT = new JTextField();
		txtTenCT.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTenCT.setColumns(10);
		txtTenCT.setBounds(156, 52, 123, 24);
		pnlLeft.add(txtTenCT);
		
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu");
		lblNgayBatDau.setFont(new Font("Arial", Font.BOLD, 16));
		lblNgayBatDau.setBounds(10, 101, 123, 30);
		pnlLeft.add(lblNgayBatDau);
		ngayBatDau = new JDateChooser();
		ngayBatDau.setDateFormatString("dd/MM/yyyy");
		ngayBatDau.setBounds(156, 101, 123, 30);
		pnlLeft.add(ngayBatDau);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
		lblNgayKetThuc.setFont(new Font("Arial", Font.BOLD, 16));
		lblNgayKetThuc.setBounds(10, 157, 123, 30);
		pnlLeft.add(lblNgayKetThuc);
		
		ngayKetThuc = new JDateChooser();
		ngayKetThuc.setDateFormatString("dd/MM/yyyy");
		ngayKetThuc.setBounds(156, 157, 123, 30);
		pnlLeft.add(ngayKetThuc);
		
		JButton btnThem = new JButton("THÊM");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTenCT.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Tên chương trình không được rỗng !");
				}else {
					if (ngayKetThuc.getDate().before(ngayBatDau.getDate())) {
						JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải trước ngày kết thúc !");
					}else {
						themKM();
						cboMaKM.removeAllItems();
						cboMaKMNhieu.removeAllItems();
						
						updateDataKM();
						xoaTrang();
					}
			}
			}
		});
		btnThem.setFont(new Font("Arial", Font.BOLD, 20));
		btnThem.setBounds(10, 243, 98, 30);
		pnlLeft.add(btnThem);
		
		JButton btnXoa = new JButton("XÓA");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int row = tblDsKM.getSelectedRow();
	                if (row >= 0) {
	                    int n = JOptionPane.showConfirmDialog(null, "Chắc chắn xóa ? ", "Cảnh báo", JOptionPane.YES_NO_OPTION);
	                    if (n == JOptionPane.YES_OPTION) {
	                        xoaKM();
	                        modelQAKM.setRowCount(0);
	                        JOptionPane.showMessageDialog(null, "Xóa thành công !");
	                        updateDataQAKM();
	                        quanAo.updateLaiMaKM();
	                        xoaTrang();
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khuyến mãi cần xóa ! ");
	                }
	            }
	        });
		btnXoa.setFont(new Font("Arial", Font.BOLD, 20));
		btnXoa.setBounds(118, 243, 98, 30);
		pnlLeft.add(btnXoa);
		
		JButton btnSua = new JButton("SỬA");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				if(txtTenCT.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Tên chương trình không được rỗng !");
				}else {
					if (ngayKetThuc.getDate().before(ngayBatDau.getDate())) {
						JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải trước ngày kết thúc !");
					}else {
				
						int n = JOptionPane.showConfirmDialog(null, "Chắc chắn sửa ? ", "Cảnh báo", JOptionPane.YES_NO_OPTION);
						if(n== JOptionPane.YES_OPTION) {
							suaKM();
						}
					}
				}
			}
		});
		btnSua.setFont(new Font("Arial", Font.BOLD, 20));
		btnSua.setBounds(226, 243, 98, 30);
		pnlLeft.add(btnSua);
		
		JButton btnXoaTrang = new JButton("XÓA TRẮNG");
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 20));
		btnXoaTrang.setBounds(329, 243, 164, 30);
		pnlLeft.add(btnXoaTrang);
		
		tblDsKM = new JTable();
		
		String[] tieude = {
			"Mã khuyến mãi", "Tên chương trình ", "Ngày bắt đầu", "Ngày kết thúc"};
	modelDSKhuyenMai = new DefaultTableModel(tieude,0);
		JScrollPane scrDSKM = new JScrollPane();
		scrDSKM.setBounds(20, 310, 460, 249);
		pnlLeft.add(scrDSKM);
		
		scrDSKM.setViewportView(tblDsKM = new  JTable(modelDSKhuyenMai));
		tblDsKM.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = tblDsKM.getSelectedRow();

		            txtMaKhuyenMai.setText(tblDsKM.getValueAt(row, 0).toString());
		            txtTenCT.setText(tblDsKM.getValueAt(row, 1).toString());

		            // Lấy ngày từ model và cập nhật JDateChooser
		            try {
		                Date ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(tblDsKM.getValueAt(row, 2).toString());
		                ngayBatDau.setDate(ngayBD);
		                Date ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(tblDsKM.getValueAt(row, 3).toString());
		                ngayKetThuc.setDate(ngayKT);
		            } catch (ParseException ex) {
		                ex.printStackTrace();
		            }
		       
		    }
		});


		scrDSKM.setViewportView(tblDsKM);
		
			
		
		
		JPanel pnlRight = new JPanel();
		pnlRight.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pnlRight.setBackground(new Color(192, 192, 192));
		pnlRight.setBounds(523, 59, 745, 620);
		pnlContent.add(pnlRight);
		pnlRight.setLayout(null);
		
		Panel pnlMotQuanAo = new Panel();
		pnlMotQuanAo.setBackground(new Color(64, 128, 128));
		pnlMotQuanAo.setBounds(10, 10, 345, 170);
		pnlRight.add(pnlMotQuanAo);
		pnlMotQuanAo.setLayout(null);
		
		JLabel lblMaQuanAo = new JLabel("Mã quần áo");
		lblMaQuanAo.setFont(new Font("Arial", Font.BOLD, 14));
		lblMaQuanAo.setBounds(10, 10, 123, 30);
		pnlMotQuanAo.add(lblMaQuanAo);
		
		cboMaQuanAo = new JComboBox();
		cboMaQuanAo.setModel(new DefaultComboBoxModel(new String[] {"Không"}));
		cboMaQuanAo.setBounds(106, 12, 117, 21);
		pnlMotQuanAo.add(cboMaQuanAo);
		
		JLabel lblMaKhuyenMai = new JLabel("Mã KM");
		lblMaKhuyenMai.setFont(new Font("Arial", Font.BOLD, 14));
		lblMaKhuyenMai.setBounds(10, 54, 123, 30);
		pnlMotQuanAo.add(lblMaKhuyenMai);
		
		cboMaKM = new JComboBox();
		cboMaKM.setBounds(106, 56, 117, 21);
		pnlMotQuanAo.add(cboMaKM);
		
		JLabel lblTiLeKM = new JLabel("Tỉ lệ KM");
		lblTiLeKM.setFont(new Font("Arial", Font.BOLD, 14));
		lblTiLeKM.setBounds(10, 99, 123, 30);
		pnlMotQuanAo.add(lblTiLeKM);
		
		txtTiLeKM = new JTextField();
		txtTiLeKM.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTiLeKM.setColumns(10);
		txtTiLeKM.setBounds(106, 101, 117, 22);
		pnlMotQuanAo.add(txtTiLeKM);
		
		Panel pnlNhieuQuanAo = new Panel();
		pnlNhieuQuanAo.setLayout(null);
		pnlNhieuQuanAo.setBackground(new Color(64, 128, 128));
		pnlNhieuQuanAo.setBounds(376, 10, 345, 170);
		pnlRight.add(pnlNhieuQuanAo);
		
		JLabel lblMaQuanAo_1 = new JLabel("Mã quần áo");
		lblMaQuanAo_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblMaQuanAo_1.setBounds(10, 10, 91, 30);
		pnlNhieuQuanAo.add(lblMaQuanAo_1);
		
		cboMaQuanAoNhieu1 = new JComboBox();
		cboMaQuanAoNhieu1.setModel(new DefaultComboBoxModel(new String[] {"Không"}));
		cboMaQuanAoNhieu1.setBounds(10, 50, 117, 21);
		pnlNhieuQuanAo.add(cboMaQuanAoNhieu1);
		
		cboMaQuanAoNhieu2 = new JComboBox();
		cboMaQuanAoNhieu2.setModel(new DefaultComboBoxModel(new String[] {"Không"}));
		cboMaQuanAoNhieu2.setBounds(178, 50, 117, 21);
		pnlNhieuQuanAo.add(cboMaQuanAoNhieu2);
		
		JLabel lblDen = new JLabel("-->");
		lblDen.setFont(new Font("Arial", Font.BOLD, 24));
		lblDen.setBounds(137, 52, 45, 17);
		pnlNhieuQuanAo.add(lblDen);
		
		JLabel lblTiLeKMNhieu = new JLabel("Tỉ lệ KM");
		lblTiLeKMNhieu.setFont(new Font("Arial", Font.BOLD, 14));
		lblTiLeKMNhieu.setBounds(178, 98, 123, 30);
		pnlNhieuQuanAo.add(lblTiLeKMNhieu);
		
		txtTiLeKmNhieu = new JTextField();
		txtTiLeKmNhieu.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTiLeKmNhieu.setColumns(10);
		txtTiLeKmNhieu.setBounds(178, 124, 117, 22);
		pnlNhieuQuanAo.add(txtTiLeKmNhieu);
		
		JLabel lblMaKMNhieu = new JLabel("Mã KM");
		lblMaKMNhieu.setFont(new Font("Arial", Font.BOLD, 14));
		lblMaKMNhieu.setBounds(10, 98, 123, 30);
		pnlNhieuQuanAo.add(lblMaKMNhieu);
		
		cboMaKMNhieu = new JComboBox();
		cboMaKMNhieu.setBounds(10, 127, 117, 21);
		pnlNhieuQuanAo.add(cboMaKMNhieu);
		
				
		String[] tieude1 = {
			"Mã quần áo","Tên quần áo", "Mã khuyến mãi ", "Tỉ lệ KM"};
		modelQAKM = new DefaultTableModel(tieude1,0);
		JScrollPane scrQAKM = new JScrollPane();
		scrQAKM.setBounds(10, 254, 711, 308);
		pnlRight.add(scrQAKM);
		
		scrQAKM.setViewportView(tblQaKM = new  JTable(modelQAKM));
		tblQaKM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblQaKM.getSelectedRow();
				cboMaQuanAo.setSelectedItem(tblQaKM.getValueAt(row, 0));
				cboMaKM.setSelectedItem(tblQaKM.getValueAt(row, 2));
				txtTiLeKM.setText(tblQaKM.getValueAt(row, 3).toString());
			}
		});
		scrQAKM.setViewportView(tblQaKM);
		
		JButton btnThemQAKM = new JButton("THÊM");
		btnThemQAKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themQuanAoKM();
				quanAo.updateLaiMaKM();
				
			}
		});
		btnThemQAKM.setFont(new Font("Arial", Font.BOLD, 20));
		btnThemQAKM.setBounds(10, 201, 98, 30);
		pnlRight.add(btnThemQAKM);
		
		JButton btnXoaQAKM = new JButton("XÓA");
		btnXoaQAKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblQaKM.getSelectedRow();
				if(row >=0) {
				int n = JOptionPane.showConfirmDialog(null,"Chắc chắn xóa ? ", "Cảnh báo", JOptionPane.YES_NO_OPTION );
				if(n== JOptionPane.YES_OPTION) {
					
					xoaQAKM(tblQaKM.getValueAt(row,0).toString(), tblQaKM.getValueAt(row,2).toString());
					quanAo.updateLaiMaKM();
					JOptionPane.showMessageDialog(null, "Xóa thành công ");
				}
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn quần áo khuyến mãi cần xóa ! ");
				}
			}
		});
		btnXoaQAKM.setFont(new Font("Arial", Font.BOLD, 20));
		btnXoaQAKM.setBounds(124, 201, 98, 30);
		pnlRight.add(btnXoaQAKM);
		
		JButton btnSuaQAKM = new JButton("SỬA");
		btnSuaQAKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblQaKM.getSelectedRow();
				if(row >=0 ) {
					suaQAKM();
				}else {
					JOptionPane.showMessageDialog(null,"Vui lòng chọn quần áo khuyến mãi cần sửa !");
				}
			}
			
		});
		btnSuaQAKM.setFont(new Font("Arial", Font.BOLD, 20));
		btnSuaQAKM.setBounds(240, 201, 98, 30);
		pnlRight.add(btnSuaQAKM);
		updateComboMaQuanAo();
		updateData();
		updateDataKM();
		updateDataQAKM();
		
	}

	  
	  //Update danh sách 
	  public void updateComboMaQuanAo() {
			DAO_QuanAo dao= new DAO_QuanAo();
			for (QuanAo qa : dao.getAllQuanAo()) {
				cboMaQuanAo.addItem(qa.getMaQuanAo());
				cboMaQuanAoNhieu1.addItem(qa.getMaQuanAo());
				cboMaQuanAoNhieu2.addItem(qa.getMaQuanAo());
				}
			}
	//Tạo mã tăng tự động
		public String taoMa() {

			DAO_KhuyenMai dao = new DAO_KhuyenMai();
			
			int n = dao.getAllKhuyenMai().size();
			if(n<9) {
			do {
				 n=n+1;
				
				ma = "KM00" + String.valueOf(n);
				list = dao.getAllKhuyenMai();
			} while (list.contains(ma));
			
		}else if(n<99) {
			do {
				 n=n+1;
				
				ma = "KM0" + String.valueOf(n);
				list = dao.getAllKhuyenMai();
			} while (list.contains(ma));
		}
		else if(n<999) {
			do {
				 n=n+1;
				
				ma = "KM" + String.valueOf(n);
				list = dao.getAllKhuyenMai();
			} while (list.contains(ma));
		}
			return ma;
		}
		
		//LoadDS
		 public void updateData() {
				DAO_KhuyenMai dao = new DAO_KhuyenMai();
				List<KhuyenMai> list = dao.getAllKhuyenMai();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				for(KhuyenMai km : list) {
					Object [] data = {km.getMaKM(),km.getTenChuongTrinh(),dateFormat.format(km.getNgayBatDau()),dateFormat.format(km.getNgayKetThuc())};
					modelDSKhuyenMai.addRow(data);
				}
				
			}
		 
		 public void updateDataKM() {
				DAO_KhuyenMai dao = new DAO_KhuyenMai();
				List<KhuyenMai> list = dao.getAllKhuyenMai();
				if(list.isEmpty()) {
					cboMaKM.addItem("Không");
					cboMaKMNhieu.addItem("Không");
				}else {
					for(KhuyenMai km : list) {
						cboMaKM.addItem(km.getMaKM());
						cboMaKMNhieu.addItem(km.getMaKM());
						
					}
				}
				
			}
		 
		 public void updateDataQAKM() {
				DAO_ChiTietKhuyenMai dao = new DAO_ChiTietKhuyenMai();
				List<ChiTietKhuyenMai> list = dao.getAllChiTietKhuyenMai();
				
				for(ChiTietKhuyenMai ctkm : list) {
					  Object[] data = { ctkm.getQuanAo().getMaQuanAo(),ctkm.getQuanAo().getTenQuanAo(),ctkm.getMaKM().getMaKM(),ctkm.getTiLeKM()};
				        modelQAKM.addRow(data);
					
				}
				
			}
		
		// Lấy ngày kết thúc của khuyến mãi đã chọn
		 private Date getEndDateOfSelectedPromotion(String maKM) {
		     DAO_KhuyenMai daoKM = new DAO_KhuyenMai();
		     KhuyenMai km = daoKM.getKhuyenMaiByMa(maKM);

		     
		     if (km != null) {
		         return km.getNgayKetThuc();
		     }

		     return null;
		 }
		 
		//Thêm
		public void themKM() {
		    DAO_KhuyenMai dao = new DAO_KhuyenMai();
		    String ma = taoMa();
		    String tenCT = txtTenCT.getText().trim();
		    Date ngayBD = ngayBatDau.getDate();
		    Date ngayKT = ngayKetThuc.getDate();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    KhuyenMai km = new KhuyenMai(ma, tenCT, ngayBD, ngayKT);
		    if (dao.create(km)) {
		        
		        Object[] data = { ma, tenCT,dateFormat.format(ngayBD),dateFormat.format( ngayKT) };
		        modelDSKhuyenMai.addRow(data);
		    }
		}
		
		public void themQuanAoKM() {
		    DAO_ChiTietKhuyenMai dao = new DAO_ChiTietKhuyenMai();
		    if(cboMaQuanAo.getSelectedItem().equals("Không") && txtTiLeKM.getText().equals("") && cboMaQuanAoNhieu1.getSelectedItem().equals("Không") 
		    	&& cboMaQuanAoNhieu2.getSelectedItem().equals("Không") && txtTiLeKmNhieu.getText().equals("")) {
		    	JOptionPane.showMessageDialog(null, "Thông tin quần áo khuyến mãi không được rỗng !");
		    	return;
		    }
		    if(cboMaQuanAoNhieu1.getSelectedItem().equals("Không") && cboMaQuanAoNhieu2.getSelectedItem().equals("Không")
		    		&& txtTiLeKmNhieu.getText().equals("") && (!cboMaQuanAo.getSelectedItem().equals("Không") || !txtTiLeKM.getText().equals(""))) {
		    	if(validDataChiTiet1SP()) {
				    String maQuanAo = cboMaQuanAo.getSelectedItem().toString();
				    DAO_QuanAo daoQuanAo = new DAO_QuanAo();
				    QuanAo quanAo = daoQuanAo.getQuanAoByMa(maQuanAo);
				    String tenQuanAo = quanAo.getTenQuanAo();
				    String maKM = cboMaKM.getSelectedItem().toString();
				    Float tiLeKM = Float.parseFloat(txtTiLeKM.getText().trim());
				    List<ChiTietKhuyenMai> list = dao.getAllChiTietKhuyenMai();
				    for (ChiTietKhuyenMai chiTietKhuyenMai : list) {
						if(chiTietKhuyenMai.getQuanAo().getMaQuanAo().equals(maQuanAo)) {
							JOptionPane.showMessageDialog(this, "Quần áo đang trong đợt khuyến mãi khác !");
							return;
						}
					}
				    if (kiemTraDaCoKMDoChua(maQuanAo, maKM,tiLeKM)) {
				        JOptionPane.showMessageDialog(this, "Mã quần áo đã có mã khuyến mãi này !");
				        return;
				    }
				    
				    // Lấy ngày kết thúc của khuyến mãi đã chọn
				    Date ngayKT = getEndDateOfSelectedPromotion(maKM);
				    if (ngayKT != null && ngayKT.before(new Date())) {
				        JOptionPane.showMessageDialog(this, "Khuyến mãi đã kết thúc !");
				        return; 
				    }
				   
				    QuanAo qa = new QuanAo(maQuanAo);
				    ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai(new KhuyenMai(maKM), qa, tiLeKM);
		
				    if (dao.create(ctkm)) {
				    	if (daoQuanAo.updateMaKM(maQuanAo, maKM)) {
				        Object[] data = {maQuanAo, quanAo.getTenQuanAo(), maKM, tiLeKM};
				        modelQAKM.addRow(data);
				    }
			    }
		    	}
		    }
		    if (cboMaQuanAo.getSelectedItem().equals("Không") && txtTiLeKM.getText().equals("") &&
		    	(!cboMaQuanAoNhieu1.getSelectedItem().equals("Không") || cboMaQuanAoNhieu2.getSelectedItem().equals("Không")
		    	|| txtTiLeKmNhieu.getText().equals(""))
		    		) {
		    	if(validDataChiTietNhieuSP()) {
			        String maKM = cboMaKMNhieu.getSelectedItem().toString();
			        Float tiLeKM = Float.parseFloat(txtTiLeKmNhieu.getText().trim());
	
			        // Lấy danh sách quần áo từ cboMaQuanAoNhieu1 đến cboMaQuanAoNhieu2
			        List<String> listMaQuanAo = new ArrayList<>();
			        for (int i = cboMaQuanAoNhieu1.getSelectedIndex(); i <= cboMaQuanAoNhieu2.getSelectedIndex(); i++) {
			            listMaQuanAo.add(cboMaQuanAoNhieu1.getItemAt(i).toString());
			        }
	
			        // Thêm chi tiết khuyến mãi cho từng sản phẩm
			        for (String maQuanAo : listMaQuanAo) {
			            DAO_QuanAo daoQuanAo = new DAO_QuanAo();
			            QuanAo quanAo = daoQuanAo.getQuanAoByMa(maQuanAo);
			            String tenQuanAo = quanAo.getTenQuanAo();
	
			            // Kiểm tra xem quần áo đã có trong đợt khuyến mãi nào chưa
			            List<ChiTietKhuyenMai> list = dao.getAllChiTietKhuyenMai();
			            for (ChiTietKhuyenMai chiTietKhuyenMai : list) {
			                if (chiTietKhuyenMai.getQuanAo().getMaQuanAo().equals(maQuanAo)) {
			                    JOptionPane.showMessageDialog(this, "Quần áo đang trong đợt khuyến mãi khác !");
			                    return;
			                }
			            }
	
			            // Kiểm tra xem quần áo đã có mã khuyến mãi này chưa
			            if (kiemTraDaCoKMDoChua(maQuanAo, maKM, tiLeKM)) {
			                JOptionPane.showMessageDialog(this, "Mã quần áo đã có mã khuyến mãi này !");
			                return;
			            }
	
			            // Lấy ngày kết thúc của khuyến mãi đã chọn
			            Date ngayKT = getEndDateOfSelectedPromotion(maKM);
			            if (ngayKT != null && ngayKT.before(new Date())) {
			                JOptionPane.showMessageDialog(this, "Khuyến mãi đã kết thúc !");
			                return;
			            }
	
			            QuanAo qa = new QuanAo(maQuanAo);
			            ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai(new KhuyenMai(maKM), qa, tiLeKM);
	
			            if (dao.create(ctkm)) {
			                if (daoQuanAo.updateMaKM(maQuanAo, maKM)) {
			                    Object[] data = {maQuanAo, tenQuanAo, maKM, tiLeKM};
			                    modelQAKM.addRow(data);
			                }
			            }
		        }
		        }
		    }
		}
		
		
		//Xóa 
		public void xoaKM() {
            DAO_KhuyenMai dao = new DAO_KhuyenMai();
            int row = tblDsKM.getSelectedRow();
            String maKM = (String) tblDsKM.getValueAt(row, 0);
            if (dao.delete(maKM)) {
                modelDSKhuyenMai.removeRow(row);
                for (int i = row; i < modelDSKhuyenMai.getRowCount(); i++) {
                    String MaLoai = modelDSKhuyenMai.getValueAt(i, 0).toString();
                    int maMoi = Integer.parseInt(MaLoai.substring(3)) - deletedRowCount;
                    String newMaLoai = "KM" + String.format("%03d", maMoi);
                    modelDSKhuyenMai.setValueAt(newMaLoai, i, 0);
                }

                dao.updateAllMaKM(deletedRowCount, maKM);
                cboMaKM.removeAllItems();
                cboMaKMNhieu.removeAllItems();
                updateDataKM();
                DAO_ChiTietKhuyenMai daoCT = new DAO_ChiTietKhuyenMai();
                
                if (daoCT.deleteAllByMaKM(maKM)) {

                    DAO_QuanAo dao_QuanAo = new DAO_QuanAo();
                    List<QuanAo> list = dao_QuanAo.getAllQuanAoByMaKM(maKM);
                    for (QuanAo quanAo : list) {
                        dao_QuanAo.updateMaKM(quanAo.getMaQuanAo(), "Không");
                       
                    }
                    
                   
                }
            }
        }
    
		  
		  public void xoaQAKM(String maQuanAo, String maKM) {
			  DAO_ChiTietKhuyenMai dao = new DAO_ChiTietKhuyenMai();
			  DAO_QuanAo daoQuanAo = new DAO_QuanAo();
			  int row = tblQaKM.getSelectedRow();
			 
			  if (daoQuanAo.updateMaKM(maQuanAo, "Không")) {
			       dao.delete(maQuanAo, maKM);
			       modelQAKM.removeRow(row);
			       
			  
			  }
			}

		 //Sửa 
		  public void suaKM() {
		    	
		    	DAO_KhuyenMai dao = new DAO_KhuyenMai();
		    	
			    String ma = txtMaKhuyenMai.getText().trim();
			    String tenCT = txtTenCT.getText().trim();
			    Date ngayBD = ngayBatDau.getDate();
			    Date ngayKT = ngayKetThuc.getDate();
			    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			    KhuyenMai km = new KhuyenMai(ma, tenCT, ngayBD, ngayKT);
				if (dao.update(km)) {
					JOptionPane.showMessageDialog(this, "Sửa thành công");
					String[] data = { ma, tenCT, dateFormat.format(ngayBD), dateFormat.format(ngayKT)};
					modelDSKhuyenMai.addRow(data);
					int row = tblDsKM.getSelectedRow();
					modelDSKhuyenMai.removeRow(row);
				}


		    }
		  public void suaQAKM() {
			    DAO_ChiTietKhuyenMai dao = new DAO_ChiTietKhuyenMai();
			    int row = tblQaKM.getSelectedRow();
			    String maQA = tblQaKM.getValueAt(row, 0).toString();
			    String makm = tblQaKM.getValueAt(row, 2).toString();
			    Float tiLeKm = Float.parseFloat(txtTiLeKM.getText());
			    
			    QuanAo qa = new QuanAo(maQA);
			    KhuyenMai km = new KhuyenMai(makm);
			 
			    int n = JOptionPane.showConfirmDialog(this, "Chắc chắn sửa ?", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
			    if (n == JOptionPane.YES_OPTION) {
			        ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai(km, qa, tiLeKm);
			        dao.update(chiTietKhuyenMai);
			        JOptionPane.showMessageDialog(this, "Sửa thành công");
	                tblQaKM.setValueAt(tiLeKm, row, 3);
			       
			    }
			}


		  //Xóa trắng
		  public void xoaTrang() {
				txtMaKhuyenMai.setText("");
				txtTenCT.setText("");
				ngayBatDau.setDate(null);
				ngayKetThuc.setDate(null);
				
		  }

		  //Kiểm tra quần áo đó đã có khuyến mãi đó chưa
		  private boolean kiemTraDaCoKMDoChua(String maQuanAo, String maKM, float tiLeKM) {
			    DAO_ChiTietKhuyenMai dao = new DAO_ChiTietKhuyenMai();
			    List<ChiTietKhuyenMai> list = dao.getAllChiTietKhuyenMai();

			    for (ChiTietKhuyenMai ctkm : list) {
			        if (ctkm.getQuanAo().getMaQuanAo().equals(maQuanAo) && ctkm.getMaKM().getMaKM().equals(maKM) && ctkm.getTiLeKM() == tiLeKM) {
			            return true; 
			        }
			    }
			    return false;
			}
		// Phương thức để lấy tỉ lệ khuyến mãi từ tblQAKM trong GUI_KhuyenMai
		  public float layTileKhuyenMaiTuGUIKhuyenMai(String maQuanAo, String maKhuyenMai) {
		      int rowCount = tblQaKM.getRowCount();
		      for (int i = 0; i < rowCount; i++) {
		          String maQA = tblQaKM.getValueAt(i, 0).toString();
		          String maKM = tblQaKM.getValueAt(i, 2).toString();

		          if (maQA.equals(maQuanAo) && maKM.equals(maKhuyenMai)) {
		              // Nếu tìm thấy, trả về tỉ lệ khuyến mãi
		              return Float.parseFloat(tblQaKM.getValueAt(i, 3).toString());
		          }
		      }

		      // Nếu không tìm thấy match, trả về 0
		      return 0.0f;
		  }
		  
		  //validData
		  public boolean validDataChiTiet1SP() {
			    String maQuanAo = cboMaQuanAo.getSelectedItem().toString();
			    String maKM = cboMaKM.getSelectedItem().toString();
			    String tiLeKM = txtTiLeKM.getText().trim();

			    if (maQuanAo.equals("Không")) {
			        if (!tiLeKM.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Mã quần áo không được rỗng !");
			            return false;
			        }
			    } else {
			    	if (tiLeKM.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Mã quần áo không được rỗng !");
			            return false;
			        }
			            
			        
			    }

			    return true;
			}

		  public boolean validDataChiTietNhieuSP() {
			 
			  if(cboMaQuanAoNhieu1.getSelectedItem().equals("Không")) {
				  if((!cboMaQuanAoNhieu2.getSelectedItem().equals("Không") || cboMaQuanAoNhieu2.getSelectedItem().equals("Không"))
						 && (!txtTiLeKmNhieu.getText().equals("") || txtTiLeKmNhieu.getText().equals("") )) {
					  JOptionPane.showMessageDialog(null, "Mã quần áo bắt đầu không được rỗng !");
					  return false;
				  }
				  		  
			  }else {
				  if(cboMaQuanAoNhieu2.getSelectedItem().equals("Không")) {
					  JOptionPane.showMessageDialog(null, "Mã quần áo kết thúc không được rỗng !");
					  return false;
				  }
				  if(txtTiLeKmNhieu.getText().equals("")) {
					  JOptionPane.showMessageDialog(null, "Tỉ lệ khuyến mãi không được rỗng !");
					  return false;
				  }
				  
			  }
			 
			  return true;
		  }
}
