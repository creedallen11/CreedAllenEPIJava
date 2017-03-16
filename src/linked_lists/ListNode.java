package linked_lists;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Creed on 1/10/17.
 */
public class ListNode<T> {
    public T data;
    public ListNode<T> next;

    ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        Set<ListNode<T>> visited = new HashSet<ListNode<T>>();
        ListNode<T> iter = this;
        while (iter != null) {
            if (visited.contains(iter)) {
                result.append( "Loop back to " + data.toString());
                break;
            }
            visited.add(iter);
            result.append(iter.data.toString() + (iter.next != null ? " -> " : " "));
        }
        return result.toString();
    }
}
