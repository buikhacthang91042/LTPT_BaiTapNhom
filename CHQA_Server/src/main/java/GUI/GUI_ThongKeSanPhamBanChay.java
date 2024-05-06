package GUI;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Panel;
import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import DAO.DAO_ChuyenDoi;
import DAO.DAO_ThongKe;

import com.toedter.calendar.JDateChooser;

import connect.ConnectDB;
import entity.HoaDon;

import java.awt.Label;

public class GUI_ThongKeSanPhamBanChay extends JPanel {
	private JDateChooser dcNgayBatDau , dcNgayKetThuc;
	private DefaultTableModel model;
	private JTable tblHienThi;
	private Panel pnHienThiSPChayNhat,pnHienThiCacSPDuocBan;
	public GUI_ThongKeSanPhamBanChay() {
		setBounds(new Rectangle(0, 0, 1308, 678));
		setLayout(null);
		
		//ConnectDB
 		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 1288, 192);
		add(panel);
		
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu");
		lblNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNgayBatDau.setBounds(72, 43, 225, 32);
		panel.add(lblNgayBatDau);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
		lblNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNgayKetThuc.setBounds(606, 43, 225, 32);
		panel.add(lblNgayKetThuc);
		
		JButton btnThongKe = new JButton("THỐNG KÊ");
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

		panel.add(btnThongKe);
		
		dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.setDateFormatString("dd/MM/yyyy");
		dcNgayBatDau.setBounds(312, 44, 225, 32);
		panel.add(dcNgayBatDau);
		
		dcNgayKetThuc = new JDateChooser();
		dcNgayKetThuc.setDateFormatString("dd/MM/yyyy");
		dcNgayKetThuc.setBounds(846, 44, 223, 32);
		panel.add(dcNgayKetThuc);
		
		pnHienThiSPChayNhat = new Panel();
		pnHienThiSPChayNhat.setBounds(0, 193, 644, 485);
		add(pnHienThiSPChayNhat);
		
		pnHienThiCacSPDuocBan = new Panel();
		pnHienThiCacSPDuocBan.setBounds(644, 193, 644, 485);
		add(pnHienThiCacSPDuocBan);
//		pnHienThiCacSPDuocBan.setLayout(null);
		
		String [] tieude={"Mã quần áo", "Tên quần áo", "Số lượng đã bán", "Hãng quần áo"};
 		model = new DefaultTableModel(tieude,0);
 		pnHienThiSPChayNhat.setLayout(null);
 		JScrollPane scr = new JScrollPane();
		scr.setBounds(0, 40, 644, 445);
		pnHienThiSPChayNhat.add(scr);
		scr.setViewportView(tblHienThi = new  JTable(model));
		scr.setViewportView(tblHienThi);
		
		Label lblMaBanChay = new Label("Mã quần áo bán chạy nhất");
		lblMaBanChay.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblMaBanChay.setBounds(10, 0, 238, 24);
		pnHienThiSPChayNhat.add(lblMaBanChay);
	}
	
	public void thucThi(){
		java.util.Date utilNgayBatDau = dcNgayBatDau.getDate();
        java.util.Date utilNgayKetThuc = dcNgayKetThuc.getDate();
        
        if (utilNgayBatDau == null || utilNgayKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và ngày kết thúc");
            return;
        }

        Date trc = new Date(utilNgayBatDau.getTime());
        Date sau = new Date(utilNgayKetThuc.getTime());

        // Vẽ biểu đồ Pie Chart
        veBieuDoPieChart(trc, sau);
        // Hiển thị data lên bảng
        updateData(trc, sau);
    }
	
	public void veBieuDoPieChart(Date trc, Date sau) {
	    DAO_ThongKe dao = new DAO_ThongKe();

	    try {
	        List<Object[]> data = dao.layTongSoLuongTheoTenQuanAo(trc, sau);

	        DefaultPieDataset dataset = new DefaultPieDataset();
	        for (Object[] rowData : data) {
	            String tenQuanAo = (String) rowData[0];
	            int tongSoLuong = (int) rowData[1];
	            dataset.setValue(tenQuanAo, tongSoLuong);
	        }

	        JFreeChart chart = ChartFactory.createPieChart(
	                "Biểu đồ Pie Chart: Tỉ lệ Tổng số lượng Quần Áo bán được theo khoảng thời gian đã lựa chọn",
	                dataset,
	                true,
	                true,
	                false
	        );

	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new Dimension(644, 480));

	        // Xóa tất cả các thành phần trong pnBieuDoDoanhThu trước khi thêm ChartPanel
	        pnHienThiCacSPDuocBan.removeAll();

	        // Thêm ChartPanel vào pnBieuDoDoanhThu
	        pnHienThiCacSPDuocBan.add(chartPanel, BorderLayout.CENTER);

	        // Cập nhật UI
	        pnHienThiCacSPDuocBan.revalidate();
	        pnHienThiCacSPDuocBan.repaint();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi vẽ biểu đồ Pie Chart.");
	        e.printStackTrace();
	    }
	}
	
	public void updateData(Date trc, Date sau) {
	    DAO_ThongKe daoThongKe = new DAO_ThongKe();

	    // Lấy dữ liệu từ hàm layThongTinMaQABanChayNhat
	    List<Object[]> list = daoThongKe.layThongTinMaQABanChayNhat(trc, sau);

	    // Kiểm tra nếu không có dữ liệu
	    if (list.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Không có dữ liệu trong khoảng thời gian này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        // Xóa dữ liệu cũ trong bảng
	        model.setRowCount(0);

	        // Thêm dữ liệu mới vào bảng
	        for (Object[] rowData : list) {
	            Object[] data = {rowData[0], rowData[1], rowData[2], rowData[3]};
	            model.addRow(data);
	        }
	    }
	}

}
