package br.com.fa7.fuxico.controller;

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

	@Post("/conta")
	public void gravarConta(Usuario usuario, String confirmarSenha) throws Exception {
		
		
		if (!usuario.getSenha().equals(confirmarSenha))
			result.include("usuario", usuario).include("erroConta", "Confirmação da senha inválida.").redirectTo(LoginController.class).login();
		else if(usuarioDao.isLoginExiste(usuario.getLogin()))
			result.include("usuario", usuario).include("erroConta", "Login já existente. Favor inserir um novo login.").redirectTo(LoginController.class).login();
		else if (verificarSenhaNome(usuario.getLogin(), usuario.getSenha()))
			result.include("usuario", usuario).include("erroConta", "A Senha não pode ser igual ao login.").redirectTo(LoginController.class).login();
		else if (verificarTamanhoMinSenha(usuario.getSenha()))
			result.include("usuario", usuario).include("erroConta", "A Senha não pode ser inferior a 6 caracteres.").redirectTo(LoginController.class).login();
		else{
			usuarioDao.save(usuario);
			usuarioSession.setUsuario(usuario);
			result.include("login", usuario.getLogin()).include("ok", "Conta do FUXIQUEIRO criada com sucesso.").redirectTo(LoginController.class).login();
		}
	}

	public boolean verificarSenhaNome(String nome, String senha) {
		return senha.equalsIgnoreCase(nome);
	}

	public boolean verificarTamanhoMinSenha(String senha) {
		return senha.length() < 6;
	}
	
	public void excluir(Usuario usuario) throws Exception {
		String login = usuario.getLogin();
		usuarioDao.delete(usuario);
		result.include("excluir", "Conta \"" + login + "\" excluida com sucesso.").redirectTo(LoginController.class).logout();
	}
}