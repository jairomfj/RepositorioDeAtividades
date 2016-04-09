package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.usecases.ImportExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    @Autowired
    ImportExercise importExercise;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile, Model model) {
        model.addAttribute("exercises", importExercise.execute(uploadFile));
        return "file/upload";
    }
}
