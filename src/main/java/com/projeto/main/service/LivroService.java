package com.projeto.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.main.dto.LivroDTO;
import com.projeto.main.entity.Livro;
import com.projeto.main.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	private Mapper mapper = new DozerBeanMapper();
	
	public ResponseEntity<Livro> inserirLivro(LivroDTO dto)
	{
		Livro livro = new Livro(dto);
		livroRepository.save(livro);
		return ResponseEntity.ok().build();
	}
	public List<LivroDTO> listarTodos()
	{
		List<Livro> livro = livroRepository.findAll();
		return livro.stream().map(m ->{
			LivroDTO dto = mapper.map(m, LivroDTO.class);
			return dto;
		}).collect(Collectors.toList());
	}
	public ResponseEntity<LivroDTO> procurarLivroUnico(Integer id)
	{
		Optional<Livro> livro = livroRepository.findById(id);
		if(livro.isPresent())
		{
			return ResponseEntity.ok(mapper.map(livro.get(), LivroDTO.class));
		}
		return ResponseEntity.notFound().build();
	}
	public ResponseEntity<?> deletarLivro(Integer id)
	{
		Optional<Livro> livro = livroRepository.findById(id);
		if(livro.isPresent())
		{
			livroRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	public ResponseEntity<?> atualizarLivro(LivroDTO dto, Integer id)
	{
		Optional<Livro> livro = livroRepository.findById(id);
		if(livro.isPresent())
		{
			livro.get().setNome(dto.getNome());
			livro.get().setAutor(dto.getAutor());
			livro.get().setEditora(dto.getEditora());
			livro.get().setAlugado(dto.getAlugado());
			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
