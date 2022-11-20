package ch16networking.tcpip;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import static src.main.ch16networking.tcpip.TcpIpServer.getTime;

public class TcpIpServer3 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(61012);
            System.out.println("server is ready");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                System.out.println("waiting for access");
                serverSocket.setSoTimeout(15 * 1000);

                Socket socket = serverSocket.accept();
                System.out.println(getTime() + "- request from " + socket.getInetAddress());

                OutputStream out = socket.getOutputStream();
                DataOutputStream dOut = new DataOutputStream(out);

                dOut.writeUTF("[Notice] The Message from server3");
                System.out.println("sent data");

                dOut.close();
                socket.close();
            } catch (SocketTimeoutException e){
                e.printStackTrace();
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
