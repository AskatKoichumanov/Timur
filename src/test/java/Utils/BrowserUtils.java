package Utils;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BrowserUtils {

    public static WebDriver driver = Driver.getDriver();
    static WebDriverWait wait = new WebDriverWait(driver,30);

    public static void clickButton(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public static void inputText(WebElement element,String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public static void inputAndSendText(WebElement element,String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text+ Keys.ENTER);
    }

}
