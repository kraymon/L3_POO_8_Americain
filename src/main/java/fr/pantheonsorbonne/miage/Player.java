package fr.pantheonsorbonne.miage;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }

    public void pickCard(int number) {
        for (int i = 0; i < number; i++) {
            this.hand.add(Distribution.getRandomCard());
        }
    }

}
