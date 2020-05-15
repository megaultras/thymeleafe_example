package com.example.thymeleaf.controller;

import com.example.thymeleaf.form.PersonForm;
import com.example.thymeleaf.model.Persons;
import com.example.thymeleaf.model.PersonsRepository;
import com.example.thymeleaf.lib.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
 
@Controller
@RequestMapping("/persons")
public class PersonsController 
{
	@Autowired
	private PersonsRepository personsRepository;
	
    @RequestMapping(
		value = { "/list" }, 
		method = RequestMethod.GET
	)
    public String personList(Model model) 
    {
    	Iterable<Persons> list = personsRepository.findAll();
        model.addAttribute("list", list);
 
        return "persons/list";
    }
 
//  Add methods ===========================================================================
    @RequestMapping(
		value = { "/add" }, 
		method = RequestMethod.GET
	)
    public String showAddPersonPage(Model model) 
    {
    	Persons form = new Persons();
        model.addAttribute("form", form);
        
        return "persons/add";
    }
    
    @RequestMapping(
		value = { "/add" }, 
		method = RequestMethod.POST
	)
    public String savePerson(Model model, @ModelAttribute("form") Persons form) 
    {
        String name = form.getName();
        String email = form.getEmail();
 
        if (name != null 
    		&& name.length() > 0
            && email != null 
            && email.length() > 0
        ) {
        	personsRepository.save(form);
        	
            return "redirect:/persons/list";
        }
 
        model.addAttribute("errorMessage", "Name and E-mail are required");
        
        return "persons/add";
    }
    
//  Edit methods ============================================================================
    @RequestMapping(
		value = "/edit/{id}", 
		method = RequestMethod.GET
	)
    public String showEditdPersonPage(Model model, @PathVariable("id") int id) 
    {
    	Persons person = new Persons();
    	
    	Optional<Persons> result = personsRepository.findById(id);
    	if (result.isPresent()) {
    		person = result.get();
    	} else {
    		model.addAttribute("errorMessage", "Person not found");
    	}
    	
        model.addAttribute("form", person);
 
        return "persons/edit";
    }
    
    @RequestMapping(
		value = { "/edit/{id}" }, 
		method = RequestMethod.POST
	)
    public String editPerson(Model model, //
		@ModelAttribute("form") Persons form, //
        @PathVariable("id") int id) 
    {
    	String name = form.getName();
        String email = form.getEmail();
        
        if (name != null 
    		&& name.length() > 0
            && email != null 
            && email.length() > 0
        ) {
        	personsRepository.save(form);
        	
            return "redirect:/persons/list";
        }
        
        Optional<Persons> result = personsRepository.findById(id);
        if (!result.isPresent()) {
    		model.addAttribute("errorMessage", "Person not found");
    		
    		return "persons/edit";
    	}
        
        personsRepository.save(form);
        
        return "redirect:/persons/list";
    }
    
//  Delete methods =================================================================================
    @RequestMapping(
		value = "/delete/{id}", 
		method = RequestMethod.GET
	)
    public String showDeletedPersonPage(Model model, //
		@PathVariable("id") int id) 
    {	
    	Persons person = new Persons();
    	
    	Optional<Persons> result = personsRepository.findById(id);
    	if (result.isPresent()) {
    		person = result.get();
    	} else {
    		model.addAttribute("errorMessage", "Person not found");
    	}
    	
    	model.addAttribute("person", person);
    	
        return "persons/delete";
    }
    
    @RequestMapping(
		value = { "/delete/{id}" }, 
		method = RequestMethod.POST
	)
    public String deletePerson(Model model, //
        @PathVariable("id") int id, //
        @RequestParam("confirm") int confirm) 
    {	
    	if (confirm == 1) {
    		Optional<Persons> result = personsRepository.findById(id);
        	if (result.isPresent()) {
        		Persons person = result.get();
        		personsRepository.delete(person);
        	} else {
        		return "redirect:/persons/delete" + id;
        	}
    		
        	return "redirect:/persons/list";
        }
    	
    	return "redirect:/persons/list";
    }
}