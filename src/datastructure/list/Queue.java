package datastructure.list;

public class Queue<T> {

    private Node<T> head;

    private Node<T> tail;

    private int size = 0;

    public void push(T value) {
        Node<T> node = new Node<>();
        node.setValue(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T pop() {
        if (size == 0) {
            throw new RuntimeException("");
        }
        T res = head.getValue();
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
        size--;
        return res;
    }

    public int size() {
        return size;
    }

    private class Node<T> {
        T value;
        Node<T> next;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 7; i++) {
            queue.push(i);
        }
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            System.out.println(queue.pop());
        }
    }
}
