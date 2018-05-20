package cl.ufro.cognitiva.t1.backend.controller;

import cl.ufro.cognitiva.t1.backend.model.Entidad;
import cl.ufro.cognitiva.t1.backend.model.Texto;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.util.Triple;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class NERController {

    /**
     * Metodo que recive un texto por parametros y retorna una lista con todas
     * las entidades encontradas
     *
     * @param texto
     * @return
     */
    @PostMapping("ner")
    public List<Entidad> list(
            @RequestBody Texto texto
        ) {

        // Instanciar classifier
        AbstractSequenceClassifier              classifier      = CRFClassifier.getClassifierNoExceptions("classifiers/english.muc.7class.distsim.crf.ser.gz");
        
        // Obtener lista de tripletas (del texto)
        List<Triple<String, Integer, Integer>>  entidades       = classifier.classifyToCharacterOffsets(texto.getContenido());
        
        // Crear lista de entidades
        ArrayList<Entidad>                      listaEntidades  = new ArrayList();

        // Por cada tripleta
        for (Triple<String, Integer, Integer> entidad : entidades) {
            
            // Crear una entidad
            Entidad ent = new Entidad();
            
            // Fijarle nombre
            ent.setNombre( texto.getContenido().substring(entidad.second, entidad.third) );
            ent.setTipo( entidad.first );
            
            // Guardar en lista
            listaEntidades.add( ent );
            
        }

        return listaEntidades;
        
    }
    
    @PostMapping("ner-file")
    public List<Entidad> list(
            @RequestParam("file") MultipartFile file
        ) throws Exception {
        
        if (file != null && !file.isEmpty()) {
            
            Texto texto = new Texto();
            
            texto.setContenido( new String( file.getBytes() ) );
            
            return this.list( texto );
            
        }
        
        return null;
        
    }

}
