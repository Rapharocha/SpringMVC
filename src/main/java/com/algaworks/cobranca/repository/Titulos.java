package com.algaworks.cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.cobranca.model.Titulo;
                                             //Entidade que o Repositorio vai trabalhar
                                             //   V    // Tipo de dado do ID dessa entidade.
public interface Titulos extends JpaRepository<Titulo, Long>{

}
