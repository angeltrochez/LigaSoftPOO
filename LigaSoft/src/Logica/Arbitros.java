 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import GUI.ArbitrosGUI;
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
public class Arbitros{
    
    private int IdArbitro;
    private String NombreArbitro;
    private String SexoArbitro;
    private int EdadArbitro;
    ArrayList<Arbitros> lista = new ArrayList<>();

    public String getNombreArbitro() {
        return NombreArbitro;
    }

    public void setNombreArbitro(String NombreArbitro) {
        this.NombreArbitro = NombreArbitro;
    }

    public String getSexoArbitro() {
        return SexoArbitro;
    }

    public void setSexoArbitro(String SexoArbitro) {
        this.SexoArbitro = SexoArbitro;
    }

    public int getEdadArbitro() {
        return EdadArbitro;
    }

    public void setEdadArbitro(int EdadArbitro) {
        this.EdadArbitro = EdadArbitro;
    }

    public int getIdArbitro() {
        return IdArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.IdArbitro = idArbitro;
    }
   
    
    
    //Agregar nuevo Arbitro a la bd
    public void AgregarNuevoArbitro(){
        
    ConexionDB AgregarArbitro = new ConexionDB();
        
    AgregarArbitro.conexion();    
    
    try{      
          AgregarArbitro.st =  AgregarArbitro.conexion.createStatement();
          int z = AgregarArbitro.st.executeUpdate("INSERT INTO Arbitros (Nombre, Edad, Sexo) values("
                  + "'"+NombreArbitro+"',"
                  + "'"+EdadArbitro+"',"
                  + "'"+SexoArbitro+"')");  
          
          if(z==1){
               JOptionPane.showMessageDialog(null, "Registro Exitoso");
          }else{
              JOptionPane.showMessageDialog(null, "No es posible Hacer el Registro");
          }
      }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Problemas de Conexión", "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   }
    //Mostrar Arbitros
    public void mostrarArbitros(){
    
      ConexionDB showArb = new ConexionDB();
      
      showArb.conexion();
      
      int fila =ArbitrosGUI.jtDatos.getSelectedRow();
      
        try{
            showArb.consultaSQL = "Select * from arbitros WHERE idArbitros="+ArbitrosGUI.jtDatos.getValueAt(fila, 0);
            showArb.st = showArb.conexion.createStatement();
            showArb.rst = showArb.st.executeQuery(showArb.consultaSQL);
            showArb.rst.next();
            
            ArbitrosGUI.txtIdArbitro.setText( showArb.rst.getString("idArbitros"));
            ArbitrosGUI.txtNombreCompleto.setText(showArb.rst.getString("nombre"));
            ArbitrosGUI.cbxSexo.setSelectedItem(showArb.rst.getString("sexo"));
            ArbitrosGUI.txtEdad.setText(showArb.rst.getString("edad"));
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
    }
    
    //Eliminar Arbitros
    public void eliminarArbitro(){
   
   ConexionDB arb = new ConexionDB();
      
      arb.conexion();
      
      try{
          arb.st =  arb.conexion.createStatement();
         
          arb.consultaSQL = "DELETE FROM arbitros"
                  + " WHERE idarbitros="+IdArbitro;
          
          arb.st.executeUpdate(arb.consultaSQL);
        
      }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   
   }
    
    //Buscar Arbitros en la bd
       public void buscarArbitro(){
   
      ConexionDB arb = new ConexionDB();
      
      arb.conexion();
      
      try{
          arb.st =  arb.conexion.createStatement();
          arb.consultaSQL = "select idarbitros, nombre, edad, sexo from arbitros"
                  + " WHERE nombre::text LIKE '%"+NombreArbitro+"%'";
         
          arb.rst=arb.st.executeQuery(arb.consultaSQL);
         
           
          
           while(arb.rst.next()){
              Arbitros obj = new Arbitros();
               obj.IdArbitro = arb.rst.getInt("idarbitros");
               obj.NombreArbitro = arb.rst.getString("nombre");
               obj.EdadArbitro = arb.rst.getInt("edad");
               obj.SexoArbitro = arb.rst.getString("sexo");
               lista.add(obj);
           }
           
           DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Codigo Arbitro");
            model.addColumn("Nombre");
            model.addColumn("Sexo");
            model.addColumn("Edad");
           
            String datos[]= new String[4];
           for(Arbitros elem : lista){
               datos[0]=Integer.toString(elem.IdArbitro);
               datos[1]=(elem.NombreArbitro);
               datos[2]=(elem.SexoArbitro);
               datos[3]=Integer.toString(elem.EdadArbitro);
               model.addRow(datos);
           }
          
           ArbitrosGUI.jtDatos.setModel(model);
          
      }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+ex.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
            
      }
   
   }
       
       
       
   public void ActualizarDatos(){
   
        ConexionDB arb = new ConexionDB();
        
        arb.conexion();
        
        try{ 
            arb.st = arb.conexion.createStatement();
            arb.consultaSQL = "Update arbitros"
                    + " set nombre= '"+NombreArbitro+"',"
                    + " edad= '"+EdadArbitro+"',"
                    + " sexo= '"+SexoArbitro+"'"
                    + " where idArbitros = "+IdArbitro ;

            arb.st.executeUpdate(arb.consultaSQL);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de Conexión "+e.getMessage(), "Error de Conexión",JOptionPane.ERROR_MESSAGE);
      }
   
   
   }    
    
}

    

    
