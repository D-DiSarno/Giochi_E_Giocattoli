<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 12/07/21
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cliente" class="model.Cliente.Cliente" scope="request"/>


<%--Se esiste l'alert lo include --%>
<c:if test="${not empty alert}">
    <%@include file="../site/alert.jsp"%>
</c:if>

<form method="post" action="../accounts/update" id="crmForm">
    <input name="id" value="${cliente.idCliente}" type="hidden">
    <fieldset class="grid-x form admin">
        <legend>Aggiorna Cliente</legend>
        <label for="Nome">
            <input id="Nome" name="Nome" placeholder="Nome" type="text" value="${cliente.nome}" required>
        </label>
        <label for="Cognome" >
            <input id="Cognome" name="Cognome" placeholder="Cognome" type="text" value="${cliente.cognome}" required>
        </label>
        <label for="Mail">
            <input id="Mail" name="Mail" placeholder="e-mail" type="email" value="${cliente.email}" required>
        </label>
        <label for="Via">
            <input id="Via" name="Via" placeholder="Via" type="text" value="${cliente.via}" required>
        </label>
        <label for="Citta">
            <input id="Citta" name="Citta" placeholder="Citta" type="text" value="${cliente.citta}" required>
        </label>
        <label for="Codice_postale" >
            <input id="Codice_postale" name="Codice_postale" placeholder="Codice_postale" type="text" value="${cliente.codice_postale}" required>
        </label>

         <input type="hidden" name="ruolo" id="ruolo" value="false">
        <button type="submit" class="btn primary">Aggiorna</button>
    </fieldset>
</form>