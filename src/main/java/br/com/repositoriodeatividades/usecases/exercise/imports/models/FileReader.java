package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import br.com.repositoriodeatividades.usecases.exercise.utils.exceptions.InvalidFormatException;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.Readable;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

abstract class FileReader implements Readable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String read(MultipartFile multipartFile) throws Exception {

        if(multipartFile == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        File file = new File(multipartFile.getOriginalFilename());
        try {
            createFile(multipartFile);
            String content = extractContent(file);
            return content;
        } catch(OfficeXmlFileException oxfe) {
            throw new InvalidFormatException("This format is not accepted: " + multipartFile.getContentType());
        } catch (Exception e) {
            throw e;
        } finally {
            file.delete();
        }
    }

    private void createFile(MultipartFile multipartFile) throws Exception {
        if(multipartFile.isEmpty()) {
            throw new IllegalArgumentException("File is empty: " + multipartFile.getOriginalFilename());
        }
        byte[] bytes = multipartFile.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(multipartFile.getOriginalFilename())));
        stream.write(bytes);
        stream.close();
    }

    abstract protected String extractContent(File file) throws Exception;


}
