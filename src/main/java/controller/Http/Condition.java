package controller.Http;

public class Condition {
    private final String nome;//Parte sinistra da cercare
    private final Operator operatore;//criterio
    private final Object valore;//parte destra
    //nome operatore valore
    public Condition(String nome,Operator operatore,Object valore) {
        this.nome = nome;
        this.operatore = operatore;
        this.valore = valore;
    }

    public String getNome() {
        return nome;
    }

    public Operator getOperatore() {
        return operatore;
    }

    public Object getValore() {
        return valore;
    }
}
