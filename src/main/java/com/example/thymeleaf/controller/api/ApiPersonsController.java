package com.example.thymeleaf.controller.api;

import com.example.thymeleaf.lib.Data;
import com.example.thymeleaf.lib.Response;
import com.example.thymeleaf.model.Person;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
 
@RestController
@RequestMapping("/api/persons")
public class ApiPersonsController 
{
    @RequestMapping(
		value = {"/list" }, 
		method = RequestMethod.GET,
		produces = "application/json"
	)
    public Response personList() 
    {
    	Response response = new Response(Data.persons);
    	
        return response;
    }
    
    @RequestMapping(
		value = {"/view/{index}" }, 
		method = RequestMethod.GET,
		produces = "application/json"
	)
    public Response personView(@PathVariable("index") int index) 
    {
    	Response response;
    	
    	if (index < Data.persons.size()) {
    		Person person = Data.persons.get(index);
    		response = new Response(person);
    	} else {
    		response = new Response(404, "Person not found");
    	}
    	
        return response;
    }
}