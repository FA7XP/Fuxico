package br.com.fa7.fuxico.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Usuario;

public class FuxicarControllerTest {
	@Mock
	private UsuarioDao usuarioDaoMock;
	@Mock
	private FuxicoDao fuxicoDaoMock;
	@Mock
	private UsuarioSession usuarioSessionMock;

	private Result resultMock;
	private FuxicarController fuxicarController;
	private Usuario usuario;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.resultMock = new MockResult();
		this.fuxicarController = new FuxicarController(resultMock,
				fuxicoDaoMock, usuarioDaoMock, usuarioSessionMock);
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

		fuxicarController
				.fuxicar(
						"01234567890123456789012345678901234567890123456789012345678901234567890123456789"
								+ "01234567890123456789012345678901234567890123456789012345678901234567890123456789"
								+ "01234567890123456789012345678901234567890123456789012345678901234567890123456789"
								+ "1111111111111111", usuario);
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
	public void fuxicarVerificaMensagemFuxicar() {
		Usuario luiz = new Usuario();
		luiz.setId(9999L);
		luiz.setLogin("luiz");

		Usuario felipe = new Usuario();
		felipe.setLogin("felipe");
		
		String mensagem = "Ola @luiz como vai";
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(felipe);
		usuarios.add(luiz);
		
		when(usuarioDaoMock.list()).thenReturn(usuarios);

		String msgRetorno = fuxicarController.montaMensagem(mensagem, felipe.getLogin());
		
		assertEquals("@felipe: Ola <a href='link/9999'>@luiz</a> como vai", msgRetorno);
	}

	@Test()
	public void mensagemEnviada() {
		iniciaUsuario();

		when(usuarioDaoMock.load(usuario.getId())).thenReturn(usuario);
		fuxicarController.fuxicar("fuxicando", usuario);
		assertEquals(0, resultMock.included().size());
	}

	@Test()
	public void mensagemEnviadaParaOutroUsuario() {
		usuario = new Usuario();
		usuario.setNome("Marilia");
		usuario.setLogin("Marilia");

		when(usuarioDaoMock.load(usuario.getId())).thenReturn(usuario);
		fuxicarController.fuxicar("fuxicando", usuario);
		assertEquals(0, resultMock.included().size());
	}

	private void iniciaUsuario() {
		usuario = new Usuario();
		usuario.setLogin("felipe");
		usuario.setNome("Felipe");
		usuario.setSenha("felipe123");
	}
}
