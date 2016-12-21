package TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LanguageSwitch {
	private WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:/eclipse/geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://www.navigator.ba/#/categories";
		
		driver.manage().window().maximize(); //browser maximize
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testLanguageSwitch() throws InterruptedException {
		//due to system being designed in a way that ember provides IDs that are not constant, but rather refresh every so often,
				//I have decided to no use ID in format "emberxxx" to target web elements.
		driver.get(baseUrl);
		driver.findElement(By.className("en")).click();
		Thread.sleep(3000);
		driver.quit();
	}
}
