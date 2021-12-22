<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 06/07/21
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="grid-x paginator">
    <c:forEach var="page" begin="1" end="${pages}">
        <li>
            <a href="../${param.risorse}/?page=${page}">${page}</a>
        </li>
    </c:forEach>
</ul>
