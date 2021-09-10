package com.example.demo.three;

/**
 * 如果兔子在出生两个月后，就有繁殖能力，一对兔子每个月能生出一对兔子
 * 假设兔子不死，那么一年后可以繁殖多少兔子?
 * <p>
 * 分析：
 * 第1个月 1对      第7个月 13对
 * 第2个月 1对      第8个月 21对
 * 第3个月 2对      第9个月 34对
 * 第4个月 3对      第10个月 55对
 * 第5个月 5对      第11个月 89对
 * 第6个月 8对      第12个月 144对
 * <p>
 * 可以看到有一个十分明显的特征就是,每相邻的两项之和都构成了后一项的结果
 * 即：
 * 第1个月 + 第2个月 = 第3个月生的兔子
 * 第2个月 + 第3个月 = 第4个月生的兔子
 * 第3个月 + 第4个月 = 第5个月生的兔子
 * ...
 */
public class FibonacciSequence {
    public static void main(String[] args) {
/*        getRabbitNum1(12);
        for (int i = 0; i < 10; i++) {
            System.out.println("第" + (i + 1) + "个月的兔子数量为:" + getRabbitNum2(i));
        }*/
        System.out.println(fibonacciSequenceMethod(10));
    }

    public static int fibonacciSequenceMethod(int num) {
        if (num < 2) {
            return num == 0 ? 0 : 1;
        }
        return fibonacciSequenceMethod(num - 1) + fibonacciSequenceMethod(num - 2);
    }

    /**
     * 循环实现
     */
    public static void getRabbitNum1(int month) {
        long[] a = new long[month];
        a[0] = 1;   //第1个月兔子的对数
        a[1] = 1;   //第2个月兔子的对数

        for (int i = 2; i < month; i++) {
            a[i] = a[i - 1] + a[i - 2];
            System.out.println(a[i] + "=" + a[i - 1] + "+" + a[i - 2]);
        }
    }

    /**
     * 递归实现
     */
    public static int getRabbitNum2(int month) {
        if (month < 2) {
            return month == 0 ? 1 : 1;
        }

        return getRabbitNum2(month - 1) + getRabbitNum2(month - 2);
    }
}
