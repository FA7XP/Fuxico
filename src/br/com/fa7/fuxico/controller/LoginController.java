package br.com.fa7.fuxico.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Usuario;

@Resource
public class LoginController {

	private Result result;
	private UsuarioDao usuarioDao;
	private UsuarioSession usuarioSession;

	public LoginController( Result result, UsuarioDao usuarioDao, UsuarioSession usuarioSession ) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.usuarioSession = usuarioSession;
	}

	@Get("/login")
	public void login() throws Exception {
		if( usuarioSession.getUsuario() != null ) 
			result.redirectTo(IndexController.class).index();
	}

	@Get("/logout")
	public void logout() throws Exception {
		usuarioSession.setUsuario(null);
		result.redirectTo(this).login();
	}

	@Post("/login")
	public void login(String nome, String senhaCriptografada) throws Exception {
		Usuario usuario = usuarioDao.login(nome, senhaCriptografada);
		if( usuario == null ) {
			result.include("erro", "Login ou senha inválidos.").redirectTo(this).login();
		} else {
			result.redirectTo(IndexController.class).index();
		}
	}
}