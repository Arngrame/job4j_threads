package ru.job4j.thread_pools.tasks.fork_join_pool;

public class MergeSort {

    private static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    private static int[] sort(int[] array, int from, int to) {
        if (from == to) {
            return new int[]{array[from]};
        }

        int middle = (from + to) / 2;

        int[] leftSortedArray = sort(array, from, middle);
        int[] rightSortedArray = sort(array, middle + 1, to);
        return mergeSubArrays(
                leftSortedArray,
                rightSortedArray
        );
    }

    public static int[] mergeSubArrays(int[] leftSubArray, int[] rightSubArray) {
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = 0;
        int[] mergedArray = new int[leftSubArray.length + rightSubArray.length];

        while (mergedIndex != mergedArray.length) {
            if (leftIndex == leftSubArray.length) {
                mergedArray[mergedIndex++] = rightSubArray[rightIndex++];
            } else if (rightIndex == rightSubArray.length) {
                mergedArray[mergedIndex++] = leftSubArray[leftIndex++];
            } else if (leftSubArray[leftIndex] <= rightSubArray[rightIndex]) {
                mergedArray[mergedIndex++] = leftSubArray[leftIndex++];
            } else {
                mergedArray[mergedIndex++] = rightSubArray[rightIndex++];
            }
        }

        return mergedArray;
    }
}