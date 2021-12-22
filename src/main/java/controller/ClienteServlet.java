package controller;

import controller.Http.CommonValidator;
import controller.Http.InvalidRequestException;
import controller.componenti.Alert;
import controller.componenti.Paginator;
import model.Categoria.Categoria;
import model.Categoria.SqlCategoriaDao;
import model.Cliente.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@WebServlet(name= "AccountServlet" , value="/accounts/*")
public class ClienteServlet extends ControllerHttpServlet {


    private SqlClienteDao clienteDao=new SqlClienteDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            System.out.println(path);

            switch (path) {
                 case "/"://lista account(admin)
                    authorize(request.getSession());
                    request.setAttribute("back", view("crm/clienti"));

                    validate(CommonValidator.validatePage(request));
                    int page = parsePage(request);
                    Paginator paginatore = new Paginator(page, "ClienteServlet");
                    int size = clienteDao.countAll();
                    request.setAttribute("pages", paginatore.getPages(size));
                    System.out.println("pre clienti");
                    List<Cliente> clienti = clienteDao.fetchAccounts(paginatore);
                    System.out.println(clienti.get(0).getNome());
                    request.setAttribute("clienti", clienti);
                    request.getRequestDispatcher(view("crm/clienti")).forward(request, response);
                    break;

                    case "/signin": //login cliente(pagina)
                     request.getRequestDispatcher(view("site/signin")).forward(request,response);
                      break;

                case "/create"://creo cliente(admin)
                    authorize(request.getSession(false));
                    request.getRequestDispatcher(view("crm/cliente")).forward(request, response);
                    break;
                case "/update"://modifico cliente(admin)
                    authorize(request.getSession(false));
                    int idCl= Integer.parseInt(request.getParameter("id"));
                    Optional<Cliente> cl=clienteDao.fetchAccountById(idCl);
                    request.setAttribute("cliente",cl.get());
                    request.getRequestDispatcher(view("cliente/update")).forward(request, response);
                    break;
                case "/modificoCliente"://modifco cliente(cliente)
                    int idProfiloCliente = getClienteSessione(request.getSession(false)).getId();

                    Optional<Cliente> profiloClienteUp = clienteDao.fetchAccountById(idProfiloCliente);
                     request.setAttribute("cliente",profiloClienteUp.get());

                    request.getRequestDispatcher(view("cliente/updateCliente")).forward(request, response);

                    break;

                case "/show"://cliente info update(admin)
                    authorize(request.getSession(false));
                    validate(CommonValidator.validateId(request));
                    int id = Integer.parseInt(request.getParameter("id"));
                    Optional<Cliente> clienteOpt = clienteDao.fetchAccountById(id);
                    if (clienteOpt.isPresent()) {
                        System.out.println(" in present show");
                        request.setAttribute("cliente", clienteOpt);
                        request.getRequestDispatcher(view("crm/cliente")).forward(request, response);
                    } else
                        notFound();
                   break;
                case "/secret"://login pagina
                    System.out.println("in secret");
                    request.getRequestDispatcher(view("crm/secret")).forward(request, response);
                    // request.getRequestDispatcher("/WEB-INF/views/crm/secret.jsp").forward(request,response);
                    break;



                    case "/signup"://registro cliente
                    request.getRequestDispatcher(view("site/signup")).forward(request, response);
                    break;

                case "/profilo": //show profilo cliente
                    System.out.println("qui");
                    int profilo = getClienteSessione(request.getSession(false)).getId();
                    System.out.println(profilo);
                    Boolean ruolo=getClienteSessione(request.getSession(false)).isRuolo();
                    System.out.println(ruolo);
                    if(ruolo){

                        response.sendRedirect("../pages/dashboard");
                        //request.getRequestDispatcher(view("crm/home"));
                    }else{

                    Optional<Cliente> profiloCliente = clienteDao.fetchAccountById(profilo);
                    System.out.println("qui");
                    Optional<Cliente> ordine=clienteDao.fetchClientsWithOrders(profilo);
                    System.out.println("qui pre 1");
                    Optional<Cliente> categoria=clienteDao.fetchClientsWithCategory(profilo);
                    System.out.println("qui pre 2");
                    SqlCategoriaDao categoriaDao=new SqlCategoriaDao();
                   List<Categoria> categorieTutte=categoriaDao.fetchCategoriesAll();
                    if (profiloCliente.isPresent()) {
                        System.out.println("qui Trovato");
                        request.setAttribute("cliente", profiloCliente.get());

                        request.setAttribute("categorieTutte",categorieTutte);
                        if(ordine.isPresent()) {
                            request.setAttribute("ordini", ordine.get().getOrdini());
                        }
                        if(categoria.isPresent()) {
                            request.setAttribute("categorie", categoria.get().getPreferiti());
                        }
                        request.getRequestDispatcher(view("clienti/profiloCliente")).forward(request, response);
                    }
                    else{
                        notFound();}}
                    break;
                case "/profiloAd": //show profilo admin
                    int profiloAD = getClienteSessione(request.getSession(false)).getId();
                    Optional<Cliente> profiloAdmin = clienteDao.fetchAccountById(profiloAD);
                     System.out.println("in profilo Ad");
                    if (profiloAdmin.isPresent()) {
                        System.out.println("in profilo Ad trovato");
                        request.setAttribute("cliente", profiloAdmin.get());
                        request.getRequestDispatcher(view("clienti/profilo")).forward(request, response);
                    } else
                        notFound();
                    break;
                case "/logout"://logout

                    HttpSession session = request.getSession(false);//sessione falsa per non crearla(se log-out allora già log-in)
                    authenticated(session);
                    ClienteSession clienteSession = (ClienteSession) session.getAttribute("clienteSession");

                   //String redirect = clienteSession.isRuolo() ? "/GiochiEGiocattoli/crm/secret" : "/GiochiEGiocattoli/account/signin";
                    String redirect="../accounts/secret";
                    session.removeAttribute("clienteSession");
                    session.invalidate();
                    response.sendRedirect(redirect);
                    break;




                case "/addPreferiti":
              String cat=request.getParameter("idCategoria");
              int idProfilo = getClienteSessione(request.getSession(false)).getId();
              System.out.println(cat);

                    if (clienteDao.addPreferiti(idProfilo,cat) ){
                        response.sendRedirect("../accounts/profilo");
                    } else
                    {  internalError();}

                    break;
                default:
                    notFound();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (InvalidRequestException ex) {
                log(ex.getMessage());
                ex.handle(request, response);
        }

    }


