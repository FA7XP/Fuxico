package br.com.fa7.fuxico.funcional;

import org.junit.Test;

public class Login extends FuxicoGenericSelenium{
	
	@Test
	public void logar() throws Exception	{
		checarTextoNaoPresente("Digite o Usuário.");
		checarTextoNaoPresente("Digite o Usuário.");

		clicarNoBotao("logar");
		checarTextoPresente("Digite o Usuário.");
		checarTextoPresente("Digite a Senha.");

		checarTextoNaoPresente("Login ou senha inválido.");
		preencherCampoComTexto("login", "Samuel");
		preencherCampoComTexto("senha", "Samuel");
		clicarNoBotao("logar");
		
		checarTextoPresente("Login ou senha inválido.");
	}
}
