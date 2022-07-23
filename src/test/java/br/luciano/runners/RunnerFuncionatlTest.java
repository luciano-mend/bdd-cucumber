package br.luciano.runners;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		glue = {"br.luciano.steps"},
		tags = {"@funcionais"},
		plugin = {"pretty","html:target/report-html","json:target/report.json"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false,
		strict = false)
public class RunnerFuncionatlTest {
	
	@BeforeClass
	public static void reset() {
		WebDriver driver = new ChromeDriver();
	    driver.manage().window().setSize(new Dimension(1360, 760));
	    driver.get("https://seubarriga.wcaquino.me");
	    driver.findElement(By.id("email")).sendKeys("lsm@email.com");
	    driver.findElement(By.id("senha")).sendKeys("senha");
	    driver.findElement(By.tagName("button")).click();
	    driver.findElement(By.linkText("reset")).click();
	    driver.quit();
	    
	}
}
