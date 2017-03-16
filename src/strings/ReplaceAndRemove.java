package strings;

/**
 * Created by Creed on 1/26/17.
 */
public class ReplaceAndRemove {

    public static int replaceAndRemove(char[] s, int size) {
        int write_index = 0, aCount = 0;
        for (int i = 0; i < size; i++) {
            if (s[i] != 'b') {
                s[write_index++] = s[i];
            }
            if (s[i] == 'a') {
                aCount++;
            }
        }

        int current_index = write_index - 1;
        write_index = write_index + aCount - 1;
        int final_size = write_index + 1;

        while (current_index >= 0) {
            if (s[current_index] == 'a') {
                s[write_index--] = 'd';
                s[write_index--] = 'd';
            } else {
                s[write_index--] = s[current_index];
            }
            current_index--;
        }
        return final_size;
    }

    public static void main(String[] args) {
        char[] test = {'a','b','b','a','c','c'};
        System.out.println(test);
        System.out.println(replaceAndRemove(test, test.length));
        System.out.println(test);
    }
}
