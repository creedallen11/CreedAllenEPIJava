package primitive_types;

/**
 * Created by Creed on 1/6/17.
 */
public class Parity {

    /* Check each bit. O(n) where n is the number of bits */
    public static short parity(long x) {
        short result = 0;
        while (x != 0) {
            result ^= (x & 1);
            x >>>= 1;
        }
        return result;
    }

    static String longToBinaryString(long L) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < Long.numberOfLeadingZeros(L); i++){
            sb.append('0');
        }
        sb.append(Long.toBinaryString(L));
        return sb.toString();
    }

    static void unitTest(long L, short expectedParity) {
        short result = parity(L);
        if (result != expectedParity) {
            System.err.println("Wrong result for " + longToBinaryString(L));
            System.err.println("Expected " + expectedParity);
            System.err.println("Got " + result);
            System.exit(-1);
        }
    }

    /* The slowest implementation, checks every bit. O(n). */
    private static short parity_bit_by_bit(long x) {
        short result = 0;
        for (int i = 0; i < 64; i++) {
            result ^= 1 % (x >> i);
        }
        return result;
    }


    /* Checks only the set bits of x. O(k) where k is the number of set bits */
    private static short parity_bit_by_bit_smart(long x) {
        short result = 0;
        while (x != 0) {
            result ^= 1;
            x &= (x - 1);
        }
        return result;
    }

    /* Takes advantage of associativity of XOR. O(log n) where n is the number of bits */
    private static short parity_associative(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return (short) (0x1 & x);
    }

    /* The following uses a lookup table and O(2**16) space. */

    /* Precompute the parity of the first 2**16 possible longs. */
    static short[] precomputed;

    static {
        precomputed = new short[1 << 16];
        for (int i = 0; i < 1 << 16; i++)
            precomputed[i] = Parity.parity(i);
    }

    public static short parity_table(long x) {
        final int WORD_SIZE = 16;
        final int BIT_MASK = 0xFFFF;
        int offset = WORD_SIZE;

        short result = precomputed[(int) x & BIT_MASK];
        x = x >>> offset;

        result ^=  precomputed[(int) x & BIT_MASK];
        x = x >>> offset;

        result ^=  precomputed[(int) x & BIT_MASK];
        x = x >>> offset;

        result ^=  precomputed[(int) x & BIT_MASK];
        return result;
    }


    static long[] testCase;
    static int N = 100000;
    static {
        testCase = new long[N];
        for (int i = 0; i < N; i++) {
            testCase[i] = (i | i << 16 | ~(i << 32) | i << 48);
        }
    }

    static final long SCALE = 1000000L;

    private static long stressTestSolution() {
        long startTime = System.nanoTime();
        long totalXor = 0;
        for (int i = 0; i < N; i++) {
            totalXor ^= parity(testCase[i]);
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    private static long stressTestBitByBit() {
        long startTime = System.nanoTime();
        long totalXor = 0;
        for (int i = 0; i < N; i++) {
            totalXor ^= parity_bit_by_bit(testCase[i]);
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    private static long stressTestBitByBitSmart() {
        long startTime = System.nanoTime();
        long totalXor = 0;
        for (int i = 0; i < N; i++) {
            totalXor ^= parity_bit_by_bit_smart(testCase[i]);
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    private static long stressTestAssociative() {
        long startTime = System.nanoTime();
        long totalXor = 0;
        for (int i = 0; i < N; i++) {
            totalXor ^= parity_associative(testCase[i]);
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    private static long stressTestTable() {
        long startTime = System.nanoTime();
        long totalXor = 0;
        for (int i = 0; i < N; i++) {
            totalXor ^= parity_table(testCase[i]);
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public static void compareApproaches() {
        long bitByBitTime = stressTestBitByBit();
        long bitByBitSmartTime = stressTestBitByBitSmart();
        long tableTime = stressTestTable();
        long associativeTime = stressTestAssociative();

        System.out.println(
            "bit-by-bit, bit-smart, table, associative = " + bitByBitTime / SCALE
            + ", " + bitByBitSmartTime / SCALE + ", "
            + tableTime / SCALE + ", " + associativeTime / SCALE );

    }

    public static void stressTest() {
        long bitByBitTime = stressTestBitByBit();
        long userSolutionTime = stressTestSolution();
        long tableTime = stressTestTable();
        if (userSolutionTime > 2 * tableTime) {
            System.err.println("Your program fails too slow.");
            System.err.println("Your program took " + userSolutionTime / SCALE
                + " milliseconds for " + N + " inputs.");
            System.err.println("Your target time should be less than "
                + 2 * tableTime / SCALE + " milliseconds.");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        compareApproaches();

        unitTest(
            0b10000000000000000000000000000000L, (short)1);
//        stressTest();
    }

}

