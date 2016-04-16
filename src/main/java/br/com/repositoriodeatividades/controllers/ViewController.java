package br.com.repositoriodeatividades.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String uploadFileView() {
        return "upload";
    }

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homeView() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView() { return "login"; }

    @RequestMapping(value = "/createActivity", method = RequestMethod.GET)
    public String createActivityView() { return "activity"; }

}
