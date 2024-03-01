package standard.ch16networking.multichat.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class TcpIpMultichatServer {
    HashMap clients;

    TcpIpMultichatServer(){
        clients = new HashMap();
        Collections.synchronizedMap(clients);
    }

    public void start(){
        ServerSocket serverSocket =null;

        try {
            serverSocket = new ServerSocket(62020);
            System.out.println("server is running");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected with [" + socket.getInetAddress() + ":" + socket.getPort() + "]");

                ServerReceiver serverReceiverThread = new ServerReceiver(socket, this);
                serverReceiverThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String msg) {
        Iterator it = clients.keySet().iterator();

        while (it.hasNext()) {
            try {
                DataOutputStream out = (DataOutputStream) clients.get(it.next());
                out.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void joinClients(String name, DataOutputStream outputStream) {
        clients.put(name, outputStream);
        System.out.println("The number of users is " + clients.size());
    }

    public void disconnectClients(String name) {
        clients.remove(name);
    }

    public static void main(String[] args) {
        new TcpIpMultichatServer().start();
    }
}


