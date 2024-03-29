package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class TxtReader extends FileReader {

    protected String extractContent(File file) throws Exception {
        InputStream targetStream = new FileInputStream(file);
        return new String(targetStream.readAllBytes());
    }

}
