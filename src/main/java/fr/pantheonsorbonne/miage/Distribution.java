package fr.pantheonsorbonne.miage;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

public class Distribution {

    static String[] couleur = {"COEUR","CARREAU","PIQUE","TREFLE"};
    static String[] valeur = {"AS","ROI","DAME","VALET","DIX","NEUF","HUIT","SEPT","SIX","CINQ","QUATRE","TROIS","DEUX"};
    static LinkedList<Card> packet = new LinkedList<Card>();
    

    public static void createPacket(){
        for(int i =0;i<valeur.length;i++){
            for(int j=0;i<couleur.length;j++){
                Card carte = new Card(valeur[i],couleur[j]);
                packet.add(carte);
            }
        }
    }

    public ArrayList<Card> newRandomHand(){
        Random random = new Random();
        ArrayList<Card> hand = new ArrayList<Card>();

        for(int i =0; i<8; i++){
            int randomIndex = random.nextInt(52);
            hand.add(packet.get(randomIndex));
            packet.remove(randomIndex);
        }
       
        return hand;
    }

    
    public static Card getRandomCard(){
        Random random = new Random();

        int randomIndex = random.nextInt(packet.size());
        Card carte = packet.get(randomIndex);
        packet.remove(randomIndex);
        
        return carte;
    
    }
}
        





