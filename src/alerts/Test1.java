package alerts;

import java.net.URL;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class Test1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
//    driver = new FirefoxDriver();
    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
    baseUrl = "https://konto.onet.pl/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void testOnetKonto() throws Exception {
    driver.get(baseUrl + "/register-email.html?client_id=poczta.onet.pl.front&ticket=1416299486");
    driver.findElement(By.id("f_nameSurname")).clear();
    driver.findElement(By.id("f_nameSurname")).sendKeys("IMIE NAZWISKO");
    driver.findElement(By.id("f_gender_M")).click();
    new Select(driver.findElement(By.id("f_birthDate_day"))).selectByVisibleText("2");
    new Select(driver.findElement(By.name("birthDate[month]"))).selectByVisibleText("lutego");
    new Select(driver.findElement(By.name("birthDate[year]"))).selectByVisibleText("2013");
    new Select(driver.findElement(By.id("f_country"))).selectByVisibleText("Nikaragua");
    driver.findElement(By.id("f_login_domain_0")).click();
    driver.findElement(By.id("f_password")).clear();
    driver.findElement(By.id("f_password")).sendKeys("haslo123");
    driver.findElement(By.id("f_confirmPassword")).clear();
    driver.findElement(By.id("f_confirmPassword")).sendKeys("haslo1234");
    driver.findElement(By.id("f_phonesEmails")).clear();
    driver.findElement(By.id("f_phonesEmails")).sendKeys("123456789");
    driver.findElement(By.id("adcopy_response")).clear();
    driver.findElement(By.id("adcopy_response")).sendKeys("test");
    driver.findElement(By.id("f_agreements_1")).click();
    driver.findElement(By.id("f_agreements_3")).click();
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
