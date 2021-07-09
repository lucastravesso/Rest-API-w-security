package com.projeto.main.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatriculaDTO {

	private Integer id;
	private AlunoDTO aluno;
	private AulaDTO aula;

	
}
