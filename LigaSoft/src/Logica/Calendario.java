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
   private int equipoLocal;
   private int equipoVisita;
   private String fecha;
   private String hora;
   private String lugar;
   private String partido;

    public int getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(int equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public int getEquipoVisita() {
        return equipoVisita;
    }

    public void setEquipoVisita(int equipoVisita) {
        this.equipoVisita = equipoVisita;
    }
 
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
        int matriz[][] = new int[(cantEquipos/2)*(cantEquipos-1)][3];  //imprimir -2
        
        int k=0;
        int Jornada1;
        int Jornada2 = ((cantEquipos/2)*(cantEquipos-2))/2;
        for(int i=0;i<cantEquipos-1;i++){
            for(int j=i+1; j<cantEquipos-1; j++){
                //    if(j%2==0){
                    matriz[k][0]=clubes.get(i);
                    matriz[k][1]=clubes.get(j);
                /*    }else{
                    matriz[k][0]=clubes.get(j);
                    matriz[k][1]=clubes.get(i);
                    }
                */            
                    k++;
            }
        }

        for(int i=0;i<((cantEquipos/2)*(cantEquipos-2));i++){
                equipoLocal = (matriz[i][0]);
                equipoVisita = (matriz[i][1]);
                partido = ("select concat((select nombre from equipos where idequipos ="+equipoLocal+" ),' vs ',(select nombre from equipos where idequipos ="+equipoVisita+")");
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




        /*
        //Asignando jornadas a cada uno.
        
        int i=0;
        while(i<((cantEquipos/2)*(cantEquipos-2))){{
 
            for(int temp1=1;temp1<cantEquipos-1;temp1++){
                matriz[i][2]=temp1;
                i++;
            }
            
            for(int temp2=cantEquipos-2;temp2>=0;temp2--){
                matriz[i][2]=temp2;
                i++;
            }
            
        }
                    
        
        
        
        /*  Imprimir en pantalla
        for(i=0;i<(cantEquipos/2)*(cantEquipos-2);i++){
                System.out.println(i+1+" "+matriz[i][0]+" vrs "+matriz[i][1]+" Jornada: "+matriz[i][2]);
        }
        
        }*/
        

