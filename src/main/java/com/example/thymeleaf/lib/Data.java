package com.example.thymeleaf.lib;

import java.util.ArrayList;
import java.util.List;
import com.example.thymeleaf.model.Person;

public class Data 
{
	public static List<Person> persons = new ArrayList<Person>();
	
	static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));
    }
}