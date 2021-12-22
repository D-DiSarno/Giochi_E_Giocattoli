<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 10/05/21
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="GiochiEGiocattoli-Clienti"/>
        <jsp:param name="style" value="crm,clienti"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
</head>



</head>
<body>
<%@include file="../partials/crm/header.jsp"%>

<main class="app dashboard">

    <%@include file="../partials/crm/sidebar.jsp"%>
    <section class="content grid-y">

        <div class="body grid-x justify-center ">
            <section class="grid-y cell justify-center">
                <%@include file="../cliente/table.jsp"%>
                <jsp:include page="../partials/paginator.jsp">
                    <jsp:param name="risorse" value="clienti"/>
                </jsp:include>

            </section>
        </div>

    </section>

</main>
<%@include file="../partials/crm/footer.jsp"%>

</body>
</html>
