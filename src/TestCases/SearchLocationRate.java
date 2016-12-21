package TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchLocationRate {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:/eclipse/geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://www.navigator.ba/#/categories";	
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testSearchLocationRate() throws InterruptedException {
		driver.get(baseUrl);
		//due to system being designed in a way that ember provides IDs that are not constant, but rather refresh every so often,
		//I have decided not to use IDs in format "emberxxx" and "metamorph-xxx-xx" to target web elements.
		driver.findElement(By.id("header_search")).sendKeys("4 sobe"); 
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div/div/span/span[2]/div/span/div[1]/p")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/div[2]/div[2]/div[2]/div[1]/div/div[1]/div[1]/span[4]")).click();
		Thread.sleep(3000);
		driver.quit();
	}

}
