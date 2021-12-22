<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 12/07/21
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="prodotto" class="model.Prodotto.Prodotto" scope="request"/>


<%--Se esiste l'alert lo include --%>
<c:if test="${not empty alert}">
    <%@include file="../site/alert.jsp"%>
</c:if>

<form method="post" action="../prodotti/update" >

         <input type="hidden" name="id" value="${prodotto.idProdotto}">
    <fieldset class="grid-x form admin">
        <legend>Aggiorna Prodotto</legend>
        <label for="Nome" >
            <input id="Nome" name="Nome" placeholder="Nome" type="text" value="${prodotto.nome}"
                   pattern="^[a-zA-Z0-9_ .,)!séèò'’àùì…Ã¨(™®:“”-]{2,50}$"
                   title="Nome compreso tra i 2 e 50 caratteri" required>
        </label>
        <label for="Prezzo" >
            <input id="Prezzo" name="Prezzo" placeholder="Prezzo" type="text" value="${prodotto.prezzo}"
                   pattern="([0-9]*[.])?[0-9]+"
                   title="Il prezzo deve essere un numero con la virgola" required>
        </label>
        <label for="Quantita" >
            <input id="Quantita" name="Quantita" placeholder="Quantita'" type="number" value="${prodotto.quantita}" pattern="^\\d+$"
                   title="La quantità deve essere un intero" min="1" required>
        </label>
        <label for="Descrizione" >
            <input id="Descrizione" name="Descrizione" placeholder="Descrizione" type="text" value="${prodotto.descrizione}"
                   pattern="^[a-zA-Z0-9_ .,)!séèò'’àùì…Ã¨(™®:“”-]{2,250}$"
                   title="Descrizione compresa tra i 2 e 250 caratteri" required>
        </label>
        <label for="etaMinima" >
            <input id="etaMinima" name="etaMinima" placeholder="eta Minima" type="number" value="${prodotto.eta_minima}" pattern="^\\d+$" min="1"
                   required>
        </label>
        <label for="Produttore">
            <select name="Produttore" id="Produttore">
                <c:forEach items="${produttori}" var="produttore">
                    <option value="${produttore.idProduttore}">${produttore.nome}</option>
                </c:forEach>
                <%--
                <option value="L1">LegoMarvel</option>
                <option value="L2">LegoStarWars</option>
                <option value="L3">LegoTechnic</option>
                <option value="L4">Clementoni</option>
                <option value="L5">Ravensburger</option>
                <option value="L6">Chicco</option>
                <option value="L7">Playmobil</option>
                <option value="L8">Hasbro</option>
                <option value="L9">Hot Wheels</option>
                <option value="L10">Invincible Heroes</option>
                <option value="L12">Mondo</option>
                <option value="L11">Giochi Preziosi</option>
                --%>
            </select>
        </label>
        <select name="Categoria" id="Categoria">
            <c:forEach items="${categorie}" var="categoria">
            <option value="${categoria.idCategoria}">${categoria.tipologia}</option>
            </c:forEach>
            <%--
            <option value="C1">Action_Figures</option>
            <option value="C2">Bambole</option>
            <option value="C3">Peluche</option>
            <option value="C4">Biciclette</option>
            <option value="C5">Monopattini</option>
            <option value="C6">Cavalcabili</option>
            <option value="C7">Macchinine</option>
            <option value="C8">Soldatini</option>
            <option value="C9">Costruzioni</option>
            <option value="C10">Educativi</option>
            <option value="C11">Sportivi</option>
--%>
</select>
</label>
<label for="img" >
<input type="file" name="img" id="img" value="${prodotto.img}" >
</label>
<button type="submit" class="btn primary">Aggiorna</button>
</fieldset>
</form>