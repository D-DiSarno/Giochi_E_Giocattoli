<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 12/07/21
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="produttore" class="model.Produttore.Produttore" scope="request"/>


<%--Se esiste l'alert lo include --%>
<c:if test="${not empty alert}">
    <%@include file="../site/alert.jsp"%>
</c:if>

<form method="post" action="../produttori/update" >
    <input id="idOriginale" name="idOriginale" type="hidden" value="${produttore.idProduttore}" required>
    <fieldset class="grid-x form admin">
        <legend>Aggiorna Produttore</legend>
        <label for="id" >
            <input id="id" name="id" placeholder="Id categoria" type="text" value="${produttore.idProduttore}" required>
        </label>
        <label for="Nome" >
            <input id="Nome" name="Nome" placeholder="Nome" type="text" value="${produttore.nome}" required>
        </label>
        <label for="Mail">
            <input id="Mail" name="Mail" placeholder="e-mail" type="text" value="${produttore.email}" required>
        </label>
        <button type="submit" class="btn primary">Aggiorna</button>
    </fieldset>
</form>
