package org.lhx.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lhx
 * @date 2019/6/20 - 10:13
 */
public class SelectSort {

    public static void main(String[] args) {
//        int[] array = {3, 9, -1, 10, -2};
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(array));
//        selectSort(array);
//
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(array));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 80000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date1);
        System.out.println("排序前时间" + format);

        selectSort(arr);

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后时间" + format2);
    }

    public static void selectSort(int arr[]){

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for(int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

    }

}
