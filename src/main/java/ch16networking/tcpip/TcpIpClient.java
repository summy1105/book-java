package ch16networking.tcpip;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;

public class TcpIpClient {
    public static void main(String[] args) {
        String serverIp = "127.0.0.1";

        while(true) {
            try {
                System.out.println("Connecting to server. server ip : " + serverIp);

                Socket socket1 = new Socket(serverIp, 61014);

                InputStream inputStream = socket1.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                System.out.println("message form server : " + dataInputStream.readUTF());
                System.out.println("Connection is closing.");


                dataInputStream.close();
                socket1.close();
                System.out.println("Connection is closed.");

//                Thread.sleep(3000);
            } catch ( IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
