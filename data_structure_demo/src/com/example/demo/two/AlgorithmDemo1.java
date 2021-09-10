package com.example.demo.two;

/**
 * 累加求和的两种算法实现
 * 1+2+3+4+....+99+100
 *
 * 最基本的二分查找法
 */
public class AlgorithmDemo1 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = binarySearch(arr, 7);
        System.out.println(index);
    }
    //折半查找
    public static int binarySearch(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;
        /*======================================================*
         * 假设数组长度为n，第一次循环在n的范围内继续查找               *
         * 第二次循环在n/2的范围内查找，第三次循环在n/4的范围查找       *
         * 所以每次查找后，查找的区间都会缩小为原来的一半              *
         * 最坏的情况是：第x次循环查找时即n/(2^x)=1时,              *
         * 此时的时间复杂度为O(x)，根据n/(2^x)=1得到，              *
         * x = log2n，所以时间复杂度为O(logn)                     *
         *=====================================================*/
        while (left <= right) {
            int mid = left+(right - left) / 2;
            if (arr[mid] == num) {
                return mid;
            } else if (arr[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    //第一个求和算法执行了1 + (n+1) + n + 1次 = 2n + 3次
    public static void getSumMethod1(int n) {
        int sum = 0;                        //执行1次
        for (int i = 1; i <= n; i++) {      //循环执行了n+1次
            sum = sum + i;                  //循环执行了n次
        }
        System.out.println(sum);            //执行了1次
    }

    //第二个求和算法执行了3次
    public static void getSumMethod2(int n) {
        int sum = 0;                        //执行1次
        sum = (1 + n) * n / 2;              //执行1次
        System.out.println(sum);            //执行1次
    }

    public static void getSumMethod3(int n) {
        int sum = 0, x = 0;                    //执行1次
        for (int i = 0; i < n; i++) {         //循环执行n次
            for (int j = 0; j < n; j++) {     //从这个循环开始都执行了 n * n 次
                x++;
                sum += x;
            }
        }
        System.out.println(sum);                //执行1次
    }

    //对数阶 O(logn)
    public static void logarithmic(int n) {
        int count = 1;
        while (count < n) {
            count = count * 2;
        }
    }

    //线性对数阶段O(nlogn)
    public static void nLogarithmic(int n) {
        int count = 1;
        for (int i = 0; i < n; i++) {
            while (count < n) {
                count = count * 2;
            }
        }
    }

    //平方阶O(n2)
    public static void squareMethod(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Hello");
            }
        }
    }

    /**
     * 从指定数组中寻找指定的数字
     *
     * @param num 需要寻找的数字
     * @param arr 一个数组
     * @return true(找到) false(未找到)
     */
    public static boolean findTargetNumber(int num, int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }
}
