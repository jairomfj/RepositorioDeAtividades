package br.com.repositoriodeatividades.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String uploadFileView() {
        return "file/upload";
    }

    @RequestMapping(value = "/uploadFile2", method = RequestMethod.GET)
    public String uploadFileView2() {
        return "file/upload2";
    }

    @RequestMapping(value = { "/", "/home" })
    public String homeView() {
        return "home";
    }

    @RequestMapping("/login")
    public String loginView() { return "login"; }

}
