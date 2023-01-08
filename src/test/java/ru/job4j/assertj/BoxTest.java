package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 8);
        String name = box.whatsThis();
        assertThat(name).isNotBlank()
                .isNotNull()
                .isEqualTo("Tetrahedron");
    }

    @Test
    void checkNumberOfVertexSphere() {
        Box box = new Box(0, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotNegative()
                .isNotPositive()
                .isEqualTo(0);
    }

    @Test
    void checkNumberOfVertexCube() {
        Box box = new Box(8, 3);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEven()
                .isEqualTo(8);
    }

    @Test
    void checkUnknownObjectIsNotExist() {
        Box box = new Box(12, 6);
        boolean isExist = box.isExist();
        assertThat(isExist).isFalse()
                .isEqualTo(false);
    }

    @Test
    void checkObjectIsExist() {
        Box box = new Box(8, 6);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue()
                .isNotEqualTo(false);
    }

    @Test
    void checkAreaCube() {
        Box box = new Box(8, 5);
        double area = box.getArea();
        assertThat(area).isGreaterThan(100)
                .isLessThan(1000)
                .isPositive();
    }

    @Test
    void checkAreaUnknownObject() {
        Box box = new Box(12, 6);
        double area = box.getArea();
        assertThat(area).isGreaterThan(-1)
                .isLessThan(1)
                .isNotPositive()
                .isNotNegative()
                .isNotNull();
    }
}