<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 16/05/21
  Time: 11:58
  To change this template use File | Settings | File Templates.
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/error.css" />
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Internal Error</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/error.css" />


</head>
<body>
<section class="error">

    <h1>Errore Interno!</h1>
    <h2>Codice 500</h2>
    <p>Il Server non ha potuto processare la richiesta</p>
    <a href="../pages/">Torna alla home</a>
</section>
</body>
</html>
