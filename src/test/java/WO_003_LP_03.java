import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_003_LP_03 extends Hooks {

	/**
	 * 1-) Open the URL 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
	 * "Inar" and password "Academy". 4-) Click "Logout" button. 5-) Verify logout
	 * successfully.
	 */
	@Test
	void verifyLogout() {
		WebElement webOrderTab = driver.findElement(By.cssSelector("[href='/weborder']"));
		webOrderTab.click();
		WebElement usernameText = driver.findElement(By.id("login-username-input"));
		usernameText.sendKeys("Inar");
		WebElement passwordText = driver.findElement(By.id("login-password-input"));
		passwordText.sendKeys("Academy");
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement logOutButton = driver.findElement(By.id("logout-button"));
		logOutButton.click();

		WebElement logInTitle = driver.findElement(By.xpath("//h2[contains(text(),'Login')]"));
		String logInText = logInTitle.getText();
		assertEquals("Login", logInText, "There must be Login text after Logging out");

	}

}
