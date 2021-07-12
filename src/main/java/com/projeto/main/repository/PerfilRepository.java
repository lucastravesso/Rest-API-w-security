package com.projeto.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.main.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

	
}
