package org.lhx.algorithm.kmp;

import java.util.Arrays;

/**
 * @author lhx
 * @date 2019/7/15 - 15:23
 */
public class KMP {

    public static void main(String[] args) {
        String string1 = "BBC ABCDAB ABCDABCDABDE";
        String string2 = "ABCDABD";
        int[] ints = kmpNest(string2);
        System.out.println(Arrays.toString(ints));
        int kmpSearch = kmpSearch(string1, string2, ints);
        System.out.println(kmpSearch);
    }

    /**
     *kmp搜索算法
     * @param string1 原字符串
     * @param string2 字串
     * @param next 字串对应的部分匹配表
     * @return -1为没匹配,否则返回第一次匹配的位置
     */
    public static int kmpSearch(String string1, String string2, int[] next) {
        for (int i = 0, j = 0; i < string1.length(); i++) {
            //需要处理不相等时
            while (j > 0 && string1.charAt(i) != string2.charAt(j)) {
                j = next[j - 1];
            }
            if (string1.charAt(i) == string2.charAt(j)) {
                j++;
            }

            if (j == string2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取一个字符串的部分匹配值
    public static int[] kmpNest(String dest) {
        //创建一个next数组,保存部分匹配值
        int[] next = new int[dest.length()];
        //如果字符串长度为1 部分匹配值为1
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //如果dest.charAt(i) != dest.charAt(j),需要从next[j - 1]获取新的j
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
