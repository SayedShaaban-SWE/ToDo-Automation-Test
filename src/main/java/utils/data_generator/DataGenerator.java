package utils.data_generator;

import nl.flotsam.xeger.Xeger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    public static String generateEmail(DataEnums.MailDomains mailDomain) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = DateTimeFormatter.ofPattern("YYYYMMddhhmmss").format(localDateTime);
        String username = "AutomationTest." + generateRandomText(4) + dateTime + "@" + mailDomain.toString();
        return username;
    }

    public static String generateRandomText(int length) {
        String regex = "[A-Za-z]{" + length + "}";
        return generateStringByRegex(regex);
    }

    public static String generatePhoneNumber(DataEnums.PhoneTypes phoneType) {
        String regex = "";
        switch (phoneType) {
            case HOME_PHONE:
                regex = "+962 [1-9]{1} [0-9]{3}[ ][0-9]{4}";
                return generateStringByRegex(regex);
            case MOBILE_PHONE:
                regex = "+962 7[7-9]{1}[ ][0-9]{3}[ ][0-9]{4}";
                return generateStringByRegex(regex);
            default:
                throw new RuntimeException("Invalid phone type");
        }

    }

    private static String generateStringByRegex(String regex) {
        Xeger generator = new Xeger(regex);
        return generator.generate();
    }

}