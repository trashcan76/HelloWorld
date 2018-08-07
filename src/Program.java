import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.util.LinkedList;

import name.fraser.neil.plaintext.diff_match_patch;


public class Program {
	
    public static void main(String[] args) throws IOException {  
    	// get text from base64 encoded PDFs
    	// certutil -encode data.txt tmp.b64 && findstr /v /c:- tmp.b64 > data.b64certutil -encode data.txt tmp
    	String text1 = GetText("C:\\Users\\wzfis\\eclipse-workspace\\HelloWorld\\pdfs\\drumsinworship.txt");
        String text2 = GetText("C:\\Users\\wzfis\\eclipse-workspace\\HelloWorld\\pdfs\\drumsinworship2.txt");
                
        // https://github.com/google/diff-match-patch/wiki/Language:-Java
        diff_match_patch dmp = new diff_match_patch();        
        LinkedList<diff_match_patch.Diff> diff = dmp.diff_main(text1, text2);
        dmp.diff_cleanupSemantic(diff);
        
        // construct a crude HTML report
        try (PrintWriter out = new PrintWriter("output.htm")) {
            String html = dmp.diff_prettyHtml(diff);
        	out.println(html);
        }
    }

    public static String GetText(String path) throws IOException {
    	byte[] base64raw = Files.readAllBytes(Paths.get(path));    	
    	byte[] base64decoded = java.util.Base64.getMimeDecoder().decode(base64raw);
    	    
    	// https://www.programcreek.com/java-api-examples/?class=org.apache.pdfbox.pdmodel.PDDocument&method=load
    	PDDocument document = PDDocument.load(base64decoded);
        PDFTextStripper stripper = new PDFTextStripper();
        String documentText = stripper.getText(document);
        document.close();

        return documentText;
    }    
}
