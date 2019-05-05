/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Angel Santiago Trochez
 */
public interface Personas {
     public abstract void setNombreCompleto(String nombreCompleto);
     public abstract void setEdad(int edad);
     public abstract void setSexo(String sexo);
     
     public abstract String getNombreCompleto();
     public abstract int getEdad();
     public abstract String getSexo();
    
}
