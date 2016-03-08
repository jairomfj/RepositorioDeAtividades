package br.com.repositoriodeatividades.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = { "/", "/home" })
    public String showView() {
        return "home";
    }

}