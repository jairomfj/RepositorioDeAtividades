package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;

public class PdfReader extends FileReader {

    protected String extractContent(File file) throws Exception {
        String fileContent = "";
        PDDocument document = Loader.loadPDF(file);
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
