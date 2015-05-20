package br.com.caelum.livraria.bean;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TemaBean {
	
	private String tema = "aristo";

	public String getTema() {
		return tema;
	}

	public List<String> getTemas(){
		return Arrays.asList("aristo","black-tie","blitzer","bluesky","casablanca");
	}
	
}
