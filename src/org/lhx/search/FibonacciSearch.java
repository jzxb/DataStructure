package org.lhx.search;

import java.util.Arrays;

/**
 * @author lhx
 * @date 2019/6/21 - 9:43
 */
public class FibonacciSearch {

    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr, 8));

    }

    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 非递归方式
     * @param arr 待查找数组
     * @param key 要找到关键码
     * @return 返回对应的下标，没有则返回-1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        //斐波那契分割数值对应的下标
        int k = 0;
        int mid = 0;
        int[] f = fib();
        while (high > f[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                //全部元素= 前面+后面
                //f[k] = f[k - 1] + f[k - 2]
                //因为前面有f[k-1]个元素，可以继续拆分f[k - 1] = f[k - 2] + f[k - 3]
                //即在f[k-1]的前面继续查找 即 k--
                //即下次循环mid = f[k - 1 - 1] - 1；
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                /*
                * 全部元素= 前面+后面
                * f[k] = f[k - 1] + f[k - 2]
                * 后面有f[k-2]个元素 所以继续拆分f[k - 2] = f[k - 3] + f[k - 4]
                * 即在f[k-2]前面继续查找
                * 即下次循环mid = f[k - 1 - 2] - 1；
                 */
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

}
