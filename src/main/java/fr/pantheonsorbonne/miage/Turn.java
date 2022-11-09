package fr.pantheonsorbonne.miage;

public class Turn {

    public static int nbPlayer;
    public static Player[] players = new Player[nbPlayer];
    public static int nextPlayer = 0;

    public Turn (int nbPlayer, Player[] players){
        this.nbPlayer = nbPlayer;
        this.players = players;
    }

    public static void nextTurnIndex(){
        nextPlayer++;
        if(nextPlayer>=nbPlayer){
            nextPlayer=0;
        }
    }


}
