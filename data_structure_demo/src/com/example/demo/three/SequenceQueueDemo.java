package com.example.demo.three;

/**
 * @author 小空
 * @create 2021-08-29 20:06
 * @description 顺序队列实现(基于数组)
 * 只是简单演示了一下入队与出队的操作
 */
class SequenceQueue {
    private Object[] elements;  //存储数据的数组
    private int queueCapacity;  //这个队列的容量

    private int head;           //表示队头指针
    private int rear;           //表示队尾指针

    /**
     * 初始化一个容量默认为10的队列
     */
    public SequenceQueue() {
        this(10);
    }

    /**
     * 初始化一个指定容量的队列
     *
     * @param capacity 传入一个int类型的变量,来指定此队列的容量。
     */
    public SequenceQueue(int capacity) {
        elements = new Object[capacity];
        queueCapacity = capacity;
        head = 0;
        rear = 0;
    }

    /**
     * 时间复杂度O(1)
     * 往队列中添加一个元素(入队)
     *
     * @param element 传入Object类型的变量,代表需要添加的元素。
     */
    public void enqueue(Object element) throws Exception {
        if (rear == queueCapacity) {
            throw new Exception("Queue is Full,Can Not Enqueue");
        }
        elements[rear] = element;
        rear++;
    }

    /**
     * 最好时间复杂度O(1)
     * 最坏时间复杂度O(n),即只有在队尾指针移到了队列最右边时，才要触发数据迁移
     * 往队列中添加一个元素(入队)
     *
     * @param element 传入Object类型的变量,代表需要添加的元素。
     */
    public void enqueue1(Object element) throws Exception {
        if (rear == queueCapacity) {
            if (head == 0) throw new Exception("Queue is Full,Can Not Enqueue");
            //队尾指针已经到了队列最右边，但是队列中还有空闲空间，那么需要进行数据的整体迁移
            for (int i = head; i < rear; i++) {
                elements[i - head] = elements[i];
            }
            //重新定位指针的指向
            rear -= head;
            head = 0;
        }
        elements[rear] = element;
        rear++;
    }

    /**
     * 时间复杂度O(1)
     * 往队列中移除一个元素(出队)
     *
     * @return 返回出队的元素
     */
    public Object dequeue() throws Exception {
        if (head == rear) {
            throw new Exception("Queue Already Null,Can Not Dequeue");
        }
        return elements[head++];
    }

    /**
     * 获取队列中的元素个数
     *
     * @return 元素个数
     */
    public int size() {
        if (head == rear) {
            return 0;
        }
        return rear - head;
    }
}

public class SequenceQueueDemo {
    public static void main(String[] args) throws Exception {
        SequenceQueue queue = new SequenceQueue(5);
        queue.enqueue1("0");
        queue.enqueue1("1");
        queue.enqueue1("2");
        queue.enqueue1("3");
        queue.enqueue1("4");
        System.out.println("元素个数为:" + queue.size());
    }
}
