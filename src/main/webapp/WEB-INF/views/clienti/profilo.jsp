<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 29/06/21
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="GiochiEGiocattoli-Profilo"/>
        <jsp:param name="style" value="crm,profilo"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
    <style>
     @media (min-width: 590px) {
         .app{
             display: flex;
         }
         .table{
             font-size: x-large;
         }
     }
    </style>
</head>
<body>
<%@include file="../partials/crm/header.jsp"%>
<main class="app">

    <%@include file="../partials/crm/sidebar.jsp"%>
    <section class="content grid-y">

        <div class="body grid-x justify-center al">
            <section class="grid-y cell justify-center">
                <%@include file="../clienti/table.jsp"%>

            </section>
        </div>

    </section>
</main>
        <%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
