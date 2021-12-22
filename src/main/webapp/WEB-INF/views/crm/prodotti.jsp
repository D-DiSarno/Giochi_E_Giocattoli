<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 10/05/21
  Time: 10:55
  To change this template use File | Settings | File Templates.
   <%@include file="../prodotto/table.jsp"%>
                 <jsp:include page="../partials/paginator.jsp">
                     <jsp:param name="risorse" value="prodotti"/>
                 </jsp:include>
                 <%@include file="../prodotto/table.jsp"%>
                  <%@include file="../prodotto/table.jsp"%>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="GiochiEGiocattoli-Prodotti"/>
        <jsp:param name="style" value="crm,prodotti"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
</head>

<body>
<%@include file="../partials/crm/header.jsp"%>

<main class="app dashboard">

    <%@include file="../partials/crm/sidebar.jsp"%>

    <section class="content grid-y">

        <div class="body grid-x justify-center ">
            <section class="grid-y cell justify-center">
                <%@include file="../prodotto/table.jsp"%>
                <jsp:include page="../partials/paginator.jsp">
                    <jsp:param name="risorse" value="prodotti"/>
                </jsp:include>

            </section>
        </div>

    </section>

</main>
<%@include file="../partials/crm/footer.jsp"%>

</body>
</html>
