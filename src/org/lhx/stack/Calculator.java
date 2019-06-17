package org.lhx.stack;

/**
 * @author lhx
 * @date 2019/6/17 - 15:21
 */
public class Calculator {

    public static void main(String[] args) {
        String expression = "30-2*6+2";
        ArrayStackColculator numberStack = new ArrayStackColculator(10);
        ArrayStackColculator operStack = new ArrayStackColculator(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        //每次扫描出的存入ch
        char ch = ' ';
        //用于拼接
        String keepNum = "";
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                if (!operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numberStack.pop();
                        num2 = numberStack.pop();
                        oper = operStack.pop();
                        res = numberStack.cal(num1, num2, oper);
                        numberStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                //numberStack.push(ch - 48);
                //处理多位数
                //向后再看一位，是数继续扫描，是符号直接入栈
                //需要定义一个变量用于拼接
                //处理多位数
                keepNum += ch;
                //如果是最后一位直接入栈
                if (index == expression.length() - 1) {
                    numberStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一位
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈
                        numberStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            oper = operStack.pop();
            res = numberStack.cal(num1, num2, oper);
            numberStack.push(res);
        }
        System.out.printf("表达式%s = %d", expression, numberStack.pop());
    }
}

class ArrayStackColculator extends ArrayStack {

    public ArrayStackColculator(int maxSize) {
        super(maxSize);
    }

    public int peek() {
        return super.getStack()[getTop()];
    }

    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}
