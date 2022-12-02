package fr.pantheonsorbonne.miage;

import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name, List<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public void showHand(){
        System.out.println("main de " + name);
        for (Card card : hand) {
            System.out.println(card.getValeur() + " " + card.getCouleur());
        }
        System.out.println();
    }

}
