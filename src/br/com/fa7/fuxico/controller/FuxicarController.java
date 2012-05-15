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
	
	public FuxicarController(){
		
	}

	@Get("/fuxicar")
	public void fuxicar() {

		// Usuario usuario = new Usuario();
		Usuario usuario = usuarioSession.getUsuario();
		usuario.setNome(usuario.getNome());

		Fuxico fuxico1 = new Fuxico();
		fuxico1.setUsuario(usuario);
		fuxico1.setFuxico("teste");

		// Collection<Fuxico> fuxicos = new ArrayList<Fuxico>();
		List<Fuxico> fuxicos;
		fuxicos = fuxicoDao.consultarFuxicoPorLogin(usuario.getLogin());

		result.include("fuxicos", fuxicos).include("usuario",
				usuarioSession.getUsuario());
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
			Collection<Fuxico> fuxicos = fuxicoDao.listaFuxicosByUsuario(usuarioId);
			result.use(json()).from(fuxicos, "fuxicos").include("usuario").serialize();
		}
	}

	@Post("/fuxicar/{usuario.id}")
	public void fuxicar(String mensagem, Usuario usuario) {
		if (mensagem == null || mensagem.isEmpty()) {
			result.include("erroMensagem", "Digite uma mensagem!");
		} else if (mensagem.length() > 255) {
			result.include("erroMensagem",
					"Digite uma mensagem com até 255 caracteres!");
		} else {
			Fuxico fuxico = new Fuxico();
			fuxico.setFuxico("<a href='link/1'>samuelTeste</a> mamamia");
			fuxico.setUsuario(usuarioDao.load(usuario.getId()));
			fuxico.setData(new Date());
			fuxicoDao.save(fuxico);

			result.redirectTo(FuxicarController.class).fuxicar();
		}
	}

	public String localizarUsuarioNoInicioDaMensagem(String mensagem) {
		String usuario = "";
		if (mensagem.startsWith("@")) {
			usuario = verificarNomeUsuarioMensagem(mensagem, usuario, 1);
		}
		return usuario;
	}

	public String localizarUsuarioNoMeioDaMensagem(String mensagem) {
		String usuario = "";
		if (mensagem.contains("@")) {
			int posicaoInicioUsuario = 0;
			for (int i = 0; i < mensagem.length(); i++) {
				if (mensagem.charAt(i) == '@') {
					if (mensagem.charAt(i - 1) == ' ') {
						posicaoInicioUsuario = i + 1;
						break;
					}
					usuario += mensagem.charAt(i);
				}
			}

			usuario = verificarNomeUsuarioMensagem(mensagem, usuario, posicaoInicioUsuario);
		}
		return usuario;
	}

	private String verificarNomeUsuarioMensagem(String mensagem, String usuario, int posicaoInicioUsuario) {
		for (int i = posicaoInicioUsuario; i < mensagem.length(); i++) {
			if (mensagem.charAt(i) != ' ') {
				usuario += mensagem.charAt(i);
			} else {
				break;
			}
		}
		return usuario;
	}
	
	public String retornarMensagemComLink(String mensagemSemLink) {

		String mensagemComLink = "";

		for (int i = 0; i < mensagemSemLink.length(); i++) {
			if (mensagemSemLink.charAt(i) == '@') {
				if (i == 0) {
					String usuario = "";
					for (int j = i + 1; j < mensagemSemLink.length(); j++) {
						if (mensagemSemLink.charAt(j) != ' ') {
							usuario += mensagemSemLink.charAt(j);
						} else {
							break;
						}
					}
					i = i + usuario.length() + 1;
					if (usuarioDao.isLoginExiste(usuario) == true) {
						mensagemComLink = "<a href='link/11'>@" + usuario
								+ "</a>";
					} else {
						mensagemComLink = "@" + usuario + " ";
					}
				} else {
					//blabla ronaldo!
				}
			} else {
				mensagemComLink += mensagemSemLink.charAt(i);
			}
		}

		return mensagemComLink;
	}
}
