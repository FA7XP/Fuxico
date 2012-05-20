package br.com.fa7.fuxico.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Usuario;

public class ContaControllerTest {

	@Mock 
	private UsuarioDao usuarioDaoMock;
	@Mock 
	private UsuarioSession usuarioSessionMock;

	private Result resultMock;
	private ContaController contaController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.resultMock = new MockResult();
		this.contaController = new ContaController(resultMock, usuarioDaoMock, usuarioSessionMock);
	}

	@Test
	public void gravarConta() throws Exception 
	{
		Usuario usuario = new Usuario();
		usuario.setNome("victor a");
		usuario.setLogin("victor");
		usuario.setSenha("123456");

		when(usuarioDaoMock.isLoginExiste(usuario.getLogin())).thenReturn(false);

		contaController.gravarConta(usuario, usuario.getSenha());
		String msgRetorno = (String) resultMock.included().get("ok");
		
		assertEquals(2, resultMock.included().size());
		assertEquals("Conta do FUXIQUEIRO criada com sucesso.", msgRetorno);
	}
	
	@Test
	public void gravarContaLoginExistente() throws Exception 
	{
		Usuario usuario = new Usuario();
		usuario.setNome("victor a");
		usuario.setLogin("victor");
		usuario.setSenha("123456");
		
		when(usuarioDaoMock.isLoginExiste(usuario.getLogin())).thenReturn(true);
		contaController.gravarConta(usuario, usuario.getSenha());
		
		String msgRetorno = (String) resultMock.included().get("erroConta");
		
		assertEquals(2, resultMock.included().size());
		assertEquals("Login já existente. Favor inserir um novo login.", msgRetorno);
	}
	
	@Test
	public void gravarContaLoginSenhaIguais() throws Exception 
	{
		Usuario usuario = new Usuario();
		usuario.setNome("victor A");
		usuario.setLogin("victor");
		usuario.setSenha("victor");
		
		when(usuarioDaoMock.isLoginExiste(usuario.getLogin())).thenReturn(false);
		contaController.gravarConta(usuario, usuario.getSenha());
		
		String msgRetorno = (String) resultMock.included().get("erroConta");
		
		assertEquals(2, resultMock.included().size());
		assertEquals("A Senha não pode ser igual ao login.", msgRetorno);
	}
	
	@Test
	public void gravarContaComSenhaMenor() throws Exception 
	{
		Usuario usuario = new Usuario();
		usuario.setNome("victor a");
		usuario.setLogin("victor");
		usuario.setSenha("123");
		
		when(usuarioDaoMock.isLoginExiste(usuario.getLogin())).thenReturn(false);
		
		contaController.gravarConta(usuario, usuario.getSenha());
		String msgRetorno = (String) resultMock.included().get("erroConta");
		
		assertEquals(2, resultMock.included().size());
		assertEquals("A Senha não pode ser inferior a 6 caracteres.", msgRetorno);
	}
	
	@Test
	public void naoDevePermitirNomeIgualSenha() {
		assertTrue(contaController.verificarSenhaNome("Felipe", "felipe"));
	}

	@Test
	public void senhaDeveTerMin6Digitos() {
		assertFalse(contaController.verificarTamanhoMinSenha("123456"));
	}
}
