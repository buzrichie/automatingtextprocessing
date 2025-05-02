package com.example.automatingtextprocessing.model;

public interface DataInterface<T> {

    T getId();
    String getContent();
    void setContent(String content);
}
