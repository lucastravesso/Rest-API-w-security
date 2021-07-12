package com.projeto.main.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projeto.main.dto.AlunoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ALUNO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicUpdate
@Entity
public class Aluno implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_aluno")
	protected Integer id;
	protected String nome;
	protected String email;
	protected String senha;
	@ManyToOne(targetEntity = Curso.class)
	@JoinColumn(name = "id_curso", foreignKey = @ForeignKey(name = "fk_id_curso_aula"))
	private Curso curso;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Perfil> perfis = new HashSet<>();

	public Aluno (AlunoDTO dto, Curso curso) {

		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.senha = new BCryptPasswordEncoder().encode(dto.getSenha());
		this.curso = curso;
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
