/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ufro.cognitiva.t1.backend.controller.model;

/**
 * Clase para modelar el tipo de la entidad (persona, organizacion, lugar) y 
 * el nombre de la entidad en si.
 * @author Batman
 */
public class EntidadModel {
    
    private String entidad;
    private String tipo_entidad;

    public EntidadModel(String entidad, String tipo_entidad) {
        this.entidad = entidad;
        this.tipo_entidad = tipo_entidad;
    }
    
    public EntidadModel(){
        
    }

    public String getEntidad() {
        return entidad;
    }

    public String getTipo_entidad() {
        return tipo_entidad;
    }        
    
}
