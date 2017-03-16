package arrays;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Creed on 1/22/17.
 */
public class SampleOfflineData {


    public static void randomSampleK(List<Integer> A, int k) {
        Random gen = new Random();
        for (int i = 0; i < k; i++) {
            Collections.swap(A, i, i + gen.nextInt(A.size() - i));
        }
    }
}
