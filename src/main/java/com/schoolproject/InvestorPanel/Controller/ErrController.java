package com.schoolproject.InvestorPanel.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrController implements ErrorController {

	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return PATH;
    }
    
    @GetMapping(PATH)
    public String errorPage() {
        return PATH;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
	

}