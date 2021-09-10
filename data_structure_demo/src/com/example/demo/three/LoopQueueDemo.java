package com.example.demo.three;

/**
 * @author 小空
 * @create 2021-08-31 20:23
 * @description 循环队列
 */
class LoopQueue {
    private Object[] data;      //存储元素的数组
    private int queueCapacity;  //队列容量

    private int head;   //队头指针
    private int rear;   //队尾指针

    /**
     * 构造一个容量默认为10的循环队列
     */
    public LoopQueue() {
        this(10);
    }

    /**
     * 构造一个指定容量的循环队列
     *
     * @param capacity int 容量
     */
    public LoopQueue(int capacity) {
        data = new Object[capacity];
        queueCapacity = capacity;
        head = 0;
        rear = 0;
    }

    /**
     * 循环队列入队操作
     *
     * @param data 数据
     */
    public void enqueue(Object data) throws Exception {
        if ((rear + 1) % queueCapacity == head) {
            throw new Exception("Queue is Full,Can Not Enqueue");
        }
        this.data[rear] = data;
        rear = (rear + 1) % queueCapacity;          //rear指针后移一位,如果指针移到了最后面,则转到开头索引为0的位置
    }

    /**
     * 循环队列出队操作
     *
     * @throws Exception
     */
    public Object dequeue() throws Exception {
        if (head == rear) {
            throw new Exception("Queue Already Null,Can Not Dequeue");
        }
        Object element = this.data[head];
        head = (head + 1) % queueCapacity;          //head指针同样往后移动
        return element;
    }

}

public class LoopQueueDemo {
    public static void main(String[] args) throws Exception {
        LoopQueue loopQueue = new LoopQueue(5);
        loopQueue.enqueue("0");
        loopQueue.enqueue("1");
        loopQueue.enqueue("2");
        loopQueue.enqueue("3");

        System.out.println(loopQueue.dequeue());
        System.out.println(loopQueue.dequeue());
        System.out.println(loopQueue.dequeue());
        System.out.println(loopQueue.dequeue());
    }
}
