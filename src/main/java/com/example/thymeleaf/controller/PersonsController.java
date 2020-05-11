package com.example.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
 
import com.example.thymeleaf.form.PersonForm;
import com.example.thymeleaf.model.Person;
import com.example.thymeleaf.lib.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
 
@Controller
@RequestMapping("/persons")
public class PersonsController 
{
    @Value("${error.message}")
    private String errorMessage;
    
    @RequestMapping(
		value = { "/list" }, 
		method = RequestMethod.GET
	)
    public String personList(Model model) 
    {
        model.addAttribute("persons", Data.persons);
 
        return "persons/list";
    }
 
//  Add methods ===========================================================================
    @RequestMapping(
		value = { "/add" }, 
		method = RequestMethod.GET
	)
    public String showAddPersonPage(Model model) 
    {
        PersonForm form = new PersonForm();
        model.addAttribute("form", form);
        
        return "persons/add";
    }
    
    @RequestMapping(
		value = { "/add" }, 
		method = RequestMethod.POST
	)
    public String savePerson(Model model, @ModelAttribute("form") PersonForm form) 
    {
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
 
        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName);
            Data.persons.add(newPerson);
 
            return "redirect:/persons/list";
        }
 
        model.addAttribute("errorMessage", errorMessage);
        
        return "persons/add";
    }
    
//  Edit methods ============================================================================
    @RequestMapping(
		value = "/edit/{index}", 
		method = RequestMethod.GET
	)
    public String showEditdPersonPage(Model model, //
		@ModelAttribute("form") PersonForm form, //
		@PathVariable("index") int index) 
    {	
    	if (index < Data.persons.size()) {
    		Person person = Data.persons.get(index);
    		form.setFirstName(person.getFirstName());
    		form.setLastName(person.getLastName());
    		
    		model.addAttribute("index", index);
    	} else {
    		model.addAttribute("errorMessage", "Person not found");
    	}
    	
        model.addAttribute("form", form);
 
        return "persons/edit";
    }
    
    @RequestMapping(
		value = { "/edit/{index}" }, 
		method = RequestMethod.POST
	)
    public String editPerson(Model model, //
        @ModelAttribute("personForm") PersonForm personForm, //
        @PathVariable("index") int index) 
    {
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
        
        if (firstName == null 
    		|| firstName.length() == 0 
            || lastName == null 
            || lastName.length() == 0
        ) {
        	model.addAttribute("errorMessage", errorMessage);
        	
        	return "persons/edit";
        }
        
        Person person;
        if (index < Data.persons.size()) {
    		person = Data.persons.get(index);
    	} else {
    		model.addAttribute("errorMessage", "Person not found");
    		
    		return "persons/edit";
    	}
        
        person.setFirstName(firstName);
        person.setLastName(lastName);
        
        Data.persons.set(index, person);
        
        return "redirect:/persons/list";
    }
    
//  Delete methods =================================================================================
    @RequestMapping(
		value = "/delete/{index}", 
		method = RequestMethod.GET
	)
    public String showDeletedPersonPage(Model model, //
		@PathVariable("index") int index) 
    {	
    	if (index < Data.persons.size()) {
    		Person person = Data.persons.get(index);
    		model.addAttribute("person", person);
    		model.addAttribute("index", index);
    	} else {
    		model.addAttribute("errorMessage", "Person not found");
    	}
    	
        return "persons/delete";
    }
    
    @RequestMapping(
		value = { "/delete/{index}" }, 
		method = RequestMethod.POST
	)
    public String deletePerson(Model model, //
        @PathVariable("index") int index, //
        @RequestParam("confirm") int confirm) 
    {	
    	if (confirm == 1) {
    		Data.persons.remove(index);
        	
        	return "redirect:/persons/list";
        }
    	
    	return "redirect:/persons/list";
    }
}