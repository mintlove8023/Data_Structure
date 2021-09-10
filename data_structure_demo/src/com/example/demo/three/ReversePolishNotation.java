package com.example.demo.three;

import java.util.*;

/**
 * 逆波兰表达式(RPN)
 * (未完成)
 */
class RPNStack {
    private Object[] element;    //存储元素的数组
    private int top;             //栈顶指针
    private int stackCapacity;   //栈的容量

    /**
     * 无参构造默认初始化一个容量为10的栈
     */
    public RPNStack() {
        this(10);
    }

    /**
     * 初始化一个栈并指定该栈的容量
     *
     * @param capacity 容量
     */
    public RPNStack(int capacity) {
        element = new Object[capacity];
        top = 0;
        stackCapacity = capacity;
    }

    /**
     * 将指定元素压入栈中
     *
     * @param element 元素值
     */
    public void push(Object element) {
        if (isFull()) {
            autoAddedCapacity(top + 1);
        }
        this.element[top] = element;
        top++;
    }

    /**
     * 元素依次出栈
     *
     * @return 出栈的元素值
     */
    public Object pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        top--;
        return element[top];
    }

    /**
     * 获取指定索引的元素值
     *
     * @param index 索引值
     * @return 指定索引的元素值
     */
    public Object get(int index) {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        if (index < 0 || index >= top) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return element[index];
    }

    public int size() {
        return top;
    }

    public Object getTop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return element[top - 1];
    }

    /**
     * 判断栈是否为空
     *
     * @return true则为空栈, 否则栈中就有元素
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * 判断是否满栈
     *
     * @return true则为栈满, 否则栈还有容量
     */
    public boolean isFull() {
        return top == stackCapacity;
    }

    /**
     * 自动扩容
     */
    private void autoAddedCapacity(int capacity) {
        int beforeCapacity = stackCapacity;   //扩容之前的容量
        int afterCapacity = beforeCapacity << 1;    //扩容为原容量的2倍
        if ((capacity - element.length) > 0) {
            element = Arrays.copyOf(element, afterCapacity);
            stackCapacity = afterCapacity;
        }
    }
}


public class ReversePolishNotation {
    public static void main(String[] args) throws Exception {
        RPNStack stack = new RPNStack();
//        String testData = "12*(4-3)";               //1243-*
//        String testData = "1*(2+3)+4";              //123+*4+
//        String testData = "(2+8)*3-30+(4*5)";       //28+3*30-45*+
//        String testData = "1+3";                    //13+
//        String testData = "1+(2-3)*4";              //123-4*+
//        String testData = "6+5*(4-3)+2/1";          //6543-*+21/+
        String testData = "(1+2*3-4)";            //
        String s = expr2RPN(stack, testData);
        System.out.println(s);
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param rpnStack 栈来进行表达式操作
     * @param expr     中缀表达式
     */
    public static String expr2RPN(RPNStack rpnStack, String expr) {
        char[] chars = expr.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                continue;
            }

            if (isNumber(chars[i])) {
                sb.append(chars[i]);
            } else {
                symbolJudge(rpnStack, chars[i], sb);
            }
        }
        //如果栈中还符号,那么全部出栈输出
        while (!rpnStack.isEmpty()) {
            sb.append(rpnStack.pop());
        }
        return sb.toString();
    }

    /**
     * 判断是否为数字
     *
     * @param num 表达式中的数字
     * @return 如果是数字则为true, 否则为false
     */
    public static boolean isNumber(char num) {
        return !((num == '+') | (num == '-') | (num == '*') | (num == '/') | (num == '(') | (num == ')'));
    }

    /**
     * 判断是否是左括号
     */
    public static boolean isLeftBrackets(char left) {
        return left == '(';
    }

    /**
     * 判断是否为右括号
     */
    public static boolean isRightBrackets(char right) {
        return right == ')';
    }

    /**
     * 判断是否为"+"或者"-"
     */
    public static boolean isAddAndsubtrac(char c) {
        return c == '+' || c == '-';
    }

    /**
     * 判断是否为"*"或者"/"
     */
    public static boolean isMultiplyAndDiv(char c) {
        return c == '*' || c == '/';
    }

    /**
     * 符号规则
     */
    public static void symbolJudge(RPNStack rpnStack, char symbol, StringBuilder sb) {
        if (rpnStack.isEmpty()) {
            rpnStack.push(symbol);
        } else {
            //如果当前的栈已经存在符号了,那么根据符号来进行优先级比较来确定是否要出入栈
            bracketsFunction(rpnStack, symbol, sb);
        }
    }

    /**
     * 返回栈顶符号的优先级
     */
    public static int priorityLevel(RPNStack rpnStack) {
        int priorityLevel;   //代表栈顶符号的优先级
        if ((rpnStack.getTop().equals('*')) || (rpnStack.getTop().equals('/'))) {
            priorityLevel = 1;
        } else if ((rpnStack.getTop().equals('+')) || (rpnStack.getTop().equals('-'))) {
            priorityLevel = 0;
        } else {
            priorityLevel = 2;
        }
        return priorityLevel;
    }

    /**
     * 符号出入栈与输出处理
     */
    public static void bracketsFunction(RPNStack rpnStack, char enter, StringBuilder sb) {
        int priorityLevel = priorityLevel(rpnStack);
        switch (priorityLevel) {
            case 0: //栈顶符号优先级为0,则栈顶的符号是"+" 或者 "-"
                if (isRightBrackets(enter)) {
                    bracketsHandel(rpnStack, sb);
                    break;
                }
                if (isAddAndsubtrac(enter)) {
                    sb.append(rpnStack.pop());
                    rpnStack.push(enter);
                } else {
                    rpnStack.push(enter);
                }
                break;
            case 1: //栈顶符号优先级为1,则栈顶的符号是"*" 或者 "/"
                if (isRightBrackets(enter)) {
                    bracketsHandel(rpnStack, sb);
                    break;
                }
                if (isAddAndsubtrac(enter)) {
                    int tempSize = rpnStack.size();
                    for (int i = 0; i < tempSize; i++) {
                        while (rpnStack.getTop().equals('(')) {
                            break;
                        }
                        sb.append(rpnStack.pop());
                    }
                    rpnStack.push(enter);
                } else if (isMultiplyAndDiv(enter)) {
                    sb.append(rpnStack.pop());
                    rpnStack.push(enter);
                } else {
                    rpnStack.push(enter);
                }
                break;
            case 2: //栈顶符号优先级为2,则栈顶的符号是"("
                if (!isNumber(enter)) {
                    rpnStack.push(enter);
                } else if (isRightBrackets(enter)) {
                    bracketsHandel(rpnStack, sb);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 进到这个方法则表示是遇到右括号了,那么就会一直出栈,直到左括号出栈完成<p>
     * 中间的所有符号(除括号外)都会输出
     */
    private static void bracketsHandel(RPNStack rpnStack, StringBuilder sb) {
        while (!rpnStack.isEmpty()) {
            if (rpnStack.getTop().equals('(')) {
                rpnStack.pop();
                break;      //从右括号开始一直出栈直到遇到左括号出栈完成
            }
            sb.append(rpnStack.pop());
        }
    }
}