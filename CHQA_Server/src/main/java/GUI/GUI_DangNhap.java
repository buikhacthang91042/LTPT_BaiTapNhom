package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import DAO.DAO_DangNhap;
import DAO.EntityManagerFactoryUtil;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import GUI.GUI_QuenMatKhau;

public class GUI_DangNhap extends JFrame {
    JPanel contentPane;
    static JTextField txtTenDangNhap;
    JPasswordField txtMatKhau;
    EntityManager entityManager;
    GUI_QuenMatKhau quenMatKhau;
    public GUI_DangNhap(EntityManager entityManager) {
        this.entityManager = entityManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("CỬA HÀNG QUẦN ÁO THỜI TRANG");
        setBounds(100, 100, 990, 571);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tạo panel chứa các ô để nhập thông tin
        JPanel PDangNhap = new JPanel();
        PDangNhap.setBackground(new Color(211, 211, 211));
        PDangNhap.setBounds(226, 115, 472, 300);
        PDangNhap.setLayout(null);
        contentPane.add(PDangNhap);

        JLabel lbTenCH = new JLabel("CỬA HÀNG AM");
        lbTenCH.setBounds(113, 10, 255, 36);
        JLabel lbDangNhap = new JLabel("Đăng nhập");
        lbDangNhap.setBounds(180, 40, 95, 30);
        lbTenCH.setFont(new Font("Arial", Font.BOLD, 30));
        lbDangNhap.setFont(new Font("Arial", Font.BOLD, 15));
        lbTenCH.setForeground(new Color(0, 255, 100));

        PDangNhap.add(lbTenCH);
        PDangNhap.add(lbDangNhap);
      //Hình nền
      		JLabel lbHinhNen = new JLabel();
      		lbHinhNen.setBounds(0, 0, 976, 534);
      		lbHinhNen.setIcon(new ImageIcon(GUI_DangNhap.class.getResource("/Image/img_DangNhap.png")));
      		contentPane.add(lbHinhNen);

        // Tên đăng nhập
        txtTenDangNhap = new JTextField(40);
        txtTenDangNhap.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTenDangNhap.setBounds(30, 100, 410, 25);
        PDangNhap.add(txtTenDangNhap);

       
      //Đường kẻ 1
      		Canvas canvas1 = new Canvas();
      		canvas1.setBackground(new Color(130, 117, 123));
      		canvas1.setBounds(30, 128, 410, 2);
      		PDangNhap.add(canvas1);
        // Mật khẩu
        txtMatKhau = new JPasswordField(40);
        txtMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        txtMatKhau.setBounds(30, 150, 410, 25);
        PDangNhap.add(txtMatKhau);
      //Đường kẻ 2
      		Canvas canvas2 = new Canvas();
      		canvas2.setBackground(new Color(130, 117, 123));
      		canvas2.setBounds(30, 178, 410, 2);
      		PDangNhap.add(canvas2);
      	//PlaceHolder Tên đăng nhập
    		txtTenDangNhap.addFocusListener(new FocusAdapter() {
    			@Override
    			public void focusGained(FocusEvent e) {
    				if(txtTenDangNhap.getText().equals("Tên đăng nhập")) {
    					txtTenDangNhap.setText("");
    					txtTenDangNhap.setForeground(new Color(153,153,153));
    				}
    			}
    			@Override
    			public void focusLost(FocusEvent e) {
    				if(txtTenDangNhap.getText().equals("")) {
    					txtTenDangNhap.setText("Tên đăng nhập");
    					txtTenDangNhap.setForeground(new Color(130, 117, 123));
    					txtTenDangNhap.setFont(new Font("Serial", Font.ITALIC, 20));
    				}
    			}
    		});
    		
    		//PlaceHolder Mật khẩu
    		txtMatKhau.addFocusListener(new FocusAdapter() {
    			@Override
    			public void focusGained(FocusEvent e) {
    				if(txtMatKhau.getText().equals("Mật khẩu")) {
    					txtMatKhau.setText("");
    					txtMatKhau.setForeground(new Color(153,153,153));
    					txtMatKhau.setEchoChar('*');
    				}
    			}
    			@Override
    			public void focusLost(FocusEvent e) {
    				if(txtMatKhau.getText().length()==0) {
    					txtMatKhau.setEchoChar((char)0);
    					txtMatKhau.setText("Mật khẩu");
    					txtMatKhau.setForeground(new Color(130, 117, 123));
    					txtMatKhau.setFont(new Font("Serial", Font.ITALIC, 20));
    				}
    			}
    		});

        // Quên mật khẩu
    		JLabel lbQuenMatKhau = new JLabel("Quên mật khẩu ?");
    		lbQuenMatKhau.setFont(new Font("Arial", Font.PLAIN, 10));
    		lbQuenMatKhau.setForeground(new Color(0, 0, 255));
    		lbQuenMatKhau.setBounds(30, 190, 95, 20);
    		PDangNhap.add(lbQuenMatKhau);
    		
    		
    		lbQuenMatKhau.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseEntered(MouseEvent e) {
    				Border label_border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.blue);
    				lbQuenMatKhau.setBorder(label_border);
    			}
    			@Override
    			public void mouseExited(MouseEvent e) {
    				Border label_border = BorderFactory.createMatteBorder(0, 0, 0,0,new Color(211, 211, 211));
    				lbQuenMatKhau.setBorder(label_border);
    			}
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				// TODO Auto-generated method stub
    				quenMatKhau = new GUI_QuenMatKhau();
    				quenMatKhau.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    				quenMatKhau.setVisible(true);
    			}
    			
    		});

        // Các nút chức năng
        JButton btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDangNhapActionPerformed(e);
            }
        });
        btnDangNhap.setForeground(Color.white);
        btnDangNhap.setBackground(new Color(83, 83, 255));
        btnDangNhap.setIcon(new ImageIcon(GUI_DangNhap.class.getResource("/Image/icon_QuanLi.png")));
        btnDangNhap.setFont(new Font("Arial", Font.BOLD, 20));
        btnDangNhap.setBounds(0, 248, 236, 52);
        PDangNhap.add(btnDangNhap);

        // Đăng kí

        JButton btnDangKi = new JButton("Đăng kí");
        btnDangKi.setForeground(Color.white);
        btnDangKi.setBackground(new Color(0, 255, 64));
        btnDangKi.setIcon(new ImageIcon(GUI_DangNhap.class.getResource("/Image/icon_NhanVien.png")));
        btnDangKi.setFont(new Font("Arial", Font.BOLD, 20));
        btnDangKi.setBounds(234, 248, 238, 52);
        PDangNhap.add(btnDangKi);

        txtTenDangNhap.setText("NV001");
        txtMatKhau.setText("NV01");

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void btnDangNhapActionPerformed(ActionEvent e) {
    	
    	   String tenDangNhap = txtTenDangNhap.getText();
    	    String matKhau = new String(txtMatKhau.getPassword());
    	    if (!entityManager.isOpen()) {
    	        // EntityManager đã đóng, xử lý tương ứng
    	        return;
    	    }
    	    if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
    	        JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập và mật khẩu");
    	        return;
    	    }

    	    // Tạo một đối tượng DAO_DangNhap
    	    DAO_DangNhap daoDangNhap = new DAO_DangNhap(entityManager);
    	    System.out.println("btnDangNhapActionPerformed called");
    	    // Gọi phương thức dangNhap từ đối tượng daoDangNhap
    	    List<TaiKhoan> list = daoDangNhap.dangNhap(tenDangNhap, matKhau);
    	    System.out.println(list);
    	    if (!list.isEmpty()) {
    	        JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
    	        
    	        dispose(); // Đóng cửa sổ đăng nhập
    	        SwingUtilities.invokeLater(() -> {
    	            GUI_Tong m = new GUI_Tong(); 
    	            m.setVisible(true);
    	        });
    	    } else {
    	        JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu");
    	    }
    }

    public static void main(String[] args) {
        // Khởi tạo EntityManager từ EntityManagerFactoryUtil	
        EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
        EntityManager entityManager = util.getEnManager();

        // Khởi tạo GUI_DangNhap với EntityManager đã tạo
        GUI_DangNhap dn = new GUI_DangNhap(entityManager);
        dn.setVisible(true);
    }
}
