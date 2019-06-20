package org.lhx.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lhx
 * @date 2019/6/20 - 20:46
 */
public class RadixSort {

    public static void main(String[] args) {
//        int[] arr = {3, 9, 1, 10, 2};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random() * 80000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date1);
        System.out.println("排序前时间" + format);

        radixSort(arr);

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后时间" + format2);
    }

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];

        //记录每个桶中存放了多少个数据
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            for (int j = 0; j < bucketElementCounts.length; j++) {
                if (bucketElementCounts[j] != 0) {
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        arr[index] = bucket[j][k];
                        index++;
                    }
                }
                bucketElementCounts[j] = 0;
            }
        }
    }

}
