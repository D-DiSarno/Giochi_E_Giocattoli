package controller;

import controller.Http.InvalidRequestException;
import model.Categoria.SqlCategoriaDao;
import model.Cliente.Cliente;
import model.Cliente.SqlClienteDao;
import model.Ordine.SqlOrdineDao;
import model.Prodotto.SqlProdottoDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Optional;
/*
@WebServlet(name="CrmServlet", value="/crm/*")
public class CrmServlet extends ControllerHttpServlet {
    private SqlClienteDao clienteDao = new SqlClienteDao();
    private SqlOrdineDao ordineDao = new SqlOrdineDao();
    private SqlProdottoDao prodottoDao = new SqlProdottoDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String path = getPath(request);
            System.out.println(path);
            switch (path) {
                case "/dashboard":
                    /*Session
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/clienti"));
                    System.out.println("qui");
                    int clienti = clienteDao.countAll();
                    request.setAttribute("clientiNum", clienti);
                    System.out.println("qua");
                    int ordiniMensili = ordineDao.countAll();
                    request.setAttribute("ordiniMensili", ordiniMensili);
                    System.out.println("que");
                    Double incassoMensile = ordineDao.getTotaleIncasso();
                    System.out.println("quw");
                    request.setAttribute("incassoMensile", incassoMensile);
                    System.out.println("quo"+incassoMensile);
                    int prodottiTot = prodottoDao.getTotale();
                    request.setAttribute("prodottiRimanenti", prodottiTot);


                    request.getRequestDispatcher(view("crm/home")).forward(request, response);
                    break;

                  case "/prodotti":
                request.getRequestDispatcher(view("crm/prodotti")).forward(request,response);
                  break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
            e.handle(request, response);
        }
    }
}

*/
