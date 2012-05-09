package br.com.fa7.fuxico.controller;

import java.util.ArrayList;
import java.util.Collection;
import static br.com.caelum.vraptor.view.Results.json;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Fuxico;
import br.com.fa7.fuxico.model.Usuario;

@Resource
public class FuxicarController {

	private Result result;
	private FuxicoDao fuxicoDao;
	private UsuarioSession usuarioSession;

	public FuxicarController(Result result, FuxicoDao fuxicoDao,
			UsuarioSession usuarioSession) {
		this.result = result;
		this.fuxicoDao = fuxicoDao;
		this.usuarioSession = usuarioSession;
	}

	@Get("/fuxicar")
	public void fuxicar() {
		
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		
		Fuxico fuxico1 = new Fuxico();
		fuxico1.setUsuario(usuario);
		fuxico1.setFuxico("teste");

		Collection<Fuxico> fuxicos = new ArrayList<Fuxico>();
		fuxicos.add(fuxico1);
		
		
		result.include("fuxicos", fuxicos).include("usuario", usuarioSession.getUsuario());
	}

	@Get("/fuxicos")
	public void fuxicos() {
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		
		Fuxico fuxico1 = new Fuxico();
		fuxico1.setUsuario(usuario);
		fuxico1.setFuxico("teste");

		Collection<Fuxico> fuxicos = new ArrayList<Fuxico>();
		fuxicos.add(fuxico1);

		result.use(json()).from(fuxicos, "fuxicos").include("usuario").serialize();
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
			fuxico.setFuxico(mensagem);
			fuxico.setUsuario(usuario);
			fuxicoDao.save(fuxico);

			result.redirectTo(FuxicarController.class).fuxicar();
		}

		// Primeiro caractere da msg = @
		// A mensagem contem __ @exemplo

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

			usuario = verificarNomeUsuarioMensagem(mensagem, usuario,
					posicaoInicioUsuario);
		}
		return usuario;
	}

	private String verificarNomeUsuarioMensagem(String mensagem,
			String usuario, int posicaoInicioUsuario) {
		for (int i = posicaoInicioUsuario; i < mensagem.length(); i++) {
			if (mensagem.charAt(i) != ' ') {
				usuario += mensagem.charAt(i);
			} else {
				break;
			}
		}
		return usuario;
	}
}
