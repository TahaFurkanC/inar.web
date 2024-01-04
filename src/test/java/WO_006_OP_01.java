import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_006_OP_01 extends Hooks {
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
     * 13-) Enter "78701" as Zip Code(number).
     * 14-) Select "Visa" as Card Type.
     * 15-) Enter "4938281746192845" as Card Number.
     * 16-) Enter "11/28" Expire Date(mm/yy format).
     * 17-) Click "Process"" button.
     * 18-) Verify the confirmation message is displayed.
     * 19-) Navigate to view all orders page.
     * 20-) Verify the order is successfully place
     */

    public static final int NUMBER_OF_DISCOUNT = 20;

    @Test
    void testVerifyOrderPlacement() {

        List<String> orderInput = new ArrayList<>();

        orderInput.add("Inar Academy");
        orderInput.add("MyMoney");
        orderInput.add("8");
        orderInput.add(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        orderInput.add("1100 Congress Ave");
        orderInput.add("Austin");
        orderInput.add("TX");
        orderInput.add("78701");
        orderInput.add("Visa");
        orderInput.add("4938281746192845");
        orderInput.add("11/28");

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

        // 5-) Select "MyMoney" from Product dropdown.
        Select select = new Select(driver.findElement(By.id("productSelect")));
        select.selectByValue(orderInput.get(1));

        // 6-) Enter "8" as quantity number.

        WebElement quantityBox = driver.findElement(By.id("quantityInput"));
        quantityBox.sendKeys(orderInput.get(2));

        // 7-) Enter "20" as discount percentage.
        WebElement discountBox = driver.findElement(By.id("discountInput"));
        discountBox.sendKeys(NUMBER_OF_DISCOUNT + "");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,500)");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        // 8-) Click on the "Calculate" button.
        WebElement calculateButton = driver.findElement(By.xpath("//button[@type='submit']"));
        calculateButton.click();

        // 9-) Enter "Inar Academy" as Name
        WebElement nameBox = driver.findElement(By.id("name"));
        nameBox.sendKeys(orderInput.get(0));

        // 10-) Enter "1100 Congress Ave" as Street.
        WebElement streetBox = driver.findElement(By.id("street"));
        streetBox.sendKeys(orderInput.get(4));

        // 11-) Enter "Austin" as City.
        WebElement cityBox = driver.findElement(By.id("city"));
        cityBox.sendKeys(orderInput.get(5));

        // 12-) Enter "TX" State.
        WebElement stateBox = driver.findElement(By.id("state"));
        stateBox.sendKeys(orderInput.get(6));

        // 13-) Enter "78701" as Zip Code(number).
        WebElement zipBox = driver.findElement(By.id("zip"));
        zipBox.sendKeys(orderInput.get(7));

        js.executeScript("window.scroll(0,800)");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 14-) Select "Visa" as Card Type.
        WebElement cardBox = driver.findElement(By.id("visa"));
        cardBox.click();

        // 15-) Enter "4938281746192845" as Card Number.
        WebElement cardNo = driver.findElement(By.id("cardNumber"));
        cardNo.sendKeys(orderInput.get(9));

        // 16-) Enter "11/28" Expire Date(mm/yy format)
        WebElement expireDateBox = driver.findElement(By.id("expiryDate"));
        expireDateBox.sendKeys(orderInput.get(10));

        // 17-) Click "Process"" button
        WebElement processButton = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        processButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        // 18-) Verify the confirmation message is displayed.
        WebElement newOrderMessage = driver.findElement(By.cssSelector("div[role='alert']"));
        String verifyMessage = newOrderMessage.getText();
        assertEquals("New order has been successfully added.", verifyMessage,
                "new order message should be after ordering");

        js.executeScript("window.scroll(0,-1000)");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 19-) Navigate to view all orders page.
        WebElement viewAllOrdersTab = driver.findElement(By.cssSelector("a[href='/weborder/view-orders']"));
        viewAllOrdersTab.click();

        //20-) Verify the order is successfully place
        List<WebElement> row = driver.findElements(By.xpath("//tbody/tr"));
        List<WebElement> rowDatas = row.get(row.size() -1).findElements(By.xpath("//td"));
        for(int i = 0; i < orderInput.size(); i++){
            assertEquals(orderInput.get(i),rowDatas.get(i+1).getText());
        }
    }
}
