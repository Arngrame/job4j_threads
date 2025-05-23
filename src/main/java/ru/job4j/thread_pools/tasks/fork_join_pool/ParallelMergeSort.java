package ru.job4j.thread_pools.tasks.fork_join_pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {

    private final int[] array;
    private final int from;
    private final int to;

    public ParallelMergeSort(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected int[] compute() {
        if (from == to) {
            return new int[] {array[from]};
        }
        int middle = (from + to) / 2;

        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, middle);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, middle + 1, to);

        leftSort.fork();
        rightSort.fork();

        int[] left = leftSort.join();
        int[] right = rightSort.join();

        return MergeSort.mergeSubArrays(left, right);
    }

    public static int[] sort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
    }
}