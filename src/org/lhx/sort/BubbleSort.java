package org.lhx.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lhx
 * @date 2019/6/19 - 16:14
 */
public class BubbleSort {

    public static void main(String[] args) {
//        int[] array = {3, 9, -1, 10, -2};
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(array));
//        bubbleSort(array);
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

        bubbleSort(arr);

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后时间" + format2);
    }

    public static void bubbleSort(int[] array) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

}
