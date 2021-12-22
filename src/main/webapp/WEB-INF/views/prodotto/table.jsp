<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 05/07/21
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>


<table class="table">
    <caption class="align-center justify-center">Lista Prodotti<a href="../prodotti/create" >Crea Prodotto</a>
                <form action="../prodotti/delete" method="post" >
                   <input type="text" name="id" placeholder="Elimina prodotto" style="color: black"/>
                </form>
      </caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Url</th>
        <th>Nome</th>
        <th>Prezzo</th>
        <th>Quantita'</th>
        <th>Descrizione</th>

    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${prodotti.isEmpty()}">
            <tr>
                <td>Nessun prodotto presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${prodotti}" var="prodotto">
                <tr>
                    <td><a href="../prodotti/details?id=${prodotto.idProdotto}">${prodotto.idProdotto}</a></td>


                    <td><a href="../../images/img/${prodotto.idProdotto}">${prodotto.img}</a></td>

                    <td>${prodotto.nome}</td>

                    <td>${prodotto.prezzo}</td>

                    <td>${prodotto.quantita}</td>

                    <td class="Descrizione">${prodotto.descrizione}</td>
                  <td><a href="../prodotti/update?id=${prodotto.idProdotto}" >Modifica Prodotto</a><td>
                </tr>

            </c:forEach>
        </c:otherwise>
    </c:choose>

    </tbody>


</table>