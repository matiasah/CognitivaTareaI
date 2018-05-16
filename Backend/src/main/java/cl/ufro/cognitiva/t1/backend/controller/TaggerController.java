package cl.ufro.cognitiva.t1.backend.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaggerController {
    
    @GetMapping("tagger")
    public List<String> index() {
        
        List<String> taggers = new ArrayList<>();
        
        File folder = new File("models");
        File [] files = folder.listFiles();
        
        for (int i = 0; i < files.length; i++) {
            
            taggers.add( files[i].getName() );
            
        }
        
        return taggers;
        
    }
    
    @PostMapping("tagger")
    public List<List<String []>> taggerize(HashMap<String, String> map) {
        
        MaxentTagger tagger = new MaxentTagger("models/" + map.get("tagger"));
        
        List<List<HasWord>> sentences = MaxentTagger.tokenizeText( new StringReader( map.get("string") ) );
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
