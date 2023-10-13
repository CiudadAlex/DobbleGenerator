package org.leviatanplatform.dobble.engine;

import org.leviatanplatform.dobble.engine.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class DobbleGenerator {

    private final int numItemsPerCard;
    private final int numCards;
    private final int numTotalItems;
    private final int primeNumber;

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
     *
     * Example for p = 5:
     *
     *  0  1  2  3  4  5
     *  0  6  7  8  9 10
     *  0 11 12 13 14 15
     *  0 16 17 18 19 20
     *  0 21 22 23 24 25
     *  0 26 27 28 29 30
     *  1  6 11 16 21 26
     *  1  7 12 17 22 27
     *  1  8 13 18 23 28
     *  1  9 14 19 24 29
     *  1 10 15 20 25 30
     *  2  6 12 18 24 30
     *  2  7 13 19 25 26
     *  2  8 14 20 21 27
     *  2  9 15 16 22 28
     *  2 10 11 17 23 29
     *  3  6 13 20 22 29
     *  3  7 14 16 23 30
     *  3  8 15 17 24 26
     *  3  9 11 18 25 27
     *  3 10 12 19 21 28
     *  4  6 14 17 25 28
     *  4  7 15 18 21 29
     *  4  8 11 19 22 30
     *  4  9 12 20 23 26
     *  4 10 13 16 24 27
     *  5  6 15 19 23 27
     *  5  7 11 20 24 28
     *  5  8 12 16 25 29
     *  5  9 13 17 21 30
     *  5 10 14 18 22 26
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

        MatrixCombinator<Integer> matrixCombinator = new MatrixCombinator<>(MatrixCombinator.buildMatrixOfNumbers(primeNumber, primeNumber + 1));
        addCards(listCard, 0, matrixCombinator.getHorizontal());
        addCards(listCard, 1, matrixCombinator.getVertical());

        for (int i = 0; i < numItemsPerCard - 2; i++) {

            List<List<Integer>> combinations = matrixCombinator.getDiagonal(i + 1);
            addCards(listCard, i + 2, combinations);
        }

        fullValidation(listCard);
        return listCard;
    }

    private void addCards(List<Card> listCard, Integer leaderNumber, List<List<Integer>> combinations) {

        for (List<Integer> combination : combinations) {
            Card card = new Card();
            card.getListItems().add(leaderNumber);
            card.getListItems().addAll(combination);
            listCard.add(card);
        }
    }

    private void fullValidation(List<Card> listCard) throws ValidationException {

        CardValidator.validate(listCard, numItemsPerCard);

        if (numCards != listCard.size()) {
            throw new ValidationException("Not generated all possible cards");
        }
    }
}
