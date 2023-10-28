package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenAdd3ChildThenChildrenSizeIs3() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(1).get().children).hasSize(3);
    }

    @Test
    void whenAdd3ChildThenIsBinaryFalse() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary()).isFalse();
    }

    @Test
    void whenAddLessThan2ChildThenIsBinaryTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(5);
        tree.add(5, 3);
        tree.add(3, 1);
        tree.add(3, 6);
        tree.add(6, 2);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenAdd2ChildThenIsBinaryTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(5);
        tree.add(5, 3);
        tree.add(5, 7);
        tree.add(3, 6);
        tree.add(3, 4);
        assertThat(tree.isBinary()).isTrue();
    }
}