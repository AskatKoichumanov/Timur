package Pages.slack;

import Pages.PageBase;
import Utils.ConfigReader;
import Utils.DataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static Utils.BrowserUtils.*;

public class Home extends PageBase {

    static Home home = new Home();
    @FindBy(xpath = "//a[text()='Sign in']")
    WebElement signIn;

    @FindBy(xpath = "//input[@name='domain']")
    WebElement domainInput;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement continueButton;

    @FindBy(xpath = "//input[@type='email']")
    WebElement email;

    @FindBy(xpath = "//input[@type='password']")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement signIn2;

    @FindBy(xpath = "//span[text()='api_channel']")
    WebElement channel;

    @FindBy(xpath = "//p")
    WebElement inputBox;

    public static void enterChannel(){
        home.signIn.click();
        inputText(home.domainInput, ConfigReader.getProperty("domain"));
        clickButton(home.continueButton);
        inputText(home.email, DataReader.getProperty("login"));
        inputText(home.password, DataReader.getProperty("password"));
        clickButton(home.signIn2);
        clickButton(home.channel);
    }

    public static void writeMessage(String text){
        inputAndSendText(home.inputBox,text);
    }

    public static boolean checkIfPresent(String text) {
        try{
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[text()='"+text+"']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
