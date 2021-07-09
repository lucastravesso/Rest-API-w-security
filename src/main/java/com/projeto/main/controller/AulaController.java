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

import com.projeto.main.dto.AulaCursoDTO;
import com.projeto.main.dto.AulaDTO;
import com.projeto.main.entity.Aula;
import com.projeto.main.service.AulaService;

@RequestMapping("/aula")
@RestController
public class AulaController {

	@Autowired
	private AulaService service;
	
	@PostMapping("/inserir-aula")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Aula> inserirAula(@RequestBody @Valid AulaDTO dto)
	{
		return service.insertAula(dto);
	}
	@PutMapping("/atualizar-aula/{id}")
	@Transactional
	public ResponseEntity<?> atualizarAula(@PathVariable Integer id, @RequestBody @Valid AulaDTO dto)
	{
		return service.atualizarAula(id, dto);
	}
	@GetMapping("/listar-tudo")
	public List<AulaDTO> listarAulas()
	{
		return service.listar();
	}
	@GetMapping("/listar-aula/{id}")
	public ResponseEntity<AulaDTO> listarPorId(@PathVariable Integer id)
	{
		return service.listarPorId(id);
	}
	@DeleteMapping("/deletar-aula/{id}")
	public ResponseEntity<?> deletar(@PathVariable Integer id)
	{
		return service.deletar(id);
	}
	@GetMapping("/listar-aula/curso/{id}")
	public ResponseEntity<List<AulaCursoDTO>> listarPorCurso(@PathVariable Integer id)
	{
		return service.listarAulasPorCurso(id);
	}
}
