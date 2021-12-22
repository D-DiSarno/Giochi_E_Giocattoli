<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 12/07/21
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="categoria" class="model.Categoria.Categoria" scope="request"/>

<%--Se esiste l'alert lo include --%>
<c:if test="${not empty alert}">
    <%@include file="../site/alert.jsp"%>
</c:if>

<form method="post" action="../categorie/update">


        <input type="hidden" name="id" value="${categoria.idCategoria}">
    <fieldset class="grid-x form admin">
        <legend>Aggiorna Categoria</legend>
        <label for="Tipologia" >
            <input id="Tipologia" name="Tipologia" placeholder="Tipologia" type="text" value="${categoria.tipologia}" required>
        </label>
        <label for="Eta" >
            <input id="Eta" name="Eta" placeholder="eta' minima" type="text" value="${categoria.etaMinima}" required>
        </label>
        <button type="submit" class="btn primary">Aggiorna</button>
    </fieldset>
</form>