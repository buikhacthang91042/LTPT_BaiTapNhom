package DAO;

import java.text.NumberFormat;
import java.text.ParseException;

public class DAO_ChuyenDoi {
	public static String DinhDangTien(float tien) {
        return NumberFormat.getNumberInstance().format(tien);
    }
	public static String DinhDangTien(double tien) {
        return NumberFormat.getNumberInstance().format(tien);
    }
	
    public static float ChuyenTien(String tien) {
        try {
            return NumberFormat.getNumberInstance().parse(tien).floatValue();
        } catch (ParseException ex) {


        }
        return 0;
    }

}
