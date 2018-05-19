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
public class NERController {

    /**
     * Metodo que recive un texto por parametros y retorna una lista con todas
     * las entidades encontradas
     *
     * @return
     */
    @PostMapping("/ner")
    public List<EntidadModel> ner(@RequestParam(value = "texto") String texto) {

        //
        String serializedClassifier = "classifiers/english.muc.7class.distsim.crf.ser.gz";

        AbstractSequenceClassifier classifier = CRFClassifier.
                                                getClassifierNoExceptions(serializedClassifier);

        List<Triple<String, Integer, Integer>> entidades = classifier.
                                                            classifyToCharacterOffsets(texto);

        
        ArrayList<EntidadModel> tipoEntidad = new ArrayList();

        //variables auxiliar para guardar los valores de las posiciones de la 
        //entidad dentro del texto
        int begin, end;

        //Agregar cada par "entidad", "tipo entidad" a un arraylist
        for (Triple<String, Integer, Integer> entidad : entidades) {
            begin = entidad.second;
            end = entidad.third;
            tipoEntidad.add(new EntidadModel(texto.substring(begin, end),
                    entidad.first));
        }

        return tipoEntidad;
    }

}
