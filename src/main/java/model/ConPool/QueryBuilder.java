package model.ConPool;

import controller.Http.Condition;
import controller.Http.Operator;
import org.apache.taglibs.standard.lang.jstl.EqualityOperator;

import java.util.List;
import java.util.StringJoiner;

import static controller.Http.Operator.EQ;
import static controller.Http.Operator.QM;

public class QueryBuilder {
    private StringBuffer query;
    private String alias;
    public QueryBuilder(StringBuffer s,String alias){
        this.query=s;
        this.alias=alias;
    }
    public QueryBuilder search(List<Condition> conditionList){ //Concateno condizioni con un AND grazie a stringJoiner
        StringJoiner stringJoiner=new StringJoiner(" AND ");

        for(Condition cs:conditionList){

              stringJoiner.add(String.format("%s.%s %s %s", alias, cs.getNome(),cs.getOperatore(),QM));// QM mettici alias.campo e condizione
                System.out.println("%s.%s%=s "+ alias+" "+ cs.getNome()+" "+" "+QM+"  "+cs.toString());
            }

        query.append(stringJoiner);
        return this;
    }

    public QueryBuilder where(){
        query.append(" WHERE ");return this;
    }

    public String generateQuery() {
        return query.toString();
    }
}
