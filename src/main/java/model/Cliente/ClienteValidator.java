package model.Cliente;

import controller.Http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ClienteValidator {
        public static RequestValidator validateForm(HttpServletRequest request,Boolean update){
            RequestValidator validator=new RequestValidator(request);

            validator.assertMatch("Nome", Pattern.compile("^\\w{2,30}$"),"Nome compreso tra i 2 e 30 caratteri");
            validator.assertMatch("Cognome", Pattern.compile("^\\w{2,30}$"),"Cognome compreso tra i 2 e 30 caratteri");
            validator.assertMatch("Via", Pattern.compile("^^[a-zA-Z0-9_ ]{2,30}$"),"Via compresa tra i 2 e 30 caratteri");
            validator.assertMatch("Citta", Pattern.compile("^\\w{2,40}$"),"Citta compresa tra i 2 e 40 caratteri");
            validator.assertMatch("Codice_postale", Pattern.compile("^\\w{2,10}$"),"Codice Postale compreso tra i 2 e 10 caratteri");
            validator.assertEmail("Mail","Email non valida");
            if(update){
                validator.assertInt("id","Id deve essere una cifra");
            }
            System.out.println("ERRORI"+validator.getErrors());
            return validator;
        }

    public static RequestValidator validateSignin(HttpServletRequest request,Boolean update){
        RequestValidator validator=new RequestValidator(request);
        validator.assertEmail("Mail", "Email non valida");

        validator.assertMatch("password", Pattern.compile("^\\w{2,20}$"),"Password compresa tra i 2 e 20 caratteri");
        if(update){
            validator.assertInt("id","Id deve essere una cifra");
        }
        return validator;
    }
    public static RequestValidator validateDelete(HttpServletRequest request){
        RequestValidator validator=new RequestValidator(request);
        validator.assertInt("id","Id deve essere una cifra");
        return validator;
    }
}


