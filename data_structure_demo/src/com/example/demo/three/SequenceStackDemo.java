package com.example.demo.three;

/**
 * 顺序栈
 */
class SequenceStack {
    private Object[] stack;         //用于存放数据的数组
    private int stackCapacity;      //栈的容量
    private int top;                //栈顶指针(永远指向栈顶元素)

    /**
     * 默认构造一个容量为10的栈
     */
    public SequenceStack() {
        this(10);
    }

    /**
     * 构造一个指定容量的栈
     *
     * @param capacity 容量
     */
    public SequenceStack(int capacity) {
        stack = new Object[capacity];
        stackCapacity = capacity;
        top = -1;
    }

    /**
     * 判断是否为一个空栈
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 数据进栈
     *
     * @param element 数据元素
     * @throws Exception 如果栈满了,数据还进栈则会抛出异常!
     */
    public void push(Object element) throws Exception {
        if (top == stackCapacity - 1) {
            throw new Exception("Stack is Full!");
        }
        top++;                  //栈顶指针指向新的元素
        stack[top] = element;   //向栈中添加新元素
    }

    /**
     * 数据出栈
     *
     * @return 出栈的数据元素
     */
    public Object pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is Empty!");
        }
        Object element = stack[top];
        top--;
        return element;
    }

    /**
     * 获取栈中元素个数
     */
    public int size() {
        return top + 1;
    }

    /**
     * 获取栈中的指定位置的元素
     */
    public Object get(int index) throws Exception {
        if (index > top) {
            throw new Exception("Index out of bounds!");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Illegal Parameter!");
        }

        return stack[index];
    }
}

public class SequenceStackDemo {
    public static void main(String[] args) throws Exception {
        SequenceStack myStack = new SequenceStack();
        //依次将数据压入栈中
        myStack.push("1");
        myStack.push("2");
        myStack.push("3");
        myStack.push("4");
        myStack.push("5");

        //遍历栈中元素
        for (int i = 0; i < myStack.size(); i++) {
            System.out.print(myStack.get(i) + " ");
        }

        //仅仅用作换行
        System.out.println();

        //数据依次出栈
        System.out.print(myStack.pop());
        System.out.print(myStack.pop());
        System.out.print(myStack.pop());
        System.out.print(myStack.pop());
        System.out.print(myStack.pop());
    }
}
