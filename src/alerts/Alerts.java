package alerts;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.opera.core.systems.OperaDriver;



public class Alerts {

	private WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.ie.driver", "c:\\selenium\\webdriver\\IEdriverServer.exe");
		System.setProperty("webdriver.chrome.driver", "c:\\selenium\\webdriver\\chromedriver.exe");
		driver = new InternetExplorerDriver();
	    baseUrl = "file://c:/selenium/alerts_ex.html";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testAlerts() {
		driver.navigate().to(baseUrl);
		driver.findElement(By.id("btnConfirm")).click();
		Alert confirm = driver.switchTo().alert();
		assertEquals("To nie ten confirm()", "Chose an option.", confirm.getText());
		confirm.accept();
		assertTrue(driver.findElement(By.tagName("body")).getText().contains("Confirmed."));
		
		driver.findElement(By.id("btnAlert")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("To nie ten alert()", "I'm blocking!", alert.getText());
		alert.accept();
		assertTrue(driver.findElement(By.tagName("body")).getText().contains("Alert is gone."));
		
		driver.findElement(By.id("btnPrompt")).click();
		Alert prompt = driver.switchTo().alert();
		assertEquals("To nie ten prompt()", "What's the best web QA tool?", prompt.getText());
		prompt.sendKeys("selenium");
		prompt.accept();
		assertTrue(driver.findElement(By.tagName("body")).getText().contains("selenium"));
	}
	
	@Test
	public void testWindow() {
		String title = "Google";
		driver.navigate().to(baseUrl);
		
		String winHandleBefore = driver.getWindowHandle();
		
		driver.findElement(By.id("lnkNewWindow")).click();
		
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(winHandleBefore)){
				System.out.println(winHandle);
				driver.switchTo().window(winHandle);
			}
		}
		assertEquals("Błąd okna lub niepoprawny tytuł", title, driver.getTitle());
		driver.close();
		driver.switchTo().window(winHandleBefore);
		
		driver.findElement(By.id("btnNewNamelessWindow")).click();
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(winHandleBefore)){
				System.out.println(winHandle);
				driver.switchTo().window(winHandle);
			}
		}
		assertEquals("Błąd okna lub niepoprawny tytuł", title, driver.getTitle());
		driver.close();
		driver.switchTo().window(winHandleBefore);
		
		driver.findElement(By.id("btnNewNamedWindow")).click();
		driver.switchTo().window("Tester");
		assertEquals("Błąd okna lub niepoprawny tytuł", title, driver.getTitle());
		driver.close();
		driver.switchTo().window(winHandleBefore);
	}

}
