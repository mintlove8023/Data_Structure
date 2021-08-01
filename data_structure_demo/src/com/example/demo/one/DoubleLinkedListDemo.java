package com.example.demo.one;

/**
 * 双向链表
 */
class DoubleLinedList<E> {
    private class Node {
        Node prev;  //前驱指针
        E data;     //数据域
        Node next;  //后继指针

        public Node() {
            this(null, null, null);
        }

        public Node(Node prev, E data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private int linkedListSize;    //链表长度
    private Node head;              //头结点

    public DoubleLinedList() {
        linkedListSize = 0;
        head = new Node();
    }

    public void add(int index, E element) throws Exception {
        //index越界或者不在范围内,抛出异常
        if (index < 0 || index > linkedListSize) {
            throw new Exception("index out of Bounds!");
        }

        Node newNode = new Node(null, element, null);     //需要插入的新结点
        Node p = head;
        int i = 0;
        while (p != null && i < index) {
            p = p.next;
            ++i;
        }
        Node q = p.next;        //转存当前结点的后继结点
        p.next = newNode;       //将新结点存入新结点前一结点的指针域中
        newNode.prev = p;       //新结点的前驱指针指向了前一结点
        newNode.next = q;       //新结点的后继指向了转存的结点
        linkedListSize++;      //表长+1
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
        if (index < 0 || index > linkedListSize - 1) {
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

    public E remove(int index) throws Exception {
        //index越界或者不在范围内,抛出异常
        if (index < 0 || index > linkedListSize - 1) {
            throw new Exception("index out of Bounds!");
        }
        //从头开始遍历
        Node p = head;
        int i = 0;
        while (p.next != null && i < index) {
            p = p.next;
            ++i;
        }

        Node delNode = p.next;              //需要删除的结点
        E e = delNode.data;                 //将需要删除结点的数据提取出来

        //把需要删除结点的下一结点地址存入需要删除结点上一节点中的指针域里面
        delNode.prev.next = delNode.next;

        //把删除结点的前一结点地址存入删除结点的后继结点的前驱指针中
        delNode.prev = p;

        //释放被删除的结点
        delNode.data = null;
        delNode.prev = null;
        delNode.next = null;
        linkedListSize--;   //表长-1
        return e;
    }

    /**
     * 获取链表长度
     *
     * @return size
     */
    public int size() {
        return linkedListSize;
    }
}


public class DoubleLinkedListDemo {
    public static void main(String[] args) throws Exception {
        DoubleLinedList<String> list = new DoubleLinedList<>();
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        list.add(3, "3");
        list.add(4, "4");
        System.out.println("========插入5个元素========");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }

        System.out.println();
        System.out.println("========移除索引为4的元素========");
        list.remove(4);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }


        System.out.println();
        System.out.println("========在索引为0的位置插入新元素========");
        list.add(0, "newElement");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}


