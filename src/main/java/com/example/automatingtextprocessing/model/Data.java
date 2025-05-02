package com.example.automatingtextprocessing.model;

import java.util.Objects;

public class Data <T> implements DataInterface<T>{
    private final T id;
    private String content;

    public Data(T id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public T getId() { return id; }

    @Override
    public String getContent() { return content; }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data<?> data = (Data<?>) o;
        return Objects.equals(id, data.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Content: " + content;
    }
}
