<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 12/07/21
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean id="produttore" class="model.Produttore.Produttore" scope="request"/>


<%--Se esiste l'alert lo include --%>
<c:if test="${not empty alert}">
    <%@include file="../site/alert.jsp"%>
</c:if>

<form method="post" action="../produttori/create" >

    <fieldset class="grid-x form admin">
        <legend>Crea Produttori</legend>
        <label for="id">
            <input id="id" name="id" placeholder="Id categoria" type="text" value="${produttore.idProduttore}" required pattern="^\w{2,30}$">
        </label>
        <label for="Nome" >
            <input id="Nome" name="Nome" placeholder="Nome" type="text" value="${produttore.nome}" required>
        </label>
        <label for="Mail" >
            <input id="Mail" name="Mail" placeholder="e-mail" type="text" value="${produttore.email}" required>
        </label>
        <button type="submit" class="btn primary">Crea</button>
    </fieldset>
</form>
