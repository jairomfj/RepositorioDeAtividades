package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.usecases.exercise.retrieve.RetrieveExercises;
import br.com.repositoriodeatividades.usecases.exercise.retrieve.RetrieveSingleExercise;
import br.com.repositoriodeatividades.usecases.exercise.retrieve.models.vo.ExerciseVO;
import br.com.repositoriodeatividades.usecases.user.FindLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExerciseController extends AbstractController {

    @Autowired FindLoggedUser findLoggedUser;
    @Autowired RetrieveExercises retrieveExercises;
    @Autowired
    RetrieveSingleExercise retrieveSingleExercise;

    @RequestMapping(value = "/exercise", method = RequestMethod.GET)
    public String retrieveExercises(Model model) {
        try {
            User user = findLoggedUser.find(getCurrentUser());
            var exercises = retrieveExercises.findAllBy(user);
            model.addAttribute("exercises", exercises);
            return "exercises";
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
    }

    @RequestMapping(value = "/exercise/edit", method = RequestMethod.GET)
    public String showView(String id, Model model) {
        try {
            Long exerciseId = Long.parseLong(id);
            User currentUser = findLoggedUser.find(getCurrentUser());
            ExerciseVO exercise = retrieveSingleExercise.retrieve(currentUser, exerciseId);
            if(exercise == null) {
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
