package utils.loggers;

public class Logger {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);

    public static org.apache.log4j.Logger logger(){
        return logger;
    }
}
