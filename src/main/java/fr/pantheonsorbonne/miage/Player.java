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

    public void showHand() {
        System.out.println();
        System.out.println("main de " + name);
        for (Card card : hand) {
            card.showCard();
        }
        System.out.println();
        
    }

    public void showPlayedCard(Card carte){
        System.out.print(name+" a joué : ");
        carte.showCard();
    }

    public void showNumberPickedCard(int n){
        System.out.println(name+" a pioché(e) "+n+" carte(s)");
   }

   public void showPlayerCanReplay(){
        System.out.println(name+" doit rejouer");
   }

}
