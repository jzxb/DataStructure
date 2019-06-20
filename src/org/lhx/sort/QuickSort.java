package org.lhx.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lhx
 * @date 2019/6/20 - 16:31
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 10, -2};
//        int[] arr = new int[800000];
//        for (int i = 0; i < 800000; i++) {
//            arr[i] = (int)(Math.random() * 80000);
//        }
//
//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(date1);
//        System.out.println("排序前时间" + format);
        quickSort(arr,0,arr.length - 1);
//        Date date2 = new Date();
//        String format2 = simpleDateFormat.format(date2);
//        System.out.println("排序后时间" + format2);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(l + r) / 2];
        int temp = 0;
        while (l < r) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }

        if (right > l) {
            quickSort(arr, l, right);
        }
    }

}
