package br.com.fa7.fuxico.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UsuarioControllerTest {
	UsuarioController usuarioController;

	@Before
	public void setUp() {
		usuarioController = new UsuarioController();
	}

	@Test
	public void naoDevePermitirNomeIgualSenha() {
		assertTrue(usuarioController.verificarSenhaNome("Felipe", "felipe"));
	}
	
	@Test
	public void senhaDeveTerMin6Digitos() {
		assertTrue(usuarioController.verificarTamanhoMinSenha("123456"));
	}
	

}
