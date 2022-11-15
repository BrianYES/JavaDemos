package brian.algorithm.sort;

/**
 * 归并排序
 *
 * @author Brian
 * @date 2018/6/28
 */
public class MergeSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        mergeSort(arr, 0, n-1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        // 递归使用归并排序，对[l, r]范围进行排序

        if (l >= r) {
            return;
        }
        // 在数组足够小的时候可以用插入排序法

        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid+1, r);

        if (arr[mid] > arr[mid+1]) {
            merge(arr, l, mid, r);
        }
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        // 将[l, mid] 和 [mid+1, r]进行归并
        System.out.println(l + " " + mid + " " + r);

        int[] aux = new int[r-l+1];
        for (int i = l; i <= r; i++) {
            aux[i-l] = arr[i];
        }

        int i = l, j = mid+1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j-l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i-l];
                i++;
            } else if (aux[i-l] < aux[j-l]) {
                arr[k] = aux[i-l];
                i++;
            } else {
                arr[k] = aux[j-l];
                j++;
            }
        }

    }

    public static void sortBU(int[] arr) {
        int n = arr.length;

        for (int sz = 2; sz <= n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz+sz) {
                // 归并[i, i+sz-1] [i+sz, i+2*sz-1]
                merge(arr, i, i + sz - 1, Math.min(i+sz+sz-1, n-1));
            }
        }
    }
}
