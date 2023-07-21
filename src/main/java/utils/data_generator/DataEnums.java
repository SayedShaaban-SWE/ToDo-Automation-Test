package utils.data_generator;

public class DataEnums {
    public enum MailDomains {
        GOOGLE("gmail.com"),
        YAHOO("yahoo.com"),
        HOTMAIL("hotmail.com");
        private final String emailDomain;

        MailDomains(String emailDomain) {
            this.emailDomain = emailDomain;
        }

        @Override
        public String toString() {
            return emailDomain;
        }

    }

    public enum PhoneTypes {
        MOBILE_PHONE, HOME_PHONE
    }
}