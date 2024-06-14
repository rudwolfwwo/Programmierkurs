public class ClientProtocol {
    private static String welcome = "Countdown - Welcome";
    private static String from = "Countdown from ";
    private static String number = "\\d+";
    private static String start = "Starting Countdown from ";
    private static String bye = "Countdown finished - Bye";
    public static String getFrom() {
        return from;
    }
    public static boolean isValidWelcome (String s) {
        return s != null || s.matches("Countdown - Welcome");
    }
    public static boolean isValidStart(String s) {
        return s.matches(start + number);
    }
    public static boolean isValidNumber(String s) {
        return s.matches(number);
    }
    public static boolean isValidBye(String s) {
        return s.matches(bye);
    }
    public static int extractZaehler(String s) {
        return Integer.parseInt(s);

    }

}
