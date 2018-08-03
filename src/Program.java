import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.util.ArrayList;
import java.util.LinkedList;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;


public class Program {
	
    public static void main(String[] args) throws IOException {
        String text1 = GetText("C:/Users/wzfis/Documents/Drums in Worship.pdf");
        String text2 = GetText("C:/Users/wzfis/Documents/Drums in Worship 2.pdf");
                
        // https://github.com/google/diff-match-patch/wiki/Language:-Java
        diff_match_patch dmp = new diff_match_patch();
        LinkedList<diff_match_patch.Diff> diff = dmp.diff_main(text1, text2);
        
        ArrayList<String> output = new ArrayList<String>();
        for (Diff d : diff) {
        	System.out.println(d);
        	output.add("<div class='" + d.operation + "'>" + d.text.replaceAll("\r\n",  "</br>") + "</div>");
        }
        
        Path path = Paths.get("output.htm");
        Files.write(path, output, Charset.forName("UTF-8"));
    }

    public static String GetText(String path) throws IOException {
    	// https://www.programcreek.com/java-api-examples/?class=org.apache.pdfbox.pdmodel.PDDocument&method=load
        PDDocument document = PDDocument.load(new File(path));
        PDFTextStripper stripper = new PDFTextStripper();
        String documentText = stripper.getText(document);
        document.close();

        return documentText;
    }
}
