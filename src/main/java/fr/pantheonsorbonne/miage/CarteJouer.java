package fr.pantheonsorbonne.miage;

import java.util.LinkedList;
import java.util.Random;

public class CarteJouer {
    static LinkedList<Card> playedCard = new LinkedList<Card>();
    static Random random = new Random();

    public static void firstCard() {
        Card carte;
        int randomIndex;
        do {
            randomIndex = random.nextInt(Distribution.packet.size());
            carte = Distribution.packet.get(randomIndex);
        } while (carte.getValeur().equals("AS") || carte.getValeur().equals("VALET") || carte.getValeur().equals("DIX")
                || carte.getValeur().equals("SEPT") || carte.getValeur().equals("HUIT"));
        CarteJouer.playedCard.add(carte);
        Distribution.packet.remove(randomIndex);
    }

}
