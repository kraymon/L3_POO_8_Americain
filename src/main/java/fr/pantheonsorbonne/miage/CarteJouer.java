package fr.pantheonsorbonne.miage;
import java.util.LinkedList;
import java.util.Random;

public class CarteJouer {
    static LinkedList<Card> playedCard; 
    Random random = new Random();

    public void firstCard(){
        playedCard.add(Distribution.getRandomCard());
    }
    
}
