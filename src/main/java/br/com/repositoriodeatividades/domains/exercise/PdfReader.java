package br.com.repositoriodeatividades.domains.exercise;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

import java.io.File;

public class PdfReader extends FileReader {

    protected String extractContent(File file) throws Exception {
        String fileContent = "";
        PDDocument document = PDDocument.load(file);
        document.getClass();
        if(!document.isEncrypted()){
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition( true );
            PDFTextStripper Tstripper = new PDFTextStripper();
            fileContent =  Tstripper.getText(document);
        }
        document.close();
        return fileContent.trim();
    }

}
