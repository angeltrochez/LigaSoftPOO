/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Logica.ConexionDB;
import GUI.DirectivosGUI;
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
public class Directivos implements Personas{
    private int idDirectivo;
    private String nombreCompleto;
    private String sexo;
    private int edad;
    private String equipo;
    private String posicion;
   
    ArrayList<Directivos> lista = new ArrayList<>();
    
   
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

	public void setIdDirectivo(int idDirectivo) {
        this.idDirectivo = idDirectivo;
    }
	
	public void setEquipo(String equipo){
		this.equipo = equipo;
	}
	
	public void setPosicion(String posicion){
		this.posicion = posicion;
	}
	
    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getSexo() {
        return this.sexo;
    }

       
    public int getIdDirectivo() {
        return this.idDirectivo;
    }
	
    public String getEquipo(){
	return this.equipo;
    }
	
    public String getPosicion(){
    	return posicion;
    }
  
   
   //insertar nuevos directivos a la base de datos alojada en un server Postgresql 11
   public void agregarDirectivo(){
      ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
          
          nuevaConexion.consultaSQL=("INSERT INTO public.directivos(nombre, edad, sexo, equipo, posicion)VALUES("
                                +"'"+nombreCompleto+"',"
                                 +"'"+edad+"',"
                                 +"'"+sexo+"',"
                                 +"'"+equipo+"',"
                                 +"'"+posicion+"')");
          
          int z = nuevaConexion.st.executeUpdate(nuevaConexion.consultaSQL);
            
          
           if(z==1){
               JOptionPane.showMessageDialog(null, "Registro Exitoso", "Ingreso de Directivos",JOptionPane.OK_OPTION);
          }else{
              JOptionPane.showMessageDialog(null, "Ocurrio un error al ingresar el Directivos", "Ingreso de Directivos",JOptionPane.OK_OPTION);
          }
           
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public void eliminarDirectivo(){
   
   ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
         
          nuevaConexion.consultaSQL = "DELETE FROM directivos"
                  + " WHERE iddirectivo="+idDirectivo;
          
          nuevaConexion.st.executeUpdate(nuevaConexion.consultaSQL);
        
        
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   
   }
    
   public void buscarDirectivo(){
   
      ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
          nuevaConexion.consultaSQL = "select iddirectivos, nombre, edad, sexo, equipo, posicion from directivos"
                  + " WHERE nombre::text LIKE"+"'%"+nombreCompleto+"%'"
                  + "ORDER BY iddirectivos ASC";
         
          nuevaConexion.rst=nuevaConexion.st.executeQuery(nuevaConexion.consultaSQL);
         
           
          
           while(nuevaConexion.rst.next()){
              Directivos objDirectivo = new Directivos();
               objDirectivo.idDirectivo = nuevaConexion.rst.getInt("iddirectivos");
               objDirectivo.nombreCompleto = nuevaConexion.rst.getString("nombre");
               objDirectivo.edad = nuevaConexion.rst.getInt("edad");
               objDirectivo.sexo = nuevaConexion.rst.getString("sexo");
               objDirectivo.equipo = nuevaConexion.rst.getString("equipo");
               objDirectivo.posicion = nuevaConexion.rst.getString("posicion");
               lista.add(objDirectivo);
           }
           
           DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Codigo Directivo");
            model.addColumn("Nombre");
            model.addColumn("Sexo");
            model.addColumn("Edad");
            model.addColumn("Equipo");
            model.addColumn("Posicion"); 
           
            String datos[]= new String[6];
           for(Directivos elem : lista){
               datos[0]=Integer.toString(elem.idDirectivo);
               datos[1]=(elem.nombreCompleto);
               datos[2]=(elem.sexo);
               datos[3]=Integer.toString(elem.edad);
               datos[4]=(elem.equipo);
               datos[5]=(elem.posicion);
               model.addRow(datos);
           }
          
           DirectivosGUI.jtDatos.setModel(model);
          
      }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+ex.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
            
      }
   
   }
 
 
       
    public void mostrarDirectivos(){
    
      ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      int fila =DirectivosGUI.jtDatos.getSelectedRow();
      
        try{
            nuevaConexion.consultaSQL = "Select * from directivos WHERE iddirectivos="+DirectivosGUI.jtDatos.getValueAt(fila, 0);
            nuevaConexion.st = nuevaConexion.conexion.createStatement();
            nuevaConexion.rst = nuevaConexion.st.executeQuery(nuevaConexion.consultaSQL);
            nuevaConexion.rst.next();
            
            DirectivosGUI.txtIdDirectivo.setText( nuevaConexion.rst.getString("iddirectivos"));
            DirectivosGUI.txtNombreCompleto.setText( nuevaConexion.rst.getString("nombre"));
            DirectivosGUI.cbxSexo.setSelectedItem(nuevaConexion.rst.getString("sexo"));
            DirectivosGUI.txtEdad.setText( nuevaConexion.rst.getString("edad"));
            DirectivosGUI.cbxEquipo.setSelectedItem(nuevaConexion.rst.getString("equipo"));
            DirectivosGUI.cbxPosicion.setSelectedItem(nuevaConexion.rst.getString("posicion"));
            
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
    }
     
    public void actualizarDirectivo(){
   
    ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
                  
	  nuevaConexion.consultaSQL = "UPDATE public.directivos SET  nombre="+"'"+nombreCompleto+"'," 
                                         +"edad="+"'"+edad+"'," 
                                         +"sexo="+"'"+sexo+"'," 
                                         +"equipo="+"'"+equipo+"',"
                                          +"posicion="+"'"+posicion+"'"
                                         +"WHERE iddirectivos="+idDirectivo;
        
         nuevaConexion.st.executeUpdate(nuevaConexion.consultaSQL);
        
        
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   
   }
}
