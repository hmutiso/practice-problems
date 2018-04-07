import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import static org.junit.Assert.*;

/*
 * Implement a Queue using Stacks.
 */
public class QueueWithStacks {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public QueueWithStacks() {
        inStack = new Stack<Integer>();
        outStack = new Stack<Integer>();
    }

    public class StackFullException extends RuntimeException {
        public StackFullException(String s) {
            super(s);
        }
    }

    /*
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int dequeue() {
        if (!outStack.isEmpty()) {
            return outStack.pop();
        } else if (!inStack.isEmpty()) {
            // pop all elts from the inStack and push them to the outStack
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
            // now pop from outStack and return
            return outStack.pop();
        } else {
            // both inStack and outStack are empty
            throw new StackFullException("Cannot dequeue from a full stack!");
        }
    }

    /*
     * Time complexity: O(1)
     * Space complexity: O(1)
     */
    public void enqueue(int val) {
        inStack.push(val);
    }

    /*
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public void enqueue_old(int val) {
        int curr;
        while (!outStack.isEmpty()) {
            curr = outStack.pop();
            inStack.push(curr);
        }
        inStack.push(val);
    }

    public static void main(String[] args) {
        /*
         * Test the "stackQueue" by performing a sequence of operations, both
         * on the "stackQueue" and the actual queue. Then verify the dequeue
         * operations on both queues have the same results.
         */
        Queue<Integer> queue = new LinkedList<Integer>();
        QueueWithStacks stackQueue = new QueueWithStacks();

        // Enqueue 3 elements
        int[] elts = new int[] {10, 20, 30};
        for (int i=0; i < elts.length; i++) {
            stackQueue.enqueue(elts[i]);
            queue.add(elts[i]);
        }

        // Dequeue 2 elements
        for (int i=0; i < 2; i++) {
            assertTrue(stackQueue.dequeue() == queue.remove());
        }

        // Enqueue 1 more element
        stackQueue.enqueue(40);
        queue.add(40);

        // Dequeue 1 more element
        assertTrue(stackQueue.dequeue() == queue.remove());

        System.out.println("All tests passed!");
    }
}
