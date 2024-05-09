/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import DAO.DAO_NhanVien;
import DAO.EntityManagerFactoryUtil;
import entity.NhanVien;
import jakarta.persistence.EntityManager;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;


public class GUI_Tong extends JFrame {
    private JPanel pnlThongTin,pnlTieuDe,pnlMenu,pnlNhanVien,pnlKhachHang,pnlQuanAo,pnlThongKe;
   	private JLabel lblTenNhanVien,lblTieuDe,lblThoiGian;
    private JMenuBar mnuNhanVien,mnuKhachHang,mnuQuanAo,mnuThongKe;
    private JMenu mnNhanVien,mnKhachHang,mnQuanAo,mnThongKe;
    private JMenuItem mniBanHang,mniCapNhatNhanVien,mniTimKiemNhanVien,mniCapNhatQuanAo,mniTimNhanVien,mniTimKiemQuanAo,mniTimKiemKhachHang,mniCuoiNgay,mniDoanhThu,mniTimKiemNhaCungCap
    ,mniDatHangTruoc,mniLoaiQuanAo,mniLichSuMuaHang,mniSanPham,mniKhachHang,mniKhuyenMai;
    private String tenNhanVien;
   
   
    public GUI_Tong() {
    	EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
        EntityManager entityManager = util.getEnManager();
    	setTitle("Cửa hàng quần áo thời trang");
    	setSize(new Dimension(871, 473));
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Import các GUI
        GUI_CapNhatKhachHang capNhatKhachHang = new GUI_CapNhatKhachHang();
        capNhatKhachHang.setBounds(0, 0, 1308, 710);
        GUI_CapNhatNhanVien capNhatNhanVien = new GUI_CapNhatNhanVien();
        capNhatNhanVien.setBounds(0, 0, 1308, 710);
        GUI_TimKiemQuanAo timKiemQuanAo = new GUI_TimKiemQuanAo();
        timKiemQuanAo.setBounds(0, 0, 1308, 710);
     
        GUI_CapNhatQuanAo capNhatQuanAo = new GUI_CapNhatQuanAo(timKiemQuanAo);
        capNhatQuanAo.setBounds(0, 0, 1308, 710);
        GUI_KhuyenMai khuyenMai = new GUI_KhuyenMai(capNhatQuanAo);
        khuyenMai.setBounds(0, 0, 1308, 710);
        GUI_DatHang banHang = new GUI_DatHang(capNhatQuanAo, khuyenMai, capNhatKhachHang);
        banHang.setBounds(0, 0, 1308, 710);
       
        
        GUI_TimKiemNhanVien timKiemNhanVien = new GUI_TimKiemNhanVien();
        timKiemNhanVien.setBounds(0, 0, 1308, 710);
        GUI_TimKiemKhachHang timKiemKhachHang = new GUI_TimKiemKhachHang();
        timKiemKhachHang.setBounds(0, 0, 1308, 710);

        GUI_ThongKeDoanhThu thongKeDoanhThu = new GUI_ThongKeDoanhThu();
        thongKeDoanhThu.setBounds(0, 0, 1308, 710);
        
        GUI_ThongKeSanPhamBanChay thongKeSPBanChay = new GUI_ThongKeSanPhamBanChay();
        thongKeSPBanChay.setBounds(0, 0, 1308, 710);
        GUI_ThongKeKhachHangMuaNhieu thongKeKHMuaNhieu = new GUI_ThongKeKhachHangMuaNhieu();
        thongKeKHMuaNhieu.setBounds(0, 0, 1308, 710);
        GUI_ThongKeCuoiNgay thongKeCuoiNgay=  new GUI_ThongKeCuoiNgay();
        thongKeCuoiNgay.setBounds(0, 0, 1308, 710);
        
        GUI_TimKiemNhaCungCap timKiemNhaCungCap = new GUI_TimKiemNhaCungCap();
        timKiemNhaCungCap.setBounds(0, 0, 1308, 710);
        GUI_DatHangTruoc datHangTruoc = new GUI_DatHangTruoc();
        datHangTruoc.setBounds(0, 0, 1308, 710);
        GUI_LoaiQuanAo loaiQuanAo = new GUI_LoaiQuanAo(capNhatQuanAo, banHang);
        loaiQuanAo.setBounds(0, 0, 1308, 710);;
        GUI_CapNhatNhaCungCap nhaCungCap= new GUI_CapNhatNhaCungCap(capNhatQuanAo, banHang, timKiemNhaCungCap, timKiemQuanAo);
        nhaCungCap.setBounds(0, 0, 1308, 710);
        
        GUI_LichSuMuaHang lsMuaHang = new GUI_LichSuMuaHang();
        lsMuaHang.setBounds(0, 0, 1308, 710);
        
    
        
        khuyenMai.setVisible(false);
        lsMuaHang.setVisible(false);
        capNhatQuanAo.setVisible(false);
        capNhatNhanVien.setVisible(false);
        timKiemQuanAo.setVisible(false);
        banHang.setVisible(false);
        nhaCungCap.setVisible(false);
        capNhatKhachHang.setVisible(false);
        timKiemNhanVien.setVisible(false);
        timKiemKhachHang.setVisible(false);
        thongKeDoanhThu.setVisible(false);
        thongKeKHMuaNhieu.setVisible(false);
        thongKeSPBanChay.setVisible(false);
        thongKeCuoiNgay.setVisible(false);
        timKiemNhaCungCap.setVisible(false);
        datHangTruoc.setVisible(false);
        loaiQuanAo.setVisible(false);
        
        
        //Tạo panel chứa các thông tin
        
        pnlThongTin = new JPanel();
        pnlThongTin.setBackground(Color.BLACK);
        getContentPane().add(pnlThongTin, BorderLayout.CENTER);
        pnlThongTin.setLayout(null);
        
        JLabel lblHinhNen = new JLabel("");
        lblHinhNen.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/img_TrangChu(1).jpg")));
        lblHinhNen.setBounds(0, 0, 1300, 740);
      
        
        //Panel tiêu đề
        pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(0, 0, 160));
        pnlTieuDe.setPreferredSize(new Dimension(561, 80));
	    pnlTieuDe.setLayout(null);
        
