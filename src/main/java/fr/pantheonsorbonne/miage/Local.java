package fr.pantheonsorbonne.miage;
import java.util.ArrayList;

public class Local {
    public static void main(String[] args){
        Distribution.createPacket();
        Player malik = new Player("malik", Distribution.newRandomHand());
        /* 
        for(int i=0;i<Distribution.packet.size();i++){
            System.out.println(Distribution.packet.get(i).getValeur()+" "+Distribution.packet.get(i).getCouleur());
        }
        
        ArrayList<Card> main = Distribution.newRandomHand();
        */
        for(int i = 0;i<malik.getHand().size();i++){
            System.out.println(malik.getHand().get(i).getValeur()+" "+malik.getHand().get(i).getCouleur());
        }

        malik.pickCard(2);

        System.out.println(Distribution.packet.size());
        
    }
}
