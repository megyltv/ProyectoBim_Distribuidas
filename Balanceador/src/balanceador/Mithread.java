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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Mithread extends Thread {

    Socket clientSocket;
    String inputLine;
    int idThread;
    float cmdUptimeS1 = (float) 0.0, cmdUptimeS2 = (float) 0.0;
    int cmdFreeS1, cmdFreeS2;
    String estado1, estado2;
    int a;
    
    //probeA probeI = new probeA();

    public Mithread(Socket client) {
        clientSocket = client;
    }

    public void run() {
        PrintWriter out = null;
        
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
            Random j = new Random(); 
            a = j.nextInt(9);
                if ((a%2==0)) {
                    estado1 = enviarServidor1(inputLine);
                    out.println(estado1);
                    
                } else {
                    estado2 = enviarServidor2(inputLine);
                    out.println(estado2);
                    
                }
                // enviarServidor1(inputLine);
                //enviarServidor2(inputLine);
               
            }

        } catch (IOException ex) {
            Logger.getLogger(Mithread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    public String enviarServidor1(String coordenadas) {
        String hostName = "172.30.24.227";
        String result = "";
        int portNumber = 8001;
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out1
                = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in1
                = new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn1
                = new BufferedReader(
                        new InputStreamReader(System.in))) {
            String userInput = inputLine;
            out1.println(userInput);
            String str = in1.readLine();
            String[] parametros = str.split("\t");
            //System.out.println(str); 
            //out.println(parametros[0].trim());
            result = parametros[0].trim();
            cmdUptimeS1 = Float.parseFloat(parametros[1].trim());
            cmdFreeS1 = Integer.parseInt(parametros[2].trim());
            //System.out.println("echo: " + userInput);
            System.out.println("S1 " + cmdUptimeS1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
        return result;
    }

    public String enviarServidor2(String coordernadas) {
        String hostName = "172.30.36.184";
        String resultado = "";
        int portNumber = 8002;
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out2
                = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in2
                = new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn2
                = new BufferedReader(
                        new InputStreamReader(System.in))) {
            String userInput = inputLine;
            out2.println(userInput);
            String str = in2.readLine();
            String[] parametros = str.split("\t");
            //System.out.println(str); 
            //out.println(parametros[0].trim()); 
            resultado = parametros[0].trim();
            cmdUptimeS2 = Float.parseFloat(parametros[1].trim());
            cmdFreeS2 = Integer.parseInt(parametros[2].trim());
            //System.out.println("echo: " + userInput);
            System.out.println("S2 " + cmdUptimeS2);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
        return resultado;
    }
}