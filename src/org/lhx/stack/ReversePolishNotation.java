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
        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpression(expression);
        System.out.println(strings);
        List<String> strings1 = parseSuffixExpressionList(strings);
        System.out.println(strings1);
        System.out.println(cal(strings1));

//        String suffixExpression = "30 4 + 5 * 6 -";
//        List<String> listString = getListString(suffixExpression);
//        System.out.println(listString);
//        int res = cal(listString);
//
//        System.out.println(res);
    }

    public static List<String> parseSuffixExpressionList(List<String> expression) {
        Stack<String> s1 = new Stack<>();
        //s2在转换操作中没有pop，后面还需要逆序输出，因此使用ArrayList
        List<String> s2 = new ArrayList<>();
        for (String s : expression) {
            if (s.matches("\\d+")) {
                s2.add(s);
            } else if (s.equals("(")) {
                s1.push(s);
            } else if (s.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(s)) {
                    s2.add(s1.pop());
                }
                s1.push(s);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static List<String> toInfixExpression(String expression) {
        int index = 0;
        String str = "";
        char c = ' ';
        List<String> list = new ArrayList<>();
        while (index < expression.length()) {
            if ((c = expression.charAt(index)) < 48 || (c = expression.charAt(index)) > 57) {
                list.add("" + c);
                index++;
            } else {
                str = "";
                while (index < expression.length() && (c = expression.charAt(index)) >= 48 && (c = expression.charAt(index)) <= 57) {
                    str += c;
                    index++;
                }
                list.add(str);
            }
        }
        return list;
    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> res = new ArrayList<>();
        for (String s : split) {
            res.add(s);
        }
        return res;
    }

    public static int cal(List<String> list) {
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

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 1;
    private static int DIV = 1;

    public static int getValue(String operation){
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                break;
        }
        return res;
    }
}
