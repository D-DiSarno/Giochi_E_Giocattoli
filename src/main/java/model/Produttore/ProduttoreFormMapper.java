package model.Produttore;

import controller.Http.FormMapper;
import model.Categoria.Categoria;

import javax.servlet.http.HttpServletRequest;

public class ProduttoreFormMapper implements FormMapper<Produttore> {


       public Produttore map(HttpServletRequest request, Boolean update) {
       Produttore produttore=new Produttore();
        if(update){ produttore.setIdProduttore((request.getParameter("id")));}
        produttore.setNome(request.getParameter("Nome"));
        produttore.setEmail(request.getParameter("Mail"));
        return produttore;
    }
    }

