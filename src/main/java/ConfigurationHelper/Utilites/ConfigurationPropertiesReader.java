package ConfigurationHelper.Utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationPropertiesReader {
    private Properties properties;

    public Properties loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration properties");
        }
        return properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
