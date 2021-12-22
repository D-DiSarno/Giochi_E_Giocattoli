package model.Ordine;

import controller.Http.FormMapper;
import model.Prodotto.Prodotto;

import javax.servlet.http.HttpServletRequest;

public class OrdineFormMapper implements FormMapper<Ordine> {
    public Ordine map(HttpServletRequest request, Boolean update) {
        Ordine ordine = new Ordine();
        ordine.setTotale(Double.parseDouble(request.getParameter("totale")));
        ordine.setCitta(request.getParameter("citta"));
        ordine.setVia(request.getParameter("via"));
        ordine.setCodice_postale(request.getParameter("codice_postale"));
        ordine.setStato_ordine(request.getParameter("stato_ordine"));
      //  ordine.setQuantita(Integer.parseInt(request.getParameter("quantita")));

        if (update) {
            ordine.setNumeroOrdine(Integer.parseInt(request.getParameter("id")));

        }
        return ordine;
    }
}