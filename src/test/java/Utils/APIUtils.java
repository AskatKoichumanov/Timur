package Utils;

import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIUtils {

    public static boolean workingLink(String link) {
        try{
            int statusCode = given().get(link).statusCode();
            return statusCode == HttpStatus.SC_OK;
        }catch (Exception e) {
            return false;
        }
    }

    public static Map<String,Object> getMessageByText(String text, List<Map<String,Object>> listOfMessages) {
        for(Map<String,Object> message: listOfMessages) {
            if(message.get("text").equals(text)){
               return message;
            }
        }
        return null;
    }
}
