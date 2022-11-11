package fr.pantheonsorbonne.miage;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }

    public void pickCard(int number) {
        for (int i = 0; i < number; i++) {
            if(!Distribution.packet.isEmpty()){
            this.hand.add(Distribution.getRandomCard());
            }
            else{
                int initialSize = CarteJouer.playedCard.size()-1;
                for(int j = 0;j<initialSize;j++){
                    Distribution.packet.add(CarteJouer.playedCard.get(0));
                    CarteJouer.playedCard.remove(0);
            }
                
                CarteJouer.playedCard.add(Distribution.packet.get(Distribution.packet.size()-1));
                number++;
            }
        }
    }

    public void playCard(){
        Card lastPlayedCard = CarteJouer.playedCard.get(CarteJouer.playedCard.size()-1);
        String lastColor = lastPlayedCard.getCouleur();
        String lastValue = lastPlayedCard.getValeur();

        if(lastValue.equals("AS") && As.asPicked==false){
            for(int i =0;i<this.hand.size();i++ ){
                if(hand.get(i).getValeur().equals("AS")){
                    As.addAs();
                    CarteJouer.playedCard.add(hand.get(i));
                    hand.remove(i);
                    return;
                }  
            }
            As.plusTwo();
            As.asPicked=true;
            return;
        }

        for(int i =0;i<this.hand.size();i++ ){
            if(lastColor.equals(hand.get(i).getCouleur())||lastValue.equals(hand.get(i).getValeur())||hand.get(i).getValeur().equals("HUIT")){
                CarteJouer.playedCard.add(hand.get(i));
                hand.remove(i);
                As.asPicked=false;
                return;
            }
        }


        pickCard(1);

    }

}
