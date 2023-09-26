package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAddWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfEquals() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 8, 2, 9));
        ListUtils.removeIf(input, p -> p == 2);
        assertThat(input).hasSize(4).containsSequence(1, 3, 8, 9);
    }

    @Test
    void whenRemoveIfBigger() {
        input = new ArrayList<>(Arrays.asList(1, 5, 3, 8, 9));
        ListUtils.removeIf(input, p -> p > 3);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIfEquals() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 8, 2, 9));
        ListUtils.replaceIf(input, p -> p == 2, 12);
        assertThat(input).hasSize(6).containsSequence(1, 12, 3, 8, 12, 9);
    }

    @Test
    void whenReplaceIfBigger() {
        input = new ArrayList<>(Arrays.asList(1, 5, 3, 8, 9));
        ListUtils.replaceIf(input, p -> p > 3, null);
        assertThat(input).hasSize(5).containsSequence(1, null, 3, null, null);
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 8, 2, 9));
        ListUtils.removeAll(input, Arrays.asList(1, 8, 9));
        assertThat(input).hasSize(3).containsSequence(2, 3, 2);
    }

    @Test
    void whenRemoveAllNoMatches() {
        input = new ArrayList<>(Arrays.asList(1, 5, 3, 8, 9));
        ListUtils.removeAll(input, Arrays.asList(0, 11, 4));
        assertThat(input).hasSize(5).containsSequence(1, 5, 3, 8, 9);
    }
}