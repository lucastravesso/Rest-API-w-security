package com.projeto.main.crypt;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Crypt {

	
	//CRIPTOGRAFIA CRIADA COM MD5 NA MAO SEM USAR SERVIÇO DO SPRING DE BCrypt
	
	// MD5 é um sistema fraco de criptografia
	
	public static String crypt(String senha)
	{
		//cria variavel retorno que armazena a senha criptografada
		String retorno = "";
		
		//import do messageDigest que traz funcionalidade de hash
		MessageDigest md;
		//tenta o codigo
		try {
			//define o hash(digest) como MD-5 de criptografia
			md = MessageDigest.getInstance("MD5");
			//cria um hash de bigInteger que vai pegar a senha em bytes
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			//adiciona em hash string a senha e diz que deve ser em hexadecimal o hash
			retorno = hash.toString(16);
			//nao estou tratando erro.....
		}catch(Exception e) {}
		
		return retorno;
		//criado por Lusgas
	}
	
}
