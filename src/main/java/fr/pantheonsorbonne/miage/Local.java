package fr.pantheonsorbonne.miage;

import java.util.ArrayList;

public class Local {
    public static void main(String[] args) {
        Distribution.createPacket();
        Player malik = new Player("malik", Distribution.newRandomHand());
        Player ken = new Player("ken", Distribution.newRandomHand());
        Player herbaut = new Player("herbaut", Distribution.newRandomHand());

        Player[] players = { malik, ken, herbaut };
        Turn tour = new Turn(3, players);

        CarteJouer.firstCard();

        while (true) {

            System.out.println(
                    "Carte jouée : " + CarteJouer.playedCard.get(CarteJouer.playedCard.size() - 1).getValeur() + " "
                            + CarteJouer.playedCard.get(CarteJouer.playedCard.size() - 1).getCouleur());
            System.out.println();

            System.out.println("main de " + players[Turn.nextPlayer].getName());
            for (int i = 0; i < players[Turn.nextPlayer].getHand().size(); i++) {
                System.out.println(players[Turn.nextPlayer].getHand().get(i).getValeur() + " "
                        + players[Turn.nextPlayer].getHand().get(i).getCouleur());
            }

            System.out.println();

            players[Turn.nextPlayer].playCard();
            if (players[Turn.nextPlayer].getHand().isEmpty()) {
                System.out.println(players[Turn.nextPlayer].getName() + " a gagné");
                break;
            }
            Turn.nextTurnIndex();

            /*
             * for(int i = 0;i<Distribution.packet.size();i++){
             * System.out.println(Distribution.packet.get(i).getValeur()+" "+Distribution.
             * packet.get(i).getCouleur());
             * }
             * 
             * System.out.println();
             */

        }

    }
}
