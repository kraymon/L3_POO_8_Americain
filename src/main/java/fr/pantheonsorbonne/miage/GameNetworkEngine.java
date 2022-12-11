package fr.pantheonsorbonne.miage;
/*
import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.GameEngine;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.*;

public class GameNetworkEngine extends GameEngine {
    private static final int PLAYER_COUNT = 4;

    private final HostFacade hostFacade;
    private final Set<String> players;
    private final Game war;

    public GameNetworkEngine(Distribution deck, HostFacade hostFacade, Set<String> players, fr.pantheonsorbonne.miage.model.Game war) {
        this.hostFacade = hostFacade;
        this.players = players;
        this.war = war;
    }

    public static void main(String[] args) {
        //create the host facade
        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();

        //set the name of the player
        hostFacade.createNewPlayer("Host");

        //create a new game of war
        fr.pantheonsorbonne.miage.model.Game war = hostFacade.createNewGame("WAR");

        //wait for enough players to join
        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);

        GameEngine host = new GameNetworkEngine(new Distribution(), hostFacade, war.getPlayers(), war);
        host.play();
        System.exit(0);


    }


    @Override
    protected Player[] getInitialPlayers() {
        Player[] list = new Player[3];
        return list;
    }


    
    protected void giveCardsToPlayer(String playerName, String hand) {
        hostFacade.sendGameCommandToPlayer(war, playerName, new GameCommand("cardsForYou", hand));
    }


    protected void declareWinner(String winner) {
        hostFacade.sendGameCommandToPlayer(war, winner, new GameCommand("gameOver", "win"));
    }


    
    protected Card getCardOrGameOver(Collection<Card> leftOverCard, String cardProviderPlayer, String cardProviderPlayerOpponent) {

        try {
            return getCardFromPlayer(cardProviderPlayer);
        } catch (Throwable nmc) {
            //contestant A is out of cards
            //we send him a gameover
            hostFacade.sendGameCommandToPlayer(war, cardProviderPlayer, new GameCommand("gameOver"));
            //remove him from the queue so he won't play again
            players.remove(cardProviderPlayer);

            return null;
        }

    }

    
    protected void giveCardsToPlayer(Collection<Card> roundStack, String winner) {
        List<Card> cards = new ArrayList<>();
        cards.addAll(roundStack);
        //shuffle the round deck so we are not stuck
        Collections.shuffle(cards);
        
    }



    protected Card getCardFromPlayer(String player) throws Throwable {
        hostFacade.sendGameCommandToPlayer(war, player, new GameCommand("playACard"));
        GameCommand expectedCard = hostFacade.receiveGameCommand(war);
        
        if (expectedCard.name().equals("outOfCard")) {
            throw new Throwable();
        }
        //should not happen!
        throw new RuntimeException("invalid state");

    }
}
*/