package hash_tables;

import java.util.HashMap;

/**
 * Created by Creed on 1/12/17.
 */
public class AnonymousLetter {

    public static boolean isLetterConstructable(String letterText, String magazineText) {

        HashMap<Character, Integer> charFrequency = new HashMap<>();
        /* Map all the characters in the letter */
        for (int i = 0; i < letterText.length(); i++) {
            char c = letterText.charAt(i);
            if (charFrequency.containsKey(c)) {
                charFrequency.put(c, charFrequency.get(c) + 1);
            } else {
                charFrequency.put(c, 1);
            }
        }
        /* For characters in the magazine text, if they're in the letter decrament the map for that char
        and update the freq. Do this until the required character map is empty or all characters in the magazine have
        been visited.
         */
        for (char c : magazineText.toCharArray()) {
            if (charFrequency.containsKey(c)) {
                charFrequency.put(c, charFrequency.get(c) - 1);

                if (charFrequency.get(c) == 0) {
                    charFrequency.remove(c);
                    // early termination check
                    if (charFrequency.isEmpty()) {
                        break;
                    }
                }

            }

        }
        /* This will be empty if the magazine's characters satisfied the letter requirements. */
        return charFrequency.isEmpty();
    }


    private static void check(String letter, String magazine, boolean expected) {
        if (isLetterConstructable(letter, magazine) != expected) {
            System.err.println("Program incorrectly claims " + (letter.length() > 0 ? letter : "the empty string ")
                    + " is " + (expected ? "not" : "") + " construcable from" + magazine);
            System.exit(-1);
        }
    }

    private static void simpleChecks() {
        check("", "", true);
        check("123", "456", false);
        check("creed", "creeeed", true);

    }
    public static void main(String[] args) {
        simpleChecks();
    }
}
