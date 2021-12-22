<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 28/06/21
  Time: 10:15
  To change this template use File | Settings | File Templates.

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it" dir="lt">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="signup"/>
    </jsp:include>

</head>
<body>
<%@include file="/WEB-INF/views/partials/crm/header.jsp"%>
<h1>Ciao</h1>
<table>
    <form action="/accounts/signup">
        <input type="text" >Nome</input>
         <input type="text" >Cognome</input>
    <input type="text" >e-mail</input>
    <input type="text" >conferma</input>
        <input type="text" >password</input>
        <input type="text" > conf password</input>

    </form>
    <p>Procedendo, confermi di avere letto ed accettato i nostri <a href="">Termini e Condizioni</a> e la nostra <a href="">politica per la Privacy</a>.</p>
    <button type="submit" class="btn primary" >Registrati! </button>
</table>
<%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
--%>