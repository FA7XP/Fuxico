package br.com.fa7.fuxico.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Usuario;

@Resource
public class ContaController {

	private Result result;
	private UsuarioDao usuarioDao;
	private UsuarioSession usuarioSession;

	public ContaController(Result result, UsuarioDao usuarioDao, UsuarioSession usuarioSession) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.usuarioSession = usuarioSession;
	}

	@Get("/conta")
	public void conta(Usuario usuario) throws Exception {
		gravarConta(usuario); 
	}

	@Post("/conta")
	public void gravarConta(Usuario usuario) throws Exception {
		if (usuarioDao.isLoginExiste(usuario.getLogin()))
			result.include("erroConta", "Login já existente. Favor inserir um novo login.").redirectTo(LoginController.class).login();
		else if (verificarSenhaNome(usuario.getLogin(), usuario.getSenha()))
			result.include("erroConta", "A Senha não pode ser igual ao login.").redirectTo(LoginController.class).login();
		else if (verificarTamanhoMinSenha(usuario.getSenha()))
			result.include("erroConta", "A Senha não pode ser inferior a 6 caracteres.").redirectTo(LoginController.class).login();
		else{
			usuarioDao.save(usuario);
			usuarioSession.setUsuario(usuario);
			result.include("ok", "Conta do FUXIQUEIRO criada com sucesso.").redirectTo(LoginController.class).login();
		}
	}

	public boolean verificarSenhaNome(String nome, String senha) {
		return senha.equalsIgnoreCase(nome);
	}

	public boolean verificarTamanhoMinSenha(String senha) {
		return senha.length() < 6;
	}
}