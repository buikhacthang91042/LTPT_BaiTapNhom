package GUI;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;





import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;





import javax.swing.JLabel;





import java.awt.Font;





import javax.swing.JButton;





import java.awt.Color;





import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;





import DAO.DAO_ThongKe;





import com.toedter.calendar.JDateChooser;





import java.awt.Panel;





import javax.swing.JScrollPane;





import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;





import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class GUI_ThongKeKhachHangMuaNhieu extends JPanel{
	private JPanel pnHienThiBieuDo, pnHienThiGiaTriMuaNhieuNhat, pnHienThiKHCoLuotMuaCaoNhat;
	private DefaultTableModel modelGiaTriMua, modelLuotMua, modelKhachHang;
	private JTable tblHienThiKhachHang, tblHienThiGiaTriMua, tblHienThiLuotMua;
	private JDateChooser dcNgayBatDau, dcNgayKetThuc;
	private JButton btnThongKe;
	
	public GUI_ThongKeKhachHangMuaNhieu() {
		setBounds(new Rectangle(0, 0, 1308, 678));
		setLayout(null);
		
		JPanel pnYeuCau = new JPanel();
		pnYeuCau.setLayout(null);
		pnYeuCau.setBackground(SystemColor.controlHighlight);
		pnYeuCau.setBounds(0, 0, 1288, 192);
		add(pnYeuCau);
		
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu");
		lblNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNgayBatDau.setBounds(72, 43, 225, 32);
		pnYeuCau.add(lblNgayBatDau);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
		lblNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNgayKetThuc.setBounds(606, 43, 225, 32);
		pnYeuCau.add(lblNgayKetThuc);
		
		btnThongKe = new JButton("THỐNG KÊ");
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setFont(new Font("Arial", Font.PLAIN, 20));
		btnThongKe.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnThongKe.setBackground(new Color(0, 64, 0));
		btnThongKe.setBounds(846, 116, 200, 45);
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thucThi();
			}
		});
		pnYeuCau.add(btnThongKe);
		
		dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.setDateFormatString("dd/MM/yyyy");
		dcNgayBatDau.setBounds(312, 44, 225, 32);
		pnYeuCau.add(dcNgayBatDau);
		
		dcNgayKetThuc = new JDateChooser();
		dcNgayKetThuc.setDateFormatString("dd/MM/yyyy");
		dcNgayKetThuc.setBounds(846, 44, 223, 32);
		pnYeuCau.add(dcNgayKetThuc);
		
		Panel pnHienThiKhachHang = new Panel();
		pnHienThiKhachHang.setLayout(null);
		pnHienThiKhachHang.setBounds(0, 328, 644, 350);
		add(pnHienThiKhachHang);
		
		String [] tieudeKhachHang={"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Số lần đã mua", "Tổng giá trị đã mua"};
 		modelKhachHang = new DefaultTableModel(tieudeKhachHang,0);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 40, 644, 310);
		pnHienThiKhachHang.add(scrollPane);
		scrollPane.setViewportView(tblHienThiKhachHang = new  JTable(modelKhachHang));
		scrollPane.setViewportView(tblHienThiKhachHang);
		
		
		Label lblThongTinKH = new Label("Khách hàng");
		lblThongTinKH.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblThongTinKH.setBounds(10, 0, 120, 24);
		pnHienThiKhachHang.add(lblThongTinKH);
		
		pnHienThiBieuDo = new JPanel();
		pnHienThiBieuDo.setBounds(644, 328, 644, 350);
		add(pnHienThiBieuDo);

		pnHienThiGiaTriMuaNhieuNhat = new JPanel();
		pnHienThiGiaTriMuaNhieuNhat.setLayout(null);
		pnHienThiGiaTriMuaNhieuNhat.setBounds(0, 193, 644, 135);
		add(pnHienThiGiaTriMuaNhieuNhat);
		
		String [] tieudeGiaTriMua={"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Tổng giá trị đã mua"};
 		modelGiaTriMua = new DefaultTableModel(tieudeGiaTriMua,0);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 30, 644, 105);
		pnHienThiGiaTriMuaNhieuNhat.add(scrollPane_1);
		scrollPane_1.setViewportView(tblHienThiGiaTriMua = new  JTable(modelGiaTriMua));
		scrollPane_1.setViewportView(tblHienThiGiaTriMua);
		
		Label lblMuaLonNhat = new Label("Khách hàng có giá trị mua lớn nhât");
		lblMuaLonNhat.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblMuaLonNhat.setBounds(10, 0, 312, 24);
		pnHienThiGiaTriMuaNhieuNhat.add(lblMuaLonNhat);
		
		pnHienThiKHCoLuotMuaCaoNhat = new JPanel();
		pnHienThiKHCoLuotMuaCaoNhat.setLayout(null);
		pnHienThiKHCoLuotMuaCaoNhat.setBounds(644, 193, 644, 135);
		add(pnHienThiKHCoLuotMuaCaoNhat);
		
		String [] tieudeLuotMua={"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Số lần đã mua"};
 		modelLuotMua = new DefaultTableModel(tieudeLuotMua,0);
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 30, 644, 105);
		pnHienThiKHCoLuotMuaCaoNhat.add(scrollPane_2);
		scrollPane_2.setViewportView(tblHienThiLuotMua = new  JTable(modelLuotMua));
		scrollPane_2.setViewportView(tblHienThiLuotMua);
		
		Label lblLuotMuaNhieuNhat = new Label("Khách hàng có lượt mua nhiều nhất");
		lblLuotMuaNhieuNhat.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblLuotMuaNhieuNhat.setBounds(10, 0, 276, 24);
		pnHienThiKHCoLuotMuaCaoNhat.add(lblLuotMuaNhieuNhat);
	}
	
	public void thucThi(){
		DAO_ThongKe dao = new DAO_ThongKe();
		java.util.Date utilNgayBatDau = dcNgayBatDau.getDate();
        java.util.Date utilNgayKetThuc = dcNgayKetThuc.getDate();
        
        if (utilNgayBatDau == null || utilNgayKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và ngày kết thúc");
            return;
        }

        Date trc = new Date(utilNgayBatDau.getTime());
        Date sau = new Date(utilNgayKetThuc.getTime());

        // Hiển thị data lên bảng
        hienThiGiaTriMuaCaoNhat(trc, sau);
        hienThiTanXuatMuaCaoNhat(trc, sau);
        hienThiTanXuatMuaVaGiaTriMua(trc, sau);
        
     // Lấy dữ liệu từ hàm layTongTienVaNgayTheoNgay
        List<Object[]> data = dao.layNgayMuaVaFrequencyCaoNhatTheoMaKH(trc, sau);
        
     // Vẽ biểu đồ BarChart
        veBieuDoBarChart(data, pnHienThiBieuDo);
    }
	
	//Khách hàng có giá trị mua cao nhất
	public void hienThiGiaTriMuaCaoNhat(Date trc, Date sau) {
        DAO_ThongKe daoThongKe = new DAO_ThongKe();

        // Xóa dữ liệu cũ trong bảng
        modelGiaTriMua.setRowCount(0);

        // Lấy dữ liệu từ hàm layThongTinKhachHangCoTongGiaTriCaoNhat
        List<Object[]> list = daoThongKe.layThongTinKhachHangCoTongGiaTriCaoNhat(trc, sau);

        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : list) {
        	Object[] data = {rowData[0], rowData[1], rowData[2], rowData[3]};
            modelGiaTriMua.addRow(data);
        }
    }
	
	//Khách hàng có tần xuất mua cao nhất
	public void hienThiTanXuatMuaCaoNhat(Date trc, Date sau) {
        DAO_ThongKe daoThongKe = new DAO_ThongKe();

        // Xóa dữ liệu cũ trong bảng
        modelLuotMua.setRowCount(0);

        // Lấy dữ liệu từ hàm layThongTinKhachHangCoTanSuatMuaCaoNhat
        List<Object[]> list = daoThongKe.layThongTinKhachHangCoTanSuatMuaCaoNhat(trc, sau);

        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : list) {
        	Object[] data = {rowData[0], rowData[1], rowData[2], rowData[3]};
            modelLuotMua.addRow(data);
        }
    }
	
	//Lấy thông tin, tần xuất mua hàng và giá trị mua hàng của khách hàng
	public void hienThiTanXuatMuaVaGiaTriMua(Date trc, Date sau) {
        DAO_ThongKe daoThongKe = new DAO_ThongKe();

        // Xóa dữ liệu cũ trong bảng
        modelKhachHang.setRowCount(0);

        // Lấy dữ liệu từ hàm layTanSuatXuatVaGiaTriMua
        List<Object[]> list = daoThongKe.layThongTinKhachHangTanSuatMuaVaTongGiaTri(trc, sau);

        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : list) {
        	Object[] data = {rowData[0], rowData[1], rowData[2], rowData[3], rowData[4]};
            modelKhachHang.addRow(data);
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
            int tanXuat = (int) rowData[1];

            dataset.addValue(tanXuat, "Tần xuất mua hàng", ngayMua);
        }

        return dataset;
    }

    private JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ khách hàng có tần xuất mua lớn nhất",
                "Ngày",
                "Tần xuất",
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
}
