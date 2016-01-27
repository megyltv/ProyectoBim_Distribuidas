package servidor;

/**
 *
 * @author megan
 */
public class PeligroDistancia {
    public String estadoDistancia(float distancia, int valor){
        String result="";
        
        if(distancia>0 && distancia<=4){
            System.out.println("Peligro");
        }else{
            if(distancia>4 && distancia<=15){
                System.out.println("Peligro Medio");
            }
                else{
                    if(distancia>15 && distancia<=40){
                        System.out.println("Peligro Mínimo");
                    }
                    else{
                        System.out.println("No hay Peligro");
                    }
                }
            }  
        return result;
    }
}
