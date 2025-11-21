package org.bookmap.service;

import org.bookmap.model.OrdersBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OperationService {
    private final OrdersBook ordersBook;

    public OrderService(@Autowired OrdersBook ordersBook) {
        this.ordersBook = ordersBook;
    }

    @Override
    public void doOperation(String line) {
        String[] arr = line.split(SEPARATOR_IN_FILES);
        int size = Integer.parseInt(arr[2]);
        switch (arr[1]) {
            case "buy" -> ordersBook.buy(size);
            case "sell" -> ordersBook.sell(size);
            default -> throw new IllegalStateException("Unexpected value: " + arr[1]);
        }
    }

    @Override
    public boolean isType(String type) {
        return type.equalsIgnoreCase("o");
    }
}
