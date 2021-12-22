package controller;

import controller.Http.*;
import controller.componenti.Alert;
import controller.componenti.Paginator;
import model.Categoria.Categoria;
import model.Categoria.SqlCategoriaDao;
import model.Cliente.SqlClienteDao;
import model.Ordine.Ordine;
import model.Prodotto.*;
import model.Produttore.Produttore;
import model.Produttore.SqlProduttoreDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(name="ProdottoServlet",value = "/prodotti/*")
@MultipartConfig
public class ProdottoServlet extends ControllerHttpServlet {
    private SqlProduttoreDao produttoreDao=new SqlProduttoreDao();
    private SqlCategoriaDao categoriaDao=new SqlCategoriaDao();
    private SqlProdottoDao prodottoDao=new SqlProdottoDao();

    private String nomeServlet="ProdottoServlet";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String path = getPath(request);

            System.out.println("qui in" + path);
            switch (path) {
                case "/": //mostra prodotti(admin)
                    System.out.println("pre authorize");
                    authorize(request.getSession(false));//solo admin passa

                    validate(CommonValidator.validatePage(request));//
                    System.out.println(request.getParameter("page"));
                    int page = parsePage(request);

                    System.out.println(page);
                    Paginator paginator = new Paginator(page, nomeServlet);//Paginatore per numero elementi
                    System.out.println("IN PS" + paginator.getOffset() + " " + paginator.getLimite());
                    int size = prodottoDao.countAll();//numero totale elementi in Db
                    request.setAttribute("pages", paginator.getPages(size));
                    System.out.println("X" + paginator.getPages(size));
                    List<Prodotto> prodotti = prodottoDao.fetchProducts(paginator);
                    System.out.println(prodotti.get(1).getNome());
                    request.setAttribute("prodotti", prodotti);
                    request.getRequestDispatcher(view("crm/prodotti")).forward(request, response);
                    break;

                case "/show": //mostra prodotto(admin)/*NON USATO*/
                    System.out.println("\nin show\n");
                    authorize(request.getSession(false));
                    validate(CommonValidator.validateId(request));
                    int id = Integer.parseInt(request.getParameter("id"));
                    System.out.println("Prodotto="+id);
                    Optional<Prodotto> optionalProdotto = prodottoDao.fetchProduct(id);
                    if (optionalProdotto.isPresent()) {
                        request.setAttribute("prodotto", optionalProdotto.get());
                        request.getRequestDispatcher(view("crm/prodotto")).forward(request, response);
                    } else {
                        notFound();
                    }
                    break;

                case "/create": //crea prodotto(admin)
                    authorize(request.getSession(false));
                    List<Produttore>produttoreList=produttoreDao.fetchProducersAll();
                    request.setAttribute("produttori",produttoreList);
                    List<Categoria>categoriaList=categoriaDao.fetchCategoriesAll();
                    request.setAttribute("categorie",categoriaList);
                    request.getRequestDispatcher(view("crm/prodotto")).forward(request, response);
                    break;
                case "/update": //aggiorna prodotto(admin)
                    authorize(request.getSession(false));
                    int idUpdate= Integer.parseInt(request.getParameter("id"));
                    Optional<Prodotto> upd=prodottoDao.fetchProduct(idUpdate);
                    request.setAttribute("prodotto",upd.get());
                    authorize(request.getSession(false));

                    List<Produttore>produttoreList1=produttoreDao.fetchProducersAll();
                    request.setAttribute("produttori",produttoreList1);
                    List<Categoria>categoriaList1=categoriaDao.fetchCategoriesAll();
                    request.setAttribute("categorie",categoriaList1);
                    request.getRequestDispatcher(view("prodotto/update")).forward(request, response);
                    break;

                case "/search": //cerca prodotto per nome(cliente)
                    System.out.println(" ENTRO IN SEARCH\n");
                    // SqlCategoriaDao categoriaDao=new SqlCategoriaDao();
                     //SqlProduttoreDao produttoreDao=new SqlProduttoreDao();

                    List<Categoria>categorie=categoriaDao.fetchCategoriesAll();
                    List<Produttore>produttori=produttoreDao.fetchProducersAll();

                    request.setAttribute("categorie",categorie);
                    request.setAttribute("produttori",produttori);
                    System.out.println("qui pre condizioni");
                    List<Condition> condizioni = new ProdottoSearch().buildSearch(request);
                    System.out.println("qui post condizioni");
                    List<Prodotto> prodottiSearch = condizioni.isEmpty() ? prodottoDao.fetchProducts
                            (new Paginator(1, "ProdottoServlet")) : prodottoDao.search(condizioni);

                    request.setAttribute("prodotti", prodottiSearch);
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;

                 case "/searchCategoria": //cerca prodotto per categoria(cliente)
                    System.out.println(" ENTRO IN SEARCH\n");
                    SqlCategoriaDao categoriaDaoSearch=new SqlCategoriaDao();
                    //SqlProduttoreDao produttoreDao=new SqlProduttoreDao();
                    List<Categoria>categorieSearch=categoriaDaoSearch.fetchCategoriesAll();
                    //List<Produttore>produttori=produttoreDao.fetchProducersAll();
                    request.setAttribute("categorie",categorieSearch);
                   // request.setAttribute("produttori",produttori);
                    System.out.println("qui pre condizioni");
                    List<Condition> condizioniSearchCat = new ProdottoSearch().buildSearch(request);
                    System.out.println("qui post condizioni");
                    List<Prodotto> prodottiSearchCat = condizioniSearchCat.isEmpty() ? prodottoDao.fetchProducts
                            (new Paginator(1, "ProdottoServlet")) : prodottoDao.search(condizioniSearchCat);
                    System.out.println("post operation in Search"+prodottiSearchCat.get(0).getImg());

                    request.setAttribute("prodotti", prodottiSearchCat);
                    request.getRequestDispatcher(view("site/searchCategoria")).forward(request, response);
                    break;



                case "/searchProduttore": //cerca prodotto per produttore(cliente)
                    System.out.println(" ENTRO IN SEARCH\n");
                    //SqlCategoriaDao categoriaDaoSearch=new SqlCategoriaDao();
                    SqlProduttoreDao produttoreDaoSearch=new SqlProduttoreDao();
                   // List<Categoria>categorieSearch=categoriaDaoSearch.fetchCategoriesAll();
                    List<Produttore>produttoriSearch=produttoreDaoSearch.fetchProducersAll();
                   // request.setAttribute("categorie",categorieSearch);
                     request.setAttribute("produttori",produttoriSearch);

                    List<Condition> condizioniSearchProd = new ProdottoSearch().buildSearch(request);

                    List<Prodotto> prodottiSearchProd = condizioniSearchProd.isEmpty() ? prodottoDao.fetchProducts
                            (new Paginator(1, "ProdottoServlet")) : prodottoDao.search(condizioniSearchProd);

                    request.setAttribute("prodotti", prodottiSearchProd);
                    request.getRequestDispatcher(view("site/searchProduttore")).forward(request, response);
                    break;
                case "/searchBarra": //cerca prodotto per produttore(cliente)
                    System.out.println("in Search Barra");
                    String ricerca=request.getParameter("ricerca");
                    Optional<Prodotto>prodotto=prodottoDao.findProdotto(ricerca);
                    System.out.println(prodotto.get().getNome());
                    if(prodotto.get().getNome()== null){
                        //request.getRequestDispatcher(view("site/search"));
                        System.out.println("non trovato");
                        response.sendRedirect("../prodotti/search");
                    }else
                    {   //request.setAttribute("idProdotto",prodotto.get().getIdProdotto());

                        response.sendRedirect("../prodotti/details?id="+prodotto.get().getIdProdotto());

                    }
                    break;
                case "/searchEta": //cerca prodotto per eta minima(cliente)
                    System.out.println("in SearchEta");
                    int minEta= Integer.parseInt(request.getParameter("minNumber"));
                    int maxEta= Integer.parseInt(request.getParameter("maxNumber"));
                    List<Categoria>categorieEta=categoriaDao.fetchCategoriesAll();
                    List<Produttore>produttoriEta=produttoreDao.fetchProducersAll();

                    request.setAttribute("categorie",categorieEta);
                    request.setAttribute("produttori",produttoriEta);
                    List<Prodotto>prodottiEta=prodottoDao.searchEta(minEta,maxEta);
                    request.setAttribute("prodotti", prodottiEta);
                   // response.sendRedirect("../prodotti/search");
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;
                case "/searchCategoriaPreferita": //cerca prodotto per categoria preferita(cliente)
                    System.out.println("in search Preferita");
                    String tipo=request.getParameter("tipologia");

                    List<Prodotto>prodottiPref=prodottoDao.searchCategoria(tipo);

                    List<Produttore>produttoriListUp = produttoreDao.fetchProducersAll();
                    List<Categoria>categoriaListUp=categoriaDao.fetchCategoriesAll();
                    request.setAttribute("produttori",produttoriListUp);
                    request.setAttribute("categorie",categoriaListUp);
                    request.setAttribute("prodotti", prodottiPref);
                    //response.sendRedirect("../prodotti/search");
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;

                case "/details":
                    int idProdotto = Integer.parseInt(request.getParameter("id"));
                    System.out.println("in details");
                    Optional<Prodotto> optionalProdottoView = prodottoDao.fetchProductCategoriaProduttore(idProdotto);

                    if (optionalProdottoView.isPresent()) {
                       // System.out.println(optionalProdottoView.get().getNome()+""+optionalProdottoView.get().getImg());
                        request.setAttribute("prodotto", optionalProdottoView.get());

                        request.getRequestDispatcher(view("site/details")).forward(request, response);

                    } else {
                        notFound();
                    }
                    break;
                default:
                    notFound();
            }
            }catch(SQLException throwables){
                log(throwables.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, throwables.getMessage());
            } catch(InvalidRequestException e){
                log(e.getMessage());
                e.handle(request, response);
            }
        }



      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            String path=getPath(request);
            System.out.println("In Servlet prodotto");
            System.out.println(path);
            switch (path){
                case "/create": //crea prodotto(admin)
                    authorize(request.getSession(false));//autorizzo
                    request.setAttribute("back", view("crm/prodotto"));

                   validate(ProdottoValidator.validateForm(request,false));
                   System.out.println("post valida");
                   Prodotto prodotto=new ProdottoFormMapper().map(request,false);

                    if(prodottoDao.createProduct(prodotto))
                    {
                       // prodotto.writeImg(getUploadPath(),request.getPart("img"));
                        System.out.println(prodotto.getImg()+" img");
                        request.setAttribute("prodotto",prodotto);
                        request.setAttribute("alert",new Alert(List.of("Prodotto Creato!"),"success"));
                        request.getRequestDispatcher(view("crm/prodotto")).forward(request, response);
                    }else{

                       internalError();
                    }
                    break;
                case "/update": //aggiorno prodotto(admin)

                    authorize(request.getSession(false));
                    request.setAttribute("back",view("prodotto/update"));
                    validate(ProdottoValidator.validateForm(request,true));
                    Prodotto prodottoAgg=new ProdottoFormMapper().map(request,true);
                    request.setAttribute("prodotto",prodottoAgg);
                    if(prodottoDao.updateProduct(prodottoAgg)) {
                       // prodottoAgg.writeImg(getUploadPath(), request.getPart("img"));
                        request.setAttribute("alert",new Alert(List.of("Prodotto Aggiornato!"),"success"));
                        //response.setStatus(HttpServletResponse.SC_CREATED);
                        request.setAttribute("prodotto",prodottoAgg);
                        request.getRequestDispatcher(view("prodotto/update")).forward(request, response);
                    }
                    else{ internalError();}
                    break;
                case "/delete": //elimino prodotto(admin)
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("crm/prodotto"));
                    validate(ProdottoValidator.validateDelete(request));
                   int id= Integer.parseInt(request.getParameter("id"));
                   System.out.println("id"+id);
                    if(prodottoDao.deleteProduct(id)) {

                        request.setAttribute("alert",new Alert(List.of("Prodotto Eliminato!"),"success"));
                        //response.setStatus(HttpServletResponse.SC_CREATED);
                        request.getRequestDispatcher(view("crm/delete")).forward(request,response);
                    }
                    else{ internalError();}
                    break;
                 default:
                    notAllowed();
            }
        }
        catch(SQLException | InvalidRequestException ex) {
            log(ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore server");
        }
    }
}
