package com.example.demo.one;

class MyLinkedList<E> {
    private class Node {
        E data;     //结点的数据
        Node next;  //指向下一结点的引用

        public Node() {
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

    private int listSize;   //链表长度
    private Node head;      //头结点

    public MyLinkedList() {
        listSize = 0;
        head = new Node();
    }

    /**
     * 获取某一索引的数据
     *
     * @param index
     * @return element
     * @throws Exception
     */
    public E get(int index) throws Exception {
        //index越界或者不在范围内,抛出异常
        if (index < 0 || index > listSize - 1) {
            throw new Exception("index out of Bounds!");
        }

        //从头开始遍历,一直遍历到指定索引的数据
        Node p = head;
        int i = 0;
        while (p.next != null && i <= index) {
            p = p.next;
            ++i;
        }
        return p.data;
    }

    /**
     * 获取链表长度
     *
     * @return size
     */
    public int size() {
        return listSize;
    }

    /**
     * 向指定索引插入指定的数据
     *
     * @param index
     * @param element
     * @throws Exception
     */
    public void add(int index, E element) throws Exception {
        //index越界或者不在范围内,抛出异常
        if (index < 0 || index > listSize) {
            throw new Exception("index out of Bounds!");
        }

        //新结点
        Node newNode = new Node();
        newNode.data = element;

        //从头开始遍历
        Node p = head;
        int i = 0;
        while (p.next != null && i < index) {
            p = p.next;
            ++i;
        }
        newNode.next = p.next;  //新结点的指针域存入原p结点指向的下一节点地址
        p.next = newNode;       //插入新节点后,p结点的指针域指向了新结点
        listSize++;             //插入新结点后表长+1
    }

    public E remove(int index) throws Exception {
        //index越界或者不在范围内,抛出异常
        if (index < 0 || index > listSize - 1) {
            throw new Exception("index out of Bounds!");
        }

        //从头开始遍历
        Node p = head;
        int i = 0;
        while (p.next != null && i < index) {
            p = p.next;
            ++i;
        }
        Node q = p.next;     //q即准备要删除的结点
        E e = q.data;       //把q结点的数据提取出来
        p.next = q.next;    //把q结点的下一结点地址存入p结点的指针域
        q.next = null;      //删除q结点
        q.data = null;
        listSize--;         //表长-1
        return e;
    }
}

public class LianBiaoDemo {
    public static void main(String[] args) throws Exception {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        list.add(3, "3");
        list.add(4, "4");
        list.add(5, "5");
        System.out.println("=====添加6个元素=====");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        System.out.println("=====获取索引为2的元素=====");
        System.out.println(list.get(2));
        System.out.println("=====移除索引为4的元素打印=====");
        System.out.println("移除的元素为:"+list.remove(4));
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
