<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 18/07/21
  Time: 21:29
  To change this template use File | Settings | File Templates.
  action="../prodotti/searchProduttore"
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Search"/>
    </jsp:include>

    <style>
        div.prodotto{
            min-height: 580px;
            margin: 5px;
        }
        form.search {
            margin: auto;
        }
        div.prodotto button.btn {
            margin-bottom: 5px;
        }
        select#idProduttore {
            font-size: inherit;
        }
    </style>
    <script src="../js/ajax_search.js"></script>
</head>
<body>
<%@include file="../partials/crm/header.jsp"%>
<main class="app ">
    <section class=" grid-x">
        <form method="get" action="" class="search Barra">
            <label class="field" for="idProduttore">
                <select name="idProduttore" id="idProduttore" onchange="showProduttore(this.value)">
                    <option value="">Tutti</option>
                    <c:forEach items="${produttori}" var="produttore">
                        <option value="${produttore.idProduttore}" name="${produttore.nome}" >${produttore.nome}</option>
                    </c:forEach>
                </select>
            </label>
            <button class="btn primary" type="submit">Cerca</button>
        </form>
        <div id="result"></div>
        <div class="">
            <div>
                <c:forEach items="${prodotti}" var="prodotto">
                    <form action="../carrelli/add?id=${prodotto.idProdotto}" method="post"  class="search">
                    <div class="contenitore">
                        <div class="prodotto">
                            <a href="../prodotti/details?id=${prodotto.idProdotto}">
                                <img src="../images/img/${prodotto.img}" alt="errore" width="300" height="300">
                            <div class="desc">${prodotto.descrizione}<br><b>${prodotto.prezzo}$</b></div>
                            </a>
                            <input type="hidden" value="1" name="Quantita" id="Quantita">
                            <button class="btn primary">Aggiungi al carrello</button>
                        </div>
                    </div>
                    </form>
                </c:forEach>
            </div>
        </div>
        </form>
    </section>

</main>
<%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
