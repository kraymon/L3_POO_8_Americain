package fr.pantheonsorbonne.miage;

public abstract class LocalEngine {

    private  int nbAs = 0;
    private boolean asPicked = false;
    private boolean sevenStopped = false;
    private boolean playAgain = false;
    private String lastColorChosen = "";
    private int nextPlayer = 0;
    private int rotation = 0;
    Distribution dist = new Distribution();

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
            if (rotation == 0) {
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
        rotation++;
        if (rotation > 1) {
            rotation = 0;
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

                dist.getPlayedCard().add(dist.getPacket().get(dist.getPacket().size() - 1));
                number++;
            }
        }
    }

    public  String chooseColor(){
        String[] tabColor ={"PIQUE", "TREFLE", "COEUR", "CARREAU"};
        int max = 0;
        String maxColor = "";

        for(int j=0;j<4;j++){
            int count=0;
            for(int i=0; i<getInitialPlayers()[nextPlayer].getHand().size();i++){
                if(getInitialPlayers()[nextPlayer].getHand().get(i).getCouleur().equals(tabColor[j])){
                    count++;
                }
            }
            if(count>max){
                max=count;
                maxColor= tabColor[j];
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
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i).getValeur().equals("AS")) {
                addAs();
                dist.getPlayedCard().add(player.getHand().get(i));
                player.getHand().remove(i);
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
                player.getHand().remove(j);
            }
        }
    }
    private void makePlayAgainOrChangeRotation(String valeur){
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
                makePlayAgainOrChangeRotation(card.getValeur());
                String toCombinate = card.getValeur();
                player.getHand().remove(card);
                // combination of cards

                if (!toCombinate.equals("AS")) {
                    playCombination(toCombinate, player);
                }
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
