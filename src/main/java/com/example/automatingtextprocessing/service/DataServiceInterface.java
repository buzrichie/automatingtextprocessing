package com.example.automatingtextprocessing.service;

import com.example.automatingtextprocessing.model.Data;

import java.util.List;

public interface DataServiceInterface<T> {

    boolean addEntry(T id, String content) throws Exception;

    Data<T> getEntry(T id);

    boolean updateEntry(T id, String newContent);

    boolean deleteEntry(T id);

    List<Data<T>> getAllEntries();

}
