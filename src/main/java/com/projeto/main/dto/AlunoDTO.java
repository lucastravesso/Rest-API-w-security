package com.projeto.main.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.projeto.main.entity.Perfil;

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
public class AlunoDTO {

	private int id;
	@NotNull @NotEmpty @Length(max = 50)
	private String nome;
	@NotNull @NotEmpty @Length(max = 75)
	private String email;
	@NotNull @NotEmpty @Length(max = 30)
	private String senha;
	private CursoDTO curso;	
	private List<Perfil> perfil;

}
