package model.Categoria;

import controller.Http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class CategoriaValidator {
    public static RequestValidator validateForm(HttpServletRequest request,boolean update){
        RequestValidator validator=new RequestValidator(request);
        validator.assertMatch("Tipologia", Pattern.compile("^\\w{2,30}$"),"Tipologia compresa tra i 2 e 30 caratteri");
        validator.assertInt("Eta","L'età deve essere un numero intero");
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


