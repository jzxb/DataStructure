package org.lhx.search;

/**
 * @author lhx
 * @date 2019/6/21 - 9:16
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(i);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || arr[0] > findVal || arr[arr.length - 1] < findVal) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (midVal < findVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if(midVal > findVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

}
