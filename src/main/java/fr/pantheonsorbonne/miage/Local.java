package fr.pantheonsorbonne.miage;
import java.util.ArrayList;

public class Local {
    public static void main(String[] args){
        Distribution.createPacket();
        Player malik = new Player("malik", Distribution.newRandomHand());
        Player ken = new Player("ken", Distribution.newRandomHand());

        CarteJouer.firstCard();

        while(!malik.getHand().isEmpty()||!ken.getHand().isEmpty()){
            malik.playCard();
            ken.playCard();
        }

        if(malik.getHand().isEmpty()){
            System.out.println("malik gagnant");
        }
        else{
            System.out.println("ken gagnant");
        }


        /* 
        for(int i=0;i<Distribution.packet.size();i++){
            System.out.println(Distribution.packet.get(i).getValeur()+" "+Distribution.packet.get(i).getCouleur());
        }
        
        ArrayList<Card> main = Distribution.newRandomHand();
        
        for(int i = 0;i<malik.getHand().size();i++){
            System.out.println(malik.getHand().get(i).getValeur()+" "+malik.getHand().get(i).getCouleur());
        }

        malik.pickCard(2);

        System.out.println(Distribution.packet.size());
        */
    }
}
