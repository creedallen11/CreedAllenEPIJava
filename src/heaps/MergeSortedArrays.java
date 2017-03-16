package heaps;

import java.util.*;

/**
 * Created by Creed on 1/12/17.
 */
public class MergeSortedArrays {

    /* Used to remember which iterator the value in the heap came from */
    private static class ArrayEntry {
        Integer value;
        public Integer arrayId;


        public ArrayEntry(Integer value, Integer arrayID) {
            this.value = value;
            this.arrayId = arrayID;
        }
    }

    public static List<Integer> mergeSortedArrays(
        List<List<Integer>> sortedArrays) {

        List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
        for (List<Integer> array : sortedArrays) {
            iters.add(array.iterator()); // add the first item from each
        }

        // java 8 llambda, note to make it a max heap swap the comparison
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<ArrayEntry>(sortedArrays.size(), (x,y) -> x.value-y.value);

        // initialize the minheap
        for (int i = 0; i < iters.size(); i++) {
            if (iters.get(i).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(i).next(), i));
            }
        }

        // note : result could just write to a file instead or something (assuming space matters)
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ArrayEntry headEntry = minHeap.poll();
            result.add(headEntry.value);
            if (iters.get(headEntry.arrayId).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(headEntry.arrayId).next(),
                        headEntry.arrayId));
            }
        }
        return result;
    }

    private static void check(List<List<Integer>> S, List<Integer> ans,
        List<Integer> golden) {

        if (!ans.equals(golden)) {
            System.err.println("Your program failed on input " + S);
            System.err.println("Expected " + golden);
            System.err.println("Got " + ans);
            System.exit(-1);
        }
    }

    private static void simpleTest() {
        List<List<Integer>> S
            = Arrays.asList(Arrays.asList(1, 5, 10), Arrays.asList(2, 3, 100),
                Arrays.asList(2, 12, Integer.MAX_VALUE));
        List<Integer> ans = mergeSortedArrays(S);
        List<Integer> golden
            = Arrays.asList(1, 2, 2, 3, 5, 10, 12, 100, Integer.MAX_VALUE);
        check(S, ans, golden);

    }

    public static void main(String[] args) {
        simpleTest();

        // more complicated test:
        // build N random lists of lists
        // send each through the program
        // brute force check that they are ascending
    }
}
