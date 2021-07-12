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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.main.dto.AlunoCursoDTO;
import com.projeto.main.dto.AlunoDTO;
import com.projeto.main.entity.Aluno;
import com.projeto.main.service.AlunoService;


@RequestMapping("/aluno")
@RestController
public class AlunoController {

	@Autowired
	private AlunoService service;
	
	@PostMapping(value = "/inserir-aluno")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Aluno> insertAluno(@RequestBody @Valid AlunoDTO dto) 
	{
		return service.insertAluno(dto);
	}
 
	@GetMapping(value = "/listar-tudo")
	public List<AlunoDTO> listar()
	{
		return service.listar();
		
	}
	
	@GetMapping(value = "/listar-aluno/{id}")
	public ResponseEntity<AlunoDTO> procurar(@PathVariable("id") Integer id)
	{
		return service.listarPorId(id);
	}
	
	@DeleteMapping("/deletar-aluno/{id}")
	public ResponseEntity<?> deletar(@PathVariable("id") Integer id) 
	{
		return service.deletar(id);
	}

	@PutMapping("/atualizar-aluno/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody @Valid AlunoDTO dto)
	{
		return service.atualizarAluno(id, dto);
	}
	@GetMapping("/listar-aluno/curso/{id}")
	public ResponseEntity<List<AlunoCursoDTO>> listarAlunosCurso(@PathVariable Integer id)
	{
		return service.listarAlunosPorCurso(id);
	}
	@PutMapping("/logar")
	public ResponseEntity<?> loganAluno(@RequestParam(value ="nome", required = false) String nome,@RequestParam(value ="senha", required = false) String senha)
	{
		return service.logar(nome, senha);
	}
	@PutMapping("/mudar-acesso/{id}")
	@Transactional
	public ResponseEntity<?> mudarAcesso(@PathVariable Integer id, @RequestParam(value = "id_perfil", required = true) Integer id_perfil)
	{
		return service.atualizarPerfil(id, id_perfil);
	}
	
}
