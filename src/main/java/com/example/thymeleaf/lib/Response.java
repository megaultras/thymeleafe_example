package com.example.thymeleaf.lib;

public class Response 
{
	public int statusCode;
	
	public String error;
	
	public Object data;
	
	public Response(int statusCode, String error)
	{
		this.statusCode = statusCode;
		this.data = null;
		this.error = error;
	}
	
	public Response(Object data)
	{
		this.statusCode = 200;
		this.data = data;
		this.error = "";
	}
}