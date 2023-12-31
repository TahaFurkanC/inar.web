import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_001_LP_01 extends Hooks {

	// Open the URL.
	// 2-) Click "WebOrder" button on top bar.
	// 3-) Enter "Inar" as username and "Academy" password.
	// 4-) Click on the "Login" button.
	// 5-) Verify that the user is successfully logged in.

	@Test
	void testLoginFunctionaity() {
		WebElement webOrderTab = driver.findElement(By.cssSelector("[href='/weborder']"));
		webOrderTab.click();
		WebElement usernameText = driver.findElement(By.id("login-username-input"));
		usernameText.sendKeys("Inar");
		WebElement passwordText = driver.findElement(By.id("login-password-input"));
		passwordText.sendKeys("Academy");
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("welcome-heading")));

		WebElement welcomeMessage = driver.findElement(By.id("welcome-heading"));
		String messageText = welcomeMessage.getText();
		assertEquals("Welcome, Inar!", messageText);
	}

}
