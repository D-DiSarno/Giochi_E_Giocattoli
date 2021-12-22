package model.Cliente;


public class ClienteSession {
    private final String Nome;
    private final String Cognome;
    private final int id;
    private final boolean Ruolo;

    public ClienteSession(Cliente cliente){
        this.Nome=cliente.getNome();
        this.Cognome=cliente.getCognome();
        this.id=cliente.getIdCliente();
        this.Ruolo=cliente.getRuolo();
    }

    public String getNome() {
        return Nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public int getId() {
        return id;
    }

    public boolean isRuolo() {
        return Ruolo;
    }


}

