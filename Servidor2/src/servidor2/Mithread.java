/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor2;

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
    public Distancia dist;
    public PeligroDistancia pd;
    
    public Mithread(Socket client, Simulador sim){
        clientSocket=client;
        this.sim=sim;
    }
    
    public void run(){
        int a=sim.getValor();
        //System.out.println(a);
        int portNumber = 8001;
        
        try (
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine); //coordenadas
                //out.println(inputLine);
                String aviso= monitoreoVolcan(inputLine, a);
                System.out.println(aviso);
                out.println(aviso);
            }
            
            
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    
    }
    
    public String monitoreoVolcan(String input, int valor){
        String result= "";
        int a=4;
        float distancia, lat2, long2;
        float lat1 = (float)-0.6837;
        float long1 = (float) -78.437;
        
        //Split input
        String[] parametros=input.split(",");
        lat2=Float.parseFloat(parametros[0]);
        long2=Float.parseFloat(parametros[1]);
        
        dist= new Distancia();
        distancia = dist.distanciaDesde(lat1, long1, lat2, long2);
        //System.out.println(distancia+"\n");
        
        pd=new PeligroDistancia();
        
        result=pd.estadoDistancia(distancia,a);
        System.out.println(result+ "\t" + distancia);
        
        return result+"\t"+probeA.sondas();
    }
    
}
