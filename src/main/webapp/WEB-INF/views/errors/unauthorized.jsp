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
    <Title>401 Unauthorized</Title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/error.css" />


</head>
<body>
<section class="error">
    <h1>Non sei Autorizzato!</h1>
    <h2>Codice 401</h2>
    <p>Accedi per poter entrare in questa pagina</p>
    <a href="../accounts/secret">Vai alla pagina di accesso</a>
</section>
</body>
</html>
