package com.example.thymeleaf.model;

import org.springframework.data.repository.CrudRepository;
import com.example.thymeleaf.model.Persons;

public interface PersonsRepository extends CrudRepository<Persons, Integer>
{
	
}