package com.projeto.main.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.projeto.main.dto.AlunoCursoDTO;
import com.projeto.main.dto.AlunoDTO;
import com.projeto.main.entity.Aluno;
import com.projeto.main.entity.Curso;
import com.projeto.main.repository.AlunoRepository;
import com.projeto.main.repository.CursoRepository;
import com.projeto.main.validator.Validator;


@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private Validator valid;

	private Mapper mapper = new DozerBeanMapper();

	public ResponseEntity<Aluno> insertAluno(AlunoDTO dto) {
	
		Curso curso = cursoRepository.findOneById(dto.getCurso().getId());
		boolean validate = valid.ValidateStudentsFields(dto);

		if (!validate) {
			if (Objects.nonNull(curso)) {
				
				Aluno aluno = new Aluno(dto, curso);
				repository.save(aluno);
				return ResponseEntity.ok(aluno);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	public ResponseEntity<?> atualizarAluno(Integer id, AlunoDTO dto) {

		Optional<Aluno> aluno = repository.findById(id);
		Curso curso = cursoRepository.findOneById(dto.getCurso().getId());

		if (aluno.isPresent() && Objects.nonNull(curso)) {

			//aluno.get().setRole(dto.getRole());
			aluno.get().setNome(dto.getNome());
			aluno.get().setEmail(dto.getEmail());
			aluno.get().setSenha(dto.getSenha());
			aluno.get().setCurso(curso);

			return ResponseEntity.ok().build();

		}
		return ResponseEntity.notFound().build();
	}
	/*
	public void atualizarAcesso(Integer id, RolesDTO dto) throws NotFoundException {

		Aluno aluno = repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado . ."));

		aluno.setRole(dto.getRole());
	}*/
	
	public List<AlunoDTO> listar() {

		List<Aluno> aluno = repository.findAll();
		return aluno.stream().map(m -> {
			AlunoDTO dto = mapper.map(m, AlunoDTO.class);
			return dto;
		}).collect(Collectors.toList());

	}

	public ResponseEntity<AlunoDTO> listarPorId(Integer id) {

		Optional<Aluno> aluno = repository.findById(id);
		if (aluno.isPresent()) {
			return ResponseEntity.ok(mapper.map(aluno.get(), AlunoDTO.class));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<?> deletar(Integer id) {

		Optional<Aluno> aluno = repository.findById(id);

		if (aluno.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	public ResponseEntity<List<AlunoCursoDTO>> listarAlunosPorCurso(Integer id)
	{
		List<Aluno> aluno = repository.findAllByCurso_Id(id);
		if(Objects.nonNull(aluno))
		{
			List<AlunoCursoDTO> res = aluno.stream().map(m -> {
				AlunoCursoDTO dto = mapper.map(m, AlunoCursoDTO.class);
				return dto;
			}).collect(Collectors.toList());
			
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.notFound().build();
	}
	public ResponseEntity<?> logar(String nome,String senha)
	{
		Aluno user = repository.findOneByName(nome);
		
		if(Objects.nonNull(user))
		{
			if(BCrypt.checkpw(senha, user.getSenha()))
			{
				return ResponseEntity.ok(user);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		}
		return ResponseEntity.notFound().build();
	}

}
