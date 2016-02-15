/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servidor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author megan
 */
public class PeligroDistancia {
    protected final Connection con;
    String cadSQL;
    
    
    public PeligroDistancia(){
        con = (new Conexion().obtenerConexion());
    }
    
    public String estadoDistancia(float distancia, int valor){
        String result[]=new String[1];
        
        
        if(distancia>0 && distancia<=4){
            cadSQL= "select pmayor from estado where escala = "+valor;
            try {
                Statement st = con.createStatement();
                ResultSet rs =st.executeQuery(cadSQL);
                while(rs.next()){
                    result[0]=rs.getString("pmayor");
                }
    
            } catch (SQLException ex) {
                System.out.println("estado distancia "+ ex);
            }
            
            //System.out.println(result[0]);
        }else{
            if(distancia>4 && distancia<=15){
                cadSQL= "select pmedio from estado where escala = "+valor;
                try {
                    Statement st = con.createStatement();
                    ResultSet rs =st.executeQuery(cadSQL);
                    while(rs.next()){
                        result[0]=rs.getString("pmedio");
                    }
                } catch (SQLException ex) {
                    System.out.println("estado distancia "+ ex);
                }
                
                //System.out.println(result[0]);
            }
                else{
                    if(distancia>15 && distancia<=40){
                        cadSQL= "select pbajo from estado where escala = "+valor;
                        try {
                            Statement st = con.createStatement();
                            ResultSet rs =st.executeQuery(cadSQL);
                            while(rs.next()){
                                result[0]=rs.getString("pbajo");
                            }
                        } catch (SQLException ex) {
                            System.out.println("estado distancia "+ ex);
                        }
                        //System.out.println(result[0]);
                    }
                    else{
                        cadSQL= "select pminimo from estado where escala = "+valor;
                        try {
                            Statement st = con.createStatement();
                            ResultSet rs =st.executeQuery(cadSQL);
                            while(rs.next()){
                                result[0]=rs.getString("pminimo");
                            }
                        } catch (SQLException ex) {
                            System.out.println("estado distancia "+ ex);
                        }
                        //System.out.println(result[0]);
                    }
                }
            }  
        return result[0];
    }
}
