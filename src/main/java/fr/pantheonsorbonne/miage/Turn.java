package fr.pantheonsorbonne.miage;

public class Turn {

    public static int nbPlayer;
    public static Player[] players = new Player[nbPlayer];
    public static int nextPlayer = 0;
    public static int rotation = 0;

    public Turn(int nbPlayer, Player[] players) {
        this.nbPlayer = nbPlayer;
        this.players = players;
    }

    public static void nextTurnIndex() {
        if (!Ten.playAgain) {
            if (rotation == 0) {
                nextPlayer++;
                if (nextPlayer >= nbPlayer) {
                    nextPlayer = 0;
                }
            } else {
                nextPlayer--;
                if (nextPlayer < 0) {
                    nextPlayer = nbPlayer - 1;
                }
            }
        } else {
            Ten.playAgain = false;
        }
    }

    public static void changeRotation() {
        rotation++;
        if (rotation > 1) {
            rotation = 0;
        }
    }

}
