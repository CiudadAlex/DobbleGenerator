package org.leviatanplatform.dobble.engine;

import java.util.ArrayList;
import java.util.List;

public class DobbleGenerator {

    private final int numItemsPerCard;
    private int nextItem = 0;

    public DobbleGenerator(int numItemsPerCard) {
        this.numItemsPerCard = numItemsPerCard;
    }

    public List<Card> generate(int numberOfCards) throws ValidationException {

        List<Card> listCard = new ArrayList<>();
        List<Card> listCardUnfinished = new ArrayList<>();

        for (int i = 0; i < numberOfCards; i++) {

            Card card = new Card();
            listCardUnfinished.add(card);
            listCard.add(card);
        }

        Card card = listCardUnfinished.remove(0);
        List<Integer> newItems = fillWithNewItemsAndReturnThem(card);
        addOneItemToEach(listCardUnfinished, newItems);

        card = listCardUnfinished.remove(0);
        newItems = fillWithNewItemsAndReturnThem(card);
        addOneItemToEach(listCardUnfinished, newItems);

        card = listCardUnfinished.remove(0);
        newItems = fillWithNewItemsAndReturnThem(card);
        addOneItemToEach(listCardUnfinished, newItems);

        card = listCardUnfinished.remove(0);
        newItems = fillWithNewItemsAndReturnThem(card);
        addOneItemToEach(listCardUnfinished, newItems);

        card = listCardUnfinished.remove(0);
        newItems = fillWithNewItemsAndReturnThem(card);
        addOneItemToEach(listCardUnfinished, newItems);

        card = listCardUnfinished.remove(0);
        newItems = fillWithNewItemsAndReturnThem(card);
        addOneItemToEach(listCardUnfinished, newItems);

        // FIXME validate
        // CardValidator.validate(listCard, numItemsPerCard);
        return listCard;
    }

    private void addOneItemToEach(List<Card> listCard, List<Integer> listItems) {

        CyclicIterator<Integer> iterItems = new CyclicIterator<>(listItems);

        for (Card card : listCard) {
            card.getListItems().add(iterItems.nextItem());
        }
    }

    private List<Integer> fillWithNewItemsAndReturnThem(Card card) {

        List<Integer> newItems = new ArrayList<>();
        int numItemsToAdd = numItemsPerCard - card.getListItems().size();

        for (int i = 0; i < numItemsToAdd; i++) {
            card.getListItems().add(nextItem);
            newItems.add(nextItem);
            nextItem++;
        }

        return newItems;
    }
}
