package model.Cliente;

import model.Categoria.Categoria;
import model.Ordine.Ordine;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Cliente {
    private String nome;
    private String cognome;
    private String email;
    private String via;
    private String citta;
    private String codice_postale;//anche numerico
    private int idCliente;
    private Boolean ruolo;
    private String password;
    private List<Categoria> preferiti;
    private List<Ordine>ordini;
   //DB relazioni N a N Db
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException { //SHA-512 prende array byte e format per usarlo
      /*  MessageDigest digest= MessageDigest.getInstance("SHA-512");
        byte[] hashedPwd= digest.digest(password.getBytes( StandardCharsets.UTF_8));
        StringBuilder builder=new StringBuilder();
        for(byte bit :hashedPwd){
            builder.append(String.format("%02x",bit));
        }

        this.password = builder.toString();

       */
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password = String.format("%02x", new
                    BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Categoria> getPreferiti() {
        return preferiti;
    }

    public void setPreferiti(List<Categoria> preferiti) {
        this.preferiti = preferiti;
    }

    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCodice_postale() {
        return codice_postale;
    }

    public void setCodice_postale(String codice_postale) {
        this.codice_postale = codice_postale;
    }

    public Boolean getRuolo() {
        return ruolo;
    }

    public void setRuolo(Boolean ruolo) {
        this.ruolo = ruolo;
    }

    public Cliente() {
    }

}
