/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balanceador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author megan
 */
public class Mithread extends Thread{
    Socket clientSocket;
    String inputLine;
    int idThread;
    //probeA probeI = new probeA();
    
    public Mithread(Socket client){
        clientSocket=client;
    }
    
    public void run(){
        PrintWriter out = null;   
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            
            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine); //coordenadas
                
                String hostName = "192.168.1.9";
                int portNumber = 8001;
                try (
                    Socket echoSocket = new Socket(hostName, portNumber);
                    PrintWriter out1 =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                    BufferedReader in1 =
                        new BufferedReader(
                            new InputStreamReader(echoSocket.getInputStream()));
                    BufferedReader stdIn1 =
                        new BufferedReader(
                            new InputStreamReader(System.in))
                ) {
                    String userInput =inputLine;
                    out1.println(userInput);
                    System.out.println(in1.readLine());
                    
                    //userInput=in1.readLine();
                    out.println(in1.readLine());
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to " +
                        hostName);
                    System.exit(1);
                } 
            }
            //System.out.println("echo: " + in.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Mithread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
}
