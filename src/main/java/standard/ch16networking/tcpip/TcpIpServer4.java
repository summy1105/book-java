package standard.ch16networking.tcpip;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static standard.ch16networking.tcpip.TcpIpServer.getTime;

public class TcpIpServer4 implements Runnable {
    ServerSocket serverSocket;
    Thread[] threadArr;

    public TcpIpServer4(int num) {
        try {
            serverSocket = new ServerSocket(61014);
            System.out.println(getTime() + "- server is ready");

            threadArr = new Thread[num];
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        TcpIpServer4 server = new TcpIpServer4(5);
        server.start();
    }

    public void start(){
        for (int i = 0; i < threadArr.length; i++) {
            threadArr[i] = new Thread(this);
            threadArr[i].start();
        }
    }

    public void run(){
        while (true) {
            try {
                System.out.println(getTime() + "- listening");

                Socket socket = serverSocket.accept();
                System.out.println(getTime() + "- request from " + socket.getInetAddress());

                OutputStream out = socket.getOutputStream();
                DataOutputStream dOut = new DataOutputStream(out);

                dOut.writeUTF("[Notice] The message from server4."+Thread.currentThread().getId());
                System.out.println(getTime() + " sent");

                dOut.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
