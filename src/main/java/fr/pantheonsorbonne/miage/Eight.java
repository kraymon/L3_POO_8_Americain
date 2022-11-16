package fr.pantheonsorbonne.miage;

public class Eight extends Card {

    public static String lastColorChosen = "";
    
    public Eight (String valeur,String couleur){
        super(valeur,couleur);
    }

    public static String chooseColor(){
        String[] tabColor ={"PIQUE", "TREFLE", "COEUR", "CARREAU"};
        int max = 0;
        String maxColor = "";

        for(int j=0;j<4;j++){
            int count=0;
            for(int i=0; i<Turn.players[Turn.nextPlayer].getHand().size();i++){
                if(Turn.players[Turn.nextPlayer].getHand().get(i).getCouleur().equals(tabColor[j])){
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
}
