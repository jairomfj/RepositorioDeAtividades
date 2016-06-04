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
    public String createActivity(HttpServletRequest request, Model model) {
        try {
            User currentUser = getCurrentUser();

            List<CreateActivityParameters> createActivityParametersList = new ArrayList<>();
            for(int i = 0; ; i++) {
                String id = request.getParameter("id_" + i);

                if(id == null) break;

                String level = request.getParameter("level_" + i);
                String amount = request.getParameter("amount_" + i);
                String[] tags = extractTags(request.getParameterValues("tags_" + i));
                createActivityParametersList.add(new CreateActivityParameters(Integer.parseInt(id), level, Integer.parseInt(amount), tags, currentUser.getUsername()));
            }

            List<Exercise> exercises = createActivity.execute(createActivityParametersList);

            model.addAttribute("exercises", exercises);
            log.info("Activity has been created");
            return "document";
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO improve error handling
        return "";
    }

    private String[] extractTags(String[] tags) {
        if(StringUtils.isEmpty(tags)) {
            return null;
        }
        return tags[0].toUpperCase().split(",");
    }




}
