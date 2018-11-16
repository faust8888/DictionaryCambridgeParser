package com.faust8888.cambridge.logic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

    @RequestMapping(value = "/dictionary")
    public String resolve(){
        return "index.html";
    }

}
