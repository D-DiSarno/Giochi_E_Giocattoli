<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 16/07/21
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="GiochiEGiocattoli-Profilo_Utente"/>
        <jsp:param name="style" value="crm,profilo"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
    <style>
        caption{
            padding: 20px;
        }
        div.around {
            font-size: large;
            border: 2px solid orange;
            padding: 30px;
            margin: auto;
            background: darkcyan;
            margin-top: 20px;

            border-radius: 50px;
            /* color: white; */
            /* align-items: center; */
        }
    </style>
</head>
<body>
<%@include file="../partials/crm/header.jsp"%>
<main class="app">


    <section class="content grid-y">

        <div class="body grid-x justify-center al">
            <section class="grid-y cell justify-center">
                <%@include file="../clienti/tableCliente.jsp"%>

            </section>
        </div>

    </section>
</main>
    <%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
