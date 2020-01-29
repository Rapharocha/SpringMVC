package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;


@Controller  // Obrigatório em Spring
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	
    
	//Para instanciar e pode usar o repositório.
	@Autowired
	private Titulos titulos;
	
	
	@RequestMapping("/novo") //Quando digitar no browser vai entrar nesse método.
	public ModelAndView novo() {
		
		ModelAndView mv= new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		
		
//Pra deixar esse cara Disponivel no html || retorna um array do StatusTitulo.
		mv.addObject("todosStatusTitulo", StatusTitulo.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	
	//Carrega nome da view e mais informações 
	     //    V
	//public ModelAndView salvar(@Validated Titulo titulo , Errors errors, RedirectAttributes attributes ) {
	  public String salvar(@Validated Titulo titulo , Errors errors, RedirectAttributes attributes) {	
		//CONSTRUTOR RECEBE A VIEW QUE VAI RETORNAR
	   //ModelAndView mv= new ModelAndView("CadastroTitulo");
	    
	    if(errors.hasErrors()) {
	    	return CADASTRO_VIEW;
	    }
		
		//SALVAR NO BANCO DE DADOS
		titulos.save(titulo);
	//ModelAndView mv2 = new ModelAndView("redirect:/titulos/novo");
				
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!!!");		
				
		//atributo mensagem está disponivel da página do html
		//e mandará para o span após utilizar thymeleaf
	//mv.addObject("mensagem", "Título salvo com sucesso!!!");
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos",todosTitulos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo")Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
}