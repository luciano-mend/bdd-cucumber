package br.luciano.steps;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class InserirContasSteps {
	
	private WebDriver driver;
	
	@After(order = 1, value = "@funcionais")
	public void screenshot(Scenario cenario) {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String caminho = "target" + File.separator + "screenshot" + File.separator + cenario.getName() + cenario.getLine() + ".jpg";
		try {
			FileUtils.copyFile(file, new File(caminho));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After(order = 0, value = "@funcionais")
	public void fechaNavegador() {
		driver.quit();
	}

	@Dado("que desejo adicionar uma conta")
	public void queDesejoAdicionarUmaConta() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1360, 760));
		driver.get("https://seubarriga.wcaquino.me");
		driver.findElement(By.id("email")).sendKeys("lsm@email.com");
		driver.findElement(By.id("senha")).sendKeys("senha");
		driver.findElement(By.tagName("button")).click();
		String texto = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		Assert.assertEquals("Bem vindo, lsm!", texto);
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Adicionar")).click();
	}

	@Quando("adiciono a conta {string}")
	public void adicionoAConta(String string) {
		driver.findElement(By.id("nome")).sendKeys(string);
		driver.findElement(By.tagName("button")).click();
	}

	@Então("recebo a mensagem {string}")
	public void receboAMensagem(String string) {
		String texto = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		Assert.assertEquals(string, texto);
	}
	
}
