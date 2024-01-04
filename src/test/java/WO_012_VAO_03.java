import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class WO_012_VAO_03 extends Hooks{
    /**
     * 1-) Open the URL
     * 2-) Click "WebOrder" button on top bar.
     * 3-) Enter valid username "Inar" and password "Academy".
     * 4-) Navigate to the view all order page.
     * 5-) Click "Add More Data" "8" times.
     * 6-) Click 1st, 3rd and 5th orders checkbox's.
     * 7-) Click "Delete All" button.
     * 8-) Verify the orders are deleted.
     */
    @Test
    void verifyDeleteFunctionalityInViewAllOrderPage(){
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

        WebElement viewAllOrdersLink = driver.findElement(By.xpath("//div[@id='view-orders-tab']/a"));
        viewAllOrdersLink.click();

        WebElement addMoreDataButton = driver.findElement(By.xpath("//button[@class='fs-4 btn btn-primary text-fifth me-3']"));
        for (int i = 0; i < 8; i++){
            addMoreDataButton.click();
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,500)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Click 1st, 3rd and 5th orders checkbox's.
        List<WebElement> allOrders = driver.findElements(By.xpath("//tbody/tr"));
        List<String> linksOfDeletedOrders = new ArrayList<>();
        for (int i = 0; i <= 4; i += 2) {
            allOrders.get(i).findElement(By.tagName("input")).click();
            linksOfDeletedOrders.add(allOrders.get(i).findElement(By.cssSelector("td > a")).getAttribute("href"));
        }

        driver.findElement(By.xpath("//button[text()='Delete']")).click();

        js.executeScript("window.scroll(0,-200)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify the orders are deleted.
        allOrders = driver.findElements(By.xpath("//tbody/tr"));
        ArrayList<String> linksOfRemainingOrders = new ArrayList<>();

        for (WebElement each : allOrders) {
            linksOfRemainingOrders.add(each.findElement(By.cssSelector("td > a")).getAttribute("href"));
        }
        for (String s : linksOfDeletedOrders) {
            assertFalse(linksOfRemainingOrders.contains(s));
        }
    }
}
