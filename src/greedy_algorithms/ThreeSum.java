package greedy_algorithms;

import java.util.Collections;
import java.util.List;

/**
 * Created by Creed on 1/18/17.
 */
public class ThreeSum {
    public static boolean hasThreeSum(List<Integer> A, int t) {
        Collections.sort(A);
        for (Integer a : A) {
            // find if the sum of two numbers in A equals t - a
            if (hasTwoSum(A, t - a)) {
                return true;
            }
        }
        return false;
    }

    // O(n) solution to two sum problem
    public static boolean hasTwoSum(List<Integer> A, int t) {
        int i = 0, j = A.size() - 1;
        while (i <= j) {
            if (A.get(i) + A.get(j) == t) {
                return true;
            } else if (A.get(i) + A.get(j) < t) {
                ++i;
            } else { // A[i] + A[j] > t.
                --j;
            }
        }
        return false;
    }
}
