package ru.job4j.thread_pools.tasks.fork_join_pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T value;
    private final int leftIndex;
    private final int rightIndex;

    public ParallelIndexSearch(T[] array, T value, int leftIndex, int rightIndex) {
        this.array = array;
        this.value = value;
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
    }

    @Override
    protected Integer compute() {
        if (rightIndex - leftIndex <= 10) {
            return linearIndexSearch();
        }

        int middleIndex = (leftIndex + rightIndex) / 2;

        ParallelIndexSearch<T> searchInLeftSubArray = new ParallelIndexSearch<>(array, value, leftIndex, middleIndex);
        ParallelIndexSearch<T> searchInRightSubArray = new ParallelIndexSearch<>(array, value, middleIndex + 1, rightIndex);

        searchInLeftSubArray.fork();
        searchInRightSubArray.fork();

        Integer resultFromLeftSubArray = searchInLeftSubArray.join();
        Integer resultFromRightSubArray = searchInRightSubArray.join();

        return resultFromLeftSubArray != -1 ? resultFromLeftSubArray : resultFromRightSubArray;
    }

    private Integer linearIndexSearch() {
        return IntStream.range(leftIndex, rightIndex)
                .filter(i -> array[i].equals(value))
                .findFirst()
                .orElse(-1);
    }

    public Integer searchIndex(T[] array, T value) {
        if (array == null || array.length == 0) {
            return -1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelIndexSearch<>(array, value, 0, array.length - 1));
    }
}
