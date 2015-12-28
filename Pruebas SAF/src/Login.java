import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;

public class Login {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		//final FirefoxProfile firefoxProfile = new FirefoxProfile();
		//firefoxProfile.setPreference("xpinstall.signatures.required", false);
		System.setProperty("webdriver.chrome.driver", "/Users/Esteban/Desktop/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://54.174.139.165:8080/ords/f?p=129";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testLogin() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("P101_USERNAME")).clear();
		driver.findElement(By.id("P101_USERNAME")).sendKeys("apex");
		driver.findElement(By.id("P101_PASSWORD")).clear();
		driver.findElement(By.id("P101_PASSWORD")).sendKeys("S1ner4");
		driver.findElement(By.cssSelector("input.t13Button")).click();
		driver.findElement(By.linkText("Saf")).click();
		driver.findElement(By.linkText("Anticipos y Reembolsos")).click();
		driver.findElement(By.cssSelector("li > a")).click();
		driver.findElement(
				By.xpath("//table[@id='260276737920940248']/tbody/tr[2]/td/a/img"))
				.click();
		driver.findElement(By.id("ADD")).click();
		// Prueba de select list
		driver.findElement(By.cssSelector("img[alt=\"Popup Lov\"]")).click();
		driver.findElement(
				By.xpath("//a[contains(text(),'354487	APOLINAR CRIALES OSCAR')]"))
				.click();
		new Select(driver.findElement(By.id("P110_FK_CENTRO_COSTO")))
				.selectByVisibleText("ALMACEN GENERAL");
		new Select(driver.findElement(By.id("P110_FK_CONCEPTO")))
				.selectByVisibleText("AD-COMBUSTIBLE");
		new Select(driver.findElement(By.id("P110_DEPARTAMENTO")))
				.selectByVisibleText("Bogot\\E1 D.C.");
		new Select(driver.findElement(By.id("P110_MUNICIPIO")))
				.selectByVisibleText("Bogot\\E1 D.C.");
	}

	@After
	public void tearDown() throws Exception {
		// driver.quit();
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