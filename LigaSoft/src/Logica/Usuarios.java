/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Logica.ConexionDB;
import GUI.UsuariosGUI;
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
 * @author Angel Santiago Trochez
 */
public class Usuarios implements Personas {

    private int idUsuario;
    private String nombreCompleto;
    private String sexo;
    private int edad;
    private String usuario;
    private String contrasena;
    private String tipoUsuario;
    ArrayList<Usuarios> lista = new ArrayList<>();
    
   
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

   public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }

   
    
    public String getUsuario() {
        return usuario;
    }

   public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
   
       /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
   
   //insertar nuevos usuarios a la base de datos alojada en un server Postgresql 11
   public void agregarUsuario(){
      ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
          
          nuevaConexion.consultaSQL=("INSERT INTO public.usuarios(nombre, edad, sexo, usuario, contrasena, tipousuario)VALUES("
                                +"'"+nombreCompleto+"',"
                                 +"'"+edad+"',"
                                 +"'"+sexo+"',"
                                 +"'"+usuario+"',"
                                 +"'"+contrasena+"',"
                                 +"'"+tipoUsuario+"')");
          
          int z = nuevaConexion.st.executeUpdate(nuevaConexion.consultaSQL);
            
          
           if(z==1){
               JOptionPane.showMessageDialog(null, "Registro Exitoso", "Ingreso de Usuarios",JOptionPane.OK_OPTION);
          }else{
              JOptionPane.showMessageDialog(null, "Ocurrio un error al ingresar el usuario", "Ingreso de Usuarios",JOptionPane.OK_OPTION);
          }
           
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public void eliminarUsuario(){
   
   ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
         
          nuevaConexion.consultaSQL = "DELETE FROM usuarios"
                  + " WHERE idusuarios="+idUsuario;
          
          nuevaConexion.st.executeUpdate(nuevaConexion.consultaSQL);
        
        
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   
   }
    
   public void buscarUsuario(){
   
      ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      try{
          nuevaConexion.st =  nuevaConexion.conexion.createStatement();
          nuevaConexion.consultaSQL = "select idusuarios, nombre, edad, sexo, usuario, tipousuario from usuarios"
                  + " WHERE nombre::text LIKE"+"'%"+nombreCompleto+"%'";
         
          nuevaConexion.rst=nuevaConexion.st.executeQuery(nuevaConexion.consultaSQL);
         
           
          
           while(nuevaConexion.rst.next()){
              Usuarios objUsuario = new Usuarios();
               objUsuario.idUsuario = nuevaConexion.rst.getInt("idusuarios");
               objUsuario.nombreCompleto = nuevaConexion.rst.getString("nombre");
               objUsuario.edad = nuevaConexion.rst.getInt("edad");
               objUsuario.sexo = nuevaConexion.rst.getString("sexo");
               objUsuario.usuario = nuevaConexion.rst.getString("usuario");
               objUsuario.tipoUsuario = nuevaConexion.rst.getString("tipousuario");
               lista.add(objUsuario);
           }
           
           DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Codigo Usuario");
            model.addColumn("Nombre");
            model.addColumn("Sexo");
            model.addColumn("Edad");
            model.addColumn("Usuario");
            model.addColumn("Tipo Usuario"); 
           
            String datos[]= new String[6];
           for(Usuarios elem : lista){
               datos[0]=Integer.toString(elem.idUsuario);
               datos[1]=(elem.nombreCompleto);
               datos[2]=(elem.sexo);
               datos[3]=Integer.toString(elem.edad);
               datos[4]=(elem.usuario);
               datos[5]=(elem.tipoUsuario);
               model.addRow(datos);
           }
          
           UsuariosGUI.jtDatos.setModel(model);
          
      }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+ex.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
            
      }
   
   }
 
 
       
    public void mostrarUsuarios(){
    
      ConexionDB nuevaConexion = new ConexionDB();
      
      nuevaConexion.conexion();
      
      int fila =UsuariosGUI.jtDatos.getSelectedRow();
      
        try{
            nuevaConexion.consultaSQL = "Select * from usuarios WHERE idusuarios="+UsuariosGUI.jtDatos.getValueAt(fila, 0);
            nuevaConexion.st = nuevaConexion.conexion.createStatement();
            nuevaConexion.rst = nuevaConexion.st.executeQuery(nuevaConexion.consultaSQL);
            nuevaConexion.rst.next();
            
            UsuariosGUI.txtIdUsuario.setText( nuevaConexion.rst.getString("idusuarios"));
            UsuariosGUI.txtNombreCompleto.setText( nuevaConexion.rst.getString("nombre"));
            UsuariosGUI.cbxSexo.setSelectedItem(nuevaConexion.rst.getString("sexo"));
            UsuariosGUI.txtEdad.setText( nuevaConexion.rst.getString("edad"));
            UsuariosGUI.txtUsuario.setText( nuevaConexion.rst.getString("usuario"));
            UsuariosGUI.txtContrasena.setText( nuevaConexion.rst.getString("contrasena"));
            UsuariosGUI.cbxTipoUsuario.setSelectedItem(nuevaConexion.rst.getString("tipousuario"));
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
    }
}
