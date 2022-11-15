package brian.algorithm.test;

import java.lang.reflect.Method;

public class TestHelper {

    /**
     * 生成随机数组，范围在[rangeL, rangeR]
     */
    public static int[] generateRandomIntArray(int num, int rangeL, int rangeR) {
        assert rangeL < rangeR;

        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            // Math.Random()函数返回值范围:[0.0, 1.0)
            int n = (int)(Math.random() * (rangeR - rangeL + 1)) + rangeL;
            arr[i] = n;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public static boolean isSorted(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
    }

    public static void testSort(String sortClassName, String sortMethodName, int[] arr) {
        try {
            Class clazz = Class.forName(sortClassName);
            Method method = clazz.getDeclaredMethod(sortMethodName, arr.getClass());

            long startTime = System.currentTimeMillis();
            method.invoke(null, arr);
            long endTime = System.currentTimeMillis();

            isSorted(arr);

            System.out.println(clazz.getSimpleName() + ":" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] copyArray(int[] arr) {
        int n = arr.length;

        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr2[i] = arr[i];
        }
        return arr2;
    }
}
