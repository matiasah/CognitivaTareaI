package cl.ufro.cognitiva.t1.backend.controller;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NERController{
    
    /**
     * 
     * @return 
     */
    @PostMapping("ner")
    public String ner(){
        
        //
        String serializedClassifier = "classifiers/english.muc.7class.distsim.crf.ser.gz";
        
        AbstractSequenceClassifier classifier = CRFClassifier.
                                                getClassifierNoExceptions
                                                (serializedClassifier);
        
        
        
        
       return ""; 
    }
    
    
}