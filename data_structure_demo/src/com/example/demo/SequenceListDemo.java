package com.example.demo;

/**
 * 顺序存储结构的插入与删除
 *
 * @author sora
 */
class SequenceList<T> {
    private final int DEFAULT_LENGTH = 10;  //线性表默认长度
    private Object[] dataList;               //存储数据的数组
    private int currentLen;                 //线性表当前长度

    /**
     * 创建一个默认长度的线性表
     */
    public SequenceList() {
        dataList = new Object[DEFAULT_LENGTH];
        currentLen = 0;
    }

    /**
     * 创建一个指定长度的线性表
     *
     * @param length 你指定的长度
     */
    public SequenceList(int length) {
        dataList = new Object[length];
        currentLen = 0;
    }

    /**
     * 向表中index的位置插入element
     *
     * @param index   需要插入的位置
     * @param element 需要插入的元素
     * @throws Exception
     */
    public void insert(int index, Object element) throws Exception {
        //线性表已满,抛出异常!
        if (currentLen == dataList.length) {
            throw new Exception("Sequence List is full!");
        }
        //index越界或者不在范围内,抛出异常
        if (index > currentLen || index < 0) {
            throw new Exception("index out of Bounds!");
        }
        //i即表的最后一个元素的位置,将插入位置后面的所有元素后移一位
        // (如果一直在表尾插入数据,此for循环将永远走不进去)
        for (int i = currentLen; i > index; i--) {
            dataList[i] = dataList[i - 1];
        }
        dataList[index] = element;  //插入新元素
        currentLen++;               //表长+1
    }

    /**
     * 删除表中指定位置的元素
     *
     * @param index
     */
    public void delete(int index) throws Exception {
        //线性表为空,抛出异常!
        if (currentLen == 0) {
            throw new Exception("Sequence List has no element");
        }

        if (index >= currentLen || index < 0) {
            throw new Exception("index out of Bounds!");
        }
        //将删除位置后继元素前移
        for (int i = index; i < currentLen - 1; i++) {
            dataList[i] = dataList[i + 1];
        }
        currentLen--;       //表长-1
    }

    /**
     * 打印线性表中所有元素
     */
    public void listAllElement() {
        for (int i = 0; i < currentLen; i++) {
            System.out.print(dataList[i] + " ");
        }
    }
}

/**
 * main()方法中进行测试
 */
public class SequenceListDemo {
    public static void main(String[] args) throws Exception {
        SequenceList<String> sequenceList = new SequenceList<>();
        //插入数据
        System.out.print("===向表中插入数据===" + "\n");
        sequenceList.insert(0, "data0");
        sequenceList.insert(1, "data1");
        sequenceList.insert(2, "data2");
        sequenceList.insert(3, "data3");
        sequenceList.insert(4, "data4");
        sequenceList.insert(5, "data5");
        sequenceList.listAllElement();
        System.out.println();
        //删除数据
        System.out.print("===删除索引为3的数据===" + "\n");
        sequenceList.delete(3);
        sequenceList.listAllElement();
        System.out.println();
        //在索引为3的位置插入数据
        System.out.print("===在索引为3的位置插入数据===" + "\n");
        sequenceList.insert(3,"data3");
        sequenceList.listAllElement();
    }
}
