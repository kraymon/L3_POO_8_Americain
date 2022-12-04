package fr.pantheonsorbonne.miage;


public class Local extends LocalEngine{

    private Player[] players = new Player[3];
    
    public Local(){
        Player malik = new Player("malik", dist.newRandomHand());
        Player ken = new Player("ken", dist.newRandomHand());
        Player herbaut = new Player("herbaut", dist.newRandomHand());
        
        players[0] =malik;
        players[1] =ken;
        players[2] =herbaut;
    }

    public static void main(String[] args) {
        Local game = new Local();
        game.play();
    }

    @Override
    protected Player[] getInitialPlayers(){
        return this.players;
    }
}
