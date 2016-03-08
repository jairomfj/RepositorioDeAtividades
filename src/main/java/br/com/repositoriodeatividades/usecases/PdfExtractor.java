package br.com.repositoriodeatividades.usecases;

import br.com.repositoriodeatividades.domains.interfaces.Extractable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Component("pdfExtractor")
public class PdfExtractor implements Extractable {

    @Override
    public String read(File file) throws IOException {
        String fileContent = null;

        PDDocument document = PDDocument.load(file);
        document.getClass();
        if(!document.isEncrypted()){
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition( true );
            PDFTextStripper Tstripper = new PDFTextStripper();
            fileContent =  Tstripper.getText(document);
        }
        document.close();
        return fileContent;
    }
}
