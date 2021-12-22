package model.Cliente;

import controller.Http.FormMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public class ClienteFormMapper implements FormMapper<Cliente> {
    public Cliente map(HttpServletRequest request,Boolean update)  {
        Cliente cliente=new Cliente();
        cliente.setEmail(request.getParameter("Mail"));
        //System.out.println(cliente.getEmail()+" "+request.getParameter("Mail"));
        cliente.setNome(request.getParameter("Nome"));
        //System.out.println(cliente.getNome()+" "+request.getParameter("Nome"));
        cliente.setCognome(request.getParameter("Cognome"));
        cliente.setVia(request.getParameter("Via"));
        cliente.setCitta(request.getParameter("Citta"));
        cliente.setCodice_postale(request.getParameter("Codice_postale"));
        cliente.setRuolo( Boolean.getBoolean(request.getParameter("ruolo")));
        System.out.println(cliente.getRuolo()+" "+request.getParameter("ruolo"));
        if(update){
            System.out.println();
            cliente.setIdCliente(Integer.parseInt(request.getParameter("id")));
        }
        return cliente;
    }

}
