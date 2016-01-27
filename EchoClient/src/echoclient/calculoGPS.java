/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoclient;

import java.util.Random;

/**
 *
 * @author megan
 */
public class calculoGPS {
    public String coordenadas(){
        String result="";
        float latitud, longitud;
        float max=(float) -0.16, min=(float) -0.68;
        
        Random rand = new Random();
        latitud=((rand.nextFloat()*min)+max); //latitud
        longitud = (rand.nextFloat())-79; //longitud
        
        result= String.valueOf(latitud)+ ","+String.valueOf(longitud);
        
        return result;
    }
}
