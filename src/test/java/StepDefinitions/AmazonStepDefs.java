package StepDefinitions;

import Pages.amazon.AmazonHome;
import Utils.APIUtils;
import Utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.stream.Collectors;

public class AmazonStepDefs extends SeleniumStepDefsBase {

    List allLinks;
    AmazonHome home = new AmazonHome();

    @Given("navigate to amazon")
    public void navigate_to_amazon() {
        driver.navigate().to(ConfigReader.getProperty("amazon"));
    }

    @When("user gets all links")
    public void user_gets_all_links() {
        if (allLinks==null){
            allLinks = home.links.stream().map(link -> link.getAttribute("href")).collect(Collectors.toList());
        }
    }

    @Then("print {string} links")
    public void print_links(String condition) {
        switch (condition){
            case "all":
                allLinks.stream().forEach(link-> System.out.println(link));
                break;
            case "working":
                allLinks.stream().filter(link ->APIUtils.workingLink((String)link))
                        .forEach(link -> System.out.println(link));
        }
    }


}
