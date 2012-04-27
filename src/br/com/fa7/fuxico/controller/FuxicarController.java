package br.com.fa7.fuxico.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.dao.UsuarioSession;

public class FuxicarController {

	Map<String, List<String>> timeline;
	//UsuarioDao usuarioDao;
	

	private Result result;
	private FuxicoDao fuxicoDao;
	private UsuarioSession usuarioSession;

	public FuxicarController( Result result, FuxicoDao fuxicoDao, UsuarioSession usuarioSession ) {
		this.result = result;
		this.fuxicoDao = fuxicoDao;
		this.usuarioSession = usuarioSession;
	}
	
	@Get("/fuxicar")
	public void fuxicar() 
	{
		result.include("fuxicos", fuxicoDao.list());
	}

	@Get("/atualizaFuxico")
	public void atualizaFuxico() 
	{
//		result.use(json()).from( fuxicoDao.list(), "fuxicos").serialize();
	}
	
	@Post("/fuxicar")
	public void fuxicar(String mensagem, String usuario) throws Exception 
	{
		timeline = new HashMap<String, List<String>>();

		if (mensagem == null || mensagem.isEmpty()) {
			throw new Exception("Digite uma mensagem!");
		} else if (mensagem.length() > 255) {
			throw new Exception("Digite uma mensagem com até 255 caracteres!");
		} else {
			List<String> msgs = new ArrayList<String>();
			msgs.add(mensagem);
			timeline.put(usuario, msgs);
		}
		result.redirectTo(FuxicarController.class).fuxicar();
	}

	public List<String> obterMensagens(String usuario) {
		return timeline.get(usuario);
	}

	public Map<String, List<String>> getTimeline() {
		return timeline;
	}

}
