package fr.pantheonsorbonne.miage;
import java.util.LinkedList;
import java.util.Random;

public class CarteJouer {
    static LinkedList<Card> playedCard; 
    Random random = new Random();

    public void firstCard(){
        int randomIndex = random.nextInt(52);
        Card carte = Distribution.packet.get(randomIndex);
        Distribution.packet.remove(randomIndex);
        playedCard.add(carte);
    }
}
