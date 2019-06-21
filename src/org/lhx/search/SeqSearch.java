package org.lhx.search;

/**
 * @author lhx
 * @date 2019/6/21 - 7:43
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = { 1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr,11);
        if (index == -1) {
            System.out.println("未找到");
        } else {
            System.out.println("找到下标为：" + index);
        }
    }

    /**
     * 找到一个满足条件的就返回
     * @param arr 待查找数组
     * @param value 要查找的值
     * @return 返回查找到的值在数组中的下标
     */
    public static int seqSearch(int[] arr,int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
