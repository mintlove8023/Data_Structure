package com.example.demo;

import java.util.LinkedList;

/**
 * 单向链表操作
 * 代码出处：https://blog.csdn.net/weixin_45432742/article/details/100899251
 */

class SingleLinkedList<E> {
    private class Node {
        E data;        //结点的数据
        Node next;     //指向下一个结点的指针

        public Node() {
            this(null, null);
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private int size;       //元素个数
    private Node head;      //指向头结点的头指针
    private Node rear;      //指向尾结点的尾指针

    public SingleLinkedList() {
        size = 0;
        head = new Node();
        rear = head;
    }

    /**
     * 返回链表中元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 向链表中的指定位置插入指定元素
     *
     * @param index
     * @param e
     * @throws Exception
     */
    public void add(int index, E e) throws Exception {
        //index越界或者不在范围内,抛出异常
        if (index < 0 || index > size) {
            throw new Exception("index out of Bounds!");
        }

        //创建一个新的结点,然后将插入的数据存入新的结点的数据域中
        Node newNode = new Node();
        newNode.data = e;

        //判断新元素插入的位置
        if (index == 0) {                //如果插入的位置在头节点
            newNode.next = head.next;   //将头结点指针域指向的结点地址存入新结点的指针域中
            head.next = newNode;        //头结点的指针域指向了新结点
            if (size == 0) {            //如果是第一次添加元素,尾指针会指向新添加结点的位置,如果再来一个新结点从头插入,尾指针可以不用移动。
                rear = newNode;
            }
        } else if (index == size) {     //如果插入的位置是末尾
            rear.next = newNode;        //那么尾结点的指针域存放新结点的地址
            rear = rear.next;           //尾指针存放的地址是新节点的地址
        } else {
            Node p = head;              //从头进行遍历,遍历到新结点插入位置的前一个结点p
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            newNode.next = p.next;      //新结点的指针域存入 插入位置结点的地址(即p结点的下一个结点)
            p.next = newNode;           //p结点的指针域存入新结点地址
        }
        size++;                         //每次插入一个元素,元素个数+1
    }

    /**
     * 从头插入一个元素
     *
     * @param e 需要插入的元素
     * @throws Exception
     */
    public void addFirst(E e) throws Exception {
        add(0, e);
    }

    /**
     * 从尾插入一个元素
     *
     * @param e 需要插入的元素
     * @throws Exception
     */
    public void addLast(E e) throws Exception {
        add(size, e);
    }

    /**
     * 获取某个结点的元素
     *
     * @param index 索引
     * @return
     * @throws Exception
     */
    public E get(int index) throws Exception {
        //index越界或者不在范围内,抛出异常
        if (index < 0 || index > size - 1) {
            throw new Exception("index out of Bounds!");
        }

        if (index == 0) {
            return head.next.data;           //返回头指针指向的下一个结点的数据
        } else if (index == size - 1) {
            return rear.data;               //直接返回尾指针指向的结点的数据
        } else {
            Node p = head;                  //从头结点开始遍历,找到要取元素的结点
            for (int i = 0; p.next != null && i <= index; i++) {
                p = p.next;
            }
            return p.data;                 //返回该结点的数据
        }
    }

    /**
     * 移除某个位置的元素
     *
     * @param index
     */
    public E remove(int index) throws Exception {
        //index越界或者不在范围内,抛出异常
        if (index < 0 || index > size - 1) {
            throw new Exception("index out of Bounds!");
        }

        if (index == 0) {
            Node p = head.next;         //将链表真实的头结点和数据提取出来
            E e = p.data;
            head.next = p.next;         //虚拟头结点存入真实头结点指向的下一结点的地址
            p.next = null;              //将真实头结点指针域和数据域置为空
            p.data = null;
            if (size == 1) {            //如果删除的元素是最后一个
                rear = head;            //尾指针指向虚拟头结点
            }
            size--;                     //删除后的元素-1
            return e;
        } else if (index == size - 1) {
            Node p = head;               //从头开始遍历
            E e = rear.data;            //将尾指针的数据提取出来
            while (p.next != rear) {     //遍历到尾结点的前一个结点
                p = p.next;
            }
            p.next = null;              //将尾结点的前一结点指针域置为null
            rear = p;                   //然后尾指针指向了原尾节点的前一结点
            size--;                     //删除后的元素-1
            return e;
        } else {
            Node p = head;              //同样从头开始遍历
            for (int i = 0; i < index; i++) {
                p = p.next;             //遍历到需要删除的结点位置的前一个结点
            }
            Node d = p.next;            //将要删除的结点和数据提取出来
            E e = d.data;
            p.next = d.next;            //将删除结点的下一结点地址存入删除结点前一结点的指针域中
            d.next = null;              //将需要删除的结点置为null
            d.data = null;
            size--;                     //删除后的元素-1
            return e;
        }
    }
}

public class SingleListDemo {
    public static void main(String[] args) throws Exception {
        SingleLinkedList<String> linkedList = new SingleLinkedList<>();
        linkedList.add(0, "0");
        linkedList.add(1, "1");
        linkedList.add(2, "2");
        linkedList.add(3, "3");
        linkedList.add(4, "4");
        linkedList.add(5, "5");

        linkedList.addFirst("head");
        linkedList.addLast("rear");

        linkedList.remove(4);
        linkedList.add(4, "3");
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.print(linkedList.get(i) + " ");
        }
    }
}
