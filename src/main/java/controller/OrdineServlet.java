package controller;

import controller.Http.CommonValidator;
import controller.Http.InvalidRequestException;
import controller.componenti.Alert;
import controller.componenti.Paginator;
import model.Carrello.Carrello;
import model.Cliente.Cliente;
import model.Cliente.SqlClienteDao;
import model.Ordine.Ordine;
import model.Ordine.OrdineFormMapper;
import model.Ordine.OrdineValidator;
import model.Ordine.SqlOrdineDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(name="OrdiniServlet",value="/ordini/*")
public class OrdineServlet extends ControllerHttpServlet {
    //@Resource(name = "jdbc/GiochiEGiocattoli")
    protected SqlOrdineDao sqlOrdineDao=new SqlOrdineDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            System.out.println(path);
            switch (path) {
                case "/"://show ordini(admin)
                    authorize(request.getSession(false));
                    validate(CommonValidator.validatePage(request));


                    int intPage = parsePage(request);
                    int size = sqlOrdineDao.countAll();
                    Paginator paginator = new Paginator(intPage, "OrdineServlet");
                    List<Ordine> ordini = sqlOrdineDao.fetchOrders(paginator);
                    System.out.println(ordini.get(0).getDataOrdine());
                    request.setAttribute("ordini", ordini);
                    request.setAttribute("pages", paginator.getPages(size));
                    request.getRequestDispatcher(view("crm/ordini")).forward(request, response);
                    break;

                case "/show":
                    authorize(request.getSession(false));
                    validate(CommonValidator.validateId(request));
                    int id = Integer.parseInt(request.getParameter("idProdotto"));
                    Optional<Ordine> optOrdine = sqlOrdineDao.fetchOrdersWithProducts(id);
                    if (optOrdine.isPresent()) {
                        request.setAttribute("ordine", optOrdine.get());
                        request.getRequestDispatcher(view("crm/ordine")).forward(request, response);
                    } else {
                        notFound();
                    }
                    break;
                case "/create"://creo ordine(admin)
                    authorize(request.getSession(false));
                    request.getRequestDispatcher(view("crm/ordine")).forward(request, response);
                    break;
                case "/update": //aggiorna ordine(admin)
                    authorize(request.getSession(false));
                    int idUpdate= Integer.parseInt(request.getParameter("id"));
                    System.out.println(idUpdate+" id update");
                    Optional<Ordine> upd=sqlOrdineDao.fetchOrdiniByNumero(idUpdate);
                    System.out.println(upd.get().getTotale());
                    request.setAttribute("ordine",upd.get());
                    request.getRequestDispatcher(view("ordine/update")).forward(request, response);
                    break;

               /* case "/profile":
                    HttpSession session = request.getSession(false);
                    authenticated(session);
                    int idCliente= getClienteSessione(session).getId();
                    System.out.println(idCliente +" profilo");
                    List<Ordine>ordineList=sqlOrdineDao.fetchOrdersWithProductsListByCliente(idCliente);
                    System.out.println(ordineList.size()+" numero ordini");
                    int i=0;
                    for (Ordine x:ordineList
                         ) {
                        System.out.println("ordine"+x.getCliente()+" "+x.getCarrello().getElementi().get(i).getProdotto().getIdProdotto());
                      i++;
                    }
                    request.setAttribute("ordini",ordineList);
                    request.getRequestDispatcher(view("site/profile")).forward(request,response);
                    break;*/
                default:
                    notFound();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (InvalidRequestException e) {
            log(e.getMessage());
            e.handle(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path=getPath(request);
            switch (path){
                case "/create": //crea ordine (cliente)
                    HttpSession session=request.getSession(false);
                    System.out.println("in create");
                    authenticated(session);
                    Ordine ordine=new Ordine();
                    Optional<Cliente> cliente;
                    int idCliente=((getClienteSessione(session)).getId());
                    SqlClienteDao clienteDao=new SqlClienteDao();
                    cliente=clienteDao.fetchAccountById(idCliente);

                    Carrello carrello= (Carrello) request.getSession(false).getAttribute("clienteCarrello");

                    ordine.setVia(cliente.get().getVia());
                    ordine.setStato_ordine("in elaborazione");
                    ordine.setCitta(cliente.get().getCitta());
                    ordine.setCodice_postale(cliente.get().getCodice_postale());
                    ordine.setCarrello(carrello);
                    ordine.setTotale(getCarrelloSessione(session).totale());
                    ordine.setCliente(cliente.get());
                    ordine.setDataOrdine(LocalDate.now());

                    if(sqlOrdineDao.createOrder(ordine)){
                        System.out.println("creato");

                        getCarrelloSessione(session).resetCarrello();
                        response.sendRedirect("../accounts/profilo");

                    }else{
                        internalError();
                    }break;

                case "/update": //aggiorno ordine(admin)
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("ordine/update"));

                    validate(OrdineValidator.validateForm(request));
                    Ordine ord=new OrdineFormMapper().map(request,true);
                    request.setAttribute("ordine",ord);
                    System.out.println("in update");
                    if(sqlOrdineDao.updateOrder(ord)) {

                        request.setAttribute("alert",new Alert(List.of("Ordine Aggiornato!"),"success"));
                        response.setStatus(HttpServletResponse.SC_CREATED);
                      //  response.sendRedirect("../ordini/");
                        request.getRequestDispatcher(view("ordine/update")).forward(request, response);
                    }
                    else{ internalError();}
                    break;
                case "/delete": //elimino ordine(admin)
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("crm/ordine"));
                    validate(OrdineValidator.validateForm(request));
                    int id= (int) request.getAttribute("id");
                    if(sqlOrdineDao.deleteOrder(id)) {
                        request.setAttribute("alert",new Alert(List.of("Ordine Eliminato!"),"success"));
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        response.sendRedirect("../ordini/");
                    }
                    else{ internalError();}
                    break;

                default:
                    notAllowed();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (InvalidRequestException e){
            log(e.getMessage());
            e.handle(request,response);
        }
    }
    }
