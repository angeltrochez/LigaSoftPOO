/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import GUI.EstPartidosGUI;
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
 * @author Edelberto Rz
 */
public class EstPartidos {
    private String  EquipoLocal ,EquipoVisita,
                    PosesionLocal,PosesionVisita;
                   
    private int     GolesLocal,GolesVisita,
                    DisparosDirectosLocal,DisparosDirectosVisita,
                    FaltasLocal,FaltasVisita,  
                    TAmarillasLocal,TAmarillasVisita,
                    TRojasLocal,TRojasVisita,
                    FueraDeJuegoLocal,FueraDeJuegoVisita;

    public String getEquipoLocal() {
        return EquipoLocal;
    }

    public void setEquipoLocal(String EquipoLocal) {
        this.EquipoLocal = EquipoLocal;
    }

    public String getEquipoVisita() {
        return EquipoVisita;
    }

    public void setEquipoVisita(String EquipoVisita) {
        this.EquipoVisita = EquipoVisita;
    }

    public String getPosesionLocal() {
        return PosesionLocal;
    }

    public void setPosesionLocal(String PosesionLocal) {
        this.PosesionLocal = PosesionLocal;
    }

    public String getPosesionVisita() {
        return PosesionVisita;
    }

    public void setPosesionVisita(String PosesionVisita) {
        this.PosesionVisita = PosesionVisita;
    }

    public int getGolesLocal() {
        return GolesLocal;
    }

    public void setGolesLocal(int GolesLocal) {
        this.GolesLocal = GolesLocal;
    }

    public int getGolesVisita() {
        return GolesVisita;
    }

    public void setGolesVisita(int GolesVisita) {
        this.GolesVisita = GolesVisita;
    }

    public int getDisparosDirectosLocal() {
        return DisparosDirectosLocal;
    }

    public void setDisparosDirectosLocal(int DisparosDirectosLocal) {
        this.DisparosDirectosLocal = DisparosDirectosLocal;
    }

    public int getDisparosDirectosVisita() {
        return DisparosDirectosVisita;
    }

    public void setDisparosDirectosVisita(int DisparosDirectosVisita) {
        this.DisparosDirectosVisita = DisparosDirectosVisita;
    }

    public int getFaltasLocal() {
        return FaltasLocal;
    }

    public void setFaltasLocal(int FaltasLocal) {
        this.FaltasLocal = FaltasLocal;
    }

    public int getFaltasVisita() {
        return FaltasVisita;
    }

    public void setFaltasVisita(int FaltasVisita) {
        this.FaltasVisita = FaltasVisita;
    }

    public int getTAmarillasLocal() {
        return TAmarillasLocal;
    }

    public void setTAmarillasLocal(int TAmarillasLocal) {
        this.TAmarillasLocal = TAmarillasLocal;
    }

    public int getTAmarillasVisita() {
        return TAmarillasVisita;
    }

    public void setTAmarillasVisita(int TAmarillasVisita) {
        this.TAmarillasVisita = TAmarillasVisita;
    }

    public int getTRojasLocal() {
        return TRojasLocal;
    }

    public void setTRojasLocal(int TRojasLocal) {
        this.TRojasLocal = TRojasLocal;
    }

    public int getFueraDeJuegoLocal() {
        return FueraDeJuegoLocal;
    }

    public void setFueraDeJuegoLocal(int FueraDeJuegoLocal) {
        this.FueraDeJuegoLocal = FueraDeJuegoLocal;
    }

    public int getTRojasVisita() {
        return TRojasVisita;
    }

    public void setTRojasVisita(int TRojasVisita) {
        this.TRojasVisita = TRojasVisita;
    }

    public int getFueraDeJuegoVisita() {
        return FueraDeJuegoVisita;
    }

    public void setFueraDeJuegoVisita(int FueraDeJuegoVisita) {
        this.FueraDeJuegoVisita = FueraDeJuegoVisita;
    }
    
        
    public void AgregarEstadistica(){
        
    ConexionDB Est = new ConexionDB();
        
    Est.conexion();    
    
    try{      
          Est.st =  Est.conexion.createStatement();
          int z = Est.st.executeUpdate("INSERT INTO EstPartidos("
                  + "PosesionL, PosesionV,"
                  + " GolesL, GolesV,"
                  + " DisparosL, DisparosV,"
                  + " FaltasL, FaltasV,"
                  + " TarjAmarillasL, TarjAmarillasV,"
                  + " TarjRojasL, TarjRojasV,"
                  + " FueradeJuegoL, FueradeJuegoV) "
                  
                  + "values("
                  
                        + "'"+PosesionLocal+"',"
                        + "'"+PosesionVisita+"',"
                        + "'"+GolesLocal+"',"
                        + "'"+GolesVisita+"',"                  
                        + "'"+DisparosDirectosLocal+"',"
                        + "'"+DisparosDirectosVisita+"',"
                        + "'"+FaltasLocal+"',"                  
                        + "'"+FaltasVisita+"',"                                
                        + "'"+TAmarillasLocal+"',"                  
                        + "'"+TAmarillasVisita+"',"
                        + "'"+TRojasLocal+"',"                  
                        + "'"+TRojasVisita+"',"
                        + "'"+FueraDeJuegoLocal+"',"                  
                        + "'"+FueraDeJuegoVisita+"');"

          );  
          
          if(z==1){
               JOptionPane.showMessageDialog(null, "Registro Exitoso");
          }else{
              JOptionPane.showMessageDialog(null, "No es posible Hacer el Registro");
          }
      }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Problemas de Conexión", "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
    }
    
    
    
    
    
    
    
}
