package org.lhx.sort;

import java.util.Arrays;

/**
 * @author lhx
 * @date 2019/6/30 - 15:12
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            adjustHeap(arr, i,arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }

        System.out.println(Arrays.toString(arr));
    }

    //将一个数组（二叉树），调整成一个大顶堆

    /**
     * 完成将以i对应的非叶子结点的树调整成大顶堆
     *
     * @param arr    待调整的数组
     * @param i      非叶子结点在数组中的索引
     * @param length 表示对多少个元素继续调整，length是在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素的值保存在临时变量
        int temp = arr[i];
        //k = i * 2 + 1 是i个结点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //当前子结点大于父节点
            if (arr[k] > temp) {
                //把较大的值赋给当前结点
                arr[i] = arr[k];
                //i指向k，继续循环比较
                i = k;
            } else {
                break;
            }
        }
        //循环结束时，以i为父节点的最大值放在了最顶（局部）
        //将temp放在最后的位置
        arr[i] = temp;
    }

}
