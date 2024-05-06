package App;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
        final String serverAddress = "192.168.232.1"; // Địa chỉ IP của server
        final int serverPort = 9999; // Cổng của server
        
        try (
            Socket socket = new Socket(serverAddress, serverPort);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            System.out.println("Connected to server: " + serverAddress + ":" + serverPort);
            
            // Gửi yêu cầu mở GUI_DangNhap cho server
            out.writeUTF("DangNhap");
            out.flush();
            
            // Đọc phản hồi từ server
            String response = in.readUTF();
            System.out.println("Server response: " + response);
            
            // Xử lý phản hồi nếu cần
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
