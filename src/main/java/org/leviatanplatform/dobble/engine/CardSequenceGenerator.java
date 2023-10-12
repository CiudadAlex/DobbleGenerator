package org.leviatanplatform.dobble.engine;

import org.leviatanplatform.dobble.engine.exceptions.IndexFinishedException;

public class CardSequenceGenerator {

    private final int numItemsPerCard;
    private final int numTotalItems;
    private int[] combination;

    public CardSequenceGenerator(int numItemsPerCard, int numTotalItems) {
        this.numItemsPerCard = numItemsPerCard;
        this.numTotalItems = numTotalItems;
    }

    public Card getNextCard() throws IndexFinishedException {

        Card card = generateNextCard();

        while (!card.hasDifferentItems()) {
            card = generateNextCard();
        }

        return card;
    }

    private Card generateNextCard() throws IndexFinishedException {

        moveToNextCombination();

        Card card = new Card();

        for (int item : combination) {
            card.getListItems().add(item);
        }

        return card;
    }

    private void moveToNextCombination() throws IndexFinishedException {

        if (combination == null) {
            this.combination = new int[numItemsPerCard];
            return;
        }

        for (int i = 0; i < combination.length; i++) {

            combination[i] = combination[i] + 1;
            combination[i] = combination[i] % numTotalItems;

            if (combination[i] != 0) {
                return;
            }
        }

        throw new IndexFinishedException();
    }
}
