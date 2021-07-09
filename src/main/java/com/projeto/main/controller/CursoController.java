package com.projeto.main.controller;


import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.main.dto.CursoDTO;
import com.projeto.main.entity.Curso;
import com.projeto.main.service.CursoService;

@RequestMapping("/curso")
@RestController
public class CursoController {

	@Autowired
	private CursoService service;
	
	@PostMapping(value = "/inserir-curso")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Curso> insertCurso(@RequestBody @Valid CursoDTO dto) 
	{
		return service.insertCurso(dto);
	}

	@GetMapping(value = "/listar-tudo")
	public List<CursoDTO> listar()
	{
		return service.listar();
	}
	
	@GetMapping(value = "/listar-curso/{id}")
	public ResponseEntity<CursoDTO> procurar(@PathVariable("id") Integer id)
	{
		return service.procurar(id);
	}
	
	@DeleteMapping("/deletar-curso/{id}")
	public ResponseEntity<?> deletar(@PathVariable("id") Integer id) 
	{
		return service.deletar(id);
	}
	
	@PutMapping("/atualizar-curso/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody @Valid CursoDTO dto)
	{
		return service.atualizar(id, dto);
	}
	
	
}
