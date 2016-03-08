package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.daos.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showView() {
        return "login";
    }
}
