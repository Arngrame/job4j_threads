package ru.job4j.thread_pools.tasks.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class RowColSumCalculator {

    private static RowColSum calculateSum(int[][] matrix, int rowIndex) {
        int rowSum = 0;
        int colSum = 0;

        for (int colIndex = 0; colIndex < matrix[rowIndex].length; colIndex++) {
            rowSum += matrix[rowIndex][colIndex];
            colSum += matrix[colIndex][rowIndex];
        }

        return new RowColSum(rowSum, colSum);
    }

    public static RowColSum[] sum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new RowColSum[0];
        }

        return IntStream.range(0, matrix.length)
                .mapToObj(rowIndex -> calculateSum(matrix, rowIndex))
                .toArray(RowColSum[]::new);
    }

    public static RowColSum[] asyncSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new RowColSum[0];
        }

        RowColSum[] sumArray = new RowColSum[matrix.length];
        CompletableFuture<Void>[] futureTasks = new CompletableFuture[matrix.length];

        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            final int index = rowIndex;

            futureTasks[rowIndex] = CompletableFuture
                    .supplyAsync(() -> calculateSum(matrix, index))
                    .thenAccept(sum -> sumArray[index] = sum);
        }

        CompletableFuture.allOf(futureTasks).join();
        return sumArray;
    }
}