package com.algaworks.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;


@Controller  // Obrigatório em Spring
@RequestMapping("/titulos")
public class TituloController {
    
	//Para instanciar e pode usar o repositório.
	@Autowired
	private Titulos titulos;
	
	
	@RequestMapping("/novo") //Quando digitar no browser vai entrar nesse método.
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	
	//Carrega nome da view e mais informações 
	     //    V
	public ModelAndView salvar(Titulo titulo) {
		//SALVAR NO BANCO DE DADOS
		titulos.save(titulo);
		
		//CONSTRUTOR RECEBE A VIEW QUE VAI RETORNAR
		ModelAndView mv= new ModelAndView("CadastroTitulo");
		
		//atributo mensagem está disponivel da página do html
		//e mandará para o span após utilizar thymeleaf
		mv.addObject("mensagem", "Título salvo com sucesso!!!");
		return mv;
	}
}