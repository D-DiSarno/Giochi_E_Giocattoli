package model.Ordine;

import controller.Http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.regex.Pattern;
public class OrdineValidator {
public static RequestValidator validateForm(HttpServletRequest request){
        RequestValidator validator=new RequestValidator(request);
        validator.assertMatch("citta",Pattern.compile("^\\w{2,40}$"),"Citta compresa tra i 2 e 40 caratteri");
        validator.assertMatch("via",Pattern.compile("^[a-zA-Z0-9_ ]{2,40}$"),"Via che deve essere tra i 2 e 40 caratteri");
        validator.assertMatch("codice_postale",Pattern.compile("^\\w{2,10}$"), "Codice Postale che deve essere tra i 2 e 10 caratteri");
        validator.assertDouble("totale","Il prezzo deve essere un numero con la virgola");
      //  validator.assertInt("quantita","la quantita deve essere un intero");
        return validator;
        }
}
