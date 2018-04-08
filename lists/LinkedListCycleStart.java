import static org.junit.Assert.*;

public class LinkedListCycleStart {

    public class LinkedListNode {
        private char value;
        private LinkedListNode next;
        
        public LinkedListNode(char value) {
            this.value = value;
        }
    }
    
    /*
     * This routine returns the first node in a linked list cycle. If the
     * linked list has no cycle, this routine returns 'null'.
     */
    public LinkedListNode findLinkedListCycleStart(LinkedListNode head) {
        if (head == null) {
            return null;
        }
        LinkedListNode slow = head;
        LinkedListNode fast = head;
        
        /*
         * Move the fast 'pointer' two nodes forward each iteration, and
         * move the slow 'pointer' one node forward each time. If the
         * pointers meet, then the linked list has a cycle.
         */
        while (true) {
            slow = slow.next;
            if (fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            if (fast == slow) {
                // Found a cycle in this linked list
                break;
            }
        }
        /*
         * Now move the fast pointer back to the head of the linked list,
         * and keep the slow pointer at the node where the two pointers
         * met. Then, move both pointers one node at a time. The next
         * node at which the two pointers meet at will be the start of
         * the linked list cycle. 
         * For an explanation of why this works, see the StackOverflow
         * link here: https://stackoverflow.com/a/6110767
         */
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    
    public static void main(String[] args) {
        LinkedListCycleStart loop = new LinkedListCycleStart();
        /*
         * Test 1: Linked list has a cycle starting at node 'c':
         */
        LinkedListNode a = loop.new LinkedListNode('a');
        LinkedListNode b = loop.new LinkedListNode('b');
        LinkedListNode c = loop.new LinkedListNode('c');
        LinkedListNode d = loop.new LinkedListNode('d');
        LinkedListNode e = loop.new LinkedListNode('e');
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = c;
        
        assertSame(c, loop.findLinkedListCycleStart(a));
        
        /*
         * Test 2: Linked list has no cycle. Should return null.
         */
        e.next = null;
        assertNull(loop.findLinkedListCycleStart(a));
        
        System.out.println("All tests passed!");
    }
}
