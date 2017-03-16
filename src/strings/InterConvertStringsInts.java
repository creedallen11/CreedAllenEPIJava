package strings;

/**
 * Created by Creed on 1/9/17.
 */
public class InterConvertStringsInts {

    public static String intToString(int x) {
        boolean isNegative = false;
        if (x < 0) {
            x = -x;
            isNegative = true;
        }

        StringBuilder s = new StringBuilder();
        do {
            s.append((char)('0' + x % 10));
            x /= 10;
        } while (x != 0);

        if (isNegative) {
            s.append('-');
        }

        s.reverse();
        return s.toString();
    }

    public static int stringToInt(String s) {
        boolean isNegative = s.charAt(0) == '-';
        int result = 0;
        for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
        }
        return isNegative ? -result : result;
    }
}
