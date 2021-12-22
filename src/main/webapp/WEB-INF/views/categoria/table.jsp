<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 06/07/21
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>

<table class="table">
    <caption class="align-center justify-center">Lista Categorie<a href="../categorie/create" >Crea Categoria</a>
            <form action="../categorie/delete"  method="post" >
                <input type="text" name="id" placeholder="Elimina prodotto" style="color: black"/>
            </form></caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Eta' minima</th>
        <th>Tipologia</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${categoria.isEmpty()}">
            <tr>
                <td>Nessuna categoria presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${categorie}" var="categoria">
                <tr>
                    <td><a href="../categorie/show?id=${categoria.idCategoria}">${categoria.idCategoria}</a></td>

                    <td>${categoria.etaMinima}</td>

                    <td>${categoria.tipologia}</td>
                    <td><a href="../categorie/update?id=${categoria.idCategoria}" >Modifica Categoria</a></td>
                </tr>

            </c:forEach>
        </c:otherwise>
    </c:choose>

    </tbody>


</table>
