package com.example.automatingtextprocessing.service;

import com.example.automatingtextprocessing.exception.DuplicateEntryException;
import com.example.automatingtextprocessing.exception.EntryNotFoundException;
import com.example.automatingtextprocessing.exception.InvalidDataException;
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
    public boolean addEntry(T id, String content)
            throws InvalidDataException, DuplicateEntryException {

        if (id == null) {
            throw new InvalidDataException("ID cannot be null.");
        }

        if (content == null || content.trim().isEmpty()) {
            throw new InvalidDataException("Content cannot be empty.");
        }

        if (dataMap.containsKey(id)) {
            throw new DuplicateEntryException("An entry with this ID already exists.");
        }

        dataMap.put(id, new Data<>(id, content));
        return true;
    }

    // Read
    @Override
    public Data<T> getEntry(T id) throws EntryNotFoundException {
        if (!dataMap.containsKey(id)) {
            throw new EntryNotFoundException("No entry found with the given ID.");
        }
        return dataMap.get(id);
    }

    // Update
    @Override
    public boolean updateEntry(T id, String newContent)
            throws EntryNotFoundException, InvalidDataException {

        if (!dataMap.containsKey(id)) {
            throw new EntryNotFoundException("Cannot update. Entry not found.");
        }

        if (newContent == null || newContent.trim().isEmpty()) {
            throw new InvalidDataException("New content cannot be empty.");
        }

        dataMap.get(id).setContent(newContent);
        return true;
    }

    // Delete
    @Override
    public boolean deleteEntry(T id) throws EntryNotFoundException {
        if (!dataMap.containsKey(id)) {
            throw new EntryNotFoundException("Cannot delete. Entry not found.");
        }

        dataMap.remove(id);
        return true;
    }

    // List all
    @Override
    public List<Data<T>> getAllEntries() {
        return new ArrayList<>(dataMap.values());
    }
}
