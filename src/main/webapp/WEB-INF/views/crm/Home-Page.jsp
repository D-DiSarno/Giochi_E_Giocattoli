<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 03/07/21
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html>
<head>

    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="HomePage"/>
    </jsp:include>

</head>
<body>

<%@include file="../partials/crm/header.jsp"%>

<div class="row">
    <p role="tab">I nostri consigli :</p>
    <div class="contenitore">
    <c:forEach items="${prodotti}" var="prodotto">
         <div class="prodotto">
        <a  href="../prodotti/details?id=${prodotto.idProdotto}">
            <img src="../images/img/${prodotto.img}" alt="errore"  width="300" height="300" > </a>
        <div class="desc">${prodotto.descrizione}<br><b>${prodotto.prezzo}$</b></div>

         </div>
    </c:forEach>
    </div>
    <div class="eta">
        <h2>Se preferisci cerca anche in base all'età:</h2>
        <div class="categoriaEta"><a href="../prodotti/searchEta?minNumber=0&&maxNumber=2">0-2 Anni <img src="../icons/typicons.font-2.1.2/src/png-24px/star.png"> </a></div>
        <div class="categoriaEta"><a href="../prodotti/searchEta?minNumber=3&&maxNumber=6">3-6 Anni <img src="../icons/typicons.font-2.1.2/src/png-24px/flow-children.png"></a></div>
        <div class="categoriaEta"><a href="../prodotti/searchEta?minNumber=7&&maxNumber=9">7-9 Anni <img src="../icons/typicons.font-2.1.2/src/png-24px/plane.png"></a></div>
        <div class="categoriaEta"><a href="../prodotti/searchEta?minNumber=10&&maxNumber=99">10+ Anni <img src="../icons/typicons.font-2.1.2/src/png-24px/world.png"></a></div>
    </div>
</div>
<%@include file="../partials/crm/footer.jsp"%>
</body>
</html>