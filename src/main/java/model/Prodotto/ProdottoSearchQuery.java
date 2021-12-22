package model.Prodotto;

import controller.Http.Condition;
import controller.Http.Operator;
import model.ConPool.QueryBuilder;

import java.util.List;
import java.util.StringJoiner;

final class ProdottoSearchQuery{
     private static final  String Prodotto_Tavolo="prodotto";
     private static final String Prodotto_Alias="pro";


    static String search(List<Condition> conditionList){

          StringBuffer query=new StringBuffer("Select * from prodotto as pro INNER JOIN Categoria as cat ON pro.idCategoria=cat.idCategoria INNER JOIN Produttore as p on " +
                  "pro.idProduttore=p.idProduttore ");
         QueryBuilder builder=new QueryBuilder(query,Prodotto_Alias);

          if(!conditionList.isEmpty()){
              builder.where().search(conditionList);
          }
          return builder.generateQuery();
      }


}