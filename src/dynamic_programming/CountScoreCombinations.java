package dynamic_programming;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Creed on 1/18/17.
 */
public class CountScoreCombinations {

    public static int numCombinationsForScore(int finalScore, List<Integer> individualPlayScores) {
        int[][] numCombinations = new int[individualPlayScores.size()][finalScore + 1];

        for (int i = 0; i < individualPlayScores.size(); i++) {
            numCombinations[i][0] = 0;

            for (int j = 1; j <= finalScore; j++) {
                int withoutThisPlay = i - 1 >= 0 ? numCombinations[i - 1][j] : 0; // num ways to make this score without new play
                int withThisPlay // num ways with score - the new play
                    = j >= individualPlayScores.get(i) ? numCombinations[i][j - individualPlayScores.get(i)] : 0;

                numCombinations[i][j] = withoutThisPlay + withThisPlay;
            }
        }

        return numCombinations[individualPlayScores.size()][finalScore];
    }

    private static void simpleTest() {
        List<Integer> individualPlayScores = Arrays.asList(2, 3, 7);
        assert(4 == numCombinationsForScore(12, individualPlayScores));
        assert(1 == numCombinationsForScore(5, individualPlayScores));
        assert(3 == numCombinationsForScore(9, individualPlayScores));
    }

    /* Brute force method of finding the number of ways to score */
    private static int checkAnswer(int totalScore, List<Integer> scoreWays) {
        int[] combinations = new int[totalScore + 1];
        combinations[0] = 1; // One way to reach 0.
        for (int score : scoreWays) {
            for (int j = score; j <= totalScore; ++j) {
                combinations[j] += combinations[j - score];
            }
        }
        return combinations[totalScore];
    }


}
