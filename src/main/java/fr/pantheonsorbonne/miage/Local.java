package fr.pantheonsorbonne.miage;

public class Local {
    public static void main(String[] args){
        Distribution.createPacket();

        for(int i=0;i<Distribution.packet.size();i++){
            System.out.println(Distribution.packet.get(i).getValeur()+" "+Distribution.packet.get(i).getCouleur());
        }
        
    }
}
