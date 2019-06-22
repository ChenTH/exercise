package datastructure.find;

public class NormalSearch {
    public static void main(String[] args) {
        int[] array = {1, 22, 23, 34, 45, 76, 87, 88, 98, 99};
        int target = 450;
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                index = target;
                break;
            }
        }
        System.out.println(index);
    }
}
