package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = in.pop();
        size--;
        return item;
    }

    public void push(T value) {
        for (int i = 0; i < size; i++) {
            out.push(in.pop());
        }
        in.push(value);
        for (int i = 0; i < size; i++) {
            in.push(out.pop());
        }
        size++;
    }
}