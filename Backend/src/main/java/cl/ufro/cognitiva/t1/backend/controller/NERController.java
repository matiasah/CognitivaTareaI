package cl.ufro.cognitiva.t1.backend.controller;

import cl.ufro.cognitiva.t1.backend.controller.model.EntidadModel;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import static edu.stanford.nlp.time.SUTime.TemporalOp.IN;
import edu.stanford.nlp.util.Triple;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NERController{
    
    /**
     * Metodo que recive un texto por parametros y retorna una lista con todas las
     * entidades encontradas
     * @return 
     */
    @PostMapping("/ner")
    public List<EntidadModel> ner(@RequestParam(value = "texto") String texto){
        
        //
        String serializedClassifier = "classifiers/english.muc.7class.distsim.crf.ser.gz";
        
        AbstractSequenceClassifier classifier = CRFClassifier.
                                                getClassifierNoExceptions
                                                (serializedClassifier);
        
        List<Triple<String, Integer, Integer>> entidades = classifier.
                                                           classifyToCharacterOffsets(texto);
        
        ArrayList<EntidadModel> tipoEntidad = new ArrayList();
        
        //variables auxiliar para guardar los valores de las posiciones de la 
        //entidad dentro del texto
        int begin, end;
        
        for (int i = 0; i < entidades.size(); i++) {
            begin = entidades.get(i).second;
            end = entidades.get(i).third;
            tipoEntidad.add(new EntidadModel(texto.substring(begin,end),entidades.get(i).first));
        }
        
       return tipoEntidad;
    }
    
    
}