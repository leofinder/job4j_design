package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).first().isNotNull().isEqualTo("first");
        assertThat(list).last().isNotNull().isEqualTo("five");
        assertThat(list).isNotEmpty()
                .hasSize(5)
                .containsSequence("second", "three")
                .allMatch(e -> e.length() > 2);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "four", "second", "five");
        assertThat(set).isNotEmpty()
                .hasSize(5)
                .doesNotHaveDuplicates()
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .doesNotContain("six");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsValues(0, 1, 2, 3, 4)
                .containsKeys("first", "four", "second", "five", "three")
                .doesNotContainValue(5)
                .containsEntry("second", 1);
    }
}