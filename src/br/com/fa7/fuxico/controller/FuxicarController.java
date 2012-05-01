package br.com.fa7.fuxico.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.model.Fuxico;
import br.com.fa7.fuxico.model.Usuario;

@Resource
public class FuxicarController {

	private Result result;
	private FuxicoDao fuxicoDao;

	public FuxicarController( Result result, FuxicoDao fuxicoDao) {
		this.result = result;
		this.fuxicoDao = fuxicoDao;
	}
	
	@Get("/fuxicar")
	public void fuxicar(){
//		result.include("fuxicos", fuxicoDao.list());
	}

	@Get("/atualizaFuxico")
	public void atualizaFuxico(){
//		result.use(json()).from( fuxicoDao.list(), "fuxicos").serialize();
	}
	
	@Post("/fuxicar")
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
