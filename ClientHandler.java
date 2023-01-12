package HomeWorkWeek1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler {
    private Socket sConn;
    private Path dirPath;
    //Constructor
    public ClientHandler(Socket sConn, Path dirPath){
        this.sConn = sConn;
        this.dirPath = dirPath;
    }
    //Methods
    public void run() throws IOException{
        while (true){
            //Output and input stream
            OutputStream os = this.sConn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = this.sConn.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            List<String> tempCart = new ArrayList<>();
            String cartOwner = "";
            boolean stop = false;
            String reply = "";
            while (!stop){
                String input = dis.readUTF();
                String[] inputStream = input.split(" ");
                //Exit button
                if (input.equals("exit")){
                    System.out.println("exit");
                    stop = true;
                    break;
                }
                //Load user first
                if (inputStream[0].equals("load")) {
                    cartOwner = inputStream[1];
                    File file = new File(String.format("./%s/%s.cart.txt",dirPath,cartOwner));
                    if (file.createNewFile()){
                        reply = String.format("New cart file is created for %s.\n",cartOwner);
                    } else {
                        reply = String.format("%s's shopping cart is ready.\n",cartOwner);
                    }
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferReader = new BufferedReader(fileReader);
                    //Re-initialized temporary cart
                    tempCart = new ArrayList<String>();
                    String line;
                    while (null != (line = bufferReader.readLine())){
                        tempCart.add(line.split(" ")[1]);
                    }  
                }
                //Users button to list out all users
                if (input.equals("users")){
                    File directory = dirPath.toFile();
                    reply = "List of users:\n";
                    for (File file: directory.listFiles()) {
                        reply += String.format("%s (%d)\n", file.getName().substring(0,file.getName().length()-9), file.length());
                    }
                } else if (cartOwner.equals("")){
                    reply = "Please load a user first.\n";
                } else {
                    //Below options are only allowed after loading a user
                    switch (inputStream[0]){
                        case "load":
                            break;
                        case "list":
                            if (tempCart.size()<1){
                                reply = "Cart is empty.\n";
                            } else {
                                reply = "";
                                for (int i=0;i<tempCart.size();i++){
                                    reply += (String.format("%d. %s\n",i+1,tempCart.get(i)));
                                }
                            }
                            break;
                        case "add":
                            String itemAdded = "";
                            for (int i=1;i<inputStream.length;i++){
                                tempCart.add(inputStream[i]);
                                if (i==inputStream.length-1){
                                    itemAdded += inputStream[i];
                                } else {
                                    itemAdded += inputStream[i]+", ";
                                }
                            }
                            reply = String.format("%s added to the cart.\n",itemAdded);
                            break;
                        case ("delete"):
                            if (tempCart.size()<1){
                                reply = "Cart is empty, nothing to remove.\n";
                            } else if (inputStream.length>2 || Integer.parseInt(inputStream[1])>tempCart.size() || Integer.parseInt(inputStream[1])<1){
                                reply = "Invalid input.\n";
                            } else {
                                reply = String.format("%s removed from the cart.\n",tempCart.get(Integer.parseInt(inputStream[1])-1));
                                tempCart.remove(Integer.parseInt(inputStream[1])-1);
                            }
                            break;
                        case "save":
                            //File savedFile = new File(String.format("./shoppingcart/%s.cart.txt",cartOwner));
                            FileWriter fileWriter = new FileWriter(String.format("./%s/%s.cart.txt",dirPath,cartOwner));
                            for (int i=0;i<tempCart.size();i++){
                                fileWriter.write(Integer.toString(i+1)+". "+tempCart.get(i));
                                fileWriter.write("\n");
                            }
                            fileWriter.close();
                            reply = String.format("%s's shopping cart has been saved.\n",cartOwner);
                            break;
                        default:
                            reply = "Invalid command.\n";
                    }
                }
                dos.writeUTF(reply);
                dos.flush();
            }
            System.out.println("Terminating client connection");
            try {
                this.sConn.close();
            } catch (IOException ex){ }
        }
    }
}
