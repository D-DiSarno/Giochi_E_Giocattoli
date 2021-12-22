package model.Carrello;

import model.Prodotto.Prodotto;

public class CarrelloElementi {
    private final Prodotto prodotto;//final perchè solo lettura no riassegno
    private int quantita;
    public CarrelloElementi(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }
    public void setQuantita(int quantita){ this.quantita=quantita;}
    public int getQuantita() {
        return quantita;
    }
    public double totale(){
        return prodotto.getPrezzo()*quantita;
    }
}
