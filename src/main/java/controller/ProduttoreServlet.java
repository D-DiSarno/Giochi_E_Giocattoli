package controller;

import controller.Http.CommonValidator;
import controller.Http.InvalidRequestException;
import controller.componenti.Alert;
import controller.componenti.Paginator;
import model.Categoria.Categoria;
import model.Produttore.Produttore;
import model.Produttore.ProduttoreFormMapper;
import model.Produttore.ProduttoreValidator;
import model.Produttore.SqlProduttoreDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@WebServlet(name="ProduttoreServlet",value = "/produttori/*")
public class ProduttoreServlet extends ControllerHttpServlet{
    protected SqlProduttoreDao produttoreDao=new SqlProduttoreDao();
    private String nomeServlet="ProduttoreServlet";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/"://show produttori(admin)
                    authorize(request.getSession(false));
                    validate(CommonValidator.validatePage(request));
                    //request.setAttribute("page",1);
                    int page = parsePage(request);
                    Paginator paginatore = new Paginator(page, nomeServlet);
                    int size=produttoreDao.countAll();
                    System.out.println(size);
                    List<Produttore> produttori = produttoreDao.fetchProducers(paginatore);
                    request.setAttribute("produttori", produttori);
                    request.setAttribute("pages", paginatore.getPages(size));
                    request.getRequestDispatcher(view("crm/produttori")).forward(request, response);
                    break;

                case "/show"://show produttori(admin)
                    authorize(request.getSession(false));
                    validate(CommonValidator.validatePage(request));
                    String id = request.getParameter("id");
                    Optional<Produttore> produttoreOptional = produttoreDao.fetchProducer(id);
                    if (produttoreOptional.isPresent()) {
                        request.setAttribute("produttore", produttoreOptional);
                        request.getRequestDispatcher(view("crm/produttore")).forward(request, response);
                    } else {
                        notFound();
                    }
                    break;
                case "/create":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher(view("crm/produttore")).forward(request, response);
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    String idUpd= request.getParameter("id");
                    Optional<Produttore> cl=produttoreDao.fetchProducer(idUpd);
                    request.setAttribute("produttore",cl.get());
                    request.getRequestDispatcher(view("produttori/update")).forward(request, response);
                    break;
                case "/prodotti":
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;
               /* case "/api":
                    if(isAjax(request)){
                        List<Produttore>produttoriAjax=produttoreDao.fetchProducers(new Paginator(1,"ProduttoreServlet"));
                        JSONObject root=new JSONObject();
                        JSONArray arr=new JSONArray();
                        root.put("produttori",arr);
                        produttoriAjax.forEach(c -> arr.add(c.toJson()));
                        sendJson(response,root);
                        break;
                    }else{ notFound();}*/
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");

            }
        } catch (SQLException throwables) {
           log(throwables.getMessage());
        }catch (InvalidRequestException ex){
            log(ex.getMessage());
            ex.handle(request,response);
        }
    }


    //alterare stato del server
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{String path=getPath(request);
            switch(path){
                case"/create"://creo(admin)
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("crm/produttore"));
                    validate(ProduttoreValidator.validateForm(request,false));//creo validatore
                    Produttore produttore=new ProduttoreFormMapper().map(request,true);
                    if(produttoreDao.createProducer(produttore)){
                       request.setAttribute("produttore",produttore);
                        request.setAttribute("alert",new Alert(List.of("Produttore creato!"),"success"));
                        request.getRequestDispatcher(view("crm/produttore")).forward(request,response);
                      //  response.sendRedirect("../produttori/");
                    }else{internalError();}
                    break;
                case "/update": //aggiorno(admin)
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("produttori/update"));
                    validate(ProduttoreValidator.validateForm(request,true));
                   Produttore produttoreAgg=new ProduttoreFormMapper().map(request,true);
                   String idOriginale=request.getParameter("idOriginale");
                    request.setAttribute("produttore",produttoreAgg);
                    if(produttoreDao.updateProduces(produttoreAgg,idOriginale)) {
                        request.setAttribute("produttore",produttoreAgg);
                        request.setAttribute("alert", new Alert(List.of("Produttore Aggiornato!"), "success"));
                        request.getRequestDispatcher(view("produttori/update")).forward(request, response);
                        //response.sendRedirect("../produttori/");
                    }else{internalError();}
                    break;

                case"/delete"://elimino(admin)
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("crm/produttore"));
                    validate(ProduttoreValidator.validateDelete(request));
                    String id=request.getParameter("id");
                    if(produttoreDao.deleteProducer(id)) {
                        request.setAttribute("alert", new Alert(List.of("Produttore Rimosso!"), "success"));
                        request.getRequestDispatcher(view("crm/delete")).forward(request,response);
                    }else{internalError();}
                    break;

                default:
                    notAllowed();

            }
        } catch (SQLException throwables) {
           log(throwables.getMessage());
        } catch (InvalidRequestException e) {
            log(e.getMessage());
            e.handle(request,response);
        }
    }
}


