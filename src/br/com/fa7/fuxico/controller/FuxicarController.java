package br.com.fa7.fuxico.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Fuxico;
import br.com.fa7.fuxico.model.Usuario;

@Resource
public class FuxicarController {

	private Result result;
	private FuxicoDao fuxicoDao;
	private UsuarioDao usuarioDao;
	private UsuarioSession usuarioSession;

	public FuxicarController(Result result, FuxicoDao fuxicoDao, UsuarioDao usuarioDao,	UsuarioSession usuarioSession) {
		this.result = result;
		this.fuxicoDao = fuxicoDao;
		this.usuarioDao = usuarioDao;
		this.usuarioSession = usuarioSession;
	}
	
	@Get("/fuxicar")
	public void fuxicar(Usuario usuario, boolean exibirFuxicar) {

		if(usuario == null)
			usuario = usuarioSession.getUsuario();
		
		List<Fuxico> fuxicos = fuxicoDao.listaFuxicosByUsuario(usuario.getId());
		result.include("fuxicos", fuxicos).include("usuario", usuario).include("exibirFuxicar", exibirFuxicar);
	}

	@Get("/fuxicos/{usuario.id}")
	public void fuxicos(Usuario usuario) {
		if(usuario != null && usuario.getId() != null){
			Collection<Fuxico> fuxicos = fuxicoDao.listaFuxicosByUsuario(usuario.getId());
			result.use(json()).from(fuxicos, "fuxicos").include("usuario").serialize();
		}
	}
	
	@Get("/link/{usuarioId}")
	public void fuxicos(Long usuarioId) {
		if(usuarioId != null){
			Usuario usuario = usuarioDao.load(usuarioId); 
			boolean exibirFuxico = usuarioSession.getUsuario().getId().equals(usuarioId);
			result.redirectTo(this).fuxicar(usuario, exibirFuxico);
		}
	}

	@Post("/fuxicar/{usuario.id}")
	public void fuxicar(String mensagem, Usuario usuario) {
		if (mensagem == null || mensagem.isEmpty()) {
			result.include("erroMensagem", "Digite uma mensagem!");
		} else if (mensagem.length() > 255) {
			result.include("erroMensagem", "Digite uma mensagem com at� 255 caracteres!");
		} else {
			usuario = usuarioDao.load(usuario.getId());
			
			Fuxico fuxico = new Fuxico();
			fuxico.setFuxico("@" + usuario.getLogin() + ": "+ montaMensagem(mensagem));
			fuxico.setData(new Date());
			fuxico.setUsuario(usuario);
			fuxicoDao.save(fuxico);
			
			String loginUsuarioLogado = "@" + usuario.getLogin() + " ";
			List<Usuario> usuarios = usuarioDao.list();
			for (Usuario usu : usuarios) {
				String loginUsuario = "@" + usu.getLogin() + " ";
				if(mensagem.contains(loginUsuario) && !mensagem.contains(loginUsuarioLogado))	{
					Fuxico fuxicoClone = fuxico.clone();
					fuxicoClone.setUsuario(usu);
					fuxicoDao.save(fuxicoClone);
				}
			}

			result.redirectTo(this).fuxicar(usuario, true);
		}
	}

	public String montaMensagem(String mensagem){
		List<Usuario> usuarios = usuarioDao.list();

		for (Usuario usuario : usuarios) {
			String loginUsuario = "@" + usuario.getLogin() + " ";
			if(mensagem.contains(loginUsuario))
				mensagem = mensagem.replace(loginUsuario, "<a href='link/" + usuario.getId() + "'>" + loginUsuario.trim()+ "</a> ");
		}
		
		return mensagem;
	}
}
