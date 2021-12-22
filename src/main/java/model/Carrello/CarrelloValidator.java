package model.Carrello;

import controller.Http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class CarrelloValidator {
    public static RequestValidator validateProduct(HttpServletRequest request) {
        RequestValidator validator=new RequestValidator(request);
        System.out.println(request.getParameter("nome"));
       // validator.assertMatch("Nome",Pattern.compile("^\\w{2,50}$"),"Nome compreso tra i 2 e 50 caratteri");
     //   validator.assertMatch("Categoria",Pattern.compile("^\\w{2,30}$"),"Categoria che deve essere tra i 2 e 30 caratteri");
     //   validator.assertMatch("Produttore",Pattern.compile("^\\w{2,30}$"), "Produttore che deve essere tra i 2 e 30 caratteri");
      //  validator.assertDouble("Prezzo","Il prezzo deve essere un numero con la virgola");
        validator.assertInt("Quantita","La quantità deve essere un intero");
   //     validator.assertMatch("Descrizione", Pattern.compile("^\\w{2,2500}$"),"Descrizione compresa tra i 2 e 250 caratteri");
        return validator;
    }
}
