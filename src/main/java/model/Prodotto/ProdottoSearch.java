package model.Prodotto;

import controller.Http.Condition;
import controller.Http.Operator;
import controller.Http.SearchBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ProdottoSearch implements SearchBuilder {
    @Override
    public List<Condition> buildSearch(HttpServletRequest request) {
     List<Condition> conditions=new ArrayList<>();
     Enumeration<String> parameterNames=request.getParameterNames();
     while (parameterNames.hasMoreElements()){
         System.out.println(parameterNames+"*");
         String param=parameterNames.nextElement();
         String value=request.getParameter(param);
         System.out.println(value +" "+param);
         if(value!=null &&!value.isBlank()){
             switch (param){
                 case"nome": conditions.add(new Condition("nome", Operator.MATCH,value)); break;
                 case"idCategoria": conditions.add(new Condition("idCategoria", Operator.EQ,value)); break;
                 case"idProduttore": conditions.add(new Condition("idProduttore", Operator.EQ,value)); break;
                 case"eta_minima": conditions.add(new Condition("eta_Minima", Operator.GE,value)); break;
                 case"minPrezzo": conditions.add(new Condition("prezzo", Operator.GE,value)); break;
                 case"maxPrezzo": conditions.add(new Condition("prezzo", Operator.LE,value)); break;
                 default: break;
             }
             System.out.println("in Prodotto Search "+ conditions.get(0).getValore() +" "+conditions.get(0).getNome());
         }else{System.out.println(" no condizioni");}
     }
     return conditions;
    }
}
