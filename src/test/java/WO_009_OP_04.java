import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_009_OP_04 extends Hooks {
    /**
     * 1-) Open the URL. 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
     * "Inar" and password "Academy". 4-) Navigate to the order page. 5-) Select
     * "SportsEquipment" from Product dropdown. 6-) Enter "1" as quantity number. 7-) Enter
     * "10" as discount percentage. 8-) Click on the "Calculate" button. 9-) Enter "Inar
     * Academy" as Name. 10-) Enter "1100 Congress Ave" as Street. 11-) Enter "Austin" as
     * City. 12-) Enter "TX" State. 13-) Enter "78701" as Zip Code(number). 14-) Enter
     * "4938220192845" as Card Number. 15-) Enter "09/26" Expire Date(mm/yy format). 16-)
     * Click "Process"" button. 17-) Verify the Card Type error message is displayed.
     */
    final int QUANTITY = 1;
    final int DISCOUNT = 10;

    @Test
    void verifyOrderPlacementWithoutCardType() {
        WebElement webOrderTag = driver.findElement(By.cssSelector("[href='/weborder']"));
        webOrderTag.click();

        // 3-) Enter "Inar" as username and "Academy" password.

        WebElement userNameText = driver.findElement(By.id("login-username-input"));
        userNameText.sendKeys("Inar");
        WebElement passwordText = driver.findElement(By.id("login-password-input"));
        passwordText.sendKeys("Academy");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("welcome-heading")));
        //4-) Navigate to the order page.
        WebElement orderPageTab = driver.findElement(By.cssSelector("[href='/weborder/order']"));
        orderPageTab.click();

        Select sportsEquipment = new Select(driver.findElement(By.id("productSelect")));
        sportsEquipment.selectByValue("SportsEquipment");

        WebElement quantity = driver.findElement(By.id("quantityInput"));
        quantity.sendKeys(QUANTITY + "");

        WebElement discount = driver.findElement(By.id("discountInput"));
        discount.sendKeys(DISCOUNT + "");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,250)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement calculateButton = driver.findElement(By.xpath("//button[@type='submit'][1]"));
        calculateButton.click();

        WebElement nameText = driver.findElement(By.id("name"));
        nameText.sendKeys("Inar Academy");

        WebElement streetText = driver.findElement(By.id("street"));
        streetText.sendKeys("1100 Congress Ave");

        WebElement cityText = driver.findElement(By.id("city"));
        cityText.sendKeys("Austin");

        WebElement stateText = driver.findElement(By.id("state"));
        stateText.sendKeys("TX");

        WebElement zipText = driver.findElement(By.id("zip"));
        zipText.sendKeys("78701");

        js.executeScript("window.scroll(0,1000)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement cardNo = driver.findElement(By.id("cardNumber"));
        cardNo.sendKeys("4938220192845");

        WebElement expiryDateText = driver.findElement(By.id("expiryDate"));
        expiryDateText.sendKeys("09/26");

        WebElement processButton = driver.findElement(By.xpath("//button[contains(text(),'Process')]"));
        processButton.click();

        WebElement errorElement = driver.findElement(By.xpath("//em[text()='Card type cannot be empty']"));
        String errorMessage = errorElement.getText();
        assertEquals("Card type cannot be empty", errorMessage);

    }
}
