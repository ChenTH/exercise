package datastructure.list;

public class LinkList<T> {

    private Node root = new Node();
    private Node tail;
    private int size = 0;

    public void add(T value) {
        Node<T> node = new Node<>();
        node.setValue(value);
        if (root.next == null) {
            root.next = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public T get(int i) {
        if (i > size - 1 || i < 0) {
            throw new ArrayIndexOutOfBoundsException("");
        }
        Node node = root;
        for (int j = 0; j <= i; j++) {
            node = node.next;
        }
        return (T) node.getValue();
    }

    public void remove(int i) {
        if (i > size - 1 || i < 0) {
            throw new ArrayIndexOutOfBoundsException("");
        }
        Node node = root;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        node.next = node.next.next;
        size--;
    }

    private class Node<T> {
        T value;
        Node next;

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
        LinkList<Integer> list = new LinkList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
