import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_013_CF_03 extends Hooks{
    /**
     * 1-) Open the URL. 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
     * "Inar" and password "Academy". 3-) Enter valid username "Inar" and password
     * "Academy". 4-) Navigate to the order page. 5-) Select "SportsEquipment" from
     * Product dropdown. 6-) Enter "1" as quantity number. 7-) Enter "10" as discount
     * percentage. 8-) Click on the "Calculate" button.
     * 9-) Enter "1100 Congress Ave" as Street.
     * 10-) Enter "Austin" as City.
     * 11-) Enter"TX" State.
     * 12-) Enter "78701" as Zip Code(number).
     * 13-)Enter the Card Type (Visa)
     * 14-) Enter "4938220192845" as Card Number.
     * 15-) Enter "09/26" Expire Date(mm/yy format).
     * 16-) Click "Process" button.
     * 17-) Verify the Name error message is displayed.
     */
    public static final int NUMBER_OF_QUANTITY = 1;

    public static final int NUMBER_OF_DISCOUNT = 10;

    @Test
    void testOrderPlacementWithoutName() {
        // 2-) Click "WebOrder" button on top bar.
        WebElement webOrderTag = driver.findElement(By.cssSelector("[href='/weborder']"));
        webOrderTag.click();

        // 3-) Enter "Inar" as username and "Academy" password.
        WebElement userNameText = driver.findElement(By.id("login-username-input"));
        userNameText.sendKeys("Inar");
        WebElement passwordText = driver.findElement(By.id("login-password-input"));
        passwordText.sendKeys("Academy");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // 4-) Navigate to the order page.
        WebElement orderPageTab = driver.findElement(By.cssSelector("[href='/weborder/order']"));
        orderPageTab.click();

        // 5-) Select "SportsEquipment" from Product dropdown.
        Select select = new Select(driver.findElement(By.id("productSelect")));
        select.selectByValue("SportsEquipment");

        // 6-) Enter "1" as quantity number.
        WebElement quantityBox = driver.findElement(By.id("quantityInput"));
        quantityBox.sendKeys(NUMBER_OF_QUANTITY + "");

        // 7-) Enter "10" as discount percentage.
        WebElement discountBox = driver.findElement(By.id("discountInput"));
        discountBox.sendKeys(NUMBER_OF_DISCOUNT + "");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,550)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 8-) Click on the "Calculate" button.
        WebElement calculateButton = driver.findElement(By.xpath("//button[@type='submit']"));
        calculateButton.click();


        // 9-) Enter "1100 Congress Ave" as Street.
        WebElement streetBox = driver.findElement(By.id("street"));
        streetBox.sendKeys("1100 Congress Ave");

        // 10-) Enter "Austin" as City.
        WebElement cityBox = driver.findElement(By.id("city"));
        cityBox.sendKeys("Austin");

        // 11-) Enter "TX" State.
        WebElement stateBox = driver.findElement(By.id("state"));
        stateBox.sendKeys("TX");

        // 12-) Enter "78701" as Zip Code(number).
        WebElement zipBox = driver.findElement(By.id("zip"));
        zipBox.sendKeys("78701");

        js.executeScript("window.scroll(0,1000)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //13-)Enter the Card Type (Visa)
        WebElement cardBox = driver.findElement(By.id("visa"));
        cardBox.click();

        // 14-) Enter "4938220192845" as Card Number.
        WebElement cardNo = driver.findElement(By.id("cardNumber"));
        cardNo.sendKeys("4938281746192845");

        // 15-) Enter "09/26" Expire Date(mm/yy format).
        WebElement expireDateBox = driver.findElement(By.id("expiryDate"));
        expireDateBox.sendKeys("09/26");

        // 16-) Click "Process"" button
        WebElement processButton = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        processButton.click();

        // 17-) Verify the Name error message is displayed.
        WebElement NameBox = driver.findElement(By.xpath("//em[contains(text(),'Name cannot be empty')]"));
        String nameErrorMessage = NameBox.getText();
        assertEquals("Name cannot be empty", nameErrorMessage, "should be text message when name is not in box");

    }
}
