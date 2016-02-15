/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Probe;


/**
 *
 * @author megan
 */
import java.io.*;

public class probe {
    //public static void main(String args[]) {
    String cmd="";
    String ut, f;
	public String sondas(){
        try {
        	int cont =0;
                
                
		FileWriter fw = new FileWriter("log.out",true);
 
    		Runtime r = Runtime.getRuntime();
    		Runtime r2 = Runtime.getRuntime();
	
		Process p = r.exec("uptime > log.out");
		p.waitFor();
		Process p2 = r2.exec("free > log.out");
		p2.waitFor();
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader b2 = new	BufferedReader(new InputStreamReader(p2.getInputStream()));
	
		String line = "", line2 = "";
	
			while ((line = b.readLine()) != null) {
                                ut=line.substring(45,48).trim().replace(',', '.');
  				fw.write(line.substring(44,48));
  		
			}
			while ((line2 = b2.readLine()) != null){
				if (cont==1){
                                    f=line2.substring(22,31).trim().replace(',', '.');
                                    fw.write(line2.substring(20,31));
				}
				cont++;
			}
			fw.write("\n");
			b.close();
			fw.close();
			}
		catch(IOException e1){
			System.out.println(e1);	
		}

       	catch(InterruptedException e2) {
			System.out.println(e2);
		}

        return cmd=String.valueOf(ut)+"\t"+String.valueOf(f);
  	}
}

