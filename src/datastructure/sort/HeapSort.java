package datastructure.sort;

public class HeapSort {
    public static void main(String[] args) {
        int length = 1000;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
        minHeapSort(array);
        for (int i = 0; i < length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void minHeapSort(int[] array) {
        for (int i = array.length / 2 + 1; i >= 0; i--) {
            adjustMinHeap(array, i, array.length);
        }
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            adjustMinHeap(array, 0, i);
        }
        for (int i = 0; i < array.length / 2; i++) {
            swap(array, i, array.length - 1 - i);
        }
    }

    private static void adjustMinHeap(int[] array, int beg, int end) {
        for (int i = beg * 2 + 1; i < end; i = i * 2 + 1) {
            if (i + 1 < end && array[i] > array[i + 1]) {
                i = i + 1;
            }
            if (array[beg] > array[i]) {
                swap(array, i, beg);
                beg = i;
            } else {
                return;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
