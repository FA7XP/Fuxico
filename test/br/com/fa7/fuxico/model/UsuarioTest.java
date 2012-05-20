package br.com.fa7.fuxico.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest{

	private Usuario usuario;

	@Before
	public void setUp() {
		usuario = usuario();
	}
	
	@Test
	public void getNome() {
		assertEquals("Samuel Pinheiro", usuario.getNome());
	}

	@Test
	public void getLogin() {
		assertEquals("samuel", usuario.getLogin());
	}
	
	@Test
	public void getSenha() {
		assertEquals("123456", usuario.getSenha());
	}
	
	@Test
	public void getEmail() {
		assertEquals("samuel.flf@gmail.com", usuario.getEmail());
	}
	
	private Usuario usuario() {
		usuario = new Usuario();
		usuario.setNome("Samuel Pinheiro");
		usuario.setLogin("samuel");
		usuario.setSenha("123456");
		usuario.setEmail("samuel.flf@gmail.com");
		return usuario;
	}
}