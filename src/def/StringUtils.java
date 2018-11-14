package def;

public class StringUtils {
    public static String center(String str, int length) {
        int space = length - str.length();
        if (space <= 0) {
            return str.substring(0, length);
        }
        boolean unbalance = false;
        if (length % 2 == 0) {
            unbalance = true;
        }
        space = space / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < space; i++) {
            sb.append(" ");
        }
        sb.append(str);
        if (unbalance) {
            space++;
        }
        for (int i = 0; i < space; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String padLeft(String str, int length) {
        int space = length - str.length();
        if (space <= 0) {
            return str.substring(0, length);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        for (int i = 0; i < space; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String padRight(String str, int length) {
        int space = length - str.length();
        if (space <= 0) {
            return str.substring(0, length);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < space; i++) {
            sb.append(" ");
        }
        sb.append(str);
        return sb.toString();
    }
}
