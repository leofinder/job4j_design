package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;
    private int outSize;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outSize == 0) {
            for (int i = 0; i < size; i++) {
                out.push(in.pop());
                outSize++;
            }
        }
        T item = out.pop();
        size--;
        outSize--;
        return item;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}