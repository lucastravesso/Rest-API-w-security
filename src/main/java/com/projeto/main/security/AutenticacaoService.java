package com.projeto.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.main.entity.Aluno;
import com.projeto.main.repository.AlunoRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private AlunoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Aluno usuario = repository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Dados inválidos!"));
		return usuario;
	}
}
