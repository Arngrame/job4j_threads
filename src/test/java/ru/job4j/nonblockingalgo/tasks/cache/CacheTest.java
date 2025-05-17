package ru.job4j.nonblockingalgo.tasks.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CacheTest {

    @Test
    public void whenAddFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        var find = cache.findById(base.id());
        assertThat(find.get().name())
                .isEqualTo("Base");
    }

    @Test
    public void whenAddUpdateFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(new Base(1, "Base updated", 1));
        var find = cache.findById(base.id());
        assertThat(find.get().name())
                .isEqualTo("Base updated");
    }

    @Test
    public void whenAddDeleteFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.delete(1);
        var find = cache.findById(base.id());
        assertThat(find.isEmpty()).isTrue();
    }

    @Test
    public void whenMultiUpdateThrowException() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(base);
        assertThatThrownBy(() -> cache.update(base))
                .isInstanceOf(OptimisticException.class);
    }

    @Test
    public void whenMultiUpdateSuccess() {
        var base1 = new Base(1, "Base1", 1);
        var base2 = new Base(1, "Base2", 1);
        var base3 = new Base(1, "Base3", 2);

        var cache = new Cache();
        cache.add(base1);
        cache.update(base2);
        cache.update(base3);

        var find = cache.findById(1);

        assertThat(find.get().name()).isEqualTo("Base3");
    }

    @Test
    public void whenMultiUpdateFail() {
        var base1 = new Base(1, "Base1", 1);
        var base2 = new Base(1, "Base2", 2);

        var cache = new Cache();
        cache.add(base1);

        assertThatThrownBy(() -> cache.update(base2)).isInstanceOf(OptimisticException.class);
    }

    @Test
    public void whenVersionUpdatedDespiteEqualFields() {
        var base1 = new Base(1, "Base1", 1);
        var base2 = new Base(1, "Base1", 1);

        var cache = new Cache();
        cache.add(base1);
        cache.update(base2);

        var find = cache.findById(1);

        assertThat(find.get().name()).isEqualTo("Base1");
        assertThat(find.get().version()).isEqualTo(2);
    }
}