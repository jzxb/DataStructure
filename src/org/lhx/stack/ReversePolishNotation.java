package org.lhx.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lhx
 * @date 2019/6/18 - 13:36
 */
public class ReversePolishNotation {

    public static void main(String[] args) {
        String suffixExpression = "30 4 + 5 * 6 -";
        List<String> listString = getListString(suffixExpression);
        System.out.println(listString);
        int res = cal(listString);

        System.out.println(res);
    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> res = new ArrayList<>();
        for (String s : split) {
            res.add(s);
        }
        return res;
    }

    public static int cal(List<String > list) {
        Stack<String> strings = new Stack<>();
        for (String s : list) {
            //使用正则表达式取数，匹配多位数
            if (s.matches("\\d+")) {
                strings.push(s);
            } else {
                int num2 = Integer.parseInt(strings.pop());
                int num1 = Integer.parseInt(strings.pop());
                int res = 0;
                if (s.equals("+")) {
                    res = num1 + num2;
                } else if (s.equals("-")) {
                    res = num1 - num2;
                } else if (s.equals("*")) {
                    res = num1 * num2;
                } else if (s.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("符号有误");
                }
                strings.push("" + res);
            }
        }
        return Integer.parseInt(strings.pop());
    }

}
