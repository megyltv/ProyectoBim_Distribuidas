/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author megan
 */
public class Simulador extends Thread {

    double valor = 1;
    int i = 1;
    double max = 0.02, min = -0.01;
    double aux;
    int rango;
    int j = 1;
    List<Double> lstPositivos=new ArrayList<>();

    Random rand = new Random();
   int k=0;
    @Override
    public void run() {

        while (true) {
//            aux=rand.nextFloat()*max+min;
//            valor = valor+ aux;

            rango = rand.nextInt(9) + 1;
            while (i < 1001) {
                if (rango > 2) {
                    valor=(rango - 1) + (rango - (rango - 1)) * rand.nextDouble();
//                    valor = rand.nextDouble()* rango - (rango - 2);
                } else {
                    valor = rand.nextDouble() * rango;
                }
                if (j % 2 == 0) {
                    //System.out.println("valor1:" + valor);
                    //lstPositivos.add(valor);
                } else {
                    //System.out.println("valor1:" + valor * -1.0);
                }
                i++;
                j++;
            }
            i = 1;
            k++;
            //enviarValor();
        }
        
        /*for(Double dbl:lstPositivos){
            System.out.println("Positivos: "+dbl);
        }
        System.out.println("valor entero ultimo:"+getValor());*/
    }

    public int getValor() {
        return (int)valor;
        //return lstPositivos.get(lstPositivos.size()-1).intValue();
    }
    
    public void enviarValor(){
       String hostName = "172.30.36.184";
        int portNumber = 8003;
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out3
                = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in3
                = new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn3
                = new BufferedReader(
                        new InputStreamReader(System.in))) {

            out3.println((int)valor); 

            //System.out.println("echo: " + userInput);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }

}
