package GUI;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Rectangle;

import javax.swing.JLabel;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.HoaDon;
import DAO.DAO_ChuyenDoi;
import DAO.DAO_ThongKe;

import java.awt.Color;

public class GUI_ThongKeCuoiNgay extends JPanel{
	private JTextField txtDoanhThu;
	private JTextField txtTongSoLuong;
	private JLabel lblNgayHienTai;
	private JPanel pnHienThiBaoCao, pnTongQuan;
	private JTable tblBaoCao;
	private DefaultTableModel modelBaocao;
	
	public GUI_ThongKeCuoiNgay() {
		setBounds(new Rectangle(0, 0, 1308, 678));
		setLayout(null);
		
		pnTongQuan = new JPanel();
		pnTongQuan.setBounds(0, 0, 1308, 200);
		add(pnTongQuan);
		pnTongQuan.setLayout(null);
		
		JLabel lblBaoCaoCuoiNgay = new JLabel("BÁO CÁO CUỐI NGÀY");
		lblBaoCaoCuoiNgay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblBaoCaoCuoiNgay.setBounds(509, 13, 290, 50);
		pnTongQuan.add(lblBaoCaoCuoiNgay);
		
		lblNgayHienTai = new JLabel("");
		lblNgayHienTai.setForeground(Color.RED);
		lblNgayHienTai.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNgayHienTai.setBounds(818, 13, 200, 50);
		pnTongQuan.add(lblNgayHienTai);
		capNhatThoiGian();
		
		JLabel lblDoanhThu = new JLabel("Doanh Thu:");
		lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDoanhThu.setBounds(58, 91, 182, 50);
		pnTongQuan.add(lblDoanhThu);
		
		txtDoanhThu = new JTextField();
		txtDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDoanhThu.setBorder(null);
		txtDoanhThu.setEditable(false);
		txtDoanhThu.setBounds(246, 91, 243, 50);
		pnTongQuan.add(txtDoanhThu);
		txtDoanhThu.setColumns(10);
		
		JLabel lblTongSoLuong = new JLabel("Tổng số lượng đã bán:");
		lblTongSoLuong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongSoLuong.setBounds(772, 91, 259, 50);
		pnTongQuan.add(lblTongSoLuong);
		
		txtTongSoLuong = new JTextField();
		txtTongSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongSoLuong.setBorder(null);
		txtTongSoLuong.setEditable(false);
		txtTongSoLuong.setColumns(10);
		txtTongSoLuong.setBounds(1037, 91, 243, 50);
		pnTongQuan.add(txtTongSoLuong);
		
		pnHienThiBaoCao = new JPanel();
		pnHienThiBaoCao.setBounds(0, 198, 1308, 480);
		add(pnHienThiBaoCao);
		String [] tieude={"Mã quần áo","Tên quần áo","Số lượng tồn","Số lượng nhập vào","Số lượng đã bán","Tổng doanh số"};
 		modelBaocao = new DefaultTableModel(tieude,0);
 		JScrollPane scrBaoCao = new JScrollPane();
		scrBaoCao.setBounds(0, 0, 1308, 480);
		pnHienThiBaoCao.add(scrBaoCao);
		pnHienThiBaoCao.setLayout(null);
		scrBaoCao.setViewportView(tblBaoCao = new  JTable(modelBaocao));
		scrBaoCao.setViewportView(tblBaoCao);
		

		
 		
		
		
		//======== Hiển thị
		capNhatThongTinLienTuc();
	}
	
	public void thucThi(){
		DAO_ThongKe dao = new DAO_ThongKe();
	    DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();
	    
	    // Hiển thị doanh thu
        double kq = dao.hienThiDoanhThuHienTai();
        txtDoanhThu.setText(chuyenDoi.DinhDangTien(kq));
        
        // Hiển thị số lượng
        int sl = dao.hienThiSoLuongHienTai();
        txtTongSoLuong.setText(String.valueOf(sl));
    }
	
	//cập nhật liên tục vì ko có tác động đến GUI
	private void capNhatThongTinLienTuc() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    thucThi();
                    updateData();

                   
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }
	
	private void capNhatThoiGian() {
	    Thread thread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                while (true) {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                    String thoiGian = dateFormat.format(new Date());
	                    lblNgayHienTai.setText(thoiGian);
	                    Thread.sleep(1000); // Cập nhật mỗi giây
	                }
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    });

	    thread.start();
	}
	
	public void updateData() {
	    DAO_ThongKe daoThongKe = new DAO_ThongKe();

	    // Xóa dữ liệu cũ trong bảng
	    modelBaocao.setRowCount(0);

	    // Lấy dữ liệu từ hàm layThongTinQuanAo
	    List<Object[]> list = daoThongKe.layThongTinQuanAo();

	    // Thêm dữ liệu mới vào bảng
	    for (Object[] rowData : list) {
	        modelBaocao.addRow(rowData);
	    }
	}


}
