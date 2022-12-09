package fr.pantheonsorbonne.miage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DistributionTest {

    @Test
    public void testSizeCreatePacket() {
        Distribution dist = new Distribution();
        assertEquals(52, dist.getPacket().size());

    }

    @Test
    public void testAllDifferentCards() {
        Distribution dist = new Distribution();
        Set<Card> packetTest = new HashSet<>();
        for (Card i : dist.getPacket()) {
            packetTest.add(i);
        }

        assertEquals(52, packetTest.size());

    }

    @Test
    public void newRandomHandSizeTest() {
        Distribution dist = new Distribution();
        List<Card> hand = dist.newRandomHand();
        assertEquals(8, hand.size());
    }

    @Test
    public void testAllDifferentCardsInHand() {
        Distribution dist = new Distribution();
        List<Card> hand = dist.newRandomHand();
        Set<Card> handTest = new HashSet<Card>();
        for (Card i : hand) {
            handTest.add(i);
        }

        assertEquals(8, hand.size());

    }

    @Test
    public void CardsInHandRemovedInPacket() {
        Distribution dist = new Distribution();

        List<Card> hand = dist.newRandomHand();
        for (Card i : hand) {
            assertFalse(dist.getPacket().contains(i));
        }

        assertEquals(44, dist.getPacket().size());

    }

    @Test
    public void getRandomCardTest() {
        Distribution dist = new Distribution();
        Card card = dist.getRandomCard();
        assertEquals(51, dist.getPacket().size());
        assertFalse(dist.getPacket().contains(card));

        while (!dist.getPacket().isEmpty()) {
            dist.getPacket().remove(0);
        }

        assertThrows(IllegalStateException.class, () -> dist.getRandomCard());

    }

    @Test
    public void firstCardAddedToPlayedCard() {
        Distribution dist = new Distribution();
        dist.distributeFirstCardOnTheTable();

        boolean isSimpleCard = true;

        assertTrue(!dist.getPlayedCard().isEmpty());

        for (int i = 0; i < 50; i++) {
            Distribution dist2 = new Distribution();
            dist2.distributeFirstCardOnTheTable();
            if (dist2.getPlayedCard().get(0).getValeur().equals("AS")
                    || dist2.getPlayedCard().get(0).getValeur().equals("VALET")
                    || dist2.getPlayedCard().get(0).getValeur().equals("DIX")
                    || dist2.getPlayedCard().get(0).getValeur().equals("SEPT")
                    || dist2.getPlayedCard().get(0).getValeur().equals("HUIT")) {
                isSimpleCard = false;
            }
        }

        assertTrue(isSimpleCard);
    }

    @Test
    public void firstCardDeleteFromPacket() {
        Distribution dist = new Distribution();
        dist.distributeFirstCardOnTheTable();
        boolean isDeleteFromPacket = true;
        for (Card i : dist.getPacket()) {
            if (i.getCouleur().equals(dist.getPlayedCard().get(0).getCouleur())
                    && i.getValeur().equals(dist.getPlayedCard().get(0).getValeur())) {
                isDeleteFromPacket = false;
            }
        }

        assertTrue(isDeleteFromPacket);
    }

}
