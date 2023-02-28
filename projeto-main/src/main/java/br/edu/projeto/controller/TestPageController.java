package br.edu.projeto.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
@RequestScoped
@Named
public class TestPageController {
	private String addString;
	
	
	
	public TestPageController() {
		this.addString = "HEY";
	}


	public String getAddString() {
		return addString;
	}


	public void setAddString(String addString) {
		this.addString = addString;
	}


	public void add(){
		this.addString = "CONSEGUI PORRA";
	}

}
