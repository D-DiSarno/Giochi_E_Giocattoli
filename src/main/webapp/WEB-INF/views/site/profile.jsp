
<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 05/07/21
  Time: 17:13
  To change this template use File | Settings | File Templates.
  Profilo ordine
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Visualizza ordini"/>
        <jsp:param name="styles" value="site,ecomerce"/>
    </jsp:include>
<style>
    section.main.grid-x.justify-center {
        margin: auto;
    }
    .table >thead {
        /* visibility: hidden; */
         display: block;
        margin-left: 47px;
    }
    td {
        background: aliceblue;
    }
    .table >tbody td {

        text-align: center;
        display: grid;
        margin: inherit;
        margin-left: 5px;
    }
    table.table * {
         display: flex;
    }
    summary {
        color: orangered;
    }
</style>
</head>
<body>
<%@include file="../partials/crm/header.jsp"%>
<main class="app grid-x">

    <c:if test="${not empty alert}">
        <%@include file="alert.jsp"%>
    </c:if>

    <section class="main grid-x justify-center">
        <div class="cell">
            <h1>Ordini presenti</h1>
            <br>
            <br>
        <c:choose>
            <c:when test="${ordini.isEmpty()}">
                <h2>Non ci sono ordini effettuati</h2>
            </c:when>
            <c:otherwise>
            <c:forEach items="${ordini}" var="ordine">
                 <summary>Data ordine :${ordine.dataOrdine} - Totale : ${ordine.totale}</summary>
                 <table class="table">
                         <thead>
                         <tr>
                             <th>Prodotto</th>
                             <th>Prezzo</th>
                             <th>Quantità</th>
                         </tr>
                         </thead>
                         <tbody>
                          <c:forEach items="${ordine.carrello.elementi}" var="elemento">
                          <tr>
                              <td data-head="Prodotto"><a href="../prodotti/details?id=${elemento.prodotto.idProdotto}">${elemento.prodotto.idProdotto}
                              </a></td>
                              <td data-head="Prezzo">${elemento.prodotto.prezzo}</td>
                              <td data-head="Quantità">${elemento.quantita}</td>
                            </tr>
                          </c:forEach>
                         </tbody>
                 </table>
              </c:forEach>
            </c:otherwise>
        </c:choose>
    </section>
</main>
<%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
