package br.com.fa7.fuxico.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.model.Usuario;


public class UsuarioDaoTest{
	
	@Mock
	private UsuarioDao usuarioDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void list(){
		Usuario usuario1 = new Usuario();
		usuario1.setNome("Samuel Pinheiro");
		usuario1.setLogin("samuel");
		usuario1.setSenha("123456");
		usuario1.setEmail("samuel.flf@gmail.com");
		usuarioDao.save(usuario1);

		Usuario usuario2 = new Usuario();
		usuario2.setNome("Samuel Pinheiro");
		usuario2.setLogin("samuel");
		usuario2.setSenha("123456");
		usuario2.setEmail("samuel.flf@gmail.com");
		usuarioDao.save(usuario2);
		
		when(usuarioDao.list()).thenReturn(Arrays.asList(usuario1, usuario2));

		assertEquals(2, usuarioDao.list().size());
	}

	@Test
	public void isLoginExiste(){
		Usuario usuario = new Usuario();
		usuario.setNome("Samuel Pinheiro");
		usuario.setLogin("samuel");
		usuario.setSenha("123456");
		usuario.setEmail("samuel.flf@gmail.com");
		usuarioDao.save(usuario);
		
		when(usuarioDao.isLoginExiste(usuario.getLogin())).thenReturn(true);
		
		assertTrue(usuarioDao.isLoginExiste(usuario.getLogin()));
	}
	
	@Test
	public void login(){
		Usuario usuario = new Usuario();
		usuario.setNome("Samuel Pinheiro");
		usuario.setLogin("samuel");
		usuario.setSenha("123456");
		usuario.setEmail("samuel.flf@gmail.com");
		usuarioDao.save(usuario);
		
		when(usuarioDao.login(usuario.getLogin(), usuario.getSenha())).thenReturn(usuario);
		
		assertEquals(usuario, usuarioDao.login(usuario.getLogin(), usuario.getSenha()));
	}
}