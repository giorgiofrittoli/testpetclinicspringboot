package it.frigir.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/","index.html","index"})
    public String index(){
        return "index";
    }

    @GetMapping("oups")
    public String oupsHandler(){
        return "notImplemented";

    }
}
