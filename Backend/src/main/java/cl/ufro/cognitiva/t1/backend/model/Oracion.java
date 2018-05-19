package cl.ufro.cognitiva.t1.backend.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matia
 */
public class Oracion {
    
    private List<Palabra> palabras;
    
    public Oracion() {
        this.palabras = new ArrayList<>();
    }
    
    public List<Palabra> getPalabras() {
        return this.palabras;
    }
    
}
