package cl.ufro.cognitiva.t1.backend.controller;

import cl.ufro.cognitiva.t1.backend.model.Oracion;
import cl.ufro.cognitiva.t1.backend.model.Palabra;
import cl.ufro.cognitiva.t1.backend.model.Texto;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author matia
 */
@RestController
public class TaggerController {
    
    /**
     * Metodo que recive la oracion y aplica el etiquetado de categorias 
     * gramaticales
     * @param texto
     * @return 
     */
    @PostMapping("tagger")
    public List<Oracion> taggerize(
            @RequestBody Texto texto
        ) {
        
        // Utiliza el archivo para el etiquetado segun el parametro que se recive
        MaxentTagger tagger = new MaxentTagger("models/english-bidirectional-distsim.tagger");
        
        // Obtener una lista de oraciones
        List<List<HasWord>> sentences = MaxentTagger.tokenizeText( new StringReader( texto.getContenido() ) );
        
        // Lista de oraciones
        List<Oracion> oraciones = new ArrayList<>();
        
        for ( List<HasWord> sentence : sentences ) {
            
            // Obtener tags de una oración
            List<TaggedWord> tSentence = tagger.tagSentence(sentence);
            
            // Lista de tags para la oración
            Oracion oracion = new Oracion();
            
            // Por cada tag
            for ( TaggedWord tWord : tSentence ) {
                
                // Crear una palabra nueva
                Palabra palabra = new Palabra( tWord.tag(), tWord.word() );
                
                // Agregar palabra a oracion
                oracion.getPalabras().add( palabra );
                
            }
            
            // Agregar oracion a la lista de oraciones
            oraciones.add(oracion);
            
        }
        
        // Enviar datos
        return oraciones;
        
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws Exception 
     */
    @PostMapping("tagger-file")
    public List<Oracion> taggerize(
            @RequestParam("file") MultipartFile file
        ) throws Exception {
        
        if (!file.isEmpty()) {
            
            Texto texto = new Texto();
            
            texto.setContenido( new String( file.getBytes() ) );
            
            return this.taggerize( texto );
            
        }
        
        return null;
    }
    
}
