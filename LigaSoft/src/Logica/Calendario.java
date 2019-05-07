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

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getPartido() {
        return partido;
    }

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
        cantEquipos++;
        int matriz[][] = new int[cantEquipos][cantEquipos];
        
        int numeroV; 
        int n;
        int k=0;
        int local;
        boolean ban;
        int jornadas=1;
        
        for(int i=1; i<cantEquipos; i++){
            matriz[0][i]=clubes.get(k);
            matriz[i][0]=clubes.get(k);
            k++;
        }
        
       
        do{
            n=0;

            do{  
                ban=false;
                
                do{
                    local = (int)(Math.random() * cantEquipos-1)+1;
                    numeroV = (int)(Math.random() * cantEquipos-1)+1;
                }while(numeroV==local);
                
                if(matriz[local][numeroV]==0 && matriz[numeroV][local]==0){
                    
                    for(int i=1;i<cantEquipos;i++){
                        if(matriz[local][i]==1 || matriz[i][numeroV]==1){
                            ban=true;
                        }
                    }
                    
                    if(ban!=true){
                        matriz[local][numeroV]=1;       
                        matriz[numeroV][local]=1;
                        System.out.println(matriz[local][0]+" vrs "+matriz[0][numeroV]+"..."+n);
                        n++;
                   }
                } 
            }while(n<(cantEquipos/2));

        }while(jornadas<(cantEquipos-1));

                for(int i=0; i<cantEquipos; i++){
                        for(int j=0; j<cantEquipos; j++){
                    System.out.print(matriz[i][j]+" ");
                    }
                System.out.println("");
                }
                
                

        boolean impar=(cantEquipos%2==0);
        if(impar){
           JOptionPane.showMessageDialog(null, "El numero de equipos ingresados es impar", "Error al Generar el Calendario",JOptionPane.ERROR_MESSAGE);
        }   
       
        
        
    }catch(SQLException e){
    JOptionPane.showMessageDialog(null, "Error al generar el torneo "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
    }
    
    
    }//fin de la funcion
    
}

