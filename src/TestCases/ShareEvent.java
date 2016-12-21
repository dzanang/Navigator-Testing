package TestCases;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ShareEvent {
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
	public void testShareEvent() throws InterruptedException {
		// due to system being designed in a way that ember provides IDs that
		// are not constant, but rather refresh every so often,
		// I have decided not to use IDs in format "emberxxx" and
		// "metamorph-xxx-xx" to target web elements.
		driver.get(baseUrl);
		// Not a fan of using xpath, but due to lack of IDs or IDs being
		// dynamic, and lack of unique class names and element names, I have to
		// resort to xpath
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/ul/div[1]")).click();
		// I am using Thread.sleep() just to show it all happening a bit slower
		// on the website
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/ul/li[2]")).click();
		WebElement facebook = driver
				.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/div[1]/div[2]/div/a[2]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", facebook);

		newWindow(2);
		driver.findElement(By.id("email")).sendKeys("charrnorris@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("luster123");
		//Sleep timer is needed for the new pop-up window to load and the script can continue 
		//executing. Otherwise it fails.
		Thread.sleep(2000); 
		driver.findElement(By.id("loginbutton")).click();
		Thread.sleep(2000);  //Same principle applies here for sleep timer.
		newWindow(2); //previous window gets closed so we still end up with 2 windows, thus this being number 2 again.
		driver.findElement(By.id("u_0_n")).sendKeys("Great Show");
		driver.findElement(By.id("u_0_k")).click();
		Thread.sleep(3000);
		driver.quit();
	}
	
	//In order to avoid the mess with window handles, this method assigns numbers to each window that gets opened
	//so it is much easier to manage switches.
	public void newWindow (int winNumber) {
	    Set < String > handle = driver.getWindowHandles();   
	    Iterator < String > iterator = handle.iterator();
	    while (iterator.hasNext()) {
	        String popupHandle = iterator.next().toString();
	        driver.switchTo().window(popupHandle);
	    }
	}

}
