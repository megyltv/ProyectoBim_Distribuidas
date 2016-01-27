
package servidor;

/**
 *
 * @author megan
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Probe.probe;

public class Mithread extends Thread {   
           
    Socket clientSocket;
    String inputLine;
    int idThread;
    probe probeA = new probe();
    private Simulador sim;
    
    public Mithread(Socket client, Simulador sim){
        clientSocket=client;
        this.sim=sim;
    }
    
    public void run(){
        int a=sim.getValor();
        System.out.println(a);
        int portNumber = 8001;
        
        try (
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    
    }
}