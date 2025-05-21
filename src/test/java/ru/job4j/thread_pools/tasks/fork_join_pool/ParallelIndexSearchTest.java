package ru.job4j.thread_pools.tasks.fork_join_pool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParallelIndexSearchTest {

    private final String[] colors = {"RED-0", "GREEN-1", "BLUE-2", "CYAN-3", "MAGENTA-4", "YELLOW-5", "BLACK-6",
            "WHITE-7", "ORANGE-8", "SKY-9", "PINK-10", "PURPLE-11", "GREY-12", "BROWN-13", "GOLDEN-14", "SANDY-15",
            "CHOCOLATE-16"};
    private final String[] measures = {"inch", "foot", "yard", "centimetre", "metre", "kilometre"};
    private final Integer[] indexes = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final Integer[] otherIndexes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120};



    @Test
    public void whenParallelSearchString() {
        ParallelIndexSearch<String> index = new ParallelIndexSearch<>(colors, "GREY-12", 0, colors.length);
        Integer actualResult = index.searchIndex(colors, "GREY-12");

        assertEquals(12, actualResult);
    }

    @Test
    public void whenLinearSearchString() {
        ParallelIndexSearch<String> index = new ParallelIndexSearch<>(measures, "foot", 1, measures.length);
        Integer actualResult = index.searchIndex(measures, "foot");

        assertEquals(1, actualResult);
    }

    @Test
    public void whenParallelSearchInteger() {
        ParallelIndexSearch<Integer> index = new ParallelIndexSearch<>(otherIndexes, 110, 0, otherIndexes.length);
        Integer actualResult = index.searchIndex(otherIndexes, 110);

        assertEquals(10, actualResult);
    }

    @Test
    public void whenLinearSearchInteger() {
        ParallelIndexSearch<Integer> index = new ParallelIndexSearch<>(indexes, 1, 1, indexes.length);
        Integer actualResult = index.searchIndex(indexes, 1);

        assertEquals(0, actualResult);
    }

    @Test
    public void whenParallelSearchButValueDoesNotExist() {
        ParallelIndexSearch<Integer> index = new ParallelIndexSearch<>(otherIndexes, 54, 0, otherIndexes.length);
        Integer actualResult = index.searchIndex(otherIndexes, 1);

        assertEquals(-1, actualResult);
    }

    @Test
    public void whenLinearSearchButValueDoesNotExist() {
        ParallelIndexSearch<Integer> index = new ParallelIndexSearch<>(indexes, -30, 0, indexes.length);
        Integer actualResult = index.searchIndex(indexes, -30);

        assertEquals(-1, actualResult);
    }

}