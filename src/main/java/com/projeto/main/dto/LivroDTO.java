package com.projeto.main.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
public class LivroDTO {

	protected int id;
	@NotNull @NotEmpty @Length(max = 50)
	protected String nome;
	@NotNull @NotEmpty @Length(max = 50)
	protected String autor;
	@NotNull @NotEmpty @Length(max = 50)
	protected String editora;
	@NotNull
	protected int alugado;
}
