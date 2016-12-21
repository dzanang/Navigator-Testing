package TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReserveTicket {
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
	public void testReserveTicket() throws InterruptedException {
		//due to system being designed in a way that ember provides IDs that are not constant, but rather refresh every so often,
		//I have decided not to use IDs in format "emberxxx" and "metamorph-xxx-xx" to target web elements.
		driver.get(baseUrl);
		driver.findElement(By.className("art")).click();
		//I am using Thread.sleep() just to show it all happening a bit slower on the website
		Thread.sleep(1000);
		//Not a fan of using xpath, but due to lack of IDs or IDs being dynamic, and lack of unique class names and element names, I have to resort to xpath
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div[2]/div[2]/div[2]/div[3]/div[5]/span[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/div[2]/div[3]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("txtName")).sendKeys("Mujo");
		Thread.sleep(1000);
		driver.findElement(By.name("surname")).sendKeys("Suljic");
		Thread.sleep(1000);
		driver.findElement(By.name("email")).sendKeys("mujo@suljo.com");
		Thread.sleep(1000);
		driver.findElement(By.name("phone")).sendKeys("061123321");
		Thread.sleep(1000);
		driver.findElement(By.name("note")).sendKeys("Ignorisite ovu rezervaciju. Radi se cisto u svrhu testiranja sistema. Hvala");
		Thread.sleep(1000);
		driver.findElement(By.id("txtNmb")).clear();
		driver.findElement(By.id("txtNmb")).sendKeys("3");
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]/div[4]/div/form/div[5]/div/label[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]/div[4]/div/form/div[7]/input")).click();
		Thread.sleep(3000);
		driver.quit();
	}

}
