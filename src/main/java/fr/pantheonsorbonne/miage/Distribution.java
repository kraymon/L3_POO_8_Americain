package fr.pantheonsorbonne.cri;
import java.util.Random;
import java.util.ArrayList;

public class Distribution {

    static String[] couleur = {"COEUR","CARREAU","PIQUE","TREFLE"};
    static String[] valeur = {"AS","ROI","DAME","VALET","DIX","NEUF","HUIT","SEPT","SIX","CINQ","QUATRE","TROIS","DEUX"};
    static ArrayList<String> repetition=new ArrayList<String>() ;

    ArrayList<Card> packet = new ArrayList<Card>();
    for(int i =0;i<valeur.length;i++){
        for(int j=0;i<couleur.length;j++){
            Card carte = new Card(valeur[i],couleur[j]);
        }
    }
    
    public static ArrayList<Card> newRandomHand(){
        Random random = new Random();
        ArrayList<Card> hand = new ArrayList<Card>();

        for(int i =0; i<8; i++){
            int indexCouleur = random.nextInt(4);
            int indexValeur = random.nextInt(13);
            
             
            while(repetition.contains(valeur[indexValeur]+couleur[indexCouleur])){
                indexCouleur = random.nextInt(4);
                indexValeur = random.nextInt(13);
            }
            
            Card carte = new Card(valeur[indexValeur],couleur[indexCouleur]);
            hand.add(carte);
            repetition.add(valeur[indexValeur]+couleur[indexCouleur]);
            }
       
        return hand;
    }

    
    public static Card getRandomCard(){
        Random random = new Random();

        int indexCouleur = random.nextInt(4);
        int indexValeur = random.nextInt(13);

        while(repetition.contains(valeur[indexValeur]+couleur[indexCouleur])){
            indexCouleur = random.nextInt(4);
            indexValeur = random.nextInt(13);
        }

        Card newCarte = new Card(valeur[indexValeur],couleur[indexCouleur]);
        hand[i] = newCarte;
        repetition.add(valeur[indexValeur]+couleur[indexCouleur]);
              
        }

        return hand;
    
    }
}
        





