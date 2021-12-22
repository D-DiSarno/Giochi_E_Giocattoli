<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 12/07/21
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="GiochiEGiocattoli-Home"/>
        <jsp:param name="style" value="crm,dashboard"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
    <style>
        .products{
            margin:1rem;
        }
        .product-table >thead{
            background-color: var(--primary);
            color:var(--white);
        }
    </style>

</head>
<body>
<%@include file="../partials/crm/header.jsp"%>

<main class="app dashboard">

    <%@include file="../partials/crm/sidebar.jsp"%>
    <section class="content grid-y">

        <div class="body grid-x justify-center ">
            <%@include file="../produttori/formUpdate.jsp"%>
        </div>

    </section>

</main>

<%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
