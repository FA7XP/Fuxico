package br.com.fa7.fuxico.funcional;

import org.junit.Test;

public class Login extends FuxicoGenericSelenium{
	
	@Test
	public void logar() throws Exception	{
		checarTextoNaoPresente("Digite o nome.");
		checarTextoNaoPresente("Digite a senha.");

		clicarNoBotao("logar");
		checarTextoPresente("Digite o nome.");
		checarTextoPresente("Digite a senha.");

		checarTextoNaoPresente("Login ou senha inválidos.");
		preencherCampoComTexto("login", "Samuel");
		preencherCampoComTexto("senha", "Samuel");
		clicarNoBotao("logar");
		
		checarTextoPresente("Login ou senha inválidos.");
	}
}
