package com.example.demo.three;

/**
 * @author 小空
 * @create 2021-08-30 21:23
 * @description 链式队列的简单实现(基于单链表)
 */
class LinkedQueue {
    /**
     * 结点类
     */
    class Node {
        Object data;
        Node next;

        public Node() {
            this(null, null);
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size;   //队列中的元素个数
    private Node head;  //队头指针
    private Node rear;  //队尾指针

    public LinkedQueue() {
        head = new Node();
        rear = new Node();
        size = 0;
    }

    /**
     * 入队操作
     *
     * @param data 需要入队的元素
     */
    public void enqueue(Object data) {
        Node newNode = new Node(data, null);
        if (size == 0) {
            head.next = newNode;
            head = head.next;
        }
        rear.next = newNode;
        rear = rear.next;
        size++;
    }

    /**
     * 出队操作
     *
     * @return 返回出队的元素
     * @throws Exception 如果队列没有元素,还继续执行出队操作,则会出现异常
     */
    public Object dequeue() throws Exception {
        if (size == 0) {
            throw new Exception("LinkedQueue Already Null,Can Not Dequeue");
        }
        Object data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /**
     * 获取队列中的元素个数
     *
     * @return 队列中的元素个数
     */
    public int getSize() {
        return size;
    }
}

public class LinkedQueueDemo {
    public static void main(String[] args) throws Exception {
        LinkedQueue linkedQueue = new LinkedQueue();
        //先入队5个元素
        linkedQueue.enqueue("1");
        linkedQueue.enqueue("2");
        linkedQueue.enqueue("3");
        linkedQueue.enqueue("4");
        linkedQueue.enqueue("5");
        //再出队2个元素
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue.dequeue());
        System.out.println("链式队列的元素个数:" + linkedQueue.getSize() + "个");
    }
}
