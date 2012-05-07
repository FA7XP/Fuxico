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

	public FuxicarController( Result result, FuxicoDao fuxicoDao, UsuarioSession usuarioSession) {
		this.result = result;
		this.fuxicoDao = fuxicoDao;
		this.usuarioSession = usuarioSession;
	}
	
	@Get("/fuxicar")
	public void fuxicar(){
		result.include("usuario", usuarioSession.getUsuario());
	}

	@Get("/fuxicar/fuxicos")
	public void fuxicos(){
		Fuxico fuxico1 = new Fuxico();
		fuxico1.setFuxico("teste");
		
		Collection<Fuxico> fuxicos = new ArrayList<Fuxico>();
		fuxicos.add(fuxico1);
		
		result.use(json()).from(fuxicos, "fuxicos").serialize();
	}
	
	@Post("/fuxicar/{usuario.id}")
	public void fuxicar(String mensagem, Usuario usuario){
		if (mensagem == null || mensagem.isEmpty()) {
			result.include("erroMensagem", "Digite uma mensagem!");
		} else if (mensagem.length() > 255) {
			result.include("erroMensagem", "Digite uma mensagem com até 255 caracteres!");
		} else {
			Fuxico fuxico = new Fuxico();
			fuxico.setFuxico(mensagem);
			fuxico.setUsuario(usuario);
			fuxicoDao.save(fuxico);
			
			result.redirectTo(FuxicarController.class).fuxicar();
		}
	}
}
