package standard.ch16networking.tcpip;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
    Socket socket;
    DataInputStream dIn;

    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            dIn = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (dIn != null) {
            try {
                System.out.println(dIn.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
