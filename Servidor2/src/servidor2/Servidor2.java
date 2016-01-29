/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor2;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Thread;

public class Servidor2 {

    private static Simulador sim;
    
    public static void main(String[] args) throws IOException {
        
        sim=new Simulador();
        sim.start();
        
        int portNumber = 8002;
        int i,j=1;
        
        try        
        {
            ServerSocket serverSocket = new ServerSocket(8002);
            ArrayList<Socket> clientS=new ArrayList<Socket> ();
            ArrayList<Thread> threads=new ArrayList<Thread> ();
            
            for(i=0;i<j;i++){
                Socket clientSocket = serverSocket.accept();
                clientS.add(clientSocket);
            
                Thread t1 = new Thread(new Mithread(clientSocket, sim));
                threads.add(t1);
                t1.start();
                j++;
                
                //Enrutamiento
                System.out.println(t1.toString());
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
   
}
