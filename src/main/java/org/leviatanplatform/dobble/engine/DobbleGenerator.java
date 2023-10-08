package org.leviatanplatform.dobble.engine;

import java.util.ArrayList;
import java.util.List;

public class DobbleGenerator {

    private final int numItemsPerCard;
    private int nextItem = 0;

    public DobbleGenerator(int numItemsPerCard) {
        this.numItemsPerCard = numItemsPerCard;
    }

    public List<Card> generate() throws ValidationException {


        List<Card> listCard = new ArrayList<>();

        Card card0 = new Card();
        fillWithNewItems(card0);
        listCard.add(card0);

        // FIXME finish

        CardValidator.validate(listCard, numItemsPerCard);
        return listCard;
    }

    private void fillWithNewItems(Card card) {

        int numItemsToAdd = numItemsPerCard - card.getListItems().size();

        for (int i = 0; i < numItemsToAdd; i++) {
            card.getListItems().add(nextItem++);
        }
    }
}
