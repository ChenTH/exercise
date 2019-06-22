package datastructure.find;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 22, 23, 34, 45, 76, 87, 88, 98, 99};
        int target = 450;
        int index = -1;
//        index = binarySearchRecursion(array, 0, array.length - 1, target);
        index = binarySearchNormal(array, 0, array.length - 1, target);
        System.out.println(index);
    }

    public static int binarySearchRecursion(int[] array, int beg, int end, int target) {
        if (end < beg) {
            return -1;
        }
        int mid = (beg + end) / 2;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearchRecursion(array, mid + 1, end, target);
        } else {
            return binarySearchRecursion(array, beg, mid - 1, target);
        }
    }

    public static int binarySearchNormal(int[] array, int beg, int end, int target) {
        int res = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if (array[mid] == target) {
                res=mid;
                break;
            } else if (array[mid] < target) {
                beg = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }
}
