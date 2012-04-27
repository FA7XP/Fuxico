package br.com.fa7.fuxico.controller;

import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.model.Usuario;

public class UsuarioController {
	
	private UsuarioDao usuariodao;
	
	
	public boolean verificarSenhaNome(String senha, String nome) {
		return senha.equalsIgnoreCase(nome);
	}
	
	public boolean verificarTamanhoMinSenha(String senha) {
		if (senha.length() >= 6) {
			return true;
		}
		return false;
	}
	
	public boolean verificarLoginDisponivel(String login) {
		Usuario usuarioTeste = usuariodao.load(login);
		if (login.equalsIgnoreCase(usuarioTeste.getLogin())) {
			return true;
		}
		return false;
	}
}
