package br.com.fa7.fuxico.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class IndexControllerTest {

	private Result resultMock;
	private IndexController indexController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.resultMock = new MockResult();
		this.indexController = new IndexController(resultMock);
	}
	
	@Test
	public void index() throws Exception{
		indexController.index();
	}
}