package com.projeto.main.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.main.entity.Aula;


@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer>{

	@Query("SELECT a from Aula a where a.id = ?1")
	Aula findOneById(Integer id);
	
	@Query("SELECT a from Aula a where a.nome_aula like %?1%")
	Aula findOneByNome(String nome);
	
	List<Aula> findAllByCurso_Id(Integer id);
	
}
