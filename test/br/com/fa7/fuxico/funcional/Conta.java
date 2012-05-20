package br.com.fa7.fuxico.funcional;

import org.junit.Test;
import org.openqa.selenium.By;

public class Conta  extends FuxicoGenericSelenium{

	@Test
	public void criarConta() throws Exception	{
		checarTextoNaoPresente("Digite Nome Completo.");
		checarTextoNaoPresente("Digite o Usuário.");
		checarTextoNaoPresente("Digite a Senha.");
		checarTextoNaoPresente("Confirme a Senha.");
		checarTextoNaoPresente("Digite o Email.");

		clicarNoBotao("criar");
		checarTextoPresente("Digite Nome Completo.");
		checarTextoPresente("Digite o Usuário.");
		checarTextoPresente("Digite a Senha.");
		checarTextoPresente("Confirme a Senha.");
		checarTextoPresente("Digite o Email.");

		checarTextoNaoPresente("Login ou senha inválido.");
		preencherCampoComTexto("usuario.nome", "Samuel Gomes Pinheiro");
		preencherCampoComTexto("usuario.login", "slxy");
		preencherCampoComTexto("usuario.senha", "senha123");
		preencherCampoComTexto("confirmarSenha", "senha123");
		preencherCampoComTexto("usuario.email", "samuel@teste.com");
		clicarNoBotao("criar");

		checarTextoPresente("Conta do FUXIQUEIRO criada com sucesso.");

		preencherCampoComTexto("login", "slxy");
		preencherCampoComTexto("senha", "senha123");
		clicarNoBotao("logar");
		
		preencherCampoComTexto("mensagem", "olá @samuel como vai a chica soltou a franga.");
		clicarNoElemento(By.xpath("//input[@id='fuxicar']"));
		checarTextoNaoPresente("olá @samuel como vai a chica soltou a franga.");
		
		clicarNoLink("Excluir Conta");
		clicarNoElemento(By.xpath("//input[@id='popup_ok']"));
		checarTextoPresente("Conta \"slxy\" excluida com sucesso.");
	}		
}
