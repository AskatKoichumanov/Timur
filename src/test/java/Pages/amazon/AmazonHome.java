package Pages.amazon;

import Pages.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AmazonHome extends PageBase {

    @FindBy (xpath = "//a[@href]")
    public List<WebElement> links;
}
