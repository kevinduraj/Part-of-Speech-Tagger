package kduraj;

import java.io.PrintWriter;

public class App {

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws Exception {

        String[] urls = {

             "http://pacific-design.com/"
           , "http://myhealthcare.com/"
           , "http://find1friend.com"
           , "http://nootrino.com"
        };

        int i=1;
        for (String url : urls) {
            
            PageTagger pageTagger = new PageTagger();
            String sourceText = pageTagger.getText(url);            
            String taggedText = pageTagger.tagText(sourceText);
            System.out.println("taggedText = " + taggedText);
            
            PrintWriter out = new PrintWriter("src/main/java/output/url" + i++ + ".txt");
            
            out.println(taggedText);
            out.close();
        }

    }
    /*--------------------------------------------------------------------------------------------*/
}
