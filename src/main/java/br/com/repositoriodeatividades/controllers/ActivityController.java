package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.usecases.activities.create.CreateActivity;
import br.com.repositoriodeatividades.usecases.activities.create.models.vo.CreateActivityParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ActivityController extends AbstractController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CreateActivity createActivity;


    @RequestMapping(value = "/activity/create", method = RequestMethod.GET)
    public String createActivityView() { return "activity"; }

    @RequestMapping(value = "/document", method = RequestMethod.GET)
    public String activityView() { return "document"; }

    @RequestMapping(value = "/activity/create", method = RequestMethod.POST)
    public String createActivity(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {

        Integer status;
        String message;

        try {
            User currentUser = getCurrentUser();

            List<CreateActivityParameters> createActivityParametersList = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                String id = request.getParameter("id_" + i);

                if(id == null) break;

                String level = request.getParameter("level_" + i);
                String amount = request.getParameter("amount_" + i);
                String[] tags = extractTags(request.getParameterValues("tags_" + i));
                if(tags != null) {
                    createActivityParametersList.add(new CreateActivityParameters(Integer.parseInt(id), level, Integer.parseInt(amount), tags, currentUser.getUsername()));
                }
            }

            List<Exercise> exercises = createActivity.execute(createActivityParametersList);

            if(exercises.size() > 0) {
                model.addAttribute("exercises", exercises);
                return "document";
            }
            status = 400;
            message = "Ops, você não possui nenhum exercício.";
        } catch (IllegalArgumentException iae) {
            status = 400;
            message = "Preencha valores válidos para a geração das atividades.";
        } catch (Exception e) {
            status = 500;
            message = "Ocorreu um erro, tente novamente.";
        }

        redirectAttributes.addFlashAttribute("status", status);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:create";
    }

    private String[] extractTags(String[] tags) {
        if(StringUtils.isEmpty(tags[0])) {
            return null;
        }
        return tags[0].toUpperCase().split(",");
    }

}
