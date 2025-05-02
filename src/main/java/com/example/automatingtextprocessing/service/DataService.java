package com.example.automatingtextprocessing.service;

import com.example.automatingtextprocessing.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataService<T> implements DataServiceInterface<T> {
    private final Map<T, Data<T>> dataMap;

    public DataService() {
        this.dataMap = new HashMap<>();
    }

    // Create
    @Override
    public boolean addEntry(T id, String content) throws Exception {

        if (content.trim().isEmpty()) throw new Exception("content can no to empty");

        Data<T> entry = new Data<>(id, content);
        if (dataMap.containsKey(entry.getId())) {
            return false;
        }
        dataMap.put(entry.getId(), entry);
        return true;
    }

    // Read
    @Override
    public Data<T> getEntry(T id) {
        return dataMap.get(id);
    }

    // Update
    @Override
    public boolean updateEntry(T id, String newContent) {
        Data<T> entry = dataMap.get(id);
        if (entry != null) {
            entry.setContent(newContent);
            return true;
        }
        return false;
    }

    // Delete
    @Override
    public boolean deleteEntry(T id) {
        return dataMap.remove(id) != null;
    }

    // List all
    @Override
    public List<Data<T>> getAllEntries() {
        return new ArrayList<>(dataMap.values());
    }
}
