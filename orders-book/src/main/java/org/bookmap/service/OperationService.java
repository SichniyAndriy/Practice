package org.bookmap.service;

public interface OperationService {
    String SEPARATOR_IN_FILES = ",";
    void doOperation(String line);
    boolean isType(String type);
}
