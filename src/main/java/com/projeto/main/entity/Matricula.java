package com.projeto.main.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Matricula {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@NotNull
	@ManyToOne(targetEntity = Aluno.class)
	@JoinColumn(name = "id_aluno", foreignKey = @ForeignKey(name = "fk_id_aluno"))
	protected Aluno aluno;
	@NotNull
	@ManyToOne(targetEntity = Aula.class)
	@JoinColumn(name = "id_aula", foreignKey = @ForeignKey(name = "fk_id_aula"))
	protected Aula aula;

	
	public Matricula(Aluno aluno, Aula aula)
	{
		this.aluno = aluno;
		this.aula = aula;
	}
}
