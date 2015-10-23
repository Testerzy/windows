package javascript;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.opera.core.systems.OperaDriver;

public class TestJavascript {

	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
//		System.setProperty("webdriver.chrome.driver", "c:\\selenium\\webdriver\\chromedriver.exe");
		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
	
	@Test
	public void test1() throws InterruptedException {
		driver.get("file:///c:/selenium/formularz.html");
		new Select(driver.findElement(By.id("header_app_select"))).selectByVisibleText("Admin");
		new Select(driver.findElement(By.id("header_auction_select"))).selectByVisibleText("AB: 3068 14.12. Schwaba");
		new Select(driver.findElement(By.id("equip[116]"))).selectByVisibleText("Opony letnie");
		new Select(driver.findElement(By.id("equip[117]"))).selectByVisibleText("0 mm");
		new Select(driver.findElement(By.id("equip[158]"))).selectByVisibleText("0 mm");
		new Select(driver.findElement(By.id("equip[159]"))).selectByVisibleText("3 mm");
		new Select(driver.findElement(By.id("equip[157]"))).selectByVisibleText(">9 mm");
		driver.findElement(By.name("28_16")).click();
		driver.findElement(By.name("26_16")).click();
		driver.findElement(By.name("27_16")).click();
		driver.findElement(By.name("29_16")).click();
		driver.findElement(By.name("28_17")).click();
		driver.findElement(By.name("26_17")).click();
		driver.findElement(By.name("27_17")).click();
		driver.findElement(By.name("29_17")).click();
		driver.findElement(By.name("28_18")).click();
		driver.findElement(By.name("26_18")).click();
		driver.findElement(By.name("27_18")).click();
		driver.findElement(By.name("29_18")).click();
		new Select(driver.findElement(By.id("equip[119]"))).selectByVisibleText("12");
		driver.findElement(By.name("28_3")).click();
		driver.findElement(By.name("26_3")).click();
		driver.findElement(By.name("27_3")).click();
		driver.findElement(By.name("29_3")).click();
		driver.findElement(By.name("28_4")).click();
		driver.findElement(By.name("26_4")).click();
		driver.findElement(By.name("27_4")).click();
		driver.findElement(By.name("29_4")).click();
		driver.findElement(By.name("28_2")).click();
		driver.findElement(By.name("26_2")).click();
		driver.findElement(By.name("27_2")).click();
		driver.findElement(By.name("28_15")).click();
		driver.findElement(By.name("26_15")).click();
		driver.findElement(By.name("27_15")).click();
		driver.findElement(By.name("29_15")).click();
		driver.findElement(By.name("31_10")).click();
		driver.findElement(By.name("30_10")).click();
		driver.findElement(By.name("30_11")).click();
		driver.findElement(By.name("31_11")).click();
		driver.findElement(By.name("30_12")).click();
		driver.findElement(By.name("31_12")).click();
		driver.findElement(By.name("30_13")).click();
		driver.findElement(By.name("31_13")).click();
		driver.findElement(By.name("30_14")).click();
		driver.findElement(By.name("31_14")).click();
		driver.findElement(By.name("group_desc_4")).clear();
		driver.findElement(By.name("group_desc_4")).sendKeys("test");
		driver.findElement(By.cssSelector("input[type=\"password\"]")).clear();
		driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("haslo123");
		getAllCheckboxIds();
		Thread.sleep(3000);
		clearForm("form_main");
		Thread.sleep(3000);
	}
	
	public void clearForm(String formId){
        String script = "";
        script += "var frm_elements = document.forms['"+formId+"'].elements;\n"; //pobieranie elementow formularza
        script += "for(i=0; i<frm_elements.length; i++) { \n"; //petla po zebranych elementach
        script += "field_type = frm_elements[i].type.toLowerCase(); \n"; //konwersja na male litery
        script += "switch(field_type) { \n"; //badanie obecnego typu elementu
        script += "case \"text\": \n";
        script += "case \"file\": \n";
        script += "frm_elements[i].value = \"\"; break; \n"; //jesli pole jest typu file lub text, czyscimy go
        script += "case \"password\":\n";
        script += "case \"textarea\": frm_elements[i].value = \"\"; \n"; //pola password i textarea czyscimy
        script += "case \"hidden\": break; \n";
        script += "case \"radio\": \n";
        script += "case \"checkbox\": \n";
        script += "if (frm_elements[i].checked) { \n"; //jelsli element jest zaznaczony, radio lub checkbox
        script += "frm_elements[i].checked = false; \n"; //odznaczamy go
        script += "} \n";
        script += "break; \n";
        script += "case \"select-one\": \n";
        script += "case \"select-multi\": \n";
        script += "frm_elements[i].selectedIndex = -1; break; \n"; //dla listy rozwijanej wybieramy pozycje -1
        script += "default: break; \n";
        script += "}};";
        Reporter.log(script);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }
	
	public void getAllCheckboxIds() {
		
        String script = "var inputId  = new Array();" ; // Tworzenie tablicy w java script
        script += "var cnt = 0;" ; // Licznik id pola wyboru
        script += "var inputFields  = new Array();" ; // Tworzenie tablicy w java script
        script += "inputFields = window.document.getElementsByTagName('input');" ; // kolekcja elementow typu input
        script += "for(var i=0; i<inputFields.length; i++) {" ; // petla po zebranych elementach
        script += "if(inputFields[i].name !=null " +
        "&& inputFields[i].name !='undefined' " +
        "&& inputFields[i].getAttribute('type') == 'checkbox') {"; // jesli id pola nie jest null i nie jest type niezdefiniowanego i jest typu checkbox
        script += "inputId[cnt]=inputFields[i].name ;" + // zapis id elementow do tablicy inputId
        "cnt++;" + // zwiekszanie licznika - indeksu tablicy elementow
        "}" + 
        "}";
        script += "return inputId.toString();" ; // konwersja tablicy do lancucha
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String result = (String) js.executeScript(script);
        Reporter.log(result);
	} 

	
}
