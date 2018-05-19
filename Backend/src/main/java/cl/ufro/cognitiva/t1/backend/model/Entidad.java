/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ufro.cognitiva.t1.backend.model;

/**
 * Clase para modelar el tipo de la entidad (persona, organizacion, lugar) y 
 * el nombre de la entidad en si.
 * @author Batman
 */
public class Entidad {
    
    private String nombre;
    private String tipo;
    
    public Entidad() {
        this.nombre = null;
        this.tipo = null;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
