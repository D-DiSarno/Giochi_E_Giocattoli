package controller;

import com.mysql.cj.Session;
import controller.Http.CommonValidator;
import controller.Http.InvalidRequestException;
import model.Carrello.Carrello;
import model.Carrello.CarrelloValidator;
import model.Cliente.SqlClienteDao;
import model.Prodotto.Prodotto;
import model.Prodotto.SqlProdottoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;


@WebServlet(name= "CarrelloServlet" , value="/carrelli/*")
    public class CarrelloServlet extends ControllerHttpServlet {


    private SqlProdottoDao prodottoDao = new SqlProdottoDao();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            System.out.println(path);
            switch (path) {
                case "/show":
                    authenticated(request.getSession(false));

                    //System.out.println("carrello show" + request.getParameter("authenticated"));
                    request.getRequestDispatcher(view("site/carrello")).forward(request, response);

                    break;
                default:
                    notFound();
            }
        } catch (InvalidRequestException e) {
            log(e.getMessage());
            e.handle(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            System.out.println(path);
            switch (path) {
                case "/add":// add a carrello
                    //System.out.println("in add"); //
                    request.setAttribute("back",view(("site/details")));
                    validate(CarrelloValidator.validateProduct(request));
                    int id=Integer.parseInt(request.getParameter("id"));
                    //Optional<Prodotto>prodottoOptional=prodottoDao.fetchProductWithhRelations(id);
                    Optional<Prodotto>prodottoOptional=prodottoDao.fetchProduct(id);
                    //System.out.println(prodottoOptional.get().getNome()+" "+ id);


                    if(prodottoOptional.isPresent()){

                        int quantita=Integer.parseInt((request.getParameter("Quantita")));
                        System.out.println("quanto="+quantita);

                        if(request.getSession(false).getAttribute("clienteCarrello")== null){//se non creato lo creo
                            request.getSession(false).setAttribute("clienteCarrello",new Carrello(new ArrayList<>()));
                        }
                        getCarrelloSessione(request.getSession(false)).addProdotti(prodottoOptional.get(),quantita);
                        request.setAttribute("prodotto",prodottoOptional.get());
                       //  request.getRequestDispatcher(view("site/details")).forward(request,response);
                        //response.sendRedirect("../prodotti/details?id="+prodottoOptional.get().getIdProdotto());
                         response.sendRedirect("../prodotti/search");
                    }
                    else{notFound();}

                    break;
                case "/remove":
                    validate(CommonValidator.validateId(request));
                    int idRemove=Integer.parseInt(request.getParameter("id"));
                    if(getCarrelloSessione(request.getSession(false)).removeProdotto(idRemove)){
                        response.sendRedirect("../carrelli/show");
                    }
                   else{notFound();}
                    break;
                default:
                    notFound();
            }
        } catch (InvalidRequestException e) {
            log(e.getMessage());
            e.handle(request, response);
        } catch (SQLException throwables) {
            log(throwables.getMessage());
        }
    }

}

