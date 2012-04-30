package br.com.fa7.fuxico.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.model.Usuario;


public class FuxicarControllerTest{
	@Mock 
	private FuxicoDao fuxicoDaoMock;

	private Result resultMock;
	private FuxicarController fuxicarController;
	private Usuario usuario;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.resultMock = new MockResult();
		this.fuxicarController = new FuxicarController(resultMock, fuxicoDaoMock);
	}


	@Test
	public void naoDevePermitirEnviarMsgVazia() {
		iniciaUsuario();

		fuxicarController.fuxicar("", usuario);
		String msgRetorno = (String) resultMock.included().get("erroMensagem");

		assertEquals(1, resultMock.included().size());
		assertEquals("Digite uma mensagem!", msgRetorno);
	}

	@Test
	public void naoDevePermitirEnviarMsgAcimaDoPermitido() {
		iniciaUsuario();

		fuxicarController.fuxicar(
				"01234567890123456789012345678901234567890123456789012345678901234567890123456789" +
				"01234567890123456789012345678901234567890123456789012345678901234567890123456789" +
				"01234567890123456789012345678901234567890123456789012345678901234567890123456789" +
				"1111111111111111",
				usuario);
		String msgRetorno = (String) resultMock.included().get("erroMensagem");

		assertEquals(1, resultMock.included().size());
		assertEquals("Digite uma mensagem com até 255 caracteres!", msgRetorno);		

	}

	@Test()
	public void naoDevePermitirMsgNula() {
		iniciaUsuario();

		fuxicarController.fuxicar(null, usuario);
		String msgRetorno = (String) resultMock.included().get("erroMensagem");

		assertEquals(1, resultMock.included().size());
		assertEquals("Digite uma mensagem!", msgRetorno);
	}

	@Test()
	public void mensagemEnviada() {
		iniciaUsuario();

		fuxicarController.fuxicar("fuxicando", usuario);
		assertEquals(0, resultMock.included().size());
	}

	@Test()
	public void mensagemEnviadaParaOutroUsuario() {
		usuario = new Usuario();
		usuario.setNome("Marilia");

		fuxicarController.fuxicar("fuxicando", usuario);
		assertEquals(0, resultMock.included().size());
	}

	private void iniciaUsuario() {
		usuario = new Usuario();
		usuario.setNome("Felipe");
	}
}
