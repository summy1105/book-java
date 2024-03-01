package standard.ch16networking.tcpip;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpIpClient5 {
    public static void main(String[] args) {
        try{
            String serverIp = "127.0.0.1";
            Socket socket = new Socket(serverIp, 61015);

            System.out.println("connecting server");
            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
