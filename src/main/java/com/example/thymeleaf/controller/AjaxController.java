package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;
 
@Controller
@RequestMapping("/ajax")
public class AjaxController 
{
	/**
	 * Text response without template
	 * @return String
	 */
	@RequestMapping(
		value = { "/text" }, 
		method = RequestMethod.GET
	)
	@ResponseBody
    public String textResponse() 
    {
        return "This is text response";
    }
	
	/**
	 * JSON response without template
	 * @return String
	 */
	@RequestMapping(
		value = { "/json" }, 
		method = RequestMethod.GET,
		produces = "application/json"
	)
	@ResponseBody
    public String jsonResponse() 
    {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "Artem");
		jsonObj.put("age", 44);
		
        return jsonObj.toString();
    }
}