package brian.algorithm.sort;

import brian.algorithm.utils.AlgorithmUtils;

/**
 * 希尔排序
 *
 * @author Brian
 * @date 2018/6/28
 */
public class ShellSort {

    public static void sort(int[] arr) {
        int n = arr.length;

        int h = n / 2;
        while (h >= 1) {
            // 对arr[i] arr[i+h] arr[i+2h]...进行插入排序
            for (int i = h; i < n; i++) {
                int temp = arr[i];
                int j = i;
                for ( ;j >= h && arr[j-h] > temp; j -= h) {
                    arr[j] = arr[j-h];
                }
                arr[j] = temp;
            }

            h /= 2;
        }
    }
}
