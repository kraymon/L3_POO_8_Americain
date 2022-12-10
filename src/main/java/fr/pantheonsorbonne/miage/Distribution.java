package fr.pantheonsorbonne.miage;

import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Cette classe crée le deck, gère la distribution de cartes et les cartes
 * jouées.
 */
public class Distribution {

    private static final String[] COULEUR = { "COEUR", "CARREAU", "PIQUE", "TREFLE" };
    private static final String[] VALEUR = { "AS", "ROI", "DAME", "VALET", "DIX", "NEUF", "HUIT", "SEPT", "SIX", "CINQ",
            "QUATRE",
            "TROIS", "DEUX" };

    private List<Card> packet = new LinkedList<>();
    private List<Card> playedCard = new LinkedList<>();
    private static Random random = new Random();

    public List<Card> getPacket() {
        return packet;
    }

    public List<Card> getPlayedCard() {
        return playedCard;
    }

    public Distribution() {
        this.createPacket();
    }

    /**
     * Cette méthode crée un deck de 52 cartes à partir des 10 valeurs et 4 couleurs
     */
    private void createPacket() {
        for (String valeur : VALEUR) {
            for (String couleur : COULEUR) {
                Card carte = new Card(valeur, couleur);
                packet.add(carte);
            }
        }
    }

    /**
     * Cette méthode crée une nouvelle main de 8 cartes tirées aléatoirement dans le
     * deck
     * 
     * @return la main crée
     */
    public List<Card> newRandomHand() {
        List<Card> hand = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(packet.size());
            hand.add(packet.get(randomIndex));
            packet.remove(packet.get(randomIndex));
        }

        return hand;
    }

    /**
     * Cette méthode tire une seule carte aléatoirement dans le deck
     * <p>
     * si le deck est vide la méthode lance une exception
     * 
     * @return la carte
     * @throw IllegalStateException si le deck est vide
     */
    public Card getRandomCard() {
        if (getPacket().isEmpty()) {
            throw new IllegalStateException("Packet cannot be empty !");
        }
        int randomIndex = random.nextInt(packet.size());
        Card carte = this.packet.get(randomIndex);
        this.packet.remove(randomIndex);

        return carte;

    }

    /**
     * Cette méthode ajoute la première carte jouée non spéciale aléatoirement à
     * partir du deck
     */
    public void distributeFirstCardOnTheTable() {
        Card carte;
        int randomIndex;
        do {
            randomIndex = random.nextInt(packet.size());
            carte = packet.get(randomIndex);
        } while (carte.getValeur().equals("AS") || carte.getValeur().equals("VALET") || carte.getValeur().equals("DIX")
                || carte.getValeur().equals("SEPT") || carte.getValeur().equals("HUIT"));
        playedCard.add(carte);
        showFirstCard(carte);
        packet.remove(randomIndex);
    }

    /**
     * Cette méthode affiche la première carte jouée
     * 
     * @param carte a afficher
     */
    private void showFirstCard(Card carte) {
        System.out.print("Première Carte : ");
        carte.showCard();
    }
}
