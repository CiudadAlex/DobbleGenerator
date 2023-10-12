package org.leviatanplatform.dobble.engine;

import org.leviatanplatform.dobble.engine.exceptions.IndexFinishedException;
import org.leviatanplatform.dobble.engine.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class DobbleGenerator {

    private final int numItemsPerCard;
    private final int numCards;
    private final int numTotalItems;
    private final int primeNumber;
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
     * Example for p = 3:
     *
     *  0  1  2  3
     *  0  4  5  6
     *  0  7  8  9
     *  0 10 11 12
     *  1  4  7 10
     *  1  5  8 11
     *  1  6  9 12
     *  2  4  8 12
     *  2  5  9 10
     *  2  6  7 11
     *  3  4  9 11
     *  3  5  7 12
     *  3  6  8 10
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
        this.primeNumber = primeNumber;
    }

    public List<Card> generate() throws ValidationException {

        List<Card> listCard = new ArrayList<>();

        Card card0 = new Card();
        for (int i = 0; i < numItemsPerCard; i++) {
            card0.getListItems().add(i);
        }

        listCard.add(card0);
        List<Integer> listRestOfItems = new ArrayList<>();
        for (int i = numItemsPerCard; i < numTotalItems; i++) {
            listRestOfItems.add(i);
        }

        for (int i = 0; i < numItemsPerCard; i++) {

            int jump = i == 0 ? 1 : primeNumber + i - 1;
            ListIterator<Integer> listIterator = new ListIterator<>(listRestOfItems, jump);

            for (int k = 0; k < primeNumber; k++) {

                Card card = new Card();
                card.getListItems().add(i);
                listCard.add(card);

                for (int m = 0; m < primeNumber; m++) {
                    card.getListItems().add(listIterator.next());
                }
            }
        }

        //fullValidation(listCard);
        return listCard;
    }

    public List<Card> generateOld() throws ValidationException {

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

        fullValidation(listCard);
        return listCard;
    }

    private void fullValidation(List<Card> listCard) throws ValidationException {

        CardValidator.validate(listCard, numItemsPerCard);

        if (numCards != listCard.size()) {
            throw new ValidationException("Not generated all possible cards");
        }
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
