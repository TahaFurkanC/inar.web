import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WO_011_VAO_02 extends Hooks{
    /**
     * 1-) Open the URL
     * 2-) Click "WebOrder" button on top bar.
     * 3-) Enter valid username "Inar" and password "Academy".
     * 4-) Navigate to the view all order page.
     * 5-) Click "Add More Data" "6" times.
     * 6-) Click "Check All" button.
     * 7-) Verify all orders selected.
     * 8-) Click "Uncheck All" button.
     * 9-) Verify all orders are unselected.
     */
    @Test
    void verifyUncheckAllFunctionalityInViewAllOrderPage(){
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
        for (int i = 0; i < 6; i++){
            addMoreDataButton.click();
        }
        WebElement checkAllButton = driver.findElement(By.xpath("//button[@class='btn btn-success fs-4 text-fifth me-3']"));
        checkAllButton.click();

        List<WebElement> verifyAllButtonChecked = driver.findElements(By.xpath("//input[@class='form-check-input']"));

        for(WebElement each : verifyAllButtonChecked){
            assertTrue(each.isSelected());
        }

        WebElement uncheckAllButton = driver.findElement(By.xpath("//button[@class='btn btn-primary fs-4 text-fifth']"));
        uncheckAllButton.click();

        List<WebElement> verifyAllButtonUnchecked = driver.findElements(By.xpath("//input[@class='form-check-input']"));
        for(WebElement each : verifyAllButtonUnchecked){
            assertFalse(each.isSelected());
        }
    }
}
