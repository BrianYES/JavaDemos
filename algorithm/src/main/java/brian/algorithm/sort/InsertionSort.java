package brian.algorithm.sort;

import brian.algorithm.utils.AlgorithmUtils;

/**
 * 插入排序
 *
 * @author Brian
 * @date 2018/6/28
 */
public class InsertionSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    AlgorithmUtils.swap(arr, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    public static void sort2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j;
            for (j = i; j > 0; j--) {
                if (arr[j] > temp) {
                    arr[j] = arr[j-1];
                } else {
                    break;
                }
            }
            arr[j] = temp;
        }
    }
}
