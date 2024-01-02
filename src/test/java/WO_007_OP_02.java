import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_007_OP_02 extends Hooks{
    /**
     * 1-) Open the URL.
     * 2-) Click "WebOrder" button on top bar.
     * 3-) Enter valid username "Inar" and password "Academy".
     * 4-) Navigate to the order page.
     * 5-) Select "FamilyAlbum" from Product dropdown.
     * 6-) Enter "3" as quantity number.
     * 7-) Enter "17" as discount percentage.
     * 8-) Enter "Inar Academy" as Name.
     * 9-) Enter "1100 Congress Ave" as Street.
     * 10-) Enter "Austin" as City.
     * 11-) Enter "TX" State.
     * 12-) Enter "78701" as Zip Code(number).
     * 13-) Select "Mastercard" as Card Type.
     * 14-) Enter "5162738261027163" as Card Number.
     * 15-) Enter "11/28" Expire Date(mm/yy format).
     * 16-) Click "Process"" button.
     * 17-) Verify the invalid Product Information error message is displayed.
     */
    final int QUANTITY = 3;
    final int DISCOUNT = 17;
    @Test
    void verifyOrderPlacementWithoutCalculation(){
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

        Select homeDecor = new Select(driver.findElement(By.id("productSelect")));
        homeDecor.selectByValue("FamilyAlbum");

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


        WebElement nameText = driver.findElement(By.id("name"));
        nameText.sendKeys("Inar Academy");

        WebElement streetText = driver.findElement(By.id("street"));
        streetText.sendKeys("1100 Congress Ave");

        js.executeScript("window.scroll(0,250)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement cityText = driver.findElement(By.id("city"));
        streetText.sendKeys("Austin");

        WebElement stateText = driver.findElement(By.id("state"));
        streetText.sendKeys("TX");

        WebElement zipText = driver.findElement(By.id("zip"));
        streetText.sendKeys("78701");

        js.executeScript("window.scroll(0,1000)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement cardType = driver.findElement(By.id("Mastercard"));
        cardType.click();

        WebElement cardNo = driver.findElement(By.id("cardNumber"));
        cardNo.sendKeys("5162738261027163");

        WebElement expiryDateText = driver.findElement(By.id("expiryDate"));
        expiryDateText.sendKeys("11/28");

        WebElement processButton = driver.findElement(By.xpath("//button[contains(text(),'Process')]"));
        processButton.click();

        WebElement errorMessageText = driver.findElement(By.xpath("//em[contains(text(),'Fix errors in Product Information')]"));
        String relativeMessage = errorMessageText.getText();
        assertEquals("Fix errors in Product Information",relativeMessage,"An error message should be displayed indicating that the product information is invalid");
    }
}
