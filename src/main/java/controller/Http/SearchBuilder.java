package controller.Http;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SearchBuilder {
    List<Condition> buildSearch(HttpServletRequest request);
}
