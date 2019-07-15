package org.lhx.algorithm.kmp;

/**
 * @author lhx
 * @date 2019/7/15 - 14:36
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String string1 = "jzxbxbxbsj sa fsalkhg asgfhasliowq hasg";
        String string2 = "hg asgfhaslio";
        int i = violenceMatch(string1, string2);
        System.out.println(i);
    }

    //暴力匹配算法实现
    public static int violenceMatch(String string1,String string2) {
        char[] s1 = string1.toCharArray();
        char[] s2 = string2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        //索引指向s1
        int i = 0;
        //索引指向s2
        int j = 0;
        //保证匹配时不越界
        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        //判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }

}
