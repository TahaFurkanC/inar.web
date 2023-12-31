import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {

	protected static WebDriver driver;

	protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@BeforeAll
	public static void setUpBeforeTest() throws InterruptedException {
		String browser = System.getProperty("browser", "chrome");
		switch (browser.toLowerCase()) {
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "chrome":
			default:
				driver = new ChromeDriver();
				break;
		}
		driver.manage().window().maximize();

		driver.get("https://InarAcademy:Fk160621.@test.inar-academy.com");
		Thread.sleep(1000);
		if (browser.equalsIgnoreCase("firefox")) {
			driver.navigate().refresh();
			Thread.sleep(1000);
		}
	}

	@AfterAll
	public static void tearDownAfterTest() {
		if (driver != null) {
			driver.quit();
		}
	}

}
