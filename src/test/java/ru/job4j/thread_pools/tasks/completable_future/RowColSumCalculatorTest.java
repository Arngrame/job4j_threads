package ru.job4j.thread_pools.tasks.completable_future;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RowColSumCalculatorTest {

    private final int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    private final RowColSum[] expectedResult = {
            new RowColSum(6, 12),
            new RowColSum(15, 15),
            new RowColSum(24, 18)
    };

    @Test
    public void whenSeqSum() {
        RowColSum[] actualResult = RowColSumCalculator.sum(matrix);
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    public void whenAsyncSum() {
        RowColSum[] actualResult = RowColSumCalculator.asyncSum(matrix);
        assertThat(expectedResult).isEqualTo(actualResult);
    }
}