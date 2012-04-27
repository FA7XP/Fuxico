package br.com.fa7.fuxico.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;


public class FuxicoControllerTest{
	FuxicarController fuxicarController;
	Result resultMock;
	FuxicoDao fuxicoDaoMock;
	UsuarioSession usuarioSessionMock;

	@Before
	public void setUp() 
	{
		resultMock = EasyMock.createMock(Result.class);
		fuxicoDaoMock = EasyMock.createMock(FuxicoDao.class);
		usuarioSessionMock = EasyMock.createMock(UsuarioSession.class);
		fuxicarController = new FuxicarController(resultMock, fuxicoDaoMock, usuarioSessionMock);
	}

	@Test
	public void naoDevePermitirEnviarMsgVazia() {
		try {
			fuxicarController.fuxicar("", "felipe");
			fail("NÃ£o deve permitir enviar mensagem vazia!");
		} catch (Exception e) {
			assertEquals("Digite uma mensagem!", e.getMessage());
		}
	}

	@Test
	public void naoDevePermitirEnviarMsgAcimaDoPermitido() {
		try {
			fuxicarController.fuxicar(
					"01234567890123456789012345678901234567890123456789012345678901234567890123456789" +
					"01234567890123456789012345678901234567890123456789012345678901234567890123456789" +
					"01234567890123456789012345678901234567890123456789012345678901234567890123456789" +
					"1111111111111111",
					"felipe");
			fail("NÃ£o deve permitir enviar msg acima de 255 caracteres!");
		} catch (Exception e) {
			assertEquals("Digite uma mensagem com até 255 caracteres!",
					e.getMessage());
		}
	}

	@Test()
	public void naoDevePermitirMsgNula() {
		try {
			fuxicarController.fuxicar(null, "felipe");
			fail("NÃ£o deve permitir enviar msg nula!");
		} catch (Exception e) {
			assertEquals("Digite uma mensagem!", e.getMessage());
		}
	}

	@Test()
	public void mensagemEnviada() {
		try 
		{
			EasyMock.expect(resultMock.redirectTo(FuxicarController.class)).andReturn(EasyMock.createMock(FuxicarController.class));
			EasyMock.replay(resultMock);
			
			fuxicarController.fuxicar("fa7", "felipe");
		} 
		catch (Exception e) 
		{
			fail("Messagem não enviada. erro" + e.getStackTrace());
		}
	}
	
	@Test()
	public void mensagemEnviadaParaOutroUsuario() {
		try {
			EasyMock.expect(resultMock.redirectTo(FuxicarController.class)).andReturn(EasyMock.createMock(FuxicarController.class));
			EasyMock.replay(resultMock);
			
			fuxicarController.fuxicar("faSete", "luiz");
			List<String> teste = fuxicarController.getTimeline().get("luiz");
			assertEquals(1, teste.size());
		} catch (Exception e) {
			fail("Messagem nï¿½o enviada. erro" + e.getMessage());
		}
	}
}
