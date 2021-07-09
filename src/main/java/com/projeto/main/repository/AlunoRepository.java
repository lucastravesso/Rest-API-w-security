package com.projeto.main.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.main.entity.Aluno;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

	@Query("SELECT a from Aluno a where a.id = ?1")
	Aluno findOneById(Long idUsuario);
	
	@Query("SELECT a from Aluno a where a.id = ?1")
	Aluno findOneById(int idUsuario);
	
	@Query("SELECT a from Aluno a where a.nome like %?1%")
	Aluno findOneByName(String nome);
	
	@Query("SELECT a from Aluno a where a.senha like %?1%")
	Aluno findOneBySenha(String senha);
	
	@Query("SELECT a from Aluno a where a.email like %?1%")
	Aluno findOneByEmail(String email);
	
	@Query("SELECT a from Aluno a where a.email like %?1%")
	Optional<Aluno> findByEmail(String email);
	
	List<Aluno> findAllByCurso_Id(Integer id);

}
