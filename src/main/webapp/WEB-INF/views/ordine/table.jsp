<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 11/07/21
  Time: 15:33
  To change this template use File | Settings | File Templates.

--%>

<table class="table">
    <caption class="align-center">Lista Ordini
<%--  <form action="../ordini/delete"  method="post" >
      <input type="text" name="id" placeholder="Elimina Ordine" style="color: black"/>
  </form>--%>
</caption>
  <thead>
  <tr>
      <th>Id</th>
      <th>Via</th>
      <th>Codice Postale</th>
      <th>Città</th>
      <th>Data</th>
      <th>Totale</th>
      <th>Stato</th>
      <th>Azioni</th>
</tr>
</thead>
<tbody>
<c:choose>
<c:when test="${ordini.isEmpty()}">
  <tr>
      <td>Nessun ordine presente!</td>
  </tr>
</c:when>
<c:otherwise>
  <c:forEach items="${ordini}" var="ordine">
      <tr>
          <td><a href="../ordini/show?id=${ordine.numeroOrdine}">${ordine.numeroOrdine}</a></td>

          <td>${ordine.via}</td>

          <td>${ordine.codice_postale}</td>

          <td>${ordine.citta}</td>

          <td>${ordine.dataOrdine}</td>

          <td>${ordine.totale}</td>

          <td>${ordine.stato_ordine}</td>


          <td><a href="../ordini/update?id=${ordine.numeroOrdine}" >Modifica ordine</a></td>

      </tr>

  </c:forEach>
</c:otherwise>
</c:choose>

</tbody>


</table>
