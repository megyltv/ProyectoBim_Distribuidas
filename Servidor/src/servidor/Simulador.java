
package servidor;

import java.util.Random;

/**
 *
 * @author megan
 */
public class Simulador extends Thread {
    double valor=1;
    int i=1;
    double max=0.2, min=-0.1;
    double aux;
        
    Random rand= new Random();
    
    @Override
    public void run(){
        while(true){
            aux=rand.nextFloat()*max+min;
            valor = valor+ aux;
            //System.out.println(aux + "\t"+valor);
            
        }
    }
    
    public int getValor(){
        return (int)valor;
    }
    
}