package primitive_types;

/**
 * Created by Creed on 1/22/17.
 */
public class PowerXY {

    public static double recur_power(double x, int y) {
        if (y == 1) {
            return x;
        } else if (y % 2 == 0) {
            return Math.pow(recur_power(x, y / 2), 2);
        } else {
            return x * Math.pow(recur_power(x, y / 2), 2);
        }
    }

    public static double iter_power(double x, int y) {
        double result = 1.0;
        long power = y;
        if (y < 0) {
            power = -power;
            x = 1.0 / x;
        }
        while (power != 0) {
            if ((power & 1) != 0) {
                result *= x;
            }
            x *= x;
            power >>>= 1;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(recur_power(5, 11));
    }
}
