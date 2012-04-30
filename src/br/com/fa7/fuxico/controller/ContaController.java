package br.com.fa7.fuxico.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.model.Usuario;

@Resource
public class ContaController {

	private Result result;
	private UsuarioDao usuarioDao;

	public ContaController(Result result, UsuarioDao usuarioDao) {
		this.result = result;
		this.usuarioDao = usuarioDao;
	}

	@Get("/conta")
	public void conta() {
	}

	@Post("/conta")
	public void gravarConta(Usuario usuario) throws Exception {
		if (usuarioDao.isLoginExiste(usuario.getLogin()))
			result.include("erro", "Login já existente. Favor inserir um novo login.").redirectTo(ContaController.class).conta();

		if (verificarSenhaNome(usuario.getLogin(), usuario.getSenha()))
			result.include("erro", "A Senha não pode ser igual ao login.").redirectTo(ContaController.class).conta();

		if (verificarTamanhoMinSenha(usuario.getSenha()))
			result.include("erro", "A Senha não pode ser inferior a 6 caracteres.").redirectTo(ContaController.class).conta();

		usuarioDao.save(usuario);
		result.redirectTo(LoginController.class).login();
	}

	public boolean verificarSenhaNome(String nome, String senha) {
		return senha.equalsIgnoreCase(nome);
	}

	public boolean verificarTamanhoMinSenha(String senha) {
		return senha.length() < 6;
	}
}