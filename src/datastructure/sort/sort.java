package datastructure.sort;

public class sort {

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
            System.out.print(array[i] + " ");
        }
        System.out.println();
//        int[] sortArray = new int[array.length];
//        System.arraycopy(array, 0, sortArray, 0, array.length);

//        System.out.println("冒泡排序开始时间" + System.currentTimeMillis());
//        bubbleSort(sortArray);
//        System.out.println("冒泡排序结束时间" + System.currentTimeMillis());
//        System.arraycopy(array, 0, sortArray, 0, array.length);
//        System.out.println("选择排序开始时间" + System.currentTimeMillis());
//        selectionSort(array);
//        System.out.println("选择排序结束时间" + System.currentTimeMillis());
//        System.arraycopy(array, 0, sortArray, 0, array.length);
//        System.out.println("插入排序开始时间" + System.currentTimeMillis());
//        insertionSort(array);
//        System.out.println("插入排序结束时间" + System.currentTimeMillis());
//        System.arraycopy(array, 0, sortArray, 0, array.length);
//        System.out.println("快速排序开始时间" + System.currentTimeMillis());
//        quickSort(array);
//        System.out.println("快速排序结束时间" + System.currentTimeMillis());
//        System.arraycopy(array, 0, sortArray, 0, array.length);
//        System.out.println("堆排序开始时间" + System.currentTimeMillis());
//        maxHeapSort(array);
//        System.out.println("堆排序结束时间" + System.currentTimeMillis());
        minHeapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /***
     * 冒泡排序
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /***
     * 选择排序
     */
    public static void selectionSort(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            int index = 0;
            for (int j = 1; j <= i; j++) {
                if (array[index] < array[j]) {
                    index = j;
                }
            }
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    /***
     * 插入排序
     */
    public static void insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            int index = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > temp) {
                    array[j + 1] = array[j];
                    index = j;
                } else {
                    break;
                }
            }
            array[index] = temp;
        }
    }

    /***
     * 希尔排序
     */
    public static void shellSort(int[] array) {

    }

    /***
     * 归并排序
     */
    public static void mergeSort(int[] array) {

    }

    /***
     * 快速排序
     */
    public static void quickSort(int[] array) {
        quickSortPart(array, 0, array.length - 1);
    }

    private static void quickSortPart(int[] array, int head, int tail) {
        if (head >= tail) {
            return;
        }
        int temp = array[head];
        int beg = head;
        int end = tail;
        while (beg < end) {
            while (beg < end && array[end] > temp) {
                end--;
            }
            if (beg < end) {
                array[beg] = array[end];
            }
            while (beg < end && array[beg] <= temp) {
                beg++;
            }
            if (beg < end) {
                array[end] = array[beg];
            }
        }
        array[beg] = temp;
        quickSortPart(array, head, beg - 1);
        quickSortPart(array, beg + 1, tail);
    }

    /***
     * 堆排序(大根堆)
     */
    public static void maxHeapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustMaxHeap(array, i, array.length);
        }

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            adjustMaxHeap(array, 0, i);
        }
    }

    private static void adjustMaxHeap(int[] array, int index, int length) {
        for (int i = index * 2 + 1; i < length; i = i * 2 + 1) {
            if (i + 1 < length && array[i] < array[i + 1]) {
                i = i + 1;
            }
            if (array[index] < array[i]) {
                swap(array, index, i);
                index = i;
            } else {
                return;
            }
        }
    }

    /***
     * 堆排序(小根堆)
     */
    public static void minHeapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustMinHeap(array, i, array.length);
        }

        //在topk算法中此处加入和堆顶的比较逻辑
        int[] otherElems = new int[100];
        for (int i = 0; i < otherElems.length; i++) {
            otherElems[i] = (int) (Math.random() * 100);
        }
        for (int i = 0; i < otherElems.length; i++) {
            if (otherElems[i] > array[0]) {
                array[0] = otherElems[i];
                adjustMinHeap(array, 0, array.length);
            }
        }

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            adjustMinHeap(array, 0, i);
        }
    }

    private static void adjustMinHeap(int[] array, int index, int length) {
        for (int i = index * 2 + 1; i < length; i = i * 2 + 1) {
            if (i + 1 < length && array[i] > array[i + 1]) {
                i = i + 1;
            }
            if (array[index] > array[i]) {
                swap(array, index, i);
                index = i;
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
