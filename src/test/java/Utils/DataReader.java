package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class DataReader {
    private static Properties dataFile;

    static {
        String path = "src\\test\\resources\\properties\\data.properties";
        try {
            FileInputStream input = new FileInputStream(path);
            dataFile = new Properties();
            dataFile.load(input);

            input.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String keyValue) {
        return dataFile.getProperty(keyValue);
    }
}

