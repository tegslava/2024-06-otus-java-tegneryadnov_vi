package ru.otus;

import java.util.*;

public class CustomerService {

    private final NavigableMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        var fEntry = map.firstEntry();
        var fKey = fEntry.getKey();
        return new AbstractMap.SimpleEntry<>(
                new Customer(fKey.getId(), fKey.getName(), fKey.getScores()), fEntry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var hEntry = map.higherEntry(customer);
        if (hEntry == null) {
            return map.containsKey(customer) ? map.lastEntry() : null;
        }
        return hEntry;
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
