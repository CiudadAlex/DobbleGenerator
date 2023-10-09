package org.leviatanplatform.dobble;

import org.leviatanplatform.dobble.engine.Card;
import org.leviatanplatform.dobble.engine.CardValidator;
import org.leviatanplatform.dobble.engine.DobbleGenerator;
import org.leviatanplatform.dobble.engine.exceptions.ValidationException;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ValidationException {

        int numItemsPerCard = 6;
        DobbleGenerator dobbleGenerator = new DobbleGenerator(numItemsPerCard);

        List<Card> listCard = dobbleGenerator.generate();

        for (Card card : listCard) {
            System.out.println(card);
        }

        CardValidator.validate(listCard, numItemsPerCard);
    }
}
