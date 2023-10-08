package org.leviatanplatform.dobble;

import org.leviatanplatform.dobble.engine.Card;
import org.leviatanplatform.dobble.engine.DobbleGenerator;
import org.leviatanplatform.dobble.engine.ValidationException;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ValidationException {

        int numItemsPerCard = 6;
        DobbleGenerator dobbleGenerator = new DobbleGenerator(numItemsPerCard);

        // FIXME remove numberOfCards argument
        int numberOfCards = 20;
        List<Card> listCard = dobbleGenerator.generate(numberOfCards);

        for (Card card : listCard) {
            System.out.println(card);
        }
    }
}
