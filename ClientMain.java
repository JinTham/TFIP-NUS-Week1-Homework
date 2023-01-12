package HomeWorkWeek1;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException{
        String host;
        int port;
        if (args.length<=0){
            host = "127.0.0.1";
            port = 3000; 
        } else if (args.length<=1){
            host = args[0];
            port = 3000;
        } else {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        //Connect to server
        Socket cConn = new Socket(host,port);
        System.out.printf("Connected to server %s on port:%d\n",host,port);
        Console cons = System.console();
        //Output and input stream
        OutputStream os = cConn.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        InputStream is = cConn.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        //Connection while loop
        boolean stop = false;
        while (!stop){
            //Ask for client input
            String input = cons.readLine("Client pls input cmd > ");
            //Send client input to server
            if (input.equals("exit")){
                dos.writeUTF(input);
                dos.flush();
                stop = true;
                break;
            }
            dos.writeUTF(input);
            dos.flush();
            //Receive reply from server
            String reply = dis.readUTF();
            System.out.printf(">>>Reply from server: %s",reply);
        }
        System.out.println("Closing connection");
        try {
            cConn.close();
        } catch (IOException ex){ }
    }
}
