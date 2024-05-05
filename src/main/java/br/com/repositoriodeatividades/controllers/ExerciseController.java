package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.controllers.models.ExerciseOutput;
import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.usecases.exercise.retrieve.RetrieveExercises;
import br.com.repositoriodeatividades.usecases.exercise.retrieve.RetrieveSingleExercise;
import br.com.repositoriodeatividades.usecases.exercise.retrieve.models.vo.ExerciseVO;
import br.com.repositoriodeatividades.usecases.user.FindLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class ExerciseController extends AbstractController {

    @Autowired
    FindLoggedUser findLoggedUser;
    @Autowired
    RetrieveExercises retrieveExercises;
    @Autowired
    RetrieveSingleExercise retrieveSingleExercise;

    @RequestMapping(value = "/exercises", method = RequestMethod.GET)
    public String retrieveExercises(Model model) {
        try {
            UserEntity user = findLoggedUser.find(getCurrentUser());
            List<ExerciseOutput> exerciseOutputs = retrieveExercises.findAllBy(user)
                    .stream()
                    .map(it -> new ExerciseOutput(
                            it.getExternalId(),
                            it.getText(),
                            it.getCreatedAt(),
                            it.getType().toString(),
                            it.isActive(),
                            it.getLevel()
                    )).collect(Collectors.toList());
            model.addAttribute("exercises", exerciseOutputs);
            return "exercises";
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
    }

    @RequestMapping(value = "/exercise/edit", method = RequestMethod.GET)
    public String showView(String id, Model model) {
        try {
            UUID exerciseId = UUID.fromString(id);
            UserEntity currentUser = findLoggedUser.find(getCurrentUser());
            ExerciseVO exercise = retrieveSingleExercise.retrieve(currentUser, exerciseId);
            if (exercise == null) {
                return "redirect:/exercise";
            }
            model.addAttribute("exercise", exercise);
            return "edit-exercise";
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
    }
}
