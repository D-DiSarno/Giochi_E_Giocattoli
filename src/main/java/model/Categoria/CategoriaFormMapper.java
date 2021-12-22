package model.Categoria;

import controller.Http.FormMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoriaFormMapper implements FormMapper<Categoria> {

    @Override
    public Categoria map(HttpServletRequest request, Boolean update) {
        Categoria categoria=new Categoria();

        categoria.setTipologia(request.getParameter("Tipologia"));
        categoria.setEtaMinima(Integer.parseInt(request.getParameter("Eta")));
        if(update){ categoria.setIdCategoria((request.getParameter("id")));}
        return categoria;
    }
}

