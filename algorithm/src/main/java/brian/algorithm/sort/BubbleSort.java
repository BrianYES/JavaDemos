package brian.algorithm.sort;

import brian.algorithm.utils.AlgorithmUtils;

/**
 * 冒泡排序
 *
 * @author Brian
 * @date 2018/6/28
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        // 外围循环n-1次，每次确定一个元素的位置，位于尾部
        for (int i = 0; i < n-1; i++) {
            // 内部循环，相邻元素进行比较，比较次数逐步减1
            for (int j = 0; j < n-1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    AlgorithmUtils.swap(arr, j, j+1);
                }
            }
        }
    }

    public static void sort2(int[] arr) {
        int n = arr.length;
        // 外围循环n-1次，每次确定一个元素的位置，位于尾部
        for (int i = 0; i < n-1; i++) {
            //标记位，如果这一趟发生了交换，则为true，否则为false。明显如果有一趟没有发生交换，说明排序已经完成。
            boolean flag = false;

            // 内部循环，相邻元素进行比较，比较次数逐步减1
            for (int j = 0; j < n-1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    AlgorithmUtils.swap(arr, j, j+1);

                    // 发生交换，置flag为true
                    flag = true;
                }
            }

            // 没有发生交换，则表明已是有序，跳出循环
            if (flag == false) {
                break;
            }
        }
    }
}
