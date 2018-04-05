public class DeleteNodeSinglyLinkedList {
	
    public static class LinkedListNode {
        String value;
        LinkedListNode next;
        
        public LinkedListNode(String s) {
            this.value = s;
            this.next = null;
        }
    }
    
    public static boolean deleteNode(LinkedListNode node) {
        if (node.next == null) {
                // node is the last element in the linked list so cannot be deleted.
                return false;
        }
        node.value = node.next.value;
        node.next = node.next.next;
        return true;
    }

    public static void main(String[] args) {
        LinkedListNode a = new LinkedListNode("a");
        LinkedListNode b = new LinkedListNode("b");
        LinkedListNode c = new LinkedListNode("c");	
        a.next = b;
        b.next = c;
        
        System.out.println("Deleting node \"b\" returned: " + deleteNode(b));
        System.out.println("After deleting \"b\", a->next is now: " + a.next.value);
        System.out.println("Deleting node \"c\" returned: " + deleteNode(c));
    }
}
