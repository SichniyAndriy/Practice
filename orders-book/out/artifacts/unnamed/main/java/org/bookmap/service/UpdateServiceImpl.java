package org.bookmap.service;

import org.bookmap.model.OrdersBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateServiceImpl implements OperationService {
    private final OrdersBook ordersBook;

    @Autowired
    public UpdateServiceImpl(OrdersBook ordersBook) {
        this.ordersBook = ordersBook;
    }

    @Override
    public void doOperation(String line) {
        String[] arr = line.split(SEPARATOR_IN_FILES);
        int price = Integer.parseInt(arr[1]);
        int size = Integer.parseInt(arr[2]);
        ordersBook.update(price, size, arr[3]);
    }

    @Override
    public boolean isType(String type) {
        return type.equalsIgnoreCase("u");
    }
}
