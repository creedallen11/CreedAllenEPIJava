package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Creed on 1/9/17.
 */
public class BuyAndSellOnce {

    /*
    Find the max profit in O(n) / O(1) noting that the maximum profit at each day will be the difference of the
    minimum seen so far and the max so far. Maintain the minimum to accomplish this.
     */
    public static double maxProfit(List<Double> prices) {
        double minSoFar = Double.MAX_VALUE;
        double bestProfitSoFar = 0;
        for (Double price: prices) {
            minSoFar = Double.min(minSoFar, price);
            bestProfitSoFar = Double.max(price - minSoFar, bestProfitSoFar);
        }
        return bestProfitSoFar;
    }

    /* Brute force check */
    private static double checkAnswer(List<Double> prices) {
        double bestProfit = 0;
        for (int i = 0; i < prices.size(); i++) {
            for (int j = i; j < prices.size(); j++ ) {
                bestProfit = Double.max(bestProfit, prices.get(j) - prices.get(i));
            }
        }
        return bestProfit;
    }

    public static void main(String[] args) {
        List<Double> prices = Stream.of(310.0, 315.0, 275.0, 295.0, 260.0, 270.0, 290.0, 230.0, 255.0, 250.0).collect(Collectors.toList());
//        System.out.println(checkAnswer(prices) + " " + maxProfit(prices));

        Random gen = new Random();
        for (int times = 0; times < 1000; times++) {
            int n = gen.nextInt(10000) + 1;


            List<Double> A = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                A.add(gen.nextDouble() * 1000);
            }
            System.out.println(maxProfit(A));
            assert(Double.compare(checkAnswer(A), maxProfit(A)) == 0);
        }
    }
}
