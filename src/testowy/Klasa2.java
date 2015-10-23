package testowy;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Klasa2 {

	WebDriver driver;
	String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://infopraca.pl";
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
