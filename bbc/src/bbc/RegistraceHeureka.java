package bbc;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class RegistraceHeureka {

	public static void main(String[] args) {
		int pokusy = 0;
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\martin\\Desktop\\geckodriver.exe");
		File file = new File(
				"C:\\Users\\konir\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\v6wg2m4p.profileToolsQA\\extensions\\jid1-NIfFY2CA8fy1tg@jetpack.xpi");
		FirefoxProfile fp = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver();

		registrujHrace(driver);

	}

	private static void provedRecenzi(WebDriver driver) {
	}

	public static void registrujHrace(WebDriver driver) {

		String name = "";

		InsertDb databaze = new InsertDb();
		name = databaze.ziskejdata("prezdivka");
		if (name == "") {
			System.out.println("Nedostal jsem jméno");
			System.exit(0);
		}

		driver.get("https://ucet.heureka.cz/registrace");

		WebElement registracni_jmeno = driver.findElement(By.id("frm-registrationForm-registrationForm-email"));
		registracni_jmeno.sendKeys(name + "@trajgymon.33mail.com");

		WebElement registracni_mail = driver.findElement(By.id("frm-registrationForm-registrationForm-password"));
		registracni_mail.sendKeys("Heslo321");

		driver.findElement(By.xpath("//*[@id='frm-registrationForm-registrationForm']/fieldset/div[4]/div[1]/label[1]"))
				.click();

		driver.findElement(By.xpath("//*[@id='frm-registrationForm-registrationForm']/fieldset/div[4]/div[2]/label[1]"))
				.click();

		driver.findElement(By.name("_submit")).click();

		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'"
				+ "Abychom mohli registraci dokonèit, potøebujeme od vás potvrdit aktivaèní e-mail. Najdete ho ve své schránce."
				+ "')]"));
		if (list.size() > 0) {
			System.out.println("Registrace probìhla");

		} else {
			System.out.println("Registrace neprobìhla");
			databaze.zmen_na_used(name, "prezdivka");
			driver.close();
			PotvrzeniEmail a = new PotvrzeniEmail();
			a.OverRegistraci();

		}

	}

}
