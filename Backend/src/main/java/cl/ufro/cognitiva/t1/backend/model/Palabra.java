package cl.ufro.cognitiva.t1.backend.model;

/**
 *
 * @author matia
 */
public class Palabra {
    
    private String tag;
    private String palabra;
    
    public Palabra() {
        
        this.tag = null;
        this.palabra = null;
        
    }
    
    public Palabra(String tag, String palabra) {
        
        this.tag = tag;
        this.palabra = palabra;
        
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public String getPalabra() {
        return this.palabra;
    }
    
    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
}
