<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 12/07/21
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="ordine" class="model.Ordine.Ordine" scope="request"/>


<%--Se esiste l'alert lo include --%>
<c:if test="${not empty alert}">
    <%@include file="../site/alert.jsp"%>
</c:if>

<form method="post" action="../ordini/update">
    <input name="id" value="${ordine.numeroOrdine}" type="hidden">
    <fieldset class="grid-x form admin">
        <legend>Aggiorna Ordine</legend>
        <label for="citta" >
            <input id="citta" name="citta" placeholder="citta" type="text" value="${ordine.citta}" required>
        </label>
        <label for="via" >
            <input id="via" name="via" placeholder="via" type="text" value="${ordine.via}" required>
        </label>
        <label for="codice_postale">
            <input id="codice_postale" name="codice_postale" placeholder="codice postale" type="text" value="${ordine.codice_postale}" required>
        </label>
        <label for="stato_ordine" >
            <input id="stato_ordine" name="stato_ordine" placeholder="stato" type="text" value="${ordine.stato_ordine}" required>
        </label>
        <label for="totale" >
            <input id="totale" name="totale" placeholder="totale" type="text" value="${ordine.totale}" required>
        </label>

        <button type="submit" class="btn primary">Aggiorna</button>
    </fieldset>
</form>