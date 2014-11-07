package kduraj;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageTagger {

    List<String> list = new ArrayList();
    /*--------------------------------------------------------------------------------------------*/

    public String getText(String url) {

        String sourceText = null;
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
            sourceText = doc.text();

        } catch (IOException ex) {

            System.out.println("Trying Java native http");
            MyHttpConnection http = new MyHttpConnection();
            sourceText = http.sendGet(url);
            sourceText = Jsoup.parse(sourceText).text();

        }
        sourceText = cleanText(sourceText);

        return sourceText;
    }
    /*--------------------------------------------------------------------------------------------
     Part of Speech Tagger
     */

    public String tagText(String text1) throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        MaxentTagger tagger = new MaxentTagger("src/main/java/models/english-left3words-distsim.tagger");
        List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new StringReader(text1));

        for (List<HasWord> sentence : sentences) {
            List<TaggedWord> tSentence = tagger.tagSentence(sentence);
            list.add(Sentence.listToString(tSentence, false));
        }

        for (String str1 : list) {
            String[] arrayList = str1.split(" ");

            for (String str2 : arrayList) {

                sb.append(str2).append(" ");
            }
        }

        return sb.toString();
    }
    /*--------------------------------------------------------------------------------------------*/

    public String cleanText(String dirty) {

        dirty = dirty.replaceAll("\\s+", " ").trim(); // shrink spaces
        dirty = dirty.replaceAll("[,|\"|\\|(|)|.|?]", "");

        List<String> list = new ArrayList();

        String[] arrayList = dirty.split(" ");
        
        for (String str2 : arrayList) {
            list.add(str2);
        }
        
        return list.toString();
    }
    /*--------------------------------------------------------------------------------------------*/

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    /*--------------------------------------------------------------------------------------------*/
}
