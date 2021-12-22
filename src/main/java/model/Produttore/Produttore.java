package model.Produttore;


import model.Prodotto.Prodotto;


import java.util.List;

public class Produttore  {
    private String idProduttore;
    private String nome;
    private String email;
    private List<Prodotto> prodotti;

    public String getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(String idProduttore) {
        this.idProduttore = idProduttore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public Produttore() {
    }

}
