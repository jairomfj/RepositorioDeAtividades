package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class WordDocReader extends FileReader {

    protected String extractContent(File file) throws Exception {
        InputStream inputStrm = new FileInputStream(file);
        WordExtractor wordExtractor = new WordExtractor(inputStrm);
        return wordExtractor.getText();
    }

}
