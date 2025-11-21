package org.bookmap.model;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookmap.service.OperationService;
import org.springframework.stereotype.Component;

@Component
public class OrdersBook {
    Set<Ask> asks;
    Set<Bid> bids;
    Map<String, ElementMarket> container;

    public OrdersBook() {
        asks = new TreeSet<>(Comparator.comparingInt(ElementMarket::getPrice));
        bids = new TreeSet<>(Comparator.comparingInt(ElementMarket::getPrice).reversed());
    }

    public void update(int price, int size, String typeElement) {
        if (typeElement.equals("ask")) {
            updateAsks(price, size);
        } else {
            updateBids(price, size);
        }
    }

    private void updateAsks(int price, int size) {
        Optional<Ask> askOptional = asks.stream().filter(a -> a.getPrice() == price).findFirst();
        if (askOptional.isPresent()) {
            Ask ask = askOptional.get();
            ask.setSize(ask.getSize() + size);
        } else {
            asks.add(new Ask(price, size));
        }
    }

    private void updateBids(int price, int size) {
        Optional<Bid> askOptional = bids.stream().filter(a -> a.getPrice() == price).findFirst();
        if (askOptional.isPresent()) {
            Bid bid = askOptional.get();
            bid.setSize(bid.getSize() + size);
        } else {
            bids.add(new Bid(price, size));
        }
    }

    public String bestAsk() {
        Optional<Ask> optionalAsk = asks.stream().findFirst();
        String result;
        if (optionalAsk.isPresent()) {
            Ask ask = optionalAsk.get();
            result = ask.getPrice() + OperationService.SEPARATOR_IN_FILES + ask.getSize();
        } else {
            result = "NO DATA";
        }
        return result;
    }

    public String bestBid() {
        Optional<Bid> optionalAsk = bids.stream().findFirst();
        String result;
        if (optionalAsk.isPresent()) {
            Bid bid = optionalAsk.get();
            result = bid.getPrice() + OperationService.SEPARATOR_IN_FILES + bid.getSize();
        } else {
            result = "NO DATA";
        }
        return result;
    }

    public int getSizeByPrice(int price) {
        Stream<Ask> askStream = asks.stream();
        Stream<Bid> bidStream = bids.stream();
        Stream<ElementMarket> elementMarketStream = Stream.concat(askStream, bidStream);
        Optional<ElementMarket> first =
                elementMarketStream.filter(el -> el.getPrice() == price).findFirst();
        return first.map(ElementMarket::getSize).orElse(0);
    }

    public void buy(int size) {
        Optional<Ask> first = asks.stream().findFirst();
        if (first.isPresent()) {
            Ask ask = first.get();
            ask.setSize(ask.getSize() - size);
        }
    }

    public void sell(int size) {
        Optional<Bid> first = bids.stream().findFirst();
        if (first.isPresent()) {
            Bid bid = first.get();
            bid.setSize(bid.getSize() - size);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private abstract class ElementMarket {
        private int price;
        private int size;
    }
    private class Ask extends ElementMarket {
        Ask(int price, int size) {
            super(price, size);
        }
    }

    private class Bid extends ElementMarket {
        Bid(int price, int size) {
            super(price, size);
        }
    }
}
