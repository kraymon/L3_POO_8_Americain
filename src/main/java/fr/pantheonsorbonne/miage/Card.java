package fr.pantheonsorbonne.miage;

public class Card {

    private String couleur;
    private String valeur;

    protected Card(String valeur, String couleur) {
        this.valeur = valeur;
        this.couleur = couleur;
    }

    public String getValeur() {
        return this.valeur;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public void showCard() {
        System.out.println(this.getValeur() + " " + this.getCouleur());
    }

}
