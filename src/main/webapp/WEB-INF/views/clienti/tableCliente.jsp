<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 16/07/21
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>



<table class="table">
    <caption>Ecco le tue informazioni</caption>
    <thead>
    <tr>
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
        <td>${cliente.nome}</td>

        <td>${cliente.cognome}</td>

        <td>${cliente.email}</td>

        <td>${cliente.codice_postale}</td>

        <td>${cliente.via}</td>

        <td>${cliente.citta}</td>
        <td><a href="../accounts/modificoCliente">Modifica</a></td>
    </tr>



    </tbody>
</table>
<table  class="table">
    <caption>Ordini effettuati</caption>
  <thead>
    <tr>
    <th>Data</th>
    <th>Stato</th>
    <th>totale</th>
    <th>Codice Postale </th>
    <th>Via</th>
    <th>Citta'</th>
   </tr>
  </thead>
     <tbody>
 <c:choose>
  <c:when test="${ordini.isEmpty()}">
    <tr>
        <td>Nessun ordine presente!</td>
    </tr>
    </c:when><c:otherwise>

    <c:forEach items="${ordini}" var="ordine">

        <tr>


            <td>${ordine.dataOrdine}</td>

            <td>${ordine.stato_ordine}</td>

            <td>${ordine.totale}</td>

            <td>${ordine.codice_postale}</td>

            <td>${ordine.via}</td>

            <td>${ordine.citta}</td>
        </tr>
    </c:forEach>
    </c:otherwise>
 </c:choose>
     </tbody>
</table>

<table  class="table">
    <caption>Categorie preferite</caption>
        <thead>
        <tr>
            <th>eta minima</th>
            <th>tipologia</th>
            <th>Operazioni</th>
            <th>
            <form method="get" action="../accounts/addPreferiti" class="cell"><p>Aggiungi categoria</p>
            <select name="idCategoria" id="idCategoria" >
                <option value="">Nessuna</option>
                <c:forEach items="${categorieTutte}" var="categoria">
                <option value="${categoria.idCategoria}" name="${categoria.tipologia}" >${categoria.tipologia}</option>
                </c:forEach>
            </select><button type="submit">Conferma!</button></form></th>


        </tr>
        </thead>
    <tbody>
    <c:choose>
        <c:when test="${categorie.isEmpty()}">
            <tr>
                <td>Nessuna categoria presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${categorie}" var="categoria">
                <tr>
                    <td>${categoria.etaMinima}</td>

                    <td>${categoria.tipologia}</td>

                    <td><a href="../prodotti/searchCategoriaPreferita?tipologia=${categoria.tipologia}">Cerca prodotto</a></td>

                </tr>

            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
    </table>
<div class="around">
    <a href="../accounts/logout" style="margin-bottom: 2px">Logout</a>
</div>