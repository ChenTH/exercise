package datastructure.list;

public class Stack<T> {
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
            node.next = head;
            head = node;
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

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 7; i++) {
            stack.push(i);
        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(stack.pop());
        }
    }
}
