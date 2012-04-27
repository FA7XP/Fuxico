package br.com.fa7.fuxico.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;

@Resource
public class IndexController {

	private Result result;
	private UsuarioDao usuarioDao;
	private UsuarioSession usuarioSession;

	public IndexController( Result result, UsuarioDao usuarioDao, UsuarioSession usuarioSession ) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.usuarioSession = usuarioSession;
	}

	@Get("/")
	public void index() throws Exception {
		if( usuarioSession.getUsuario() == null ) 
			result.redirectTo(LoginController.class).login();
	}

}