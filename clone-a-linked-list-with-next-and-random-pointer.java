// https://www.geeksforgeeks.org/problems/clone-a-linked-list-with-next-and-random-pointer/1

/*
class Node {
    int data;
    Node next;
    Node random;

    Node(int x) {
        data = x;
        next = null;
        random = null;
    }
}
*/
class Solution {
    public Node cloneLinkedList(Node head) {
        // code here
        // Step 1 create a bridge.
        Node head2 = null;
        Node temp = head;
        while(temp != null) {
            Node newNode = new Node(temp.data);
            if(head2 == null) {
                head2 = newNode;
            }
            Node hold = temp.next;
            newNode.next = temp.next;
            temp.next = newNode;
            newNode.random = temp;
            temp = hold;
        }
        
        // Step 2 make random pointers in clone.
        temp = head;
        while(temp != null) {
            Node newNode = temp.next;
            Node oldRandom = temp.random;
            Node newRandom = null;
            if(temp.random != null)
                newRandom = temp.random.next;
            newNode.random = newRandom;
            temp = newNode.next;
        }
        
        // Step 3 reassign next edge in old and new
        temp = head;
        while(temp != null) {
            Node newNode = temp.next;
            Node oldNext = newNode.next;
            Node hold = oldNext;
            if(oldNext != null) {
                Node newNext = oldNext.next;
                temp.next = oldNext;
                newNode.next = newNext;
            }
            else {
                temp.next = oldNext;
                newNode.next = null;
            }
            temp = hold;
        }
        return head2;
    }
}
