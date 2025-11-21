package org.bookmap.util;

import java.util.List;
import java.util.Optional;
import org.bookmap.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationUtil {
    private final List<OperationService> operationServiceList;

    @Autowired
    OperationUtil(@Autowired List<OperationService> operationServiceList) {
        this.operationServiceList = operationServiceList;
    }

    public Optional<OperationService> chooseOperation(String symbol) {
        return operationServiceList.
                stream().
                filter(el -> el.isType(symbol)).
                findFirst();
    }
}
