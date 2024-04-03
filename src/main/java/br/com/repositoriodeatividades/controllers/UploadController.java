package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.usecases.exercise.imports.ImportExercise;
import br.com.repositoriodeatividades.usecases.exercise.imports.models.ExtractedExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController extends AbstractController {

    @Autowired
    ImportExercise importExercise;


    @RequestMapping(value = "/exercise/upload", method = RequestMethod.GET)
    public String uploadFileView() {
        return "upload";
    }

    @RequestMapping(value = "/exercise/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile, RedirectAttributes redirectAttributes) {

        Integer status;
        String message;
        List<ExtractedExercise> exercises = new ArrayList<>();

        try {
            exercises = importExercise.execute(uploadFile);
            status = 200;
            message = "Exercícios extraídos com sucesso.";
        } catch (IllegalArgumentException e) {
            status = 400;
            message = e.getMessage();
        } catch (Exception e) {
            status = 500;
            message = "Ocorreu um erro, tente novamente.";
        }

        redirectAttributes.addFlashAttribute("exercises", exercises);
        redirectAttributes.addFlashAttribute("status", status);
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:upload";
    }

    @RequestMapping(value = "/exercise/upload-v2", method = RequestMethod.GET)
    public String uploadFileViewV2() {
        return "upload-v2";
    }

    @RequestMapping(value = "/exercise/upload-v2", method = RequestMethod.POST)
    public String uploadFileV2(@RequestParam("uploadFile") MultipartFile uploadFile, RedirectAttributes redirectAttributes) {

        Integer status;
        String message;
        List<ExtractedExercise> exercises = new ArrayList<>();

        try {
            exercises = importExercise.execute(uploadFile);
            status = 200;
            message = "Exercícios extraídos com sucesso.";
        } catch (IllegalArgumentException e) {
            status = 400;
            message = e.getMessage();
        } catch (Exception e) {
            status = 500;
            message = "Ocorreu um erro, tente novamente.";
        }

        redirectAttributes.addFlashAttribute("exercises", exercises);
        redirectAttributes.addFlashAttribute("status", status);
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:upload-v2";
    }
}
