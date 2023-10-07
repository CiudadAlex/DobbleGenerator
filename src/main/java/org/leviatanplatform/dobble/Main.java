package org.leviatanplatform.dobble;

import org.leviatanplatform.dobble.engine.Card;
import org.leviatanplatform.dobble.engine.DobbleGenerator;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        int numItemsPerCard = 6;
        List<Card> listCard = DobbleGenerator.generate(numItemsPerCard);

        for (Card card : listCard) {
            System.out.println(card);
        }
    }
}
