package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.usecases.exercise.CreateExercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ExerciseController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CreateExercise createExercise;

    @RequestMapping(value = "/exercise", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam(value="exercise-label") String exerciseLabel, @RequestParam(value="option-label") String[] optionLabels) {

        HttpStatus status;
        String body = "";
        try {
            createExercise.saveFileExtractedExercise(exerciseLabel, optionLabels);
            status = HttpStatus.CREATED;
        } catch (IllegalArgumentException iae) {
            status = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(body, status);
    }
}
