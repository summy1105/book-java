package standard.ch16networking.tcpip;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // 서버소켓을 생성하여 61010번 포트와 결합(bind) 시킴
            serverSocket = new ServerSocket(61010);
            System.out.println(getTime() + "- server is ready.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                System.out.println(getTime() + "- waiting to access");

                Socket socket = serverSocket.accept();
                System.out.println(getTime() + "- request from" + socket.getInetAddress());

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                dataOutputStream.writeUTF("[notice] The Message from server");
                System.out.println(getTime() + "- sent data");

                dataOutputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static String getTime(){
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    }
}
