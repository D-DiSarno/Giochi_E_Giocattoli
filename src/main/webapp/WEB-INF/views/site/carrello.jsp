
<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 18/07/21
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Carrello"/>
        <jsp:param name="styles" value="site,ecomerce"/>
    </jsp:include>
<style>
    table, thead, tbody, td {
         display: inline-grid;
    }
    section.main {
        margin: auto;
        border: 5px double;
        padding: 30px;
        background-color: azure;
    }
    thead tr{
        display: flex;
    }
    tr {
        display: grid;
        border: 3px solid darkcyan;
    }
    form.carrello {
        display: grid;
        margin: 20px;
    }
    table {
        margin-left: 50px;
    }
    form.cell {
        margin-left: 57px;
    }
    button.end {
        margin-top: 10px;
    }
</style>
</head>
  <body>
  <%@include file="../partials/crm/header.jsp"%>
    <main class="app grid-x">
        <c:choose>
        <c:when test="${empty clienteCarrello or clienteCarrello.elementi.isEmpty()}">
            <h2 style="margin: auto;">Il carrello è attualmente senza prodotti</h2>
        </c:when>
        <c:otherwise>

    <section class="main" style="margin: auto;">
        <h1> Il tuo carrello - Totale:${clienteCarrello.totale()}</h1>
                <table>
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Prezzo</th>
                        <th>Quantità</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${clienteCarrello.elementi}" var="elemento">
                        <td data-head="Nome">${elemento.prodotto.nome}</td>
                        <td data-head="Prezzo">${elemento.prodotto.prezzo}$</td>
                        <td data-head="Quantità">${elemento.quantita}</td>
                        <td data-head="Azioni">
                            <form method="post" action="../carrelli/remove" class="carrello">
                                <input type="hidden" name="id" value="${elemento.prodotto.idProdotto}">
                                <button class="btn primary" type="submit">Rimuovi</button>
                            </form>
                        </td>
                    </c:forEach>

                    </tbody>
                </table>
                <form method="post" action="../ordini/create" class="cell">
                    <button type="submit" class="primary btn end">Completa Ordine</button>
                </form>
            </c:otherwise>
        </c:choose>
    </section>
   </main>
  <%@include file="../partials/crm/footer.jsp"%>
  </body>
</html>
