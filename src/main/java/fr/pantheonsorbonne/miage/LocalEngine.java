package fr.pantheonsorbonne.miage;

public abstract class LocalEngine {

    private  int nbAs = 0;
    private boolean asPicked = false;
    private boolean sevenStopped = false;
    private boolean playAgain = false;
    private String lastColorChosen = "";
    private int nextPlayer = 0;
    private boolean clockwise  = true;
    Distribution dist = new Distribution();

    public boolean getClockwise(){
        return clockwise;
    }
    public boolean getasPicked(){
        return asPicked;
    }
    public boolean getSevenStopped(){
        return sevenStopped;
    }
    public int getNbAs(){
        return nbAs;
    }
    public boolean getPlayAgain(){
        return playAgain;
    }
    public int getNextPlayer(){
        return nextPlayer;
    }
    public String getLastColorChosen(){
        return lastColorChosen;
    }

    protected abstract Player[] getInitialPlayers();

    protected void play(){

        dist.distributeFirstCardOnTheTable();

        while (true) {

            System.out.println(
                    "Carte jouée : " + dist.getPlayedCard().get(dist.getPlayedCard().size() - 1).getValeur() + " "
                            + dist.getPlayedCard().get(dist.getPlayedCard().size() - 1).getCouleur());
            System.out.println();

            getInitialPlayers()[nextPlayer].showHand();

            playCard(getInitialPlayers()[nextPlayer]);

            if (getInitialPlayers()[nextPlayer].getHand().isEmpty()) {
                System.out.println(getInitialPlayers()[nextPlayer].getName() + " a gagné");
                break;
            }
           nextTurnIndex();

        }
    }

    public  void nextTurnIndex() {
        if (!playAgain) {
            if (clockwise) {
                nextPlayer++;
                if (nextPlayer >= getInitialPlayers().length) {
                    nextPlayer = 0;
                }
            } else {
                nextPlayer--;
                if (nextPlayer < 0) {
                    nextPlayer = getInitialPlayers().length - 1;
                }
            }
        } else {
            playAgain = false;
        }
    }

    public  void changeRotation() {
        if(clockwise){
            clockwise = false;
        }
        else{
            clockwise = true;
        }
    }

    public void pickCard(int number, Player player) {
        for (int i = 0; i < number; i++) {
            if (!dist.getPacket().isEmpty()) {
                player.getHand().add(dist.getRandomCard());
            } else {
                int initialSize = dist.getPlayedCard().size() - 1;
                for (int j = 0; j < initialSize; j++) {
                    dist.getPacket().add(dist.getPlayedCard().poll());
                }

                dist.getPlayedCard().add(dist.getPacket().pollLast());
                number++;
            }
        }
    }

    public  String chooseColor(){
        String[] tabColor ={"PIQUE", "TREFLE", "COEUR", "CARREAU"};
        int max = 0;
        String maxColor = "";

        for(String color : tabColor){
            int count=0;
            for(Card card : getInitialPlayers()[nextPlayer].getHand()){
                if(card.getCouleur().equals(color)){
                    count++;
                }
            }
            if(count>max){
                max=count;
                maxColor= color;
            }
        }
        return maxColor;
    }

    public void plusTwo(Player player){
        pickCard(nbAs*2, player);
        nbAs=0;
    }

    public  void addAs(){
        nbAs++;
    }

    private void asPlayed(Player player) {
        for (Card card : player.getHand()) {
            if (card.getValeur().equals("AS")) {
                addAs();
                dist.getPlayedCard().add(card);
                player.getHand().remove(card);
                return;
            }
        }
        addAs();
        plusTwo(player);
        asPicked = true;
    }

    private void playEight(int indexEight, Player player){
        dist.getPlayedCard().add(player.getHand().get(indexEight));
        player.getHand().remove(indexEight);
        asPicked = false;
        sevenStopped = false;
        lastColorChosen = chooseColor();
    }

    private void playCombination(String toCombinate, Player player){
        for (int j = 0; j < player.getHand().size(); j++) {
            if (toCombinate.equals(player.getHand().get(j).getValeur())) {
                dist.getPlayedCard().add(player.getHand().get(j));
                if (player.getHand().get(j).getValeur().equals("VALET") && getInitialPlayers().length != 2) {
                    changeRotation();
                }
                player.getHand().remove(player.getHand().get(j));
            }
        }
    }
    private void verifyIfTenOrJack(String valeur){
        if (valeur.equals("DIX")) {
            playAgain = true;
        } else if (valeur.equals("VALET")) {
            if (getInitialPlayers().length == 2) {
                playAgain = true;
            } else {
                changeRotation();
            }
        }
    }

    private void verifyIfPlayerCanCombinate(String toCombinate, Player player){
        if (!toCombinate.equals("AS")) {
            playCombination(toCombinate, player);
        }
    }
    public void playCard(Player player) {
        Card lastPlayedCard = dist.getPlayedCard().get(dist.getPlayedCard().size() - 1);
        String lastColor = lastPlayedCard.getCouleur();
        String lastValue = lastPlayedCard.getValeur();

        if (lastValue.equals("HUIT")) {
            lastColor = lastColorChosen;
        }

        else if (lastValue.equals("SEPT") && !sevenStopped) {
            sevenStopped = true;
            return;
        }

        else if (lastValue.equals("AS") && !asPicked) {
            asPlayed(player);
            return;
        }

        int indexEight = -1;

        for (Card card : player.getHand()) {

            if ((lastColor.equals(card.getCouleur()) || lastValue.equals(card.getValeur()))
                    && !card.getValeur().equals("HUIT")) {
                dist.getPlayedCard().add(card);
                verifyIfTenOrJack(card.getValeur());
                String toCombinate = card.getValeur();
                player.getHand().remove(card);
                // combination of cards
                verifyIfPlayerCanCombinate(toCombinate, player);
                asPicked = false;
                sevenStopped = false;
                return;
            }
            if (card.getValeur().equals("HUIT")) {
                indexEight = player.getHand().indexOf(card);
            }
        }

        if (indexEight != -1) {
            playEight(indexEight, player);
            return;
        }

        pickCard(1, player);
    }
}
