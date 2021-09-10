package com.example.demo.three;

/**
 * 链栈实现
 */
class LinkStack {
    /**
     * 结点类
     */
    private class Node {
        Object data;        //存储元素的数据域
        Node next;          //存储下一节点的指针域

        public Node() {
            this(null, null);
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node topNode;       //栈顶指针
    private int elementSize;    //栈中元素个数

    public LinkStack() {
        elementSize = 0;
    }

    /**
     * 入栈操作
     *
     * @param element 需要入栈的元素
     */
    public void push(Object element) {
        if (elementSize == 0) {
            topNode = new Node(element, null);
        } else {
            Node newNode = new Node();
            newNode.data = element;
            newNode.next = topNode;
            topNode = newNode;
        }
        elementSize++;
    }

    /**
     * 出栈操作
     *
     * @return 出栈的元素
     */
    public Object pop() throws Exception {
        if (elementSize == 0) {
            throw new Exception("Stack is Null");
        }

        Node tempNode;                      //这个结点表示它是需要出栈的结点
        Object resultData = topNode.data;   //获取到当前栈顶结点的数据元素
        tempNode = topNode;                 //将栈顶指针指向的结点转存入等待被出栈的结点
        topNode = tempNode.next;            //将栈顶指针指向下一结点
        tempNode = null;                    //原来的栈顶结点出栈
        elementSize--;                      //栈的元素个数-1
        return resultData;                  //返回出栈的结点数据
    }
}

public class LinkStackDemo {
    public static void main(String[] args) throws Exception {
        LinkStack stack = new LinkStack();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
