package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private final Properties properties;
    private static ConfigManager configManager;

    private ConfigManager(){
        try {
            properties=readPropertiesFile("src/test/resources/dev_config.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static ConfigManager getInstance(){
        if(configManager == null){
            configManager = new ConfigManager();
        }
        return configManager;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null) return prop;
        else throw new RuntimeException("property baseUrl is not specified in the dev_config.properties file");
    }

    public String getUserOne(){
        String prop = properties.getProperty("user1");
        if(prop != null) return prop;
        else throw new RuntimeException("property user1 is not specified in the dev_config.properties file");
    }

    public String getUserTwo(){
        String prop = properties.getProperty("user2");
        if(prop != null) return prop;
        else throw new RuntimeException("property user2 is not specified in the dev_config.properties file");
    }

   private static Properties readPropertiesFile(String filePath) throws IOException {
        FileInputStream fileInput = null;
        Properties prop = null;
        try {
            fileInput = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fileInput);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            throw new RuntimeException("properties file not found at " + filePath);
        } catch(IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException("failed to load properties file "+ filePath);
        } finally {
            fileInput.close();
        }
        return prop;
    }



}
