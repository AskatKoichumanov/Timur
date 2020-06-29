package StepDefinitions;

import Utils.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static Pages.slack.Home.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class SlackStepDefs extends SeleniumStepDefsBase{

    String actualTS ="";
    final String TOKEN = DataReader.getProperty("token");
    final String CHANNEL = "C0164SXRETU";
    final String AUTH = "Authorization";
    Map<String,Object> moakMessage;


    @When("write message via API with text {string}")
    public void write_message_via_API_with_text(String text) {
        given().contentType(ContentType.JSON).header(AUTH,TOKEN)
                .body(PayLoadUtil.getSlackMessage(text, CHANNEL)).when()
                .post("https://slack.com/api/chat.postMessage");
    }

    @When("write message via Selenium with text {string}")
    public void write_message_via_Selenium_with_text(String text) {
        writeMessage(text);

    }


    @Then("verify message via API with text {string}")
    public void verify_message_via_API_with_text(String text) {
        assertEquals(moakMessage.get("text"),text);
    }

    @Then("verify message via Selenium with text {string}")
    public void verify_message_via_Selenium_with_text(String text) {
        assertTrue(checkIfPresent(text));
    }

    @Then("verify message deleted via API")
    public void verify_message_deleted_via_API_with_text() {
        assertTrue(moakMessage==null);
    }

    @Then("verify message deleted via Selenium with text {string}")
    public void verify_message_deleted_via_Selenium(String text) throws InterruptedException {
        Thread.sleep(2000);
        assertFalse(checkIfPresent(text));
    }

    @Then("delete message")
    public void delete_message() {
        String uri = "https://slack.com/api/chat.delete?channel="+CHANNEL+"&ts="+actualTS;
        System.out.println(uri);
        given().header(AUTH,TOKEN).when().post(uri).then().statusCode(200);
    }

    @Then("get message timeStamp with {string}")
    public void get_message_timeStamp(String text) {
        Response response = given().accept(ContentType.JSON).header(AUTH,TOKEN).when()
                .get("https://slack.com/api/conversations.history?channel="+CHANNEL);

        Map<String,Object> body = response.getBody().as(new TypeRef<>() {});
        List<Map<String,Object>> messagesInfo = (List<Map<String, Object>>) body.get("messages");

        moakMessage = APIUtils.getMessageByText(text,messagesInfo);
        if(moakMessage !=null) {
            actualTS = (String) moakMessage.get("ts");
        }
    }

    @Given("go to channel page")
    public void go_to_channel_page() {
        try{
        driver.navigate().to(ConfigReader.getProperty("slack"));
        driver.manage().window().maximize();
        enterChannel();
        } catch (Exception e) {
            driver.navigate().back();
        }
    }

}
