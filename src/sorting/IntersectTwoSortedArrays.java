package sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Creed on 1/12/17.
 */
public class IntersectTwoSortedArrays {

    public static List<Integer> intersectTwoSortedArrays(List<Integer> A, List<Integer> B) {
        int i = 0, j = 0;
        List<Integer> intersection = new ArrayList<>();

        while (i <= A.size() && j <= B.size()) {

            if (A.get(i) == B.get(j) && (i == 0 || A.get(i) != A.get(i - 1))) {
                // add this value. incrament both
            } else if (A.get(i) < B.get(j)) {
                // incrament i,
                i++;
            } else {
                // i is greater than j, incrament j since its current cant be in the intersection or has
                // already been added
                j++;
            }
        }
        return intersection;
    }


    private static void check(List<Integer> A, List<Integer> B, List<Integer> expected) {
        // todo:
    }

    public static void main(String[] args) {
        // write some basic checks
    }
}
