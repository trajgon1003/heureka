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
