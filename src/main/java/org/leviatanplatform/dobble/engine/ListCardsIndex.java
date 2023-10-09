package org.leviatanplatform.dobble.engine;

import org.leviatanplatform.dobble.engine.exceptions.IndexFinishedException;

import java.util.ArrayList;
import java.util.List;

public class ListCardsIndex {

    private final int numCards;
    private final int numItemsPerCard;
    private final List<Card> listCard;

    private int[] index;

    public ListCardsIndex(List<Card> listCard, int numItemsPerCard) {
        this.numCards = listCard.size();
        this.numItemsPerCard = numItemsPerCard;
        this.listCard = listCard;
    }

    public List<Integer> getNextCombinationOfItems() throws IndexFinishedException {

        moveToNextIndex();

        List<Integer> listItems = new ArrayList<>();

        for (int i = 0; i < numCards; i++) {
            int idx = index[i];
            listItems.add(listCard.get(i).getListItems().get(idx));
        }

        return listItems;
    }

    private void moveToNextIndex() throws IndexFinishedException {

        if (index == null) {
            this.index = new int[numCards];
            return;
        }

        for (int i = 0; i < numCards; i++) {

            index[i] = index[i] + 1;
            index[i] = index[i] % numItemsPerCard;

            if (index[i] != 0) {
                return;
            }
        }

        throw new IndexFinishedException();
    }
}
