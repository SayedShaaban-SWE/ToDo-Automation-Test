package utils.data_generator;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateHelper {

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return date.toString();
    }
}