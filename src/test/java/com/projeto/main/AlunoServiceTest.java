package com.projeto.main;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.main.dto.AlunoDTO;
import com.projeto.main.service.AlunoService;

class AlunoServiceTest extends MainApplicationTests{

	@Autowired
	private AlunoService service;
	
	@Test
	void testListarTodosOsAlunos() {
		List<AlunoDTO> dto = service.listar();
		assertEquals(dto.size(), 5); 
	}

	@Test 
	void testDeIgualdadeDeIdENome(){
		List<AlunoDTO> dto = service.listar();
		assertEquals(dto.stream().findFirst().get().getNome(), "Lucas");
		assertEquals(dto.stream().findFirst().get().getId(), 1);
		assertNotEquals(dto.stream().findAny().get().getId(), null);
	}
	
	@Test
	void testandoDeletarAluno() {
		List<AlunoDTO> dto = service.listar();
		service.deletar(dto.stream().filter(p -> p.getId() == 5).iterator().next().getId());
		
		List<AlunoDTO> dtoAtt = service.listar();
		assertEquals(dtoAtt.size(), 4);
	}
	

}
