/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Logica.ConexionDB;
import GUI.JugadoresGUI;
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
public class Jugadores implements Personas {

     
    private int idJugador;
    private String nombreCompleto;
    private String sexo;
    private int edad;
    private String equipo;
    private int numeroCamisa;
    private String posicion;
    private String estado;
    ArrayList<Jugadores> lista = new ArrayList<>();

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(int numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    public void agregarJugador(){
      ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
          
          nuevaConexion.consultaSQL=("INSERT INTO public.jugadores(nombre, edad, sexo, equipo, posicion, estado, numerocamisa) VALUES( "
                                +"'"+nombreCompleto+"',"
                                 +"'"+edad+"',"
                                 +"'"+sexo+"',"
                                 +"'"+equipo+"',"
                                 +"'"+posicion+"',"
                                 +"'"+estado+"',"
                                 +"'"+numeroCamisa+"')");
          
          int z = nuevaConexion.st.executeUpdate(nuevaConexion.consultaSQL);
            
          
           if(z==1){
               JOptionPane.showMessageDialog(null, "Registro Exitoso", "Ingreso de Jugadores",JOptionPane.OK_OPTION);
          }else{
              JOptionPane.showMessageDialog(null, "Ocurrio un error al ingresar el jugador", "Ingreso de Jugadores",JOptionPane.OK_OPTION);
          }
           
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   }
    
    public void eliminarJugador(){
   
   ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
         
          nuevaConexion.consultaSQL = "DELETE FROM jugadores"
                  + " WHERE idjugadores="+idJugador;
          
          nuevaConexion.st.executeUpdate(nuevaConexion.consultaSQL);
        
        
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   
   }
    
   public void buscarJugador(){
   
      ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
          nuevaConexion.consultaSQL = "	SELECT idjugadores, nombre, edad, sexo, equipo, posicion, estado, numerocamisa FROM public.jugadores"
                  + " WHERE nombre::text LIKE"+"'%"+nombreCompleto+"%'"
                  + "ORDER BY idjugadores ASC";
         
          nuevaConexion.rst=nuevaConexion.st.executeQuery(nuevaConexion.consultaSQL);
         
           
          
           while(nuevaConexion.rst.next()){
              Jugadores objJugador = new Jugadores();
               objJugador.idJugador = nuevaConexion.rst.getInt("idjugadores");
               objJugador.nombreCompleto = nuevaConexion.rst.getString("nombre");
               objJugador.edad = nuevaConexion.rst.getInt("edad");
               objJugador.sexo = nuevaConexion.rst.getString("sexo");
               objJugador.equipo = nuevaConexion.rst.getString("equipo");
               objJugador.posicion = nuevaConexion.rst.getString("posicion");
               objJugador.estado = nuevaConexion.rst.getString("estado");
               objJugador.numeroCamisa = nuevaConexion.rst.getInt("numerocamisa");
               lista.add(objJugador);
           }
           
           DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Codigo Jugador");
            model.addColumn("Nombre");
            model.addColumn("Sexo");
            model.addColumn("Edad");
	    model.addColumn("Equipo");
            model.addColumn("Posición"); 
            model.addColumn("Estado"); 
             model.addColumn("Número de Camisa"); 
		   
            String datos[]= new String[8];
           for(Jugadores elem : lista){
               datos[0]=Integer.toString(elem.idJugador);
               datos[1]=(elem.nombreCompleto);
               datos[2]=(elem.sexo);
               datos[3]=Integer.toString(elem.edad);
               datos[4]=(elem.equipo);
               datos[5]=(elem.posicion);
               datos[6]=(elem.estado);
               datos[7]=Integer.toString(elem.numeroCamisa);
               model.addRow(datos);
           }
          
           JugadoresGUI.jtDatos.setModel(model);
          
      }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+ex.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
            
      }
   
   }
 
   public void mostrarJugador(){
    
      ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      int fila =JugadoresGUI.jtDatos.getSelectedRow();
      
        try{
            nuevaConexion.consultaSQL = "Select * from jugadores WHERE idjugadores="+JugadoresGUI.jtDatos.getValueAt(fila, 0);
            nuevaConexion.st = nuevaConexion.conexion.createStatement();
            nuevaConexion.rst = nuevaConexion.st.executeQuery(nuevaConexion.consultaSQL);
            nuevaConexion.rst.next();
            
            JugadoresGUI.txtIdJugador.setText( nuevaConexion.rst.getString("idjugadores"));
            JugadoresGUI.txtNombreCompleto.setText( nuevaConexion.rst.getString("nombre"));
            JugadoresGUI.cbxSexo.setSelectedItem(nuevaConexion.rst.getString("sexo"));
            JugadoresGUI.txtEdad.setText( nuevaConexion.rst.getString("edad"));
            JugadoresGUI.txtEquipo.setText( nuevaConexion.rst.getString("equipo"));
            JugadoresGUI.txtNumeroCamisa.setText( nuevaConexion.rst.getString("numerocamisa"));
            JugadoresGUI.txtPosicion.setText( nuevaConexion.rst.getString("posicion"));
            JugadoresGUI.cbxEstado.setSelectedItem(nuevaConexion.rst.getString("estado"));
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
    }
    
   public void actualizarJugador(){
   
    ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
                  
	  nuevaConexion.consultaSQL = "UPDATE public.jugadores SET  nombre="+"'"+nombreCompleto+"'," 
                                         +"edad="+"'"+edad+"'," 
                                         +"sexo="+"'"+sexo+"'," 
                                         +"equipo="+"'"+equipo+"'," 
                                         +"posicion="+"'"+posicion+"'," 
                                         +"estado="+"'"+estado+"'," 
					 +"numerocamisa="+"'"+numeroCamisa+"'" 
                                         +"WHERE idjugadores="+idJugador;
	       
         nuevaConexion.st.executeUpdate(nuevaConexion.consultaSQL);
        
        
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   
   }
    
}
