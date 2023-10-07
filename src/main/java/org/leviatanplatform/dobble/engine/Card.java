package org.leviatanplatform.dobble.engine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Card {

    private final List<Integer> listItems;

    public Card(List<Integer> listItems) {
        this.listItems = listItems;
    }

    public List<Integer> getListItems() {
        return listItems;
    }

    public String toString() {
        return "" + listItems;
    }

    public boolean hasDifferentItems() {
        Set<Integer> setItems = new HashSet<>(listItems);
        return setItems.size() == listItems.size();
    }
}
