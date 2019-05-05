/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import GUI.EquiposGUI;
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
public class Equipos {
    
    private int IDEquipos;
    private String NombreEquipos;
    private String Ciudad;
    private String Estadio;
    private String Estado;
    ArrayList<Equipos> lista = new ArrayList<>();
  
    public int getIDEquipos() {
        return IDEquipos;
    }

    public void setIDEquipos(int IDEquipos) {
        this.IDEquipos = IDEquipos;
    }

    public String getNombreEquipos() {
        return NombreEquipos;
    }

    public void setNombreEquipos(String Equipos) {
        this.NombreEquipos = Equipos;
    }
    
    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getEstadio() {
        return Estadio;
    }

    public void setEstadio(String Estadio) {
        this.Estadio = Estadio;
    }
    
    public String getEstado() {
        return Estadio;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    
    
    public void AgregarEquipo(){
        
    ConexionDB AddEquipo = new ConexionDB();
        
    AddEquipo.conexion();    
    
    try{      
          AddEquipo.st =  AddEquipo.conexion.createStatement();
          int z = AddEquipo.st.executeUpdate("INSERT INTO equipos (Nombre,Estadio,Ciudad,Estado) values("
                                              + "'"+NombreEquipos+"',"
                                            + "'"+Estadio+"',"
                                            + "'"+Ciudad+"',"
                                            + "'"+Estado+"')");
          
          if(z==1){
               JOptionPane.showMessageDialog(null, "Registro Exitoso");
          }else{
              JOptionPane.showMessageDialog(null, "No es posible Hacer el Registro");
          }
      }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Problemas de Conexión", "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   }
    
    public void MostrarEquipos(){
    
      ConexionDB Eq = new ConexionDB();
      
      Eq.conexion();
      
      int fila =EquiposGUI.jtDatos.getSelectedRow();
      
        try{
            Eq.consultaSQL = "Select * from Equipos WHERE idEquipos="+EquiposGUI.jtDatos.getValueAt(fila, 0);
            Eq.st = Eq.conexion.createStatement();
            Eq.rst = Eq.st.executeQuery(Eq.consultaSQL);
            Eq.rst.next();
            
            EquiposGUI.txtIdEquipo.setText(Eq.rst.getString("idEquipos"));
            EquiposGUI.txtNombreEquipo.setText(Eq.rst.getString("nombre"));
            EquiposGUI.txtEstadio.setText(Eq.rst.getString("estadio"));
            EquiposGUI.txtCiudad.setText(Eq.rst.getString("ciudad"));
            EquiposGUI.cbxEstado.setSelectedItem(Eq.rst.getString("estado"));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
    }
    
    public void EliminarEquipo(){
   
        ConexionDB Eq = new ConexionDB();
      
        Eq.conexion();
      
        try{
            Eq.st =  Eq.conexion.createStatement();
         
            Eq.consultaSQL = "DELETE FROM equipos"
                  + " WHERE idequipos="+IDEquipos;
          
            Eq.st.executeUpdate(Eq.consultaSQL);
        
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   
   }
      
    public void BuscarEquipos(){
   
        ConexionDB Eq = new ConexionDB();
      
        Eq.conexion();
      
        try{
                Eq.st =  Eq.conexion.createStatement();
                Eq.consultaSQL = "select idEquipos, nombre, estadio, ciudad, estado from equipos"
                                + " WHERE nombre::text LIKE'%"+NombreEquipos+"%'";
         
                Eq.rst=Eq.st.executeQuery(Eq.consultaSQL);
         
           
          
                    while(Eq.rst.next()){
                        Equipos obj = new Equipos();
                        obj.IDEquipos = Eq.rst.getInt("idequipos");
                        obj.NombreEquipos= Eq.rst.getString("nombre");
                        obj.Estadio= Eq.rst.getString("estadio");
                        obj.Ciudad= Eq.rst.getString("ciudad");
                        obj.Estado= Eq.rst.getString("estado");
                        
                        lista.add(obj);
                    }
           
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Codigo Equipo");
                model.addColumn("Nombre");
                model.addColumn("Estadio");
                model.addColumn("Ciudad");
                model.addColumn("Estado");
                
                
                String datos[]= new String[5];
                    
                    for(Equipos elem : lista){
                        datos[0]=Integer.toString(elem.IDEquipos);
                        datos[1]=(elem.NombreEquipos);
                        datos[2]=(elem.Estadio);
                        datos[3]=(elem.Ciudad);
                        datos[4]=(elem.Estado);
                        
                        model.addRow(datos);
                    }
          
                EquiposGUI.jtDatos.setModel(model);
          
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Problemas de Conexión "+ex.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      } 
   }

    public void ActualizarDatos(){
   
        ConexionDB Eq = new ConexionDB();
        
        Eq.conexion();
        
        try{ 
            Eq.st = Eq.conexion.createStatement();
            Eq.consultaSQL  = "Update equipos"
                            + " set nombre = '"+NombreEquipos+"',"
                            + "estadio = '"+Estadio+"',"
                            + "ciudad = '"+Ciudad+"',"
                            + "estado = '"+Estado+"'"
                            + "where idequipos= "+IDEquipos;

            Eq.st.executeUpdate(Eq.consultaSQL);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   
   
   }  


    
    
    
}
