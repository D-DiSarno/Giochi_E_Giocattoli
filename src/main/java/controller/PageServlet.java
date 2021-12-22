package controller;

import controller.Http.InvalidRequestException;
import model.Cliente.SqlClienteDao;
import model.Ordine.SqlOrdineDao;
import model.Prodotto.Prodotto;
import model.Prodotto.SqlProdottoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "PageServlet", value = "/pages/*")
public class PageServlet extends ControllerHttpServlet {
    private SqlClienteDao clienteDao = new SqlClienteDao();
    private SqlOrdineDao ordineDao = new SqlOrdineDao();
    private SqlProdottoDao prodottoDao = new SqlProdottoDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String path = getPath(request);
            System.out.println("in pages Servlet");
            switch (path) {

                case "/dashboard":
                    System.out.println("dashboard");
                    authorize(request.getSession(false));
                    System.out.println("sessione autorizzata");
                    request.setAttribute("back", view("crm/clienti"));

                    int clienti = clienteDao.countAll();
                    request.setAttribute("clientiNum", clienti);
                    //System.out.println("qua"+clienti);
                    int ordini = ordineDao.countAll();
                    request.setAttribute("ordini", ordini);

                    Double incasso= ordineDao.getTotaleIncasso();

                    request.setAttribute("incasso", incasso);


                    int prodottiTot = prodottoDao.getTotale();
                    request.setAttribute("prodottiRimanenti", prodottiTot);

                    request.getRequestDispatcher(view("crm/home")).forward(request, response);
                    break;
                case "/":
                    SqlProdottoDao prodottoDao=new SqlProdottoDao();
                    List<Prodotto> prodottoList=prodottoDao.fetchProductsLimit(10);
                    request.setAttribute("prodotti",prodottoList);
                    request.getRequestDispatcher(view("crm/Home-Page")).forward(request, response);
                  //  request.getRequestDispatcher(view("site/home")).forward(request, response);
                    break;

                case "/regolamento": //show regolamento
                    System.out.println("in regolamento");
                    request.getRequestDispatcher(view("documenti/regolamentiInfo")).forward(request, response);
                    break;
                case "/politiche": //show politiche
                    System.out.println("in politiche");
                    request.getRequestDispatcher(view("documenti/politiche")).forward(request, response);
                    break;
                case "/contattaci": //show info
                    System.out.println("in contattaci");
                    request.getRequestDispatcher(view("documenti/contattaci")).forward(request, response);
                    break;
                default:
                    notFound();
            }
        } catch (InvalidRequestException ex) {
            log(ex.getMessage());
            ex.handle(request, response);
        }catch (SQLException s){
            s.printStackTrace();
        }
    }
}