    @Override
    //alterare stato del server
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
           if(path.compareToIgnoreCase("/secret")==0 && request.getParameter("Mail").compareToIgnoreCase("admin@google.com")!=0){path="/signin";}
           switch (path) {
                case "/secret"://login admin (ricerca nel DB)
                   // System.out.println("ADMIN");
                    request.setAttribute("back", view("crm/secret"));
                    validate(ClienteValidator.validateSignin(request,false));/**/
                  //  System.out.println("creo tmp");
                    Cliente tmpCliente = new Cliente();
                    tmpCliente.setEmail(request.getParameter("Mail"));
                    tmpCliente.setPassword(request.getParameter("password"));
                   // System.out.println("\n"+tmpCliente.getEmail()+" "+tmpCliente.getPassword()+" "+request.getParameter("password"));
                    Optional<Cliente> optionalCliente=clienteDao.findAccount(tmpCliente.getEmail(), tmpCliente.getPassword(), true);
                 //   System.out.println("tornato"+""+optionalCliente.get().getNome());
                    if (optionalCliente.isPresent() && optionalCliente.get().getNome()!=null) {

                        ClienteSession clienteSession = new ClienteSession(optionalCliente.get()); //Meno info cliente=meno info sensibili
                        request.getSession(true).setAttribute("clienteSession", clienteSession);
                        response.sendRedirect("../pages/dashboard");
                    } else {
                        throw new InvalidRequestException("Credenziali non valide", List.of("Credenziali non valide"),
                                HttpServletResponse.SC_BAD_REQUEST);

                    }
                    break;


                case "/create"://creo cliente
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/cliente"));

                    validate(ClienteValidator.validateForm(request, false));
                    request.setAttribute("ruolo",false);
                    Cliente cliente = new ClienteFormMapper().map(request, false);

                    cliente.setPassword(request.getParameter("password"));
                 //   System.out.println(cliente.getPassword());
                    if (clienteDao.createAccount(cliente)) {
                        System.out.println(cliente.getIdCliente());
                        request.setAttribute("cliente",cliente);
                        request.setAttribute("alert", new Alert(List.of("Cliente creato"), "success"));
                        request.getRequestDispatcher(view("crm/cliente")).forward(request, response);
                       // response.sendRedirect("../accounts/");
                    } else {
                        internalError();
                    }
                    break;
                case "/update": //aggiorno cliente

                    authorize(request.getSession(false));
                    request.setAttribute("back", view("cliente/update"));
                    validate(ClienteValidator.validateForm(request, true));
                    Cliente clienteAggiornato=new ClienteFormMapper().map(request,true);
                    request.setAttribute("cliente",clienteAggiornato);
                    if(clienteDao.updateAccount(clienteAggiornato)){

                        request.setAttribute("cliente",clienteAggiornato);
                        request.setAttribute("alert",new Alert(List.of("Cliente Aggiornato!"),"success"));
                       // response.sendRedirect("../accounts/");
                        request.getRequestDispatcher(view("cliente/update")).forward(request, response);

                    }else{internalError();}
                 break;
               case "/modificoCliente": //aggiorno cliente

                   authenticated(request.getSession(false));
                   request.setAttribute("back", view("cliente/updateCliente"));
                   validate(ClienteValidator.validateForm(request, true));
                   Cliente clienteAggiornato1=new ClienteFormMapper().map(request,true);
                   request.setAttribute("cliente",clienteAggiornato1);
                   if(clienteDao.updateAccount(clienteAggiornato1)){

                       request.setAttribute("cliente",clienteAggiornato1);
                       request.setAttribute("alert",new Alert(List.of("Cliente Aggiornato!"),"success"));
                       // response.sendRedirect("../accounts/");
                       request.getRequestDispatcher(view("cliente/updateCliente")).forward(request, response);

                   }else{internalError();}
                   break;
                case "/delete": //delete cliente
                    System.out.println("pronto");
                    authorize(request.getSession(false));
                    System.out.println("delete  pre");
                    request.setAttribute("back", view("crm/cliente"));
                    validate(ClienteValidator.validateDelete(request));
                    //   Cliente clienteDel=new ClienteFormMapper().map(request,true);
                    int id=Integer.parseInt(request.getParameter("id"));
                    System.out.println("in delete "+id);
                    if(clienteDao.deleteAccountById(id)){

                        System.out.println("cancellato");
                        request.setAttribute("alert",new Alert(List.of("Cliente Eliminato!"),"success"));

                        request.getRequestDispatcher(view("crm/delete")).forward(request,response);
                    }else
                        {internalError();}
                    break;


                case "/signupCliente"://registrazione cliente

                    request.setAttribute("back", view("site/signup"));
                    validate(ClienteValidator.validateForm(request,false));

                    Cliente clienteSign=new ClienteFormMapper().map(request,false);
                    clienteSign.setPassword(request.getParameter("password"));
                    System.out.println(clienteSign.getPassword());
                    if(clienteDao.createAccount(clienteSign)){
                        System.out.println("creato");
                       response.sendRedirect("../pages/");
                    }else{internalError();}
                    break;

                case "/signin"://login cliente (ricerca nel DB)

                    request.setAttribute("back", view("crm/secret"));

                    validate(ClienteValidator.validateSignin(request,false));/**/
                    Cliente clienteTmp=new Cliente();
                    clienteTmp.setEmail(request.getParameter("Mail"));
                    clienteTmp.setPassword(request.getParameter("password"));
                    Optional<Cliente>clienteOpt=clienteDao.findAccount(clienteTmp.getEmail(),clienteTmp.getPassword(),false);


                    if(clienteOpt.isPresent() && clienteOpt.get().getNome()!=null){
                        ClienteSession clienteSession=new ClienteSession(clienteOpt.get());
                       // request.setAttribute("alert",new Alert(List.of("Cliente trovato!"),"success"));
                        request.getSession(true).setAttribute("clienteSession", clienteSession);
                        System.out.println("creata sessione");
                        response.sendRedirect("../accounts/profilo");//
                    }else{
                        throw new InvalidRequestException("Credenziali non valide", List.of("Credenziali non valide"),
                                HttpServletResponse.SC_BAD_REQUEST);
                    }
                    break;
               default:
                   notAllowed();
                   break;

            }

        } catch (InvalidRequestException ex) {
            log(ex.getMessage());
            ex.handle(request, response);
        } catch (NoSuchAlgorithmException al) {
            log(al.getMessage());
        } catch (SQLException throwables) {
            log(throwables.getMessage());
        }
    }
}