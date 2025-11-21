package org.bookmap.service;

import org.bookmap.dao.FileWriter;
import org.bookmap.model.OrdersBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService implements OperationService {
    private final OrdersBook ordersBook;

    private final FileWriter fileWriter;

    public QueryService(@Autowired OrdersBook ordersBook,
                        @Autowired FileWriter fileWriter) {
        this.ordersBook = ordersBook;
        this.fileWriter = fileWriter;
    }

    @Override
    public void doOperation(String line) {
        String[] arr = line.split(SEPARATOR_IN_FILES);
        String result = switch(arr.length) {
            case 2 -> arr[1].equals("best_ask") ? ordersBook.bestAsk() : ordersBook.bestBid();
            case 3 -> String.valueOf(ordersBook.getSizeByPrice(Integer.parseInt(arr[2])));
            default -> throw new IllegalStateException("Unexpected value: " + arr.length);
        };
        fileWriter.toFile(result);
    }

    @Override
    public boolean isType(String type) {
        return type.equalsIgnoreCase("q");
    }
}
