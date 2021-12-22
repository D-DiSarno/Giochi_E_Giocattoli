<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 11/07/21
  Time: 17:04
  To change this template use File | Settings | File Templates.

  ADMIN
   <c:choose>

           <c:when test="${clienti.isEmpty()}">
            <tr>
                <td>Nessun cliente presente!</td>
            </tr>

           </c:when>
            <c:otherwise>
             <c:forEach items="${clienti}" var="cliente">

              </c:forEach>
            </c:otherwise>
        </c:choose>
--%>

 <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>E-mail</th>
            <th>Codice Postale </th>
            <th>Via</th>
            <th>Citta'</th>
        </tr>
        </thead>
        <tbody>

        <tr>
            <td><a href="../clienti/show?id=${cliente.idCliente}">${cliente.idCliente}</a></td>
            <td>${cliente.nome}</td>

            <td>${cliente.cognome}</td>

            <td>${cliente.email}</td>

            <td>${cliente.codice_postale}</td>

            <td>${cliente.via}</td>

            <td>${cliente.citta}</td>
        </tr>

        </tbody>

 </table>