package datastructure.list;

import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class ArrayList<T> {

    private Object[] list;
    private int index = 0;

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            capacity = 8;
        }
        list = new Object[capacity];
    }

    public void add(T obj) {
        list[index] = obj;
        index++;
        if (index >= list.length) {
            resize();
        }
    }

    private void resize() {
        Object[] newLise = new Object[list.length * 2 / 3];
        System.arraycopy(list, 0, newLise, 0, index);
        list = newLise;
    }

    public T get(int i) {
        if (i < index) {
            return (T) list[i];
        } else {
            throw new ArrayIndexOutOfBoundsException("");
        }
    }

    public void remove(int i) {
        if (i >= index) {
            throw new ArrayIndexOutOfBoundsException("");
        }
        for (int j = i; j < index - 1; j++) {
            list[j] = list[j + 1];
        }
        list[index - 1] = null;
        index--;
    }

    public int size() {
        return index;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>(10);
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
