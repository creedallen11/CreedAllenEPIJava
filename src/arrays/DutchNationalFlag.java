package arrays;
/**
 * Created by Creed on 1/9/17.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class DutchNationalFlag {

    public enum Color {RED, WHITE, BLUE}

    public static void slowInPlacePartition(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);

        // First pass group elements smaller than the pivot to the front.
        for (int i = 0; i < A.size(); ++i) {
            // Look for a smaller element.
            for (int j = i + 1; j < A.size(); ++j) {
                if (A.get(j).ordinal() < pivot.ordinal()) {
                    Collections.swap(A, i, j);
                    break;
                }
            }
        }

        // Second pass groups elements larger than the pivot.
        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal(); --i) {
            // Look for a larger element. Stop when we reach an element
            // less than the pivot since first pass has moved them to start of A.
            for (int j = i - 1; j >= 0 && A.get(j).ordinal() >= pivot.ordinal(); j--)
            {
                if (A.get(j).ordinal() > pivot.ordinal()) {
                    Collections.swap(A, i, j);
                    break;
                }
            }
        }
    }

    public static void twoPassInPlacePartition(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);
        // First pass will group elements less than the pivot.
        int smaller = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).ordinal() < pivot.ordinal()) {
                Collections.swap(A, smaller++, i);
            }
        }

        // Second pass groups elements larger than pivot.
        int larger = A.size() - 1;
        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal(); i--) {
            if (A.get(i).ordinal() > pivot.ordinal()) {
                Collections.swap(A, larger--, i);
            }
        }
    }

    public static void fastInPlacePartition(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);

        int smaller = 0, equal = 0, larger = A.size();

        /* Maintains following invariant:
        L.S. = [0, smaller)
        Equal = [equal, larger)
        G. T = [larger, n)
         */
        while (equal < larger) {
            if (A.get(equal).ordinal() < pivot.ordinal()) {
                Collections.swap(A, smaller++, equal++);
            }
            else if (A.get(equal).ordinal() == pivot.ordinal()) {
                equal++;
            }
            else {
                Collections.swap(A, equal, --larger);
            }

        }
    }

    /* Generate a list of random colors of length len */
    private static List<Color> randArray(int len) {
        Random r = new Random();
        List<Color> ret = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            ret.add(Color.values()[r.nextInt(3)]);
        }
        return ret;
    }

    public static void main(String[] args) {
        Random gen = new Random();

        for (int times = 0; times < 1000; ++times) {
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = gen.nextInt(100) + 1;
            }

            List<Color> A = randArray(n);

            int pivotIndex = gen.nextInt(n);
            Color pivot = A.get(pivotIndex);

            fastInPlacePartition(pivotIndex, A);

            int i = 0;
            while (i < n && A.get(i).ordinal() < pivot.ordinal()) {
                System.out.print(A.get(i) + " ");
                i++;
            }
            while (i < n && A.get(i) == pivot) {
                System.out.print(A.get(i) + " ");
                i++;
            }
            while (i < n && A.get(i).ordinal() > pivot.ordinal()) {
                System.out.print(A.get(i) + " ");
                i++;
            }
            System.out.println();

            assert(i == n);

        }
    }
}
