package Utils;

public class PayLoadUtil {

    public static String getSlackMessage(String text, String channel) {
        return "{\n" +
                "  \"channel\": \""+channel+"\",\n" +
                "  \"text\": \""+text+"\"\n" +
                "}";
    }
}
