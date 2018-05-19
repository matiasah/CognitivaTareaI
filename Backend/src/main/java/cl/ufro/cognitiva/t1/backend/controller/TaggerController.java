package cl.ufro.cognitiva.t1.backend.controller;

import cl.ufro.cognitiva.t1.backend.controller.model.TagOracion;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaggerController {
    
    
    /**
     * Metodo para retornar una lista con los nombres de los archivos que contienen
     * los tagger
     * @return Lista con nombres de archivos tagger
     */
    @GetMapping("tagger")
    public List<String> index() {
        
        List<String> taggers = new ArrayList<>();
        
        //Lee el contenido de la carpeta models
        File folder = new File("models");
        File [] files = folder.listFiles();
        
        for (int i = 0; i < files.length; i++){
            taggers.add( files[i].getName() );            
        }
        
        //retorna una lista con el nombre de todos los tag
        return taggers;
        
    }
    
    
    /**
     * Metodo que recive la oracion y aplica el etiquetado de categorias 
     * gramaticales
     * @param oracion Modelo que representa el tagg a utilizar y la oracion para
     * aplicar el etiuetado
     * @return 
     */
    @PostMapping("tagger")
    public List<List<String []>> taggerize(@RequestBody TagOracion oracion) {
        
        //Utiliza el archivo para el etiquetado segun el parametro que se recive
        MaxentTagger tagger = new MaxentTagger("models/" + oracion.getIdioma());
                
        
        List<List<HasWord>> sentences = MaxentTagger.tokenizeText( new StringReader( oracion.getTexto() ) );
        List<List<String []>> tSentences = new ArrayList<>();
        
        for ( List<HasWord> sentence : sentences ) {
            List<TaggedWord> tSentence = tagger.tagSentence(sentence);
            List<String[]> tsubSentences = new ArrayList<>();
            for ( TaggedWord tWord : tSentence ) {
                tsubSentences.add( new String[] { tWord.word(), tWord.tag() } );
            }
            tSentences.add(tsubSentences);
        }
        
        return tSentences;
        
    }
    
}
