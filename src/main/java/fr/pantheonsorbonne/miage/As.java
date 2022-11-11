package fr.pantheonsorbonne.miage;

public class As extends Card {

    private static int nbAs = 0;
    public static boolean asPicked = false;

    public As (String valeur,String couleur){
        super(valeur,couleur);
    }

    public static void plusTwo(){
        Turn.players[Turn.nextPlayer].pickCard(nbAs*2);
        nbAs=0;
    }

    public static void addAs(){
        nbAs++;
    }

}
