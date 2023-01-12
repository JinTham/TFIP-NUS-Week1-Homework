package HomeWorkWeek1;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerMain {
    public static void main(String[] args) throws IOException{
        //Getting directory
        String dir = "shoppingcart";
        if (args.length>0){
            dir = args[0];
        }
        Path dirPath = Paths.get(dir);
        File directory = dirPath.toFile();
        if (!directory.exists()){ //If the directory doesn't exist, create one
            directory.mkdirs();
        }
        int fileCount = directory.list().length;
        System.out.printf("Using %s directory for persistence\n",dirPath);
        System.out.printf("There are %d carts in %s directory\n",fileCount,dirPath);
        //Setup port
        int port = 3000;
        if (args.length>1){
            port = Integer.parseInt(args[1]);
        }
        System.out.printf("Starting server on port %d\n",port);
        ServerSocket server = new ServerSocket(port);
        while (true){
            //Wait for a connection
            System.out.println("Waiting for incoming connection");
            Socket sConn = server.accept();
            System.out.println("Got a connection");
            //Get the input stream from the client
            ClientHandler handler = new ClientHandler(sConn,dirPath);
            handler.run();
            // try {
            //     handler.run();
            // } catch (IOException ex){ 
            //     System.out.println("Error liao lor");
            // }
        }
    }
}
