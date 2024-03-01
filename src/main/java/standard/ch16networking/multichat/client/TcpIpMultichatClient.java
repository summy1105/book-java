package standard.ch16networking.multichat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;


public class TcpIpMultichatClient {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("USAGE: the name of TcpIpMultichatClient");
            System.exit(0);
        }

        try {
            String name = args[0];
            String serverIp = "127.0.0.1";

            Socket socket = new Socket(serverIp, 62020);
            System.out.println("Connected to server");
            Thread sender = new ClientSender(socket, name);
            Thread receiver = new ClientReceiver(socket);

            sender.start();
            receiver.start();
        } catch (ConnectException connectException) {
            connectException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class ClientSender extends Thread {
    Socket socket;
    DataOutputStream outputStream;
    String name;

    ClientSender(Socket socket, String name){
        this.socket = socket;
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
            this.name = name;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        try {
            if (outputStream != null) {
                outputStream.writeUTF(name);
            }
            while (outputStream != null) {
                outputStream.writeUTF("[" + name + "]:" + scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientReceiver extends Thread {
    Socket socket;
    DataInputStream inputStream;

    ClientReceiver(Socket socket) {
        this.socket = socket;
        try {
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while (inputStream != null) {
            try {
                System.out.println(inputStream.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
