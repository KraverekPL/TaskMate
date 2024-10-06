package io.github.kraverekpl.TaskMate.controllers.web;

import io.github.kraverekpl.TaskMate.controllers.advice.GlobalExceptionHandlerProcessing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@GlobalExceptionHandlerProcessing
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}
