package fr.pantheonsorbonne.miage;

//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class DistributionTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSizeCreatePacket()
    {
        Distribution.createPacket();
        assertEquals(52,Distribution.packet.size());
        
    }
    @Test
    public void testAllDifferentCards()
    {
        Distribution.createPacket();
        Set<Card> packetTest = new HashSet<Card>();
        for(Card i : Distribution.packet){
            packetTest.add(i);
        }

        assertEquals(52,packetTest.size());
        
    }
    @Test
    public void newRandomHandSizeTest()
    {
        Distribution.createPacket();
        List<Card> hand = Distribution.newRandomHand();
        assertEquals(8,hand.size());
    }
    @Test
    public void testAllDifferentCardsInHand()
    {
        Distribution.createPacket();
        List<Card> hand = Distribution.newRandomHand();
        Set<Card> handTest = new HashSet<Card>();
        for(Card i : hand){
            handTest.add(i);
        }

        assertEquals(8,hand.size());
        
    }

    @Test
    public void CardsInHandRemovedInPacket()
    {
        Distribution.createPacket();
        List<Card> hand = Distribution.newRandomHand();
        for(Card i : hand){
            assertFalse(Distribution.packet.contains(i));
        }

        assertEquals(44,Distribution.packet.size());
        
    }

    @Test
    public void getRandomCardTest()
    {
        Distribution.createPacket();
        Card card = Distribution.getRandomCard();
        assertEquals(51,Distribution.packet.size());
        assertFalse(Distribution.packet.contains(card));
    }

}
