package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Creed on 1/25/17.
 */
public class SpiralOrderingClockwise {

    private static List<List<Integer>> generateNxN(int N) {
        int accumulator = 1;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            result.add(new ArrayList<>());
            for (int j = 0; j < N; j++) {
                result.get(i).add(accumulator++);
            }
        }

        return result;
    }

    private static void getPerimeter(int offset, List<List<Integer>> A, List<Integer> result) {
        if (offset == (A.size() - offset - 1)) {
            result.add(A.get(offset).get(offset));
            return;
        }
        for (int i = offset; i < A.size() - 1 - offset; i++) {
            result.add(A.get(offset).get(i));
        }
        for (int i = offset; i < A.size() - 1 - offset; i++) {
            result.add(A.get(i).get(A.size() - 1 - offset));
        }
        for (int i = A.size() - 1 - offset; i > offset; i--) {
            result.add(A.get(A.size() - 1 - offset).get(i));
        }
        for (int i = A.size() - 1 - offset; i > offset; i--) {
            result.add(A.get(i).get(offset));
        }
    }

    public static void findSpiralClockWise(List<List<Integer>> A) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < Math.ceil(A.size() * 0.5); i++) {
            getPerimeter(i, A, result);
        }
        System.out.println(result);
    }

    public static void findSpiralClockWiseMarch(List<List<Integer>> A) {
        // march through the n x n array to the center using shifts

    }

    public static void main(String[] args) {
        findSpiralClockWise(generateNxN(4));
    }
}
