package fr.pantheonsorbonne.miage;

import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Distribution {

    private static final String[] COULEUR = { "COEUR", "CARREAU", "PIQUE", "TREFLE" };
    private static final String[] VALEUR = { "AS", "ROI", "DAME", "VALET", "DIX", "NEUF", "HUIT", "SEPT", "SIX", "CINQ",
            "QUATRE",
            "TROIS", "DEUX" };

    public List<Card> getPacket() {
        return packet;
    }

    public void setPacket(List<Card> packet) {
        this.packet = packet;
    }

    public List<Card> getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(List<Card> playedCard) {
        this.playedCard = playedCard;
    }

    private List<Card> packet = new LinkedList<>();
    private List<Card> playedCard = new LinkedList<>();
    private static Random random = new Random();

    public Distribution() {
        this.createPacket();
    }

    private void createPacket() {
        for (String valeur : VALEUR) {
            for (String couleur : COULEUR) {
                Card carte = new Card(valeur, couleur);
                packet.add(carte);
            }
        }
    }

    public List<Card> newRandomHand() {
        ArrayList<Card> hand = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(packet.size());
            hand.add(packet.get(randomIndex));
            packet.remove(packet.get(randomIndex));
        }

        return hand;
    }

    public Card getRandomCard() {
        if(getPacket().isEmpty()){
            throw new IllegalStateException("Packet cannot be empty !");
        }
        int randomIndex = random.nextInt(packet.size());
        Card carte = this.packet.get(randomIndex);
        this.packet.remove(randomIndex);

        return carte;

    }

    public void distributeFirstCardOnTheTable() {
        Card carte;
        int randomIndex;
        do {
            randomIndex = random.nextInt(packet.size());
            carte = packet.get(randomIndex);
        } while (carte.getValeur().equals("AS") || carte.getValeur().equals("VALET") || carte.getValeur().equals("DIX")
                || carte.getValeur().equals("SEPT") || carte.getValeur().equals("HUIT"));
        playedCard.add(carte);
        packet.remove(randomIndex);
    }
}
