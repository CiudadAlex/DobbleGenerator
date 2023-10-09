package org.leviatanplatform.dobble.engine;

import org.leviatanplatform.dobble.engine.exceptions.IndexFinishedException;
import org.leviatanplatform.dobble.engine.exceptions.ValidationException;

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

        while (true) {
            Card newCard = generateNewCard(listCard);

            if (newCard == null) {
                break;
            }

            listCard.add(newCard);
        }

        CardValidator.validate(listCard, numItemsPerCard);
        return listCard;
    }

    private Card generateNewCard(List<Card> listCard) {

        Card cardUnderConstruction = generateNewCardOnlySelectItems(listCard);

        if (cardUnderConstruction == null) {
            return null;
        }

        fillWithNewItemsAndReturnThem(cardUnderConstruction);
        return cardUnderConstruction;
    }

    private Card generateNewCardOnlySelectItems(List<Card> listCard) {

        ListCardsIndex listCardsIndex = new  ListCardsIndex(listCard, numItemsPerCard);

        while (true) {

            try {

                List<Integer> listItems = listCardsIndex.getNextCombinationOfItems();

                Card newCard = new Card();
                newCard.getListItems().addAll(listItems);

                List<Card> listCardExtended = new ArrayList<>(listCard);
                listCardExtended.add(newCard);
                CardValidator.validateCardUnderConstruction(newCard, listCardExtended);

                return newCard;

            } catch (ValidationException e) {
                // Try next combination

            } catch (IndexFinishedException e) {
                // No more cards found
                return null;
            }
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
