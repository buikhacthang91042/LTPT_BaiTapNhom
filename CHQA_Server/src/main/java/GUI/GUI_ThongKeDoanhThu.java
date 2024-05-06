package GUI;

import javax.swing.JPanel;

import DAO.DAO_ChuyenDoi;
import DAO.DAO_HoaDon;
import DAO.DAO_KhachHang;
import DAO.DAO_LoaiQuanAo;
import DAO.DAO_NhaCungCap;
import DAO.DAO_ThongKe;
import connect.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiQuanAo;
import entity.NhaCungCap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;

public class GUI_ThongKeDoanhThu extends JPanel {
	private JTextField txtHienThiDoanhThu;
	private JPanel pnBieuDoDoanhThu;
	private DefaultTableModel modelHoaDon;
	private JTable tblHoaDon;
	private JDateChooser dcNgayKetThuc, dcNgayBatDau;

	public GUI_ThongKeDoanhThu() {
		setBackground(new Color(0, 64, 64));
		setBounds(new Rectangle(0, 0, 1308, 678));
		setLayout(null);
		
		//ConnectDB
 		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel pnForm = new JPanel();
		pnForm.setLayout(null);
		pnForm.setBackground(SystemColor.controlHighlight);
		pnForm.setBounds(10, 20, 1288, 181);
		add(pnForm);
		
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu");
		lblNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNgayBatDau.setBounds(72, 43, 225, 32);
		pnForm.add(lblNgayBatDau);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
		lblNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNgayKetThuc.setBounds(606, 43, 225, 32);
		pnForm.add(lblNgayKetThuc);
		
		JButton btnThongKe = new JButton("THỐNG KÊ");
		btnThongKe.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienThiDoanhThuTheoNgay();
			}
		});
		btnThongKe.setFont(new Font("Arial", Font.PLAIN, 20));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setBackground(new Color(0, 64, 0));
		btnThongKe.setBounds(846, 116, 200, 45);
		pnForm.add(btnThongKe);
		
		dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.setDateFormatString("dd/MM/yyyy");
		dcNgayBatDau.setBounds(312, 44, 225, 32);
		pnForm.add(dcNgayBatDau);
		
		dcNgayKetThuc = new JDateChooser();
		dcNgayKetThuc.setDateFormatString("dd/MM/yyyy");
		dcNgayKetThuc.setBounds(846, 44, 223, 32);
		pnForm.add(dcNgayKetThuc);
		
		JPanel pnSoLieu = new JPanel();
		pnSoLieu.setLayout(null);
		pnSoLieu.setBackground(SystemColor.controlHighlight);
		pnSoLieu.setBounds(0, 214, 1308, 90);
		add(pnSoLieu);
		
		JLabel lblDoanhThu = new JLabel("Doanh Thu");
		lblDoanhThu.setForeground(SystemColor.controlDkShadow);
		lblDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDoanhThu.setBounds(94, 33, 192, 30);
		pnSoLieu.add(lblDoanhThu);
		
		txtHienThiDoanhThu = new JTextField();
		txtHienThiDoanhThu.setBounds(787, 33, 238, 30);
		txtHienThiDoanhThu.setEditable(false);
		pnSoLieu.add(txtHienThiDoanhThu);
		txtHienThiDoanhThu.setColumns(10);
		
		pnBieuDoDoanhThu = new JPanel();
		pnBieuDoDoanhThu.setBounds(656, 317, 642, 361);
		add(pnBieuDoDoanhThu);
		
		String [] tieude={"Mã hóa đơn","Ngày mua","Mã nhân viên","Tên nhân viên","Mã khách hàng","Tên khách hàng","Tổng tiền"};
 		modelHoaDon = new DefaultTableModel(tieude,0);
 		JScrollPane scrHoaDon = new JScrollPane();
		scrHoaDon.setBounds(10, 317, 642, 361);
		add(scrHoaDon);
		
		scrHoaDon.setViewportView(tblHoaDon = new  JTable(modelHoaDon));
		scrHoaDon.setViewportView(tblHoaDon);
		
		
	}
//	 public void updateComboLoaiQuanAo() {
//			DAO_LoaiQuanAo dao = new DAO_LoaiQuanAo();
//			for(LoaiQuanAo loai : dao.getAllLoaiQuanAo()) {
//				cboLoaiQuanAo.addItem(loai.getTenLoai());
//			}
//		}
		
