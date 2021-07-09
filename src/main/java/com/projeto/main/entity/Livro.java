package com.projeto.main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.projeto.main.dto.LivroDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicUpdate
@Entity
public class Livro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String nome;
	protected String autor;
	protected String editora;
	protected int alugado;
	
	public Livro(LivroDTO dto)
	{
		this.nome = dto.getNome();
		this.autor = dto.getAutor();
		this.editora = dto.getEditora();
		this.alugado = dto.getAlugado();
	}
	
}
