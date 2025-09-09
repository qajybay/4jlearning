package berrx.algo.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;

public class LinkedListImpl {

    public static void main(String[] args) {
      LinkedList ll = new LinkedList(5);
        System.out.println(ll);
    }

}

@Data
class Node<T> {
    T value;
    Node next;

    Node(T value) {
        this.value = value;
    }
}

@Data
@AllArgsConstructor
class LinkedList<T> {
    private Node head;
    private Node tail;
    private int length;

    public LinkedList(T value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    private void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

}