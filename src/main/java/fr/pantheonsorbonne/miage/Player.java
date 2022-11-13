package fr.pantheonsorbonne.miage;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public void pickCard(int number) {
        for (int i = 0; i < number; i++) {
            if (!Distribution.packet.isEmpty()) {
                this.hand.add(Distribution.getRandomCard());
            } else {
                int initialSize = CarteJouer.playedCard.size() - 1;
                for (int j = 0; j < initialSize; j++) {
                    Distribution.packet.add(CarteJouer.playedCard.get(0));
                    CarteJouer.playedCard.remove(0);
                }

                CarteJouer.playedCard.add(Distribution.packet.get(Distribution.packet.size() - 1));
                number++;
            }
        }
    }

    public void asPlayed() {
        for (int i = 0; i < this.hand.size(); i++) {
            if (hand.get(i).getValeur().equals("AS")) {
                As.addAs();
                CarteJouer.playedCard.add(hand.get(i));
                hand.remove(i);
                return;
            }
        }
        As.addAs();
        As.plusTwo();
        As.asPicked = true;
    }

    public void playCard() {
        Card lastPlayedCard = CarteJouer.playedCard.get(CarteJouer.playedCard.size() - 1);
        String lastColor = lastPlayedCard.getCouleur();
        String lastValue = lastPlayedCard.getValeur();

        if (lastValue.equals("HUIT")) {
            lastColor = Eight.lastColorChosen;
        }

        else if (lastValue.equals("SEPT") && !Seven.sevenStopped) {
            Seven.sevenStopped = true;
            return;
        }

        else if (lastValue.equals("AS") && !As.asPicked) {
            asPlayed();
            return;
        }

        int indexEight = -1;

        for (int i = 0; i < this.hand.size(); i++) {

            if ((lastColor.equals(hand.get(i).getCouleur()) || lastValue.equals(hand.get(i).getValeur()))
                    && !hand.get(i).getValeur().equals("HUIT")) {
                CarteJouer.playedCard.add(hand.get(i));
                if (hand.get(i).getValeur().equals("DIX")) {
                    Ten.playAgain = true;
                }
                else if (hand.get(i).getValeur().equals("VALET")){
                    if(Turn.nbPlayer==2){
                        Ten.playAgain = true;
                    }
                    else{
                        Turn.changeRotation();
                    }
                }
                hand.remove(i);
                As.asPicked = false;
                Seven.sevenStopped = false;
                return;
            }
            if (hand.get(i).getValeur().equals("HUIT")) {
                indexEight = i;
            }
        }

        if (indexEight != -1) {
            CarteJouer.playedCard.add(hand.get(indexEight));
            hand.remove(indexEight);
            As.asPicked = false;
            Seven.sevenStopped = false;
            Eight.lastColorChosen = Eight.chooseColor();
            return;
        }

        pickCard(1);

    }

}
