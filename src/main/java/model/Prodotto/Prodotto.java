package model.Prodotto;

import model.Categoria.Categoria;
import model.Produttore.Produttore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;

public class Prodotto {
    private String nome;
    private int idProdotto;
    private double prezzo;
    private String descrizione;
    private int quantita;
    private int eta_minima;
    private String img;
    private Categoria categoria;
    private Produttore produttore;

    public String getImg() {
        return img;
    }

    public void setImg(String imgName) {
        this.img = imgName;
    }

    public int getEta_minima() {
        return eta_minima;
    }

    public void setEta_minima(int eta_minima) {
        this.eta_minima = eta_minima;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

    public Prodotto() {
    }

    public void writeImg(String path, Part stream) throws IOException {
        try (InputStream fileStream = stream.getInputStream()) {//apre inputStream
            File file = new File(path + img);//crea file
            Files.copy(fileStream, file.toPath());//direttiva file

        }
    }
}
