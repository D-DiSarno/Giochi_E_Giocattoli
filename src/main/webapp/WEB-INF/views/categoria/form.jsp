<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 06/07/21
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean id="categoria" class="model.Categoria.Categoria" scope="request"/>


<%--Se esiste l'alert lo include --%>
<c:if test="${not empty alert}">
    <%@include file="../site/alert.jsp"%>
</c:if>

<form method="post" action="../categorie/create" >

    <fieldset class="grid-x form admin">
        <legend>Crea Categoria</legend>
        <label for="id" >
            <input id="id" name="id" placeholder="Id categoria" type="text" value="${categoria.idCategoria}" required minlength="2"
            maxlength="30">
        </label>
        <label for="Tipologia" >
            <input id="Tipologia" name="Tipologia" placeholder="Tipologia" type="text" value="${categoria.tipologia}" required>
        </label>
        <label for="Eta">
            <input id="Eta" name="Eta" placeholder="eta' minima" type="text" value="${categoria.etaMinima}" required>
        </label>
        <button type="submit" class="btn primary">Crea</button>
    </fieldset>
</form>