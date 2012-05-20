package br.com.fa7.fuxico.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Usuario;

@Resource
public class GoogleController {

	private Result result;
	private UsuarioDao usuarioDao;
	private UsuarioSession usuarioSession;

	public GoogleController(Result result, UsuarioDao usuarioDao,	UsuarioSession usuarioSession) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.usuarioSession = usuarioSession;
	}
	
	@Get("/google/{login}/{senha}/{nome}/{email}")
	public void google(String login, String senha, String nome, String email) {
		
		Usuario usuario = usuarioDao.login(login.toLowerCase(), senha);
		
		if(usuario == null)	{
			usuario = new Usuario();
			usuario.setLogin(login.toLowerCase());
			usuario.setNome(nome);
			usuario.setSenha(senha);
			usuario.setEmail(email);
			usuarioDao.save(usuario);
		}
		
		usuarioSession.setUsuario(usuario);
		result.redirectTo(FuxicarController.class).fuxicar(usuario, true);
	}
}
