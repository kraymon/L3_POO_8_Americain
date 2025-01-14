package fr.pantheonsorbonne.miage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GameEngineTest {

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
        int taille = 52 - (8 * local.getInitialPlayers().length);
        assertEquals(taille, local.dist.getPacket().size());
        Player player = local.getInitialPlayers()[0];
        local.pickCard(2, player);
        assertEquals(10, player.getHand().size());
        boolean isDeleteFromPacket = true;
        for (Card c : player.getHand()) {
            for (Card c2 : local.dist.getPacket()) {
                if (c.getCouleur().equals(c2.getCouleur())
                        && c.getValeur().equals(c2.getValeur())) {
                    isDeleteFromPacket = false;
                }
            }
        }
        assertTrue(isDeleteFromPacket);
        assertEquals(taille - 2, local.dist.getPacket().size());
        local.pickCard(taille - 2, player);
        assertEquals(0, local.dist.getPlayedCard().size());

    }

    @Test
    public void testPickCardWhenPacketIsEmpty() {
        Local local = new Local();
        while (!local.dist.getPacket().isEmpty()) {
            local.dist.getPlayedCard().add(local.dist.getPacket().get(0));
            local.dist.getPacket().remove(0);
        }
        assertEquals(0, local.dist.getPacket().size());
        local.pickCard(1, local.getInitialPlayers()[0]);
        assertEquals(50 - (8 * local.getInitialPlayers().length), local.dist.getPacket().size());
        assertEquals(9, local.getInitialPlayers()[0].getHand().size());
    }

    @Test
    public void testChooseColor() {
        Local local = new Local();
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
        Player p1 = local.getInitialPlayers()[0];
        local.setNbAs(1);
        local.plusTwo(local.getInitialPlayers()[0]);
        assertEquals(10, p1.getHand().size());
        if (local.getInitialPlayers().length < 6) {
            local.setNbAs(2);
            local.plusTwo(p1);
            assertEquals(14, p1.getHand().size());
        }
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

    @Test
    public void testAsPlayed() {
        Local local = new Local();
        ArrayList<Card> hand = new ArrayList<>();
        Card carte1 = new Card("DEUX", "COEUR");
        hand.add(carte1);
        Player p = new Player("test", hand);
        local.asPlayed(p);
        assertEquals(3, p.getHand().size());
        assertTrue(local.getasPicked());
        assertEquals(0, local.getNbAs());
        Card carte2 = new Card("AS", "COEUR");
        hand.add(carte2);
        local.asPlayed(p);
        assertEquals(1, local.getNbAs());
        assertEquals(3, p.getHand().size());
        assertEquals(1, local.dist.getPlayedCard().size());
    }

    @Test
    public void testPlayEight() {

        Local local = new Local();

        ArrayList<Card> hand = new ArrayList<>();
        Card carte1 = new Card("HUIT", "COEUR");
        hand.add(carte1);
        Player p = new Player("test", hand);
        local.playEight(0, p);
        assertEquals(0, p.getHand().size());
        assertEquals(1, local.dist.getPlayedCard().size());
    }

    @Test
    public void testPlayCombination() {
        Local local = new Local();

        ArrayList<Card> hand = new ArrayList<>();
        Card carte1 = new Card("CINQ", "COEUR");
        hand.add(carte1);
        Card carte2 = new Card("CINQ", "PIQUE");
        hand.add(carte2);
        Player p = new Player("test", hand);
        local.playCombination("CINQ", p);
        assertEquals(0, p.getHand().size());
        assertEquals(2, local.dist.getPlayedCard().size());

        Card carte3 = new Card("VALET", "PIQUE");
        hand.add(carte3);
        Card carte4 = new Card("VALET", "COEUR");
        hand.add(carte4);

        local.playCombination("VALET", p);
        if (local.getInitialPlayers().length != 2) {
            assertTrue(local.getClockwise());
        }

    }

    @Test
    public void testverifyIfTenOrJack() {
        Local local = new Local();
        local.verifyIfTenOrJack("DIX");
        assertTrue(local.getPlayAgain());
        local.verifyIfTenOrJack("VALET");
        if (local.getInitialPlayers().length == 2) {
            assertTrue(local.getClockwise());
        } else {
            assertFalse(local.getClockwise());
        }
    }

    @Test
    public void testPlayCard() {
        Local local = new Local();
        ArrayList<Card> hand = new ArrayList<>();
        Card carte1 = new Card("QUATRE", "COEUR");
        local.dist.getPlayedCard().add(carte1);
        Card carte2 = new Card("QUATRE", "PIQUE");
        hand.add(carte2);
        Player p = new Player("test", hand);
        local.playCard(p);
        Card carte3 = new Card("SIX", "PIQUE");
        hand.add(carte3);
        local.playCard(p);
        assertEquals(0, p.getHand().size());
        assertEquals(3, local.dist.getPlayedCard().size());
    }
}
