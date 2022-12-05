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
        if (local.getInitialPlayers().length==3){
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
            local.pickCard(18, player);
            assertEquals(0, local.dist.getPlayedCard().size());
            //local.pickCard(1, player);

        }

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

    public void testAsPlayed(){
        Local local = new Local();
        int nbAsInHand = 0;
        for(Card carte : local.getInitialPlayers()[0].getHand()){
            if(carte.getValeur().equals("AS")){
                nbAsInHand++;
            }
        }
        local.asPlayed(local.getInitialPlayers()[0]);
        assertEquals(nbAsInHand,local.getNbAs());
        if(nbAsInHand==0){
            assertTrue(local.getasPicked());
        }
        else{
            assertFalse(local.getasPicked());
        }
    }
    @Test
    /*
     * test si le huit est bien remove de la main
     * test si le huit est bien add aux cartes jouées
     */
    public void testPlayEight(){
        Local local = new Local();
        int countEight = 0;
        int indexHandWithEight =0;
        for(int i=0; i<local.getInitialPlayers().length;i++){
            for(Card card : local.getInitialPlayers()[i].getHand()){
                if(card.getValeur().equals("HUIT")){
                    countEight++;
                    indexHandWithEight=i;
                }
            }
        }
        List<Card> test = new ArrayList<>();
        Player player = new Player("test", test);
        if(countEight<4){
            Card carte = local.dist.getRandomCard();
            String valeur=carte.getValeur();
            while(valeur!="HUIT"){
                carte = local.dist.getRandomCard();
                valeur=carte.getValeur();
            }
            local.getInitialPlayers()[0].getHand().add(carte);
            player = local.getInitialPlayers()[0];
        }
        else{
            player = local.getInitialPlayers()[indexHandWithEight-1];
        }
        int indexEight = 0;
        for(int i =0;i<player.getHand().size();i++){
            if(player.getHand().get(i).getValeur().equals("HUIT")){
                indexEight = i;
            }
        }
        Card carte = player.getHand().get(indexEight);
        local.playEight(indexEight, player);
        assertTrue(local.dist.getPlayedCard().contains(carte));
        assertFalse(local.getInitialPlayers()[0].getHand().contains(carte));
    }
    @Test
    public void testPlayCombination(){
        Local local = new Local();
        int countValet = 0;
        int indexHandWithValet =0;
        for(Player player : local.getInitialPlayers()){
            indexHandWithValet++;
            for(Card card : player.getHand()){
                if(card.getValeur().equals("VALET")){
                    countValet++;
                }
            }
        }
        List<Card> test = new ArrayList<>();
        Player player = new Player("test", test);
        if(countValet<4){
            Card carte = local.dist.getRandomCard();
            String valeur=carte.getValeur();
            while(valeur!="VALET"){
                carte = local.dist.getRandomCard();
                valeur=carte.getValeur();
            }
            local.getInitialPlayers()[0].getHand().add(carte);
            player = local.getInitialPlayers()[0];
        }
        else{
            player = local.getInitialPlayers()[indexHandWithValet-1];
        }
        countValet = 0;
        for(Card card : player.getHand()){
            if(card.getValeur().equals("VALET")){
                countValet++;
            }
        }
        local.playCombination("VALET",player);
       boolean isValetDeleteFromHand = true;
       for(Card card : player.getHand()){
        if(card.getValeur().equals("VALET")){
            isValetDeleteFromHand=false;
            break;
        }
        assertTrue(isValetDeleteFromHand);
        //assertEquals(countValet,local.dist.getPlayedCard().size());
       }
    }
    @Test
    /*
     * si  10 => playagain true
     * si Valet playagain et nombre de joueur >2 => changeRotation
     * manque le test avec une partie de 2 joueur et
     */
    public void testverifyIfTenOrJack(){
        Local local = new Local();
        local.verifyIfTenOrJack("DIX");
        assertTrue(local.getPlayAgain());
        local.verifyIfTenOrJack("VALET");
        assertFalse(local.getClockwise());
        local.setPlayAgain();
    }
}
