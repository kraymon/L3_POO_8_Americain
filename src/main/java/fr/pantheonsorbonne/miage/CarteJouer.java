package fr.pantheonsorbonne.miage;
import java.util.LinkedList;
import java.util.Random;

public class CarteJouer {
    static LinkedList<Card> playedCard = new LinkedList<Card>(); 
    Random random = new Random();

    public static void firstCard(){
        playedCard.add(Distribution.getRandomCard());
    }

}
