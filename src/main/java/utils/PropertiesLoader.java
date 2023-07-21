package utils;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Properties;

import static core.drivers_initializer.drivers.DriverConstants.IS_ONEAPP;

public class PropertiesLoader {
    public static Properties properties;

    /**
     * Read the properties file
     *
     * @param configFilePath Path of the config file to read
     */
    public static Properties readPropertyFile(String configFilePath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(configFilePath));
            properties = new Properties();

            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + configFilePath);
        }
        return properties;
    }

    /**
     * method to read the config fle of the core automation
     *
     * @param key      he key param is being passed to determine the key on the config file
     * @param filePath the desired file path.
     * @return the value of the config key.
     */
    public static String readConfig(String key, String filePath) {
        File file = new File(filePath);
        return getPropValue(key, file);
    }

    /**
     * method to read the config file from automation modules.
     *
     * @param key the key param is being passed to determine the key on the config file
     * @return the value of the config key.
     */
    public static String readConfig(String key) {
        File file = new File("src/main/resources/config.properties");
        return getPropValue(key, file);
    }

    @NotNull
    private static String getPropValue(String key, File file) {

        Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            properties.load(bufferedReader);

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }

    public static boolean isOneAppScript(){
        if (System.getenv("isOneApp") != null) {
            return Boolean.parseBoolean(System.getenv("isOneApp"));
        }
        else {
            return Boolean.parseBoolean(readConfig(IS_ONEAPP));
        }
    }
}