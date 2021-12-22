package model.Ordine;

import model.Carrello.Carrello;
import model.Cliente.Cliente;


import java.time.LocalDate;


public class Ordine {
    private int numeroOrdine;
    private String via;
    private String codice_postale;
    private String citta;
    private LocalDate dataOrdine;
    private double totale;
    private String stato_ordine;
    //private List<Prodotto> prodotti;
    private int quantita;
    private Carrello carrello;
    private Cliente cliente;

    public Ordine() {
        totale=0;
        quantita=0;
    }

    public int getNumeroOrdine() {
        return numeroOrdine;
    }

    public void setNumeroOrdine(int numeroOrdine) {
        this.numeroOrdine = numeroOrdine;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCodice_postale() {
        return codice_postale;
    }

    public void setCodice_postale(String codice_postale) {
        this.codice_postale = codice_postale;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public double getTotale() {
        return this.totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public String getStato_ordine() {
        return stato_ordine;
    }

    public void setStato_ordine(String stato) {
        this.stato_ordine = stato;
    }

    /*public List<Prodotto> getProdotti() {
        return prodotti;
    }
   public void addProdotti(Prodotto prodotto) {
        this.prodotti.add(prodotto);
    }
    public void setProdotti(List<Prodotto> p){this.prodotti=p; }*/
    public int getQuantita() {
        return this.quantita;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
