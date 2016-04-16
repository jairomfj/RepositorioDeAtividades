package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.usecases.activities.create.CreateActivity;
import br.com.repositoriodeatividades.usecases.activities.create.vo.CreateActivityParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    CreateActivity createActivity;

    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public void createActivity(HttpServletRequest request) {

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

            createActivity.execute(createActivityParametersList);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] extractTags(String[] tags) {

        if(StringUtils.isEmpty(tags)) {
            return null;
        }

        return tags[0].split(",");

    }


}
