package standard.ch16networking.multichat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReceiver extends Thread {
    Socket socket;
    DataInputStream inputStream;
    DataOutputStream outputStream;

    TcpIpMultichatServer server;

    public ServerReceiver(Socket socket, TcpIpMultichatServer server) {
        this.socket = socket;
        this.server = server;
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String name = "";

        try {
            name = inputStream.readUTF();
            server.sendToAll("#" + name + " is joined.");
            server.joinClients(name, outputStream);

            while (inputStream != null) { // : inputStream is not closing
                server.sendToAll(inputStream.readUTF());
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            server.sendToAll("#" + name + " is left");
            server.disconnectClients(name);
            System.out.println("["+ socket.getInetAddress()+":"+socket.getPort()+"]");
        }
    }

}
