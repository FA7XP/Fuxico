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
			fuxico.setUsuario(usuario);
			fuxico.setFuxico(montaMensagem(mensagem));
			fuxico.setData(new Date());
			fuxicoDao.save(fuxico);

			result.redirectTo(this).fuxicar(usuario, true);
		}
	}

	private String montaMensagem(String mensagem){
		List<Usuario> usuarios = usuarioDao.list();

		for (Usuario usuario : usuarios) {
			String loginUsuario = "@" + usuario.getLogin() + " ";
			if(mensagem.contains(loginUsuario))
				mensagem = mensagem.replace(loginUsuario, "<a href='link/" + usuario.getId() + "'>" + loginUsuario+ "</a>");
		}
		
		return mensagem;
	}
	
	
//	public String localizarUsuarioNoInicioDaMensagem(String mensagem) {
//		String usuario = "";
//		if (mensagem.startsWith("@")) {
//			usuario = verificarNomeUsuarioMensagem(mensagem, usuario, 1);
//		}
//		return usuario;
//	}
//
//	public String localizarUsuarioNoMeioDaMensagem(String mensagem) {
//		String usuario = "";
//		if (mensagem.contains("@")) {
//			int posicaoInicioUsuario = 0;
//			for (int i = 0; i < mensagem.length(); i++) {
//				if (mensagem.charAt(i) == '@') {
//					if (mensagem.charAt(i - 1) == ' ') {
//						posicaoInicioUsuario = i + 1;
//						break;
//					}
//					usuario += mensagem.charAt(i);
//				}
//			}
//
//			usuario = verificarNomeUsuarioMensagem(mensagem, usuario, posicaoInicioUsuario);
//		}
//		return usuario;
//	}
//
//	private String verificarNomeUsuarioMensagem(String mensagem, String usuario, int posicaoInicioUsuario) {
//		for (int i = posicaoInicioUsuario; i < mensagem.length(); i++) {
//			if (mensagem.charAt(i) != ' ') {
//				usuario += mensagem.charAt(i);
//			} else {
//				break;
//			}
//		}
//		return usuario;
//	}
//	
//	public String retornarMensagemComLink(String mensagemSemLink) {
//
//		String mensagemComLink = "";
//
//		for (int i = 0; i < mensagemSemLink.length(); i++) {
//			if (mensagemSemLink.charAt(i) == '@') {
//				if (i == 0) {
//					String usuario = "";
//					for (int j = i + 1; j < mensagemSemLink.length(); j++) {
//						if (mensagemSemLink.charAt(j) != ' ') {
//							usuario += mensagemSemLink.charAt(j);
//						} else {
//							break;
//						}
//					}
//					i = i + usuario.length() + 1;
//					if (usuarioDao.isLoginExiste(usuario) == true) {
//						mensagemComLink = "<a href='link/11'>@" + usuario+ "</a>";
//					} else {
//						mensagemComLink = "@" + usuario + " ";
//					}
//				} else {
//					//blabla ronaldo!
//				}
//			} else {
//				mensagemComLink += mensagemSemLink.charAt(i);
//			}
//		}
//
//		return mensagemComLink;
//	}
}
