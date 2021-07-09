package com.projeto.main.validator;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.main.dto.AlunoDTO;
import com.projeto.main.dto.AulaDTO;
import com.projeto.main.dto.CursoDTO;
import com.projeto.main.entity.Aluno;
import com.projeto.main.entity.Aula;
import com.projeto.main.entity.Curso;
import com.projeto.main.repository.AlunoRepository;
import com.projeto.main.repository.AulaRepository;
import com.projeto.main.repository.CursoRepository;

@Service
public class Validator{

	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private AulaRepository aulaRepository;
 
	public boolean ValidateStudentsFields(AlunoDTO dto)
	{
		Aluno alunoNome = repository.findOneByName(dto.getNome());
		Aluno alunoEmail = repository.findOneByEmail(dto.getEmail());
		
		if(Objects.nonNull(alunoNome) || Objects.nonNull(alunoEmail))
		{
			return true;
		}
		return false;
	}
	
	public boolean ValidateCourseFields(CursoDTO dto)
	{
		Curso cursoNome = cursoRepository.findOneByName(dto.getNome());
		
		if(Objects.nonNull(cursoNome))
		{
			return true;
		}
		return false;
	}	
	
	public boolean ValidateClassFields(AulaDTO dto)
	{
		Aula aulaNome = aulaRepository.findOneByNome(dto.getNome());
		if(Objects.nonNull(aulaNome))
		{
			return true;
		}
		return false;
	}
}
