import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WO_008_OP_03 extends Hooks {
    /**
     * 1-) Open the URL.
     * 2-) Click "WebOrder" button on top bar.
     * 3-) Enter valid username "Inar" and password "Academy".
     * 4-) Navigate to the order page.
     * 5-) Select "MyMoney" from Product dropdown.
     * 6-) Enter "8" as quantity number.
     * 7-) Enter "20" as discount percentage.
     * 8-) Click on the "Calculate" button.
     * 9-) Enter "Inar Academy" as Name.
     * 10-) Enter "1100 Congress Ave" as Street.
     * 11-) Enter "Austin" as City.
     * 12-) Enter "TX" State.
     * 13-) Enter "92@#83" as Zip Code.
     * 14-) Select "American Express" as Card Type.
     * 15-) Enter "342738261027163" as Card Number.
     * 16-) Enter "01/28" Expire Date(mm/yy format).
     * 17-) Click "Process"" button.
     * 18-) Verify the invalid Zip Code error message is displayed.
     */
    final int QUANTITY = 8;
    final int DISCOUNT = 20;

    @Test
    void verifyOrderPlacementWithInvalidZipCode() {
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

        Select myMoney = new Select(driver.findElement(By.id("productSelect")));
        myMoney.selectByValue("MyMoney");

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
        zipText.sendKeys("92@#83");

        js.executeScript("window.scroll(0,1000)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement cardType = driver.findElement(By.id("amex"));
        cardType.click();

        WebElement cardNo = driver.findElement(By.id("cardNumber"));
        cardNo.sendKeys("342738261027163");

        WebElement expiryDateText = driver.findElement(By.id("expiryDate"));
        expiryDateText.sendKeys("01/28");

        WebElement processButton = driver.findElement(By.xpath("//button[contains(text(),'Process')]"));
        processButton.click();

//        WebElement errorMessageElement = driver.findElement(By.id("zip"));
//        String errorMessage = errorMessageElement.getText();
//        assertEquals("Zip Code can not be invalid", errorMessage,"An error message should be displayed indicating that the entered Zip Code is invalid");

        WebElement alertText = null;
        assertNotNull(alertText, "There should be an alert text written 'Zip Code is invalid'");
    }
}
