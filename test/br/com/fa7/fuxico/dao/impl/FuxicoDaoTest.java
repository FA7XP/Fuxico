package br.com.fa7.fuxico.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.model.Fuxico;
import br.com.fa7.fuxico.model.Usuario;

public class FuxicoDaoTest {
		
	Usuario usuario;
	@Mock
	private UsuarioDao usuarioDao;
	@Mock
	private FuxicoDao fuxicoDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void list(){
		usuario = new Usuario();
		usuario.setNome("Samuel Pinheiro");
		usuario.setLogin("samuel");
		usuario.setSenha("123456");
		usuario.setEmail("samuel.flf@gmail.com");
		usuarioDao.save(usuario);
		
		Fuxico fuxico1 = new Fuxico();
		fuxico1.setUsuario(usuario);
		fuxico1.setFuxico("mensagem Fuxico 1");
		fuxicoDao.save(fuxico1);
		
		Fuxico fuxico2 = new Fuxico();
		fuxico2.setUsuario(usuario);
		fuxico2.setFuxico("mensagem Fuxico 2");
		fuxicoDao.save(fuxico2);
		
		when(fuxicoDao.listaFuxicosByUsuario(null)).thenReturn(Arrays.asList(fuxico1, fuxico2));

		assertEquals(2, fuxicoDao.listaFuxicosByUsuario(null).size());
	}
}