//		public void updateComboNhaCungCap() {
//			DAO_NhaCungCap dao = new DAO_NhaCungCap();
//			for(NhaCungCap loai : dao.getAllNhaCungCap()) {
//				cboNhaCungCap.addItem(loai.getTenNCC());
//			}
//		}
		
		

		public void hienThiDoanhThuTheoNgay() {
		    DAO_ThongKe dao = new DAO_ThongKe();
		    DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();

		    try {
		        java.util.Date utilNgayBatDau = dcNgayBatDau.getDate();
		        java.util.Date utilNgayKetThuc = dcNgayKetThuc.getDate();
		        
		        if (utilNgayBatDau == null || utilNgayKetThuc == null) {
		            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và ngày kết thúc");
		            return;
		        }

		        Date trc = new Date(utilNgayBatDau.getTime());
		        Date sau = new Date(utilNgayKetThuc.getTime());

		        double kq = dao.hienThiDoanhThuTheoNgay(trc, sau);
		        String doanhThuString = String.valueOf(kq);

		        // Hiển thị doanh thu
		        txtHienThiDoanhThu.setText(chuyenDoi.DinhDangTien(kq));
		        
		     // Gọi phương thức updateData để cập nhật dữ liệu lên modelHoaDon
		        updateData(trc, sau);
		        
		        // Lấy dữ liệu từ hàm layTongTienVaNgayTheoNgay
	            List<Object[]> data = dao.layTongTienVaNgayTheoNgay(trc, sau);
	            
		     // Vẽ biểu đồ BarChart
	            veBieuDoBarChart(data, pnBieuDoDoanhThu);
	
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tính toán doanh thu.");
		        e.printStackTrace();
		    }
		}

		private void veBieuDoBarChart(List<Object[]> data, JPanel containerPanel) {
		    CategoryDataset dataset = createDataset(data);
		    JFreeChart barChart = createBarChart(dataset);

		    ChartPanel chartPanel = new ChartPanel(barChart);
		    chartPanel.setPreferredSize(new Dimension(600, 400));

		    // Xóa tất cả các thành phần trong containerPanel trước khi thêm ChartPanel
		    containerPanel.removeAll();

		    // Thêm ChartPanel vào containerPanel
		    containerPanel.add(chartPanel, BorderLayout.CENTER);

		    // Cập nhật UI
		    containerPanel.revalidate();
		    containerPanel.repaint();
		}

	    private CategoryDataset createDataset(List<Object[]> data) {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	        for (Object[] rowData : data) {
	            Date ngayMua = (Date) rowData[0];
	            double tongTien = (Double) rowData[1];

	            dataset.addValue(tongTien, "Doanh thu", ngayMua);
	        }

	        return dataset;
	    }

	    private JFreeChart createBarChart(CategoryDataset dataset) {
	        JFreeChart barChart = ChartFactory.createBarChart(
	                "Biểu đồ doanh thu",
	                "Ngày",
	                "Doanh Thu",
	                dataset,
	                PlotOrientation.VERTICAL,
	                true, true, false);

	        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
	        plot.setBackgroundPaint(new Color(255, 255, 255));

	        CategoryAxis axis = plot.getDomainAxis();
	        axis.setLowerMargin(0.01);
	        axis.setUpperMargin(0.01);
	        axis.setCategoryMargin(0.1);

	        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
	        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

	        return barChart;
	    }
	    
	    public void updateData(Date trc, Date sau) {
	        DAO_ThongKe daoThongKe = new DAO_ThongKe();
	        DAO_ChuyenDoi chuyenDoi = new DAO_ChuyenDoi();

	        // Xóa dữ liệu cũ trong bảng
	        modelHoaDon.setRowCount(0);

	        // Lấy dữ liệu từ hàm layThongTinHoaDonTrongKhoangNgay
	        List<HoaDon> list = daoThongKe.layThongTinHoaDonTrongKhoangNgay(trc, sau);

	        // Kiểm tra nếu không có dữ liệu
	        if (list.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Không có dữ liệu trong khoảng thời gian này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            // Thêm dữ liệu mới vào bảng
	            for (HoaDon hd : list) {
	                Object[] data = {hd.getMaHD(), hd.getNgayMua(), hd.getNV().getMaNV(), hd.getNV().getTenNV(), hd.getKH().getMaKH(), hd.getKH().getHoTen(), chuyenDoi.DinhDangTien(hd.getTongTien())};
	                modelHoaDon.addRow(data);
	            }
	        }
	    }

}
