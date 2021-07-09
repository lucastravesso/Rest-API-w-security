package com.projeto.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.main.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{
	
	

}