	    //Tạo tên Tiêu đề
        lblTieuDe = new JLabel("CỬA HÀNG QUẦN ÁO THỜI TRANG");
        lblTieuDe.setForeground(Color.GREEN);
        lblTieuDe.setBackground(new Color(0, 0, 0));
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 36));
        lblTieuDe.setBounds(500, 10, 700, 50);
        pnlTieuDe.add(lblTieuDe);
        getContentPane().add(pnlTieuDe, BorderLayout.NORTH);
        
        //Panel Menu bên trái
        pnlMenu = new JPanel();
        pnlMenu.setBackground(new Color(0, 0, 64));
        pnlMenu.setPreferredSize(new Dimension(250, 384));
        
       
        
        getContentPane().add(pnlMenu, BorderLayout.WEST);
        pnlMenu.setLayout(null);
        
        
        //Tên nhóm
        JLabel lblTenNhom = new JLabel("AM");
    	pnlThongTin.add(lblHinhNen);
		lblHinhNen.setVisible(true);
        lblTenNhom.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		pnlThongTin.removeAll();
        		pnlThongTin.add(lblHinhNen);
        		lblHinhNen.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
        	}
        });
        lblTenNhom.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
        lblTenNhom.setForeground(new Color(0, 255, 0));
        lblTenNhom.setBounds(83, 21, 66, 37);
        pnlMenu.add(lblTenNhom);
        
        
        //Panel cho MenuBar Nhân viên     
        pnlNhanVien = new JPanel();
        pnlNhanVien.setBounds(0, 110, 250, 37);
        pnlMenu.add(pnlNhanVien);
        pnlNhanVien.setLayout(null);
        
        //MenuBar Nhân viên
        mnuNhanVien = new JMenuBar();
        mnuNhanVien.setBounds(0, 0, 250, 37);
        pnlNhanVien.add(mnuNhanVien);
        
        //Menu Nhân viên
        mnNhanVien = new JMenu("Nhân Viên");
        mnNhanVien.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_nhanvien1.png")));
        mnNhanVien.setMargin(new Insets(2, 70, 2, 70));
        mnNhanVien.setFont(new Font("Arial",Font.BOLD,24));
        mnuNhanVien.add(mnNhanVien);
        
        //MenuItem Bán hàng
        mniBanHang = new JMenuItem("Đặt Hàng");
        mniBanHang.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_BanHang.png")));
        mniBanHang.setFont(new Font("Arial",Font.PLAIN , 20));
        mniBanHang.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		pnlThongTin.removeAll();
        		pnlThongTin.add(banHang);
        		banHang.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
        	}
        });
        mnNhanVien.add(mniBanHang);
        
            
      //MenuItem Cập nhật nhân viên
	    mniCapNhatNhanVien = new JMenuItem("Cập nhật");
	    mniCapNhatNhanVien.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_CapNhat.png")));
	    mniCapNhatNhanVien.setFont(new Font("Arial",Font.PLAIN , 20));
		
		mniCapNhatNhanVien.addActionListener(new ActionListener() { 
			public void	actionPerformed(ActionEvent e) {
				pnlThongTin.removeAll();
        		pnlThongTin.add(capNhatNhanVien);
        		capNhatNhanVien.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
		} });
		 
	    mnNhanVien.add(mniCapNhatNhanVien);
	    
	    //MenuItem Tìm nhân viên
	    mniTimNhanVien = new JMenuItem("Tìm kiếm");
	    mniTimNhanVien.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(timKiemNhanVien);
        		timKiemNhanVien.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    		
	    	}
	    });
	    mniTimNhanVien.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_TimKiem_nho.png")));
	    mniTimNhanVien.setFont(new Font("Arial", Font.PLAIN, 20));
	    mnNhanVien.add(mniTimNhanVien);
	    
	    //Panel Nhà cung cấp
	    JPanel pnlNhaCungCap = new JPanel();
	    pnlNhaCungCap.setLayout(null);
	    pnlNhaCungCap.setBounds(0, 190, 250, 37);
	    pnlMenu.add(pnlNhaCungCap);
	    
	    JMenuBar mnuNhaCungCap = new JMenuBar();
	    mnuNhaCungCap.setBounds(0, 0, 250, 37);
	    pnlNhaCungCap.add(mnuNhaCungCap);
	    
	    JMenu mnNhaCungCap = new JMenu("Nhà cung cấp");
	    mnNhaCungCap.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_NhaCC.png")));
	     mnNhaCungCap.setFont(new Font("Arial", Font.BOLD, 24));
	    mnuNhaCungCap.add(mnNhaCungCap);
	    
	    JMenuItem mniCapNhatNhaCC = new JMenuItem("Cập nhật");
	    mniCapNhatNhaCC.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_CapNhat.png")));
	    mniCapNhatNhaCC.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(nhaCungCap);
        		nhaCungCap.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniCapNhatNhaCC.setFont(new Font("Arial", Font.PLAIN, 20));
	    mnNhaCungCap.add(mniCapNhatNhaCC);
	    
	    mniTimKiemNhaCungCap = new JMenuItem("Tìm kiếm");
	    mniTimKiemNhaCungCap.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(timKiemNhaCungCap);
        		timKiemNhaCungCap.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniTimKiemNhaCungCap.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_TimKiem_nho.png")));
	    mniTimKiemNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 20));
	    mnNhaCungCap.add(mniTimKiemNhaCungCap);
	    
	    //Panel Khách hàng
	    pnlKhachHang = new JPanel();
	    pnlKhachHang.setLayout(null);
	    pnlKhachHang.setBounds(0, 270, 250, 37);
	    pnlMenu.add(pnlKhachHang);
	    
	    mnuKhachHang = new JMenuBar();
	    mnuKhachHang.setFont(new Font("Arial", Font.BOLD, 24));
	    mnuKhachHang.setBounds(0, 0, 250, 37);
	    pnlKhachHang.add(mnuKhachHang);
	    
	    mnKhachHang = new JMenu("Khách hàng");
	    mnKhachHang.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_KhachHang.png")));
	    mnKhachHang.setFont(new Font("Arial", Font.BOLD, 24));
	    mnuKhachHang.add(mnKhachHang);
	    
	    JMenuItem mniCapNhatKhachHang = new JMenuItem("Cập nhật");
	    mniCapNhatKhachHang.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_CapNhat.png")));
	    mniCapNhatKhachHang.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(capNhatKhachHang);
        		capNhatKhachHang.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniCapNhatKhachHang.setFont(new Font("Arial", Font.PLAIN, 20));
	    mnKhachHang.add(mniCapNhatKhachHang);
	    
	    mniTimKiemKhachHang = new JMenuItem("Tìm kiếm");
	    mniTimKiemKhachHang.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(timKiemKhachHang);
        		timKiemKhachHang.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniTimKiemKhachHang.setFont(new Font("Arial", Font.PLAIN, 20));
	    mniTimKiemKhachHang.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_TimKiem_nho.png")));
	    mnKhachHang.add(mniTimKiemKhachHang);
	    
	    mniDatHangTruoc = new JMenuItem("Đặt hàng trước");
	    mniDatHangTruoc.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(datHangTruoc);
        		datHangTruoc.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniDatHangTruoc.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_DatHangTruoc.png")));
	    mniDatHangTruoc.setFont(new Font("Arial", Font.PLAIN, 20));
	    mnKhachHang.add(mniDatHangTruoc);
	    
	    mniLichSuMuaHang = new JMenuItem("Lịch sử mua hàng");
	    mniLichSuMuaHang.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(lsMuaHang);
        		lsMuaHang.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniLichSuMuaHang.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_LSMuaHang.png")));
	    mniLichSuMuaHang.setFont(new Font("Arial", Font.PLAIN, 20));
	    mnKhachHang.add(mniLichSuMuaHang);
	    
	    
	    //Panel Quần áo
	    pnlQuanAo = new JPanel();
	    pnlQuanAo.setLayout(null);
	    pnlQuanAo.setBounds(0, 350, 250, 37);
	    pnlMenu.add(pnlQuanAo);
	    
	    mnuQuanAo = new JMenuBar();
	    mnuQuanAo.setFont(new Font("Arial", Font.BOLD, 24));
	    mnuQuanAo.setBounds(0, 0, 250, 37);
	    pnlQuanAo.add(mnuQuanAo);
	    
	    mnQuanAo = new JMenu("Quần áo");
	    mnQuanAo.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_QuanAo.png")));
	    mnQuanAo.setFont(new Font("Arial", Font.BOLD, 24));
	    mnuQuanAo.add(mnQuanAo);
	    
	    JMenuItem mniCapNhatQuanAo = new JMenuItem("Cập nhật");
	    mniCapNhatQuanAo.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_CapNhat.png")));
	    mniCapNhatQuanAo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(capNhatQuanAo);
        		capNhatQuanAo.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniCapNhatQuanAo.setFont(new Font("Arial", Font.PLAIN, 20));
	    mnQuanAo.add(mniCapNhatQuanAo);
	    
	    mniTimKiemQuanAo = new JMenuItem("Tìm kiếm ");
	    mniTimKiemQuanAo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(timKiemQuanAo);
        		timKiemQuanAo.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniTimKiemQuanAo.setFont(new Font("Arial", Font.PLAIN, 20));
	    mniTimKiemQuanAo.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_TimKiem_nho.png")));
	    mnQuanAo.add(mniTimKiemQuanAo);
	    
	    mniLoaiQuanAo = new JMenuItem("Loại quần áo");
	    mniLoaiQuanAo.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_LoaiQuanAo.png")));
	    mniLoaiQuanAo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(loaiQuanAo);
        		loaiQuanAo.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniLoaiQuanAo.setFont(new Font("Arial", Font.PLAIN, 20));
	    mnQuanAo.add(mniLoaiQuanAo);
	    
	    mniKhuyenMai = new JMenuItem("Khuyến mãi");
	    mniKhuyenMai.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_KhuyenMai.png")));
	    mniKhuyenMai.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(khuyenMai);
        		khuyenMai.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mniKhuyenMai.setFont(new Font("Arial", Font.PLAIN, 20));
	    mnQuanAo.add(mniKhuyenMai);
	    
	    
	    //Panel Thống kê
	    pnlThongKe = new JPanel();
	    pnlThongKe.setLayout(null);
	    pnlThongKe.setBounds(0, 430, 250, 37);
	    pnlMenu.add(pnlThongKe);
	    
	    mnuThongKe = new JMenuBar();
	    mnuThongKe.setFont(new Font("Arial", Font.BOLD, 24));
	    mnuThongKe.setBounds(0, 0, 250, 37);
	    pnlThongKe.add(mnuThongKe);
	    
	    mnThongKe = new JMenu("Thống kê");
	    mnThongKe.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_ThongKe.png")));
	    mnThongKe.setFont(new Font("Arial", Font.BOLD, 24));
	    mnuThongKe.add(mnThongKe);
	    
	    mniDoanhThu = new JMenuItem("Doanh thu");
	    mniDoanhThu.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_DoanhThu.png")));
	    mniDoanhThu.setFont(new Font("Arial", Font.PLAIN, 20));
	    mniDoanhThu.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(thongKeDoanhThu);
        		thongKeDoanhThu.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mnThongKe.add(mniDoanhThu);
	    
	    mniSanPham = new JMenuItem("Sản phẩm bán chạy");
	    mniSanPham.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_DoanhThu.png")));
	    mniSanPham.setFont(new Font("Arial", Font.PLAIN, 20));
	    mniSanPham.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(thongKeSPBanChay);
        		thongKeSPBanChay.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });

	    mnThongKe.add(mniSanPham);
	    
	    mniKhachHang = new JMenuItem("Khách hàng mua nhiều nhất");
	    mniKhachHang.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_DoanhThu.png")));
	    mniKhachHang.setFont(new Font("Arial", Font.PLAIN, 20));
	    mniKhachHang.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(thongKeKHMuaNhieu);
        		thongKeKHMuaNhieu.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mnThongKe.add(mniKhachHang);
	    
	    mniCuoiNgay = new JMenuItem("Cuối ngày");
	    mniCuoiNgay.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_DoanhThu.png")));
	    mniCuoiNgay.setFont(new Font("Arial", Font.PLAIN, 20));
	    mniCuoiNgay.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pnlThongTin.removeAll();
        		pnlThongTin.add(thongKeCuoiNgay);
        		thongKeCuoiNgay.setVisible(true);
            	pnlThongTin.repaint();
            	pnlThongTin.revalidate();
	    	}
	    });
	    mnThongKe.add(mniCuoiNgay);
	    
	    JButton btnDangXuat = new JButton("Đăng Xuất");
	    btnDangXuat.setIcon(new ImageIcon(GUI_Tong.class.getResource("/Image/icon_exit.png")));
	    btnDangXuat.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		int n=JOptionPane.showConfirmDialog(null, "Chắc chắn đăng xuất ?","Thông báo ",JOptionPane.YES_NO_OPTION);
	    		if(n == JOptionPane.YES_OPTION) {
		    		dispose();	
		    		GUI_DangNhap dangNhap = new GUI_DangNhap(entityManager);
		    		dangNhap.setVisible(true);
	    		}
	    		
	    		}
	    });
	    btnDangXuat.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
	    btnDangXuat.setBounds(0, 670, 250, 37);
	    pnlMenu.add(btnDangXuat);
	    
	    lblTenNhanVien = new JLabel("");
	    lblTenNhanVien.setForeground(new Color(128, 255, 0));
	    lblTenNhanVien.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
	    lblTenNhanVien.setBounds(23, 600, 217, 36);
	    lblTenNhanVien.setText(layTenNhanVien());
	    pnlMenu.add(lblTenNhanVien);
	    
	    lblThoiGian = new JLabel("");
	    lblThoiGian.setForeground(new Color(255, 0, 0));
	    lblThoiGian.setFont(new Font("Arial", Font.BOLD, 20));
	    lblThoiGian.setBounds(70, 57, 124, 20);
	    pnlMenu.add(lblThoiGian);
	    capNhatThoiGian();
    }
  //Hàm lấy tên nhân viên
  		public String layTenNhanVien() {
  			EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
  		    EntityManager entityManager = util.getEnManager();
  			DAO_NhanVien dao = new DAO_NhanVien(entityManager);
  			List<NhanVien> list = dao.timTheoMa(GUI_DangNhap.txtTenDangNhap.getText());
  			for (NhanVien nhanVien : list) {
  					tenNhanVien= nhanVien.getTenNV();
  				}
  				return tenNhanVien;
  			}
  	// Thời gian
  		private void capNhatThoiGian() {
  		    Thread thread = new Thread(new Runnable() {
  		        @Override
  		        public void run() {
  		            try {
  		                while (true) {
  		                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
  		                    String thoiGian = dateFormat.format(new Date());
  		                    lblThoiGian.setText(thoiGian);
  		                    Thread.sleep(1000); // Cập nhật mỗi giây
  		                }
  		            } catch (InterruptedException e) {
  		                e.printStackTrace();
  		            }
  		        }
  		    });

  		    thread.start();
  		}


        
    public static void main(String args[]) {
            
                new GUI_Tong().setVisible(true);
            }
}
