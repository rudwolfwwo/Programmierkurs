public class ServerProtocol {
    private static String welcome = "Countdown - Welcome";
    private static String from = "Countdown from ";
    private static String number = "\\d+";
    private static String start = "Starting Countdown from ";
    private static String bye = "Countdown finished - Bye";
    public static String getWelcome() {
        return welcome;
    }
    public static boolean isValidFrom (String s) {
        return s != null || s.matches(from + number);
    }
    public static String getStart(int i) {
        return start + i;
    }

    public static String getBye() {
        return bye;
    }
    public static int extractNumber(String s) {
        return Integer.parseInt(s.substring(s.lastIndexOf(' ') + 1));
    }
}
