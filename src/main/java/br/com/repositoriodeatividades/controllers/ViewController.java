package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.usecases.user.FindLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController extends AbstractController {

    @Autowired FindLoggedUser findLoggedUser;

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String homeView() {
        return "home";
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String indexView() {
        try {
            findLoggedUser.find(getCurrentUser());
            return "home";
        } catch (Exception e) {
            e.printStackTrace();
        return "index";
        }
    }

}
