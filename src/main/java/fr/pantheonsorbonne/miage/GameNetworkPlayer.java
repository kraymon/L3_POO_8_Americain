package fr.pantheonsorbonne.miage;
/*
import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.Card;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

public class GameNetworkPlayer {
    static final String playerId = "Player-" + new Random().nextInt();
    static final Deque<Card> hand = new LinkedList<>();
    static final PlayerFacade playerFacade = Facade.getFacade();
    static Game war;

    public static void main(String[] args) {

        playerFacade.waitReady();
        playerFacade.createNewPlayer(playerId);
        war = playerFacade.autoJoinGame("WAR");
        while (true) {

            GameCommand command = playerFacade.receiveGameCommand(war);
            switch (command.name()) {
                case "cardsForYou":
                    handleCardsForYou(command);
                    break;
                case "playACard":
                    handlePlayACard(command);
                    break;
                case "gameOver":
                    handleGameOverCommand(command);
                    break;

            }
        }
    }

    private static void handleCardsForYou(GameCommand command) {

        

    }

    private static void handlePlayACard(GameCommand command) {
        if (command.params().get("playerId").equals(playerId)) {
            if (!hand.isEmpty()) {
                playerFacade.sendGameCommandToAll(war, new GameCommand("card", hand.pollFirst().toString()));
            } else {
                playerFacade.sendGameCommandToAll(war, new GameCommand("outOfCard", playerId));
            }
        }
    }

    private static void handleGameOverCommand(GameCommand command) {
        if (command.body().equals("win")) {
            System.out.println("I've won!");
        } else {
            System.out.println("I've lost :-(");
        }
        System.exit(0);
    }
}
*/