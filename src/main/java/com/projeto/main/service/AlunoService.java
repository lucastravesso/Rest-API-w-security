package com.projeto.main.service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
import com.projeto.main.entity.Perfil;
import com.projeto.main.repository.AlunoRepository;
import com.projeto.main.repository.CursoRepository;
import com.projeto.main.repository.PerfilRepository;
import com.projeto.main.validator.Validator;


@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private Validator valid;

	private Mapper mapper = new DozerBeanMapper();

	public ResponseEntity<Aluno> insertAluno(AlunoDTO dto) {
	
		Curso curso = cursoRepository.findOneById(dto.getCurso().getId());
		List<Perfil> perfil = perfilRepository.findAll();
		Set<Perfil> role = perfil.stream().filter(p -> p.getNome().equals("ROLE_DEFAULT")).collect(Collectors.toSet());
		boolean validate = valid.ValidateStudentsFields(dto);

		if (!validate) {
			if (Objects.nonNull(curso)) {
				
				Aluno aluno = new Aluno(dto, curso);
				
				aluno.setPerfis(role);
				
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

			aluno.get().setPerfis(dto.getPerfil());
			aluno.get().setNome(dto.getNome());
			aluno.get().setEmail(dto.getEmail());
			aluno.get().setSenha(dto.getSenha());
			aluno.get().setCurso(curso);

			return ResponseEntity.ok().build();

		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<?> atualizarPerfil(Integer id, Integer id_perfil)
	{
		Optional<Aluno> aluno = repository.findById(id);
		Optional<Perfil> perfil = perfilRepository.findById(id_perfil);
		Set<Perfil> p = new HashSet<>();
		p.add(perfil.get());
		if (aluno.isPresent()) {
			aluno.get().setPerfis(p);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	public List<AlunoDTO> listar() {

		List<Aluno> aluno = repository.findAll();

		return aluno.stream().map(m -> {
			AlunoDTO dto = mapper.map(m, AlunoDTO.class);
			dto.setPerfil(m.getPerfis());
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
