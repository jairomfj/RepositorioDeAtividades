package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.usecases.PdfExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {

    @Autowired
    PdfExtractor pdfExtractor;

    @RequestMapping("/file")
    public String showView() {
            return "file/file";
    }

}
