<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 11/07/21
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>

<table class="table">
    <caption class="align-center justify-center">Lista Produttori<a href="../produttori/create" >Crea Produttore</a>
            <form action="../produttori/delete"  method="post">
                <input type="text" name="id" placeholder="Elimina produttore" style="color: black"/>
            </form>
        </caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>E-mail</th>
        <th> </th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${produttori.isEmpty()}">
            <tr>
                <td>Nessun produttore presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${produttori}" var="produttore">
                <tr>
                    <td><a href="../produttori/show?id=${produttore.idProduttore}">${produttore.idProduttore}</a></td>

                    <td>${produttore.nome}</td>

                    <td>${produttore.email}</td>

                    <td><a href="../produttori/update?id=${produttore.idProduttore}" >Modifica produttore</a></td>
                </tr>

            </c:forEach>
        </c:otherwise>
    </c:choose>

    </tbody>


</table>
