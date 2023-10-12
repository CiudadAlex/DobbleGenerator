package org.leviatanplatform.dobble.engine;

import java.util.List;

public class ListIterator<T> {

    private final int jump;
    private Integer index;
    private final List<T> list;

    public ListIterator(List<T> list, int jump) {
        this.list = list;
        this.jump = jump;
    }

    public T next() {

        if (index == null) {
            index = 0;
        } else {
            index = index + jump;
            index = index % list.size();
        }

        return list.get(index);
    }


}
