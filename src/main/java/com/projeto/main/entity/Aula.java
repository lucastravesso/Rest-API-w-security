package com.projeto.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projeto.main.dto.AulaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Aula {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_aula")
	private Integer id;
	@Column(name = "nome_aula")
	private String nome;
	private int modulo;
	private int semestre;
	@ManyToOne(targetEntity = Curso.class)
	@JoinColumn(name = "id_curso", foreignKey = @ForeignKey(name = "fk_id_curso"))
	private Curso curso;
	
	public Aula (AulaDTO dto, Curso curso) 
	{	
		this.nome = dto.getNome();
		this.modulo = dto.getModulo();
		this.semestre = dto.getSemestre();
		this.curso = curso;
	}
}
