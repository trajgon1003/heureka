package bbc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PotvrzeniEmail {

	public void OverRegistraci() {

		System.setProperty("webdriver.gecko.driver", "C:\\Users\\martin\\Desktop\\geckodriver.exe");

		WebDriver driver = new FirefoxDriver();

		driver.get("https://accounts.google.com/signin");

		// login
		WebElement email_login = driver.findElement(By.id("identifierId"));
		email_login.clear();
		email_login.sendKeys("trajgon100");
		driver.findElement(By.id("identifierNext")).click();

		// heslo
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

		WebElement password_login = driver.findElement(By.name("password"));
		password_login.clear();
		password_login.sendKeys("Heslo321");
		driver.findElement(By.id("passwordNext")).click();

		driver.get("https://mail.google.com/mail/u/0/");

		// kontrola zda jsem na gmailu
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "parek" + "')]"));
		if (list.size() > 0) {
			System.out.println("Jsem na gmailu");
		} else {
			System.out.println("Posral se login");
			System.exit(0);
		}

		List<WebElement> listek = driver.findElements(By.xpath("//*[contains(text(),'" + "Heureka.cz" + "')]"));
		if (listek.size() <= 0) {
			System.out.println("Email od heureky nenalezen");
			System.exit(0);
		} else {
			System.out.println("Email od heureky nalezen");
		}

		try {

			Robot robot = new Robot();

			robot.delay(5000);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			e.printStackTrace();
		}

		driver.findElement(By.xpath("//*[contains(text(),'" + "Aktivovat úèet" + "')]")).click();

		List<WebElement> heurekapage = driver.findElements(By.xpath("//*[contains(text(),'" + "Moje Heureka" + "')]"));
		if (heurekapage.size() <= 0) {
			System.out.println("Uèet aktivován");
			System.exit(0);
		} else {
			System.out.println("!!! Uèet NEaktivován!!!");
		}

		System.exit(0);

	}

}
