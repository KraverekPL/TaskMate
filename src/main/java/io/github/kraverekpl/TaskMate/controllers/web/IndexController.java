package io.github.kraverekpl.TaskMate.controllers.web;

import io.github.kraverekpl.TaskMate.controllers.advice.GlobalExceptionHandlerProcessing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@GlobalExceptionHandlerProcessing
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
