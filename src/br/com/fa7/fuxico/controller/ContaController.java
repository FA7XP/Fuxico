package br.com.fa7.fuxico.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Usuario;

@Resource
public class ContaController {

	private Result result;
	private UsuarioDao usuarioDao;
	private UsuarioSession usuarioSession;
	private Validator validator;

	public ContaController(Result result, UsuarioDao usuarioDao, UsuarioSession usuarioSession) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.usuarioSession = usuarioSession;
	}

	@Get("/conta")
	public void conta() {
	}

	@Post("/conta")
	public void gravarConta(Usuario usuario) throws Exception {
		if (existeLoginDisponivel(usuario.getLogin())) 
		{
			result.include("erro", "Login j· existente. Favor inserir um novo login.").redirectTo(ContaController.class).conta();
		}
		else
		{
			usuarioDao.save(usuario);
			result.redirectTo(LoginController.class).login();
		}
	}

	public boolean verificarSenhaNome(String senha, String nome) {
		return senha.equalsIgnoreCase(nome);
	}

	public boolean verificarTamanhoMinSenha(String senha) {
		if (senha.length() >= 6) {
			return true;
		}
		return false;
	}

	public boolean existeLoginDisponivel(String login) {
		if (usuarioDao.consultarUsuarioPorLogin(login) != null) {
			// throw new RuntimeException("Login j√° cadastrado.");
			return true;
		} else {
			return false;
		}
	}

}