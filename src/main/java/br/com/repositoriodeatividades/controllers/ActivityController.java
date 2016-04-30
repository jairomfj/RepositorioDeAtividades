package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.usecases.activities.create.CreateActivity;
import br.com.repositoriodeatividades.usecases.activities.create.vo.CreateActivityParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ActivityController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CreateActivity createActivity;

    @RequestMapping(value = "/createActivity", method = RequestMethod.POST)
    public String createActivity(HttpServletRequest request, Model model) {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal == "anonymousUser") {
            return "";
        }

        List<CreateActivityParameters> createActivityParametersList = new ArrayList<>();
        try {
            int i = 0;
            while(true) {
                String id = request.getParameter("id_" + i);

                if(id == null) break;

                String level = request.getParameter("level_" + i);
                String ammount = request.getParameter("ammount_" + i);
                String[] tags = extractTags(request.getParameterValues("tags_" + i));
                createActivityParametersList.add(new CreateActivityParameters(Integer.parseInt(id), level, Integer.parseInt(ammount), tags));
                i++;
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

    public String[] extractTags(String[] tags) {
        if(StringUtils.isEmpty(tags)) {
            return null;
        }
        return tags[0].toUpperCase().split(",");
    }


}
