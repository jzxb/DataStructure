package org.lhx.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhx
 * @date 2019/6/21 - 8:14
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 4, 8, 22, 22, 22, 56, 845, 5546};
//        int index = binarySearch(arr, 0, arr.length - 1, 1);
//        System.out.println(index);
        List<Integer> integers = binarySearch2(arr, 0, arr.length, 22);
        System.out.println(integers);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (midVal > findVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (midVal < findVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (midVal > findVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else if (midVal < findVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {

            List<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }

            return resIndexList;
        }
    }

}
