package brian.algorithm.utils;

public class AlgorithmUtils {

    /**
     * 交互数组中的两个元素
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
