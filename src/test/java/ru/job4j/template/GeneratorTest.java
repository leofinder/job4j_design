package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class GeneratorTest {

    @Test
    public void whenValidMapThenGetValidString() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "cool_name");
        map.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        Generator generator = new TemplateGenerator();
        String result = generator.produce(template, map);
        String expected = "I am a leohyde, Who are you?";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenMissingValueInMapThenGetException() {
        Map<String, String> map = new HashMap<>();
        map.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        assertThatThrownBy(() -> new TemplateGenerator().produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Не найдены значения для подстановки");
    }

    @Test
    public void whenExtraValuesInMapThenGetException() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "cool_name");
        map.put("subject", "you");
        map.put("extra", "extra_value");
        String template = "I am a ${name}, Who are ${subject}?";
        assertThatThrownBy(() -> new TemplateGenerator().produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Лишние ключи в map");
    }

}