package cn.fulong.bootdemo.utils;

public class SortTest {


    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 6, 8, 0, 3, 2, 5, 7};
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] > arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }


    }


}
