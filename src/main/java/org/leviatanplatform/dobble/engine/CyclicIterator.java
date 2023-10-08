package org.leviatanplatform.dobble.engine;

import java.util.List;

public class CyclicIterator<T> {

    private Integer index;
    private List<T> listItems;

    public CyclicIterator(List<T> listItems) {
        this.listItems = listItems;
    }

    public  T nextItem() {
        index = index == null ? 0 : ( index + 1 ) % listItems.size();
        return listItems.get(index);
    }
}
