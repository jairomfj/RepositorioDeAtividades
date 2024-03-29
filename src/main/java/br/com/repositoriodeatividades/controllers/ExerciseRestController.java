package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.usecases.exercise.create.CreateExercise;
import br.com.repositoriodeatividades.usecases.exercise.delete.DeleteExercise;
import br.com.repositoriodeatividades.usecases.exercise.edit.EditExercise;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.CreateExerciseInput;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.ExercisePlain;
import br.com.repositoriodeatividades.usecases.user.FindLoggedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ExerciseRestController extends AbstractController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CreateExercise createExercise;

    @Autowired
    DeleteExercise deleteExercise;

    @Autowired
    EditExercise editExercise;

    @Autowired
    FindLoggedUser findLoggedUser;

    @RequestMapping(value = "/exercise/create", method = RequestMethod.POST)
    public ResponseEntity<String> create(CreateExerciseInput exercisePlain) {

        HttpStatus status;
        try {
            Long currentUserID = getCurrentUserID();
            exercisePlain.setUserID(currentUserID);
            createExercise.saveExtractedExercise(exercisePlain);
            status = HttpStatus.CREATED;
        } catch (IllegalArgumentException iae) {
            status = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>("", status);
    }

    @RequestMapping(value = "/exercise/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(String externalId) {
        HttpStatus status;
        try {
            User user = getCurrentUser();
            UserEntity userEntity = findLoggedUser.find(user);
            deleteExercise.delete(userEntity, UUID.fromString(externalId));
            status = HttpStatus.OK;
        } catch (IllegalArgumentException iae) {
            status = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>("", status);
    }

    @RequestMapping(value = "/exercise/edit", method = RequestMethod.POST)
    public ResponseEntity<String> edit(ExercisePlain exercisePlain) {
        HttpStatus status;
        String body;
        try {
            getCurrentUser();
            editExercise.edit(exercisePlain);
            status = HttpStatus.OK;
            body = "Exercício atualizado com sucesso.";
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
            status = HttpStatus.BAD_REQUEST;
            body = "Erro ao atualizar o exercício. Verifique se todos os dados foram inseridos corretamente.";
        } catch (Exception e) {
            log.error(e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            body = "Erro ao atualizar o exercício. Tente novamente mais tarde.";
        }
        return new ResponseEntity<>(body, status);
    }
}
