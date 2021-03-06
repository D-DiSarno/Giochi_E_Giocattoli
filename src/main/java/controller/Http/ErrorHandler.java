package controller.Http;

import model.Cliente.ClienteSession;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ErrorHandler {
    default void authenticated(HttpSession session) throws InvalidRequestException{//Controlla sessione se null o non presente
        if(session==null || session.getAttribute("clienteSession")==null){
            System.out.println(session.getAttribute("clienteSession")+" risulta null");
            throw new InvalidRequestException("Errore autenticazione", List.of("Non sei autenticato"),
            HttpServletResponse.SC_UNAUTHORIZED);

        }
        System.out.println("autenticato");
    }
    default void authorize(HttpSession session)throws InvalidRequestException{
        authenticated(session);
        System.out.println("prendo sessione");
        ClienteSession clienteSession=(ClienteSession) session.getAttribute("clienteSession");
        //controllo se admin
        if(!clienteSession.isRuolo()){
            System.out.println("errore autorizzazione ");
            throw new InvalidRequestException("Errore autorizzazione",List.of("Azione non consentita"),
                    HttpServletResponse.SC_FORBIDDEN);
        }

    }

    default void internalError()throws InvalidRequestException{
    List<String>errori=List.of("Un errore imprevisto è accaduto","Riprova più tardi");
    throw new InvalidRequestException("Errore interno",errori,HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    default void notFound()throws InvalidRequestException{
      throw new InvalidRequestException("Errore interno",List.of("Risorsa non trovata"),
              HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    default void notAllowed()throws InvalidRequestException{
      throw new InvalidRequestException("Operazione non consentita",List.of("Operazione non permessa"),
        HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

}
