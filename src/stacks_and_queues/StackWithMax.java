package stacks_and_queues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Creed on 1/10/17.
 */
public class StackWithMax {

    private static class ElementWithCachedMax {
        public Integer element;
        public Integer max;

        public ElementWithCachedMax(Integer element, Integer max) {
            this.element = element;
            this.max = max;
        }
    }

    public static class Stack {
        // Stores (element, cached max) pair.
        private Deque<ElementWithCachedMax> elementWithCachedMax = new LinkedList<>();

        public boolean empty() { return elementWithCachedMax.isEmpty(); }

        public Integer max() {
            if (empty()) {
                throw new IllegalStateException("max(): empty stack");
            }
            return elementWithCachedMax.peek().max;
        }

        public Integer pop() {
            if (empty()) {
                throw new IllegalStateException("pop(): empty stack");
            }
            return elementWithCachedMax.removeFirst().element;
        }

        public void push(Integer x) {
            elementWithCachedMax.addFirst(new ElementWithCachedMax(x, Math.max(x, empty() ? x : max())));
        }
    }

    public static void check(boolean condition, String msg) {
        if (!condition) {
            System.err.println(msg);
            System.exit(-1);
        }
    }
}