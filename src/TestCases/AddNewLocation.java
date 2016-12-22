package TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddNewLocation {
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
	public void testAddNewLocation() {
		driver.get(baseUrl);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/ul[1]/li[1]/a")).click();
		driver.findElement(By.id("poi_name")).sendKeys("1001 Noc");
		driver.findElement(By.id("poi_city_name")).sendKeys("Bugojno");
		driver.findElement(By.xpath(".//*[@id='place-form']/div[1]/div[2]/div[2]/div[2]/span/span[2]/div/span/div/p")).click();
		
		// There is a bug with ZIP code. You can type anything (like random letters) and it
		// will pass, thus not placing your POI on the right location.
		// Should introduce some form of validation.
		
		//Also if you don't click on the suggested town, regardless of manually entering zip code, it will still
		//fail to place your POI on the right location. If, for some reason, user decides not to click on the suggested town,
		//one can end up having Tuzla with a ZIP code of 71000.
		//driver.findElement(By.id("poi_zip_code")).sendKeys("70230");
		
		
		driver.findElement(By.id("poi_place_id")).sendKeys("Sultan Ahmedova");
		driver.findElement(By.id("poi_house_number")).sendKeys("49");
		driver.findElement(By.id("poi_description")).sendKeys("Mjesto opustanja, kvalitetne nargile i dobrog drustva!");
		driver.findElement(By
				.xpath("/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/div[1]/div/form/div[2]/div[2]/div[1]/div/button"))
				.click();
		Select dropdown = new Select(driver.findElement(By.xpath(
				"/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/div[1]/div/form/div[2]/div[2]/div[1]/div/div[1]/div/div[2]/select")));
		dropdown.selectByValue("6");
		Select newDropdown = new Select(driver.findElement(By.xpath(
				"/html/body/div[4]/div/div/div[2]/div/div[1]/div/div[1]/div[1]/div/form/div[2]/div[2]/div[1]/div/div[1]/div/div[3]/select")));
		newDropdown.selectByValue("63");
		driver.findElement(By.xpath(".//*[@id='place-form']/div[2]/div[2]/div[3]/div[2]/ul/li/input"))
				.sendKeys("1001, noc, nargila, bar, lounge, mnk70230, Bugojno, ");
		driver.findElement(By.id("btn_day_sat")).click();
		driver.findElement(By.id("btn_day_sun")).click();
		driver.findElement(By.id("working_hours_0_0")).sendKeys("8");
		driver.findElement(By.id("working_hours_0_1")).sendKeys("11");
		driver.findElement(By.className("btnAddWorkingHours")).click();
		WebElement wifi = driver.findElement(By.xpath(".//*[@id='lbl-has-wifi']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", wifi);
		js.executeScript("document.getElementById('poi_wifi_pass').value = 'mnk70230';");
		js.executeScript("document.getElementById('poi_wifi_ssid').value = '1001 Noc';");
		js.executeScript(
				"document.getElementById('poi_facebook_url').value = 'https://www.facebook.com/pages/Longue-caffe-1001-No%C4%87/422751407934642';");
		js.executeScript("document.getElementById('poi_comment').value = 'Dobro dosli!';");
		WebElement create =
		driver.findElement(By.xpath(".//*[@id='place-form']/div[10]/div/button[1]"));
		js.executeScript("arguments[0].click()", create);
	}
}
