package conf;


import domain.User;

public class Settings {

    public static final String KEY_ALGORITHM = "AES/CBC/PKCS5Padding";
    public static final String KEY_GENERATOR_ALGORITHM = "AES";
    public static final String SECURITY_PROVIDER = "BC";
    public static final String WORDS_DIR = "/Users/janjelinek/temp";
    public static final String WORDS_FILE = "/Users/janjelinek/temp/words.txt";
    public static final int DEFAULT_POLLING_INTERVAL = 60000; // One minute 60000
    public static final String POLL_REQUEST = "Poll Request";
    public static final String POP3_PASSWORD = "";
    public static final int POP3_PORT = 995;
    public static final String POP3_SERVER = "pop.gmail.com";
    public static final String POP3_USER = "jelinek0105@gmail.com";
    public static final String SMTP_PASSWORD = "cgjxoprnytqbdjej";
    public static final int SMTP_PORT = 587;
    public static final String SMTP_SERVER = "smtp.gmail.com";
    public static final String SMTP_USER = "";
    public static final int FRONT_LISTENER_PORT = 5432;
    public static final int BACK_LISTENER_PORT = 5433;

    public static final String TCP_SERVER_NAME = "localhost";
    public static final User user = new User("John","Roy","Raddle","jelinek0105@gmail.com","JohnRo");


    //possibly:
    private static final String USER_RECEIVER = "oop3account1@seznam.cz";

    private static final String RECEIVER_PASSWORD = "ajbladfjsa213";

    private static final String RECEIVER_SERVER = "pop3.seznam.cz";

    public static String getUserReceiver() {return USER_RECEIVER;}

    public static String getReceiverPassword() {return RECEIVER_PASSWORD;}

    public static String getReceiverServer() {return RECEIVER_SERVER;}

}
