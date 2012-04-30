package br.com.fa7.fuxico.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class FuxicoTest {

	private Fuxico fuxico;

	@Before
	public void setUp() {
		fuxico = fuxico();
	}
	
	@Test
	public void getNome() {
		assertEquals("Samuel Pinheiro", fuxico.getUsuario().getNome());
	}
	
	@Test
	public void getMensagem() {
		assertEquals("mensagem do fuxico", fuxico.getFuxico());
	}
	
	private Fuxico fuxico() {
		Usuario usuario = new Usuario();
		usuario.setNome("Samuel Pinheiro");
		usuario.setLogin("samuel");
		usuario.setSenha("123456");
		usuario.setEmail("samuel.flf@gmail.com");
		
		fuxico = new Fuxico();
		fuxico.setUsuario(usuario);
		fuxico.setFuxico("mensagem do fuxico");
		return fuxico;
	}
}