package controller.Http;

import model.Cliente.Cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FormMapper<T> {
    public T map(HttpServletRequest request, Boolean update);//HttpServletResponse response
}
