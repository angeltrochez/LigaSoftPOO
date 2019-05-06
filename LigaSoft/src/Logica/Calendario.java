/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import GUI.CalendarioGUI;
import java.awt.List;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santiago
 */
public class Calendario {

   private int idTorneo;
   private int jornada;
   private String equipoLocal;
   private String equipoVisita;
   private String fecha;
   private String hora;
   private String lugar;
   private String partido;
 
    public int getIdTorneo() {
        return idTorneo;
    }


    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

  
    public int getJornada() {
        return jornada;
    }


    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

     public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisita() {
        return equipoVisita;
    }


    public void setEquipoVisita(String equipoVisita) {
        this.equipoVisita = equipoVisita;
    }


    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the partido
     */
    public String getPartido() {
        return partido;
    }

    /**
     * @param partido the partido to set
     */
    public void setPartido(String partido) {
        this.partido = partido;
    }
   
   
    
    
    public void generarCalendario(){
    ConexionDB nuevaConexion = new ConexionDB();
    nuevaConexion.conexion(); 
    
    ArrayList <Integer> clubes=new ArrayList<>();
     
    try{   
        nuevaConexion.st =  nuevaConexion.conexion.createStatement();
        
      //  nuevaConexion.consultaSQL1=("select count(estado) from equipos where estado ='Activo'");
        nuevaConexion.consultaSQL=("select idequipos from equipos where estado ='Activo'");
        
        
        nuevaConexion.rst = nuevaConexion.st.executeQuery(nuevaConexion.consultaSQL);
             

       

        while(nuevaConexion.rst.next()){
            int id =(nuevaConexion.rst.getInt("idequipos"));
            clubes.add(id);
        }
        int cantEquipos = clubes.size();
        
        int matriz[][] = new int[cantEquipos][cantEquipos];
        
        
        
      //  matriz[0][0]=0;
        
        
        int k=0;
        for(int i=0; i<cantEquipos; i++){
            for(int j=0; j<cantEquipos; j++){
                matriz[0][j]=1;//clubes.get(k);
                matriz[i][0]=1; //clubes.get(k);
                k++;
            }
        }
        
        for(int i=1; i<=cantEquipos; i++){
            for(int j=1; j<=cantEquipos; j++){
                matriz[i][j]=0;
            }
        }
        
        for(int i=0; i<=cantEquipos; i++){
            for(int j=0; j<=cantEquipos; j++){
                System.out.println(matriz[i][j]);
            }
            System.out.println("");
        }

        

      
 
        boolean impar=(cantEquipos%2!=0);
        if(impar){
           JOptionPane.showMessageDialog(null, "El numero de equipos ingresados es impar", "Error al Generar el Calendario",JOptionPane.ERROR_MESSAGE);
        }   
       
       
        
        /*for(int i=0; i<cantEquipos; i++){
            for(int j=0; j<=1; j++){
            matriz[i][j]= objEquipo.getIDEquipos();
            }
        }
        
         for(int i=0; i<cantEquipos; i++){
            for(int j=0; j<=1; j++){
                System.out.println(matriz[i][j]);
            }
        }    k++;
        }
        
        for(int i=0; i<cantEquipos; i++){
            for(int j=0; j<=1; j++){
                System.out.println(matriz[i][j]);
        }
        }
        
        
        
        /*
        int j=0;
        
        for(int[] equipo: matriz){
              matriz[0][0] = (nuevaConexion.rst.getInt("idequipos"));
              j++;
               System.out.println(equipo);
        }
        
        
        
         */
 
       
        
        /*for(int i=0; i<cantEquipos; i++){
            for(int j=0; j<=1; j++){
            matriz[i][j]= objEquipo.getIDEquipos();
            }
        }
        
         for(int i=0; i<cantEquipos; i++){
            for(int j=0; j<=1; j++){
                System.out.println(matriz[i][j]);
            }
        }*/
       
        
        
    }catch(SQLException e){
    JOptionPane.showMessageDialog(null, "Error al generar el torneo "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
    }
    
    
    }//fin de la funcion
    
}

