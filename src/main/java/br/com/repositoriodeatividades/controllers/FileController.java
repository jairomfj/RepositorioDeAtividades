package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.usecases.exercise.ImportMultipleChoiceExercise;
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
    ImportMultipleChoiceExercise importMultipleChoiceExercise;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String showView() {
        return "file/upload";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile, Model model) {
        model.addAttribute("exercises", importMultipleChoiceExercise.execute(uploadfile));
        return "file/upload";
    }
}
