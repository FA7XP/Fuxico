package br.com.fa7.fuxico.controller;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.dao.impl.UsuarioDaoImpl;
import br.com.fa7.fuxico.model.Usuario;

public class ContaControllerTest {
	ContaController contaController;
	Result resultMock;
	UsuarioDao usuarioDaoMock;
	UsuarioSession usuarioSessionMock;

	@Before
	public void setUp() {
		resultMock = EasyMock.createMock(Result.class);
		usuarioDaoMock = EasyMock.createMock(UsuarioDao.class);
		usuarioSessionMock = EasyMock.createMock(UsuarioSession.class);
		contaController = new ContaController(resultMock, usuarioDaoMock, usuarioSessionMock);
	}

	@Test
	public void naoDevePermitirNomeIgualSenha() {
		assertTrue(contaController.verificarSenhaNome("Felipe", "felipe"));
	}

	@Test
	public void senhaDeveTerMin6Digitos() {
		assertTrue(contaController.verificarTamanhoMinSenha("123456"));
	}

	@Test
	public void verificarLoginDisponivel() 
	{
		 Usuario usuario = new Usuario();
		 usuario.setNome("victor a");
		 usuario.setLogin("victor");
		 usuario.setSenha("123321");
		 usuarioDaoMock.save(usuario);
    	 
		 EasyMock.expect(usuarioDaoMock.consultarUsuarioPorLogin(usuario.getLogin())).andReturn(usuario);
		 EasyMock.replay(usuarioDaoMock);
		 
		 assertTrue(contaController.existeLoginDisponivel("victor"));
	}

}
