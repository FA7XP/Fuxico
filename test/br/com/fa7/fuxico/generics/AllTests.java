package br.com.fa7.fuxico.generics;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fa7.fuxico.controller.ContaControllerTest;
import br.com.fa7.fuxico.controller.FuxicarControllerTest;
import br.com.fa7.fuxico.controller.IndexControllerTest;
import br.com.fa7.fuxico.controller.LoginControllerTest;
import br.com.fa7.fuxico.dao.impl.FuxicoDaoTest;
import br.com.fa7.fuxico.funcional.Conta;
import br.com.fa7.fuxico.funcional.Login;
import br.com.fa7.fuxico.model.FuxicoTest;
import br.com.fa7.fuxico.model.UsuarioTest;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	ContaControllerTest.class,
	FuxicarControllerTest.class,
	IndexControllerTest.class,
	LoginControllerTest.class,
	FuxicoDaoTest.class,
	UsuarioTest.class,
	FuxicoTest.class,
	UsuarioTest.class,
	Login.class,
	Conta.class
})

public class AllTests {
}
