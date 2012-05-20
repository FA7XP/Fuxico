package br.com.fa7.fuxico.funcional;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class FuxicoGenericSelenium {
	
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8081/fuxico/");
	}
	
	protected void clicarNoElemento(By by) {
		driver.findElement(by).click();
	}

	protected void clicarNoBotaoCss(String css) {
		driver.findElement(By.cssSelector(css)).click();
	}

	protected void clicarNoLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}

	protected void clicarNoBotao(String botao) {
		driver.findElement(By.xpath("//button[@id='" + botao + "']")).click();
	}

	protected void selecionarCombo(By by, String valor) {
		Select combo = new Select(driver.findElement(by));
		combo.selectByVisibleText(valor);
	}
	
	protected void checarTextoNaoPresente(String... textos) {
		String pagina = driver.findElement(By.tagName("body")).getText();
		for (String texto : textos) {
			Assert.assertFalse("Valor: '" + texto + "' não encontrado", pagina.contains(texto));
		}
	}
	
	protected void checarTextoPresente(String... textos) {
		String pagina = driver.findElement(By.tagName("body")).getText();
		for (String texto : textos) {
			Assert.assertTrue("Valor: '" + texto + "' não encontrado", pagina.contains(texto));
		}
	}
	
	protected void checarBotaoPresente(String... textos) {
		String pagina = driver.findElement(By.tagName("button")).getText();
		for (String texto : textos) {
			Assert.assertTrue("Valor: '" + texto + "' não encontrado", pagina.contains(texto));
		}
	}
	
	protected void checarBotaoCssPresente(String css) {
		Assert.assertEquals(css, driver.findElement(By.cssSelector("input.botao")).getAttribute("value"));
	}

	protected void checaTituloPresente(String tipoTitulo, String... titulos) {
		String todosOsTitulos = driver.findElement(By.tagName(tipoTitulo)).getText();
		for (String titulo : titulos) {
			Assert.assertTrue("Valor do titulo: '" + titulo + "' não encontrado", todosOsTitulos.contains(titulo));
		}
	}
	
	protected void preencherCampoComTexto(String nomeCampo, String texto) {
		driver.findElement(By.name(nomeCampo)).clear();
		driver.findElement(By.name(nomeCampo)).sendKeys(texto);
	}

	protected void verificarAlert(String texto) {
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(texto, alert.getText());
		alert.accept();
	}
	
	@After
	public void tearDown() throws Exception{
		driver.quit();
	}
}
