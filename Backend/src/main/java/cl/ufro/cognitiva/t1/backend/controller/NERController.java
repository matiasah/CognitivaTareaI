package cl.ufro.cognitiva.t1.backend.controller;

import cl.ufro.cognitiva.t1.backend.model.Entidad;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.util.Triple;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Entidad> list(@RequestBody String texto) {

        // Instanciar classifier
        AbstractSequenceClassifier              classifier      = CRFClassifier.getClassifierNoExceptions("classifiers/english.muc.7class.distsim.crf.ser.gz");
        
        // Obtener lista de tripletas (del texto)
        List<Triple<String, Integer, Integer>>  entidades       = classifier.classifyToCharacterOffsets(texto);
        
        // Crear lista de entidades
        ArrayList<Entidad>                      listaEntidades  = new ArrayList();

        // Por cada tripleta
        for (Triple<String, Integer, Integer> entidad : entidades) {
            
            // Crear una entidad
            Entidad ent = new Entidad();
            
            // Fijarle nombre
            ent.setNombre( texto.substring(entidad.second, entidad.third) );
            ent.setTipo( entidad.first );
            
            // Guardar en lista
            listaEntidades.add( ent );
            
        }

        return listaEntidades;
        
    }

}
