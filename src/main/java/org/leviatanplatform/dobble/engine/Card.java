package org.leviatanplatform.dobble.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Card {

    private final List<Integer> listItems = new ArrayList<>();

    public List<Integer> getListItems() {
        return listItems;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Integer item : listItems) {
            sb.append(spacedItem(item));
        }

        return sb.toString();
    }

    private String spacedItem(Integer item) {

        String strItem = item.toString();

        if (strItem.length() == 1) {
            return "   " + strItem;
        } else if (strItem.length() == 2) {
            return "  " + strItem;
        }

        return " " + strItem;
    }

    public boolean hasDifferentItems() {
        Set<Integer> setItems = new HashSet<>(listItems);
        return setItems.size() == listItems.size();
    }
}
