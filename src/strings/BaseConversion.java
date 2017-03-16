package strings;

/**
 * Created by Creed on 1/25/17.
 */
public class BaseConversion {

    public static String base_to_decimal(String s, int base, int base2) {
        boolean isNeg = s.charAt(0) == '-';
        int decimal = 0, exp = 0;
        for (int i = s.length()-1; i >= (s.charAt(0) == '-' ? 1 : 0); i--) {
            int digit = s.charAt(i) - '0';
            decimal += digit * Math.pow(base, exp++);
        }
        return isNeg ? "-" : "" + constructFromBase(decimal, base2);
    }

    private static String constructFromBase(int x, int base) {
        return x == 0 ? "" : constructFromBase(x / base, base)
                + (char)(x % base >= 10 ? 'A' + x % base - 10 : '0' + x % base);
    }

}
