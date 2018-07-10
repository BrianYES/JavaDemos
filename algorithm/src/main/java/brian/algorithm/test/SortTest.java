package brian.algorithm.test;

public class SortTest {

    public static void main(String[] args) {
        int[] arr = TestHelper.generateRandomIntArray(10, 0, 100000);
        int[] arr2 = TestHelper.copyArray(arr);
//        int[] arr3 = TestHelper.copyArray(arr);

//        TestHelper.testSort("brian.algorithm.sort.SelectionSort", "sort", arr3);
        TestHelper.testSort("brian.algorithm.sort.InsertionSort", "sort", arr);
//        TestHelper.testSort("brian.algorithm.sort.InsertionSort", "sort2", arr);
//        TestHelper.testSort("brian.algorithm.sort.BubbleSort", "sort", arr);
//        TestHelper.testSort("brian.algorithm.sort.BubbleSort", "sort2", arr2);
//        TestHelper.testSort("brian.algorithm.sort.ShellSort", "sort", arr);
        TestHelper.testSort("brian.algorithm.sort.MergeSort", "sort", arr2);

    }
}
