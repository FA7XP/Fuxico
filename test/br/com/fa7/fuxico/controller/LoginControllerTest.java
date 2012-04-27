package br.com.fa7.fuxico.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Usuario;

public class LoginControllerTest {
	
	//Classe em teste
	LoginController loginController;
	//Mock necessarios
	UsuarioSession usuarioSessionMock;
	UsuarioDao usuarioDaoMock;
	Usuario usuarioMock;
	Result resultMock;
	
	@Before
	public void setUp()	{
		usuarioSessionMock = EasyMock.createMock(UsuarioSession.class);
		usuarioDaoMock = EasyMock.createMock(UsuarioDao.class);
		resultMock = EasyMock.createMock(Result.class);
		loginController = new LoginController(resultMock, usuarioDaoMock, usuarioSessionMock);
	}

	@Test
	public void testGetLogin() {
		//Duvida1: new Usuario()
		EasyMock.expect(usuarioSessionMock.getUsuario()).andReturn(new Usuario());
		EasyMock.expect(resultMock.redirectTo(IndexController.class))
			.andReturn(EasyMock.createMock(IndexController.class));
		EasyMock.replay(usuarioSessionMock);
		EasyMock.replay(resultMock);
		try {
			loginController.login();
		} catch (Exception e) {
			fail("Quebrou");
		}
	}

	@Test
	public void testGetLoginException()	{
		try {
			loginController.login();
		} catch (Exception e) {
			assertEquals("n�o deu certo", e.getMessage());
		}
	}
	
	@Test
	public void testPostLoginValido() {
		String nome = "victor";
		String senha = "victor123";
		//Duvida1: EasyMock.createMock(Usuario.class)
		EasyMock.expect(usuarioDaoMock.login(nome, senha)).andReturn(EasyMock.createMock(Usuario.class));
		
		//Problemas para incluir metodo void neste teste!
		
		EasyMock.expect(resultMock.redirectTo(IndexController.class))
			.andReturn(EasyMock.createMock(IndexController.class));
		EasyMock.replay(usuarioDaoMock);
		EasyMock.replay(resultMock);
		try {
			loginController.login(nome, senha);
		} catch (Exception e) {
			fail("Falhou teste de PostLogin v�lido");
		}
	}
	
	@Test
	public void testPostLoginInvalido() { //Ainda quebrando (Victor ..... verificando)
		String nome = "victor";
		String senha = "victor123";

		EasyMock.expect(usuarioDaoMock.login(nome, senha)).andReturn(EasyMock.createMock(Usuario.class));
		EasyMock.replay(usuarioDaoMock);
		
		EasyMock.expect(resultMock.redirectTo(IndexController.class)).andReturn(EasyMock.createMock(IndexController.class));
		EasyMock.replay(resultMock);

		try {
			loginController.login(nome, senha);
		} catch (Exception e) {
			fail("Falhou teste de PostLogin invalido");
		}
	}
	
}
