package model.Produttore;

import controller.Http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ProduttoreValidator {

        public static RequestValidator validateForm(HttpServletRequest request, boolean update){
            RequestValidator validator=new RequestValidator(request);

            validator.assertMatch("Nome", Pattern.compile("^\\w{2,20}$"),"Nome compreso tra i 2 e 20 caratteri");
              validator.assertEmail("Mail","E-mail non valida");
            if(update){
                validator.assertMatch("id",Pattern.compile("^\\w{2,30}$"),"Id compreso tra i 2 e i 30 caratteri");
            }
            return validator;
        }
    public static RequestValidator validateDelete(HttpServletRequest request){
        RequestValidator validator=new RequestValidator(request);
        validator.assertMatch("id",Pattern.compile("^\\w{2,30}$"),"Id compreso tra i 2 e i 30 caratteri");

        return validator;
    }
    }




