package controller;

import controller.Http.ErrorHandler;
import controller.Http.InvalidRequestException;
import controller.Http.RequestValidator;
import model.Carrello.Carrello;
import model.Cliente.ClienteSession;
import org.json.simple.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class ControllerHttpServlet extends HttpServlet implements ErrorHandler {
    @Resource(name = "jdbc/GiochiEGiocattoli")
    protected static DataSource source;

    protected String getPath(HttpServletRequest req) {
        return req.getPathInfo() != null ? req.getPathInfo() : "/";
    }

    protected String view(String viewPath) {
        String base = getServletContext().getInitParameter("basePath");
        String engine = getServletContext().getInitParameter("engine");
        return base + viewPath + engine;
    }


    protected void validate(RequestValidator validator) throws InvalidRequestException {//Verifica se ci sono errori
        if (validator.hasErrors()) {
            System.out.println("validatore ha errore" + validator.getErrors());
            throw new InvalidRequestException("Validation Errore", validator.getErrors(),
                    HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected String back(HttpServletRequest request) {
        return request.getServletPath() + request.getPathInfo();
    }

    protected Carrello getCarrelloSessione(HttpSession session) {
        return (Carrello) session.getAttribute("clienteCarrello");
    }

    protected ClienteSession getClienteSessione(HttpSession session) {
        return (ClienteSession) session.getAttribute("clienteSession");
    }


    protected int parsePage(HttpServletRequest request) {
        System.out.println(request.getParameter("page"));
        return Integer.parseInt(request.getParameter("page"));
    }
}
