package com.projeto.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.main.dto.LivroDTO;
import com.projeto.main.entity.Livro;
import com.projeto.main.service.LivroService;

@RequestMapping("/livro")
@RestController
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@PostMapping("/inserir-livro")
	public ResponseEntity<Livro> inserirLivro(@RequestBody @Valid LivroDTO dto)
	{
		return livroService.inserirLivro(dto);
	}
	@GetMapping("/listar-tudo")
	public List<LivroDTO> listarTodos()
	{
		return livroService.listarTodos();
	}
	@GetMapping("/listar-id/{id}")
	public ResponseEntity<LivroDTO> procurarLivroUnico(@PathVariable("id") Integer id)
	{
		return livroService.procurarLivroUnico(id);
	}
	@DeleteMapping("/deletar-livro/{id}")
	public ResponseEntity<?> deletarLivro(@PathVariable("id") Integer id)
	{
		return livroService.deletarLivro(id);
	}
	@PutMapping("/atualizar-aluno/{id}")
	public ResponseEntity<?> atualizarLivro(@RequestBody @Valid LivroDTO dto,@PathVariable("id") Integer id)
	{
		return livroService.atualizarLivro(dto, id);
	}
}
