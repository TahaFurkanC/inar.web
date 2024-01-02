import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_004_CF_01 extends Hooks{
    /**
     * 1-) Open the URL.
     * 2-) Click "WebOrder" button on top bar.
     * 3-) Enter valid username "Inar" and password "Academy".
     * 4-) Navigate to the order page.
     * 5-) Select "HomeDecor" from Product dropdown.
     * 6-) Enter "5" as quantity number.
     * 7-) Enter "15" as discount percentage.
     * 8-) Click on the "Calculate" button.
     * 9-) Verify that the total amount is successfully displayed.
     */

    final int QUANTITY = 5;
    final int DISCOUNT = 15;
    @Test
    void testFunctionalityOfCalculate() {
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

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("welcome-heading")));
        //4-) Navigate to the order page.
        WebElement orderPageTab = driver.findElement(By.cssSelector("[href='/weborder/order']"));
        orderPageTab.click();

        Select homeDecor = new Select(driver.findElement(By.id("productSelect")));
        homeDecor.selectByValue("HomeDecor");

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

        WebElement total = driver.findElement(By.id("totalInput"));
        double actualTotal = Double.parseDouble(total.getAttribute("value"));
        WebElement unitPriceTag = driver.findElement(By.xpath("//input[@value='150']"));
        String unitPrice = unitPriceTag.getAttribute("value");
        double unitPricee = Double.parseDouble(unitPrice);
        double tot = QUANTITY * unitPricee / 100 * (100 - DISCOUNT);
        System.out.println(tot);
        assertEquals(tot,actualTotal);

    }
}
