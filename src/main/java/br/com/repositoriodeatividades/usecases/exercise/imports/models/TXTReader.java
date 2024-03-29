package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;


public class TXTReader extends FileReader {

    protected String extractContent(File file) throws Exception {
        InputStream inputStrm = new FileInputStream(file);
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStrm, writer, "UTF-8");
        return writer.toString();
    }

}
