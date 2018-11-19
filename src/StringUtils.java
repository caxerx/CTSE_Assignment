public class StringUtils {
    public static String padLeft(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        for (int i = 0; i < length - str.length(); i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
