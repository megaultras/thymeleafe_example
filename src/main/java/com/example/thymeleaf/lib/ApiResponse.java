package com.example.thymeleaf.lib;

public class ApiResponse 
{
	public int statusCode;
	
	public String error;
	
	public Object data;
	
	public ApiResponse(int statusCode, String error)
	{
		this.statusCode = statusCode;
		this.data = null;
		this.error = error;
	}
	
	public ApiResponse(Object data)
	{
		this.statusCode = 200;
		this.data = data;
		this.error = "";
	}
}