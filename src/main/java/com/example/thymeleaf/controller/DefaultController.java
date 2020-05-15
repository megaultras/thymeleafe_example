package com.example.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
 
import com.example.thymeleaf.form.PersonForm;
import com.example.thymeleaf.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
 
@Controller
public class DefaultController 
{
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;
 
    @RequestMapping(
		value = { "/", "/index" }, 
		method = RequestMethod.GET
	)
    public String index(Model model) 
    {
        model.addAttribute("message", message);
 
        return "default/index";
    }
}