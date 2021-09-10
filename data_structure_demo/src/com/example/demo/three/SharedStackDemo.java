package com.example.demo.three;

/**
 * 两栈共享空间
 * 时间复杂度均为O(1)
 */
class SharedStack {
    private Object[] stack;         //用于存放数据的数组
    private int stackCapacity;      //栈的容量
    private int top1;               //栈1的栈顶指针
    private int top2;               //栈2的栈顶指针

    public SharedStack() {
        this(10);
    }

    public SharedStack(int capacity) {
        stack = new Object[capacity];
        stackCapacity = capacity;
        top1 = -1;
        top2 = stackCapacity;
    }

    /**
     * 将元素插入指定的栈中
     *
     * @param stackNumber 栈号
     * @param element     需要插入的元素
     */
    public void push(int stackNumber, Object element) throws Exception {
        if (top1 + 1 == top2) {
            throw new Exception("Stack is Full");
        }

        if (stackNumber < 1 || stackNumber > 2) {
            throw new IllegalArgumentException("Stack Number Not Found!");
        }

        if (stackNumber == 1) {
            stack[++top1] = element;
        } else {
            stack[--top2] = element;
        }
    }

    /**
     * 指定的栈进行弹栈操作
     *
     * @param stackNumber 栈号
     * @return 指定栈弹出的元素
     */
    public Object pop(int stackNumber) throws Exception {
        if (stackNumber == 1) {
            if (top1 == -1) {
                throw new Exception("Stack1 is Null!");
            }
            Object element = stack[top1];
            top1--;
            return element;
        } else if (stackNumber == 2) {
            if (top2 == stackCapacity) {
                throw new Exception("Stack2 is Null!");
            }
            Object element = stack[top2];
            top2++;
            return element;
        } else {
            throw new IllegalArgumentException("Stack Number Not Found!");
        }
    }

    /**
     * 返回指定栈的元素个数
     *
     * @param stackNumber 栈号
     * @return 指定栈的元素个数
     */
    public int size(int stackNumber) {
        if (stackNumber == 1 && top1 != -1) {
            return top1 + 1;
        } else if (stackNumber == 2 && top2 != stackCapacity) {
            return stackCapacity - top2;
        } else {
            return 0;
        }
    }

    /**
     * 返回指定栈的某个索引的元素
     */
    public Object get(int stackNumber, int index) throws Exception {
        if (stackNumber == 1 && index != -1 && index <= top1) {
            return stack[index];
        } else if (stackNumber == 2 && index != -1 && index < (stackCapacity - top2)) {
            return stack[(stackCapacity - 1) - index];
        } else {
            throw new Exception("Stack Error!");
        }
    }
}

public class SharedStackDemo {
    public static void main(String[] args) throws Exception {
/*=================================================================================================


                索引:  0   1   2   3   4    5      6      7     8     9
                    |----------------------------------------------------|
                    | 0 | 1 | 2 | 3 | 4 | "肆" | "叁" | "贰" | "壹" | "零" |
                    |----------------------------------------------------|
           栈1栈底--->                 ^     ^                             <---栈2栈底
                                    top1   top2


===================================================================================================*/
        SharedStack stack = new SharedStack();
        stack.push(1, "0");
        stack.push(1, "1");
        stack.push(1, "2");
        stack.push(1, "3");
        stack.push(1, "4");
        stack.push(1, "5");
        stack.push(1, "6");


        stack.push(2, "零");
        stack.push(2, "壹");
        stack.push(2, "贰");

        System.out.print("栈1遍历:");
        for (int i = 0; i < stack.size(1); i++) {
            System.out.print(stack.get(1, i) + " ");
        }
        System.out.println();   //仅换行

        System.out.print("栈1出栈:");
        System.out.print(stack.pop(1) + " ");
        System.out.print(stack.pop(1) + " ");
        System.out.print(stack.pop(1) + " ");
        System.out.print(stack.pop(1) + " ");
        System.out.print(stack.pop(1) + " ");
        System.out.print(stack.pop(1) + " ");
        System.out.println(stack.pop(1));

        //+++++++++++++++++++++++++++++++++++++++++++++++

        System.out.print("栈2遍历:");
        for (int i = 0; i < stack.size(2); i++) {
            System.out.print(stack.get(2, i) + " ");
        }
        System.out.println();   //仅换行

        System.out.print("栈2出栈:");
        System.out.print(stack.pop(2) + " ");
        System.out.print(stack.pop(2) + " ");
        System.out.println(stack.pop(2));
    }
}
