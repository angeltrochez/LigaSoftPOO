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
   private String estadio;
   ArrayList<Equipos> lista = new ArrayList<>();

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
        
        nuevaConexion.consultaSQL=("select idequipos from equipos where estado ='Activo'");
        
        
        nuevaConexion.rst = nuevaConexion.st.executeQuery(nuevaConexion.consultaSQL);

        while(nuevaConexion.rst.next()){
            int id =(nuevaConexion.rst.getInt("idequipos"));
            clubes.add(id);
        }
       
        int cantEquipos = clubes.size();
        cantEquipos++;

        int matriz[][] = new int[(cantEquipos/2)*(cantEquipos-1)][2];
        int k=0;
        
        for(int i=0;i<cantEquipos-1;i++){
            for(int j=i+1; j<cantEquipos-1; j++){
                   for(int l=1; l<=cantEquipos/2;l++)
                    jornada = l;
                    if(j%2==0){
                    matriz[k][0]=clubes.get(i);
                    matriz[k][1]=clubes.get(j);
                    
                    }else{
                    matriz[k][0]=clubes.get(j);
                    matriz[k][1]=clubes.get(i);
                    
                    }
                    k++;
                        
                        equipoLocal=clubes.get(i);
                        equipoVisita=clubes.get(j);
                   
                    nuevaConexion.st.executeUpdate("INSERT INTO jornadas (fk_equilocal, fk_equivisita, temporada, jornada) values("
                                            + "'"+equipoLocal+"',"
                                            + "'"+equipoVisita+"',"
                                            + "'Partido de Ida',"
                                            + "'"+jornada+"')");
                    nuevaConexion.st.executeUpdate("INSERT INTO jornadas (fk_equilocal, fk_equivisita, temporada, jornada) values("
                                            + "'"+equipoVisita+"',"
                                            + "'"+equipoLocal+"',"
                                            + "'Partido de Vuelta',"
                                            + "'"+jornada+"')");
                    
            }
            
        }
        
                 
       
        boolean impar=(cantEquipos%2==0);
        if(impar){
           JOptionPane.showMessageDialog(null, "El numero de equipos ingresados es impar", "Error al Generar el Calendario",JOptionPane.ERROR_MESSAGE);
        }   
       
        
        
    }catch(SQLException e){
    JOptionPane.showMessageDialog(null, "Error al generar el torneo "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
    }
    
    
    }//fin de la funcion
    
    
    public void MostrarJornadas(){
    
      ConexionDB Eq = new ConexionDB();
      
      Eq.conexion();
      
      int fila = CalendarioGUI.jtDatos.getSelectedRow();
      
        try{
            Eq.consultaSQL = "select concat(equi.nombre, ' VS ', equip.nombre) AS partido, equi.estadio, jor.idjornadas from  jornadas jor " +
                                " inner join equipos equi on  jor.fk_equilocal = equi.idequipos " +
                                " inner join equipos equip on jor.fk_equivisita = equip.idequipos "+
                                "where jor.idjornadas="+CalendarioGUI.jtDatos.getValueAt(fila, 0)           ;
            
            Eq.st = Eq.conexion.createStatement();
            Eq.rst = Eq.st.executeQuery(Eq.consultaSQL);
            Eq.rst.next();
            
            CalendarioGUI.txtIdJornada.setText( Eq.rst.getString("idjornadas"));
            CalendarioGUI.txtPartido.setText(Eq.rst.getString("partido"));
            CalendarioGUI.txtLugar.setText(Eq.rst.getString("estadio"));
            
            
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
    
    }
    
    public void buscar(){
    ConexionDB Cal = new ConexionDB();
      
        Cal.conexion();
      
        try{
                Cal.st =  Cal.conexion.createStatement();
                Cal.consultaSQL = "select concat(equi.nombre, ' VS ', equip.nombre) AS partido, equi.estadio, equi.ciudad, jor.idjornadas from   jornadas jor" +
"				inner join equipos equi on  jor.fk_equilocal = equi.idequipos" +
"				inner join equipos equip on jor.fk_equivisita = equip.idequipos ";
                
         
                Cal.rst=Cal.st.executeQuery(Cal.consultaSQL);
         
           
          
                    ArrayList<Calendario> lista = new ArrayList<>();
                    while(Cal.rst.next()){
                        Calendario obj = new Calendario();
                        obj.idTorneo= Cal.rst.getInt("idjornadas");
                        obj.partido = Cal.rst.getString("partido");
                        obj.estadio = Cal.rst.getString("estadio");
                        obj.lugar = Cal.rst.getString("ciudad");
                                                
                        lista.add(obj);
                    }
           
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Codigo");
                model.addColumn("Partido");
                model.addColumn("Estadio");
                model.addColumn("Ciudad");
                model.addColumn("Fecha");
                
                
                                        
                String datos[]= new String[5];
                    
                    for(Calendario elem : lista){
                        datos[0]=Integer.toString(elem.idTorneo);                        
                        datos[1]=(elem.partido);
                        datos[2]=(elem.estadio);
                        datos[3]=(elem.lugar);
                        datos[4]=(elem.fecha);
                        
                        model.addRow(datos);
                    }
          
                CalendarioGUI.jtDatos.setModel(model);
          
        }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Problemas de Conexión "+ex.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
    }
}




     
        

