package br.com.fa7.fuxico.controller;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Usuario;

public class LoginControllerTest {

	@Mock 
	private UsuarioDao usuarioDaoMock;
	@Mock 
	private UsuarioSession usuarioSessionMock;

	private Result resultMock;
	private LoginController loginController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.resultMock = new MockResult();
		this.loginController = new LoginController(resultMock, usuarioDaoMock, usuarioSessionMock);
	}

	@Test
	public void testGetLogin() throws Exception {
		try {
			loginController.login();
		} catch (Exception e) {
			fail("Teste getLogin quebrou.");
		}
	}

	@Test
	public void testPostLoginValido() throws Exception {
		String nome = "victor";
		String senha = "victor123";

		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);

		when(usuarioDaoMock.login(nome, senha)).thenReturn(usuario);

		loginController.login(nome, senha);
		assertEquals(0, resultMock.included().size());
	}
	
	@Test
	public void testPostLoginInvalido() throws Exception { 
		String nome = "victor";
		String senha = "victor123";

		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);

		when(usuarioDaoMock.login(nome, senha)).thenReturn(null);

		loginController.login(nome, senha);
		
		String msgRetorno = (String) resultMock.included().get("erro");

		assertEquals(2, resultMock.included().size());
		assertEquals("Login ou senha inválido.", msgRetorno);
	}
}
