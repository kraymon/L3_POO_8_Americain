package fr.pantheonsorbonne.miage;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LocalEngineTest {

    @Test
    public void testPlay() {
        Local local = new Local();
        local.play();
        assertEquals(0, local.getInitialPlayers()[local.getNextPlayer()].getHand().size());
    }
    
    @Test
    public void testChangeRotation() {
        Local local = new Local();
        boolean clockwise = local.getClockwise();
        assertTrue(clockwise);
        local.changeRotation();
        clockwise = local.getClockwise();
        assertTrue(!clockwise);
        local.changeRotation();
        clockwise = local.getClockwise();
        assertTrue(clockwise);
    }

    @Test
    public void testNextTurnIndex() {
        Local local = new Local();
        assertEquals(0, local.getNextPlayer());
        local.nextTurnIndex();
        assertEquals(1, local.getNextPlayer());
        local.changeRotation();
        local.nextTurnIndex();
        assertEquals(0, local.getNextPlayer());
        local.nextTurnIndex();
        assertEquals(local.getInitialPlayers().length - 1, local.getNextPlayer());
        local.changeRotation();
        local.nextTurnIndex();
        assertEquals(0, local.getNextPlayer());
        local.setPlayAgain();
        local.nextTurnIndex();
        assertEquals(0, local.getNextPlayer());
    }

    @Test
    public void testPickCard() {
        Local local = new Local();
        assertEquals(28, local.dist.getPacket().size());
        Player player = new Player("player",local.dist.newRandomHand());
        local.pickCard(2, player);
        assertEquals(10, player.getHand().size());
        boolean isDeleteFromPacket = true;
        for (Card c : player.getHand()){
            for (Card c2 : local.dist.getPacket()){
                if (c.getCouleur().equals(c2.getCouleur())
                    && c.getValeur().equals(c2.getValeur())) {
                isDeleteFromPacket = false;
            }
            }
        }
        assertTrue(isDeleteFromPacket);
        assertEquals(18, local.dist.getPacket().size());
    }

    @Test
    public void testChooseColor() {
        LocalEngine local = new Local();
        ArrayList<Card> hand = new ArrayList<>();
        Card carte1 = new Card("DEUX", "COEUR");
        Card carte2 = new Card("TROIS", "PIQUE");
        Card carte3 = new Card("CINQ", "COEUR");
        hand.add(carte1);
        hand.add(carte2);
        hand.add(carte3);
        Player p1 = new Player("test", hand);
        assertEquals("COEUR", local.chooseColor(p1));
    }

    @Test
    public void testPlusTwo() {
        Local local = new Local();
        Distribution dist = new Distribution();
        Player p1 = new Player("test", dist.newRandomHand());
        local.setNbAs(1);
        local.plusTwo(p1);
        assertEquals(10, p1.getHand().size());
        local.setNbAs(2);
        local.plusTwo(p1);
        assertEquals(14, p1.getHand().size());
    }

    @Test
    public void testAddAs() {
        Local local = new Local();
        assertEquals(0, local.getNbAs());
        local.addAs();
        assertEquals(1, local.getNbAs());
    }

    @Test
    public void testGetName() {
        Distribution dist = new Distribution();
        Player p1 = new Player("test", dist.newRandomHand());
        assertEquals("test", p1.getName());
    }
}
