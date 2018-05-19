/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ufro.cognitiva.t1.backend.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Batman
 */
public class TagOracion {
        
    //Variable idioma que representa el idioma del texto que se introdujo
    @JsonProperty(value = "tagger", required= true)
    private String idioma;
    
    //Variable texto que representa el texto para aplicar los tag
    @JsonProperty(value = "texto", required = true)
    private String texto;

    public String getIdioma() {
        return idioma;
    }

    public String getTexto() {
        return texto;
    }            
    
}
