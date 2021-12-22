package model.Prodotto;

import controller.Http.RequestValidator;


import javax.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

public class ProdottoValidator {
   public static RequestValidator validateForm(HttpServletRequest request,Boolean update){
        RequestValidator validator=new RequestValidator(request);
        validator.assertMatch("Nome",Pattern.compile("^^[a-zA-Z0-9_ .,)!séèòÃ¨'’àùì…(™®:“”-]{2,50}$"),"Nome compreso tra i 2 e 50 caratteri");
        validator.assertMatch("Categoria",Pattern.compile("^\\w{2,30}$"),"Categoria che deve essere tra i 2 e 10 caratteri");
        validator.assertMatch("Produttore",Pattern.compile("^\\w{2,30}$"), "Produttore che deve essere tra i 2 e 10 caratteri");
        validator.assertDouble("Prezzo","Il prezzo deve essere un numero con la virgola");
        validator.assertInt("Quantita","La quantità deve essere un intero");
        validator.assertInt("etaMinima","L'età deve essere maggiore di 0");
        validator.assertMatch("Descrizione", Pattern.compile("^[a-zA-Z0-9_ .,)!séèò'Ã¨’àùì…(™®:“”-]{2,250}$"),"Descrizione compresa tra i 2 e 250 caratteri");
      if(update){
          validator.assertInt("id","Id deve essere una cifra");
      }
       System.out.println("ERRORI"+validator.getErrors());
         return validator;
    }
    public static RequestValidator validateDelete(HttpServletRequest request){
        RequestValidator validator=new RequestValidator(request);
        validator.assertInt("id","Id deve essere una cifra");
        return validator;
    }
}

