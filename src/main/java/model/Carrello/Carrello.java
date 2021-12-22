package model.Carrello;

import model.Prodotto.Prodotto;

import java.util.List;
import java.util.Optional;

public class Carrello {
    private List<CarrelloElementi> elementi;

    public Carrello(List<CarrelloElementi> elementi) {
        this.elementi = elementi;

    }
    public double totale(){
        double tot=0;
        for (CarrelloElementi x:elementi
             ) {
            tot+=x.totale();
        }
        return tot;
    }
       public  boolean addProdotti(Prodotto prodotto,int quantita){
           Optional<CarrelloElementi> carrelloElementiOpt=find(prodotto.getIdProdotto());
           if(carrelloElementiOpt.isPresent()) {//se prodotto è presente aggiorno quantità
               carrelloElementiOpt.get().setQuantita(quantita);
               System.out.println(carrelloElementiOpt.get().getQuantita()+"addProdotti");
               return true;
           } else {//se non presente aggiungo a carrello
               return elementi.add(new CarrelloElementi(prodotto,quantita));
           }
       }

   public Optional<CarrelloElementi> find(int id){//vedo se in array esiste prodotto con stesso id
        return elementi.stream().filter(it-> it.getProdotto().getIdProdotto() == id).findFirst();
   }
    public List<CarrelloElementi> getElementi() {
        return elementi;
    }

    public void setElementi(List<CarrelloElementi> elementi) {
        this.elementi = elementi;
    }
    public void resetCarrello(){
        elementi.clear();
    }
    public boolean removeProdotto(int id){

        return elementi.removeIf(it-> it.getProdotto().getIdProdotto() == id);
    }


    public int quantita(){
        int quantita=0;
    for (CarrelloElementi x:elementi
    ) {
        quantita+=x.getQuantita();
    }
    return quantita;
}
}

