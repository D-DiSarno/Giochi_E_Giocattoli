package controller;

import controller.Http.CommonValidator;
import controller.Http.InvalidRequestException;
import controller.componenti.Alert;
import controller.componenti.Paginator;
import model.Categoria.Categoria;
import model.Categoria.CategoriaFormMapper;
import model.Categoria.CategoriaValidator;
import model.Categoria.SqlCategoriaDao;
import model.Cliente.Cliente;
import model.Produttore.Produttore;
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
@WebServlet(name="CategoriaServlet",value = "/categorie/*")
public class CategoriaServlet extends ControllerHttpServlet {

private SqlCategoriaDao categoriaDao=new SqlCategoriaDao();
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           String path = getPath(request);
           switch (path) {
               case "/"://show categorie(admin)
                   System.out.println(" categoria pre");
                   authorize(request.getSession(false));

                   request.setAttribute("page",1);
                   validate(CommonValidator.validatePage(request));

                   System.out.println(" categoria post");
                   int page = parsePage(request);

                   Paginator paginatore = new Paginator(page, "CategoriaServlet");
                   System.out.println(paginatore.getLimite());

                   int size = categoriaDao.countAll();
                   System.out.println("X" + paginatore.getPages(size));
                   List<Categoria> categorie = categoriaDao.fetchCategories(paginatore);
                   System.out.println(categorie.get(0).getIdCategoria());
                   request.setAttribute("categorie", categorie);
                   request.setAttribute("pages", paginatore.getPages(size));
                   request.getRequestDispatcher(view("crm/categorie")).forward(request, response);
                   break;

               case "/show"://show categoria(admin)
                   authorize(request.getSession(false));
                   validate(CommonValidator.validatePage(request));
                   String id = request.getParameter("id");
                   Optional<Categoria> categoriaOptional = categoriaDao.fetchCategories(id);
                   if (categoriaOptional.isPresent()) {
                       request.setAttribute("categoria", categoriaOptional);
                       request.getRequestDispatcher(view("crm/categoria")).forward(request, response);
                   } else {
                       notFound();
                   }
                   break;
               case "/create":
                   authorize(request.getSession(false));
                   request.getRequestDispatcher(view("crm/categoria")).forward(request, response);
                   break;
               case "/update":
                   authorize(request.getSession(false));
                   String idUpd= request.getParameter("id");
                   Optional<Categoria> cl=categoriaDao.fetchCategories(idUpd);
                   request.setAttribute("categoria",cl.get());
                   request.getRequestDispatcher(view("categoria/update")).forward(request, response);
                   break;

               case "/prodotti":
                   request.getRequestDispatcher(view("site/search")).forward(request, response);
                   break;

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
                request.setAttribute("back",view("crm/categoria"));

                validate(CategoriaValidator.validateForm(request,false));
                Categoria categoria=new CategoriaFormMapper().map(request,true);
                if(categoriaDao.createCategory(categoria)){
                    System.out.println("creata");
                    request.setAttribute("categoria",categoria);
                    request.setAttribute("alert",new Alert(List.of("Categoria creata!"),"success"));
                    request.getRequestDispatcher(view("crm/categoria")).forward(request,response);
                }else{internalError();}
                break;
            case "/update": //aggiorno(admin)

                authorize(request.getSession(false));
                request.setAttribute("back",view("categoria/update"));
                validate(CategoriaValidator.validateForm(request,true));
                Categoria categoriaAgg=new CategoriaFormMapper().map(request,true);
               System.out.println(categoriaAgg.getIdCategoria());

                if(categoriaDao.updateCategory(categoriaAgg)) {
                    request.setAttribute("categoria",categoriaAgg);
                    request.setAttribute("alert", new Alert(List.of("Categoria Aggiornata!"), "success"));
                    request.getRequestDispatcher(view("categoria/update")).forward(request, response);
                }else{
                    internalError();}
                    break;

            case"/delete"://elimino(admin)
                System.out.println("in categorie Delete");
                authorize(request.getSession(false));
                request.setAttribute("back",view("crm/categoria"));
                validate(CategoriaValidator.validateDelete(request));
                String id=request.getParameter("id");
                System.out.println("sto per cancellare "+ id);
                if(categoriaDao.deleteCategory(id)) {

                      request.setAttribute("alert", new Alert(List.of("Categoria Rimossa!"), "success"));
                    //request.getRequestDispatcher(view("crm/categoria")).forward(request, response);
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
