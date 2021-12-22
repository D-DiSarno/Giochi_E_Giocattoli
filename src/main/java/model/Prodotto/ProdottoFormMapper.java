package model.Prodotto;

import controller.Http.FormMapper;
import model.Categoria.Categoria;
import model.Cliente.Cliente;
import model.Produttore.Produttore;

import javax.servlet.http.HttpServletRequest;

public class ProdottoFormMapper implements FormMapper<Prodotto> {
    public Prodotto map(HttpServletRequest request, Boolean update){
       Prodotto prodotto=new Prodotto();
        prodotto.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
        prodotto.setNome(request.getParameter("Nome"));
        prodotto.setEta_minima(Integer.parseInt(request.getParameter("etaMinima")));
         prodotto.setQuantita(Integer.parseInt(request.getParameter("Quantita")));
         Categoria categoria=new Categoria();
         categoria.setIdCategoria(request.getParameter("Categoria"));
         prodotto.setCategoria(categoria);
         Produttore produttore=new Produttore();
         produttore.setIdProduttore(request.getParameter("Produttore"));
         prodotto.setProduttore(produttore);
         prodotto.setDescrizione((request.getParameter("Descrizione")));
         prodotto.setImg(request.getParameter("img"));
         System.out.println("img"+prodotto.getImg());
        if(update){
            prodotto.setIdProdotto(Integer.parseInt(request.getParameter("id")));

        }
        return prodotto;
    }
}
