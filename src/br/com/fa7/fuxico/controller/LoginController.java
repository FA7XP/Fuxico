package br.com.fa7.fuxico.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.model.Usuario;

@Resource
public class LoginController {

	private Result result;
	private UsuarioDao usuarioDao;

	public LoginController( Result result, UsuarioDao usuarioDao) {
		this.result = result;
		this.usuarioDao = usuarioDao;
	}

	@Get("/login")
	public void login() throws Exception {
	}

	@Get("/logout")
	public void logout() throws Exception {
		result.redirectTo(this).login();
	}

	@Post("/login")
	public void login(String login, String senha) throws Exception {
		Usuario usuario = usuarioDao.login(login, senha);

		if( usuario == null ) 
			result.include("erro", "Login ou senha inválidos.");

		result.redirectTo(FuxicarController.class).fuxicar();
	}
}