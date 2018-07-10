package brian.algorithm.sort;

import brian.algorithm.utils.AlgorithmUtils;

/**
 * 选择排序法
 *
 * @author Brian
 * @date 2018/6/28
 */
public class SelectionSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 选择[i,n)里的最小值的索引
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (i != minIndex) {
                AlgorithmUtils.swap(arr, i, minIndex);
            }
        }
    }

}
