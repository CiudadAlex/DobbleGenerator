package org.leviatanplatform.dobble.engine;

import java.util.ArrayList;
import java.util.List;

public class CardValidator {

    public static void validate(List<Card> listCard) throws ValidationException {

        List<Card> listCardCopy = new ArrayList<>(listCard);

        for (Card card : listCard) {
            validate(card, listCardCopy);
        }
    }

    private static void validate(Card card, List<Card> listCard) throws ValidationException {

        for (Card cardIter : listCard) {

            if (cardIter != card) {
                matchesOnlyOneItem(cardIter, cardIter);
            }
        }
    }

    private static void matchesOnlyOneItem(Card card1, Card card2) throws ValidationException {

        if (card1.hasDifferentItems()) {
            throw new ValidationException("Repeated items in card: " + card1);
        }

        if (card2.hasDifferentItems()) {
            throw new ValidationException("Repeated items in card: " + card2);
        }

        if (card1.getListItems().size() != card2.getListItems().size()) {
            throw new ValidationException("Repeated items in card: " + card2);
        }

        int numMatches = 0;

        for (Integer item1 : card1.getListItems()) {

            if (card2.getListItems().contains(item1)) {
                numMatches++;
            }
        }

        if (numMatches != 1) {
            throw new ValidationException("Error in match: " + card1 + " != " + card2);
        }
    }
}
