<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 10/05/21
  Time: 10:54
  To change this template use File | Settings | File Templates.
  TUTTE INFO ACCOUNT
   <td><a href="../accounts/update?id=${cliente.idCliente}" >Modifica Cliente</a></td>
--%>

<table class="table">
    <caption class="align-center justify-center">Lista Clienti<a href="../accounts/create" >Crea Cliente</a>
        <form action="../accounts/delete" method="post">
            <input type="text" name="id" placeholder="Elimina cliente" style="color: black"/>
            </form>
        </caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>E-mail</th>
        <th>Codice Postale </th>
        <th>Via</th>
        <th>Citta'</th>
        <th>Modifica</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${clienti.isEmpty()}">
            <tr>
                <td>Nessun cliente presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${clienti}" var="cliente">
                <tr>
                    <td><a href="../clienti/show?id=${cliente.idCliente}">${cliente.idCliente}</a></td>

                    <td>${cliente.nome}</td>

                    <td>${cliente.cognome}</td>

                    <td>${cliente.email}</td>

                    <td>${cliente.codice_postale}</td>

                    <td>${cliente.via}</td>

                    <td>${cliente.citta}</td>
                    <td><a href="../accounts/update?id=${cliente.idCliente}" >Modifica Cliente</a></td>
                </tr>

            </c:forEach>
        </c:otherwise>
    </c:choose>

    </tbody>


</table>
