package search;

import java.util.List;

/**
 * Created by Creed on 1/12/17.
 */
public class BinarySearchFirstK {
// basically a binary search that doesnt quit on find but quits on covergence
    public static int searchFirstOfK(List<Integer> A, int k) {
        int left = 0, right = A.size() - 1, result = -1;

        // A.subList(left, right + 1) is the candidate set
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (A.get(mid) > k) {
                right = mid - 1;
            } else if (A.get(mid) == k) {
                result = mid;
                right = mid - 1; // Nothing after mid can be first occurrence
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
