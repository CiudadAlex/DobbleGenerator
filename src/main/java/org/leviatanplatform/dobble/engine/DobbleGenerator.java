package org.leviatanplatform.dobble.engine;

import org.leviatanplatform.dobble.engine.exceptions.IndexFinishedException;
import org.leviatanplatform.dobble.engine.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class DobbleGenerator {

    private final int numItemsPerCard;
    private final int numCards;
    private final int numTotalItems;
    private int nextItem = 1;

    /**
     * Generates Dobble cards.
     *
     * p = prime number
     * items per card = p + 1
     * cards = p^2 + p + 1
     * items = p^2 + p + 1
     *
     * Example for p = 2:
     *
     * 0 1 2
     * 0 3 4
     * 0 5 6
     * 1 3 5
     * 1 4 6
     * 2 3 6
     * 2 4 5
     *
     * @param primeNumber prime number
     */
    public DobbleGenerator(int primeNumber) {

        if (!DobbleUtils.isPrimeNumber(primeNumber)) {
            throw new IllegalArgumentException("The given number is no prime: " + primeNumber);
        }

        this.numItemsPerCard = primeNumber + 1;
        this.numCards = primeNumber * primeNumber + primeNumber + 1;
        this.numTotalItems = primeNumber * primeNumber + primeNumber + 1;
    }

    public List<Card> generate() throws ValidationException {

        List<Card> listCard = new ArrayList<>();

        for (int i = 0; i < numItemsPerCard; i++) {

            Card card = new Card();
            card.getListItems().add(0);
            fillWithNewItemsAndReturnThem(card);
            listCard.add(card);
        }

        Card card = generateNewCard(listCard);

        while (card != null) {
            listCard.add(card);
            card = generateNewCard(listCard);
        }

        CardValidator.validate(listCard, numItemsPerCard);

        if (numCards != listCard.size()) {
            throw new ValidationException("Not generated all possible cards");
        }

        return listCard;
    }

    private Card generateNewCard(List<Card> listCard) {

        CardSequenceGenerator cardSequenceGenerator = new CardSequenceGenerator(numItemsPerCard, numTotalItems);

        while (true) {

            try {

                Card newCard = cardSequenceGenerator.getNextCard();
                CardValidator.validateCardUnderConstruction(newCard, listCard);

                return newCard;

            } catch (ValidationException e) {
                // Try next combination

            } catch (IndexFinishedException e) {
                // No more cards found
                return null;
            }
        }
    }

    private void fillWithNewItemsAndReturnThem(Card card) {

        int numItemsToAdd = numItemsPerCard - card.getListItems().size();

        for (int i = 0; i < numItemsToAdd; i++) {
            card.getListItems().add(nextItem);
            nextItem++;
        }
    }
}
