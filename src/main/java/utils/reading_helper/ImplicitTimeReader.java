package utils.reading_helper;


import utils.PropertiesLoader;
import utils.Logger;

public class ImplicitTimeReader {

    public static final String TIME = "time";

    public static long readTime() {

        try {
            return Long.parseLong(PropertiesLoader.readConfig(TIME));
        } catch (NumberFormatException | IllegalStateException e) {
            Logger.info(e.getMessage());
            throw new RuntimeException();

        }
    }
}