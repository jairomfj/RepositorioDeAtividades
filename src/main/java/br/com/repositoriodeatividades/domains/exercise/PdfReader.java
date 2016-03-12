package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.domains.interfaces.Readable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;


@Component("pdfExtractor")
public class PdfReader implements Readable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void createFile(MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public String read(MultipartFile multipartFile)  {
        createFile(multipartFile);
        File file = new File(multipartFile.getOriginalFilename());
        String content = extractContent(file);
        file.delete();
        return content;
    }

    public String extractContent(File file) {
        String fileContent = "";
        try {
            PDDocument document = PDDocument.load(file);
            document.getClass();
            if(!document.isEncrypted()){
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition( true );
                PDFTextStripper Tstripper = new PDFTextStripper();
                fileContent =  Tstripper.getText(document);
            }
            document.close();
            log.info("File successfully read");
        } catch (Exception e) {
            log.error("Error reading file: " + e.getStackTrace().toString());
            e.printStackTrace();
        }
        return fileContent.trim();

    }
}
