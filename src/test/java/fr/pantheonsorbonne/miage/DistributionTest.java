package fr.pantheonsorbonne.miage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testCreatePacket()
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

}
