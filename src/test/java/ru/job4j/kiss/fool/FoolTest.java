package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    public void whenNumber6ThenFizz() {
        String result = Fool.getCheckWord(6);
        assertThat(result).isEqualTo("Fizz");
    }

    @Test
    public void whenNumber10ThenBuzz() {
        String result = Fool.getCheckWord(10);
        assertThat(result).isEqualTo("Buzz");
    }

    @Test
    public void whenNumber30ThenFizzBuzz() {
        String result = Fool.getCheckWord(30);
        assertThat(result).isEqualTo("FizzBuzz");
    }

    @Test
    public void whenAnswerIsCorrect() {
        boolean result = Fool.isCorrect("Fizz", "Fizz");
        assertThat(result).isTrue();
    }

    @Test
    public void whenAnswerIsNotCorrect() {
        boolean result = Fool.isCorrect("Fizz", "Buzz");
        assertThat(result).isFalse();
    }

    @Test
    public void whenWrongAnswerThenNumberIsZero() {
        int result = Fool.calculateFizzBuzz(6, "Buzz");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void whenRightAnswerThenNumberIsNext() {
        int result = Fool.calculateFizzBuzz(6, "Fizz");
        assertThat(result).isEqualTo(7);
    }
}