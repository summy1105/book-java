package ch16networking.tcpip;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static ch16networking.tcpip.TcpIpServer.*;

public class TcpIpServer2 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(61011);
            System.out.println(getTime()+"- Server is ready.");

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                System.out.println(getTime() + "- waiting for access");
                Socket socket = serverSocket.accept();
                System.out.println(getTime() + "request from " + socket.getInetAddress());

                System.out.println("port : " + socket.getPort());
                System.out.println("getLocalPort : " + socket.getLocalPort());

                OutputStream out = socket.getOutputStream();
                DataOutputStream dOut = new DataOutputStream(out);

                dOut.writeUTF("[Notice] The message is from server2");
                System.out.println("sent data");

                dOut.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
