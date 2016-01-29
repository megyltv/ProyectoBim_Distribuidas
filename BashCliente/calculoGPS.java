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