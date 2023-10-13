package org.leviatanplatform.dobble;

import org.leviatanplatform.dobble.engine.Card;
import org.leviatanplatform.dobble.engine.CardValidator;
import org.leviatanplatform.dobble.engine.DobbleGenerator;
import org.leviatanplatform.dobble.engine.exceptions.ValidationException;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ValidationException {

        generateForPrime(2);
        generateForPrime(3);
    }

    public static void generateForPrime(int primeNumber) throws ValidationException {

        System.out.println("###########################################");

        DobbleGenerator dobbleGenerator = new DobbleGenerator(primeNumber);

        List<Card> listCard = dobbleGenerator.generate();

        for (Card card : listCard) {
            System.out.println(card);
        }

        CardValidator.validate(listCard, primeNumber + 1);

        System.out.println("###########################################");
    }
}
